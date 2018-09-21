package clicker;


import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class MouseClicker {

    private Robot robot;

    public MouseClicker()  {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void leftButtonClick(int x, int y)
    {
        robot.mouseMove(x,y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public BufferedImage doScreenshot(int x, int y, int w , int h)
    {
        return robot.createScreenCapture(new Rectangle(x,y,w,h));
    }
}
