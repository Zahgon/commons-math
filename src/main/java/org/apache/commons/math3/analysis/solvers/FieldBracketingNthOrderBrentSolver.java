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

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.IntegerSequence;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/**
 * This class implements a modification of the <a
 * href="http://mathworld.wolfram.com/BrentsMethod.html"> Brent algorithm</a>.
 * <p>
 * The changes with respect to the original Brent algorithm are:
 * <ul>
 *   <li>the returned value is chosen in the current interval according
 *   to user specified {@link AllowedSolution}</li>
 *   <li>the maximal order for the invert polynomial root search is
 *   user-specified instead of being invert quadratic only</li>
 * </ul><p>
 * The given interval must bracket the root.</p>
 *
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class FieldBracketingNthOrderBrentSolver<T extends RealFieldElement<T>> implements BracketedRealFieldUnivariateSolver<T> {

    /**
     * Maximal aging triggering an attempt to balance the bracketing interval.
     */
    private static final int MAXIMAL_AGING = 2;

    /**
     * Field to which the elements belong.
     */
    private final Field<T> field;

    /**
     * Maximal order.
     */
    private final int maximalOrder;

    /**
     * Function value accuracy.
     */
    private final T functionValueAccuracy;

    /**
     * Absolute accuracy.
     */
    private final T absoluteAccuracy;

    /**
     * Relative accuracy.
     */
    private final T relativeAccuracy;

    /**
     * Evaluations counter.
     */
    private IntegerSequence.Incrementor evaluations;

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     * @param functionValueAccuracy Function value accuracy.
     * @param maximalOrder maximal order.
     * @exception NumberIsTooSmallException if maximal order is lower than 2
     */
    public FieldBracketingNthOrderBrentSolver(final T relativeAccuracy, final T absoluteAccuracy, final T functionValueAccuracy, final int maximalOrder) throws NumberIsTooSmallException {
        if (maximalOrder < 2) {
            throw new NumberIsTooSmallException(maximalOrder, 2, true);
        }
        this.field = relativeAccuracy.getField();
        this.maximalOrder = maximalOrder;
        this.absoluteAccuracy = absoluteAccuracy;
        this.relativeAccuracy = relativeAccuracy;
        this.functionValueAccuracy = functionValueAccuracy;
        this.evaluations = IntegerSequence.Incrementor.create();
    }

    /**
     * Get the maximal order.
     * @return maximal order
     */
    public int getMaximalOrder() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Get the maximal number of function evaluations.
     *
     * @return the maximal number of function evaluations.
     */
    public int getMaxEvaluations() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Get the number of evaluations of the objective function.
     * The number of evaluations corresponds to the last call to the
     * {@code optimize} method. It is 0 if the method has not been
     * called yet.
     *
     * @return the number of evaluations of the objective function.
     */
    public int getEvaluations() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Get the absolute accuracy.
     * @return absolute accuracy
     */
    public T getAbsoluteAccuracy() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the relative accuracy.
     * @return relative accuracy
     */
    public T getRelativeAccuracy() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the function accuracy.
     * @return function accuracy
     */
    public T getFunctionValueAccuracy() {
        // STUB: not implemented
        return null;
    }

    /**
     * Solve for a zero in the given interval.
     * A solver may require that the interval brackets a single zero root.
     * Solvers that do require bracketing should be able to handle the case
     * where one of the endpoints is itself a root.
     *
     * @param maxEval Maximum number of evaluations.
     * @param f Function to solve.
     * @param min Lower bound for the interval.
     * @param max Upper bound for the interval.
     * @param allowedSolution The kind of solutions that the root-finding algorithm may
     * accept as solutions.
     * @return a value where the function is zero.
     * @exception NullArgumentException if f is null.
     * @exception NoBracketingException if root cannot be bracketed
     */
    public T solve(final int maxEval, final RealFieldUnivariateFunction<T> f, final T min, final T max, final AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        // STUB: not implemented
        return null;
    }

    /**
     * Solve for a zero in the given interval, start at {@code startValue}.
     * A solver may require that the interval brackets a single zero root.
     * Solvers that do require bracketing should be able to handle the case
     * where one of the endpoints is itself a root.
     *
     * @param maxEval Maximum number of evaluations.
     * @param f Function to solve.
     * @param min Lower bound for the interval.
     * @param max Upper bound for the interval.
     * @param startValue Start value to use.
     * @param allowedSolution The kind of solutions that the root-finding algorithm may
     * accept as solutions.
     * @return a value where the function is zero.
     * @exception NullArgumentException if f is null.
     * @exception NoBracketingException if root cannot be bracketed
     */
    public T solve(final int maxEval, final RealFieldUnivariateFunction<T> f, final T min, final T max, final T startValue, final AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        // STUB: not implemented
        return null;
    }

    /**
     * Guess an x value by n<sup>th</sup> order inverse polynomial interpolation.
     * <p>
     * The x value is guessed by evaluating polynomial Q(y) at y = targetY, where Q
     * is built such that for all considered points (x<sub>i</sub>, y<sub>i</sub>),
     * Q(y<sub>i</sub>) = x<sub>i</sub>.
     * </p>
     * @param targetY target value for y
     * @param x reference points abscissas for interpolation,
     * note that this array <em>is</em> modified during computation
     * @param y reference points ordinates for interpolation
     * @param start start index of the points to consider (inclusive)
     * @param end end index of the points to consider (exclusive)
     * @return guessed root (will be a NaN if two points share the same y)
     */
    private T guessX(final T targetY, final T[] x, final T[] y, final int start, final int end) {
        // compute Q Newton coefficients by divided differences
        for (int i = start; i < end - 1; ++i) {
            final int delta = i + 1 - start;
            for (int j = end - 1; j > i; --j) {
                x[j] = x[j].subtract(x[j - 1]).divide(y[j].subtract(y[j - delta]));
            }
        }
        // evaluate Q(targetY)
        T x0 = field.getZero();
        for (int j = end - 1; j >= start; --j) {
            x0 = x[j].add(x0.multiply(targetY.subtract(y[j])));
        }
        return x0;
    }
}
