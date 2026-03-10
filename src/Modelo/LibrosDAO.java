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
                    rs.getString("tipo").toUpperCase() //Es necesario, porque la palabra debe ser identica, y cuenta las mayus
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
                        ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return libros;
    }

    // CREATE
    public void insertar(Libros p) {
        String sql = "INSERT INTO Libros (ISBN, titulo, editorial, autor, numPag, descripcion, genero, anio_pub) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getISBN()); 
            ps.setString(3, p.getTitulo());
            ps.setString(4, p.getEditorial());
            ps.setString(5, p.getAutor());
            ps.setString(6, p.getDescrip());
            ps.setString(7, p.getGenero());
            ps.setDate(8, p.getAnio_pub());
            //Enum a String
            ps.setString(9, p.getTipo().name()); 
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // UPDATE
    public void actualizar(Libros p) {
        String sql = "UPDATE Libros SET titulo=?, editorial=?, autor=?, numPag=?, descripcion=?, genero=?, anio_pub=? WHERE ISBN=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getISBN()); 
            ps.setString(3, p.getTitulo());
            ps.setString(4, p.getEditorial());
            ps.setString(5, p.getAutor());
            ps.setString(6, p.getDescrip());
            ps.setString(7, p.getGenero());
            ps.setDate(8, p.getAnio_pub());
            //Enum a String
            ps.setString(9, p.getTipo().name()); 
            ps.executeUpdate();
        } catch (SQLException e) {  e.printStackTrace();  }
    }

    // DELETE
    public void eliminar(int id) {
        String sql = "DELETE FROM Libros WHERE id=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); 
            ps.executeUpdate();
        } catch (SQLException e) {  e.printStackTrace();  }
    }
}
