package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * Vista para la gestión de participación en préstamos.
 * Mantiene la coherencia visual con el resto de las vistas del sistema.
 */
public class ParticipaPrestamosView extends JPanel {

    // Campos
    public JTextField txtISBN = new JTextField();
    public JTextField txtID_US = new JTextField();
    public JFormattedTextField txtFECHA_PRES = crearCampoFecha();
    public JFormattedTextField txtFECHA_DEV = crearCampoFecha();

    // Botones
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnLimpiar = new JButton("Limpiar");

    // Tabla y modelo
    public DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[] { "ISBN", "ID Usuario", "Fecha Préstamo", "Fecha Devolución" }, 0);
    public JTable tabla = new JTable(modeloTabla);

    public ParticipaPrestamosView() {
        inicializarComponentes();
    }

    // Configuración de los componentes y diseño
    private void inicializarComponentes() {
        // Estructura principal con BorderLayout
        setLayout(new BorderLayout(5, 5));

        // Panel Izquierdo: Tabla y área de Acciones
        JPanel panelIzquierdo = new JPanel(new BorderLayout(5, 5));

        // Sección de la Tabla de Préstamos
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Gestión de Préstamos"));
        panelIzquierdo.add(scrollTabla, BorderLayout.CENTER);

        // Panel de Acciones Inferior (Botones)
        JPanel panelAcciones = new JPanel(new BorderLayout());
        panelAcciones.setPreferredSize(new Dimension(0, 180));
        panelAcciones.setBackground(new Color(255, 204, 255)); // Fondo rosado

        // Subpanel para los botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBotones.add(btnInsertar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnLimpiar);

        panelAcciones.add(panelBotones, BorderLayout.CENTER);

        panelIzquierdo.add(panelAcciones, BorderLayout.SOUTH);

        // Panel Derecho: Formulario con Scroll
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(new Color(204, 255, 204)); // Fondo verde
        panelForm.setBorder(BorderFactory.createTitledBorder("Detalles del Préstamo"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Añadimos los campos
        agregarCampo(panelForm, "ISBN:", txtISBN, gbc, 0);
        agregarCampo(panelForm, "ID Usuario:", txtID_US, gbc, 1);
        agregarCampo(panelForm, "Fecha Préstamo:", txtFECHA_PRES, gbc, 2);
        agregarCampo(panelForm, "Fecha Devolución:", txtFECHA_DEV, gbc, 3);

        // Scroll para el formulario
        JScrollPane scrollForm = new JScrollPane(panelForm);
        scrollForm.setPreferredSize(new Dimension(300, 0));
        scrollForm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Montaje final
        add(panelIzquierdo, BorderLayout.CENTER);
        add(scrollForm, BorderLayout.EAST);
    }

    // Método auxiliar para agregar etiquetas y componentes alineados
    private void agregarCampo(JPanel p, String etiqueta, JComponent componente, GridBagConstraints gbc, int fila) {
        gbc.gridy = fila;

        // Etiqueta
        gbc.gridx = 0;
        gbc.weightx = 0;
        p.add(new JLabel(etiqueta), gbc);

        // Componente
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        componente.setPreferredSize(new Dimension(150, 25));
        p.add(componente, gbc);
    }

    // Crea un campo de texto con formato de fecha dd/mm/aaaa
    private JFormattedTextField crearCampoFecha() {
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            JFormattedTextField campo = new JFormattedTextField(mascara);
            return campo;
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }
}
