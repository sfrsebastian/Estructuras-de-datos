package interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.scene.control.Accordion;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import mundo.CentralColegios;
import mundo.Hijo;
import mundo.Usuario;

public class InterfazCupiColegios extends JFrame {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * La clase principal del mundo
	 */
	private CentralColegios central;
	
	/**
	 * El usuario actual
	 */
	private Usuario usuarioActual;
	
	/**
	 * El panel que contiene la informacion de las busquedas
	 */
	private PanelBusqueda panelBusqueda;
	
	/**
	 * El panel que contiene la tabla e informacion de colegios
	 */
	private PanelColegios panelColegios;
	
	/**
	 * Referencia estatica de la propia interfaz para los dialogos
	 */
	private static InterfazCupiColegios self;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	/**
	 * Construye la interfaz principal de la aplicacion
	 */
	public InterfazCupiColegios(){
		setSize(new Dimension(916, 652));
		getContentPane().setLayout(null);
		setTitle("CupiColegios");
		setResizable(false);
		
		panelColegios = new PanelColegios(self);
		panelColegios.setBounds(303, 6, 609, 600);
		getContentPane().add(panelColegios);
		
		panelBusqueda = new PanelBusqueda(self);
		panelBusqueda.setBounds(6, 6, 287, 600);
		getContentPane().add(panelBusqueda);
		
		//JMenu---------------------
		
		JMenuBar menu = new JMenuBar();
		
		JMenu menuOpciones = new JMenu("Opciones");
		
		JMenuItem itmAdministrarHijos = new JMenuItem("Administrar hijos");
		itmAdministrarHijos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tieneUsuario()){
				DialogoManejarHijos dialogoManejarHijos = new DialogoManejarHijos();
				dialogoManejarHijos.setParent(self);
				dialogoManejarHijos.setVisible(true);
				}else{
					mostrarError("Debe autenticarse primero!");
				}
			}
		});
		menuOpciones.add(itmAdministrarHijos);
		
		JMenuItem itmEstadisticas = new JMenuItem("Ver estadisticas");
		itmEstadisticas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DialogoEstadisticas dialogoEstadisticas = new DialogoEstadisticas();
				dialogoEstadisticas.setParent(self);
				dialogoEstadisticas.setVisible(true);
			}
		});
		menuOpciones.add(itmEstadisticas);
		
		JMenu menuCuenta = new JMenu("Cuenta");
		
		JMenuItem itmCrearUsuario = new JMenuItem("Registrar usuario");
		itmCrearUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DialogoRegistrarUsuario dialogoRegistrarUsuario = new DialogoRegistrarUsuario();
				dialogoRegistrarUsuario.setParent(self);
				dialogoRegistrarUsuario.setVisible(true);
			}
		});
		menuCuenta.add(itmCrearUsuario);
		menuCuenta.addSeparator();
		
		JMenuItem itmPreferencias = new JMenuItem("Preferencias");
		itmPreferencias.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tieneUsuario()){
					
				}else{
					mostrarError("No se encuentra autenticado!");
				}
			}
		});
		menuCuenta.add(itmPreferencias);
		menuCuenta.addSeparator();
		
		JMenuItem itmCerrarSesion = new JMenuItem("Cerrar Sesion");
		itmCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tieneUsuario()){
					//usuarioActual = null;
				}else
					mostrarError("No hay sesion iniciada");
			}
		});
		menuCuenta.add(itmCerrarSesion);
		
		JMenuItem itmGuardarInformacion = new JMenuItem("Guardar Informacion");
		itmGuardarInformacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tieneUsuario()){
					
				}else
					mostrarError("No hay sesion iniciada");
			}
		});
		menuCuenta.add(itmGuardarInformacion);
		
		menu.add(menuOpciones);
		menu.add(Box.createHorizontalGlue());
		menu.add(menuCuenta);
		setJMenuBar(menu);
		
		//JMenu end-----------------------
		
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	/**
	 * Le muestra un error al usuario
	 * @param error El error que se quiere mostrar
	 */
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	//------------------------------------------
	// Main
	//------------------------------------------
	
	/**
	 * El metodo main que inicia la ejecucion del programa
	 * @param args
	 */
	public static void main(String[] args) {
		InterfazCupiColegios interfaz = new InterfazCupiColegios();
		interfaz.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		interfaz.setVisible(true);
		self = interfaz;
	}
	
	public boolean tieneUsuario(){
		if(usuarioActual != null)
			return true;
		else
			return false;	
	}

	public Object[] darHijos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] darColegiosFavoritosHijo(Object hijo) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] darColegiosRecomendados(Object hijo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void buscarPorCriterio(Criterio[] criterios){
		Object[] resultados = central.buscarPorCriterio(criterios);
		panelColegios.refrescarTabla(resultados);
	}
	
	/**
	 * Crea un nuevo hijo y lo agrega al usuario actual de la central de colegios
	 * @param nombre
	 * @param genero
	 * @param edad
	 * @param telefono
	 * @param encargado
	 * @param ciudad
	 */
	public void registrarHijo(String nombre, int genero, int edad, int telefono, String encargado, String ciudad) {
		Hijo hijo = new Hijo(nombre, edad, genero, ciudad, telefono, encargado);
		central.registrarHijoUsuario(usuarioActual, hijo);
	}

	public void registrarUsuario(String usuario, String c1) {
		usuarioActual = central.agregarNuevoUsuario(usuario, c1);
	}
}