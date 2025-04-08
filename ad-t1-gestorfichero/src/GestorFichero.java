import java.io.*;

public class GestorFichero {

    private File fichero;

    public GestorFichero(String nombreFichero) {
        String ruta = "res" + File.separator + nombreFichero;
        this.fichero = new File(ruta);
    }

    //Muestra el contenido del fichero TXT
    public void mostrar() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            //br.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: El fichero no existe.");
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    //Insertar un nuevo registro en el fichero TXT
    public boolean insertar(String registro) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fichero, true));
            bw.write("\n" + registro);
            bw.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }
        return false;
    }

    //Buscar un registro en el fichero TXT
    public boolean buscar(String registro) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(registro)) {
                    br.close();
                    return true;
                }
            }
            br.close();

        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
        return false;
    }

    //Actualizar un registro (cadena origen por cadena destino) en el fichero TXT sin usar listas
    public int actualizar(String origen, String destino) throws IOException {
        int contador = 0;
        BufferedReader br = null;
        BufferedWriter bw = null;
        File aux = new File(fichero.getAbsolutePath() + ".tmp");

        try {
            br = new BufferedReader(new FileReader(fichero));
            bw = new BufferedWriter(new FileWriter(aux));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(origen)) {
                    linea = linea.replace(origen, destino);
                    contador++;
                }
                bw.write(linea);
            }
            bw.close();
            br.close();
        } catch (IOException e) {
            System.err.println("Error al actualizar el fichero: " + e.getMessage());
        }
        if (contador > 0) {
            aux.renameTo(fichero);
            aux.delete();
        }
        return contador;
    }

    //Eliminar un registro en el fichero TXT
    public int eliminar(String registro) throws IOException {
        int contador = 0;
        BufferedReader br = null;
        BufferedWriter bw = null;
        File aux = new File(fichero.getAbsolutePath() + ".tmp");

        try {
            br = new BufferedReader(new FileReader(fichero));
            bw = new BufferedWriter(new FileWriter(aux));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.contains(registro)) {
                    bw.write(linea);
                } else {
                    contador++;
                }
            }
            bw.close();
            br.close();

        } catch (IOException e) {
            System.err.println("Error al eliminar en el fichero: " + e.getMessage());
        }
        if (contador > 0) {
            aux.renameTo(fichero);
            aux.delete();
        }
        return contador;
    }
}