package object;

import main.GamePanel;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Object_Base {

    public BufferedImage image;
    public String name;
    public boolean collsion = false;
    public int x, y;
    public Rectangle hit_box = new Rectangle(0, 0, 48, 48);
    public int default_hit_box_x = 0, default_hit_box_y = 0;

    public void draw(Graphics2D graphics2D, GamePanel game_panel) {

        int screen_x = x - game_panel.getPlayer().global_x + game_panel.getPlayer().on_screen_x;
        int screen_y = y - game_panel.getPlayer().global_y + game_panel.getPlayer().on_screen_y;

        if (x + game_panel.getTileSize() > game_panel.getPlayer().global_x - game_panel.getPlayer().on_screen_x && x - game_panel.getTileSize() < game_panel.getPlayer().global_x + game_panel.getPlayer().on_screen_x && y + game_panel.getTileSize() > game_panel.getPlayer().global_y - game_panel.getPlayer().on_screen_y && y - game_panel.getTileSize() < game_panel.getPlayer().global_y + game_panel.getPlayer().on_screen_y) {

            graphics2D.drawImage(image, screen_x, screen_y, game_panel.getTileSize(), game_panel.getTileSize(), (ImageObserver)null);
        }
    }
}
