package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int global_x;
    public int global_y;
    public int speed;
    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;
    public String direction = "up";
    public int image_counter;
    public int image_number;
    public Rectangle hit_box;
    public boolean can_collide = false;

    public Entity() {
    }
}
