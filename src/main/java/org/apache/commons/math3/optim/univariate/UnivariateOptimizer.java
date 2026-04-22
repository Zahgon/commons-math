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
package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.BaseOptimizer;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

/**
 * Base class for a univariate scalar function optimizer.
 *
 * @since 3.1
 */
public abstract class UnivariateOptimizer extends BaseOptimizer<UnivariatePointValuePair> {

    /**
     * Objective function.
     */
    private UnivariateFunction function;

    /**
     * Type of optimization.
     */
    private GoalType goal;

    /**
     * Initial guess.
     */
    private double start;

    /**
     * Lower bound.
     */
    private double min;

    /**
     * Upper bound.
     */
    private double max;

    /**
     * @param checker Convergence checker.
     */
    protected UnivariateOptimizer(ConvergenceChecker<UnivariatePointValuePair> checker) {
        super(checker);
    }

    /**
     * {@inheritDoc}
     *
     * @param optData Optimization data. In addition to those documented in
     * {@link BaseOptimizer#parseOptimizationData(OptimizationData[])
     * BaseOptimizer}, this method will register the following data:
     * <ul>
     *  <li>{@link GoalType}</li>
     *  <li>{@link SearchInterval}</li>
     *  <li>{@link UnivariateObjectiveFunction}</li>
     * </ul>
     * @return {@inheritDoc}
     * @throws TooManyEvaluationsException if the maximal number of
     * evaluations is exceeded.
     */
    @Override
    public UnivariatePointValuePair optimize(OptimizationData... optData) throws TooManyEvaluationsException {
        // STUB: not implemented
        return null;
    }

    /**
     * @return the optimization type.
     */
    public GoalType getGoalType() {
        // STUB: not implemented
        return null;
    }

    /**
     * Scans the list of (required and optional) optimization data that
     * characterize the problem.
     *
     * @param optData Optimization data.
     * The following data will be looked for:
     * <ul>
     *  <li>{@link GoalType}</li>
     *  <li>{@link SearchInterval}</li>
     *  <li>{@link UnivariateObjectiveFunction}</li>
     * </ul>
     */
    @Override
    protected void parseOptimizationData(OptimizationData... optData) {
        // STUB: not implemented
    }

    /**
     * @return the initial guess.
     */
    public double getStartValue() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * @return the lower bounds.
     */
    public double getMin() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * @return the upper bounds.
     */
    public double getMax() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Computes the objective function value.
     * This method <em>must</em> be called by subclasses to enforce the
     * evaluation counter limit.
     *
     * @param x Point at which the objective function must be evaluated.
     * @return the objective function value at the specified point.
     * @throws TooManyEvaluationsException if the maximal number of
     * evaluations is exceeded.
     */
    protected double computeObjectiveValue(double x) {
        // STUB: not implemented
        return 0.0;
    }
}
