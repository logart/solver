import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateVectorialFunction;


public class SolverTest {

  @org.junit.Test
  public void testSimpleEquation() throws Exception {

    Solver solver = new Solver(new MultivariateVectorialFunction() {
      @Override
      public double[] value(double[] point) throws FunctionEvaluationException, IllegalArgumentException {
        return new double[]{point[0] * point[0] - point[1] + 1, point[0] - Math.cos(3.14*point[1]/2.0)};
      }
    });
    solver.solve();


  }
}
