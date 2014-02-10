package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import mundo.Canal;
import mundo.Proyecto;
import mundo.Reproductor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class InterfazCupiSoundBox extends JFrame{

	//------------------------------------
	// Atributos
	//------------------------------------
	
	private static InterfazCupiSoundBox self;
	private Reproductor reproductor;
	private PanelProyectosGuardados panelProyectosGuardados;
	private PanelProyecto panelProyecto;
	
	//------------------------------------
	// Constructor
	//------------------------------------
	
	public InterfazCupiSoundBox(){
		
		reproductor = new Reproductor();
		
		setSize(800, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JMenuBar menu = new JMenuBar();
		
		JMenu nuevoProyecto = new JMenu("Nuevo");
	
		menu.add(nuevoProyecto);
		
		JMenuItem itmNuevoProyecto = new JMenuItem("Nuevo Proyecto");
		itmNuevoProyecto.setMnemonic(KeyEvent.VK_N);
		itmNuevoProyecto.setToolTipText("Hola");
		itmNuevoProyecto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogoNuevoProyecto dialogoNuevoProyecto = new DialogoNuevoProyecto();
				dialogoNuevoProyecto.setParent(self);
				dialogoNuevoProyecto.setVisible(true);
			}
		});	
		nuevoProyecto.add(itmNuevoProyecto);
		
		JMenuItem itmBibliotecaSonidos = new JMenuItem("Biblioteca de Sonidos");
		itmBibliotecaSonidos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogoBibliotecaSonidos dialogoBibliotecaSonidos = new DialogoBibliotecaSonidos(false);
				dialogoBibliotecaSonidos.setParent(self);
				dialogoBibliotecaSonidos.setVisible(true);
			}
		});
		nuevoProyecto.add(itmBibliotecaSonidos);
		
		JMenuItem itmCategorias = new JMenuItem("Manejar Categorias");
		itmCategorias.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DialogoManejarCategorias dialogoManejarCategorias = new DialogoManejarCategorias();
				dialogoManejarCategorias.setPadre(self);
				dialogoManejarCategorias.setVisible(true);
			}
		});
		nuevoProyecto.add(itmCategorias);
		
		nuevoProyecto.addSeparator();
		
		JMenuItem itmGuardarProyecto = new JMenuItem("Guardar Proyecto");
		itmGuardarProyecto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				salvarProyecto();
			}
		});
		nuevoProyecto.add(itmGuardarProyecto);
		
		setJMenuBar(menu);
		getContentPane().setLayout(null);
		nuevoProyecto.addSeparator();
		
		JMenuItem itmSalir = new JMenuItem("Cerrar");
		itmSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});
		nuevoProyecto.add(itmSalir);
		
		panelProyectosGuardados = new PanelProyectosGuardados();
		panelProyectosGuardados.setBounds(6, 6, 191, 444);
		getContentPane().add(panelProyectosGuardados);
		panelProyectosGuardados.setParent(this);
		
		panelProyecto = new PanelProyecto();
		panelProyecto.setBounds(209, 6, 585, 444);
		getContentPane().add(panelProyecto);
		panelProyecto.setParent(this);
	}
	
	//------------------------------------
	// Metodos
	//------------------------------------
	
	private void salir(){
		this.dispose();
	}
	
	public void crearProyecto(String nombre, int bpm, String autor, int canales){
		//TODO agregar un nuevo proyecto
	}
	
	public void salvarProyecto(){
		//TODO guardar proyecto
	}
	
	//------------------------------------
	// Action Listener
	//------------------------------------
	
	
	
	//------------------------------------
	// Main
	//------------------------------------
	
	public static void main(String[] args) {
		InterfazCupiSoundBox interfaz = new InterfazCupiSoundBox();
		self = interfaz;
		interfaz.setVisible(true);
	}

	public void pausar() {
		//TODO  pausar 
	}
	
	public void reproducir(){
		// TODO reproducir
	}
	
	public void parar(){
		//TODO parar
	}

	/**
	 * 
	 * @param nombre
	 * @param autor
	 */
	public void editarProyecto(String nombre, String autor) {
		Proyecto proyecto = reproductor.darProyectoActual();
		proyecto.cambiarAutor(autor);
		proyecto.cambiarNombre(nombre);
	}

	public void filtrarProyectos(String filtro, String combo) {
		// TODO Auto-generated method stub
	}

	public void agregarCategoria(String categoria) {
		// TODO Auto-generated method stub
		
	}

	public void eliminarCategoria(String categoriaEliminada) {
		// TODO Auto-generated method stub
		
	}
	
	public double darBPM(){
		//return reproductor.darProyectoActual().darBPM();
		return 0;
	}

	public String[] darCategorias() {
		// TODO Auto-generated method stub
		//return null;
		String[] hola = {"cat1","cat2","cat3","cat4"};
		return hola;
	}

	public void filtrarCategorias(String filtro) {
		// TODO Auto-generated method stub
		
	}

	public Object[] darCanales() {
//		Object[] canales = reproductor.darProyectoActual().darCanales();
//		return canales;
		return null;
	}

	/**
	 * 
	 * @param canalAEliminar
	 */
	public void eliminarCanal(Canal canalAEliminar) {
		reproductor.darProyectoActual().eliminarCanal(canalAEliminar);
	}

	/**
	 * 
	 * @param canal
	 */
	public void agregarCanal(String canal) {
		reproductor.darProyectoActual().agregarCanal(new Canal(canal));
	}

	public String[] darSonidos() {
		String[] hola = {"cat1","cat2","cat3","cat4"};
		return hola;
	}

	public void filtrarSonidos(String tipoFiltro, String filtro) {
		// TODO Auto-generated method stub
		
	}

	public void escogerArchivo() {
		// TODO Auto-generated method stub
	}

	public void escogerCarpeta() {
		// TODO Auto-generated method stub
		
	}

	public void agregarSonidoACanal(String sonido) {
		// TODO Auto-generated method stub
		//Ni idea!!!!!!!!!!!!!!!!!!!
	}
}
