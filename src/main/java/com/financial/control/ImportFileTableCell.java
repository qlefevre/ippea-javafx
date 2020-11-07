package com.financial.control;

import com.financial.controller.PortfolioController;
import com.financial.controller.TabPaneController;
import com.financial.model.CsvFile;
import com.financial.model.Row;
import com.financial.util.FXCollectionUtils;
import javafx.application.Platform;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.text.NumberFormat;

public class ImportFileTableCell extends TableCell<CsvFile,String> {

    @Override
    protected void updateItem(String file, boolean empty) {
        super.updateItem(file, empty);
        setText(file);
        setOnMouseClicked((event) -> {
            PortfolioController.getInstance().loadFile(getTableRow().getItem().getFile());
          TabPaneController.getInstance().changeTab("equitySavingsPlan");


        });
    }
}
