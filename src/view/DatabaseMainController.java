package view;


import app.Utility;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DatabaseMainController extends AnchorPane implements Initializable {

    @FXML
    private ListView<DatabaseCategoryItem> database_list;
    @FXML
    private AnchorPane database_container;

    private ObservableList<DatabaseCategoryItem> database_item_list;

    private DatabaseCategoryController dlc;


    DatabaseCategoryController tvc;
    SimpleBooleanProperty tvc_visable;

    public ListView<DatabaseCategoryItem> getDatabase_list() {
        return database_list;
    }

    public void setDatabase_list(ListView<DatabaseCategoryItem> database_list) {
        this.database_list = database_list;
    }

    public ObservableList<DatabaseCategoryItem> getDatabase_item_list() {
        return database_item_list;
    }

    public void setDatabase_item_list(ObservableList<DatabaseCategoryItem> database_item_list) {
        this.database_item_list = database_item_list;
    }

    void setAnchors(Node node)
    {
        AnchorPane.setLeftAnchor(node, 5.0);
        AnchorPane.setRightAnchor(node, 5.0);
        AnchorPane.setTopAnchor(node, 5.0);
        AnchorPane.setBottomAnchor(node, 5.0);
    }



  public DatabaseMainController(){

      Utility.loadResource("/fxml/databaseTab.fxml",this);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database_item_list = FXCollections.observableArrayList();
        //tvc_visable = new SimpleBooleanProperty(false);
        //tvc.setVisible(tvc_visable.getValue());
        //database_container.getChildren().add(tvc);

        //setAnchors(tvc);
        dlc = new DatabaseCategoryController();
        dlc.setPrev(database_list);

        DatabaseCategoryItem players = new DatabaseCategoryItem("Players","");
        database_item_list.add(players);

        DatabaseCategoryItem guilds = new DatabaseCategoryItem("Guilds","");
        database_item_list.add(guilds);

        DatabaseCategoryItem matches = new DatabaseCategoryItem("Matches","");
        database_item_list.add(matches);

        DatabaseCategoryItem events = new DatabaseCategoryItem("Events","");
        database_item_list.add(events);

            database_list.setItems(database_item_list);

            database_item_list.forEach(databaseCategoryItem -> {
                databaseCategoryItem.setOnMouseClicked(event -> {

                    database_container.getChildren().remove(database_list);
                    database_container.getChildren().add(dlc);
                    dlc.setdLabelText(databaseCategoryItem.getItem_fullname_string());
                    System.out.println(databaseCategoryItem.getItem_fullname_string());
                    setAnchors(dlc);
                });
            });

            dlc.getBack().setOnMouseClicked(event -> {
                database_container.getChildren().remove(dlc);
                database_container.getChildren().add(dlc.getPrev());
            });




    }
}
