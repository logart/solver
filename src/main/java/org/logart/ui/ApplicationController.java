package org.logart.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateVectorialFunction;
import org.logart.model.XYSeries;
import org.logart.parser.FormulaParser;
import org.logart.solver.NewODESolver;
import org.logart.solver.RungeKuttaSolver;
import org.logart.solver.Solver;

public class ApplicationController extends Application {
    private ResultShowerFX shower;
    private Solver solver1;
    private Solver solver2;
    private FormulaParser parser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FunctionEvaluationException {
        shower = new ResultShowerFX(primaryStage);
        //TODO implement
//        MultivariateVectorialFunction function = parser.parse();
//        x
        MultivariateVectorialFunction function = new MultivariateVectorialFunction() {
            @Override
            public double[] value(double[] point) throws FunctionEvaluationException, IllegalArgumentException {
                return new double[]{
                        -point[0] - point[0] * point[0] + point[0] * point[1] + 8 * point[1] * point[1],
                        point[0] - 2 * point[1] + point[0] * point[1] + point[1] * point[1]
                };
            }
        };
        solver1 = new NewODESolver(function);
        solver2 = new RungeKuttaSolver(function);
        XYSeries data1 = solver1.solve();
        XYSeries data2 = solver2.solve();
        shower.add(data1);
        shower.add(data2);
        shower.show();
    }
}