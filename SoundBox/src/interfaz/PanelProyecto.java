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
		btnPausar.setBounds(204, 387, 117, 29);
		add(btnPausar);
		
		JButton btnReproducir = new JButton("Reproducir");
		btnReproducir.setActionCommand(Canal.PLAY);
		btnReproducir.setBounds(333, 387, 117, 29);
		add(btnReproducir);
		
		JButton btnParar = new JButton("Parar");
		btnParar.setActionCommand(Canal.STOP);
		btnParar.setBounds(462, 387, 117, 29);
		add(btnParar);
		
		//------------------------------------------------------------------
		JButton btnPrueba = new JButton("Reproducir");
		btnPrueba.setBounds(250, 40, 20, 20);
		btnPrueba.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Sample sonido = (Sample) padre.darSonidos()[0];
				Sample sonido1 = (Sample) padre.darSonidos()[1];
								
				padre.agregarSonidoACanal((Canal) padre.darCanales()[0], sonido);
				padre.agregarSonidoACanal((Canal) padre.darCanales()[1], sonido1);
			}
		});
		add(btnPrueba);
		//------------------------------------------------------------------
		
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
		
		JScrollPane scrollPane = new JScrollPane(panelDibujo);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(16, 65, 563, 302);
		add(scrollPane);
		
		JLabel lblBpm = new JLabel("BPM:");
		lblBpm.setBounds(16, 394, 35, 16);
		add(lblBpm);
		
		textField = new JTextField();
		textField.setBounds(48, 388, 51, 28);
		textField.setEditable(false);
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
		refrescarCanales(padre.darCanales());
	}
	
	public void refrescarCanales(Object[] objects){
		panelDibujo.setCanales(objects);
	}
	
	public void refrescarPanel(Proyecto proyectoActual){
		lblInformacion.setText(proyectoActual.toString());
	}
}
