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

    public String leerPropiedad(String c) {

        try {
            if (props.getProperty(c) != null)
                return props.getProperty(c);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void mostrarPropiedades(){
        try {
            for (Object clave : props.keySet()) {
                String valor = props.getProperty((String) clave);
               // System.out.println(clave + " = " + valor);
                System.out.println(clave);            }
        } catch (Exception e) {
            System.err.println("Error al mostrar las propiedades: " + e.getMessage());
        }

    }

}
