package com.financial.controller;

import com.financial.model.Row;
import com.financial.util.FXCollectionUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BrowserController {
    private static final BrowserController INSTANCE = new BrowserController();

    private WebEngine webEngine;




    private BrowserController() {
    }

    public static BrowserController getInstance() {
        return INSTANCE;
    }

    public WebEngine getWebEngine() {
        return webEngine;
    }

    public void setWebEngine(WebEngine webEngine) {
        this.webEngine = webEngine;
    }


    public void loadTicker(Row row){
        if(row.getTicker() != null ) {
            webEngine.load("https://www.boursorama.com/bourse/trackers/cours/1rT" + row.getTicker() + "/");
            TabPaneController.getInstance().changeTab("browserTab");
        }
    }
}
