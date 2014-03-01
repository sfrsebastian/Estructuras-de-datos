package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import mundo.Canal;
import mundo.Categoria;
import mundo.Proyecto;
import mundo.Reproductor;
import mundo.Sample;

public class InterfazCupiSoundBox extends JFrame{

	//------------------------------------
	// Atributos
	//------------------------------------
	
	/**
	 * La referencia estatica de la interfaz para conectar a los dialogos
	 */
	private static InterfazCupiSoundBox self;
	
	/**
	 * La clase principal de reproductor
	 */
	private Reproductor reproductor;
	
	/**
	 * El panel de proyectos guardados
	 */
	private PanelProyectosGuardados panelProyectosGuardados;
	
	/**
	 * El panel del proyecto
	 */
	private PanelProyecto panelProyecto;
	
	/**
	 * El proyecto actual del reproductor
	 */
	private Proyecto proyectoActual;
	
	//------------------------------------
	// Constructor
	//------------------------------------
	
	/**
	 * Inicializa la interfaz de CupiSoundBox
	 */
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
			
			public void actionPerformed(ActionEvent arg0) {
				DialogoNuevoProyecto dialogoNuevoProyecto = new DialogoNuevoProyecto();
				dialogoNuevoProyecto.setParent(self);
				dialogoNuevoProyecto.setVisible(true);
			}
		});	
		nuevoProyecto.add(itmNuevoProyecto);
		
		JMenuItem itmBibliotecaSonidos = new JMenuItem("Biblioteca de Sonidos");
		itmBibliotecaSonidos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				DialogoBibliotecaSonidos dialogoBibliotecaSonidos = new DialogoBibliotecaSonidos(false,null);
				dialogoBibliotecaSonidos.setParent(self);
				dialogoBibliotecaSonidos.setVisible(true);
			}
		});
		nuevoProyecto.add(itmBibliotecaSonidos);
		
		JMenuItem itmCategorias = new JMenuItem("Manejar Categorias");
		itmCategorias.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DialogoManejarCategorias dialogoManejarCategorias = new DialogoManejarCategorias();
				dialogoManejarCategorias.setPadre(self);
				dialogoManejarCategorias.setVisible(true);
			}
		});
		nuevoProyecto.add(itmCategorias);
		
		JMenuItem itmEliminarProyecto = new JMenuItem("Eliminar proyecto");
		itmEliminarProyecto.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Proyecto proyecto = panelProyectosGuardados.darProyectoSeleccionado();
				if(proyecto != null){
					reproductor.eliminarProyecto(proyecto);
					panelProyectosGuardados.refrescarListaProyectos(darProyectos());
					if(!proyectoActual.equals(proyecto)){
						panelProyecto.refrescarPanel(proyectoActual);
						panelProyecto.refrescarPanelDibujo();
						panelProyecto = null;
					}else{
						panelProyecto.limpiar();
						panelProyecto.limpiarPanelDibujo();
					}
				}
			}
		});
		
		nuevoProyecto.add(itmEliminarProyecto);
		
		nuevoProyecto.addSeparator();
		
		JMenuItem itmGuardarProyecto = new JMenuItem("Guardar Proyecto");
		itmGuardarProyecto.addActionListener(new ActionListener() {
			
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
	
	/**
	 * Hace que la interfaz termine la ejecucion
	 */
	private void salir(){
		this.dispose();
	}
	
	/**
	 * Muestra un error dado por parametro en un JOptionPane
	 * @param error El error que se quiere mostrar
	 */
	private void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Hola", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Agrega una nueva categoria al reproductor
	 * @param categoria El nombre de la nueva categoria 
	 * Post: Se ha agregado una nueva categoria al reproductor
	 */
	public void agregarCategoria(String categoria) {
		reproductor.agregarCategoria(new Categoria(categoria));
	}

	/**
	 * Agrega una categoria a un sonido dado por parametro
	 * @param sonido El sonido que se quiere anadir
	 * @param cat La categoria a la cual se le asigna el sonido
	 */
	public void agregarCategoriaASonido(Sample sonido, Categoria cat){
		reproductor.asignarCategoria(cat, sonido);
	}

	/**
	 * Agrega un nuevo canal al reproductor
	 * @param canal El nombre del nuevo canal
	 * Post: Se ha agregado una nueva categoria al reproductor
	 */
	public void agregarCanal(String canal) {
		proyectoActual.agregarCanal(new Canal(canal));
		panelProyecto.refrescarPanelDibujo();
	}

	/**
	 * Agrega un arreglo de sonidos a un canal dados por parametro
	 * @param canal El canal que al que se le agregan los sonidos
	 * @param objects El arreglo de sonidos 
	 * Post: Se ha agregado un arreglo de sonidos al canal
	 */
	public void agregarSonidoACanal(Canal canal, Object[] objects) {
		canal.agregarSonido(objects);
	}

	/**
	 * Aumenta el BPM del proyecto
	 * @return Double: El BPM actual
	 */
	public Double aumentarBPM() {
		if(proyectoActual != null)
			return proyectoActual.aumentarBPM();
			else
				mostrarError("No hay proyecto actual");
			return 0.0;
	}

	/**
	 * Carga un proyecto
	 * @param proyectoCargado El proyecto que se quiere cargar
	 * Post: Se ha cargado el proyecto 
	 */
	public void cargarProyecto(Proyecto proyectoCargado) {
		proyectoActual = proyectoCargado;
		panelProyectosGuardados.refrescarListaProyectos(reproductor.darProyectos());
		panelProyecto.refrescarPanel(proyectoActual);
		panelProyecto.refrescarPanelDibujo();
	}
	
	/**
	 * Crea un nuevo proyecto con la informacion dada
	 * @param nombre El nombre del proyecto
	 * @param autor El autor del proyecto
	 * @param canales El numero inicial de canales
	 * Post: Se ha creado y agregado un nuevo proyecto
	 */
	public void crearProyecto(String nombre, String autor, int canales){
		proyectoActual = reproductor.agregarProyecto(nombre,autor,canales);
		panelProyectosGuardados.refrescarListaProyectos(reproductor.darProyectos());
		panelProyecto.refrescarPanel(proyectoActual);
		panelProyecto.refrescarPanelDibujo();
	}

	/**
	 * Retorna las categorias que tiene el reproductor
	 * @return Object[] Categorias
	 */
	public Object[] darCategorias() {
		return reproductor.darCategorias();
	}

	/**
	 * Da el arreglo de canales que tiene el proyecto actual
	 * @return Object[] Canales
	 */
	public Object[] darCanales() {
		if(tieneProyecto()){
		Object[] canales = proyectoActual.darCanales();
		return canales;
		}else{
			return null;
		}
	}

	/**
	 * Retorna la lista de lo sonidos del reproductor
	 * @return Object[] Arreglo de sonidos
	 */
	public Object[] darSonidos() {
		return reproductor.darSonidos();
	}

	/**
	 * Retorna el arreglo de proyectos del reproductor
	 * @return Object[] Arreglo de proyectos
	 */
	public Object[] darProyectos() {
		return reproductor.darProyectos();
	}

	/**
	 * Disminuy el BPM del proyecto
	 * @return El BPM del proyecto
	 */
	public Double disminuiBPM() {
		if(proyectoActual != null)
		return proyectoActual.disminuirBPM();
		else
			mostrarError("No hay proyecto actual");
		return 0.0;
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
	 * Elimina una categoria dada por parametro
	 * @param categoriaEliminada La categoria que se quiere eliminar
	 */
	public void eliminarCategoria(Categoria categoriaEliminada) {
		reproductor.eliminarCategoria(categoriaEliminada);
	}

	/**
	 * Elimina un canal del proyecto actual
	 * @param canalAEliminar El canal a eliminar
	 */
	public void eliminarCanal(Canal canalAEliminar) {
		proyectoActual.eliminarCanal(canalAEliminar);
		panelProyecto.refrescarPanelDibujo();
	}

	/**
	 * Elimina un sonido del reproductor
	 * @param sonido El sonido que se quiere eliminar
	 */
	public void eliminarSonido(Sample sonido) {
		reproductor.eliminarSonido(sonido);
		if(proyectoActual != null)
			proyectoActual.eliminarSonidosDeCanal(sonido);
	}

	/**
	 * Permite escoger un archivo unico de sonido para agregar al reproductor
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
	 * Permite escoger una carpeta llena de sonidos para agregar al reproductor 
	 */
	public void escogerCarpeta() {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		int resultado = chooser.showOpenDialog(this);
		if(resultado == JFileChooser.APPROVE_OPTION){
			File[] f = chooser.getSelectedFiles();
			reproductor.agregarSonidosALibreria(f);
		}
	}

	/**
	 * Elimina una categoria del sonido dado por parametro
	 * @param sonido El sonido que tiene las categorias
	 * @param cat La categoria a eliminar
	 * @return Categoria la categoria eliminada
	 */
	public Categoria eliminarCategoriaDeSonido(Sample sonido, Categoria cat) {
		return sonido.eliminarCategoria(cat);
	}

	/**
	 * Edita la informacion del canal
	 * @param canalActual El canal que se quiere editar
	 * @param nombre El nuevo nombre del canal
	 */
	public void editarCanal(Canal canalActual, String nombre) {
		canalActual.cambiarNombre(nombre);
		panelProyecto.refrescarPanelDibujo();
	}
	
	/**
	 * Refresca el panel dibujo
	 */
	public void refrescarPanelProyecto(){
		panelProyecto.refrescarPanelDibujo();
	}

	/**
	 * Filtra los proyectos del reproductor dado los parametros
	 * @param filtro El tipo de filtro Nombre o Categoria
	 * @param combo El filtro que se quiere comparar
	 * @return El proyecto encontrado, null en caso contrario
	 */
	public Proyecto filtrarProyectos(String filtro, String combo) {
		if(combo.equals("Nombre")){
			return reproductor.buscarProyectoPorNombre(filtro);
		}
		else{
			return reproductor.buscarProyectoPorAutor(filtro);
		}
	}

	public void filtrarCategorias(String filtro) {
		// TODO Auto-generated method stub
	}

	/**
	 * Filtra los sonidos dada la categoria
	 * @param filtro String, con el tiepo de filtro para los sonidos
	 * @return Un arreglo con los objetos relevantes, Arreglo vacio en caso
	 * contrario
	 */
	public Object[] filtrarSonidosPorCategoria(String filtro) {
		Categoria t = reproductor.darCategoriaPorNombre(filtro);
		if(t != null)
			return reproductor.filtrarSonidosPorCategoria(t);
		else
			return null;
	}

	/**
	 * Filtra los sonidos 
	 * @param filtro El tipo de filtro por el que se quiere buscar
	 * @return Un arreglo de sonidos, arreglo vacio en caso contrario
	 */
	public Object[] filtrarSonidosPorNombre(String filtro) {
		return reproductor.filtrarSonidosPorNombre(filtro);
	}

	/**
	 * Reproduce el proyecto
	 */
	public void reproducir(){
		if(proyectoActual != null)
		proyectoActual.reproducir();
		else
			mostrarError("No hay proyecto actual");
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

	/**
	 * Comprueba si hay un proyecto cargado
	 * @return TRUE si cierto, FALSE en caso contrario
	 */
	public boolean tieneProyecto(){
		return (proyectoActual == null) ? false : true;
	}
	
	/**
	 * Pausa la reproduccion del proyecto
	 */
	public void pausar() {
		if(proyectoActual != null)
		proyectoActual.pausar();
		else
			mostrarError("No hay proyecto actual");
	}
	
	/**
	 * Para la reproduccion del proyecto
	 */
	public void parar(){
		if(proyectoActual != null)
		proyectoActual.parar();
		else
			mostrarError("No hay proyecto actual");
	}

	//------------------------------------
	// Main
	//------------------------------------	
	
	public static void main(String[] args) {
		InterfazCupiSoundBox interfaz = new InterfazCupiSoundBox();
		self = interfaz;
		interfaz.setVisible(true);
	}

}
