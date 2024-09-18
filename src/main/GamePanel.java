package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
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
    int FPS;

    public GamePanel() {
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

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;
        this.tile_manager.draw(graphics2D);
        this.player.draw(graphics2D);
        graphics2D.dispose();
    }
}
