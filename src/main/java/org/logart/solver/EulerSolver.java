package org.logart.solver;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateVectorialFunction;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.logart.model.Point;
import org.logart.model.XYSeries;

/**
 * @author Artem Loginov
 */
public class EulerSolver extends Solver {

    public EulerSolver(MultivariateVectorialFunction multivariateMatrixFunction) {
        super(multivariateMatrixFunction);
    }

    @Override
    public XYSeries solve() throws FunctionEvaluationException {
        XYSeries result = new XYSeries();
        RealMatrix Xn = new Array2DRowRealMatrix(new double[]{0.0, 0.0});

        int i = 0;
        while (i < 100) {
            ++i;
            RealMatrix xNext = (new Array2DRowRealMatrix(getG().value(Xn.getColumn(0))).scalarMultiply(getStep())).add(Xn);

            result.add(new Point(Xn.getColumn(0)[0], Xn.getColumn(0)[1]));
            Xn = xNext;
            for (double v : Xn.getColumn(0)) {
                System.out.print(v + " ");
            }
            System.out.println("\n");
        }
        return result;
    }
}
