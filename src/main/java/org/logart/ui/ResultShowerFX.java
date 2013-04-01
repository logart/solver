package org.logart.ui;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.logart.model.Point;
import org.logart.model.XYSeries;

/**
 * @author Artem Loginov
 */
public class ResultShowerFX extends ResultShower {

    private Stage stage;

    public ResultShowerFX(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void show() {
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        Scene scene = new Scene(lineChart, 800, 600);
        for (XYSeries serie : series) {
            lineChart.getData().add(convert(serie));
        }

        stage.setScene(scene);
        stage.show();
    }

    private XYChart.Series<Number, Number> convert(XYSeries series) {
        XYChart.Series<Number, Number> result = new XYChart.Series<Number, Number>();
        for (Point data : series) {
            result.getData().add(new XYChart.Data<Number, Number>(data.getX(), data.getY()));
        }
        return result;
    }
}
