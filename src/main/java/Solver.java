import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateVectorialFunction;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;

import java.util.Random;

public class Solver {
    private double epsilon = 0.75;
    private double h = 0.1;
    double omega = h / (h + epsilon);
    MultivariateVectorialFunction G;

    public Solver(MultivariateVectorialFunction multivariateMatrixFunction) {
        G = multivariateMatrixFunction;
    }


    public void solve() throws FunctionEvaluationException {
        RealMatrix Xn = new Array2DRowRealMatrix(new double[]{0.0, 0.0});
        //    Z0 = h*G(X0);
        RealMatrix Zn = new Array2DRowRealMatrix(G.value(Xn.getColumn(0))).scalarMultiply(h);
        int i = 0;
        while (i < 100) {
//      RealMatrix Xnext0 = Xn.add(Zn);

            Array2DRowRealMatrix valueOfFunctionInPoint = new Array2DRowRealMatrix(G.value(Xn.getColumn(0)));
            RealMatrix valueOfFunctionInPointWithZn = (valueOfFunctionInPoint.scalarMultiply(epsilon)).add(Zn);
            RealMatrix Znext = valueOfFunctionInPointWithZn.scalarMultiply(omega);
            RealMatrix Xnext = Xn.add(Znext);

            Zn = Znext;
            Xn = Xnext;
            ++i;

            for (double v : Xn.getColumn(0)) {
                System.out.print(v + " ");
            }
            System.out.println("\n");

        }
    }
}
