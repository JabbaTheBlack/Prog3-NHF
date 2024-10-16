package main;

import java.awt.Component;
import java.io.IOException;
import javax.swing.JFrame;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.setTitle("Vikings");

        GamePanel game_panel = new GamePanel();
        window.add(game_panel);
        window.pack();

        window.setLocationRelativeTo((Component)null);
        window.setVisible(true);

        game_panel.setUp();
        game_panel.startGameThread();
    }
}
