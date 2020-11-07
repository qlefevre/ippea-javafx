package com.financial.control;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class MoneyCellFactory implements Callback<TableColumn, TableCell> {

    public static final MoneyCellFactory INSTANCE = new MoneyCellFactory();

    @Override
    public TableCell call(TableColumn param) {
        return new CustomTableCell();
    }
}
