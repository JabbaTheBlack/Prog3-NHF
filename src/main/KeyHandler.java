package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean right;
    public boolean left;
    public boolean up;
    public boolean down;

    public KeyHandler() {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code != 87 && code != 38) {
            if (code != 83 && code != 40) {
                if (code != 65 && code != 37) {
                    if (code == 68 || code == 39) {
                        this.right = true;
                    }
                } else {
                    this.left = true;
                }
            } else {
                this.down = true;
            }
        } else {
            this.up = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code != 87 && code != 38) {
            if (code != 83 && code != 40) {
                if (code != 65 && code != 37) {
                    if (code == 68 || code == 39) {
                        this.right = false;
                    }
                } else {
                    this.left = false;
                }
            } else {
                this.down = false;
            }
        } else {
            this.up = false;
        }

    }
}
