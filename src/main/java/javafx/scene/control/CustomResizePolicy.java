package javafx.scene.control;

import javafx.util.Callback;

import java.util.List;

public class CustomResizePolicy implements Callback<TableView.ResizeFeatures, Boolean>{


    public static final Callback<TableView.ResizeFeatures, Boolean> CONSTRAINED_RESIZE_POLICY = new CustomResizePolicy();

    private boolean isFirstRun = true;

    @Override public String toString() {
        return "constrained-resize";
    }

    @Override public Boolean call(TableView.ResizeFeatures prop) {
        TableView<?> table = prop.getTable();
        List<? extends TableColumnBase<?,?>> visibleLeafColumns = table.getVisibleLeafColumns();
        Boolean result = TableUtil.constrainedResize(prop,
                isFirstRun,
                table.getWidth(),
                visibleLeafColumns);
        isFirstRun = ! isFirstRun ? false : ! result;
        return result;
    }
}
