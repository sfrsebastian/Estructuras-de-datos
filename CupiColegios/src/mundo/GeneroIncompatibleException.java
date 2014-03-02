package mundo;

public class GeneroIncompatibleException extends Exception {
	public GeneroIncompatibleException(){
		super("Genero del colegio no compatible con hijo");
	}
}
