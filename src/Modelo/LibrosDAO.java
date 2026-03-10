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
        String sql = "SELECT ISBN, titulo, editorial, autor, numPag, descripcion, genero, anio_pub, url, tipo FROM libros";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                
                Tipo tipo = Tipo.valueOf(
                    rs.getString("tipo").toUpperCase() //Es necesario, porque la palabra debe ser identica, y cuenta las mayus
                );

                libros.add(new Libros(
                            rs.getString("ISBN"),                     
	                        rs.getString("titulo"),
                            rs.getString("editorial"),
                            rs.getString("autor"),
                            rs.getInt("numPag"),
                            rs.getString("descripcion"),
                            rs.getString("genero"),
                            rs.getInt("anio_pub"),
                            rs.getString("url"),
                            tipo
                        ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return libros;
    }

    // CREATE
    public void insertar(Libros p) {
        String sql = "INSERT INTO Libros (ISBN, titulo, editorial, autor, numPag, descripcion, genero, anio_pub, url, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getISBN()); 
            ps.setString(2, p.getTitulo());
            ps.setString(3, p.getEditorial());
            ps.setString(4, p.getAutor());
            ps.setString(5, p.getDescrip());
            ps.setString(6, p.getGenero());
            ps.setInt(7, p.getAnio_pub());
            ps.setString(8, p.getUrl());
            //Enum a String
            ps.setString(9, p.getTipo().name()); 
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // UPDATE
    public void actualizar(Libros p) {
        String sql = "UPDATE Libros SET titulo=?, editorial=?, autor=?, numPag=?, descripcion=?, genero=?, anio_pub=?, url=?, tipo=? WHERE ISBN=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getISBN()); 
            ps.setString(3, p.getTitulo());
            ps.setString(4, p.getEditorial());
            ps.setString(5, p.getAutor());
            ps.setString(6, p.getDescrip());
            ps.setString(7, p.getGenero());
            ps.setInt(8, p.getAnio_pub());
            ps.setString(9, p.getUrl());
            //Enum a String
            ps.setString(10, p.getTipo().name()); 
            ps.executeUpdate();
        } catch (SQLException e) {  e.printStackTrace();  }
    }

    // DELETE
    public void eliminar(String ISBN) {
        String sql = "DELETE FROM Libros WHERE id=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ISBN); 
            ps.executeUpdate();
        } catch (SQLException e) {  e.printStackTrace();  }
    }
}
