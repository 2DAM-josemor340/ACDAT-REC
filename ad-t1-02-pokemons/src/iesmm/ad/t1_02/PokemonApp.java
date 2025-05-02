package iesmm.ad.t1_02;

import java.io.File;
import java.util.Scanner;

import static iesmm.ad.t1_02.GestionaPokemon.generarFicheroDat;

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

        //Generamos el fichero Dat
        if (gestionaPokemon.generarFichero(rutaCSV)) {
            System.out.println("Fichero generado correctamente");
        } else {
            System.out.println("Error al generar el fichero");
        }

        File f = new File("res" + File.separator + rutaCSV);
        //Mostrar los Pokémon del tipo indicado
        System.out.println("Mostrar los Pokémon del tipo indicado");
        gestionaPokemon.mostrarPokemons(f, tipoPokemon);

        //Generar el fichero .dat a partir del .csv con todos los datos
        System.out.println("Generar el fichero .dat a partir del .csv con todos los datos");
        File dat = generarFicheroDat(rutaCSV);

        //Generamos el archivo pokemon.txt
        System.out.println("Generamos el archivo pokemon.txt");
        GestionaPokemon.contabilizarTipos(dat);

        //Mostramos el contenido del archivo pokemon.txt
        System.out.println("Contenido del archivo pokemon.txt:");
        GestionaPokemon.mostrarContenidoPokemonTxt();

        //Cerrar el scanner
        teclado.close();
    }

}