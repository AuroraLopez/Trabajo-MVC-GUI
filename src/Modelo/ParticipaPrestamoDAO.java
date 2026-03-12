package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipaPrestamoDAO {

    // READ
    public List<ParticipaPrestamo> listar() {
        List<ParticipaPrestamo> lista = new ArrayList<>();
        String sql = "SELECT id_libro, id_usuario, fecha_prestamos, fecha_devolucion FROM ParticipaPrestamos";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) { 
            
                //Constructor
                lista.add(new ParticipaPrestamo(
                    rs.getString("id_libro"),
                    rs.getInt("id_usuario"),
                    rs.getDate("fecha_prestamos"),
                    rs.getDate("fecha_devolucion")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    // CREATE
    public void insertar(ParticipaPrestamo e) {
        String sql = "INSERT INTO ParticipaPrestamos (id_libro, id_usuario, fecha_prestamos, fecha_devolucion) VALUES (?,?,?,?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getLibro());
            ps.setInt(2, e.getId_usuario());
            ps.setDate(3, e.getFecha_prestamo());
            ps.setDate(4, e.getFecha_devolucion());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    // // UPDATE
    public void actualizar(ParticipaPrestamo e) {
        String sql = "UPDATE ParticipaPrestamos SET fecha_prestamos, fecha_devolucion WHERE id_libro AND id_usuario,";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, e.getFecha_prestamo());
            ps.setDate(2, e.getFecha_devolucion());
            ps.setString(3, e.getLibro());
            ps.setInt(4, e.getId_usuario());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    // // DELETE
    public void eliminar(String id_libro, int id_us) {
        String sql = "DELETE FROM ParticipaPrestamos WHERE id_libro=? AND id_usuario=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id_libro);
            ps.setInt(2, id_us);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
