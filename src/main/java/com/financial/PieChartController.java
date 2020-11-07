package com.financial;

import com.financial.chart.DonutChart;
import com.financial.control.CustomTableView;
import com.financial.controller.BrowserController;
import com.financial.controller.LazyTargetPortfolioController;
import com.financial.controller.PortfolioController;
import com.financial.controller.TabPaneController;
import com.financial.model.CsvFile;
import com.financial.util.FXCollectionUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.financial.model.Row;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Window;


import java.io.File;
import java.util.*;
import java.util.function.Function;

public class PieChartController {


    @FXML
    private DonutChart portfolio;

    @FXML
    private DonutChart issuers;

    @FXML
    private ViewTab equitySavingsPlan;

    @FXML
    private ViewTab lazyInvesting;

    @FXML
    private CustomTableView stockPurchase;

    @FXML
    private ViewTab valueInvesting;

    @FXML
    private CustomTableView imports;

    @FXML
    private TableColumn importFile;

    @FXML
    private WebView browser;

    @FXML
    private TabPane tabPane;


    @FXML
    private void initialize() {

        ObservableList<Row> portfolio = PortfolioController.getInstance().getPortfolio();

        equitySavingsPlan.setData(portfolio);
        ObservableList<Row> lazyData = portfolio.filtered(row -> LazyTargetPortfolioController.getIndexList().contains(row.getIndex()));
        lazyInvesting.setData(lazyData);
        ObservableList<Row> valueData = portfolio.filtered(row -> !LazyTargetPortfolioController.getIndexList().contains(row.getIndex()));
        valueInvesting.setData(valueData);

        ObservableList<Row> stocksPurchaseData = computeSharesToBuy(lazyData);
        stockPurchase.setCustomItems(stocksPurchaseData);

        imports.setCustomItems(PortfolioController.getInstance().getCsvFiles());

        PortfolioController.getInstance().initializeDev();

        browser.getEngine().load("https://boursorama.com");
        BrowserController browserCOntroller = BrowserController.getInstance();
        browserCOntroller.setWebEngine(browser.getEngine());
        TabPaneController.getInstance().setTabPane(tabPane);
    }


    private ObservableList<Row> computeSharesToBuy(ObservableList<Row> lazyData) {
        Map<String, Integer> target = LazyTargetPortfolioController.getIndexMap();
        double total = 200;
        ObservableList<Row> sharesToBuy = FXCollectionUtils.map(lazyData,
                vRow -> {
                    Row vNewRow = new Row(vRow);
                    vNewRow.setQuantity((int) (total * ((double) target.get(vRow.getIndex()) / 100) / vRow.getLastPrice()));
                    return vNewRow;
                });
        return sharesToBuy;
    }

    @FXML
    private void addElement(ActionEvent actionEvent) {

        final FileChooser fileChooser = new FileChooser();
        Node source = (Node) actionEvent.getSource();
        Window stage = source.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            PortfolioController.getInstance().loadFile(file);
        }
    }
}
