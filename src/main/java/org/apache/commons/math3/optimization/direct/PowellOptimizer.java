/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.univariate.BracketFinder;
import org.apache.commons.math3.optimization.univariate.BrentOptimizer;
import org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker;

/**
 * Powell algorithm.
 * This code is translated and adapted from the Python version of this
 * algorithm (as implemented in module {@code optimize.py} v0.5 of
 * <em>SciPy</em>).
 * <br/>
 * The default stopping criterion is based on the differences of the
 * function value between two successive iterations. It is however possible
 * to define a custom convergence checker that might terminate the algorithm
 * earlier.
 * <br/>
 * The internal line search optimizer is a {@link BrentOptimizer} with a
 * convergence checker set to {@link SimpleUnivariateValueChecker}.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.2
 */
@Deprecated
public class PowellOptimizer extends BaseAbstractMultivariateOptimizer<MultivariateFunction> implements MultivariateOptimizer {

    /**
     * Minimum relative tolerance.
     */
    private static final double MIN_RELATIVE_TOLERANCE = 2 * FastMath.ulp(1d);

    /**
     * Relative threshold.
     */
    private final double relativeThreshold;

    /**
     * Absolute threshold.
     */
    private final double absoluteThreshold;

    /**
     * Line search.
     */
    private final LineSearch line;

    /**
     * This constructor allows to specify a user-defined convergence checker,
     * in addition to the parameters that control the default convergence
     * checking procedure.
     * <br/>
     * The internal line search tolerances are set to the square-root of their
     * corresponding value in the multivariate optimizer.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @param checker Convergence checker.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public PowellOptimizer(double rel, double abs, ConvergenceChecker<PointValuePair> checker) {
        this(rel, abs, FastMath.sqrt(rel), FastMath.sqrt(abs), checker);
    }

    /**
     * This constructor allows to specify a user-defined convergence checker,
     * in addition to the parameters that control the default convergence
     * checking procedure and the line search tolerances.
     *
     * @param rel Relative threshold for this optimizer.
     * @param abs Absolute threshold for this optimizer.
     * @param lineRel Relative threshold for the internal line search optimizer.
     * @param lineAbs Absolute threshold for the internal line search optimizer.
     * @param checker Convergence checker.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public PowellOptimizer(double rel, double abs, double lineRel, double lineAbs, ConvergenceChecker<PointValuePair> checker) {
        super(checker);
        if (rel < MIN_RELATIVE_TOLERANCE) {
            throw new NumberIsTooSmallException(rel, MIN_RELATIVE_TOLERANCE, true);
        }
        if (abs <= 0) {
            throw new NotStrictlyPositiveException(abs);
        }
        relativeThreshold = rel;
        absoluteThreshold = abs;
        // Create the line search optimizer.
        line = new LineSearch(lineRel, lineAbs);
    }

    /**
     * The parameters control the default convergence checking procedure.
     * <br/>
     * The internal line search tolerances are set to the square-root of their
     * corresponding value in the multivariate optimizer.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public PowellOptimizer(double rel, double abs) {
        this(rel, abs, null);
    }

    /**
     * Builds an instance with the default convergence checking procedure.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @param lineRel Relative threshold for the internal line search optimizer.
     * @param lineAbs Absolute threshold for the internal line search optimizer.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     * @since 3.1
     */
    public PowellOptimizer(double rel, double abs, double lineRel, double lineAbs) {
        this(rel, abs, lineRel, lineAbs, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PointValuePair doOptimize() {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute a new point (in the original space) and a new direction
     * vector, resulting from the line search.
     *
     * @param p Point used in the line search.
     * @param d Direction used in the line search.
     * @param optimum Optimum found by the line search.
     * @return a 2-element array containing the new point (at index 0) and
     * the new direction (at index 1).
     */
    private double[][] newPointAndDirection(double[] p, double[] d, double optimum) {
        final int n = p.length;
        final double[] nP = new double[n];
        final double[] nD = new double[n];
        for (int i = 0; i < n; i++) {
            nD[i] = d[i] * optimum;
            nP[i] = p[i] + nD[i];
        }
        final double[][] result = new double[2][];
        result[0] = nP;
        result[1] = nD;
        return result;
    }

    /**
     * Class for finding the minimum of the objective function along a given
     * direction.
     */
    private class LineSearch extends BrentOptimizer {

        /**
         * Value that will pass the precondition check for {@link BrentOptimizer}
         * but will not pass the convergence check, so that the custom checker
         * will always decide when to stop the line search.
         */
        private static final double REL_TOL_UNUSED = 1e-15;

        /**
         * Value that will pass the precondition check for {@link BrentOptimizer}
         * but will not pass the convergence check, so that the custom checker
         * will always decide when to stop the line search.
         */
        private static final double ABS_TOL_UNUSED = Double.MIN_VALUE;

        /**
         * Automatic bracketing.
         */
        private final BracketFinder bracket = new BracketFinder();

        /**
         * The "BrentOptimizer" default stopping criterion uses the tolerances
         * to check the domain (point) values, not the function values.
         * We thus create a custom checker to use function values.
         *
         * @param rel Relative threshold.
         * @param abs Absolute threshold.
         */
        LineSearch(double rel, double abs) {
            super(REL_TOL_UNUSED, ABS_TOL_UNUSED, new SimpleUnivariateValueChecker(rel, abs));
        }

        /**
         * Find the minimum of the function {@code f(p + alpha * d)}.
         *
         * @param p Starting point.
         * @param d Search direction.
         * @return the optimum.
         * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
         * if the number of evaluations is exceeded.
         */
        public UnivariatePointValuePair search(final double[] p, final double[] d) {
            // STUB: not implemented
            return null;
        }
    }
}
