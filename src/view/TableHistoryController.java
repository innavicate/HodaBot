package view;

import app.Utility;
import datamodel.BattleRecordDetails;
import datamodel.BattleResult;
import datamodel.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class TableHistoryController extends VBox implements Initializable {


    @FXML
    private VBox vbox;
    @FXML
    private TableView<TableHistoryItem> table_history;
    @FXML
    private TableColumn<TableHistoryItem, Player> table_history_player;
    @FXML
    private TableColumn<TableHistoryItem, BattleResult> table_history_result;
    @FXML
    private TableColumn<TableHistoryItem, Date> table_history_date;
    @FXML
    private TableColumn<TableHistoryItem, BattleRecordDetails> table_history_details;

    ObservableList<TableHistoryItem> battleHistoryList;

   public TableHistoryController(){
        Utility.loadResource("/fxml/TableHistory.fxml", this);
    }


    Connection connection;
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/fileDB", "", "");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        battleHistoryList = FXCollections.observableArrayList();
        table_history.setItems(battleHistoryList);

        vbox.getChildren().add(0, new BarchartController());


        table_history_player.setCellValueFactory(new PropertyValueFactory<TableHistoryItem, Player>("table_history_item_player"));
        table_history_result.setCellValueFactory(new PropertyValueFactory<TableHistoryItem, BattleResult>("table_history_item_result"));
        table_history_date.setCellValueFactory(new PropertyValueFactory<TableHistoryItem, Date>("table_history_item_time"));
        table_history_details.setCellValueFactory(new PropertyValueFactory<TableHistoryItem, BattleRecordDetails>("table_history_item_details"));


        table_history_date.setCellFactory(new Callback<TableColumn<TableHistoryItem, Date>, TableCell<TableHistoryItem, Date>>() {
                public TableCell<TableHistoryItem, Date> call(TableColumn<TableHistoryItem, Date> arg0) {
                    return new TableCell<TableHistoryItem,Date >() {
                        private Text text;

                        @Override
                        public void updateItem(Date item, boolean empty) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                text = new Text(dateFormat.format(item));
                                text.setTextAlignment(TextAlignment.CENTER);
                                text.setWrappingWidth(table_history_date.getWidth()-15);
                                text.setFill(Color.rgb(169,169,169));
                                this.setWrapText(true);
                                setGraphic(text);
                            }
                        }
                    };
                }
        });


    }

    public TableView<TableHistoryItem> getTable_history() {
        return table_history;
    }

    public void setTable_history(TableView<TableHistoryItem> table_history) {
        this.table_history = table_history;
    }


    public TableColumn<TableHistoryItem, Player> getTable_history_player() {
        return table_history_player;
    }

    public void setTable_history_player(TableColumn<TableHistoryItem, Player> table_history_player) {
        this.table_history_player = table_history_player;
    }

    public TableColumn<TableHistoryItem, BattleResult> getTable_history_result() {
        return table_history_result;
    }

    public void setTable_history_result(TableColumn<TableHistoryItem, BattleResult> table_history_result) {
        this.table_history_result = table_history_result;
    }




    public TableColumn<TableHistoryItem, BattleRecordDetails> getTable_history_details() {
        return table_history_details;
    }

    public void setTable_history_details(TableColumn<TableHistoryItem, BattleRecordDetails> table_history_details) {
        this.table_history_details = table_history_details;
    }

    public void addItem(TableHistoryItem item){
        battleHistoryList.add(item);
    }

    public ObservableList<TableHistoryItem> getBattleHistoryList() {
        return battleHistoryList;
    }

    public void setBattleHistoryList(ObservableList<TableHistoryItem> battleHistoryList) {
        this.battleHistoryList = battleHistoryList;
    }
}

