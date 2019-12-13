import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CreateFileWithThisText {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digues el nom del fixer");
		String nomFicher = sc.nextLine();
		System.out.println("Digues el text que vols escriure");
		String text = sc.nextLine();
		CreateFileWithThisText2(nomFicher, text);
	}

	public static void CreateFileWithThisText2(String nomFixer, String text) throws FileNotFoundException {
		try (PrintWriter salida = new PrintWriter(nomFixer)) {
			salida.println(text);
			System.out.println("text escrit!");
		}
	}
}
