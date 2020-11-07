package com.financial.control;


import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.stream.Collectors;

public class CustomTableView<S>  extends TableView<S> {

    /**
     * Creates a default TableView control with no content.
     *
     * <p>Refer to the {@link TableView} class documentation for details on the
     * default state of other properties.
     */
    public CustomTableView() {
        this(FXCollections.<S>observableArrayList());
    }

    /**
     * Creates a TableView with the content provided in the items ObservableList.
     * This also sets up an observer such that any changes to the items list
     * will be immediately reflected in the TableView itself.
     *
     * <p>Refer to the {@link TableView} class documentation for details on the
     * default state of other properties.
     *
     * @param items The items to insert into the TableView, and the list to watch
     *          for changes (to automatically show in the TableView).
     */
    public CustomTableView(ObservableList<S> items) {
        super(items);
    }

    public void setCustomItems(ObservableList<S> items) {
        setItems(items);
        items.addListener(new ListChangeListener<S>() {
            @Override
            public void onChanged(Change<? extends S> c) {
                       setFixedCellSize(25);
        prefHeightProperty().bind(fixedCellSizeProperty().multiply(Bindings.size(getItems()).add(1.01)));
       minHeightProperty().bind(prefHeightProperty());
        maxHeightProperty().bind(prefHeightProperty());
            }
        });

    }

}
