package com.financial.chart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.lang.reflect.Field;

public class DonutChart extends PieChart {
    private final Circle innerCircle;

    /**
     * Construct a new empty DoughnutChart.
     */
    public DonutChart() {
        this(FXCollections.<Data>observableArrayList());
    }

    public DonutChart(ObservableList<Data> pieData) {
        super(pieData);

        innerCircle = new Circle();

        // just styled in code for demo purposes,
        // use a style class instead to style via css.
        innerCircle.setStroke(Color.WHITESMOKE);
        innerCircle.setStrokeWidth(2);
        innerCircle.getStyleClass().add("donut-background");
    }

    @Override
    protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
        super.layoutChartChildren(top, left, contentWidth, contentHeight);
        addInnerCircleIfNotPresent();
        updateInnerCircleLayout( top,  left,  contentWidth,  contentHeight);
    }


    private void addInnerCircleIfNotPresent() {
        if (getData().size() > 0) {
            Node pie = getData().get(0).getNode();
            if (pie.getParent() instanceof Pane) {
                Pane parent = (Pane) pie.getParent();
                    parent.getChildren().remove(innerCircle);
                    parent.getChildren().add(innerCircle);
            }
        }
    }

    private void updateInnerCircleLayout(double top, double left, double contentWidth, double contentHeight) {
        double centerX = contentWidth / 2 + left;
        double centerY = contentHeight / 2 + top;
        innerCircle.setCenterX(centerX);
        innerCircle.setCenterY(centerY);
        //innerCircle.setRadius(Math.min(contentWidth,contentHeight)/2);
        innerCircle.setRadius( contentHeight/4);
        getData().stream().forEach(data -> {
            Tooltip tooltip = new Tooltip();
            tooltip.setShowDelay(new Duration(0));
            tooltip.setText(data.getName()+"\n"+(int)data.getPieValue() + "%");
            Tooltip.install(data.getNode(), tooltip);
        });
    }

}
