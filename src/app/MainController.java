package app;

import clicker.MouseClicker;
import com.google.common.base.CharMatcher;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import database.DatabaseController;
import database.ToonController;
import datamodel.Battle;
import datamodel.Guild;
import datamodel.Player;
import datamodel.Toon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import notifications.NotificationController;
import ocr.ScreenReaderService;
import view.*;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class MainController extends AnchorPane implements Initializable {
    SepiaTone button_on, button_off;
    @FXML   private ToggleButton toggle_pg, toggle_quest, toggle_runes, toggle_consume, toggle_store;
    @FXML   private Tab tab_learn, tab_history, tab_settings, tab_database;
    @FXML   private JFXButton button_fight, button_skip, button_clear;

    @FXML   private Pane context_pane;
    @FXML   private JFXTabPane tab_pane;
    @FXML   private AnchorPane root;

            private DatabaseController databaseController;
            private NotificationController notificationsController;
    @FXML
    private SettingsController settings_controller;
    @FXML
    private TableFetchedController tableFetchedController;
            private TableHistoryController tableHistoryController;

            private ScreenReaderService screenReaderService;
            private OverlayGUI overlayGUI;
    @FXML
    BarChart barchart;

   @FXML
   AnchorPane tableHistoryPane;



     MainController(){
        Utility.loadResource("/fxml/sample.fxml",this);
        System.out.println("CTOR");
    }

    private void selectedhandler(final ToggleButton button) {
        if (button.isSelected()) {
            button.setEffect(button_on);
        } else {
            button.setEffect(button_off);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            System.setProperty("jna.encoding", "UTF8");

        databaseController  = new DatabaseController();
        overlayGUI = new OverlayGUI();
        screenReaderService = new ScreenReaderService(overlayGUI);



        try {
            ArrayList<Battle> battles = databaseController.getBattleController().getAll();
            tableHistoryController = new TableHistoryController();
            tableHistoryPane.getChildren().add(tableHistoryController);

            AnchorPane.setTopAnchor(tableHistoryController, 0.0);
            AnchorPane.setBottomAnchor(tableHistoryController, 0.0);
            AnchorPane.setLeftAnchor(tableHistoryController, 2.0);
            AnchorPane.setRightAnchor(tableHistoryController, 2.0);

//            Battle battle = new Battle(1,2,Calendar.getInstance().getTime(),1);
//            for (int i=0; i<5; i++)
//            tableHistoryController.addItem(new TableHistoryItem(battle));
//
//
//            databaseController.getBattleController().addRecord(battle);
//            System.out.println("Battles SIZE : " + battles.size());

        } catch (SQLException e1) {
            System.out.println("Add Battle error");
        }

        

//        /* END OF GUILD TEST*/


//        try {
//            ArrayList<Event> events = databaseController.getEventController().getAll();
//            System.out.println("Events SIZE : " + events.size());
//
//            Event ev1 = new Event(1,"name","asd",Calendar.getInstance().getTime());
//            databaseController.getEventController().addRecord(ev1);
//
//            } catch (SQLException e1) {
//                System.out.println("Add Event error");
//            }

        /*GUILD TEST*/
        try {
            ArrayList<Guild> guilds = databaseController.getGuildController().getAll();
            System.out.println("G SIZE : " + guilds.size());

            Guild newGuild = new Guild(4, "OK1");
            databaseController.getGuildController().addRecord(newGuild);

            System.out.println("G SIZE : " + guilds.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

            try {
                ArrayList<Player> players = databaseController.getPlayerController().getAll();
                System.out.println("Players SIZE : " + players.size());

                Player newPlayer = new Player(0,"Valerine",1);
                databaseController.getPlayerController().addRecord(newPlayer);
                System.out.println("Players SIZE : " + players.size());

            } catch (SQLException ex){ }

//            MouseClicker clicker = new MouseClicker();
//            clicker.leftButtonClick(50,50);


        Stage stage = new Stage();
        OverlayGUI oGUI = screenReaderService.getOverlayGUI();
        Scene scene = new Scene(oGUI);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setOpacity(0.5);
        stage.show();





        button_on = new SepiaTone();
        button_on.setLevel(1.0);

        button_off = new SepiaTone();
        button_off.setLevel(0.0);

        toggle_pg.setOnAction(event -> selectedhandler(toggle_pg));
        toggle_quest.setOnAction(event -> selectedhandler(toggle_quest));
        toggle_runes.setOnAction(event -> selectedhandler(toggle_runes));
        toggle_consume.setOnAction(event -> selectedhandler(toggle_consume));
        toggle_store.setOnAction(event -> selectedhandler(toggle_store));


        tab_pane.tabMinWidthProperty().bind(root.widthProperty().divide(tab_pane.getTabs().size()).subtract(20));

        notificationsController = new NotificationController();

        root.getChildren().add(notificationsController);
        AnchorPane.setBottomAnchor(notificationsController, 0.0);
        AnchorPane.setLeftAnchor(notificationsController, 2.0);
        AnchorPane.setRightAnchor(notificationsController, 2.0);

        NotificationController.setContr(notificationsController);

        DatabaseMainController databaseController= new DatabaseMainController();
        tab_database.setContent(databaseController);

        button_clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableFetchedController.clearTable();
                System.out.println(screenReaderService.getOverlayGUI().getElement_list().size());
                screenReaderService.getOverlayGUI().getElement_list().forEach(rectangle -> {
                    String name =  screenReaderService.snapAndProcess(rectangle);
                    System.out.println(" Readed name " + name);
                    Toon toon = DatabaseController.getInstance().getToonControler().getToon(name);
                    if(!toon.equals(Toon.getDefault_toon()))
                        tableFetchedController.addTableItem(toon);
                });
            }
        });

    }


}
