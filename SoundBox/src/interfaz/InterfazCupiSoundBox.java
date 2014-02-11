package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import mundo.Canal;
import mundo.Categoria;
import mundo.Proyecto;
import mundo.Reproductor;
import mundo.Sample;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import org.omg.CORBA.RepositoryIdHelper;

public class InterfazCupiSoundBox extends JFrame{

	//------------------------------------
	// Atributos
	//------------------------------------
	
	private static InterfazCupiSoundBox self;
	private Reproductor reproductor;
	private PanelProyectosGuardados panelProyectosGuardados;
	private PanelProyecto panelProyecto;
	
	private Proyecto proyectoActual;
	
	//------------------------------------
	// Constructor
	//------------------------------------
	
	public InterfazCupiSoundBox(){
		
		reproductor = new Reproductor();
		proyectoActual = reproductor.darProyectoActual();
		
		setSize(800, 500);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JMenuBar menu = new JMenuBar();
		
		JMenu nuevoProyecto = new JMenu("Opciones");
	
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
	
	private void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Hola", JOptionPane.ERROR_MESSAGE);
	}
	
	public boolean tieneProyecto(){
		return (proyectoActual == null) ? false : true;
	}
	
	//------------------------------------
	
	/**
	 * 
	 * @param nombre
	 * @param autor
	 * @param canales
	 */
	public void crearProyecto(String nombre, String autor, int canales){
		proyectoActual = reproductor.agregarProyecto(nombre,autor,canales);
		panelProyectosGuardados.refrescarListaProyectos(reproductor.darProyectos());
		panelProyecto.refrescarPanel(proyectoActual);
	}
	
	/**
	 * Guarda el proyecto actual
	 */
	public void salvarProyecto(){
		if(proyectoActual != null)
			 proyectoActual.guardarProyecto();
		else
			mostrarError("No hay proyecto cargado");
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
	 * Edita el proyecto dado un nombre y autor dados por parametro
	 * @param nombre El nombre del proyecto
	 * @param autor El autor del proyecto
	 */
	public void editarProyecto(String nombre, String autor) {
		Proyecto proyecto = proyectoActual;
		proyecto.cambiarAutor(autor);
		proyecto.cambiarNombre(nombre);
		panelProyecto.refrescarPanel(proyectoActual);
		panelProyectosGuardados.refrescarListaProyectos(reproductor.darProyectos());
	}

	/**
	 * 
	 * @param filtro
	 * @param combo
	 * @return
	 */
	public Proyecto filtrarProyectos(String filtro, String combo) {
		if(combo.equals("Nombre")){
			return reproductor.buscarProyectoPorNombre(filtro);
		}
		else{
			return reproductor.buscarProyectoPorAutor(filtro);
		}
	}

	public void agregarCategoria(String categoria) {
		reproductor.agregarCategoria(new Categoria(categoria));
	}
	
	public void agregarCategoriaASonido(Sample sonido, Categoria cat){
		reproductor.asignarCategoria(cat, sonido);
	}

	public void eliminarCategoria(Categoria categoriaEliminada) {
		reproductor.eliminarCategoria(categoriaEliminada);
	}
	
	public double darBPM(){
		//return reproductor.darProyectoActual().darBPM();
		return 0;
	}
	
	public void eliminarProyecto(){
		//TODO eliminar proyecto!!
	}

	public Object[] darCategorias() {
		return reproductor.darCategorias();
	}

	public void filtrarCategorias(String filtro) {
		// TODO Auto-generated method stub
	}

	public Object[] darCanales() {
		if(tieneProyecto()){
		Object[] canales = proyectoActual.darCanales();
		return canales;
		}else{
			return null;
		}
	}

	/**
	 * 
	 * @param canalAEliminar
	 */
	public void eliminarCanal(Canal canalAEliminar) {
		proyectoActual.eliminarCanal(canalAEliminar);
	}

	/**
	 * 
	 * @param canal
	 */
	public void agregarCanal(String canal) {
		proyectoActual.agregarCanal(new Canal(canal));
	}

	/**
	 * 
	 * @return
	 */
	public Object[] darSonidos() {
		return reproductor.darSonidos();
	}

	public void filtrarSonidos(String tipoFiltro, String filtro) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	public void escogerArchivo() {
		JFileChooser chooser = new JFileChooser();
		int resultado = chooser.showOpenDialog(this);
		if(resultado == JFileChooser.APPROVE_OPTION){
			File[] f = {chooser.getSelectedFile()};
			reproductor.agregarSonidosALibreria(f);
		}
	}

	/**
	 * 
	 */
	public void escogerCarpeta() {
		JFileChooser chooser = new JFileChooser();
		//chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setMultiSelectionEnabled(true);
		int resultado = chooser.showOpenDialog(this);
		if(resultado == JFileChooser.APPROVE_OPTION){
			File[] f = chooser.getSelectedFiles();
			reproductor.agregarSonidosALibreria(f);
		}
	}

	public void agregarSonidoACanal(String sonido) {
		// TODO Auto-generated method stub
		//Ni idea!!!!!!!!!!!!!!!!!!!
	}
	
	public Object[] darProyectos() {
		return reproductor.darProyectos();
	}
	
	public void cargarProyecto(Proyecto proyectoCargado) {
		proyectoActual = proyectoCargado;
		panelProyectosGuardados.refrescarListaProyectos(reproductor.darProyectos());
		panelProyecto.refrescarPanel(proyectoActual);
	}

	//------------------------------------
	// Main
	//------------------------------------	
	
	public static void main(String[] args) {
		InterfazCupiSoundBox interfaz = new InterfazCupiSoundBox();
		self = interfaz;
		interfaz.setVisible(true);
	}

	public void agregarSonidoACanal(Canal canal, Object object) {
		// TODO Auto-generated method stub
		Sample sonido = (Sample)object;
		Sample[] sonidos = {sonido};
		
		proyectoActual.agregarSonidosACanal(sonidos, canal);
	}

	public void eliminarCategoriaDeSonido(Sample sonido, Categoria cat) {
		sonido.eliminarCategoria(cat);
	}

}
