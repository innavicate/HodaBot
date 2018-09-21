package view;

import controls.combo_items.CheckItemCombobox;
import controls.combo_items.ItemCombobox;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private VBox search;

    @FXML
    private SearchFieldController searchController;

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane tp_general, tp_pg, tp_quest, tp_runes, tp_consume, tp_store, tp_shortcuts;


    @FXML
    private HBox hotkey_panel;

    @FXML
    private VBox general_list, pg_list, quest_list, runes_list, consume_list, hotkey_list, store_list;


    private Point2D icon_size;

    private Label change_hotkey;

    SimpleBooleanProperty settings_changed;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        icon_size = new Point2D(20, 20);

        setIcon(tp_general, "/styles/icons/icon_general.png");
        setIcon(tp_pg, "/styles/icons/icon_pg.png");
        setIcon(tp_quest, "/styles/icons/icon_quest.png");
        setIcon(tp_runes, "/styles/icons/icon_runes.png");
        setIcon(tp_consume, "/styles/icons/icon_consume.png");
        setIcon(tp_store, "/styles/icons/icon_store.png");
        setIcon(tp_shortcuts, "/styles/icons/icon_shortcuts.png");






        /*TAB GENERAL*/
        general_list.getChildren().add(new CheckItemCombobox("Use auto fight"));


        /* TAB PG */
        CheckItemCombobox pg_aimed_trophies = new CheckItemCombobox("Aimed trophies");
        CheckItemCombobox pg_use_runes = new CheckItemCombobox("Use runes in pg");
        ItemCombobox pg_rune_slot = new ItemCombobox("Use slot");

        pg_list.getChildren().addAll(pg_aimed_trophies, pg_use_runes, pg_rune_slot);



        /*TAB QUEST*/
        ItemCombobox chosen_quest = new ItemCombobox("Do following quest");
        ItemCombobox chosen_quest_difficulty = new ItemCombobox("Difficulty");

             //TODO zmienic typ na textfield
             ItemCombobox quest_target_energy= new ItemCombobox("Keep energy at");


             quest_list.getChildren().addAll(chosen_quest,chosen_quest_difficulty,quest_target_energy);

       /*TAB CONSUME*/

        ItemCombobox consume_out_of_space = new ItemCombobox("Consume out of space");
        ItemCombobox consume_faction_wise = new ItemCombobox("Consume faction wise");
        ItemCombobox consume_beneficient = new ItemCombobox("Consume beneficient");

        consume_list.getChildren().addAll(consume_out_of_space,consume_faction_wise,consume_beneficient);


        /* TAB RUNES*/
        ItemCombobox rune_first_slot = new ItemCombobox("First slot rune");
        ItemCombobox rune_second_slot = new ItemCombobox("Second slot rune");
        runes_list.getChildren().addAll(rune_first_slot, rune_second_slot);


        /* TAB STORE */

        ItemCombobox item1 = new ItemCombobox("Buy packs");
        store_list.getChildren().add(item1);




        searchController.search_field.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("OK");
            }
        });
    }

    private void setIcon(TitledPane tp, String icon_name) {
        ImageView icon = new ImageView();
        try {
            BufferedImage image = ImageIO.read(getClass().getResource(icon_name));
            icon.setImage(SwingFXUtils.toFXImage(image, null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        icon.setFitWidth(icon_size.getX());
        icon.setFitHeight(icon_size.getY());
        tp.setGraphic(icon);
        tp.setGraphicTextGap(20);
    }

}
