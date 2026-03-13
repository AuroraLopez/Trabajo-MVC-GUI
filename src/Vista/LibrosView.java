package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Vista para la gestión de libros.
 * Esta clase es una versión optimizada y limpia del diseño que hice en NetBeans.
 * Mantiene la disposición original, pero más eficiente y legible.
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
    //Reemplazo del JtextField en la version previa
    public JComboBox<String> cmbTipo = new JComboBox<>(new String[] { "EBOOK", "IMPRESO" });

    // BOTONES
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnLimpiar = new JButton("Limpiar");

    // Area para la Imagen
    public JLabel labelImage = new JLabel();

    // TABLA Y MODELO
    public DefaultTableModel modeloTabla = new DefaultTableModel(
            // Nombre_Columnas a forma de objeto
            new Object[] { 
                "ISBN", "Título", "Editorial", "Autor", "Descripción", "Páginas", "Género", "Año", "URL","Tipo"
            },
            // El número inicial de filas= 0
            0);
            
    // Se crea una JTable
    // Esta, muestra las columnas definidas y las filas entrantes        
    public JTable tabla = new JTable(modeloTabla);

    // Constructor de la vista.
    public LibrosView() {
        inicializarComponentes();
    }

    /**
     * Incializacion y configuracion de los componentes
     */
    private void inicializarComponentes() {
        // BorderLayout de base para organizar las áreas principales
        setLayout(new BorderLayout(5, 5));

        // ===============================
        // PANEL IZQUIERDO
        // ===============================

        // Contiene la Tabla (Arriba) y el Panel de Acciones (Abajo)
        JPanel panelIzquierdo = new JPanel(
            new BorderLayout(5, 5) //Separacion entre componentes hijos
        );

        // Sección de la Tabla
        JScrollPane scrollTabla = new JScrollPane(tabla);
        //Declararar Sub-titulo
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Gestión de Libros"));
        //Implementacion_Scroller
        panelIzquierdo.add(scrollTabla, BorderLayout.CENTER);

        // ===============================
        // PANEL INFERIOR 
        // ===============================

        //Panel hijo, que a su vez, tendra 2 hijos
        JPanel panelAcciones = new JPanel();

        //Declaracion_Layout
        panelAcciones.setLayout(
            //Alinea los paneles bajo el ejeX (Horizontalemnte) 
            // en funcion del tmñ de sus propios componentes
            new BoxLayout(panelAcciones, BoxLayout.X_AXIS) 
        );

        //Dimencion al ejecutat
        panelAcciones.setPreferredSize(
            new Dimension(0, 180)
        );

        //Coloreo_Fondo
        panelAcciones.setBackground(
            new Color(255, 204, 255)
        ); 

        // Subpanel para los botones (Cuadrícula 2x2) 
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 10, 10));

        //panelAcciones
        panelBotones.setOpaque(false); // Transparente para que sea visible el trasfondo rosado
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

        // Usando el la metAux, se agrega los campos
        addEntrada(panelForm, "ISBN:", txtISBN, gbc, 0);
        addEntrada(panelForm, "Título:", txtTitulo, gbc, 1);
        addEntrada(panelForm, "Editorial:", txtEditorial, gbc, 2);
        addEntrada(panelForm, "Autor:", txtAutor, gbc, 3);
        addEntrada(panelForm, "Páginas:", txtnumPag, gbc, 4);
        addEntrada(panelForm, "Descripción:", txtDescripcion, gbc, 5);
        addEntrada(panelForm, "Género:", txtGenero, gbc, 6);
        addEntrada(panelForm, "Año Pub.:", txtanio_pub, gbc, 7);
        addEntrada(panelForm, "URL Imagen:", txtURL, gbc, 8);

        // El campo tipo ahora es un selector
        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.weightx = 0;
        panelForm.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        cmbTipo.setPreferredSize(new Dimension(150, 25));
        panelForm.add(cmbTipo, gbc);

        // ScrollPane para el formulario
        JScrollPane scrollForm = new JScrollPane(panelForm);
        scrollForm.setPreferredSize(new Dimension(300, 0));
        scrollForm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Ensamblaje final de las regiones en el panel principal
        add(panelIzquierdo, BorderLayout.CENTER);
        add(scrollForm, BorderLayout.EAST);
    }

    /**
     * Método auxiliar para agregar etiquetas y campos alineados al formulario.
     */
    private void addEntrada(JPanel p, String etiqueta, JTextField campo, GridBagConstraints gbc, int fila) {
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