package interfaz;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JTextField;

import mundo.Canal;
import mundo.Proyecto;
import mundo.Sample;

public class PanelProyecto extends JPanel {

	private InterfazCupiSoundBox padre;
	private JTextField textField;
	private JLabel lblInformacion;
	private PanelDibujo panelDibujo;
	
	public PanelProyecto(){
		setBorder(new TitledBorder(null, "Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setSize(585,444);
		setLayout(null);
		
		lblInformacion = new JLabel("Nombre, Autor, Pos Actual - 00:00");
		lblInformacion.setBounds(16, 37, 299, 16);
		add(lblInformacion);
		
		JButton btnPausar = new JButton("Pausar");
		btnPausar.setActionCommand(Canal.PAUSE);
		btnPausar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.pausar();	
			}
		});
		btnPausar.setBounds(204, 387, 117, 29);
		add(btnPausar);
		
		JButton btnReproducir = new JButton("Reproducir");
		btnReproducir.setActionCommand(Canal.PLAY);
		btnReproducir.setBounds(333, 387, 117, 29);
		btnReproducir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.reproducir();
			}
		});
		add(btnReproducir);
		
		JButton btnParar = new JButton("Parar");
		btnParar.setActionCommand(Canal.STOP);
		btnParar.setBounds(462, 387, 117, 29);
		btnParar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.parar();
			}
		});
		add(btnParar);
		
		JButton btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(padre.tieneProyecto()){
				DialogoOpcionesProyecto dialogoOpcionesProyecto = new DialogoOpcionesProyecto();
				dialogoOpcionesProyecto.setPadre(padre);
				dialogoOpcionesProyecto.setVisible(true);
				}else{
					mostrarError("No hay proyecto actual");
				}

			}
		});
		btnOpciones.setBounds(333, 32, 117, 29);
		add(btnOpciones);

		JButton btnCanal = new JButton("+/- Canal");
		btnCanal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!padre.tieneProyecto()){
					mostrarError("No hay proyecto abierto");
				}
				else{
					DialogoAgregarEliminarCanal dialogoAgregarEliminarCanal = new DialogoAgregarEliminarCanal();
					dialogoAgregarEliminarCanal.setPadre(padre);
					dialogoAgregarEliminarCanal.setVisible(true);
				}
			}
		});
		btnCanal.setBounds(462, 32, 117, 29);
		add(btnCanal);
		
		panelDibujo = new PanelDibujo();
		panelDibujo.setParent(padre);
		
		final JScrollPane scrollPane = new JScrollPane(panelDibujo);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(16, 65, 563, 302);
		scrollPane.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				// TODO Auto-generated method stub
				panelDibujo.repaint();
				panelDibujo.dibujarSonidos();
				scrollPane.repaint();
			}
		});
		add(scrollPane);
		
		JLabel lblBpm = new JLabel("BPM:");
		lblBpm.setBounds(16, 394, 35, 16);
		add(lblBpm);
		
		textField = new JTextField();
		textField.setBounds(48, 388, 51, 28);
		textField.setEditable(false);
		textField.setText("1.0");
		add(textField);
		textField.setColumns(10);
		
		JButton btnAumentar = new JButton("+");
		btnAumentar.setActionCommand(Canal.AUMENTAR_BPM);
		btnAumentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("" + padre.darBPM());
				
			}
		});		
		btnAumentar.setBounds(100, 389, 51, 29);
		add(btnAumentar);
		
		JButton btnDisminuir = new JButton("-");
		btnDisminuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("" + padre.darBPM());
				
			}
		});	
		btnDisminuir.setBounds(146, 389, 51, 29);
		add(btnDisminuir);
	}
	
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Hola", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setParent(InterfazCupiSoundBox interfaz){
		padre = interfaz;
		panelDibujo.setParent(padre);
		panelDibujo.dibujarSonidos();
	}

	public void refrescarPanelDibujo(){
		panelDibujo.dibujarSonidos();
	}
	
	public void refrescarPanel(Proyecto proyectoActual){
		lblInformacion.setText(proyectoActual.toString());
	}

	public void limpiar() {
		lblInformacion.setText("Nombre, Autor, Pos Actual - 00:00");
	}

	public void limpiarPanelDibujo() {
		panelDibujo.limpiarPanel();
		panelDibujo.repaint();
	}
}
