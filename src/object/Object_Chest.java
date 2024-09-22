package object;

import javax.imageio.ImageIO;

public class Object_Chest extends Object_Base {

    public Object_Chest() {

        name = "chest";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
