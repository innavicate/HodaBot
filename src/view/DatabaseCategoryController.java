package view;

import app.Utility;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;


public class DatabaseCategoryController extends AnchorPane  {

    @FXML
    private TreeView<DatabaseRecord> treeview;

    @FXML
    private Label back;

    @FXML
    private Label section_name;

    private  TreeItem<DatabaseRecord> root;

    private Node prev;


    private ScrollBar getScrollBar(TreeView<?> tree, Orientation orientation) {
        // Get the ScrollBar with the given Orientation using lookupAll
        for (Node n : tree.lookupAll(".scroll-bar")) {
            if (n instanceof ScrollBar) {
                ScrollBar bar = (ScrollBar) n;

                if (bar.getOrientation().equals(orientation))
                    return bar;
            }
        }
        return null;
    }


    public DatabaseCategoryController()
    {
        Utility.loadResource("/fxml/DatabaseCategory.fxml",this);
        root = new TreeItem<DatabaseRecord>();
        DatabaseRecord root_item = new DatabaseRecord("ROOT");
        root.setValue(root_item);
        root.setExpanded(true);

        for(int i=0; i < 20; i++)
        addItem(new DatabaseRecord("ROOT"), root);
        treeview.setRoot(root);
    }


    void addItem(DatabaseRecord item, TreeItem<DatabaseRecord> root)
    {
        if(root!=null)
            root.getChildren().add(new TreeItem<>(item));
    }

    public TreeView<DatabaseRecord> getTreeview() {
        return treeview;
    }

    public void setTreeview(TreeView<DatabaseRecord> treeview) {
        this.treeview = treeview;
    }

    public Label getBack() {
        return back;
    }

    public void setBack(Label back) {
        this.back = back;
    }

    public Label getSection_name() {
        return section_name;
    }

    public void setSection_name(Label section_name) {
        this.section_name = section_name;
    }

    public void setdLabelText(String text){
        this.section_name.setText(text);
    }

    public TreeItem<DatabaseRecord> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<DatabaseRecord> root) {
        this.root = root;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}


