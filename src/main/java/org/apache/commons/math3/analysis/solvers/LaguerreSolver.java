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
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/**
 * Implements the <a href="http://mathworld.wolfram.com/LaguerresMethod.html">
 * Laguerre's Method</a> for root finding of real coefficient polynomials.
 * For reference, see
 * <blockquote>
 *  <b>A First Course in Numerical Analysis</b>,
 *  ISBN 048641454X, chapter 8.
 * </blockquote>
 * Laguerre's method is global in the sense that it can start with any initial
 * approximation and be able to solve all roots from that point.
 * The algorithm requires a bracketing condition.
 *
 * @since 1.2
 */
public class LaguerreSolver extends AbstractPolynomialSolver {

    /**
     * Default absolute accuracy.
     */
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Complex solver.
     */
    private final ComplexSolver complexSolver = new ComplexSolver();

    /**
     * Construct a solver with default accuracy (1e-6).
     */
    public LaguerreSolver() {
        this(DEFAULT_ABSOLUTE_ACCURACY);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     */
    public LaguerreSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     */
    public LaguerreSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     * @param functionValueAccuracy Function value accuracy.
     */
    public LaguerreSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Find a real root in the given interval.
     *
     * Despite the bracketing condition, the root returned by
     * {@link LaguerreSolver.ComplexSolver#solve(Complex[],Complex)} may
     * not be a real zero inside {@code [min, max]}.
     * For example, <code> p(x) = x<sup>3</sup> + 1, </code>
     * with {@code min = -2}, {@code max = 2}, {@code initial = 0}.
     * When it occurs, this code calls
     * {@link LaguerreSolver.ComplexSolver#solveAll(Complex[],Complex)}
     * in order to obtain all roots and picks up one real root.
     *
     * @param lo Lower bound of the search interval.
     * @param hi Higher bound of the search interval.
     * @param fLo Function value at the lower bound of the search interval.
     * @param fHi Function value at the higher bound of the search interval.
     * @return the point at which the function value is zero.
     * @deprecated This method should not be part of the public API: It will
     * be made private in version 4.0.
     */
    @Deprecated
    public double laguerre(double lo, double hi, double fLo, double fHi) {
        final Complex[] c = ComplexUtils.convertToComplex(getCoefficients());
        final Complex initial = new Complex(0.5 * (lo + hi), 0);
        final Complex z = complexSolver.solve(c, initial);
        if (complexSolver.isRoot(lo, hi, z)) {
            return z.getReal();
        } else {
            double r = Double.NaN;
            // Solve all roots and select the one we are seeking.
            Complex[] root = complexSolver.solveAll(c, initial);
            for (int i = 0; i < root.length; i++) {
                if (complexSolver.isRoot(lo, hi, root[i])) {
                    r = root[i].getReal();
                    break;
                }
            }
            return r;
        }
    }

    /**
     * Find all complex roots for the polynomial with the given
     * coefficients, starting from the given initial value.
     * <p>
     * Note: This method is not part of the API of {@link BaseUnivariateSolver}.</p>
     *
     * @param coefficients Polynomial coefficients.
     * @param initial Start value.
     * @return the full set of complex roots of the polynomial
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximum number of evaluations is exceeded when solving for one of the roots
     * @throws NullArgumentException if the {@code coefficients} is
     * {@code null}.
     * @throws NoDataException if the {@code coefficients} array is empty.
     * @since 3.1
     */
    public Complex[] solveAllComplex(double[] coefficients, double initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        // STUB: not implemented
        return null;
    }

    /**
     * Find all complex roots for the polynomial with the given
     * coefficients, starting from the given initial value.
     * <p>
     * Note: This method is not part of the API of {@link BaseUnivariateSolver}.</p>
     *
     * @param coefficients polynomial coefficients
     * @param initial start value
     * @param maxEval maximum number of evaluations
     * @return the full set of complex roots of the polynomial
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximum number of evaluations is exceeded when solving for one of the roots
     * @throws NullArgumentException if the {@code coefficients} is
     * {@code null}
     * @throws NoDataException if the {@code coefficients} array is empty
     * @since 3.5
     */
    public Complex[] solveAllComplex(double[] coefficients, double initial, int maxEval) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        // STUB: not implemented
        return null;
    }

    /**
     * Find a complex root for the polynomial with the given coefficients,
     * starting from the given initial value.
     * <p>
     * Note: This method is not part of the API of {@link BaseUnivariateSolver}.</p>
     *
     * @param coefficients Polynomial coefficients.
     * @param initial Start value.
     * @return a complex root of the polynomial
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximum number of evaluations is exceeded.
     * @throws NullArgumentException if the {@code coefficients} is
     * {@code null}.
     * @throws NoDataException if the {@code coefficients} array is empty.
     * @since 3.1
     */
    public Complex solveComplex(double[] coefficients, double initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        // STUB: not implemented
        return null;
    }

    /**
     * Find a complex root for the polynomial with the given coefficients,
     * starting from the given initial value.
     * <p>
     * Note: This method is not part of the API of {@link BaseUnivariateSolver}.</p>
     *
     * @param coefficients polynomial coefficients
     * @param initial start value
     * @param maxEval maximum number of evaluations
     * @return a complex root of the polynomial
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximum number of evaluations is exceeded
     * @throws NullArgumentException if the {@code coefficients} is
     * {@code null}
     * @throws NoDataException if the {@code coefficients} array is empty
     * @since 3.1
     */
    public Complex solveComplex(double[] coefficients, double initial, int maxEval) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        // STUB: not implemented
        return null;
    }

    /**
     * Class for searching all (complex) roots.
     */
    private class ComplexSolver {

        /**
         * Check whether the given complex root is actually a real zero
         * in the given interval, within the solver tolerance level.
         *
         * @param min Lower bound for the interval.
         * @param max Upper bound for the interval.
         * @param z Complex root.
         * @return {@code true} if z is a real zero.
         */
        public boolean isRoot(double min, double max, Complex z) {
            // STUB: not implemented
            return false;
        }

        /**
         * Find all complex roots for the polynomial with the given
         * coefficients, starting from the given initial value.
         *
         * @param coefficients Polynomial coefficients.
         * @param initial Start value.
         * @return the point at which the function value is zero.
         * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
         * if the maximum number of evaluations is exceeded.
         * @throws NullArgumentException if the {@code coefficients} is
         * {@code null}.
         * @throws NoDataException if the {@code coefficients} array is empty.
         */
        public Complex[] solveAll(Complex[] coefficients, Complex initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            // STUB: not implemented
            return null;
        }

        /**
         * Find a complex root for the polynomial with the given coefficients,
         * starting from the given initial value.
         *
         * @param coefficients Polynomial coefficients.
         * @param initial Start value.
         * @return the point at which the function value is zero.
         * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
         * if the maximum number of evaluations is exceeded.
         * @throws NullArgumentException if the {@code coefficients} is
         * {@code null}.
         * @throws NoDataException if the {@code coefficients} array is empty.
         */
        public Complex solve(Complex[] coefficients, Complex initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            // STUB: not implemented
            return null;
        }
    }
}
