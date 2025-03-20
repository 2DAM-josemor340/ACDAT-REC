import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Introducir el nombre del Archivo : ");
        String archivo;
        archivo = sc.nextLine();
        String ruta = "res" + File.separator + archivo;
        File fichero2 = new File("res" + File.separator + "palabras-sin.txt");

        try {
            File file = new File(ruta);
            if (file.exists() && file.isFile()) {
                System.out.println("El fichero existe y se llama " + file.getName());

                BufferedReader f1 = new BufferedReader(new FileReader(file));
                FileWriter f2 = new FileWriter(fichero2); // Fichero destino

                System.out.print("Introduzca la palabra que desea buscar : ");
                String palabra;
                palabra = sc.nextLine();
                int contador = 0;
                // Lectura línea
                String linea = f1.readLine();
                while (linea != null) {

                    if (palabra.equalsIgnoreCase(linea)) {
                        contador++;
                    } else if (palabra!=(linea)){
                        f2.write((linea)); // Escritura en fichero resultante
                        f2.write(("\n")); // Salto de linea

                    }
                    linea = f1.readLine();
                }

                System.out.println("El numero de palabras " + palabra + " que se repiten es .:" + contador);
                f1.close();
                f2.close();

            } else {
                System.out.println("Fichero NO existe");
            }
        } catch (IOException e) {
            System.err.println("Error al generar al fichero");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }
}