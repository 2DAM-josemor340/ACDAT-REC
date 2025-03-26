import model.Pokemon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonGenera {
	public static void main(String[] args) {
		String pokemonTxt = "res" + File.separator + "Pokemon.txt";
		String pokemonsDat = "res" + File.separator + "Pokemons.dat";

		List<Pokemon> listaPokemons = new ArrayList<>();

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
					listaPokemons.add(pok);
				}
			}
			System.out.println("Pokémon cargados correctamente desde el archivo.");
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}

		//Guardar en Pokemons.dat
		try (ObjectOutputStream foutput = new ObjectOutputStream(new FileOutputStream(pokemonsDat))) {
			foutput.writeObject(listaPokemons);
			System.out.println("Archivo Pokemons.dat creado correctamente.");
		} catch (IOException e) {
			System.err.println("Error al escribir el archivo Pokemons.dat: " + e.getMessage());
		}
	}
}