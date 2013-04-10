package org.logart.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Artem Loginov
 */
public class XYSeries implements Iterable<Point> {
    private String name;

    public XYSeries(String name) {
        this.name = name;
    }

    private List<Point> content = new ArrayList<Point>();

    @Override
    public Iterator<Point> iterator() {
        return content.iterator();
    }

    public void add(Point point) {
        content.add(point);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder("[");
        for (Point point : content) {
            buffer.append(point).append(",");
        }
        buffer.append("]");
        return buffer.toString();
    }

    public String getName() {
        return name;
    }
}
