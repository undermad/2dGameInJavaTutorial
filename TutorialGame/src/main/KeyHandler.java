package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyHandler implements KeyListener {

    public boolean wKeypad, sKeypad, aKeypad, dKeypad;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            wKeypad = true;
        }
        if (code == KeyEvent.VK_S) {
            sKeypad = true;
        }
        if (code == KeyEvent.VK_A) {
            aKeypad = true;
        }
        if (code == KeyEvent.VK_D) {
            dKeypad = true;
        }
        if (code == KeyEvent.VK_P) {
            if (gp.getGameState() == gp.getPlayState()){
                gp.setGameState(gp.getPauseState());
            } else
                gp.setGameState(gp.getPlayState());
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            wKeypad = false;
        }
        if (code == KeyEvent.VK_S) {
            sKeypad = false;
        }
        if (code == KeyEvent.VK_A) {
            aKeypad = false;
        }
        if (code == KeyEvent.VK_D) {
            dKeypad = false;
        }

    }
}
