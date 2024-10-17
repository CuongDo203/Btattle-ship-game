package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImageManager {
    
    public static final String LOGIN_IMAGE = "Login_background_resized.jpg";
    public static final String REGISTER_IMAGE = "Register_background_resized.jpg";
    public static final String MAIN_BACKGROUND_IMAGE = "main_background.jpg";
    public static final String HISTORY_BACKGROUND_IMAGE = "history.jpg";
    public static final String NAME_TAG = "NameTag.png";
    public static final String SHIP_SIZE_5 = "battleship.png";
    public static final String SHIP_SIZE_4 = "cruiser.png";
    public static final String SHIP_SIZE_3_1 = "destroyer.png";
    public static final String SHIP_SIZE_3_2 = "rescue ship.png";
    public static final String SHIP_SIZE_2 = "patrol boat.png";
    public static final String READY_BACKGROUND_IMAGE = "water.jpg";
    public static final String RANK_BACKGROUND_IMAGE = "rank.png";
    
    public static BufferedImage getImage(String filename) {
        BufferedImage img = null;
        InputStream is = ImageManager.class.getResourceAsStream("/assets/images/"+filename);
        try {
            img = ImageIO.read(is);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return img;
    }
    
}
