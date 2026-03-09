package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibrosDAO {
    // READ (lista de Persona)
    public ArrayList<Libros> listar() {
        ArrayList<Libros> libros = new ArrayList<>();
        String sql = "SELECT ISBN, titulo, editorial, autor, numPag, descripcion, genero, anio_pub, tipo FROM libros";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Tipo tipo = Tipo.valueOf(
                    rs.getString("tipo")
                );
                libros.add(new Libros(rs.getString("ISBN"),                     
	                        rs.getString("titulo"),
                            rs.getString("editorial"),
                            rs.getString("autor"),
                            rs.getInt("numPag"),
                            rs.getString("descripcion"),
                            rs.getString("genero"),
                            rs.getDate("anio_pub"),
                            tipo
                        ))));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return libros;
    }
    // // CREATE
    // public void insertar(Categoria p) {
    //     String sql = "INSERT INTO categoria (codigo, nombre) VALUES (?, ?)";
    //     try (Connection conn = Conexion.getConnection();
    //         PreparedStatement ps = conn.prepareStatement(sql)) {
    //         ps.setInt(1, p.getId()); ps.setString(2, p.getNombre());
    //         ps.executeUpdate();
    //     } catch (SQLException e) { e.printStackTrace(); }
    // }
    // // UPDATE
    // public void actualizar(Categoria p) {
    //     String sql = "UPDATE categoria SET nombre=? WHERE codigo=?";
    //     try (Connection conn = Conexion.getConnection();
    //          PreparedStatement ps = conn.prepareStatement(sql)) {
    //         ps.setString(1, p.getNombre());
    //         ps.setInt(2, p.getId()); ps.executeUpdate();
    //     } catch (SQLException e) {  e.printStackTrace();  }
    // }
    // // DELETE
    // public void eliminar(int id) {
    //     String sql = "DELETE FROM categoria WHERE codigo=?";
    //     try (Connection conn = Conexion.getConnection();
    //          PreparedStatement ps = conn.prepareStatement(sql)) {
    //         ps.setInt(1, id); ps.executeUpdate();
    //     } catch (SQLException e) {  e.printStackTrace();  }
    // }
}
