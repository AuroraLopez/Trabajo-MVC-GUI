import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.text.View;

import Controlador.LibrosController;
import Controlador.ParticipaPrestamosController;
import Controlador.UsuariosController;
import Vista.LibrosView;
import Vista.ParticipaPrestamosView;
import Vista.UsuariosView;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame=new JFrame("IES Cura Valera");

        //Crear el TabbedPane
        JTabbedPane tabbedPane =new JTabbedPane();
        tabbedPane.setBounds(50,50,300,200);
        LibrosView view = new LibrosView();
        ParticipaPrestamosView view2= new ParticipaPrestamosView();
        UsuariosView view3= new UsuariosView();

        // Añadir los paneles como pestañas
        tabbedPane.add("Libros", view);
        tabbedPane.add("Prestamos", view2);
        tabbedPane.add("Usuarios", view3);

        // Definir el controlador asociado
        LibrosController l1=new LibrosController(view);
        ParticipaPrestamosController p1 = new ParticipaPrestamosController(view2);
        UsuariosController c3=new UsuariosController(view3);

        // Asociar el TabbedPane
        frame.setContentPane(tabbedPane);
        // Tamaño y visibilidad
        frame.setSize(800,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}