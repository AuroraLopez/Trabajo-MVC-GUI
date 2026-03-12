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
import Modelo.Tipo;
import Modelo.Usuarios;
import Modelo.UsuariosDAO;
import Vista.LibrosView;
import Vista.UsuariosView;

public class UsuariosController {
    private UsuariosView view;
    private UsuariosDAO dao = new UsuariosDAO();

    public UsuariosController(UsuariosView view){
        this.view= view;

        initController();
        cargarTabla();
    }

    private void initController(){
        view.btnInsertar.addActionListener(e -> insertar());
        view.btnActualizar.addActionListener(e -> 
            {int respuesta = JOptionPane.showConfirmDialog(
            view, // Componente padre
            "¿Estás seguro de que deseas actualizar este registro?", // Mensaje
            "Confirmar Actualización", // Título
            JOptionPane.YES_NO_OPTION // Tipo de botones
    );

    if (respuesta == JOptionPane.YES_OPTION) {
        actualizar();
    }
});
        view.btnEliminar.addActionListener(e -> 
            {int respuesta = JOptionPane.showConfirmDialog(
            view, // Componente padre
            "¿Estás seguro de que deseas borrar este registro?", // Mensaje
            "Confirmar Actualización", // Título
            JOptionPane.YES_NO_OPTION // Tipo de botones
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
            int ID= Integer.parseInt(view.txtID.getText());
            String nombre = view.txtNombre.getText();
            String ape1 = view.txtApellido1.getText();
            String ape2 = view.txtApellido2.getText();
            int edad = Integer.parseInt(view.txtEdad.getText());

            dao.insertar(new Usuarios(ID, nombre, ape1, ape2, edad));

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
            int id= Integer.parseInt( view.txtID.getText());
            String nombre = view.txtNombre.getText();
            String apellido1 = view.txtApellido1.getText();
            String apellido2 = view.txtApellido2.getText();
            int edad = Integer.parseInt(view.txtEdad.getText());

            dao.actualizar(new Usuarios(id, nombre, apellido1, apellido2, edad));

            cargarTabla();
            limpiar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Código Inválido");
        }
    }

    private void eliminar(){
        
        int fila = view.tabla.getSelectedRow();
        if( fila == -1) return;
    
            int ID= Integer.parseInt(view.modeloTabla.getValueAt(fila, 0).toString());

            dao.eliminar(ID);

            cargarTabla();
            limpiar();
    }

    private void seleccionarFila(){
        
        int fila = view.tabla.getSelectedRow();
        if( fila == -1) return;
            view.txtID.setText(view.modeloTabla.getValueAt(fila, 0).toString());
            view.txtNombre.setText(view.modeloTabla.getValueAt(fila, 1).toString());
            view.txtApellido1.setText(view.modeloTabla.getValueAt(fila, 2).toString());
            view.txtApellido2.setText(view.modeloTabla.getValueAt(fila, 3).toString());
            view.txtEdad.setText(view.modeloTabla.getValueAt(fila, 4).toString());    
    }

    private void cargarTabla(){
        view.modeloTabla.setRowCount(0);

        List<Usuarios> lista= dao.listar();
        for(Usuarios c: lista){
            view.modeloTabla.addRow(new Object[]{
                c.getId(),
                c.getNombre(),
                c.getApe1(),
                c.getApe2(),
                c.getEdad()
            });
        }
    }

    private void limpiar(){
        view.txtID.setText("");
        view.txtNombre.setText("");
        view.txtApellido1.setText("");
        view.txtApellido2.setText("");
        view.txtEdad.setText("");
        view.tabla.clearSelection();
    }
}
