package com.financial.control;

import com.financial.model.Row;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;


import java.text.NumberFormat;

public class CustomTableCell extends TableCell<Row,Double> {

    private final NumberFormat numberFormat;
    private final boolean handleVariation;

    public CustomTableCell(){
        this(false,false);
    }

    public CustomTableCell(boolean handleVariation, boolean handlePercent) {
        this.handleVariation = handleVariation;
        if(handlePercent) {
            this.numberFormat = NumberFormat.getPercentInstance() ;
            this.numberFormat.setMinimumFractionDigits(2);
        }else{
            this.numberFormat =  NumberFormat.getCurrencyInstance();
        }
    }

    @Override
    protected void updateItem(Double price, boolean empty) {
        super.updateItem(price, empty);
        if (empty || price == null) {
            setText(null);
        } else {
            setText(numberFormat.format(price));
            if(handleVariation){
                setTextFill( price < 0 ? Color.RED : Color.GREEN);
            }
        }
    }
}
