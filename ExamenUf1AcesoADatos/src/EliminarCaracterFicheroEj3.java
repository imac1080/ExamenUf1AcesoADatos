import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class EliminarCaracterFicheroEj3 {
	public static void main(String[] args) {
		//fichero = ficher3.txt
		Scanner sc = new Scanner(System.in);
		System.out.println("Digues el fixer");
		String fixer = sc.nextLine();
		System.out.println("Digues el caracter que vols eliminar");
		char caracter = sc.nextLine().charAt(0);
		EliminarCaracterFicheroEj3Mod(fixer, caracter);
	}

	public static void EliminarCaracterFicheroEj3Mod(String fixer, char caracter) {
		File fichero = new File(fixer);
		Scanner s = null;
		try {
			// Leemos el contenido del fichero
			System.out.println("... Leemos el contenido del fichero ...");
			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			String[] lecturaFichero = new String[0];
			String[] lecturaFicheroAux = new String[0];
			while (s.hasNextLine()) {
				lecturaFicheroAux = lecturaFichero;
				lecturaFichero = new String[lecturaFicheroAux.length + 1];
				for (int i = 0; i < lecturaFicheroAux.length; i++) {
					lecturaFichero[i] = lecturaFicheroAux[i];
				}
				lecturaFichero[lecturaFichero.length - 1] = s.nextLine();
			}
			System.out.println(lecturaFichero.length);
			for (int i = 0; i < lecturaFichero.length; i++) {
				String EliminarCaracter = lecturaFichero[i];
				System.out.println(EliminarCaracter);
				String[] EliminarCaracterAux = EliminarCaracter.split(Character.toString(caracter));
				EliminarCaracter = "";
				System.out.println(Arrays.toString(EliminarCaracterAux));
				for (int j = 0; j < EliminarCaracterAux.length; j++) {
					EliminarCaracter = EliminarCaracter + EliminarCaracterAux[j];
				}
				lecturaFichero[i] = EliminarCaracter;
			}
			s.close();
			try (PrintWriter salida = new PrintWriter(fixer)) {
				for (int i = 0; i < lecturaFichero.length; i++) {
					salida.println(lecturaFichero[i]);
				}
				System.out.println("caracter eliminat!");
			}

		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Mensaje 2: " + ex2.getMessage());
			}
		}
	}
}
