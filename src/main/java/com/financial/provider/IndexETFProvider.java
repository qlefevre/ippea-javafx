package com.financial.provider;

import com.financial.model.Row;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class IndexETFProvider implements  ITickerProvider{

    public static final IndexETFProvider INSTANCE = new IndexETFProvider();

    private final Map<String,String> indexMap = new HashMap<>();


    private IndexETFProvider() {
        loadData();
    }

    private void loadData() {
        try {
            try(InputStream is = EuronextETFProvider.class.getResourceAsStream("/data/index_etf.csv")){
                List<String> lines = new BufferedReader(new InputStreamReader(is))
                        .lines().collect(Collectors.toList());
                lines.stream().map(line -> line.split(";"))
                        .forEach(data -> {
                            indexMap.put(data[0],data[1]);
                        });

            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public String getTicker(Row pRow) {
        return indexMap.get(pRow.getIsin());
    }
}
