package interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import mundo.Anio;
import mundo.Area;
import mundo.CentralColegios;
import mundo.Colegio;
import mundo.Criterio;
import mundo.Departamento;
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
		setSize(new Dimension(1000, 652));
		getContentPane().setLayout(null);
		setTitle("CupiColegios");
		setResizable(false);
		
		self = this;
		
		panelColegios = new PanelColegios(self);
		panelColegios.setBounds(385, 6, 609, 600);
		getContentPane().add(panelColegios);
		
		panelBusqueda = new PanelBusqueda(self);
		panelBusqueda.setBounds(6, 6, 367, 600);
		getContentPane().add(panelBusqueda);
		
		//JMenu---------------------
		
		JMenuBar menu = new JMenuBar();
		
		JMenu menuExtesion = new JMenu("Extension");
		
		JMenuItem itmE1 = new JMenuItem("Extension 1");
		itmE1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				metodoExtension1();
			}

		});
		menuExtesion.add(itmE1);
		
		JMenuItem itmE2 = new JMenuItem("Extension 2");
		itmE2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				metodoExtension2();
			}
		});
		menuExtesion.add(itmE2);
		
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
		
		menuOpciones.addSeparator();
		
		JMenuItem itmEstadisticas = new JMenuItem("Ver Grafica Deptos Superiores");
		itmEstadisticas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame grafico = new GraficoTorta(central.darDatosDepartamentos(),"Departamentos MUY SUPERIOR");
				grafico.setSize(500,500);
				grafico.setVisible(true);
			}
		});
		menuOpciones.add(itmEstadisticas);
		
		JMenuItem itmEstadisticas2 = new JMenuItem("Ver Grafica Puntajes Icfes");
		itmEstadisticas2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame grafico2 = new GraficaBarras(central.darPromediosIcfes(),"Promedio icfes", "Anio","promedio");
				grafico2.setSize(500,500);
				grafico2.setVisible(true);
			}
		});
		menuOpciones.add(itmEstadisticas2);
		
		JMenuItem itmEstadisticas3 = new JMenuItem("Ver grafica departamentos anio");
		itmEstadisticas3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame grafico = new GraficoTorta(central.darDatosDepartamentosAnio(),"Departamentos por anio");
				grafico.setSize(500,500);
				grafico.setVisible(true);
			}
		});
		menuOpciones.add(itmEstadisticas3);
		
		JMenuItem itmEstadisticas4 = new JMenuItem("Ver grafica libre");
		itmEstadisticas4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame grafico = new GraficaBarras(central.darDatosGraficaLibre(),"Matematicas", "Anio","Promedio");
				grafico.setSize(500,500);
				grafico.setVisible(true);
			}
		});
		menuOpciones.add(itmEstadisticas4);
		
		menuOpciones.addSeparator();
		
		JMenuItem itmRequUlt = new JMenuItem("Ver Estadisticas");
		itmRequUlt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					DialogoEstadisticas dialogoEstadisticas = new DialogoEstadisticas();	
					dialogoEstadisticas.setParent(self);
					dialogoEstadisticas.setVisible(true);
				} catch (Exception e2) {
					mostrarError("Error inesperado");
				}
			}
		});
		menuOpciones.add(itmRequUlt);
		
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
		
		JMenuItem itmIngresar = new JMenuItem("Iniciar sesion");
		itmIngresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DialogoIniciarSesion dialogoIniciarSesion = new DialogoIniciarSesion();
				dialogoIniciarSesion.setParent(self);
				dialogoIniciarSesion.setVisible(true);
			}
		});
		menuCuenta.add(itmIngresar);
		
		menuCuenta.addSeparator();
		
		JMenuItem itmCerrarSesion = new JMenuItem("Cerrar Sesion");
		itmCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tieneUsuario()){
					usuarioActual = null;
					central.cerrarSesion();
					panelColegios.refrescar();
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
					try {
						central.salvarUsuarios();
					} catch (IOException e1) {
						mostrarError(e1.getMessage());
					}
				}else
					mostrarError("No hay sesion iniciada");
			}
		});
		menuCuenta.add(itmGuardarInformacion);
		
		//JMenu
		
		menu.add(menuOpciones);
		menu.add(menuExtesion);
		menu.add(Box.createHorizontalGlue());
		menu.add(menuCuenta);
		setJMenuBar(menu);
		
		//JMenu end-----------------------
		
		try {
			central = new CentralColegios();
			Object[] colegios = central.darColegios();
			panelColegios.refrescarTabla(colegios);
			panelBusqueda.inicializarCombos();
			panelColegios.inicializarComboAnios();
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
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
	 * Comprueba si existe un usuario logueado
	 * @return TRUE si tiene FALSE en caso contrario
	 */
	public boolean tieneUsuario(){
		if(usuarioActual != null)
			return true;
		else
			return false;	
	}

	/**
	 * Da un arreglo de los hijos del usuario actual
	 * @return Object[] Arreglo de los hijos del usuairo
	 */
	public Object[] darHijos() {
		return central.darHijosUsuarioActual();
	}

	/**
	 * Da los colegios favoritos del hijo que entra por parametro
	 * @param hijo El hijo del que se quieren sacar los colegios
	 * @return Object[] Arreglo de los colegios favoritos del hijo
	 */
	public Object[] darColegiosFavoritosHijo(Object hijo) {
		Hijo hijito = (Hijo)hijo;
		return hijito.darColegiosFavoritos();
	}

	/**
	 * Da los colegios recomendados de la lista de favoritos del hijo 
	 * que entra por parametro
	 * @param hijo El hijo que se quiere buscar
	 * @return Object[] Un arreglo con los colegios recomendados del hijo
	 */
	public Object[] darColegiosRecomendados(Hijo hijo) {
		return hijo.darColegioRecomendados();
	}
	
	/**
	 * Busca un conjunto de colegios que cumplen cierto numero de criterios
	 * @param criterios Los criterios que se quieren filtrar
	 * POST: Actualiza el panel colegios con la informacion encontrada
	 */
	public void buscarPorCriterio(Criterio[] criterios){
		Object[] resultados = central.buscarPorCriterio(criterios);
		panelColegios.refrescarTabla(resultados);
	}
	
	/**
	 * Crea un nuevo hijo y lo agrega al usuario actual de la central de colegios
	 * @param nombre El nombre del hijo	
	 * @param genero El genero del hijo
	 * @param edad La eddad del hijo
	 * @param telefono El telefono del hijo
	 * @param encargado El encargado del hio 
	 * @param ciudad La ciudad donde vive el hijo
	 */
	public void registrarHijo(String nombre, int genero, int edad, int telefono, String encargado, String ciudad) {
		Hijo hijo = new Hijo(nombre, edad, genero, ciudad, telefono, encargado);
		central.registrarHijoUsuario(usuarioActual, hijo);
	}

	/**
	 * Registra un nuevo usuario en la central
	 * @param usuario El nombre del usuairo
	 * @param c1 La contrasena del usuario
	 * POST: Se ha agregado un nuevo usuario a la central. Es ahora el actual
	 */
	public void registrarUsuario(String usuario, String c1) {
		usuarioActual = central.agregarNuevoUsuario(usuario, c1);
	}

	/**
	 * Busca un usuario dado su nombre de usuario y contrasena
	 * @param usuario El usuairo que se quiere buscar
	 * @param pass La contrasena del usuaior
	 * @return TRUE si se encontro el usuairo, FALSE en caso contrario
	 */
	public boolean buscarUsuario(String usuario, String pass) {
		boolean encontro = central.buscarUsuario(usuario,pass);
		
		if(encontro){
			usuarioActual = central.darUsuarioActual();
			panelColegios.refrescar();
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Refresca el panel de hijos
	 */
	public void refrescarHijos() {
		panelColegios.refrescar();
	}

	/**
	 * Elimina el hijo dado por parametro del padre
	 * @param elim El hijo que se quiere eliminar
	 * POST: Se ha elimindo el hijo del padre
	 */
	public void eliminarHijo(Hijo elim) {
		central.eliminarHijo(elim);
	}

	/**
	 * Se busca el colegio dado el codigo que entra por parametro
	 * @param cod El codigo del colegio que se quiere buscar
	 * @return EL colegio encontrado NULL en caso contrario
	 */
	public Colegio buscarColegio(String cod) {
		return central.buscarColegioCodigo(cod);
	}

	/**
	 * Busca todas las areas y encuentra los colegios que cumplen con los criterios dados
	 * Y entre el rango de puntajes seleccionado
	 * @param area El area que se quiere buscar
	 * @param anio El anio que se quiere buscar
	 * @param puntajeA El puntaje maximo
	 * @param puntajeB El puntaje minimo
	 */
	public void buscarPorAreaPuntaje(String area,int anio, int puntajeA, int puntajeB) {
		Object[] resultados = central.buscarPorArea(new Area(area,0), anio, puntajeA, puntajeB);
		panelColegios.refrescarTabla(resultados);
	}

	/**
	 * Retorna la lista de departamentos de la central
	 * @return Object[] Un arreglo con los departamentos
	 */
	public Object[] darDepartamentos() {
		return central.darDepartamentos();
	}
	
	/**
	 * Retorna el departamento seleccionado del panel de busqueda
	 * @return Departmento
	 */
	public Departamento darDeptoSeleccionado(){
		return panelBusqueda.darDepartamentoSeleccionado();
	}

	/**
	 * Busca los departamentos y municipios que se quieren buscar segun sus codigos
	 * @param depto El codigo del departamento
	 * @param mun El codigo del municipio
	 * POST: Se ha actualizado el panel de colegios con los resultados de las busquedas
	 */
	public void buscarPorUbicacion(int depto, int mun) {
		Object[] resultados = central.mostrarColegiosPorUbicacion(depto, mun);
		panelColegios.refrescarTabla(resultados);
	}

	/**
	 * Retorna un arreglo de anios de la central
	 * @return Object[] Los anios de la central
	 * 
	 */
	public Object[] darAnios() {
		return central.darAnios();
	}

	/**
	 * Busca los colegios dado su codigo y el anio al que pertenecen
	 * @param codigo El codigo del colegio que se quiere buscar
	 * @param n El anio que se quiere buscar
	 * @return El colegio encontrado, NULL en caso contrario
	 */
	public Colegio buscarColegioAnio(String codigo, Anio n) {
		return central.buscarAnioColegio(codigo,n);
	}

	/**
	 * Da el promedio de los colegios dado el codigo y los anios
	 * @param codigo El codigo del departamento que se quiere buscar
	 * @return Una matriz con los datos del anio y su promedio respectivo
	 */
	public String[][] darPromedioAnios(int codigo) {
		return central.darPromedioAnios(codigo);
	}

	/**
	 * Retorna la mejor materia entre todos los anios con puntaje 10
	 * @return String la mejor materia
	 */
	public String darMejorMateria() {
		return "MATEMATICAS";
	}
	
	/**
	 * Metodo extension
	 */
	public void metodoExtension1() {
		String r = central.metodoExtension1();
		JOptionPane.showMessageDialog(this, r, "titulo", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Metodo extension
	 */
	public void metodoExtension2() {
		String r = central.metodoExtension2();
		JOptionPane.showMessageDialog(this, r, "titulo", JOptionPane.INFORMATION_MESSAGE);
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
}
