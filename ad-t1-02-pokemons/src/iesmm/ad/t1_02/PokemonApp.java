package iesmm.ad.t1_02;
import java.io.File;
import java.util.Scanner;

public class PokemonApp {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        //Solicitar la ruta del archivo CSV
        System.out.print("Introduce la ruta del archivo CSV que deseas leer : ");
        String rutaCSV = teclado.nextLine().toLowerCase();

        //Solicitar el tipo de Pokémon a mostrar
        System.out.print("Introduce el tipo de Pokémon a mostrar : ");
        String tipoPokemon = teclado.nextLine().toLowerCase();

        GestionaPokemon gestionaPokemon = new GestionaPokemon();
        File f = new File(rutaCSV);

        //Generamos el fichero Dat
       if(gestionaPokemon.generarFichero("pokemon.csv")) {
            System.out.println("Fichero generado correctamente");
        } else {
            System.out.println("Error al generar el fichero");
        }
        //Mostrar los Pokémon del tipo indicado
        gestionaPokemon.mostrarPokemons(f, tipoPokemon);

    }

}