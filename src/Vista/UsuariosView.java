package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UsuariosView extends JPanel{
    // Campos
    public JTextField txtID = new JTextField(5);
    public JTextField txtNombre = new JTextField(10);
    public JTextField txtApellido1 = new JTextField(10);
    public JTextField txtApellido2 = new JTextField(10);
    public JTextField txtEdad = new JTextField(5);

    //Botones
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnLimpiar = new JButton("Limpiar");

    // Tabla
    public DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellido1", "Apellido2","Edad"}, 0);

    public JTable tabla = new JTable(modeloTabla);

    public UsuariosView(){

        setLayout(new BorderLayout());

        // ===== PANEL SUPERIOR =======
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(
            BorderFactory.createTitledBorder("Datos Usuario"));
        
            panelFormulario.add(new JLabel("ID: "));
            panelFormulario.add(txtID);

            panelFormulario.add(new JLabel("Nombre: "));
            panelFormulario.add(txtNombre);

            panelFormulario.add(new JLabel("Apellido 1: "));
            panelFormulario.add(txtApellido1);

            panelFormulario.add(new JLabel("Apellido 2: "));
            panelFormulario.add(txtApellido2);

            panelFormulario.add(new JLabel("Edad: "));
            panelFormulario.add(txtEdad);

            panelFormulario.add(btnInsertar);
            panelFormulario.add(btnActualizar);
            panelFormulario.add(btnEliminar);
            panelFormulario.add(btnLimpiar);

        // ===== PANEL INFERIOR =======
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(
            BorderFactory.createTitledBorder("Listado Usuarios"));
            panelFormulario.setPreferredSize(new Dimension(0, 110));
            add(panelFormulario, BorderLayout.NORTH);
            add (scroll, BorderLayout.CENTER);
    }
}
