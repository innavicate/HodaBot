package view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchFieldController implements Initializable {

    @FXML
    TextField search_field;


    private void query(String text) {
        System.out.println("Searched\t" + text);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search_field.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                TextField search_bar = (TextField) event.getSource();
                if (!search_bar.getText().isEmpty())
                    query(search_bar.getText());
            }
        });
    }
}
