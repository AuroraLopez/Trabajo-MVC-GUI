package Controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Adaptador para restringir la entrada de texto a solo números.
 */
public class AceptarNumeroAdapter extends KeyAdapter {
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        // Si el carácter no es un número, se consume el evento para que no se escriba
        if (!Character.isDigit(c)) {
            e.consume();
        }
    }
}
