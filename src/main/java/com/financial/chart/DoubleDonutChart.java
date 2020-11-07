package com.financial.chart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Set;
import java.util.stream.Collectors;

public class DoubleDonutChart extends DonutChart {
    private final DonutChart innerDonut;
    private Bounds outerPieBounds;

    /**
     * Construct a new empty DoughnutChart.
     */
    public DoubleDonutChart() {
        this(FXCollections.<Data>observableArrayList());
    }

    public DoubleDonutChart(ObservableList<Data> pieData) {
        super(pieData);

        innerDonut = new DonutChart();
        innerDonut.setLabelsVisible(false);
        innerDonut.setLegendVisible(false);

    }

    @Override
    protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
        super.layoutChartChildren(top, left, contentWidth, contentHeight);
        addInnerDonutIfNotPresent();
        updateInnerDonutLayout( top,  left,  contentWidth,  contentHeight);
    }


    private void addInnerDonutIfNotPresent() {
        if (getData().size() > 0) {
            Node pie = getData().get(0).getNode();
            if (pie.getParent() instanceof Pane) {
                Pane parent = (Pane) pie.getParent();
                parent.getChildren().remove(innerDonut);
                parent.getChildren().add(innerDonut);
            }
        }
    }

    private void updateInnerDonutLayout(double top, double left, double contentWidth, double contentHeight) {
        double centerX = contentWidth / 2 + left;
        double centerY = contentHeight / 2 + top;

        updateOuterPieBounds();
        double cX = outerPieBounds.getMinX() + (outerPieBounds.getWidth() / 2);
        double cY = outerPieBounds.getMinY() + (outerPieBounds.getHeight() / 2);
        double minOuterPieBoundsWidthHeight = Math.min(outerPieBounds.getWidth(), outerPieBounds.getHeight());
        double innerSize = minOuterPieBoundsWidthHeight +4-(minOuterPieBoundsWidthHeight /4);


        innerDonut.resize(innerSize, innerSize); // THIS WHERE YOUR ISSUE LIES. YOU NEED TO PROVIDE THE SIZE TO INNER CHART
        innerDonut.setTranslateX(cX - innerDonut.getWidth() / 2);
        innerDonut.setTranslateY(cY - innerDonut.getHeight() / 2);

    }

    public final void setInnerData(ObservableList<Data> value) { innerDonut.setData(value); }


    /**
     * Determining the outer pie visual bounds.
     */
    private void updateOuterPieBounds() {
        Pane chartContent = (Pane) lookup(".chart-content");
        Set<Node> pieNodes = chartContent.getChildren().stream().filter(node -> node.getStyleClass().contains("chart-pie")).collect(Collectors.toSet());
        double minX = getWidth();
        double minY = getHeight();
        double maxX = 0, maxY = 0;
        for (Node pie : pieNodes) {
            Bounds pieBounds = pie.getBoundsInParent();
            minX = Math.min(minX, pieBounds.getMinX());
            minY = Math.min(minY, pieBounds.getMinY());
            maxX = Math.max(maxX, pieBounds.getMaxX());
            maxY = Math.max(maxY, pieBounds.getMaxY());
        }
        outerPieBounds = new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }
}
