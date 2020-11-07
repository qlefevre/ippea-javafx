package com.financial.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.*;

public class LazyTargetPortfolioController {

    private static final Map<String, Integer> TARGET = new LinkedHashMap<>();

    static {
        TARGET.put("STOXX Europe 600", 32);
        TARGET.put("S&P 500", 13);
        TARGET.put("TOPIX", 16);
        TARGET.put("MSCI AC Asia Pacific ex Japan", 16);
        TARGET.put("MSCI Emerging Markets", 23);
    }

    public static List<String> getIndexList() {
        return new ArrayList(TARGET.keySet());
    }

    public static Map<String,Integer> getIndexMap() {
        return TARGET;
    }

    public static ObservableList<PieChart.Data> getIndexChartData(){
        ObservableList<PieChart.Data> result = FXCollections.observableArrayList();
        TARGET.forEach((key,value)-> result.add(new PieChart.Data(key,value)));
        return result;
    }
}
