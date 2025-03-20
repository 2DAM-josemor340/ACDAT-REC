import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Introducir la ruta del fichero : ");
        String ruta;
        ruta = sc.nextLine();
        try {
            File file = new File(ruta);
            if (file.exists()) {
                System.out.println("El fichero existe y se llama " + file.getName());

                GestorProperties gestor = new GestorProperties(file);

                System.out.println(gestor.LeerPropiedad("username"));



            } else {
                System.out.println("Fichero NO existe");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}