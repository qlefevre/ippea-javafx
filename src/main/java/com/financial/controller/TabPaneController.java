package com.financial.controller;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabPaneController {

    private static final TabPaneController INSTANCE = new TabPaneController();



    private TabPane tabPane;

    private TabPaneController() {
    }

    public static TabPaneController getInstance() {
        return INSTANCE;
    }
    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void changeTab( String tabName){
        Tab tab = tabPane.getTabs().stream().filter(tabEl -> tabName.equals(tabEl.getId())).findAny().get();
        tabPane.getSelectionModel().select(tab);
    }

}
