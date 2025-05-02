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

            ArrayList<String> lista = new ArrayList<>();

            try {
                BufferedReader fichero = new BufferedReader(new FileReader(f));
                String linea = fichero.readLine();
                while (linea != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 7) {
                        try {
                            String nombre = datos[1];
                            String tipoPokemon = datos[2];

                            if (tipoPokemon.equalsIgnoreCase(tipo)) {
                                lista.add(nombre);
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
                fichero.close();
                for (String name : lista) {
                    System.out.println("Nombre: " + name);
                }
            } catch (IOException e) {
                System.err.println("Error al procesar el archivo: " + e.getMessage());
            }
        }
    }

    public static boolean generarFichero(String fcsv) {

        File ficheroCsv = new File("res" + File.separator + fcsv);
        File ficheroDat = new File(ficheroCsv.getAbsolutePath().replace(".csv", ".dat"));
        boolean resultado = false;

        try {
            BufferedReader fichero = new BufferedReader(new FileReader(ficheroCsv));
            ObjectOutputStream foutput = new ObjectOutputStream(new FileOutputStream(ficheroDat));

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


                        //Pokemon pok = new Pokemon(nombre, tipoPokemon, vida, ataque, defensa, velocidad);
                        //foutput.writeObject(pok);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato numérico en línea: " + linea);
                    }
                } else {
                    System.err.println("Línea con formato incorrecto: " + linea);
                }
                linea = fichero.readLine();
            }
            resultado = true;
            fichero.close();
            foutput.close();

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }

        return resultado;
    }

    public static File generarFicheroDat(String fcsv) {
        File ficheroCsv = new File("res" + File.separator + fcsv);
        File ficheroDat = new File(ficheroCsv.getAbsolutePath().replace(".csv", ".dat"));
        if (ficheroCsv.exists()) {
            try {
                BufferedReader fichero = new BufferedReader(new FileReader(ficheroCsv));
                ObjectOutputStream foutput = new ObjectOutputStream(new FileOutputStream(ficheroDat));

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
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato numérico en línea: " + linea);
                        }
                    } else {
                        System.err.println("Línea con formato incorrecto: " + linea);
                    }
                    linea = fichero.readLine();
                }
                fichero.close();
                foutput.close();

            } catch (IOException e) {
                System.err.println("Error al procesar el archivo: " + e.getMessage());
            }
        } else {
            System.err.println("El archivo " + ficheroCsv.getAbsolutePath() + " no existe.");
            ficheroDat= null;
            return ficheroDat;
        }

        return ficheroDat;
    }


    public static void contabilizarTipos(File f) {
        try {
            if (f.exists()) {
                //String ruta= f.getAbsolutePath();
                //File ficheroTxt=new File(ruta.substring(0,ruta.length()-3)+".txt");
                //File fichero = new File("res" + File.separator + f);
                File ficheroTxt = new File(f.getAbsolutePath().replace(".dat", ".txt"));
                ObjectInputStream finput = new ObjectInputStream(new FileInputStream(f));
                BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroTxt));
                Map<String, Integer> tipos = new HashMap<>();
                Pokemon pokemon;
                String tipoPokemon;
                try {
                    while ((pokemon = (Pokemon) finput.readObject()) != null) {
                        tipoPokemon = pokemon.getTipo();
                        tipos.put(tipoPokemon, tipos.getOrDefault(tipoPokemon, 0) + 1);
                    }
                } catch (EOFException e) {
                    System.err.println("Error al procesar el archivo: " + e.getMessage());
                } catch (IOException e) {
                    System.err.println("Error al procesar el archivo: " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    System.err.println("Error al procesar el archivo: " + e.getMessage());
                }
                for (String key : tipos.keySet()) {
                    bw.write(key + " " + tipos.get(key));
                    bw.newLine();
                }

                bw.close();
                finput.close();

            } else {
                System.err.println("El archivo " + f.getAbsolutePath() + " no existe.");
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    public static void mostrarContenidoPokemonTxt() {
        File ficheroTxt = new File("res" + File.separator + "pokemon.txt");
        if (ficheroTxt.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(ficheroTxt))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.err.println("El archivo " + ficheroTxt.getAbsolutePath() + " no existe.");
        }
    }

}

