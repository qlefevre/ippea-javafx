package com.financial.control;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class OpenLinkCellFactory implements Callback<TableColumn, TableCell> {

    public static final OpenLinkCellFactory INSTANCE = new OpenLinkCellFactory();

    @Override
    public TableCell call(TableColumn param) {
        return new OpenLinkTableCell();
    }
}