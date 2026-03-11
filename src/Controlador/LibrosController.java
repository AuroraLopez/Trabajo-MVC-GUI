package Controlador;

import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import Modelo.Libros;
import Modelo.LibrosDAO;
import Modelo.Tipo;
import Vista.LibrosView;

public class LibrosController {
    private LibrosView view;
    private LibrosDAO dao = new LibrosDAO();

    public LibrosController(LibrosView view){
        this.view= view;

        initController();
        cargarTabla();
    }

    private void initController(){
        view.btnInsertar.addActionListener(e -> insertar());
        view.btnActualizar.addActionListener(e -> actualizar());
        view.btnEliminar.addActionListener(e -> eliminar());
        view.btnLimpiar.addActionListener(e -> limpiar());

        view.tabla.getSelectionModel().addListSelectionListener(e ->{
            if (!e.getValueIsAdjusting()) {
                seleccionarFila();
            }
        });
    }
    
    // ===============================
    // ==        METODO CRUD        ==
    // ===============================
    private void insertar(){
        try {
            String isbn= view.txtISBN.getText();
            String titulo = view.txtTitulo.getText();
            String editorial = view.txtEditorial.getText();
            String autor = view.txtAutor.getText();
            int numPag = Integer.parseInt(view.txtnumPag.getText());
            String descripcion = view.txtDescripcion.getText();
            String genero = view.txtGenero.getText();
            Date anio_pub = Date.valueOf(view.txtanio_pub.getText());
            String url = view.txtURL.getText();
            Tipo tipo = Tipo.valueOf(view.txttipo.getText().toUpperCase());

            dao.insertar(new Libros(isbn, titulo, editorial, autor, numPag, descripcion, genero, anio_pub, url, tipo));

            cargarTabla();
            limpiar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Código Inválido");
        }
    }

    private void actualizar(){
        
        int fila = view.tabla.getSelectedRow();
        if( fila == -1) return;
        try {
            String isbn= view.txtISBN.getText();
            String titulo = view.txtTitulo.getText();
            String editorial = view.txtEditorial.getText();
            String autor = view.txtAutor.getText();
            int numPag = Integer.parseInt(view.txtnumPag.getText());
            String descripcion = view.txtDescripcion.getText();
            String genero = view.txtGenero.getText();
            Date anio_pub = Date.valueOf(view.txtanio_pub.getText());
            String url = view.txtURL.getText();
            Tipo tipo = Tipo.valueOf(view.txttipo.getText());


            dao.actualizar(new Libros(isbn, titulo, editorial, autor, numPag, descripcion, genero, anio_pub, url, tipo));

            cargarTabla();
            limpiar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Código Inválido");
        }
    }

    private void eliminar(){
        
        int fila = view.tabla.getSelectedRow();
        if( fila == -1) return;
    
            String ISBN= view.modeloTabla.getValueAt(fila, 0).toString();

            dao.eliminar(ISBN);

            cargarTabla();
            limpiar();
    }

    private void seleccionarFila(){
        
        int fila = view.tabla.getSelectedRow();
        if( fila == -1) return;
            view.txtISBN.setText(view.modeloTabla.getValueAt(fila, 0).toString());
            view.txtTitulo.setText(view.modeloTabla.getValueAt(fila, 1).toString());
            view.txtEditorial.setText(view.modeloTabla.getValueAt(fila, 2).toString());
            view.txtAutor.setText(view.modeloTabla.getValueAt(fila, 3).toString());
            view.txtnumPag.setText(view.modeloTabla.getValueAt(fila, 4).toString());
            view.txtDescripcion.setText(view.modeloTabla.getValueAt(fila, 5).toString());
            view.txtGenero.setText(view.modeloTabla.getValueAt(fila, 6).toString());
            view.txtanio_pub.setText(view.modeloTabla.getValueAt(fila, 7).toString());
            view.txtURL.setText(view.modeloTabla.getValueAt(fila, 8).toString());
            view.txttipo.setText(view.modeloTabla.getValueAt(fila, 9).toString());
      
    }
    private void cargarTabla(){
        view.modeloTabla.setRowCount(0);

        List<Libros> lista= dao.listar();
        for(Libros c: lista){
            view.modeloTabla.addRow(new Object[]{
                c.getISBN(),
                c.getTitulo(),
                c.getEditorial(),
                c.getAutor(),
                c.getNumPaginas(),
                c.getDescrip(),
                c.getGenero(),
                c.getAnio_pub(),
                c.getUrl(),
                c.getTipo()
            });
        }
    }

    private void limpiar(){
        view.txtISBN.setText("XYZ");
        view.txtTitulo.setText("XYZ");
        view.txtEditorial.setText("XYZ");
        view.txtAutor.setText("XYZ");
        view.txtnumPag.setText("12");
        view.txtDescripcion.setText("XYZ");
        view.txtGenero.setText("XYZ");
        view.txtanio_pub.setText("12");
        view.txtURL.setText("XYZ");
        view.txttipo.setText("XYZ");
        view.tabla.clearSelection();
    }
}
