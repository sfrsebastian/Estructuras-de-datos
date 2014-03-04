package interfaz;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


public class GraficoTorta extends JFrame{
	private String[][] datos;

	public GraficoTorta(String[][] nDatos, String titulo) {
		datos = nDatos;
		PieDataset dataset = crearDatos();
		JFreeChart chart = crearGrafico(dataset, titulo);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	private  PieDataset crearDatos() {
		DefaultPieDataset result = new DefaultPieDataset();
		for (int i = 0; i < datos.length; i++) {
			result.setValue(datos[i][0], Double.parseDouble(datos[i][1]));
		}
		return result;
	}

	private JFreeChart crearGrafico(PieDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true,true);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setInteriorGap(0.02);
		plot.setLabelGenerator(null);
		return chart;

	}
} 