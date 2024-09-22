package main;

import object.Object_Chest;
import object.Object_Door;
import object.Object_Key;

public class ObjectHandler {

    GamePanel game_panel;

    public ObjectHandler(GamePanel game_panel) {
        this.game_panel = game_panel;
    }

    public void setObject() {

        game_panel.objects[0] = new Object_Key();
        game_panel.objects[0].x = 23 * game_panel.tile_size;
        game_panel.objects[0].y = 7 * game_panel.tile_size;

        game_panel.objects[1] = new Object_Key();
        game_panel.objects[1].x = 23 * game_panel.tile_size;
        game_panel.objects[1].y = 40 * game_panel.tile_size;

        game_panel.objects[2] = new Object_Key();
        game_panel.objects[2].x = 37 * game_panel.tile_size;
        game_panel.objects[2].y = 7 * game_panel.tile_size;

        game_panel.objects[3] = new Object_Door();
        game_panel.objects[3].x = 10 * game_panel.tile_size;
        game_panel.objects[3].y = 9 * game_panel.tile_size;

        game_panel.objects[4] = new Object_Door();
        game_panel.objects[4].x = 8 * game_panel.tile_size;
        game_panel.objects[4].y = 28 * game_panel.tile_size;

        game_panel.objects[5] = new Object_Door();
        game_panel.objects[5].x = 12 * game_panel.tile_size;
        game_panel.objects[5].y = 22 * game_panel.tile_size;

        game_panel.objects[6] = new Object_Chest();
        game_panel.objects[6].x = 10 * game_panel.tile_size;
        game_panel.objects[6].y = 7 * game_panel.tile_size;
    }
}
