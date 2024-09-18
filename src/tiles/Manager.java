
package tiles;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Manager {
    GamePanel game_panel;
    Tile[] tiles;
    int[][] map;

    public Manager(GamePanel game_panel) {
        this.game_panel = game_panel;
        this.tiles = new Tile[6];
        this.map = new int[game_panel.getMaxWorldColoumn()][game_panel.getMaxWorldRow()];
        this.getTileImage();
        this.loadMap("/maps/world01.txt");
    }

    public int[][] getMap() {
        return this.map;
    }

    public Tile[] getTiles() {
        return this.tiles;
    }

    public void getTileImage() {
        try {
            this.tiles[0] = new Tile();
            this.tiles[0].image = ImageIO.read(this.getClass().getResourceAsStream("/map_objects/grass.png"));
            this.tiles[1] = new Tile();
            this.tiles[1].image = ImageIO.read(this.getClass().getResourceAsStream("/map_objects/wall.png"));
            this.tiles[1].no_go_zone = true;
            this.tiles[2] = new Tile();
            this.tiles[2].image = ImageIO.read(this.getClass().getResourceAsStream("/map_objects/water.png"));
            this.tiles[2].no_go_zone = true;
            this.tiles[3] = new Tile();
            this.tiles[3].image = ImageIO.read(this.getClass().getResourceAsStream("/map_objects/earth.png"));
            this.tiles[4] = new Tile();
            this.tiles[4].image = ImageIO.read(this.getClass().getResourceAsStream("/map_objects/tree.png"));
            this.tiles[4].no_go_zone = true;
            this.tiles[5] = new Tile();
            this.tiles[5].image = ImageIO.read(this.getClass().getResourceAsStream("/map_objects/sand.png"));
        } catch (IOException var2) {
            IOException e = var2;
            e.printStackTrace();
        }

    }

    public void loadMap(String file_name) {
        try {
            InputStream input_stream = this.getClass().getResourceAsStream(file_name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input_stream));
            int coloumn = 0;
            int row = 0;

            while(coloumn < this.game_panel.getMaxWorldColoumn() && row < this.game_panel.getMaxWorldRow()) {
                for(String line = reader.readLine(); coloumn < this.game_panel.getMaxWorldColoumn(); ++coloumn) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[coloumn]);
                    this.map[coloumn][row] = num;
                }

                if (coloumn == this.game_panel.getMaxWorldColoumn()) {
                    coloumn = 0;
                    ++row;
                }
            }

            reader.close();
        } catch (IOException var9) {
            IOException e = var9;
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D graphics2D) {
        int coloumn = 0;
        int row = 0;

        while(coloumn < this.game_panel.getMaxWorldColoumn() && row < this.game_panel.getMaxWorldRow()) {
            int tile_number = this.map[coloumn][row];
            int world_x = coloumn * this.game_panel.getTileSize();
            int world_y = row * this.game_panel.getTileSize();
            int screen_x = world_x - this.game_panel.getPlayer().global_x + this.game_panel.getPlayer().on_screen_x;
            int screen_y = world_y - this.game_panel.getPlayer().global_y + this.game_panel.getPlayer().on_screen_y;
            if (world_x + this.game_panel.getTileSize() > this.game_panel.getPlayer().global_x - this.game_panel.getPlayer().on_screen_x && world_x - this.game_panel.getTileSize() < this.game_panel.getPlayer().global_x + this.game_panel.getPlayer().on_screen_x && world_y + this.game_panel.getTileSize() > this.game_panel.getPlayer().global_y - this.game_panel.getPlayer().on_screen_y && world_y - this.game_panel.getTileSize() < this.game_panel.getPlayer().global_y + this.game_panel.getPlayer().on_screen_y) {
                graphics2D.drawImage(this.tiles[tile_number].image, screen_x, screen_y, this.game_panel.getTileSize(), this.game_panel.getTileSize(), (ImageObserver)null);
            }

            ++coloumn;
            if (coloumn == this.game_panel.getMaxWorldColoumn()) {
                coloumn = 0;
                ++row;
            }
        }

    }
}
