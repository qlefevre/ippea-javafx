package com.financial.provider;

import com.financial.model.Row;

public interface ITickerProvider {

    public String getTicker(Row pRow);

}
