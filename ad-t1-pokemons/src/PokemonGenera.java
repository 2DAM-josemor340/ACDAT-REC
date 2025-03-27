import model.Pokemon;

import java.io.*;

public class PokemonGenera {
    public static void main(String[] args) {
        String pokemonTxt = "res" + File.separator + "Pokemon.txt";
        String pokemonsDat = "res" + File.separator + "Pokemons.dat";

        File file = new File(pokemonTxt);

        //Comprobar si existe y es un archivo, no es necesario usar arraylist, se puede escribir directamente
        if (file.exists() && file.isFile()) {
            System.out.println("El archivo Pokemons.txt existe.");
            try (BufferedReader br = new BufferedReader(new FileReader(pokemonTxt))) {
                String linea;
                //Leer archivo Pokemon.txt
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(";");
                    if (datos.length == 5) {
                        String nombre = datos[0];
                        int vida = Integer.parseInt(datos[1]);
                        int ataque = Integer.parseInt(datos[2]);
                        int defensa = Integer.parseInt(datos[3]);
                        boolean evoluciona = Boolean.parseBoolean(datos[4]);

                        Pokemon pok = new Pokemon(nombre, vida, ataque, defensa, evoluciona);

                        try (ObjectOutputStream foutput = new ObjectOutputStream(new FileOutputStream(pokemonsDat))) {
                            foutput.writeObject(pok);
                            System.out.println("Objeto Pokemon " + pok.getNombre() + ".Insertado en el archivo Pokemons.dat correctamente.");
                        } catch (IOException e) {
                            System.err.println("Error al escribir el archivo Pokemons.dat: " + e.getMessage());
                        }
                    }
                }
                System.out.println("Pokémons cargados correctamente desde el archivo.");
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo Pokemons.txt no existe.");
        }
    }
}