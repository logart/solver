package org.logart.solver;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateVectorialFunction;
import org.logart.model.XYSeries;

public abstract class Solver {
    private final MultivariateVectorialFunction G;
    private double step = 0.01;

    public Solver(MultivariateVectorialFunction multivariateMatrixFunction) {
        G = multivariateMatrixFunction;
    }

    public abstract XYSeries solve() throws FunctionEvaluationException;

    public MultivariateVectorialFunction getG() {
        return G;
    }

    public double getStep() {
        return step;
    }
}
