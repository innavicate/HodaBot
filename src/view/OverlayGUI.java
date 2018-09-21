package view;

import app.Utility;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;



public class OverlayGUI extends Pane implements Initializable {

    @FXML
    AnchorPane dragSpace;

    @FXML Pane dragPane;

    Group group;

    @FXML
    Button button_save, button_discard, button_add;

   public SimpleDoubleProperty windowX, windowY, windowW, windowH;
    Bounds bounds;
    ObservableList<Rectangle> element_list;
    private static String resource_path = "/fxml/editor.fxml";

    public OverlayGUI() {
        Utility.loadResource(resource_path,this);

        element_list = FXCollections.observableArrayList();
        windowX = new SimpleDoubleProperty(200);
        windowY = new SimpleDoubleProperty(100);
        windowW = new SimpleDoubleProperty(200);
        windowH = new SimpleDoubleProperty(100);


            button_add.setOnMouseClicked(event -> {
                buttton_addClicked();
            });
    }

    public void buttton_addClicked(){
        if(element_list.size() < 5){
            RectWithBounds rwb = new RectWithBounds();
            element_list.add(rwb.rect);
            dragPane.getChildren().add(rwb.rect);

        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bounds = localToScreen(getBoundsInLocal());

//        windowH.addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                setHeight(newValue.doubleValue());
//            }
//        });
//
//        windowW.addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                setWidth(newValue.doubleValue());
//            }
//        });
    }

    public ObservableList<Rectangle> getElement_list() {
        return element_list;
    }


    public static class RectWithBounds{
        Rectangle rect;
        Bounds bounds;


        public RectWithBounds() {
            rect = new Rectangle(110,25,Color.RED);
            rect.setOnMousePressed(rectOnMousePresedEventHandler);
            rect.setOnMouseDragged(rectOnMouseDraggedEventHandler);
        }

        public Rectangle getRect() {
            return rect;
        }

        public void setRect(Rectangle rect) {
            this.rect = rect;
        }

        public Bounds getBounds() {
            return bounds;
        }

        public void setBounds(Bounds bounds) {
            this.bounds = bounds;
        }

        private double orgSceneX,orgSceneY,orgTranslateY,orgTranslateX;
        EventHandler<MouseEvent> rectOnMousePresedEventHandler =
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent t) {
                        orgSceneX = t.getSceneX();
                        orgSceneY = t.getSceneY();
                        orgTranslateX = ((Rectangle) (t.getSource())).getTranslateX();
                        orgTranslateY = ((Rectangle) (t.getSource())).getTranslateY();
                    }
                };

        EventHandler<MouseEvent> rectOnMouseDraggedEventHandler =
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent t) {
                        double offsetX = t.getSceneX() - orgSceneX;
                        double offsetY = t.getSceneY() - orgSceneY;
                        double newTranslateX = orgTranslateX + offsetX;
                        double newTranslateY = orgTranslateY + offsetY;

                        Bounds b = ((Rectangle)t.getSource()).getBoundsInLocal();
                        bounds = ((Rectangle)t.getSource()).localToScreen(b);
                        System.out.println(bounds);

                        ((Rectangle)(t.getSource())).setTranslateX(newTranslateX);
                        ((Rectangle)(t.getSource())).setTranslateY(newTranslateY);
                    }
                };
    }
}
