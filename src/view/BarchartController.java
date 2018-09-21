package view;

import app.Utility;
import datamodel.BattleRecord;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class BarchartController extends AnchorPane {


    static XYChart.Series won = dodajDate("WON");
    static  XYChart.Series lost = dodajDate("LOST");
    static XYChart.Series tie = dodajDate("TIE");

    public static XYChart.Series dodajDate(String date)
    {
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName(date);
        return dataSeries;
    }

    @FXML
    private AnchorPane pane;

    @FXML
    private BarChart<String, Number> barchart;
    ObservableList<XYChart.Series<String, Number>> barchart_data;

    final CategoryAxis xAxis ;
    final NumberAxis yAxis ;

    public CategoryAxis getxAxis() {
        return xAxis;
    }

    public NumberAxis getyAxis() {
        return yAxis;
    }



    public void setWon(XYChart.Series series, int val)
    {
        series.getData().add(new XYChart.Data("WIN",val));
    }

    public void setLost(XYChart.Series series, int val)
    {
        series.getData().add(new XYChart.Data("Lost",val));
    }

    public void setTie(XYChart.Series series, int val)
    {
        series.getData().add(new XYChart.Data("Tie",val));
    }


    public void addWon(BattleRecord record)
    {
        XYChart.Data needle;

    }


    /*private void setMaxBarWidth(double maxBarWidth, double minCategoryGap){
        double barWidth=0;
        do{
            double catSpace = xAxis.getCategorySpacing();
            double avilableBarSpace = catSpace - (barchart.getCategoryGap() + barchart.getBarGap());
            barWidth = (avilableBarSpace / barchart.getData().size()) - barchart.getBarGap();
            if (barWidth >maxBarWidth){
                avilableBarSpace=(maxBarWidth + barchart.getBarGap())* barchart.getData().size();
                barchart.setCategoryGap(catSpace-avilableBarSpace-barchart.getBarGap());
            }
        } while(barWidth>maxBarWidth);

        do{
            double catSpace = xAxis.getCategorySpacing();
            double avilableBarSpace = catSpace - (minCategoryGap + barchart.getBarGap());
            barWidth = Math.min(maxBarWidth, (avilableBarSpace / barchart.getData().size()) - barchart.getBarGap());
            avilableBarSpace=(barWidth + barchart.getBarGap())* barchart.getData().size();
            barchart.setCategoryGap(catSpace-avilableBarSpace-barchart.getBarGap());
        } while(barWidth < maxBarWidth && barchart.getCategoryGap()>minCategoryGap);
    }
*/

    private void displayLabelForData(XYChart.Data<String, Number> data, Color color) {
        final Node node = data.getNode();
        final Text dataText = new Text(data.getYValue() + "");
        dataText.setFill(color);
        dataText.setStyle("-fx-font: 9 arial;");
        //dataText.setRotate(90.0);
        node.parentProperty().addListener(new ChangeListener<Parent>() {
            @Override public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
                Group parentGroup = (Group) parent;
                parentGroup.getChildren().add(dataText);
            }
        });

        node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                dataText.setLayoutX(
                        Math.round(
                                bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
                        )
                );
                dataText.setLayoutY(
                        Math.round(
                                bounds.getMinY() - dataText.prefHeight(-1) * 0.5
                        )
                );
            }
        });
    }

    public BarchartController(){
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        Utility.loadResource("/fxml/barchart.fxml",this);
        barchart_data= FXCollections.observableArrayList();

        barchart.setData(barchart_data);

        barchart.setBarGap(0.5);

        barchart_data.addAll( won , lost, tie );

        barchart.getYAxis().setTickLabelsVisible(false);
        barchart.getYAxis().setOpacity(0);

        for(int i=1; i<6; i++){
            XYChart.Data data = new XYChart.Data(String.valueOf(i) + ".01.18", 20-1);
            data.nodeProperty().addListener(new ChangeListener<Node>() {
                @Override
                public void changed(ObservableValue observable, Node oldValue, Node newValue) {
                    if (newValue != null) {
                        displayLabelForData(data,Color.rgb(243, 97, 45));
                    }
                }
            });
            won.getData().add(data);
        }



        for(int i=1; i<6; i++){
            XYChart.Data data = new XYChart.Data(String.valueOf(i) + ".01.18", 20+i*2);
            data.nodeProperty().addListener(new ChangeListener<Node>() {
                @Override
                public void changed(ObservableValue observable, Node oldValue, Node newValue) {
                    if (newValue != null) {
                        displayLabelForData(data,Color.rgb(251, 165, 27));
                    }
                }
            });
            lost.getData().add(data);
        }


        for(int i=1; i<6; i++){
            XYChart.Data data = new XYChart.Data(String.valueOf(i) + ".01.18", 20-2*i);
            data.nodeProperty().addListener(new ChangeListener<Node>() {
                @Override
                public void changed(ObservableValue observable, Node oldValue, Node newValue) {
                    if (newValue != null) {
                        displayLabelForData(data,Color.rgb(87, 183, 87));
                    }
                }
            });
            tie.getData().add(data);
        }

    }

}


