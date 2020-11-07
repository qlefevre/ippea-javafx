package com.financial.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvFile implements Comparable<CsvFile>{

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH'h'mm");

    private StringProperty date = new SimpleStringProperty();
    private StringProperty folder = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private File file;
    private Date dateObj;

    public void updateDate(String date) {
        setDate(date);
        try {
            setDateObj(SDF.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getFolder() {
        return folder.get();
    }

    public StringProperty folderProperty() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder.set(folder);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Date getDateObj() {
        return dateObj;
    }

    public void setDateObj(Date dateObj) {
        this.dateObj = dateObj;
    }

    @Override
    public int compareTo(CsvFile csvFile) {
        return dateObj.compareTo(csvFile.dateObj)*-1;
    }


}
