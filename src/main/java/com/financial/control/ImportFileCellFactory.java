package com.financial.control;

import com.financial.model.Row;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ImportFileCellFactory
 implements Callback<TableColumn, TableCell> {

    public static final ImportFileCellFactory INSTANCE = new ImportFileCellFactory();

    @Override
    public TableCell call(TableColumn param) {
        return new ImportFileTableCell();
    }
}