package Modelo;

import java.sql.Date;

public class ParticipaPrestamo {
    //Atributos
    // protected Libros libro;
    // protected Usuarios usuario;   
    // protected String fecha_prestamo;
    // protected String fecha_devolucion;
    
    protected String libro;
    protected int id_usuario;   
    protected Date fecha_prestamo;
    protected Date fecha_devolucion;
    
    
    public ParticipaPrestamo() {
    }

    public ParticipaPrestamo(String libro, int id_usuario, Date fecha_prestamo, Date fecha_devolucion) {
        this.libro = libro;
        this.id_usuario = id_usuario;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    @Override
    public String toString() {
        return "ParticipaPrestamo [libro=" + libro + ", id_usuario=" + id_usuario + ", fecha_prestamo=" + fecha_prestamo
                + ", fecha_devolucion=" + fecha_devolucion + "]";
    }
    

    // public ParticipaPrestamo(Libros libro, Usuarios usuario, String fecha_prestamo, String fecha_devolucion) {
    //     this.libro = libro;
    //     this.usuario = usuario;
    //     this.fecha_prestamo = fecha_prestamo;
    //     this.fecha_devolucion = fecha_devolucion;
    // }

    // public Libros getLibro() {
    //     return libro;
    // }

    // public void setLibro(Libros libro) {
    //     this.libro = libro;
    // }

    // public Usuarios getUsuario() {
    //     return usuario;
    // }

    // public void setUsuario(Usuarios usuario) {
    //     this.usuario = usuario;
    // }

    // public String getFecha_prestamo() {
    //     return fecha_prestamo;
    // }

    // public void setFecha_prestamo(String fecha_prestamo) {
    //     this.fecha_prestamo = fecha_prestamo;
    // }

    // public String getFecha_devolucion() {
    //     return fecha_devolucion;
    // }

    // public void setFecha_devolucion(String fecha_devolucion) {
    //     this.fecha_devolucion = fecha_devolucion;
    // }

    // @Override
    // public String toString() {
    //     return "ParticipaPrestamo [libro=" + libro + ", usuario=" + usuario + ", fecha_prestamo=" + fecha_prestamo
    //             + ", fecha_devolucion=" + fecha_devolucion + "]";
    // }
    
}
