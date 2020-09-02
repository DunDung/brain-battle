package brainbattle.utils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageUtils {
    private static final String IMAGES_URL = "../../resources/main/static/images/";

    public static ImageIcon createImageIcon(Object object, String imageName) {
        URL resource = object.getClass().getClassLoader().getResource(IMAGES_URL + imageName);
        System.out.println(resource);
        return new ImageIcon(resource);
    }

    public static Image createImage(Object object, String imageName) {
        return createImageIcon(object, imageName).getImage();
    }

}
