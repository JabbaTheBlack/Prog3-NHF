package main;

import entity.Entity;
import object.Object_Base;

public class CollisionHandler {
    GamePanel game_panel;

    public CollisionHandler(GamePanel game_panel) {
        this.game_panel = game_panel;
    }

    public void checkTileCollision(Entity entity) {
        int left_side_x = entity.global_x + entity.hit_box.x;
        int right_side_x = entity.global_x + entity.hit_box.x + entity.hit_box.width;
        int top_y = entity.global_y + entity.hit_box.y;
        int bottom_y = entity.global_y + entity.hit_box.y + entity.hit_box.height;
        int left_coloumn = left_side_x / this.game_panel.getTileSize();
        int right_coloumn = right_side_x / this.game_panel.getTileSize();
        int top_row = top_y / this.game_panel.getTileSize();
        int bottom_row = bottom_y / this.game_panel.getTileSize();
        int tile_num_1;
        int tile_num_2;

        switch (entity.direction) {
            case "up":
                top_row = (top_y - entity.speed) / this.game_panel.getTileSize();
                tile_num_1 = this.game_panel.getManager().getMap()[left_coloumn][top_row];
                tile_num_2 = this.game_panel.getManager().getMap()[right_coloumn][top_row];
                if (this.game_panel.getManager().getTiles()[tile_num_1].no_go_zone || this.game_panel.getManager().getTiles()[tile_num_2].no_go_zone) {
                    entity.can_collide = true;
                }
                break;
            case "down":
                bottom_row = (bottom_y + entity.speed) / this.game_panel.getTileSize();
                tile_num_1 = this.game_panel.getManager().getMap()[left_coloumn][bottom_row];
                tile_num_2 = this.game_panel.getManager().getMap()[right_coloumn][bottom_row];
                if (this.game_panel.getManager().getTiles()[tile_num_1].no_go_zone || this.game_panel.getManager().getTiles()[tile_num_2].no_go_zone) {
                    entity.can_collide = true;
                }
                break;
            case "left":
                left_coloumn = (left_side_x - entity.speed) / this.game_panel.getTileSize();
                tile_num_1 = this.game_panel.getManager().getMap()[left_coloumn][top_row];
                tile_num_2 = this.game_panel.getManager().getMap()[left_coloumn][bottom_row];
                if (this.game_panel.getManager().getTiles()[tile_num_1].no_go_zone || this.game_panel.getManager().getTiles()[tile_num_2].no_go_zone) {
                    entity.can_collide = true;
                }
                break;
            case "right":
                right_coloumn = (right_side_x + entity.speed) / this.game_panel.getTileSize();
                tile_num_1 = this.game_panel.getManager().getMap()[right_coloumn][top_row];
                tile_num_2 = this.game_panel.getManager().getMap()[right_coloumn][bottom_row];
                if (this.game_panel.getManager().getTiles()[tile_num_1].no_go_zone || this.game_panel.getManager().getTiles()[tile_num_2].no_go_zone) {
                    entity.can_collide = true;
                }
        }

    }

    /*
        Check if player is hitting an object. If so return the index of that tile
    */
    public int checkObjectCollision(Entity entity, boolean is_on_object) {

        int idx = Integer.MAX_VALUE;

        for (int i =0 ; i < game_panel.objects.length ; i++ ) {
            Object_Base object = game_panel.objects[i];

            if(object != null) {

                // Get entity's hit_box position
                entity.hit_box.x = entity.global_x + entity.hit_box.x;
                entity.hit_box.y = entity.global_y + entity.hit_box.y;

                // Get object's hit_box position
                object.hit_box.x = object.x + object.hit_box.x;
                object.hit_box.y = object.y + object.hit_box.y;

                switch (entity.direction) {
                    case "up":
                        entity.hit_box.y -= entity.speed;

                        if(entity.hit_box.intersects(object.hit_box)) {
                            if(object.collsion == true) {
                                entity.can_collide = true;
                            }
                            if (is_on_object == true) {
                                idx = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hit_box.y += entity.speed;

                        if(entity.hit_box.intersects(object.hit_box)) {
                            if(object.collsion == true) {
                                entity.can_collide = true;
                            }
                            if (is_on_object == true) {
                                idx = i;
                            }
                        }
                        break;
                    case "right":
                        entity.hit_box.x += entity.speed;

                        if(entity.hit_box.intersects(object.hit_box)) {
                            if(object.collsion == true) {
                                entity.can_collide = true;
                            }
                            if (is_on_object == true) {
                                idx = i;
                            }
                        }
                        break;
                    case "left":
                        entity.hit_box.x -= entity.speed;

                        if(entity.hit_box.intersects(object.hit_box)) {
                            if(object.collsion == true) {
                                entity.can_collide = true;
                            }
                            if (is_on_object == true) {
                                idx = i;
                            }
                        }
                        break;

                }
                // Reset their values
                entity.hit_box.x = entity.default_hit_box_x;
                entity.hit_box.y = entity.default_hit_box_y;

                object.hit_box.x = object.default_hit_box_x;
                object.hit_box.y = object.default_hit_box_y;
            }
        }

        return idx;
    }
}
