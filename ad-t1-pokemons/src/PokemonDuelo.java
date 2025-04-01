import model.Pokemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

public class PokemonDuelo {
    public static void main(String[] args) {
        Pokemon pok1 = null, pok2 = null;

        try {
            // Lectura del fichero de dos pokemons
            ObjectInputStream finput = new ObjectInputStream(new FileInputStream("res" + File.separator + "Pokemons.dat"));
            pok1 = (Pokemon) finput.readObject();
            pok2 = (Pokemon) finput.readObject();
            finput.close();

            // Generar batalla entre pokemons
            if (pok1 != null && pok2 != null)
                batalla(pok1, pok2);

            // Fin del programa
            System.out.println("FIN DEL JUEGO");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void batalla (Pokemon pok1, Pokemon pok2) {
        while (pok1.getVida() > 0 && pok2.getVida() > 0) {
            System.out.println("DUELO ENTRE:");
            System.out.println(pok1 + "\n" + pok2);
            pausa();

            System.out.println("TURNO DE ATAQUE PARA " + pok1.getNombre());
            pok1.atacar(pok2, new Random().nextInt(pok1.getAtaque()));
            pausa();

            if (pok2.getVida() >= 0) {
                System.out.println("DUELO ENTRE:");
                System.out.println(pok1 + "\n" + pok2);
                pausa();

                System.out.println("TURNO DE ATAQUE PARA " + pok2.getNombre());
                pok2.atacar(pok1, new Random().nextInt(pok2.getAtaque()));
                pausa();

                if (pok1.getVida() <= 0)
                    System.out.println(pok1.getNombre() + " HA SIDO DERROTADO.\n");
            } else
                System.out.println(pok2.getNombre() + " HA SIDO DERROTADO.\n");
        }
    }

    private static void pausa() {
        try {
            System.out.print("\n\t\tPRESIONAR ENTER PARA ATACAR\n\n");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
