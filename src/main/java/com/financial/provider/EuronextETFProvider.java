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

import static com.financial.util.ModelUtils.unescape;

public class EuronextETFProvider implements  IIssuerProvider, ITickerProvider{

    public static final EuronextETFProvider INSTANCE = new EuronextETFProvider();

    private final Map<String,String> issuerMap = new HashMap<>();
    private final Map<String,String> tickerMap = new HashMap<>();

    private EuronextETFProvider() {
        loadData();
    }

    private void loadData() {
        try {
            try(InputStream is = EuronextETFProvider.class.getResourceAsStream("/data/euronext_etf.csv")){
                List<String> lines = new BufferedReader(new InputStreamReader(is))
                        .lines().collect(Collectors.toList());
                lines.stream().skip(4).map(line -> line.split(";"))
                        .forEach(data -> {
                            issuerMap.put(data[1],extractIssuer(data[0]));
                            tickerMap.put(data[1],data[2]);
                        });

            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private String extractIssuer(String name){
        name = unescape(name.substring(0,name.indexOf(' '))).toUpperCase();
        switch (name){
            case "AMUNDI":
            case "AM":
            return "Amundi";
            case "BNP":
            case "BNPP":
            return "BNP Paribas";
            case "LYXOR" :
            return "Lyxor";
            default :
                return "Inconnu";
        }
    }

    @Override
    public String getIssuer(Row pRow) {
        return issuerMap.get(pRow.getIsin());
    }

    @Override
    public String getTicker(Row pRow) {
        return tickerMap.get(pRow.getIsin());
    }
}
