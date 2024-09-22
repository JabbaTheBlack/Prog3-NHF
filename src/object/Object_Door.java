package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Door extends Object_Base {

    public Object_Door() {

        name = "door";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
