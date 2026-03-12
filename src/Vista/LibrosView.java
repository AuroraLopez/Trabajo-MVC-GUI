package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibrosView extends JPanel{
    // Campos
    public JTextField txtISBN = new JTextField(10);
    public JTextField txtTitulo = new JTextField(10);
    public JTextField txtEditorial = new JTextField(10);
    public JTextField txtAutor = new JTextField(10);
    public JTextField txtnumPag = new JTextField(5);
    public JTextField txtDescripcion = new JTextField(20);
    public JTextField txtGenero = new JTextField(10);
    public JTextField txtanio_pub = new JTextField(5);
    public JTextField txtURL = new JTextField(10);
    public JTextField txttipo = new JTextField(10);

    //Botones
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnLimpiar = new JButton("Limpiar");

    // Label
    public JLabel labelImage = new JLabel();

    // Tabla
    public DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"ISBN", "titulo", "editorial", "autor","descripcion", "numPag", "genero", "anio_pub", "URL", "tipo"}, 0);

    public JTable tabla = new JTable(modeloTabla);

    public LibrosView(){

        setLayout(new BorderLayout());

        // ===== PANEL SUPERIOR =======
        JPanel panelFormulario = new JPanel();
        JPanel butons = new JPanel();
        panelFormulario.setBorder(
            BorderFactory.createTitledBorder("Datos Libros"));
        
            panelFormulario.add(new JLabel("ISBN: "));
            panelFormulario.add(txtISBN);

            panelFormulario.add(new JLabel("Título: "));
            panelFormulario.add(txtTitulo);

            panelFormulario.add(new JLabel("Editorial: "));
            panelFormulario.add(txtEditorial);

            panelFormulario.add(new JLabel("Autor: "));
            panelFormulario.add(txtAutor);

            panelFormulario.add(new JLabel("Número de páginas: "));
            panelFormulario.add(txtnumPag);

            panelFormulario.add(new JLabel("Descripción: "));
            panelFormulario.add(txtDescripcion);

            panelFormulario.add(new JLabel("Género: "));
            panelFormulario.add(txtGenero);

            panelFormulario.add(new JLabel("Año de publicación: "));
            panelFormulario.add(txtanio_pub);

            panelFormulario.add(new JLabel("URL: "));
            panelFormulario.add(txtURL);

            panelFormulario.add(new JLabel("Tipo: "));
            panelFormulario.add(txttipo);

            butons.add(btnInsertar);
            butons.add(btnActualizar);
            butons.add(btnEliminar);
            butons.add(btnLimpiar);

            butons.add(labelImage);

        // ===== PANEL INFERIOR =======
        JScrollPane scroll = new JScrollPane(tabla);
        JScrollPane scroll2 = new JScrollPane(panelFormulario);
        panelFormulario.setPreferredSize(new Dimension(120, panelFormulario.getPreferredSize().height));
        butons.setPreferredSize(new Dimension(150,150));

        add(scroll2, BorderLayout.WEST);
        
        add(butons, BorderLayout.EAST);

        scroll.setBorder(
            BorderFactory.createTitledBorder("Listado Libros"));
            add (scroll, BorderLayout.CENTER);
    }
}