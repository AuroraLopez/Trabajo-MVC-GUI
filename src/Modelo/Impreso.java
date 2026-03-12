package Modelo;

import java.sql.Date;

public class Impreso extends Libros {
    //Atributos
    protected boolean esTapaDura;
    protected Date fecha_impresion;
    
    public Impreso(String iSBN, String titulo, String editorial, String autor, int numPaginas, String descrip,
            String genero, int anio_pub, String url, Tipo tipo, boolean esTapaDura, Date fecha_impresion) {
        super(iSBN, titulo, editorial, autor, numPaginas, descrip, genero, anio_pub, url, tipo);
        this.esTapaDura = esTapaDura;
        this.fecha_impresion = fecha_impresion;
    }

    public boolean isEsTapaDura() {
        return esTapaDura;
    }

    public void setEsTapaDura(boolean esTapaDura) {
        this.esTapaDura = esTapaDura;
    }

    public Date getFecha_impresion() {
        return fecha_impresion;
    }

    public void setFecha_impresion(Date fecha_impresion) {
        this.fecha_impresion = fecha_impresion;
    }

    @Override
    public String toString() {
        return "Impreso [ISBN=" + ISBN + ", esTapaDura=" + esTapaDura + ", fecha_impresion=" + fecha_impresion + "]";
    }
}
