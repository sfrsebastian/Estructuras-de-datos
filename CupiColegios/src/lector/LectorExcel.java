package lector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import mundo.Colegio;
import mundo.Llave;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import HashTable.TablaHashing;

public class LectorExcel{
	
	private File archivoExcel;
	
	private String[][] data;
	
	private int columns;
	
	private int rows;
	
	private int xcoord;
	
	private int ycoord;
	
	private String[] headers;
	

	/**
	 * Crea un nuevo lector de excel
	 * @param ruta La ruta del archivo de excel
	 * @param rs El numero de filas que tiene el archivo {inclusivo}
	 * @param col El numero de columnas que tiene el archivo {inclusivo}
	 * @param x La coordenada x inicial
	 * @param y La coordenada y inicial
	 */
	public LectorExcel(String ruta, int rs, int col, int x, int y){
		archivoExcel = new File(ruta);
		data = new String[rs][col];
		headers = new String[col];
		columns = col;
		rows = rs;	
		xcoord = x;
		ycoord = y;
	}

//	public String[] leerTitulos(){
//		try{
//			FileInputStream file = new FileInputStream(archivoExcel);
//			Workbook wb = WorkbookFactory.create(file);
//			Sheet sheet = wb.getSheetAt(0);
//
//			//Itera sobre cada fila
//			Row row = sheet.getRow(0);
//			int auxRows = 0;
//			int i = 0;
//			Iterator<Cell> cellIterator = row.cellIterator();
//			while(cellIterator.hasNext() && i<columns){
//				if(i < columns){
//					Cell cell = cellIterator.next();
//					headers[auxRows] = cell.toString();
//					auxRows++;
//				}else{
//					i++;
//				}
//			}
//
//		}catch(Exception e){
//
//		}
//
//		return headers;
//	}

	public void leer(){
		try {
			FileInputStream file = new FileInputStream(archivoExcel);
			Workbook wb = WorkbookFactory.create(file);
			Sheet sheet = wb.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			int i = 0;
			while(rowIterator.hasNext() && i <rows){
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while(cellIterator.hasNext() && j<columns){
					Cell cell = cellIterator.next();
					String cellData = cell.toString();
					if(cellData.equals("")){
						cellData = "(vacio)";
					}
					data[i][j] = cellData;
					j++;
				}
				i++;
			}
			file.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	public TablaHashing<Llave, Colegio> construirTablaHashing() throws FileNotFoundException, IOException{
		
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("./data/colegiosSerializados.col")));
		TablaHashing<Llave, Colegio> tablaColegios = new TablaHashing(7, 2);
		
		for (int i = 1; i < rows; i++){
			Colegio col = new Colegio(data[i][0],data[i][1],data[i][4],data[i][5],data[i][6]);
			tablaColegios.agregar(new Llave(col.getCodigo()), col);
		}
		
		//os.writeObject(tablaColegios);
		os.close();
		
		return tablaColegios;
		
	}
	

	//int id = Integer.parseInt(row.getCell(0).getStringCellValue());
	//String nombre = row.getCell(1).getStringCellValue();
	//					System.out.println("Row "+ contador + ": "+ row.getCell(1).getStringCellValue());
	
	public static void main(String[] args) {
		LectorExcel lector = new LectorExcel("./data/2004.xls", 8860, 19, 0, 1);
		try {
			lector.construirTablaHashing();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("error");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("error 2");
		}
		catch(Exception e){
			System.out.println("error 3");
		}
		
	}

}
