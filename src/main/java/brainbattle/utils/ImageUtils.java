package brainbattle.utils;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

public class ImageUtils {
    private static final String IMAGES_URL = "../../resources/main/static/images/";

    public static ImageIcon createImageIcon(Object object, String imageName) {
        URL resource = object.getClass().getClassLoader().getResource(IMAGES_URL + imageName);
        return new ImageIcon(resource);
    }

    public static Image createImage(Object object, String imageName) {
        return createImageIcon(object, imageName).getImage();
    }

}
