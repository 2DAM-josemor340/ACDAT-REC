import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

public class ManejadorSAXSesiones extends DefaultHandler {
    private Map<String, String> usuariosConPassword;

    private int totalUsuarios = 0;
    private int sinPassword = 0;
    private int sesionesRegistradas = 0;
    private int sesionesEnEjecucion = 0;

    private StringBuilder contenido = new StringBuilder();
    private String usuarioActual;
    private boolean dentroDeUser = false;
    private boolean tieneHoraFinal;

    public ManejadorSAXSesiones(Map<String, String> usuariosConPassword) {
        this.usuariosConPassword = usuariosConPassword;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        contenido.setLength(0);
        if (qName.equals("user")) {
            dentroDeUser = true;
            tieneHoraFinal = false; // Resetear por cada user
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        contenido.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "username":
                usuarioActual = contenido.toString().trim();
                totalUsuarios++;
                if (!usuariosConPassword.containsKey(usuarioActual)) {
                    sinPassword++;
                }
                break;

            case "horafinal":
                tieneHoraFinal = true;
                break;

            case "user":
                sesionesRegistradas++;
                if (!tieneHoraFinal) {
                    sesionesEnEjecucion++;
                }
                break;
        }
    }

    public void mostrarResumen() {
        System.out.println("---- RESULTADO ----");
        System.out.println("1. Usuarios totales dados de alta: " + totalUsuarios);
        System.out.println("2. Usuarios sin password definida: " + sinPassword);
        System.out.println("3. Nº de sesiones registradas: " + sesionesRegistradas);
        System.out.println("4. Nº de sesiones en ejecución: " + sesionesEnEjecucion);
    }
}
