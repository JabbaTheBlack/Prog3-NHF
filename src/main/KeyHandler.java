package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean right;
    public boolean left;
    public boolean up;
    public boolean down;
    public boolean esc;

    public KeyHandler() {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W: // 'W' key
            case KeyEvent.VK_UP: // Up arrow
                up = true;
                break;
            case KeyEvent.VK_S: // 'S' key
            case KeyEvent.VK_DOWN: // Down arrow
                down = true;
                break;
            case KeyEvent.VK_A: // 'A' key
            case KeyEvent.VK_LEFT: // Left arrow
                left = true;
                break;
            case KeyEvent.VK_D: // 'D' key
            case KeyEvent.VK_RIGHT: // Right arrow
                right = true;
                break;
            case KeyEvent.VK_ESCAPE: // Esc key
                esc = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_ESCAPE:
                esc = false;
                break;
        }
    }
}
