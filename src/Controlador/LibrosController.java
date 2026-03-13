package Controlador;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.View;

import Modelo.Libros;
import Modelo.LibrosDAO;
import Modelo.Tipo;
import Vista.LibrosView;

public class LibrosController {
    private LibrosView view;
    private LibrosDAO dao = new LibrosDAO();

    public LibrosController(LibrosView view) {
        this.view = view;

        initController();
        cargarTabla();
    }

    private void initController() {
        view.btnInsertar.addActionListener(e -> insertar());
        view.btnActualizar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    view, // Componente padre
                    "¿Estás seguro de que deseas actualizar este registro?", // Mensaje
                    "Confirmar Actualización", // Título
                    JOptionPane.YES_NO_OPTION // Tipo de botones
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                actualizar();
            }
        });
        view.btnEliminar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    view, // Componente padre
                    "¿Estás seguro de que deseas borrar este registro?", // Mensaje
                    "Confirmar Actualización", // Título
                    JOptionPane.YES_NO_OPTION // Tipo de botones
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                eliminar();
            }
        });
        view.btnLimpiar.addActionListener(e -> limpiar());

        view.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarFila();
            }
        });

        // Restringir campos a solo números
        AceptarNumeroAdapter soloNumeros = new AceptarNumeroAdapter();
        view.txtnumPag.addKeyListener(soloNumeros);
        view.txtanio_pub.addKeyListener(soloNumeros);
    }

    // ===============================
    // == METODO CRUD ==
    // ===============================
    
    private void insertar() {
        try {
            String isbn = view.txtISBN.getText();
            String titulo = view.txtTitulo.getText();
            String editorial = view.txtEditorial.getText();
            String autor = view.txtAutor.getText();
            int numPag = Integer.parseInt(view.txtnumPag.getText());
            String descripcion = view.txtDescripcion.getText();
            String genero = view.txtGenero.getText();
            int anio_pub = Integer.parseInt(view.txtanio_pub.getText());
            String url = view.txtURL.getText();
            Tipo tipo = Tipo.valueOf(view.cmbTipo.getSelectedItem().toString());
            String urlpredeterminada;
            
            // Condicion para que se ponga la imagen predeterminada
            if (url.isEmpty() || url=="Null") {
                urlpredeterminada="https://ih1.redbubble.net/image.4905811472.8675/st,extra_large,507x507-pad,600x600,f8f8f8.jpg";
                dao.insertar(new Libros(isbn, titulo, editorial, autor, numPag, descripcion, genero, anio_pub, urlpredeterminada, tipo));
            } else{
                dao.insertar(new Libros(isbn, titulo, editorial, autor, numPag, descripcion, genero, anio_pub, url, tipo));
            }
            
            cargarTabla();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error al insertar");
        }
    }

    private void actualizar() {

        int fila = view.tabla.getSelectedRow();
        if (fila == -1)
            return;
        try {
            String isbn = view.txtISBN.getText();
            String titulo = view.txtTitulo.getText();
            String editorial = view.txtEditorial.getText();
            String autor = view.txtAutor.getText();
            int numPag = Integer.parseInt(view.txtnumPag.getText());
            String descripcion = view.txtDescripcion.getText();
            String genero = view.txtGenero.getText();
            int anio_pub = Integer.parseInt(view.txtanio_pub.getText());
            String url = view.txtURL.getText();
            Tipo tipo = Tipo.valueOf(view.cmbTipo.getSelectedItem().toString());

            dao.actualizar(
                    new Libros(isbn, titulo, editorial, autor, numPag, descripcion, genero, anio_pub, url, tipo));

            cargarTabla();
            limpiar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Código Inválido");
        }
    }

    private void eliminar() {

        int fila = view.tabla.getSelectedRow();
        if (fila == -1)
            return;

        String ISBN = view.modeloTabla.getValueAt(fila, 0).toString();

        dao.eliminar(ISBN);

        cargarTabla();
        limpiar();
    }

    /**
     * Obtiene una versión escalada de la imagen desde la URL proporcionada.
     * Escala la imagen para que se ajuste exactamente al tamaño del contenedor.
     */
    private ImageIcon obtenerImagenEscalada() {
        String urlImagen = view.txtURL.getText();
        if (urlImagen == null || urlImagen.trim().isEmpty()) {
            return null;
        }

        try {
            // Obtenemos el tamaño actual del label, si es 0 usamos valores por defecto
            int ancho = view.labelImage.getWidth();
            int alto = view.labelImage.getHeight();

            if (ancho <= 0)
                ancho = 300;
            if (alto <= 0)
                alto = 180;

            ImageIcon iconoOriginal = new ImageIcon(new URL(urlImagen));

            // Verificamos que la imagen se haya cargado correctamente
            if (iconoOriginal.getIconWidth() > 0) {
                Image imgEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                return new ImageIcon(imgEscalada);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
        return null;
    }

    private void seleccionarFila() {
        int fila = view.tabla.getSelectedRow();
        if (fila == -1)
            return;

        // Llenar campos de texto
        view.txtISBN.setText(view.modeloTabla.getValueAt(fila, 0).toString());
        view.txtTitulo.setText(view.modeloTabla.getValueAt(fila, 1).toString());
        view.txtEditorial.setText(view.modeloTabla.getValueAt(fila, 2).toString());
        view.txtAutor.setText(view.modeloTabla.getValueAt(fila, 3).toString());
        view.txtnumPag.setText(view.modeloTabla.getValueAt(fila, 4).toString());
        view.txtDescripcion.setText(view.modeloTabla.getValueAt(fila, 5).toString());
        view.txtGenero.setText(view.modeloTabla.getValueAt(fila, 6).toString());
        view.txtanio_pub.setText(view.modeloTabla.getValueAt(fila, 7).toString());
        view.txtURL.setText(view.modeloTabla.getValueAt(fila, 8).toString());

        // Actualizar la seleccion del combo box
        view.cmbTipo.setSelectedItem(view.modeloTabla.getValueAt(fila, 9).toString());

        // Actualizar la imagen escalada al 100% del label
        view.labelImage.setIcon(obtenerImagenEscalada());
    }

    private void cargarTabla() {
        view.modeloTabla.setRowCount(0);

        List<Libros> lista = dao.listar();
        for (Libros c : lista) {
            view.modeloTabla.addRow(new Object[] {
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

    private void limpiar() {
        view.txtISBN.setText("");
        view.txtTitulo.setText("");
        view.txtEditorial.setText("");
        view.txtAutor.setText("");
        view.txtnumPag.setText("");
        view.txtDescripcion.setText("");
        view.txtGenero.setText("");
        view.txtanio_pub.setText("");
        view.txtURL.setText("");
        view.cmbTipo.setSelectedIndex(0);
        view.tabla.clearSelection();
    }
}