package org.logart.solver;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateVectorialFunction;
import org.logart.model.Point;
import org.logart.model.XYSeries;

public class RungeKuttaSolver extends Solver {


    public RungeKuttaSolver(MultivariateVectorialFunction multivariateMatrixFunction) {
        super(multivariateMatrixFunction);
    }

    @Override
    public XYSeries solve() throws FunctionEvaluationException {
        int k = 2;
        double X10, X11, X20, X21;
        double k1, k2, k3, k4;
        double q1, q2, q3, q4;

        X10 = 0.4;
        X20 = 0.4;

        XYSeries result = new XYSeries();
        int i = 0;
        while (i < 100) {
            ++i;
            k1 = getStep() * f(X10, X20);
            q1 = getStep() * g(X10, X20);

            k2 = getStep() * f(X10 + getStep() / 2.0, X20 + k1 / 2.0);
            q2 = getStep() * g(X10 + getStep() / 2.0, X20 + q1 / 2.0);

            k3 = getStep() * f(X10 + getStep() / 2.0, X20 + k2 / 2.0);
            q3 = getStep() * g(X10 + getStep() / 2.0, X20 + q2 / 2.0);

            k4 = getStep() * f(X10 + getStep(), X20 + k3);
            q4 = getStep() * g(X10 + getStep(), X20 + q3);

            X11 = X10 + (k1 + 2.0 * k2 + 2.0 * k3 + k4) / 6.0;
            X21 = X20 + (q1 + 2.0 * q2 + 2.0 * q3 + q4) / 6.0;
            result.add(new Point(r(X10, k), r(X20, k)));
            System.out.println("\t" + r(X10, k) + "\t\t" + r(X20, k));
            X10 = X11;
            X20 = X21;
        }
        return result;
    }

    private static double r(double value, int k) {
        return (double) Math.round((Math.pow(10, k) * value)) / Math.pow(10, k);
    }

    private static double f(double x, double y) {
        return (x * x) - y + 1;
    }

    private static double g(double x, double y) {
        return x - Math.cos(3.14 * y / 2.0);
    }
}