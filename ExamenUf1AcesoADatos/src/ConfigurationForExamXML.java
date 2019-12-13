import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigurationForExamXML {
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
			System.out.println("app_Version: " + app_Version);
			s.nextLine();
			linea = s.nextLine().split("\"");
			String language = linea[1];
			System.out.println("language: " + language);
			s.nextLine();
			linea = s.nextLine().split("=");
			java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(linea[1]);
			System.out.println("date: " + date);
			s.nextLine();
			linea = s.nextLine().split(" ");
			String num_of_questions = linea[2];
			System.out.println("num_of_questions: " + num_of_questions);
			s.nextLine();
			linea = s.nextLine().split(" ");
			String students_names = linea[2];
			System.out.println("students_names: " + students_names);

			// generar xml
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.newDocument();
				// root element
				Element rootElement = doc.createElement("Configurations");
				doc.appendChild(rootElement);
				Element departamento = doc.createElement("app_Version");
				rootElement.appendChild(departamento);
				departamento.appendChild(doc.createTextNode(app_Version));
				Element language2 = doc.createElement("language");
				rootElement.appendChild(language2);
				language2.appendChild(doc.createTextNode(language));
				Element date2 = doc.createElement("date");
				rootElement.appendChild(date2);
				date2.appendChild(doc.createTextNode(date.toString()));
				Element num_of_questions2 = doc.createElement("num_of_questions");
				rootElement.appendChild(num_of_questions2);
				num_of_questions2.appendChild(doc.createTextNode(num_of_questions));
				Element students_names2 = doc.createElement("students_names");
				rootElement.appendChild(students_names2);
				students_names2.appendChild(doc.createTextNode(students_names));

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("examConfig.xml"));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult consoleResult = new StreamResult(System.out);
				transformer.transform(source, consoleResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("guardat a cursos.xml");

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
