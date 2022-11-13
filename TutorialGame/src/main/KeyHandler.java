package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean wKeypad, sKeypad, aKeypad, dKeypad;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W)
            wKeypad = true;
        if(code == KeyEvent.VK_S)
            sKeypad = true;
        if(code == KeyEvent.VK_A)
            aKeypad = true;
        if(code == KeyEvent.VK_D)
            dKeypad = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W)
            wKeypad = false;
        if(code == KeyEvent.VK_S)
            sKeypad = false;
        if(code == KeyEvent.VK_A)
            aKeypad = false;
        if(code == KeyEvent.VK_D)
            dKeypad = false;
    }
}
