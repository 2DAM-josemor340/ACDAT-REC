import model.Pokemon;

import java.io.*;
import java.util.Scanner;

public class PokemonDuelo2 {
    public static void main(String[] args) {
        String ruta = "res" + File.separator + "Pokemons.dat";
        File file = new File(ruta);


        Pokemon[] pokemonBatalla = buscar(file);
        //Comprobamos que el array no sea nulo
        if (pokemonBatalla != null) {
            batalla(pokemonBatalla[0], pokemonBatalla[1]);
        } else {
            System.out.println("No se encontraron los Pokémon seleccionados.");
        }

        System.out.println("FIN DEL JUEGO");
    }

    public static Pokemon[] buscar(File file) {
        Scanner teclado = new Scanner(System.in);
        //Indicamos los pokemons que queremos que comienzen la batalla
        System.out.print("Selecciona el primer Pokémon: ");
        String pok1 = teclado.nextLine();
        System.out.print("Selecciona el segundo Pokémon: ");
        String pok2 = teclado.nextLine();
        //Comprobamos que no sean los mismos
        if (pok1.equalsIgnoreCase(pok2)) {
            System.out.println("Los dos Pokémon son iguales. Fin del juego.");
            return null;
        }
        //Leemos el fichero en busca de los pokemons
        Pokemon[] pokemons = new Pokemon[2];
        try (ObjectInputStream finput = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Pokemon p = (Pokemon) finput.readObject();
                if (p.getNombre().equalsIgnoreCase(pok1)) {
                    pokemons[0] = p;
                } else if (p.getNombre().equalsIgnoreCase(pok2)) {
                    pokemons[1] = p;
                }
                if (pokemons[0] != null && pokemons[1] != null) {
                    return pokemons;
                }
            }
        } catch (EOFException e) {
            System.out.println("Uno o ambos Pokémon no fueron encontrados. Fin del juego.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }

        return null;
    }

    private static void batalla(Pokemon pok1, Pokemon pok2) {
        while (pok1.getVida() > 0 && pok2.getVida() > 0) {
            System.out.println("DUELO ENTRE:");
            System.out.println(pok1 + "\n" + pok2);
            pausa();

            System.out.println("TURNO DE ATAQUE PARA " + pok1.getNombre());
            pok1.atacar(pok2, (int) (Math.random() * pok1.getAtaque()));
            pausa();

            if (pok2.getVida() > 0) {
                System.out.println("DUELO ENTRE:");
                System.out.println(pok1 + "\n" + pok2);
                pausa();

                System.out.println("TURNO DE ATAQUE PARA " + pok2.getNombre());
                pok2.atacar(pok1, (int) (Math.random() * pok2.getAtaque()));
                pausa();
            }
        }

        if (pok1.getVida() <= 0) {
            System.out.println(pok1.getNombre() + " HA SIDO DERROTADO.");
        } else {
            System.out.println(pok2.getNombre() + " HA SIDO DERROTADO.");
        }
    }

    private static void pausa() {
        try {
            System.out.print("\n\t\tPRESIONAR ENTER PARA CONTINUAR\n\n");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
