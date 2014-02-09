package interfaz;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PanelProyecto extends JPanel {

	private InterfazCupiSoundBox padre;
	private JTextField textField;
	
	public PanelProyecto(){
		setBorder(new TitledBorder(null, "Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setSize(585,444);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre, Autor, Pos Actual - 00:00");
		lblNewLabel.setBounds(16, 37, 299, 16);
		add(lblNewLabel);
		
		JButton btnPausar = new JButton("Pausar");
		btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.pausar();
			}
		});
		btnPausar.setBounds(204, 387, 117, 29);
		add(btnPausar);
		
		JButton btnReproducir = new JButton("Reproducir");
		btnReproducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.reproducir();
			}
		});
		btnReproducir.setBounds(333, 387, 117, 29);
		add(btnReproducir);
		
		JButton btnParar = new JButton("Parar");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.parar();
			}
		});
		btnParar.setBounds(462, 387, 117, 29);
		add(btnParar);
		
		JButton btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogoOpcionesProyecto dialogoOpcionesProyecto = new DialogoOpcionesProyecto();
				dialogoOpcionesProyecto.setPadre(padre);
				dialogoOpcionesProyecto.setVisible(true);
			}
		});
		btnOpciones.setBounds(333, 32, 117, 29);
		add(btnOpciones);
		
		JButton btnCanal = new JButton("+/- Canal");
		btnCanal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogoAgregarEliminarCanal dialogoAgregarEliminarCanal = new DialogoAgregarEliminarCanal();
				dialogoAgregarEliminarCanal.setPadre(padre);
				dialogoAgregarEliminarCanal.setVisible(true);
			}
		});
		btnCanal.setBounds(462, 32, 117, 29);
		add(btnCanal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 65, 563, 302);
		add(scrollPane);
		
		JLabel lblBpm = new JLabel("BPM:");
		lblBpm.setBounds(16, 394, 35, 16);
		add(lblBpm);
		
		textField = new JTextField();
		textField.setBounds(48, 388, 51, 28);
		add(textField);
		textField.setColumns(10);
		
		JButton btnAumentar = new JButton("+");
		btnAumentar.setBounds(100, 389, 51, 29);
		add(btnAumentar);
		
		JButton btnDisminuir = new JButton("-");
		btnDisminuir.setBounds(146, 389, 51, 29);
		add(btnDisminuir);
	}
	
	public void setParent(InterfazCupiSoundBox interfaz){
		padre = interfaz;
	}
}
