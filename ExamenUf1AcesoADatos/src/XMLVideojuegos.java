import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLVideojuegos {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File fichero = new File("examConfig.conf");
		Scanner s = null;
		Scanner teclat = new Scanner(System.in);

		// leer xml
		DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();
		Document doc2 = dBuilder2.parse(new File("videojuegos.xml"));
		// root element
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc2.getElementsByTagName("juego");
		System.out.println("digues el joc que vols afegir a ps4 (the last of us 2)");
		String juego = teclat.nextLine();
		String[] juegos = new String[0];
		String[] juegosAux = new String[0];
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				String nodeName = nNode.getTextContent();
				juegosAux = juegos;
				juegos = new String[juegosAux.length + 1];
				for (int i = 0; i < juegosAux.length; i++) {
					juegos[i] = juegosAux[i];
				}
				juegos[juegos.length - 1] = nodeName;
			}
		}
		juegosAux = juegos;
		juegos = new String[juegosAux.length + 1];
		for (int i = 0; i < juegosAux.length; i++) {
			juegos[i] = juegosAux[i];
		}
		juegos[juegos.length - 1] = juego;
		

		// generar xml
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("Videoconsolas");
			doc.appendChild(rootElement);
			Element departamento = doc.createElement("XboxOne");
			rootElement.appendChild(departamento);
			Element language2 = doc.createElement("juegos");
			departamento.appendChild(language2);
			Element juego1 = doc.createElement("juego");
			language2.appendChild(juego1);
			juego1.appendChild(doc.createTextNode(juegos[0]));
			Element departamento2 = doc.createElement("PlayStation4");
			rootElement.appendChild(departamento2);
			Element language23 = doc.createElement("juegos");
			departamento2.appendChild(language23);
			for (int i = 1; i < juegos.length; i++) {
				juego1 = doc.createElement("juego");
				language23.appendChild(juego1);
				juego1.appendChild(doc.createTextNode(juegos[i]));
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("videojuegos.xml"));
			transformer.transform(source, result);
			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("guardat a cursos.xml");

	}
}
