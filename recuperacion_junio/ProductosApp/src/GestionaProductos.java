import java.io.*;
import java.util.*;

public class GestionaProductos {

    private File ficheroCSV;
    private File ficheroDAT;

    public GestionaProductos(String nombreFicheroCSV) {
        String rutaCSV = "res" + File.separator + nombreFicheroCSV;
        this.ficheroCSV = new File(rutaCSV);
        String rutaDAT = rutaCSV.replace(".csv", ".dat");
        this.ficheroDAT = new File(rutaDAT);
    }

    // Genera el fichero .dat a partir del .csv
    public boolean generarFichero() {
        boolean estado = false;
        if (!ficheroCSV.exists()) {
            System.out.println("El archivo " + ficheroCSV.getPath() + " no existe o no es un CSV.");
            return estado;
        }

        List<Producto> productos = new ArrayList<>();
        Scanner sc = null;
        ObjectOutputStream oos = null;

        try {
            sc = new Scanner(ficheroCSV);
            while (sc.hasNextLine()) {
                String[] campos = sc.nextLine().split(",");
                if (campos.length == 4) {
                    try {
                        int cod = Integer.parseInt(campos[0].trim());
                        String nombre = campos[1].trim();
                        float precio = Float.parseFloat(campos[2].trim());
                        int stock = Integer.parseInt(campos[3].trim());
                        if (!nombre.isEmpty()) {
                            productos.add(new Producto(cod, nombre, precio, stock));
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }

            oos = new ObjectOutputStream(new FileOutputStream(ficheroDAT));
            oos.writeObject(productos);
            estado = true;

            // Cerrar flujos
            sc.close();
            oos.close();

            return estado;
        } catch (IOException e) {
            estado = false;
            return estado;
        }
    }

    // Consulta productos con stock menor a minStock
    public void consultarStock(File f, int minStock) {
        boolean archivoValido=true;
        boolean estado = false;
        if (!f.exists()) {
            archivoValido=false;
        }

        if (!archivoValido) {
            System.out.println("Archivo binario no encontrado: " + (f != null ? f.getPath() : "null"));
        } else {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                List<Producto> productos = (ArrayList<Producto>) ois.readObject();

                // Agrupar por stock usando HashMap
                Map<Integer, List<Producto>> mapaStock = new HashMap<>();
                for (Producto p : productos) {
                    if (p.getStock() < minStock) {
                        if (!mapaStock.containsKey(p.getStock())) {
                            mapaStock.put(p.getStock(), new ArrayList<>());
                        }
                        mapaStock.get(p.getStock()).add(p);
                    }
                }

                // Ordenar las claves (stock) en orden descendente
                List<Integer> stocksOrdenados = new ArrayList<>(mapaStock.keySet());
                stocksOrdenados.sort(Collections.reverseOrder());

                // Mostrar productos
                System.out.println("Productos con stock menor a " + minStock + ":");
                for (Integer stock : stocksOrdenados) {
                    for (Producto p : mapaStock.get(stock)) {
                        System.out.println(p);
                        System.out.println("----------------------");
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al leer el archivo binario.");
            }
        }
    }
}
