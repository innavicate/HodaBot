package view;

import controls.hotkey.Hotkey;
import controls.hotkey.HotkeyA;
import controls.hotkey.HotkeyB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import notifications.Confirm;
import notifications.NotificationController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HotkeyController extends AnchorPane implements Initializable {

    @FXML
    private HBox hotkey_panel;

    @FXML
    private VBox hotkey_list;

    private HotkeyA hotkey_pq, hotkey_quest, hotkey_runes, hotkey_store, hotkey_consume;

    private HotkeyB hotkey_fight, hotkey_skip, hotkey_clear, hotkey_changetab;


    ObservableList<Hotkey> hotkeys;

    ArrayList<Hotkey> to_save;


    public HotkeyController()
    {
        hotkey_pq =  new HotkeyA("PG");
        hotkey_quest =  new HotkeyA("Quest");
        hotkey_runes = new HotkeyA("Runes");
        hotkey_store = new HotkeyA("Store");
        hotkey_consume = new HotkeyA("Consume");

        hotkey_fight = new HotkeyB("Fight");
        hotkey_skip = new HotkeyB("Skip");
        hotkey_clear = new HotkeyB("Clear");
        hotkey_changetab = new HotkeyB("Change Tab");

        hotkeys = FXCollections.observableArrayList();
        to_save = new ArrayList<Hotkey>();

    }

    private void save(){
        if(to_save.isEmpty()) return;

        to_save.forEach(hotkey -> {

            //TODO ZAPIS HOTKEYA W BAZIE DANYCH LUB XML
            hotkey.key_changedProperty().setValue(false);

        });

        to_save.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hotkey_panel.getChildren().addAll( hotkey_pq, hotkey_quest ,hotkey_runes,hotkey_store,hotkey_consume );
        hotkey_list.getChildren().addAll( hotkey_fight , hotkey_skip , hotkey_clear , hotkey_changetab );

        hotkeys.addAll(hotkey_pq, hotkey_quest ,hotkey_runes,hotkey_store,hotkey_consume,
                hotkey_fight , hotkey_skip , hotkey_clear , hotkey_changetab );

        hotkeys.forEach(hotkey -> {
            hotkey.key_changedProperty().addListener(changed -> {
                System.out.println(hotkey.getLabel_text() + " need to be saved");
                to_save.add(hotkey);


                Confirm apply_changes = new Confirm();
                NotificationController.getContr().set_content(apply_changes);
            });
        });



    }


}
