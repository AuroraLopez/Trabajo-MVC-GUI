package Controlador;

import java.util.List;

import javax.swing.JOptionPane;

import Modelo.ParticipaPrestamo;
import Modelo.ParticipaPrestamoDAO;
import java.sql.Date;
import Vista.ParticipaPrestamosView;

public class ParticipaPrestamosController {
    private ParticipaPrestamosView view;
    private ParticipaPrestamoDAO dao = new ParticipaPrestamoDAO();

    public ParticipaPrestamosController(ParticipaPrestamosView view){
        this.view= view;

        initController();
        cargarTabla();
    }

    private void initController(){
        view.btnInsertar.addActionListener(e -> insertar());
        view.btnActualizar.addActionListener(e -> 
            {int respuesta = JOptionPane.showConfirmDialog(
            view, // Componente padre
            "¿Estás seguro que quieres actualizar este registro?", // Mensaje
            "Confirmar Actualización", 
            //Botones
            JOptionPane.YES_NO_OPTION 
    );

    if (respuesta == JOptionPane.YES_OPTION) {
        actualizar();
    }
});
        view.btnEliminar.addActionListener(e -> 
            {int respuesta = JOptionPane.showConfirmDialog(
            view, // Componente padre
            "¿Estás seguro de que deseas borrarlo?", 
            "Confirmar Actualización", // Título
            //Botones
            JOptionPane.YES_NO_OPTION 
    );

    if (respuesta == JOptionPane.YES_OPTION) {
        eliminar();
    }});
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
            String libro = view.txtISBN.getText();
            int id_usuario = Integer.parseInt(view.txtID_US.getText());
            //Usar DateValueOf
            Date fecha_prestamo = Date.valueOf(view.txtFECHA_PRES.getText());
            Date fecha_devolucion = Date.valueOf(view.txtFECHA_DEV.getText());
            
            dao.insertar(new ParticipaPrestamo(libro, id_usuario, fecha_prestamo, fecha_devolucion));

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
            String libro = view.txtISBN.getText();
            int id_usuario = Integer.parseInt(view.txtID_US.getText());
            //Usar DateValueOf
            Date fecha_prestamo = Date.valueOf(view.txtFECHA_PRES.getText());
            Date fecha_devolucion = Date.valueOf(view.txtFECHA_DEV.getText());

            dao.actualizar(new ParticipaPrestamo(libro, id_usuario, fecha_prestamo, fecha_devolucion));

            cargarTabla();
            limpiar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Código Inválido");
        }
    }

    private void eliminar(){
        
        int fila = view.tabla.getSelectedRow();
        if( fila == -1) return;
    
            String libro= view.modeloTabla.getValueAt(fila, 0).toString();
            int id_usuario= Integer.parseInt(view.modeloTabla.getValueAt(fila, 0).toString());

            dao.eliminar(libro, id_usuario);

            cargarTabla();
            limpiar();
    }

    private void seleccionarFila(){
        
        int fila = view.tabla.getSelectedRow();
        if( fila == -1) return;
        view.txtISBN.setText(view.modeloTabla.getValueAt(fila, 0).toString());
        view.txtID_US.setText(view.modeloTabla.getValueAt(fila, 1).toString());
        view.txtFECHA_PRES.setText(view.modeloTabla.getValueAt(fila, 2).toString());
        view.txtFECHA_DEV.setText(view.modeloTabla.getValueAt(fila, 3   ).toString());
    }


    private void cargarTabla(){
        view.modeloTabla.setRowCount(0);

        List<ParticipaPrestamo> lista= dao.listar();
        for(ParticipaPrestamo c: lista){
            view.modeloTabla.addRow(new Object[]{
                c.getLibro(),
                c.getFecha_devolucion(),
                c.getFecha_prestamo(),
                c.getFecha_devolucion(),
            });
        }
    }

    private void limpiar(){
        view.txtISBN.setText("");
        view.txtID_US.setText("");
        view.txtFECHA_PRES.setText("");
        view.txtFECHA_DEV.setText("");
    }
}
