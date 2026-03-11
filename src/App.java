import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Controlador.LibrosController;
import Controlador.UsuariosController;
import Modelo.Usuarios;
import Vista.LibrosView;
import Vista.UsuariosView;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame=new JFrame("IES Cura Valera");
        //Crear el TabbedPane
        JTabbedPane tabbedPane =new JTabbedPane();
        tabbedPane.setBounds(50,50,300,200);
        LibrosView view = new LibrosView();
        // PiezaView view2= new PiezaView();
        UsuariosView view3= new UsuariosView();
        // SuministroView view4= new SuministroView();
        // Añadir los paneles como pestañas
        tabbedPane.add("Libros", view);
        // tabbedPane.add("Pieza", view2);
        tabbedPane.add("Usuarios", view3);
        // tabbedPane.add("Suministro", view4);
        // Definir el controlador asociado
        LibrosController l1=new LibrosController(view);
        // PiezaController c2=new PiezaController(view2);
        UsuariosController c3=new UsuariosController(view3);
        // SuministraController c4=new SuministraController(view4);
        // Asociar el TabbedPane
        frame.setContentPane(tabbedPane);
        // Tamaño y visibilidad
        frame.setSize(800,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
