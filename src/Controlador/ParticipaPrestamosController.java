package Controlador;

import java.util.List;

import javax.swing.JOptionPane;

import Modelo.ParticipaPrestamo;
import Modelo.ParticipaPrestamoDAO;
import java.sql.Date;
import java.text.SimpleDateFormat;
import Vista.ParticipaPrestamosView;

public class ParticipaPrestamosController {
    private ParticipaPrestamosView view;
    private ParticipaPrestamoDAO dao = new ParticipaPrestamoDAO();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

        view.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarFila();
            }
        });

        // Restringir campos a solo números
        view.txtID_US.addKeyListener(new AceptarNumeroAdapter());
    }
    
    // ===============================
    // ==        METODO CRUD        ==
    // ===============================
    private void insertar() {
        try {
            String libro = view.txtISBN.getText();
            int id_usuario = Integer.parseInt(view.txtID_US.getText());

            // Parseamos las fechas desde el formato dd/MM/yyyy
            java.util.Date fechaP = sdf.parse(view.txtFECHA_PRES.getText());
            java.util.Date fechaD = sdf.parse(view.txtFECHA_DEV.getText());
            
            Date fecha_prestamo = new Date(fechaP.getTime());
            Date fecha_devolucion = new Date(fechaD.getTime());

            dao.insertar(new ParticipaPrestamo(libro, id_usuario, fecha_prestamo, fecha_devolucion));

            cargarTabla();
            limpiar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Código o ID Inválido");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Formato de fecha inválido (use dd/mm/aaaa)");
        }
    }

    private void actualizar() {
        int fila = view.tabla.getSelectedRow();
        if (fila == -1) return;
        try {
            String libro = view.txtISBN.getText();
            int id_usuario = Integer.parseInt(view.txtID_US.getText());

            // Parseamos las fechas desde el formato dd/MM/yyyy
            java.util.Date fechaP = sdf.parse(view.txtFECHA_PRES.getText());
            java.util.Date fechaD = sdf.parse(view.txtFECHA_DEV.getText());
            
            Date fecha_prestamo = new Date(fechaP.getTime());
            Date fecha_devolucion = new Date(fechaD.getTime());

            dao.actualizar(new ParticipaPrestamo(libro, id_usuario, fecha_prestamo, fecha_devolucion));

            cargarTabla();
            limpiar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Código o ID Inválido");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Formato de fecha inválido (use dd/mm/aaaa)");
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

    private void seleccionarFila() {
        int fila = view.tabla.getSelectedRow();
        if (fila == -1) return;
        view.txtISBN.setText(view.modeloTabla.getValueAt(fila, 0).toString());
        view.txtID_US.setText(view.modeloTabla.getValueAt(fila, 1).toString());

        // Al seleccionar de la tabla, formateamos la fecha de SQL a dd/MM/yyyy
        try {
            Object valPres = view.modeloTabla.getValueAt(fila, 2);
            Object valDev = view.modeloTabla.getValueAt(fila, 3);
            
            if (valPres != null) {
                view.txtFECHA_PRES.setText(sdf.format(valPres));
            }
            if (valDev != null) {
                view.txtFECHA_DEV.setText(sdf.format(valDev));
            }
        } catch (Exception e) {
            // Si falla el formateo, intentamos poner el texto tal cual
            view.txtFECHA_PRES.setText(view.modeloTabla.getValueAt(fila, 2).toString());
            view.txtFECHA_DEV.setText(view.modeloTabla.getValueAt(fila, 3).toString());
        }
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
