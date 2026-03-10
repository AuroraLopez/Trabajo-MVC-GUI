package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipaPrestamoDAO {

    // READ
    public List<ParticipaPrestamoDAO> listar() {
        List<ParticipaPrestamoDAO> lista = new ArrayList<>();
        String sql = "SELECT id_libro, id_usuario, fecha_prestamo, fecha_devolucion FROM ParticipaPrestamoDAO";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) { 
            
                //Constructor
                lista.add(new ParticipaPrestamoDAO(
                    rs.getString("id_libro"),
                    rs.getInt("id_usuario"),
                    rs.getString("fecha_prestamo"),
                    rs.getString("fecha_devolucion")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    // CREATE
    public void insertar(Edicion e) {
        String sql = "INSERT INTO Edicion (id, id_curso, fecha_inicio, fecha_fin, horario, lugar, id_empleado_imparte) VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, e.getId());
            ps.setInt(2, e.getIdCurso());
            ps.setDate(3, e.getFechaInicio());
            ps.setDate(4, e.getFechaFin());
            ps.setString(5, e.getHorario());
            ps.setString(6, e.getLugar());
            ps.setObject(7, e.getIdEmpleadoImparte());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    // UPDATE
    public void actualizar(Edicion e) {
        String sql = "UPDATE Edicion SET id_curso=?, fecha_inicio=?, fecha_fin=?, horario=?, lugar=?, id_empleado_imparte=? WHERE id=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, e.getIdCurso());
            ps.setDate(2, e.getFechaInicio());
            ps.setDate(3, e.getFechaFin());
            ps.setString(4, e.getHorario());
            ps.setString(5, e.getLugar());
            ps.setObject(6, e.getIdEmpleadoImparte());
            ps.setInt(7, e.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    // DELETE
    public void eliminar(int id) {
        String sql = "DELETE FROM Edicion WHERE id=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

