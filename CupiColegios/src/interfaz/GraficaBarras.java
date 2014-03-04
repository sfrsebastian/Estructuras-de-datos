package interfaz;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraficaBarras extends JFrame{
	private String[][] datos;

	public GraficaBarras(String[][] nDatos, String titulo, String ejex, String ejey) {
		datos = nDatos;
		CategoryDataset dataset = crearDatos();
		JFreeChart chart = crearGrafico(dataset, titulo,ejex,ejey);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartPanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	private  CategoryDataset crearDatos() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		for (int i = 0; i < datos.length; i++) {
			result.addValue(Double.parseDouble(datos[i][1]),"Promedio",datos[i][0]);
		}
		return result;
	}

	private JFreeChart crearGrafico(CategoryDataset dataset, String title, String ejex, String ejey) {
		JFreeChart chart = ChartFactory.createBarChart(title, ejex, ejey, dataset,PlotOrientation.VERTICAL,false, true, false);
		return chart;

	}
} 