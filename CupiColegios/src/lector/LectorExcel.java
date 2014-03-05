package lector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import mundo.Anio;
import mundo.Area;
import mundo.Colegio;
import mundo.Criterio;
import mundo.Departamento;
import mundo.Llave;
import mundo.Municipio;
import mundo.Notas;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import HashTable.TablaHashing;

public class LectorExcel{
	private final static String rutaFolder = "./data/serializados/";
	
	private File archivoExcel;
	
	private String[][] data;
	
	private int columns;
	
	private int rows;
	
	private TablaHashing <Llave,Departamento> departamentos;
	
	/**
	 * Crea un nuevo lector de excel
	 * @param ruta La ruta del archivo de excel
	 * @param rs El numero de filas que tiene el archivo {inclusivo}
	 * @param col El numero de columnas que tiene el archivo {inclusivo}
	 * @param x La coordenada x inicial
	 * @param y La coordenada y inicial
	 */
	public LectorExcel(){
	}
	public void leerDatos(){
		try {
			FileInputStream fis = new FileInputStream(archivoExcel);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while(rowIterator.hasNext() && i <rows){
				Row row = rowIterator.next();
				int j = 0;
				while(j<columns){
					Cell cell = row.getCell(j);
					String cellData = "";
					if(cell == null){
						cellData = "(vacio)";
					}
					else{
						cellData = cell.toString();
					}
					data[i][j] = cellData;
					j++;
				}
				i++;
			}
			fis.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	
	public Anio construirAnio(String ruta, int rs, int colu, int x, int y) throws FileNotFoundException, IOException{
		inicializarAtributos(ruta, rs, colu, x, y);
		inicializarTablasUbicaciones();
		leerDatos();
		int anio = Integer.parseInt(archivoExcel.getName().substring(0, 4));
		TablaHashing<Llave, Colegio> colegios = new TablaHashing<Llave,Colegio>(10000, 2);
		
		for (int i = 1; i < rows; i++){
			String codigoUbicacion = data[i][2];
			int codigoDepartamento = Integer.parseInt(codigoUbicacion.substring(0,2));
			int codigoMunicipio = Integer.parseInt(codigoUbicacion.substring(2,5));
			
			Departamento aAgregar = departamentos.buscar(new Llave(codigoDepartamento));
			Municipio aAgregar2 = aAgregar.buscarMunicipio(new Llave(codigoMunicipio));
			
//		    Notas notas = crearNotas(i);
//			Colegio col = new Colegio(data[i][0],data[i][1],data[i][4],data[i][5],data[i][6],data[i][17],notas,aAgregar2.getNombre(),aAgregar.getNombre());
			
			
			if(aAgregar2 == null){
				System.out.println(i + " No se encontro municipio: " + codigoMunicipio);
			}
			else{
				String jornada = data[i][3].equals("N")?Criterio.NOCTURNA:Criterio.DIURNA;
				Notas notas = crearNotas(i);
				Colegio col = new Colegio(data[i][0],data[i][1],jornada,data[i][4],data[i][5],data[i][6],data[i][17],notas,aAgregar2.getNombre(),aAgregar.getNombre());
				aAgregar.agregarColegio(new Llave(col.getCodigo()), col);
				aAgregar2.agregarColegio(new Llave(col.getCodigo()), col);
				colegios.agregar(new Llave(col.getCodigo()), col);
			}
		}
		System.out.println("Anio " + archivoExcel.getName().substring(0, 4)+ " Creado.");
		return new Anio(anio, colegios, departamentos);
	}

	private Notas crearNotas(int i) {
		Area sociales;
		Area quimica;
		Area fisica;
		Area biologia;
		Area filosofia;
		Area matematicas;
		Area lenguaje;
		Area ingles;
		Area geografia;
		Area historia;
		
		try{
			sociales = new Area(Area.SOCIALES,(int)Double.parseDouble(data[i][7]));
			
		}catch(Exception e){
			sociales = new Area(Area.SOCIALES,Area.NO_APLICA);
		}
		
		try{
			quimica = new Area(Area.QUIMICA,(int)Double.parseDouble(data[i][8]));
		}catch(Exception e){
			quimica = new Area(Area.QUIMICA,Area.NO_APLICA);
		}
		
		try{
			fisica = new Area(Area.FISICA,(int)Double.parseDouble(data[i][9]));
		}catch(Exception e){
			fisica = new Area(Area.FISICA, Area.NO_APLICA);
		}
		
		try{
			biologia = new Area(Area.BIOLOGIA,(int)Double.parseDouble(data[i][10]));
		}catch(Exception e){
			biologia = new Area(Area.BIOLOGIA,Area.NO_APLICA);
		}
		
		try{
			filosofia = new Area(Area.FILOSOFIA,(int)Double.parseDouble(data[i][11]));
		}catch(Exception e){
			filosofia = new Area(Area.FILOSOFIA,Area.NO_APLICA);
		}
		
		try{
			matematicas = new Area(Area.MATEMATICAS,(int)Double.parseDouble(data[i][12]));
		}catch(Exception e){
			matematicas = new Area(Area.MATEMATICAS,Area.NO_APLICA);
		}
		
		try{
			lenguaje = new Area(Area.LENGUAJE,(int)Double.parseDouble(data[i][13]));
		}catch(Exception e){
			lenguaje = new Area(Area.LENGUAJE,Area.NO_APLICA);
		}
		
		try{
			ingles = new Area(Area.INGLES,(int)Double.parseDouble(data[i][14]));
		}catch(Exception e){
			ingles = new Area(Area.INGLES,Area.NO_APLICA);
		}
		
		try{
			geografia = new Area(Area.GEOGRAFIA,(int)Double.parseDouble(data[i][15]));
		}catch(Exception e){
			geografia = new Area(Area.GEOGRAFIA,Area.NO_APLICA);
		}
		
		try{
			historia = new Area(Area.HISTORIA,(int)Double.parseDouble(data[i][16]));
		}catch(Exception e){
			historia = new Area(Area.HISTORIA,Area.NO_APLICA);
		}
		return new Notas(sociales,quimica,fisica,biologia,filosofia,matematicas,lenguaje,ingles,geografia,historia);
	}
	private void inicializarAtributos(String ruta, int rs, int col, int x, int y) {
		archivoExcel = new File (ruta);
		data = new String[rs][col];
		columns = col;
		rows = rs;
	}
	private void inicializarTablasUbicaciones(){
		try {
			departamentos = new TablaHashing<Llave,Departamento>(100,2);
			FileInputStream fis = new FileInputStream("./data/Departamentos y Municipios.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			int i = 0;
			while(rowIterator.hasNext() && i <1121){
				Row row = rowIterator.next();
				int codigoDepartamento = (int)(Double.parseDouble(row.getCell(0).toString()));
				String nombreDepartamento = row.getCell(1).toString();
				int codigoMunicipio = (int)(Double.parseDouble(row.getCell(2).toString()));
				String nombreMunicipio = row.getCell(3).toString();
				Departamento departamento = new Departamento(nombreDepartamento, codigoDepartamento);
				Municipio municipio = new Municipio(nombreMunicipio, codigoMunicipio);
				if(departamentos.buscar(new Llave(codigoDepartamento)) == null){
					departamentos.agregar(new Llave(codigoDepartamento), departamento);
				}
					departamentos.buscar(new Llave(codigoDepartamento)).agregarMunicipio(new Llave(codigoMunicipio), municipio);
				i++;
			}
			fis.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
