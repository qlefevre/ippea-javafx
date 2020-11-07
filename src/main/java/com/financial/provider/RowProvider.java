package com.financial.provider;

import com.financial.model.Row;

public class RowProvider {

    private static final RowProvider INSTANCE = new RowProvider();

    private RowProvider() {
    }

    public static Row fillRow(Row pRow){
        pRow.setIssuer(EuronextETFProvider.INSTANCE.getIssuer(pRow));
        pRow.setTicker(EuronextETFProvider.INSTANCE.getTicker(pRow));
        pRow.setIndex(IndexETFProvider.INSTANCE.getTicker(pRow));
        return pRow;
    }
}
