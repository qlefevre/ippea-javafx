package com.financial.control;

import com.financial.controller.BrowserController;
import com.financial.controller.PortfolioController;
import com.financial.model.CsvFile;
import com.financial.model.Row;
import javafx.scene.control.TableCell;

public class OpenLinkTableCell extends TableCell<Row,String> {

    @Override
    protected void updateItem(String text, boolean empty) {
        super.updateItem(text, empty);
        setText(text);
        setOnMouseClicked((event) -> {
            BrowserController.getInstance().loadTicker(getTableRow().getItem());
        });
    }
}
