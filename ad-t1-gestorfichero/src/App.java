import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        GestorFichero fichero = new GestorFichero("fichero.txt");

        //Comprobamos si el fichero existe
        if (fichero != null) {
            System.out.println("El fichero existe.");
        } else {
            System.out.println("El fichero no existe.");
        }

        //Mostramos el contenido del fichero
        fichero.mostrar();

        Scanner teclado = new Scanner(System.in);
        //Insertamos una nueva cadena en el fichero
        System.out.print("Introduce la cadena que quieres insertar: ");
        String insertarCadena = teclado.nextLine();
        if (fichero.insertar(insertarCadena)) {
            System.out.println("Cadena insertada correctamente.");
        } else {
            System.out.println("Error al insertar la cadena.");
        }

        //Buscamos una palabra en el fichero
        System.out.print("Introduce la palabra que quieres buscar: ");
        String buscarCadena = teclado.nextLine();
        if (fichero.buscar(buscarCadena)) {
            System.out.println("La palabra " + buscarCadena + " se encuentra en el fichero.");
        } else {
            System.out.println("La palabra " + buscarCadena + " no se encuentra en el fichero.");
        }

        //Actualizamos una palabra en el fichero
        System.out.print("Introduce la palabra o caracter que deseas sustituir: ");
        String origenCadena = teclado.nextLine();
        System.out.print("Introduce la palabra por la que deseas sustituir " + origenCadena + " : ");
        String destinoCadena = teclado.nextLine();


        System.out.println("En numero de palabras sustituidas son.: " + fichero.actualizar(origenCadena, destinoCadena));

        System.out.print("Introduce la palabra o caracter que deseas eliminar: ");
        String palabraEliminar = teclado.nextLine();

        if (fichero.eliminar2(palabraEliminar) > 0) {
            System.out.println("La palabra " + palabraEliminar + " se ha eliminado del fichero " + fichero.eliminar(palabraEliminar) + " veces.");
        } else {
            System.out.println("La palabra " + palabraEliminar + " no se ha encontrado en el fichero.");
        }
        fichero.mostrar();


        teclado.close();
    }
}