
package Modelo;

public class Ebook extends Libros{

    //Atributos
    protected double tamanio;
    protected String formato;
    
    //Contructores

    public double getTamanio() {
        return tamanio;
    }

    //Constructores

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }


    public Ebook(String iSBN, String titulo, String editorial, String autor, int numPaginas, String descrip,
            String genero, int anio_pub, String url, Tipo tipo, double tamanio, String formato) {
        super(iSBN, titulo, editorial, autor, numPaginas, descrip, genero, anio_pub, url, tipo);
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
        return "Ebook [ISBN=" + ISBN + ", tamanio=" + tamanio + ", formato=" + formato + "]";
    }
}
