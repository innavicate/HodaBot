package datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Avatar {

    private static Avatar default_avatar = new Avatar();

    static String def_avatar = "styles/unknown.jpg";
    static String def_loc = "/avatars/";

    SimpleIntegerProperty toon_id;
    BufferedImage toon_avatar_shown;

    public Avatar(int id_toon) {
        toon_id = new SimpleIntegerProperty(id_toon);
        try {
        String s = def_loc+String.valueOf(id_toon)+".jpg";
        System.out.println(s);
           InputStream inputStream =  getClass().getResourceAsStream(s);
         URL url =   getClass().getResource(s);
         System.out.println(url);
            if(url != null)
                toon_avatar_shown = ImageIO.read(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Avatar(){
        toon_id = new SimpleIntegerProperty(0);
        try {
            toon_avatar_shown = ImageIO.read(getClass().getResource("/styles/unknown.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static String getDef_avatar() {
        return def_avatar;
    }

    public static void setDef_avatar(String def_avatar) {
        Avatar.def_avatar = def_avatar;
    }

    Integer getToonID(String byname) {
        return toon_id.getValue();
    }

    String getAvatarNameByID(Integer id) {
        return def_avatar;
    }

    public Image getAvatar() {
        if(toon_avatar_shown != null)
            return SwingFXUtils.toFXImage(toon_avatar_shown, null);
        else
            return SwingFXUtils.toFXImage(Avatar.default_avatar.toon_avatar_shown,null);
    }

    public int getToon_id() {
        return toon_id.get();
    }

    public void setToon_id(int toon_id) {
        this.toon_id.set(toon_id);
    }

    public SimpleIntegerProperty toon_idProperty() {
        return toon_id;
    }



    public void setToon_avatar_shown(BufferedImage toon_avatar_shown) {
        this.toon_avatar_shown = toon_avatar_shown;
    }
}
