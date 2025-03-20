import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class GestorProperties {

    private File f;
    private Properties props;

    public GestorProperties(File f) {
        this.f = f;
        props = new Properties();

        try {
            props.load(new FileReader(f));

        } catch (FileNotFoundException e) {
            System.err.println("Fichero no existe");
        } catch (IOException e) {
            System.err.println("Error en lectura de propiedades de configuración");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String LeerPropiedad(String c) {

        try {
            if (props.getProperty(c) != null)
                return props.getProperty("c");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static void mostrarPros() {




    }
}