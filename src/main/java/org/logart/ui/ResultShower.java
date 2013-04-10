package org.logart.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.logart.model.Point;
import org.logart.model.XYSeries;

import java.util.ArrayList;
import java.util.List;

public class ResultShower {
    protected List<XYSeries> series = new ArrayList<XYSeries>();
    private final String description;

    public ResultShower(long firstMethodTime, long secondMethodTime) {
        this.description = "New method time " + firstMethodTime + " ms over " + secondMethodTime + " ms by Runge-Kutta method";
    }

    public void show() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (XYSeries serie : series) {
            dataset.addSeries(convert(serie));
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                null, null, null, dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartFrame frame = new ChartFrame(getDescription(), chart);
        frame.pack();
        frame.setVisible(true);
    }

    private org.jfree.data.xy.XYSeries convert(XYSeries series) {
        org.jfree.data.xy.XYSeries result = new org.jfree.data.xy.XYSeries(series.getName());
        for (Point data : series) {
            result.add(data.getX(), data.getY());
        }
        return result;
    }

    public void add(XYSeries data) {
        this.series.add(data);
    }

    public String getDescription() {
        return description;
    }
}
