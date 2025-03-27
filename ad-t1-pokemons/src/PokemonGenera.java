import model.Pokemon;

import java.io.*;

public class PokemonGenera {
    public static void main(String[] args) {
        String pokemonTxt = "res" + File.separator + "Pokemon.txt";
        String pokemonsDat = "res" + File.separator + "Pokemons.dat";

        File fileTxt = new File(pokemonTxt);

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileTxt));
            ObjectOutputStream foutput = new ObjectOutputStream(new FileOutputStream(pokemonsDat));

            //Comprobar si existe y es un archivo, no es necesario usar arraylist, se puede escribir directamente
            if (fileTxt.exists() && fileTxt.isFile()) {
                System.out.println("El archivo Pokemons.txt existe.");


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
                        foutput.writeObject(pok);
                        System.out.println("Objeto Pokemon " + pok.getNombre() + ".Insertado en el archivo Pokemons.dat correctamente.");

                    }
                }
                System.out.println("Pokémons cargados correctamente desde el archivo.");
                br.close();
                foutput.close();
            } else {
                System.out.println("El archivo Pokemons.txt no existe.");
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }


    }

}