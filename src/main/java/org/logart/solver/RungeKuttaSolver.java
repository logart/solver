package org.logart.solver;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateVectorialFunction;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.logart.model.Point;
import org.logart.model.XYSeries;

public class RungeKuttaSolver extends Solver {


    public RungeKuttaSolver(MultivariateVectorialFunction multivariateMatrixFunction) {
        super(multivariateMatrixFunction);
    }

    @Override
    public XYSeries solve() throws FunctionEvaluationException {
        XYSeries result = new XYSeries("R-K method");
//        k1 = getFunction().value()

        RealMatrix yNext, k1, k2, k3, k4;
//        for (int k = 0; k < getIterationCount(); ++k) {

        int i = 0;
        yNext = new Array2DRowRealMatrix(new double[]{0.4, 0.4});
        while (i < /*getIterationCount()*/10300) {
            ++i;
            long start = System.currentTimeMillis();
            k1 = new Array2DRowRealMatrix(getFunction().value(yNext.getColumn(0))).scalarMultiply(getStep());
            k2 = new Array2DRowRealMatrix(getFunction().value(yNext.add(k1.scalarMultiply(0.5)).getColumn(0))).scalarMultiply(getStep());
            k3 = new Array2DRowRealMatrix(getFunction().value(yNext.add(k2.scalarMultiply(0.5)).getColumn(0))).scalarMultiply(getStep());
            k4 = new Array2DRowRealMatrix(getFunction().value(yNext.add(k3).getColumn(0))).scalarMultiply(getStep());

            yNext = yNext.add(k1.add(k2).add(k3).add(k4).scalarMultiply(0.3333333333333333333333333333333333333333));
            addGlobalExecutionTime(System.currentTimeMillis() - start);
            if (Double.isNaN(yNext.getColumn(0)[0]) || Double.isNaN(yNext.getColumn(0)[1])) {
                System.out.println("NaN on " + i + " iteration!");
                return result;
            }
            result.add(new Point(yNext.getColumn(0)[0], yNext.getColumn(0)[1]));
            System.out.println("\t" + yNext.getColumn(0)[0] + "\t\t" + yNext.getColumn(0)[1]);
        }
//        }
        return result;
    }
}