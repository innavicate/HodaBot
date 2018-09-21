package app;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Utility {

    public static Parent loadResource(String resourceName, Object obj) {
        FXMLLoader fxmlLoader = new FXMLLoader(Utility.class.getResource(resourceName));
        fxmlLoader.setRoot(obj);
        fxmlLoader.setController(obj);

        try {
           return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    public static void setIcon(double w , double h, ImageView source, String icon_name) {
        ImageView icon = new ImageView();
        try {
            BufferedImage image = ImageIO.read(Utility.class.getResource(icon_name));
           source.setImage(SwingFXUtils.toFXImage(image, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        icon.setFitWidth(w);
        icon.setFitHeight(h);

    }
}
