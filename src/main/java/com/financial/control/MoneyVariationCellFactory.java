package com.financial.control;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class MoneyVariationCellFactory implements Callback<TableColumn, TableCell> {

    public static final MoneyVariationCellFactory INSTANCE = new MoneyVariationCellFactory();

    @Override
    public TableCell call(TableColumn param) {
        return new CustomTableCell(true,false);
    }
}
