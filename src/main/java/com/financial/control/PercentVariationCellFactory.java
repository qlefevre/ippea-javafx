package com.financial.control;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class PercentVariationCellFactory implements Callback<TableColumn, TableCell> {

    public static final PercentVariationCellFactory INSTANCE = new PercentVariationCellFactory();

    @Override
    public TableCell call(TableColumn param) {
        return new CustomTableCell(true,true);
    }
}
