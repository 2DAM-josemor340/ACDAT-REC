package dao;

import model.Articulo;
import utils.Conexion;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DAOImpl implements DAO {
    private static final Logger logTag = Logger.getLogger("AD");

    @Override
    public boolean insertar(Articulo articulo) {
        Connection c;
        boolean valueReturn = false;
        String sql = "INSERT INTO articulo (codigo, nombre, descripcion, existencia, precio, categoria) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            c = Conexion.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, articulo.getCodigo());
            ps.setString(2, articulo.getNombre());
            ps.setString(3, articulo.getDescripcion());
            ps.setDouble(4, articulo.getExistencia());
            ps.setDouble(5, articulo.getPrecio());
            ps.setInt(6, articulo.getCategoria());

            valueReturn = ps.executeUpdate() > 0;
            Conexion.close();

        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (IOException e) {
            System.err.println("Error de conexión a la base de datos");
        }


        return valueReturn;
    }

    @Override
    public List<Articulo> listarArticulos() {
        Connection c;
        List<Articulo> listaArticulos = new ArrayList<Articulo>();
        String sql = "SELECT * FROM articulo";
        try {
            c = Conexion.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Articulo articulo = new Articulo(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("existencia"),
                        rs.getDouble("precio"),
                        rs.getInt("categoria")
                );
                listaArticulos.add(articulo);
            }
            Conexion.close();
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (IOException e) {
            System.err.println("Error de conexión a la base de datos");
        }

        return listaArticulos;
    }

    @Override
    public List<Articulo> listarArticulosPorCategoria(String categoria) {
        Connection c;
        List<Articulo> listaArticulos = new ArrayList<Articulo>();

        String sql = "SELECT * FROM articulo WHERE categoria = ?";
        try {
            c = Conexion.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Articulo articulo = new Articulo(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("existencia"),
                        rs.getDouble("precio"),
                        rs.getInt("categoria")
                );
                listaArticulos.add(articulo);
            }
            Conexion.close();
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (IOException e) {
            System.err.println("Error de conexión a la base de datos");
        }

        return listaArticulos;
    }

    @Override
    public Articulo obtenerPorId(int id) {
        Connection c;
        boolean encontrado = false;
        Articulo articulo = null;
        String sql = "SELECT * FROM articulo WHERE id = ?";
        try {
            c = Conexion.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                articulo = new Articulo(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("existencia"),
                        rs.getDouble("precio"),
                        rs.getInt("categoria")
                );
                encontrado = true;
            }
            Conexion.close();

        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (IOException e) {
            System.err.println("Error de conexión a la base de datos");
        }

        return articulo;
    }

    @Override
    public boolean actualizar(Articulo articulo) {
        Connection c;
        boolean valueReturn = false;
        String sql = "UPDATE articulo SET codigo = ?, nombre = ?, descripcion = ?, existencia = ?, precio = ?, categoria = ? WHERE id = ?";
        try {
            c = Conexion.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, articulo.getCodigo());
            ps.setString(2, articulo.getNombre());
            ps.setString(3, articulo.getDescripcion());
            ps.setDouble(4, articulo.getExistencia());
            ps.setDouble(5, articulo.getPrecio());
            ps.setInt(6, articulo.getCategoria());
            ps.setInt(7, articulo.getId());

            valueReturn = ps.executeUpdate() > 0;
            Conexion.close();

        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (IOException e) {
            System.err.println("Error de conexión a la base de datos");
        }

        return valueReturn;
    }

    @Override
    public boolean eliminar(int id) {
        Connection c;
        boolean valueReturn = false;
        String sql = "DELETE FROM articulo WHERE id = ?";
        try {
            c = Conexion.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            valueReturn = ps.executeUpdate() > 0;
            Conexion.close();

        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (IOException e) {
            System.err.println("Error de conexión a la base de datos");
        }

        return valueReturn;
    }

    /**
     * Muestra los errores y excepciones producidas en la operación en la base de datos.
     *
     * @param e Excepción
     */
    private static void showSQLErrors(SQLException e) {
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
    }
}