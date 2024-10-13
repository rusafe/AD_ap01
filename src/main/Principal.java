package main;

/**
 * Clase inicial on es llanza el programa
 */
public class Principal {
	/**
	 * Metode principal del programa
	 * @param args Arguments introduits al programa
	 */
	public static void main(String[] args) {
		Vista vista = new Vista();
		Model model = new Model();
		Controlador controlador = new Controlador(vista, model);
	}

}
