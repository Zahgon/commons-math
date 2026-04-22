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

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.IntegerSequence;
import org.apache.commons.math3.util.MathUtils;

/**
 * Provide a default implementation for several functions useful to generic
 * solvers.
 * The default values for relative and function tolerances are 1e-14
 * and 1e-15, respectively. It is however highly recommended to not
 * rely on the default, but rather carefully consider values that match
 * user's expectations, as well as the specifics of each implementation.
 *
 * @param <FUNC> Type of function to solve.
 *
 * @since 2.0
 */
public abstract class BaseAbstractUnivariateSolver<FUNC extends UnivariateFunction> implements BaseUnivariateSolver<FUNC> {

    /**
     * Default relative accuracy.
     */
    private static final double DEFAULT_RELATIVE_ACCURACY = 1e-14;

    /**
     * Default function value accuracy.
     */
    private static final double DEFAULT_FUNCTION_VALUE_ACCURACY = 1e-15;

    /**
     * Function value accuracy.
     */
    private final double functionValueAccuracy;

    /**
     * Absolute accuracy.
     */
    private final double absoluteAccuracy;

    /**
     * Relative accuracy.
     */
    private final double relativeAccuracy;

    /**
     * Evaluations counter.
     */
    private IntegerSequence.Incrementor evaluations;

    /**
     * Lower end of search interval.
     */
    private double searchMin;

    /**
     * Higher end of search interval.
     */
    private double searchMax;

    /**
     * Initial guess.
     */
    private double searchStart;

    /**
     * Function to solve.
     */
    private FUNC function;

    /**
     * Construct a solver with given absolute accuracy.
     *
     * @param absoluteAccuracy Maximum absolute error.
     */
    protected BaseAbstractUnivariateSolver(final double absoluteAccuracy) {
        this(DEFAULT_RELATIVE_ACCURACY, absoluteAccuracy, DEFAULT_FUNCTION_VALUE_ACCURACY);
    }

    /**
     * Construct a solver with given accuracies.
     *
     * @param relativeAccuracy Maximum relative error.
     * @param absoluteAccuracy Maximum absolute error.
     */
    protected BaseAbstractUnivariateSolver(final double relativeAccuracy, final double absoluteAccuracy) {
        this(relativeAccuracy, absoluteAccuracy, DEFAULT_FUNCTION_VALUE_ACCURACY);
    }

    /**
     * Construct a solver with given accuracies.
     *
     * @param relativeAccuracy Maximum relative error.
     * @param absoluteAccuracy Maximum absolute error.
     * @param functionValueAccuracy Maximum function value error.
     */
    protected BaseAbstractUnivariateSolver(final double relativeAccuracy, final double absoluteAccuracy, final double functionValueAccuracy) {
        this.absoluteAccuracy = absoluteAccuracy;
        this.relativeAccuracy = relativeAccuracy;
        this.functionValueAccuracy = functionValueAccuracy;
        this.evaluations = IntegerSequence.Incrementor.create();
    }

    /**
     * {@inheritDoc}
     */
    public int getMaxEvaluations() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public int getEvaluations() {
        // STUB: not implemented
        return 0;
    }

    /**
     * @return the lower end of the search interval.
     */
    public double getMin() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * @return the higher end of the search interval.
     */
    public double getMax() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * @return the initial guess.
     */
    public double getStartValue() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double getAbsoluteAccuracy() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double getRelativeAccuracy() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double getFunctionValueAccuracy() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Compute the objective function value.
     *
     * @param point Point at which the objective function must be evaluated.
     * @return the objective function value at specified point.
     * @throws TooManyEvaluationsException if the maximal number of evaluations
     * is exceeded.
     */
    protected double computeObjectiveValue(double point) throws TooManyEvaluationsException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Prepare for computation.
     * Subclasses must call this method if they override any of the
     * {@code solve} methods.
     *
     * @param f Function to solve.
     * @param min Lower bound for the interval.
     * @param max Upper bound for the interval.
     * @param startValue Start value to use.
     * @param maxEval Maximum number of evaluations.
     * @exception NullArgumentException if f is null
     */
    protected void setup(int maxEval, FUNC f, double min, double max, double startValue) throws NullArgumentException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public double solve(int maxEval, FUNC f, double min, double max, double startValue) throws TooManyEvaluationsException, NoBracketingException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double solve(int maxEval, FUNC f, double min, double max) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double solve(int maxEval, FUNC f, double startValue) throws TooManyEvaluationsException, NoBracketingException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Method for implementing actual optimization algorithms in derived
     * classes.
     *
     * @return the root.
     * @throws TooManyEvaluationsException if the maximal number of evaluations
     * is exceeded.
     * @throws NoBracketingException if the initial search interval does not bracket
     * a root and the solver requires it.
     */
    protected abstract double doSolve() throws TooManyEvaluationsException, NoBracketingException;

    /**
     * Check whether the function takes opposite signs at the endpoints.
     *
     * @param lower Lower endpoint.
     * @param upper Upper endpoint.
     * @return {@code true} if the function values have opposite signs at the
     * given points.
     */
    protected boolean isBracketing(final double lower, final double upper) {
        // STUB: not implemented
        return false;
    }

    /**
     * Check whether the arguments form a (strictly) increasing sequence.
     *
     * @param start First number.
     * @param mid Second number.
     * @param end Third number.
     * @return {@code true} if the arguments form an increasing sequence.
     */
    protected boolean isSequence(final double start, final double mid, final double end) {
        // STUB: not implemented
        return false;
    }

    /**
     * Check that the endpoints specify an interval.
     *
     * @param lower Lower endpoint.
     * @param upper Upper endpoint.
     * @throws NumberIsTooLargeException if {@code lower >= upper}.
     */
    protected void verifyInterval(final double lower, final double upper) throws NumberIsTooLargeException {
        // STUB: not implemented
    }

    /**
     * Check that {@code lower < initial < upper}.
     *
     * @param lower Lower endpoint.
     * @param initial Initial value.
     * @param upper Upper endpoint.
     * @throws NumberIsTooLargeException if {@code lower >= initial} or
     * {@code initial >= upper}.
     */
    protected void verifySequence(final double lower, final double initial, final double upper) throws NumberIsTooLargeException {
        // STUB: not implemented
    }

    /**
     * Check that the endpoints specify an interval and the function takes
     * opposite signs at the endpoints.
     *
     * @param lower Lower endpoint.
     * @param upper Upper endpoint.
     * @throws NullArgumentException if the function has not been set.
     * @throws NoBracketingException if the function has the same sign at
     * the endpoints.
     */
    protected void verifyBracketing(final double lower, final double upper) throws NullArgumentException, NoBracketingException {
        // STUB: not implemented
    }

    /**
     * Increment the evaluation count by one.
     * Method {@link #computeObjectiveValue(double)} calls this method internally.
     * It is provided for subclasses that do not exclusively use
     * {@code computeObjectiveValue} to solve the function.
     * See e.g. {@link AbstractUnivariateDifferentiableSolver}.
     *
     * @throws TooManyEvaluationsException when the allowed number of function
     * evaluations has been exhausted.
     */
    protected void incrementEvaluationCount() throws TooManyEvaluationsException {
        // STUB: not implemented
    }
}
