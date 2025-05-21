import java.io.File;
import java.util.Scanner;

public class ProductosApp {
    public static void main(String[] args) {
        GestionaProductos gp = new GestionaProductos("Productos.csv");
        boolean exito = gp.generarFichero();
        System.out.println("Generación archivo: " + (exito ? "Éxito" : "Error"));

        if (exito) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el stock mínimo para la consulta: ");
            int minStock = scanner.nextInt();
            scanner.close();

            File archivoDat = new File("res" + File.separator + "Productos.dat");
            gp.consultarStock(archivoDat, minStock);
        } else {
            System.out.println("No se puede realizar la consulta porque no se generó correctamente el archivo .DAT.");
        }
    }
}