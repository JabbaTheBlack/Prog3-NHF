package main;

import entity.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

import object.Object_Base;
import tiles.Manager;

public class GamePanel extends JPanel implements Runnable {
    final int original_tile_size = 16;
    final int scale = 3;
    final int tile_size = 48;
    final int max_screen_col = 16;
    final int max_screen_row = 12;
    final int screen_width = 768;
    final int screen_height = 576;
    public final int max_world_coloumn = 50;
    public final int max_world_row = 50;
    public final int world_width = 2400;
    public final int world_height = 2400;
    Manager tile_manager = new Manager(this);
    KeyHandler key_handler = new KeyHandler();
    Player player;
    Thread game_thread;
    CollisionHandler collision_handler;
    ObjectHandler objectHandler = new ObjectHandler(this);
    Object_Base objects[] = new Object_Base[10];

    int FPS;

    public GamePanel() throws IOException {
        this.player = new Player(this, this.key_handler);
        this.collision_handler = new CollisionHandler(this);
        this.FPS = 60;
        this.setPreferredSize(new Dimension(768, 576));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.key_handler);
        this.setFocusable(true);
    }

    public int getTileSize() {
        return 48;
    }

    public int getMaxScreenCol() {
        return 16;
    }

    public int getMaxScreenRow() {
        return 12;
    }

    public int getScreenWidth() {
        return 768;
    }

    public int getScreenHeight() {
        return 576;
    }

    public int getMaxWorldColoumn() {
        return 50;
    }

    public int getMaxWorldRow() {
        return 50;
    }

    public Player getPlayer() {
        return this.player;
    }

    public CollisionHandler getCollisionHandler() {
        return this.collision_handler;
    }

    public Manager getManager() {
        return this.tile_manager;
    }

    public Object_Base[] getObjects() { return objects; }

    public void setUp(){

        objectHandler.setObject();
    }

    public void startGameThread() {
        this.game_thread = new Thread(this);
        this.game_thread.start();
    }

    public void run() {

        double draw_interval = (double)(1000000000 / this.FPS);
        double delta = 0.0;
        long last_time = System.nanoTime();
        long current_time = 0L;

        while(this.game_thread != null) {
            current_time = System.nanoTime();
            delta += (double)(current_time - last_time) / draw_interval;
            last_time = current_time;
            if (delta >= 1.0) {
                this.update();
                this.repaint();
                --delta;
            }
        }

    }

    public void update() {
        this.player.update();
    }

    public void openPauseMenu() {
        // Create a new JFrame for the pause menu
        JFrame pauseMenu = new JFrame("Pause Menu");
        pauseMenu.setSize(300, 200);
        pauseMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the menu
        pauseMenu.setUndecorated(true); // Removes window border

        // Center the pause menu on the game screen
        pauseMenu.setLocationRelativeTo(null);

        // Create a JPanel for the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1)); // One column, two rows

        // Create the "Exit Game" button
        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    player.savePosition();
                }catch(IOException exception){
                    exception.printStackTrace();
                }

                System.exit(0); // Exit the game
            }
        });

        // Add the "Exit Game" button to the panel
        panel.add(exitButton);

        // Add the panel to the JFrame
        pauseMenu.add(panel);

        // Make the JFrame visible
        pauseMenu.setVisible(true);
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;

        // Draw the tiles
        tile_manager.draw(graphics2D);

        // Draw the keys
        for(Object_Base object : objects) {
             if(object != null) {
                 object.draw(graphics2D, this);
             }
        }

        if (key_handler.esc) {
            openPauseMenu();
            key_handler.esc = false;
        }

        // Draw the player
        player.draw(graphics2D);

        graphics2D.dispose();
    }
}
