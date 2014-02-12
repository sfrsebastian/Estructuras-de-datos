package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mundo.Canal;
import mundo.Sample;

public class PanelDibujo extends JPanel implements ActionListener {

	private InterfazCupiSoundBox padre;
	
	private Object[] listaCanales;
	
	public PanelDibujo(){
		setLayout(null);
		setPreferredSize(new Dimension(2000, 2000));
	}
	
	public void setParent(InterfazCupiSoundBox interfaz) {
		padre = interfaz;
	}
	
	public void limpiarPanel(){
		this.removeAll();
	}

	public void dibujarSonidos() {
		listaCanales = padre.darCanales();
		limpiarPanel();
		if(listaCanales != null){
			for (int i = 0; i < listaCanales.length; i++) {
				Canal actual = (Canal)listaCanales[i];
				Object[] samples = actual.darSonidos();
				JButton boton = new JButton("editar: " + actual.darNombre());
				boton.setBounds(0, i*52, 100, 50);
				boton.setActionCommand(""+i);
				boton.addActionListener(this);
				add(boton);

				if(samples != null){
					for (int j = 0; j < samples.length; j++) {
						Sample samplito = (Sample) samples[j];
						JLabel lblSonido = new JLabel(samplito.darNombre());
						lblSonido.setOpaque(true);
						lblSonido.setBackground(Color.cyan);
						lblSonido.setBounds((j*103)+100, i*52, 100, 50);
						add(lblSonido);
					}
				}
			}
		}
	}
	
	public void agregarSonidosACanal(Canal nCanal, Object[] sonidos){
		padre.agregarSonidoACanal(nCanal, sonidos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		int c = Integer.parseInt(comando);
		Canal canal = (Canal)listaCanales[c];
		
		DialogoEditarCanal dialogoEditarCanal = new DialogoEditarCanal(canal,this);
		dialogoEditarCanal.setParent(padre);
		dialogoEditarCanal.setVisible(true);
	}


}
