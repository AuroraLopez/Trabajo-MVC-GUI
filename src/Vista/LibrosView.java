package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Vista para la gestión de libros.
 * Esta clase es una versión optimizada y limpia del diseño realizado en
 * NetBeans.
 * Mantiene la disposición original pero con código más eficiente y legible.
 */
public class LibrosView extends JPanel {

    // Campos
    public JTextField txtISBN = new JTextField();
    public JTextField txtTitulo = new JTextField();
    public JTextField txtEditorial = new JTextField();
    public JTextField txtAutor = new JTextField();
    public JTextField txtnumPag = new JTextField();
    public JTextField txtDescripcion = new JTextField();
    public JTextField txtGenero = new JTextField();
    public JTextField txtanio_pub = new JTextField();
    public JTextField txtURL = new JTextField();
    public JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"EBOOK", "IMPRESO"});

    // --- BOTONES ---
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnLimpiar = new JButton("Limpiar");

    // --- ETIQUETA PARA IMAGEN ---
    public JLabel labelImage = new JLabel();

    // --- TABLA Y MODELO ---
    public DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[] { "ISBN", "Título", "Editorial", "Autor", "Descripción", "Páginas", "Género", "Año", "URL",
                    "Tipo" },
            0);
    public JTable tabla = new JTable(modeloTabla);

    public LibrosView() {
        inicializarComponentes();
    }

    /**
     * Configura el diseño de la vista de forma sencilla y eficiente.
     */
    private void inicializarComponentes() {
        // Usamos BorderLayout como base para organizar las áreas principales
        setLayout(new BorderLayout(5, 5));

        // 1. PANEL IZQUIERDO: Contiene la Tabla (Arriba) y el Panel de Acciones (Abajo)
        JPanel panelIzquierdo = new JPanel(new BorderLayout(5, 5));

        // Sección de la Tabla
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Gestión de Libros"));
        panelIzquierdo.add(scrollTabla, BorderLayout.CENTER);

        // Panel Inferior (Botones e Imagen) - Equivalente a jPanelInferior en NetBeans
        JPanel panelAcciones = new JPanel();
        panelAcciones.setLayout(new BoxLayout(panelAcciones, BoxLayout.X_AXIS));
        panelAcciones.setPreferredSize(new Dimension(0, 180));
        panelAcciones.setBackground(new Color(255, 204, 255)); // Color rosado original

        // Subpanel para los botones (Cuadrícula 2x2) - Equivalente a jPanel7 en
        // NetBeans
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBotones.setOpaque(false); // Transparente para respetar el fondo rosado
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBotones.add(btnInsertar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnLimpiar);

        // Subpanel para la imagen (Derecha) - Equivalente a jPanel2 en NetBeans
        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setBackground(new Color(153, 0, 153)); // Color morado original
        panelImagen.setPreferredSize(new Dimension(300, 0));
        labelImage.setHorizontalAlignment(SwingConstants.CENTER);
        panelImagen.add(labelImage, BorderLayout.CENTER);

        panelAcciones.add(panelBotones);
        panelAcciones.add(panelImagen);

        panelIzquierdo.add(panelAcciones, BorderLayout.SOUTH);

        // 2. PANEL DERECHO: Formulario de entrada de datos (Vertical y con Scroll)
        // Equivalente a jScrollPane1 y jPanel8 de NetBeans
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(new Color(204, 255, 204)); // Color verde original
        panelForm.setBorder(BorderFactory.createTitledBorder("Detalles del Libro"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Añadimos los campos usando un método auxiliar para mantener el código limpio
        agregarEntrada(panelForm, "ISBN:", txtISBN, gbc, 0);
        agregarEntrada(panelForm, "Título:", txtTitulo, gbc, 1);
        agregarEntrada(panelForm, "Editorial:", txtEditorial, gbc, 2);
        agregarEntrada(panelForm, "Autor:", txtAutor, gbc, 3);
        agregarEntrada(panelForm, "Páginas:", txtnumPag, gbc, 4);
        agregarEntrada(panelForm, "Descripción:", txtDescripcion, gbc, 5);
        agregarEntrada(panelForm, "Género:", txtGenero, gbc, 6);
        agregarEntrada(panelForm, "Año Pub.:", txtanio_pub, gbc, 7);
        agregarEntrada(panelForm, "URL Imagen:", txtURL, gbc, 8);
        
        // El campo tipo ahora es un selector
        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.weightx = 0;
        panelForm.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        cmbTipo.setPreferredSize(new Dimension(150, 25));
        panelForm.add(cmbTipo, gbc);

        // ScrollPane para el formulario (en caso de que la ventana sea pequeña)
        JScrollPane scrollForm = new JScrollPane(panelForm);
        scrollForm.setPreferredSize(new Dimension(300, 0));
        scrollForm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // --- Ensamblaje final en el panel principal ---
        add(panelIzquierdo, BorderLayout.CENTER);
        add(scrollForm, BorderLayout.EAST);
    }

    /**
     * Método auxiliar para agregar etiquetas y campos alineados al formulario.
     */
    private void agregarEntrada(JPanel p, String etiqueta, JTextField campo, GridBagConstraints gbc, int fila) {
        gbc.gridy = fila;

        // Etiqueta (Columna 0)
        gbc.gridx = 0;
        gbc.weightx = 0;
        p.add(new JLabel(etiqueta), gbc);

        // Campo de texto (Columna 1)
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        campo.setPreferredSize(new Dimension(150, 25));
        p.add(campo, gbc);
    }
}