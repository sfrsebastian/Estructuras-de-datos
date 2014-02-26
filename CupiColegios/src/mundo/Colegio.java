package mundo;

import Lista.Lista;

public class Colegio implements Comparable<Colegio>{
	private String jornada;
	
	private String icfes;
	
	private String genero;

	private String calendario;
	
	private String tipo;
	
	@Override
	public int compareTo(Colegio o) {
		// TODO Auto-generated method stub
		return 0;
	}

	private Lista<String> darListaAtributos(){
		Lista <String> atributos = new Lista <String>();
		atributos.agregar(jornada);
		atributos.agregar(icfes);
		atributos.agregar(genero);
		atributos.agregar(calendario);
		atributos.agregar(tipo);
		return atributos;
	}
	public boolean cumpleCriterio(Criterio criterio) {
		Object[] subcriterios = criterio.darSubcriterios();
		Lista lista = darListaAtributos();
		String cumple = null;
		for(int i = 0;i<subcriterios.length && cumple != null;i++){
			cumple = (String) lista.buscar((String)subcriterios[i]);
		}
		return cumple==null?false:true;
	}

}
