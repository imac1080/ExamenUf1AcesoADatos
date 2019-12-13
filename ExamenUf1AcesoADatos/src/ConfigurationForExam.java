import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ConfigurationForExam {
	public static void main(String[] args) {
		File fichero = new File("examConfig.conf");
		Scanner s = null;

		try {
			// Leemos el contenido del fichero
			System.out.println("... Leemos el contenido del fichero ...");
			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			
			String[] linea = s.nextLine().split("\"");
			String app_Version = linea[1];
			System.out.println("app_Version: "+app_Version);
			s.nextLine();
			linea = s.nextLine().split("\"");
			String language = linea[1];
			System.out.println("language: "+language);
			s.nextLine();
			linea = s.nextLine().split("=");
			java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(linea[1]);
			System.out.println("date: "+date);
			s.nextLine();
			linea = s.nextLine().split(" ");
			String num_of_questions = linea[2];
			System.out.println("num_of_questions: "+num_of_questions);
			s.nextLine();
			linea = s.nextLine().split(" ");
			String students_names = linea[2];
			System.out.println("students_names: "+students_names);
			
			

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
