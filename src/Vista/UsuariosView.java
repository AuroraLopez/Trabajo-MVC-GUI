package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Vista para la gestión de usuarios.
 * Mantiene la misma estructura limpia y eficiente que la vista de libros.
 */
public class UsuariosView extends JPanel {

    // Campos
    public JTextField txtID = new JTextField();
    public JTextField txtNombre = new JTextField();
    public JTextField txtApellido1 = new JTextField();
    public JTextField txtApellido2 = new JTextField();
    public JTextField txtEdad = new JTextField();

    // Botones
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnLimpiar = new JButton("Limpiar");

    // Tabla y modelo
    public DefaultTableModel modeloTabla = new DefaultTableModel(
        new Object[]{"ID", "Nombre", "Apellido 1", "Apellido 2", "Edad"}, 0
    );
    public JTable tabla = new JTable(modeloTabla);

    public UsuariosView() {
        inicializarComponentes();
    }

    // Configuración de los componentes y diseño
    private void inicializarComponentes() {
        // Estructura principal con BorderLayout
        setLayout(new BorderLayout(5, 5));

        // Panel Izquierdo: Contiene la Tabla y el área de Acciones
        JPanel panelIzquierdo = new JPanel(new BorderLayout(5, 5));

        // Sección de la Tabla de Usuarios
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Gestión de Usuarios"));
        panelIzquierdo.add(scrollTabla, BorderLayout.CENTER);

        // Panel de Acciones Inferior (Botones)
        JPanel panelAcciones = new JPanel(new BorderLayout());
        panelAcciones.setPreferredSize(new Dimension(0, 180));
        panelAcciones.setBackground(new Color(255, 204, 255)); // Fondo rosado

        // Subpanel para organizar los botones en 2x2
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBotones.add(btnInsertar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnLimpiar);

        panelAcciones.add(panelBotones, BorderLayout.CENTER);
        
        panelIzquierdo.add(panelAcciones, BorderLayout.SOUTH);

        // Panel Derecho: Formulario de datos con Scroll
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(new Color(204, 255, 204)); // Fondo verde
        panelForm.setBorder(BorderFactory.createTitledBorder("Detalles del Usuario"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Añadimos los campos del usuario
        agregarCampo(panelForm, "ID:", txtID, gbc, 0);
        agregarCampo(panelForm, "Nombre:", txtNombre, gbc, 1);
        agregarCampo(panelForm, "Apellido 1:", txtApellido1, gbc, 2);
        agregarCampo(panelForm, "Apellido 2:", txtApellido2, gbc, 3);
        agregarCampo(panelForm, "Edad:", txtEdad, gbc, 4);

        // Scroll para el formulario
        JScrollPane scrollForm = new JScrollPane(panelForm);
        scrollForm.setPreferredSize(new Dimension(300, 0));
        scrollForm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Montaje final
        add(panelIzquierdo, BorderLayout.CENTER);
        add(scrollForm, BorderLayout.EAST);
    }

    // Método sencillo para agregar etiquetas y campos alineados
    private void agregarCampo(JPanel p, String etiqueta, JTextField campo, GridBagConstraints gbc, int fila) {
        gbc.gridy = fila;
        
        // Etiqueta
        gbc.gridx = 0;
        gbc.weightx = 0;
        p.add(new JLabel(etiqueta), gbc);
        
        // Campo de entrada
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        campo.setPreferredSize(new Dimension(150, 25));
        p.add(campo, gbc);
    }
}
