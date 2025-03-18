import java.io.File;
import java.util.Scanner;

public class GestorDirectorios {
    public static void main(String[] args) {
        try {
            String dato;
            Scanner sc = new Scanner(System.in);
            System.out.print("Introducir nombre: ");
            dato = sc.nextLine();
            File file = new File(dato);
            if (file.exists()) {
                if (file.isDirectory()) {
                    System.out.println("Es un directorio ");
                    File arrayFile[] = file.listFiles();
                    if (file.length() > 0) {
                        for (File f : arrayFile) {
                            if (f.isDirectory()) {
                                System.out.println(" + " + f.getName());
                            } else if (f.isFile()) {
                                System.out.println(" - " + f.getName());
                            }
                        }
                    }
                } else {
                    System.out.println("Es un Archivo ");
                    System.out.println(" - " + file.getName());

                }

            } else {
                System.out.println("Fichero NO existe");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}