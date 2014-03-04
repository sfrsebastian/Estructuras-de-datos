package interfaz;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JTable;

import mundo.Anio;
import mundo.Colegio;
import mundo.Llave;

public class DialogoResultados extends JDialog {
	
	private InterfazCupiColegios padre;
	
	private Colegio colegio;
	
	private Anio anio;
	
	public DialogoResultados(Colegio colegioActual, Anio nuevoAnio){
		setResizable(false);
		setTitle("Resultados anio " + nuevoAnio.getAnio());
		setSize(new Dimension(500, 150));
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		anio = nuevoAnio;
		colegio = colegioActual;
		
		Colegio colegioAnio = anio.getColegios().buscar(new Llave(colegioActual.getCodigo()));
		String[][] datos = colegioAnio.getNotas().darMatriz();
		
		String[] cols = {"Materia","Puntaje"};
		
		JTable table = new JTable(datos, cols);
		table.setBounds(0, 0, 500, 125);
		add(table);
	}
	
	public void setParent(InterfazCupiColegios self){
		padre = self;
	}
}
