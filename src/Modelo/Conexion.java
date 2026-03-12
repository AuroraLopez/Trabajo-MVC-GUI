package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3307/trabajoengrupo";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "M@rggio15xAlme25";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}