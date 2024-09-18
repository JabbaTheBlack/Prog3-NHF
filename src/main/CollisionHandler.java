package main;

import entity.Entity;

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
}
