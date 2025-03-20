import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

         Scanner sc = new Scanner(System.in);
        System.out.print("Introducir el nombre del Archivo : ");
        String archivo;
        archivo = sc.nextLine();
        String ruta = "res" + File.separator + archivo;
        try {
            File file = new File(ruta);
            if (file.exists() && file.isFile()) {
                System.out.println("El fichero existe y se llama " + file.getName());

                GestorProperties gestor = new GestorProperties(file);

                System.out.println("Estan son las propiedades del archivo properties "+file.getName()+" , seleccione la propiedad que desee ver su valor");

                gestor.mostrarPropiedades();

                String seleccion;

                do {
                    System.out.println("Seleccione el valor que desea ver o presione N para salir:");
                    seleccion = sc.nextLine();

                    if (!seleccion.equalsIgnoreCase("N")) {
                        String propiedad = gestor.leerPropiedad(seleccion);

                        if (propiedad != null) {
                            System.out.println(seleccion + " = " + propiedad);
                        } else {
                            System.out.println("La propiedad '" + seleccion + "' no existe.");
                            gestor.mostrarPropiedades();
                        }
                    }

                } while (!seleccion.equalsIgnoreCase("N"));

                System.out.println("Adiós");
                sc.close();

            } else {
                System.out.println("Fichero NO existe");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
