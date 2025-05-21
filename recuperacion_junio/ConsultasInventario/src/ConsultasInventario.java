import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jaxen.JaxenException;

import java.io.File;
import java.util.List;

public class ConsultasInventario {
    public static void main(String[] args) {
        File archivo = new File("res" + File.separator + "inventario.xml");

        if (archivo.exists() && archivo.isFile()) {
            try {
                // 1. Número de videojuegos con plataforma NES
                List<?> resultado = (List<?>) ProcesadorXPath.consultaJaxen(archivo, "count(//videojuego[plataforma='NES'])");
                Double numNES = Double.parseDouble(resultado.get(0).toString());
                System.out.println("1) Número de videojuegos con plataforma NES: " + numNES.intValue());

                // 2. Número de videojuegos con al menos una captura
                List<?> resultadoCaptura = (List<?>) ProcesadorXPath.consultaJaxen(archivo, "count(//videojuego[imagenes/captura])");
                Double numConCaptura = Double.parseDouble(resultadoCaptura.get(0).toString());
                System.out.println("2) Videojuegos con al menos una captura: " + numConCaptura.intValue());

                // 3. Títulos de todos los videojuegos
                List<?> titulos = (List<?>) ProcesadorXPath.consultaJaxen(archivo, "//videojuego/titulo");
                System.out.println("3) Títulos de todos los videojuegos:");
                for (Object o : titulos) {
                    Element e = (Element) o;
                    System.out.println("- " + e.getText());
                }

                // 4. Stock total de todos los juegos de mesa
                List<?> stockMesa = (List<?>) ProcesadorXPath.consultaJaxen(archivo, "sum(//juegoMesa/stock)");
                Double totalStockMesa = Double.parseDouble(stockMesa.get(0).toString());
                System.out.println("4) Stock total de juegos de mesa: " + totalStockMesa.intValue());

            } catch (DocumentException e) {
                System.err.println("Error al procesar el documento XML");
            } catch (JaxenException e) {
                System.err.println("Error en la consulta XPath");
            } catch (Exception e) {
                System.err.println("Error inesperado: " + e.getMessage());
            }
        } else {
            System.err.println("No se encontró el archivo XML");
        }
    }
}
