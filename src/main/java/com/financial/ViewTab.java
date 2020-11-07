package com.financial;

import com.financial.chart.DonutChart;
import com.financial.chart.DoubleDonutChart;
import com.financial.control.CustomTableView;
import com.financial.controller.LazyTargetPortfolioController;
import com.financial.model.Row;
import com.financial.util.FXCollectionUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.financial.util.ModelUtils.defaultString;

public class ViewTab extends Tab {

    private static final String TYPE_LAZY = "lazy";
    private static final String TYPE_VALUE = "VALUE";

    private String type;

    private String color;

    private ObservableList<Row> data = FXCollections.observableArrayList();

    @FXML
    private DoubleDonutChart portfolio;

    @FXML
    private DonutChart issuers;

    @FXML
    private CustomTableView table;

    @FXML
    private VBox viewtabpane;



    private ObservableList<Node> children = FXCollections.observableArrayList();

    public ViewTab() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "viewtab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {

        viewtabpane.getChildren().addAll(children);

        ObservableList<PieChart.Data> vDataChart = FXCollectionUtils.mapPieChartData(data,
                vRow ->  new PieChart.Data(defaultString(vRow.getIndex(),"N/A"),vRow.getAmount()));

        ObservableList<PieChart.Data> vDataIssuers = FXCollectionUtils.mapPieChartData(data,
                vRow ->  new PieChart.Data(defaultString(vRow.getIssuer(),"N/A"),vRow.getAmount()));

        if(TYPE_LAZY.equals(type)){
            portfolio.setInnerData(LazyTargetPortfolioController.getIndexChartData().sorted());
            vDataChart = vDataChart.sorted();
        }
        portfolio.setData(vDataChart);
        issuers.setData(vDataIssuers);
        table.setCustomItems(data);



    }

    public ObservableList<Row> getData() {
        return data;
    }

    public void setData(ObservableList<Row> data) {
        this.data = data;
        initialize();
    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        if(color != null){
            viewtabpane.getStyleClass().add(color);
            getStyleClass().add(color);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ObservableList<Node> getChildren() {
        return children;
    }

}
