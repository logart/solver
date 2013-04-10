package org.logart.solver;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateVectorialFunction;
import org.logart.model.XYSeries;

public abstract class Solver {
    private final MultivariateVectorialFunction G;
    private double step = 0.0001;
    private long globalExecutionTime = 0;

    public Solver(MultivariateVectorialFunction multivariateMatrixFunction) {
        G = multivariateMatrixFunction;
    }

    public abstract XYSeries solve() throws FunctionEvaluationException;

    public MultivariateVectorialFunction getFunction() {
        return G;
    }

    public double getStep() {
        return step;
    }

    public long getExecutionTime() {
        return globalExecutionTime;
    }

    protected void addGlobalExecutionTime(long executionTime) {
        globalExecutionTime += executionTime;
    }

    protected int getIterationCount() {
        return 103500;
    }
}
