package interfaz;

import java.awt.Color;
import java.awt.Dimension;
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
		setPreferredSize(new Dimension(1000, 800));
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
				JButton boton = new JButton("" + actual.darNombre());
				boton.setBounds(0, i*72, 100, 70);
				boton.setActionCommand(""+i);
				boton.addActionListener(this);
				add(boton);
				
				if(samples != null){
					for (int j = 0; j < samples.length; j++) {
						Sample samplito = (Sample) samples[j];
						
						JLabel lblSonido = new JLabel(samplito.darNombre());
						lblSonido.setOpaque(true);
						Color color = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
						lblSonido.setBackground(color);
						lblSonido.setBounds((j*103)+100, i*72, 100, 70);
						add(lblSonido);
					}
				}
			}
		}
		repaint();
	}
	
	public void agregarSonidosACanal(Canal nCanal, Object[] sonidos){
		padre.agregarSonidoACanal(nCanal, sonidos);
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		int c = Integer.parseInt(comando);
		Canal canal = (Canal)listaCanales[c];
		
		DialogoEditarCanal dialogoEditarCanal = new DialogoEditarCanal(canal,this);
		dialogoEditarCanal.setParent(padre);
		dialogoEditarCanal.setVisible(true);
	}


}
