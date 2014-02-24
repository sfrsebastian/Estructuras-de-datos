package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



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

	public String[] leerTitulos(){
		try{
			FileInputStream file = new FileInputStream(archivoExcel);
			//Get the workbook instance for XLS file 
			Workbook wb = WorkbookFactory.create(file);		 
			//Get first sheet from the workbook
			Sheet sheet = wb.getSheetAt(0);

			//Iterate through each rows from first sheet
			Row row = sheet.getRow(0);
			int auxRows = 0;
			int i = 0;
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()){
				if(i < columns){
					Cell cell = cellIterator.next();
					headers[auxRows] = cell.toString();
					auxRows++;
				}else{
					i++;
				}
			}

		}catch(Exception e){

		}

		return headers;
	}

	public String[][] leer(){
		try {
			FileInputStream file = new FileInputStream(archivoExcel);

			//Get the workbook instance for XLS file 
			Workbook wb = WorkbookFactory.create(file);		 
			//Get first sheet from the workbook
			Sheet sheet = wb.getSheetAt(0);

			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			int auxRows = 0;
			while(rowIterator.hasNext()) 
			{
				if(i <= rows && i >= ycoord)
				{
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					int j = 0;
					int auxCols = 0;
					while(cellIterator.hasNext())
					{
						if(j <= columns && j >= xcoord)
						{
							Cell cell = cellIterator.next();
							data[auxRows][auxCols] = cell.toString();
							auxCols++;
						}
						else
						{
							cellIterator.next();
						}
						j++;
					}
					auxRows++;
				}
				else{
					rowIterator.next();
				}
				i++;
			}
			file.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return data;
	}
	

	//int id = Integer.parseInt(row.getCell(0).getStringCellValue());
	//String nombre = row.getCell(1).getStringCellValue();
	//					System.out.println("Row "+ contador + ": "+ row.getCell(1).getStringCellValue());
	
	public String[][] darDataColegios(){
		String[][] data = new String[50][50];
		//	for(int i = 0; i < colegios.size(); i++){
		//		Colegio actual = colegios.get(i);
		//		data[i][0] = actual.darCodigo()+"";
		//		data[i][1] = actual.darNombre();
		//	}
		return data;
	}

	public static void main(String[] args) {
		LectorExcel lector = new LectorExcel("./data/text.xls",6,3,1,1);
		String[][] datos = lector.leer();
	}

}
