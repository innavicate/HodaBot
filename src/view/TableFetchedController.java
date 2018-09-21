package view;

import datamodel.Avatar;
import datamodel.Toon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class TableFetchedController extends VBox implements Initializable {

    @FXML
    private TableView<TableFetchedItem> table;
    @FXML
    private TableColumn<TableFetchedItem, ImageView> table_fetched_toon_avatar;
    @FXML
    private TableColumn<TableFetchedItem, String> table_fetched_toon_name;
    @FXML
    private TableColumn<TableFetchedItem, String> table_fetched_toon_pos;
    @FXML
    private TableColumn<TableFetchedItem, String> table_fetched_toon_details;
    @FXML
    private ObservableList<TableFetchedItem> fetched_enemies;

    public TableFetchedController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("TNIIIT");
        fetched_enemies = FXCollections.observableArrayList();
        table_fetched_toon_avatar.setCellValueFactory(new PropertyValueFactory<TableFetchedItem, ImageView>("fetched_toon_avatar"));
        table_fetched_toon_name.setCellValueFactory(new PropertyValueFactory<TableFetchedItem, String>("fetched_toon_name"));
        table_fetched_toon_pos.setCellValueFactory(new PropertyValueFactory<TableFetchedItem, String>("fetched_toon_position"));
        table_fetched_toon_details.setCellValueFactory(new PropertyValueFactory<TableFetchedItem, String>("fetched_toon_details"));
        table.setItems(fetched_enemies);


        table_fetched_toon_name.setCellFactory(new Callback<TableColumn<TableFetchedItem, String>, TableCell<TableFetchedItem, String>>() {
            @Override
            public TableCell<TableFetchedItem, String> call(TableColumn<TableFetchedItem, String> arg0) {
                return new TableCell<TableFetchedItem, String>() {
                    private Text text;

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            text = new Text(item.toString());
                            text.setTextAlignment(TextAlignment.CENTER);
                            text.setWrappingWidth(table_fetched_toon_name.getWidth() - 10);
                            text.setFill(Color.rgb(169, 169, 169));
                            this.setWrapText(true);
                            setGraphic(text);
                        }
                    }
                };
            }
        });

    }

    public void clearTable(){
        this.fetched_enemies.clear();
    }

    public void addTableItem(Toon toon){
        TableFetchedItem item = new TableFetchedItem(toon);
        System.out.println(fetched_enemies);
        fetched_enemies.add(item);
        table.setItems(fetched_enemies);
    }


}
