package ocr;

import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import view.OverlayGUI;
import view.OverlayGUI.RectWithBounds;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenReaderService {
    Robot robot;
    OverlayGUI overlayGUI;
    Tesseract tesseract;

    public ScreenReaderService(OverlayGUI gui) {

        tesseract = new Tesseract();
        tesseract.setDatapath(LoadLibs.extractTessResources("tessdata").getPath());

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        overlayGUI = gui;
    }

    public OverlayGUI getOverlayGUI() {
        return overlayGUI;
    }

     BufferedImage takeScreenshot(Rectangle rectWithBounds)
    {
        Bounds bRect = rectWithBounds.getBoundsInLocal();
        Bounds global = rectWithBounds.localToScreen(bRect);
              int x  = Double.valueOf(global.getMinX()).intValue();
              int y  = Double.valueOf(global.getMinY()).intValue();
              int w  = Double.valueOf(rectWithBounds.getWidth()).intValue();
              int h  = Double.valueOf(rectWithBounds.getHeight()).intValue();

              System.out.println("TAKING SNAPSHOT OF RECT [x:" + x + ", y: " +y + " , w: " + w + " h: " +h );

       return robot.createScreenCapture(new java.awt.Rectangle(x,y,w,h));
    }

    String getScreenshotText(BufferedImage bImg) throws TesseractException {
        return tesseract.doOCR(bImg);
    }

    public String snapAndProcess(Rectangle rect)
    {
        try {
            return getScreenshotText(takeScreenshot(rect));
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return "Error";
    }

}
