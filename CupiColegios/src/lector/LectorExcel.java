package lector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import mundo.Colegio;
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
		File file = new File(rutaFolder);
		if(!file.exists()){
			file.mkdirs();
		}
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
	
	
	public void construirAnio(String ruta, int rs, int colu, int x, int y) throws FileNotFoundException, IOException{
		inicializarAtributos(ruta, rs, colu, x, y);
		inicializarTablasUbicaciones();
		leerDatos();
		File archivo = new File(rutaFolder + archivoExcel.getName().substring(0, 4)+".col"); 
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
		TablaHashing<Llave, Colegio> tablaColegios = new TablaHashing<Llave,Colegio>(7, 2);
		
		for (int i = 1; i < rows; i++){
			String codigoUbicacion = data[i][2];
			int codigoDepartamento = Integer.parseInt(codigoUbicacion.substring(0,2));
			int codigoMunicipio = Integer.parseInt(codigoUbicacion.substring(2,5));
			Notas notas = crearNotas(i);
			Colegio col = new Colegio(data[i][0],data[i][1],data[i][4],data[i][5],data[i][6],data[i][17],notas);
			Departamento aAgregar = departamentos.buscar(new Llave(codigoDepartamento));
			if(aAgregar == null){
				System.out.println(i + " No se encontro departamento: " + codigoDepartamento);
			}
			else{
				aAgregar.agregarColegio(new Llave(col.getCodigo()), col);
				Municipio aAgregar2 = aAgregar.buscarMunicipio(new Llave(codigoMunicipio));
				if(aAgregar2 == null){
					System.out.println(i + " No se encontro municipio: " + codigoMunicipio);
				}
				else{
					aAgregar2.agregarColegio(new Llave(col.getCodigo()), col);
				}
			}
			tablaColegios.agregar(new Llave(col.getCodigo()), col);
		}
		
		oos.writeObject(tablaColegios);
		oos.writeObject(departamentos);
		oos.close();	
		System.out.println("Colegios " + archivoExcel.getName().substring(0, 4)+ " serializados.");
	}
	
	private Notas crearNotas(int i) {
		int sociales;
		int quimica;
		int fisica;
		int biologia;
		int filosofia;
		int matematicas;
		int lenguaje;
		int ingles;
		int geografia;
		int historia;
		
		try{
			sociales = (int)Double.parseDouble(data[i][7]);
		}catch(Exception e){
			sociales = Notas.NO_APLICA;
		}
		
		try{
			quimica = (int)Double.parseDouble(data[i][8]);
		}catch(Exception e){
			quimica = Notas.NO_APLICA;
		}
		
		try{
			fisica = (int)Double.parseDouble(data[i][9]);
		}catch(Exception e){
			fisica = Notas.NO_APLICA;
		}
		
		try{
			biologia = (int)Double.parseDouble(data[i][10]);
		}catch(Exception e){
			biologia = Notas.NO_APLICA;
		}
		
		try{
			filosofia = (int)Double.parseDouble(data[i][11]);
		}catch(Exception e){
			filosofia = Notas.NO_APLICA;
		}
		
		try{
			matematicas = (int)Double.parseDouble(data[i][12]);
		}catch(Exception e){
			matematicas = Notas.NO_APLICA;
		}
		
		try{
			lenguaje = (int)Double.parseDouble(data[i][13]);
		}catch(Exception e){
			lenguaje = Notas.NO_APLICA;
		}
		
		try{
			ingles = (int)Double.parseDouble(data[i][14]);
		}catch(Exception e){
			ingles = Notas.NO_APLICA;
		}
		
		try{
			geografia = (int)Double.parseDouble(data[i][15]);
		}catch(Exception e){
			geografia = Notas.NO_APLICA;
		}
		
		try{
			historia = (int)Double.parseDouble(data[i][16]);
		}catch(Exception e){
			historia = Notas.NO_APLICA;
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
			departamentos = new TablaHashing<Llave,Departamento>(7,2);
			FileInputStream fis = new FileInputStream("./data/Departamentos y Municipios.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			int i = 0;
			while(rowIterator.hasNext() && i <1121){
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
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
	public static void main(String[] args) {
		LectorExcel lector = new LectorExcel();
		try {
			
			lector.construirAnio("./data/2004.xls", 8861, 19, 0, 1);
			lector.construirAnio("./data/2005.xls", 8838, 19, 0, 1);
			lector.construirAnio("./data/2006.xls", 9250, 19, 0, 1);
			lector.construirAnio("./data/2007.xls", 9681, 19, 0, 1);
			lector.construirAnio("./data/2008.xls", 10161, 19, 0, 1);
			lector.construirAnio("./data/2009.xls", 10376, 19, 0, 1);
			lector.construirAnio("./data/2010.xls", 10975, 19, 0, 1);
			lector.construirAnio("./data/2011.xls", 8587, 19, 0, 1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
