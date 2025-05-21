import java.io.*;
import java.util.*;
import javax.xml.parsers.*;

public class GestionarSAX {

    public void procesar(String rutaXML) {
        try {
            // Cargar usuarios con password
            Map<String, String> usuariosConPassword = cargarUsuariosConPassword("res" + File.separator + "users.txt");

            // Crear parser SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // Procesar XML
            ManejadorSAXSesiones manejador = new ManejadorSAXSesiones(usuariosConPassword);
            parser.parse(new File(rutaXML), manejador);

            // Mostrar resumen
            manejador.mostrarResumen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> cargarUsuariosConPassword(String ruta) {
        Map<String, String> usuarios = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(":")) {
                    String[] partes = linea.split(":");
                    if (partes.length == 2) {
                        usuarios.put(partes[0].trim(), partes[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
