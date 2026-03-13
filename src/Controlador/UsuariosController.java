package Controlador;

import java.util.List;
import javax.swing.JOptionPane;
import Modelo.Usuarios;
import Modelo.UsuariosDAO;
import Vista.UsuariosView;

public class UsuariosController {

    private UsuariosView view;
    private UsuariosDAO dao = new UsuariosDAO();

    //Constructor
    public UsuariosController(UsuariosView view) {
        this.view = view;

        initController();
        cargarTabla();
    }

    //Inicializador_Componentes
    private void initController() {

        //Boton Insertar
        view.btnInsertar.addActionListener(e -> insertar());

        //Boton Actulizar
        view.btnActualizar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    view, // Componente padre
                    "¿Estás seguro de que deseas actualizar este registro?", // Mensaje
                    "Confirmar Actualización", // Título
                    JOptionPane.YES_NO_OPTION // Tipo de botones
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                actualizar();
            }
        });

        //Boton Eliminar
        view.btnEliminar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    view, // Componente padre
                    "¿Estás seguro de que deseas borrar este registro?", // Mensaje
                    "Confirmar Eliminación", // Título
                    JOptionPane.YES_NO_OPTION // Tipo de botones
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                eliminar();
            }
        });

        //Boton Clean
        view.btnLimpiar.addActionListener(e -> limpiar());

        view.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarFila();
            }
        });

        //Restriccion_Numeros
        AceptarNumeroAdapter soloNumeros = new AceptarNumeroAdapter();
        view.txtID.addKeyListener(soloNumeros);
        view.txtEdad.addKeyListener(soloNumeros);
    }

    // ===============================
    // == METODO CRUD ==
    // ===============================

    //Introducion_Datos
    private void insertar() {
        try {
            int ID = Integer.parseInt(view.txtID.getText());
            String nombre = view.txtNombre.getText();
            String ape1 = view.txtApellido1.getText();
            String ape2 = view.txtApellido2.getText();
            int edad = Integer.parseInt(view.txtEdad.getText());

            //Introducion_Fila
            dao.insertar(new Usuarios(ID, nombre, ape1, ape2, edad));

            //Automaticamente, 
            cargarTabla();
            limpiar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Código Inválido");
        }
    }

    private void actualizar() {

        int fila = view.tabla.getSelectedRow();
        if (fila == -1)
            return;
        try {
            int id = Integer.parseInt(view.txtID.getText());
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

    //Suprimir Tabla
    private void eliminar() {

        int fila = view.tabla.getSelectedRow();
        if (fila == -1) return;

        int ID = Integer.parseInt(view.modeloTabla.getValueAt(fila, 0).toString());

        dao.eliminar(ID);

        cargarTabla();
        limpiar();
    }

    
    // ===============================
    // == Otros Métodos ==
    // ===============================

    //Señalamiento_Fila
    private void seleccionarFila() {

        int fila = view.tabla.getSelectedRow();
        if (fila == -1)
            return;
        view.txtID.setText(view.modeloTabla.getValueAt(fila, 0).toString());
        view.txtNombre.setText(view.modeloTabla.getValueAt(fila, 1).toString());
        view.txtApellido1.setText(view.modeloTabla.getValueAt(fila, 2).toString());
        view.txtApellido2.setText(view.modeloTabla.getValueAt(fila, 3).toString());
        view.txtEdad.setText(view.modeloTabla.getValueAt(fila, 4).toString());
    }

    private void cargarTabla() {
        view.modeloTabla.setRowCount(0);

        List<Usuarios> lista = dao.listar();
        for (Usuarios c : lista) {
            view.modeloTabla.addRow(new Object[] {
                    c.getId(),
                    c.getNombre(),
                    c.getApe1(),
                    c.getApe2(),
                    c.getEdad()
            });
        }
    }

    //Clean
    private void limpiar() {
        view.txtID.setText("");
        view.txtNombre.setText("");
        view.txtApellido1.setText("");
        view.txtApellido2.setText("");
        view.txtEdad.setText("");
        view.tabla.clearSelection();
    }
}
