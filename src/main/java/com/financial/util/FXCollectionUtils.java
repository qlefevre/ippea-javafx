package com.financial.util;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FXCollectionUtils{

    public static <T, U> ObservableList<U> map(
            ObservableList<? extends T> sourceList,
            Function<? super T, ? extends U> mapFunction) {
        ObservableList<U> destinationList = FXCollections.observableArrayList();
        sourceList.addListener(new ListChangeListener<T>() {
            @Override
            public void onChanged(Change<? extends T> c) {
                List<U> vDestDataList = sourceList.stream().map(mapFunction).collect(Collectors.toList());
                destinationList.clear();
                destinationList.setAll(vDestDataList);
            }
        });
        return destinationList;
    }

    public static <T> ObservableList<PieChart.Data> mapPieChartData(
            ObservableList<? extends T> sourceList,
            Function<? super T, PieChart.Data> mapFunction) {
        ObservableList<PieChart.Data> destinationList = FXCollections.observableArrayList();
        sourceList.addListener(new ListChangeListener<T>() {
            @Override
            public void onChanged(Change<? extends T> c) {
                Collection<PieChart.Data> vDestDataList = sourceList.stream().map(mapFunction)
                        .collect(Collectors.toMap(PieChart.Data::getName,Function.identity(),
                                (data0,data1)-> new PieChart.Data(data0.getName(),
                                        data0.getPieValue()+data1.getPieValue()))).values();
                double total = vDestDataList.stream().mapToDouble(PieChart.Data::getPieValue).sum();
                vDestDataList.forEach(data -> data.setPieValue(Math.round(data.getPieValue()/total*100)));
                destinationList.clear();
                destinationList.addAll(vDestDataList);
            }
        });
        return destinationList;
    }




}
