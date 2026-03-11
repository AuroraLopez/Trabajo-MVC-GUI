package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ParticipaPrestamosView extends JPanel{
    // Campos
    public JTextField txtISBN = new JTextField(10);
    public JTextField txtID_US = new JTextField(10);
    public JTextField txtFECHA_PRES = new JTextField(10);
    public JTextField txtFECHA_DEV = new JTextField(10);

    //Botones
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnLimpiar = new JButton("Limpiar");

    // Tabla
    public DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"ISBN", "ID_US", "FECHA_PRES", "FECHA_DEV"}, 0);

    public JTable tabla = new JTable(modeloTabla);

    public ParticipaPrestamosView(){

        setLayout(new BorderLayout());

        // ===== PANEL SUPERIOR =======
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(
            BorderFactory.createTitledBorder("Datos Libros"));
        
            panelFormulario.add(new JLabel("ISBN: "));
            panelFormulario.add(txtISBN);

            panelFormulario.add(new JLabel("ID_Usuario: "));
            panelFormulario.add(txtID_US);

            panelFormulario.add(new JLabel("Fecha Prestamo: "));
            panelFormulario.add(txtFECHA_PRES);

            panelFormulario.add(new JLabel("Fecha Devolución: "));
            panelFormulario.add(txtFECHA_DEV);            

            //Botones
            panelFormulario.add(btnInsertar);
            panelFormulario.add(btnActualizar);
            panelFormulario.add(btnEliminar);
            panelFormulario.add(btnLimpiar);

        // ===== PANEL INFERIOR =======
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(
            BorderFactory.createTitledBorder("Listado_Prestamos"));
            panelFormulario.setPreferredSize(new Dimension(0, 100));
            add(panelFormulario, BorderLayout.NORTH);
            add (scroll, BorderLayout.CENTER);
    }
}
