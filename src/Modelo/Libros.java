package Modelo;

import java.sql.Date;

public class Libros {
    //Atributos
    protected String ISBN;
    protected String titulo;
    protected String editorial;
    protected String autor;
    protected int numPaginas;
    protected String descrip;
    protected String Genero;
    protected int anio_pub;
    protected String url;
    protected Tipo tipo;
    

    public Libros(String iSBN, String titulo, String editorial, String autor, int numPaginas, String descrip,
            String genero, int anio_pub, String url, Tipo tipo) {
        ISBN = iSBN;
        this.titulo = titulo;
        this.editorial = editorial;
        this.autor = autor;
        this.numPaginas = numPaginas;
        this.descrip = descrip;
        Genero = genero;
        this.anio_pub = anio_pub;
        this.url = url;
        this.tipo = tipo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public int getAnio_pub() {
        return anio_pub;
    }

    public void setAnio_pub(int anio_pub) {
        this.anio_pub = anio_pub;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

       public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        return "Libros [ISBN=" + ISBN + ", titulo=" + titulo + ", editorial=" + editorial + ", autor=" + autor
                + ", numPaginas=" + numPaginas + ", descrip=" + descrip + ", Genero=" + Genero + ", anio_pub="
                + anio_pub + ", tipo=" + tipo + "]";
    }
}