import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Controlador.LibrosController;
import Vista.LibrosView;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame=new JFrame("IES Cura Valera");
        //Crear el TabbedPane
        JTabbedPane tabbedPane =new JTabbedPane();
        tabbedPane.setBounds(50,50,300,200);
        LibrosView view = new LibrosView();
        // Añadir los paneles como pestañas
        tabbedPane.add("Libros", view);
        // Definir el controlador asociado
        LibrosController l1=new LibrosController(view);
        // Asociar el TabbedPane
        frame.setContentPane(tabbedPane);
        // Tamaño y visibilidad
        frame.setSize(800,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
