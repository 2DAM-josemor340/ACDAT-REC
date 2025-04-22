package iesmm.ad.t1_02;

import java.io.*;
import java.util.*;

public class GestionaPokemon {



    public void mostrarPokemons(File f, String tipo) {
        //Comprobamos si el archivo existe
        if (!f.exists()) {
            System.err.println("El archivo " + f.getAbsolutePath() + " no existe.");
        } else {
            System.err.println("El archivo " + f.getAbsolutePath() + " existe.");
            String mostrarPokemons = "res" + File.separator + "mostrarPokemons.csv";

            ArrayList<String> lista = new ArrayList<>();

            try (BufferedReader fichero = new BufferedReader(new FileReader(f));
                 FileWriter foutput = new FileWriter(mostrarPokemons);
                 BufferedWriter bw = new BufferedWriter(foutput)) {

                String linea = fichero.readLine();
                while (linea != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 7) {
                        try {
                            String nombre = datos[1];
                            String tipoPokemon = datos[2];
                            int vida = Integer.parseInt(datos[3]);
                            int ataque = Integer.parseInt(datos[4]);
                            int defensa = Integer.parseInt(datos[5]);
                            int velocidad = Integer.parseInt(datos[6]);

                            if (tipoPokemon.equalsIgnoreCase(tipo)) {
                                lista.add(nombre);
                                Pokemon pok = new Pokemon(nombre, tipoPokemon, vida, ataque, defensa, velocidad);
                                foutput.write(pok.getNombre() + "," + pok.getTipo() + "," + pok.getVida() + "," + pok.getAtaque() + "," + pok.getDefensa() + "," + pok.getVelocidad() + "\n");
                                System.out.println("Objeto Pokemon " + pok.getNombre() + "," + pok.getTipo() + "," + pok.getVida() + "," + pok.getAtaque() + "," + pok.getDefensa() + "," + pok.getVelocidad() + " insertado correctamente." + "\n");
                            }
                            Collections.sort(lista);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato numérico en línea: " + linea);
                        }
                    } else {
                        System.err.println("Línea con formato incorrecto: " + linea);
                    }
                    linea = fichero.readLine();
                }
                Collections.sort(lista);

                for (String name : lista) {
                    System.out.println("Nombre: " + name);
                }
            } catch (IOException e) {
                System.err.println("Error al procesar el archivo: " + e.getMessage());
            }
        }
    }

    public boolean generarFichero(String fcsv) {

        File ficheroCsv = new File("res" + File.separator + fcsv);
        String ruta = ficheroCsv.getParent();
        File ficheroDat = new File(ruta + fcsv + ".dat");
        boolean resultado = false;

        try (BufferedReader fichero = new BufferedReader(new FileReader(ficheroCsv));
             ObjectOutputStream foutput = new ObjectOutputStream(new FileOutputStream(ficheroDat))) {

            String linea = fichero.readLine();
            while (linea != null) {
                String[] datos = linea.split(",");
                if (datos.length == 7) {
                    try {
                        String nombre = datos[1];
                        String tipoPokemon = datos[2];
                        int vida = Integer.parseInt(datos[3]);
                        int ataque = Integer.parseInt(datos[4]);
                        int defensa = Integer.parseInt(datos[5]);
                        int velocidad = Integer.parseInt(datos[6]);


                        Pokemon pok = new Pokemon(nombre, tipoPokemon, vida, ataque, defensa, velocidad);
                        foutput.writeObject(pok);
                        //System.out.println("Objeto Pokemon " + pok.getNombre() + " insertado correctamente.");

                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato numérico en línea: " + linea);
                    }
                } else {
                    System.err.println("Línea con formato incorrecto: " + linea);
                }
                linea = fichero.readLine();
            }
            resultado = true;

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
        if (resultado) {
            System.out.println("Fichero generado correctamente: " + ficheroDat.getAbsolutePath());
        } else {
            System.out.println("Error al generar el fichero.");
        }

        return resultado;
    }

    public static void contabilizarTipos(File f) {
        HashMap<String, Integer> tipoCount = new HashMap<>();

        try (BufferedReader fichero = new BufferedReader(new FileReader(f))) {
            String linea = fichero.readLine();
            while (linea  != null) {
                String[] datos = linea.split(",");
                if (datos.length == 7) {
                    String [] tipos = new String[]{datos[2]};

                    for (String tipo : tipos) {
                        tipoCount.put(tipo, tipoCount.getOrDefault(tipo, 0) + 1);
                    }
                }
                linea = fichero.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }

        System.out.println("Conteo de tipos: " + tipoCount);
    }

}