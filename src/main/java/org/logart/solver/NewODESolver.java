package org.logart.solver;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateVectorialFunction;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.logart.model.Point;
import org.logart.model.XYSeries;

public class NewODESolver extends Solver {
    private double epsilon = 0.75;
    double omega = getStep() / (getStep() + epsilon);

    public NewODESolver(MultivariateVectorialFunction multivariateMatrixFunction) {
        super(multivariateMatrixFunction);
    }

    @Override
    public XYSeries solve() throws FunctionEvaluationException {
        XYSeries result = new XYSeries("new method");
//        for (int k = 0; k < getIterationCount(); ++k) {
        RealMatrix Xn = new Array2DRowRealMatrix(new double[]{0.4, 0.4});
        RealMatrix Zn = new Array2DRowRealMatrix(getFunction().value(Xn.getColumn(0))).scalarMultiply(getStep());
        int i = 0;
        while (i < 13800/*getIterationCount()*/) {
            long start = System.currentTimeMillis();

            Array2DRowRealMatrix valueOfFunctionInPoint = new Array2DRowRealMatrix(getFunction().value(Xn.getColumn(0)));
            RealMatrix valueOfFunctionInPointWithZn = (valueOfFunctionInPoint.scalarMultiply(epsilon)).add(Zn);
            RealMatrix Znext = valueOfFunctionInPointWithZn.scalarMultiply(omega);
            RealMatrix Xnext = Xn.add(Znext);

            Zn = Znext;
            Xn = Xnext;
            ++i;

            long executionTime = System.currentTimeMillis() - start;
            addGlobalExecutionTime(executionTime);

            if (Double.isNaN(Xn.getColumn(0)[0]) || Double.isNaN(Xn.getColumn(0)[1])) {
                System.out.println("NaN on " + i + " iteration!");
                return result;
            }

            result.add(new Point(Xn.getColumn(0)[0], Xn.getColumn(0)[1]));
            for (double v : Xn.getColumn(0)) {
                System.out.print(v + " ");
            }
            System.out.println("\n");
        }
//        }
        System.out.println("finish new solver");
        return result;
    }
}
