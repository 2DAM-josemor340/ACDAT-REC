import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introducir el nombre del Archivo: ");
        String archivo = sc.nextLine();
        String ruta = "res" + File.separator + archivo;
        File fichero2 = new File("res" + File.separator + "palabras-sin.txt");

        try {
            File file = new File(ruta);
            if (file.exists() && file.isFile()) {
                System.out.println("El fichero existe y se llama " + file.getName());

                try (BufferedReader f1 = new BufferedReader(new FileReader(file));
                     FileWriter f2 = new FileWriter(fichero2)) {

                    System.out.print("Introduzca la palabra que desea buscar: ");
                    String palabra = sc.nextLine().toLowerCase();
                    int contador = 0;

                    String linea;
                    while ((linea = f1.readLine()) != null) {
                        String[] palabras = linea.split("[,\\s]+");
                        StringBuilder nuevaLinea = new StringBuilder();

                        for (String palabraArchivo : palabras) {
                            if (palabraArchivo.equalsIgnoreCase(palabra)) {
                                contador++;
                            } else {
                                if (nuevaLinea.length() > 0) {
                                    nuevaLinea.append(", ");
                                }
                                nuevaLinea.append(palabraArchivo);
                            }
                        }

                        if (nuevaLinea.length() > 0) {
                            f2.write(nuevaLinea.toString() + "\n");
                        }
                    }

                    System.out.println("El número de veces que aparece '" + palabra + "' es: " + contador);
                }
            } else {
                System.out.println("Fichero NO existe");
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
}