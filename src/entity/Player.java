
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.spi.AbstractResourceBundleProvider;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel game_panel;
    KeyHandler key_handler;
    public final int on_screen_x;
    public final int on_screen_y;
    int has_key = 0;

    public Player(GamePanel game_panel, KeyHandler key_handler) throws IOException {
        this.game_panel = game_panel;
        this.key_handler = key_handler;
        on_screen_x = game_panel.getScreenWidth() / 2 - game_panel.getTileSize() / 2;
        on_screen_y = game_panel.getScreenHeight() / 2 - game_panel.getTileSize() / 2;

        this.hit_box = new Rectangle(8, 16, 32, 32);
        default_hit_box_x = hit_box.x;
        default_hit_box_y = hit_box.y;

        this.setDefault();
        this.getPlayerImage();
    }

    public void setDefault() throws IOException {
        try{
            loadPosition();
        }catch(Exception e){}

        this.speed = 4;
    }

    public void savePosition() throws IOException{
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("last_position.txt"));
            writer.write(global_x + " " + global_y);
            writer.newLine();  // This will add a new line in the file
            writer.flush();    // Ensure the data is written to the file
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();  // Close the writer to free system resources
            }
        }
    }

    public void loadPosition() throws IOException{
        File file = new File("last_position.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                if (br.ready()) {
                    String[] position = br.readLine().split(" ");

                    // Ensure we have exactly two elements (X and Y positions)
                    if (position.length == 2) {
                        try {
                            global_x = Integer.parseInt(position[0]);
                            global_y = Integer.parseInt(position[1]);
                            System.out.println("Position loaded: (" + global_x + ", " + global_y + ")");
                            return;
                        } catch (NumberFormatException e) {
                            // If the values aren't integers, fall back to default
                            System.out.println("Invalid position format. Setting default values.");
                        }
                    } else {
                        System.out.println("Position data is incomplete. Setting default values.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();  // Handle any IO exception
            }
        } else {
            System.out.println("Position file not found. Setting default values.");
        }

        // Set default position if file doesn't exist or data is invalid
        global_x = game_panel.getTileSize() * 23;  // Default X position
        global_y = game_panel.getTileSize() * 21;  // Default Y position
        System.out.println("Default position set: (" + global_x + ", " + global_y + ")");
    }

    public void getPlayerImage() {
        try {
            this.up1 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_up_1.png"));
            this.up2 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_up_2.png"));
            this.down1 = ImageIO.read(this.getClass().getResourceAsStream("/player/player_down_1.png"));
            this.down2 = ImageIO.read(this.getClass().getResourceAsStream("/player/player_down_2.png"));
            this.left1 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_left_1.png"));
            this.left2 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_left_2.png"));
            this.right1 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_right_1.png"));
            this.right2 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException var2) {
            IOException e = var2;
            e.printStackTrace();
        }

    }

    public void update() {
        if (this.key_handler.up || this.key_handler.down || this.key_handler.left || this.key_handler.right) {
            if (this.key_handler.up) {
                this.direction = "up";
            } else if (this.key_handler.down) {
                this.direction = "down";
            } else if (this.key_handler.left) {
                this.direction = "left";
            } else if (this.key_handler.right) {
                this.direction = "right";
            }

            // Check tile collision
            can_collide = false;
            game_panel.getCollisionHandler().checkTileCollision(this);

            // Check object collision
            int object_index = game_panel.getCollisionHandler().checkObjectCollision(this, true);
            pickUpObject(object_index);

            if (!this.can_collide) {
                switch (this.direction) {
                    case "up":
                        this.global_y -= this.speed;
                        break;
                    case "down":
                        this.global_y += this.speed;
                        break;
                    case "left":
                        this.global_x -= this.speed;
                        break;
                    case "right":
                        this.global_x += this.speed;
                }
            }
        }

        ++this.image_counter;
        if (this.image_counter > 15) {
            if (this.image_number == 1) {
                this.image_number = 2;
            } else {
                this.image_number = 1;
            }

            this.image_counter = 0;
        }

    }

    public void pickUpObject(int idx) {

        if (idx != Integer.MAX_VALUE){

            String object_name = game_panel.getObjects()[idx].name;

            switch (object_name) {
                case "door":
                    if(has_key > 0) {
                        game_panel.getObjects()[idx] = null;
                        has_key--;
                    }
                    break;
                case "key":
                    has_key++;
                    game_panel.getObjects()[idx] = null;
                    break;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (this.direction) {
            case "up":
                if (this.image_number == 1) {
                    image = this.up2;
                } else {
                    image = this.up1;
                }
                break;
            case "down":
                if (this.image_number == 1) {
                    image = this.down1;
                } else {
                    image = this.down2;
                }
                break;
            case "left":
                if (this.image_number == 1) {
                    image = this.left2;
                } else {
                    image = this.left1;
                }
                break;
            case "right":
                if (this.image_number == 1) {
                    image = this.right2;
                } else {
                    image = this.right1;
                }
        }

        graphics2D.drawImage(image, this.on_screen_x, this.on_screen_y, this.game_panel.getTileSize(), this.game_panel.getTileSize(), (ImageObserver)null);
    }
}
