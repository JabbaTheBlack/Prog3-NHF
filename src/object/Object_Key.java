package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Object_Key extends Object_Base {

    public Object_Key() {

        name = "key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        collsion = true;
    }

}
