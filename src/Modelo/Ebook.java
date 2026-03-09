
package Modelo;

import java.sql.Date;

public class Ebook extends Libros{

    //Atributos
    protected double tamanio;
    protected String formato;
    
    //Contructor
 
    //Getters and Setters

    public double getTamanio() {
        return tamanio;
    }

    //Constructores

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public Ebook(String iSBN, String titulo, String editorial, String autor, int numPaginas, String descrip,
            String genero, Date anio_pub, Tipo tipo, double tamanio, String formato) {
        super(iSBN, titulo, editorial, autor, numPaginas, descrip, genero, anio_pub, tipo);
        this.tamanio = tamanio;
        this.formato = formato;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    @Override
    public String toString() {
        return "Ebook [tamanio=" + tamanio + ", formato=" + formato + "]";
    }
}
