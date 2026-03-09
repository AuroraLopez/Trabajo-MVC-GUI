package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    // READ
    public List<Usuarios> listar() {
        List<Usuarios> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, ape1, ape2, edad FROM Usuarios";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                lista.add(new Usuarios(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getInt("edad")
                ));
            }

        } catch (SQLException e) { e.printStackTrace(); }

        return lista;
    }

    // CREATE
    public void insertar(Usuarios c) {
        String sql = "INSERT INTO Usuarios (id, nombre, ape1, ape2, edad) VALUES (?,?,?,?,?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getId());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApe1());
            ps.setString(4, c.getApe2());
            ps.setInt(5, c.getEdad());

            ps.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }
    }

    // UPDATE
    public void actualizar(Usuarios c) {
        String sql = "UPDATE Usuarios SET nombre=?, ape1=?, ape2=?, edad=?  WHERE id=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
           
            ps.setInt(1, c.getId());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApe1());
            ps.setString(4, c.getApe2());
            ps.setInt(5, c.getEdad());

            ps.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }
    }

    // DELETE
    public void eliminar(int id) {
        String sql = "DELETE FROM Client WHERE id=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }
    }
}

