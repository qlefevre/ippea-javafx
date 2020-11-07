package com.financial.controller;

import com.financial.model.CsvFile;
import com.financial.model.Row;
import com.financial.provider.RowProvider;
import com.financial.util.FXCollectionUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.financial.util.ModelUtils.unescape;

public class PortfolioController {

    private static final PortfolioController INSTANCE = new PortfolioController();

    private final ObservableList<Row> portfolio = FXCollections.observableArrayList();

    private PortfolioController() {
    }

    public void initializeDev() {
        portfolio.add(new Row("BNPP STOXX 600C", "FR0011550193", "BNP Paribas", "STOXX Europe 600", 86, 10.36, 10.16));
        portfolio.add(new Row("AMUNDI PEA MSCI EM", "FR0013412020", "Amundi", "MSCI Emerging Markets", 28, 19.87, 20.48));
        portfolio.add(new Row("LYXOR ASIP EXJ PEA", "FR0011869312", "Lyxor", "MSCI AC Asia Pacific ex Japan", 28, 15.53, 15.97));

    }

    public static PortfolioController getInstance() {
        return INSTANCE;
    }

    public ObservableList<Row> getPortfolio() {
        return portfolio;
    }

    public void loadFile(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            List<Row> rows = lines.stream().skip(1).map(line -> line.split(";"))
                    .map(data -> new Row(unescape(data[0]), data[1], toInt(data[2]), toDouble(data[3]), toDouble(data[4])))
                    .map(row -> RowProvider.fillRow(row))
                    .collect(Collectors.toList());

            portfolio.clear();
            portfolio.addAll(rows);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ObservableList<CsvFile> getCsvFiles() {
        List<File> files = new ArrayList<>();
        String vPath = System.getProperty("user.home") + "/Desktop";
        files.addAll(Arrays.asList(new File(vPath).listFiles()));

        String vDownloads = System.getProperty("user.home") + "/Downloads";
        files.addAll(Arrays.asList(new File(vDownloads).listFiles()));

        files = files.stream().filter(vFile -> filterCsvFile(vFile)).collect(Collectors.toList());

        ObservableList<CsvFile> result = FXCollections.observableArrayList();
        files.forEach(vFile -> {
            CsvFile csvFile = new CsvFile();
            csvFile.setFile(vFile);
            csvFile.setName(vFile.getName());
            csvFile.setFolder(vFile.getParentFile().getName().equals("Desktop") ? "Bureau" : "Téléchargements");
            csvFile.updateDate(getCsvFileDate(vFile));
            result.add(csvFile);
        });
        Collections.sort(result);
        return result;
    }

    private boolean filterCsvFile(File file) {
        return file.getName().startsWith("export-positions") &&
                file.getName().endsWith(".csv");
    }

    private String getCsvFileDate(File file) {
        String name = file.getName()
                .replace("export-positions-instantanees-", "")
                .replace("export-positions-comptables-", "");
        int indexOfUnderscore = name.indexOf('_');
        String date = name.substring(0,indexOfUnderscore ).replace('-', '/');
        date += " "+name.substring(indexOfUnderscore+1,indexOfUnderscore+1+5).replace('-','h');
        return date;
    }

    private int toInt(String toInt) {
        return (int) Float.parseFloat(toInt.replace(',', '.'));
    }

    private double toDouble(String toDouble) {
        return Double.parseDouble(toDouble.replace(',', '.'));
    }
}
