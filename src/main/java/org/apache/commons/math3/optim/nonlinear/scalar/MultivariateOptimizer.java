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
package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optim.BaseMultivariateOptimizer;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

/**
 * Base class for a multivariate scalar function optimizer.
 *
 * @since 3.1
 */
public abstract class MultivariateOptimizer extends BaseMultivariateOptimizer<PointValuePair> {

    /**
     * Objective function.
     */
    private MultivariateFunction function;

    /**
     * Type of optimization.
     */
    private GoalType goal;

    /**
     * @param checker Convergence checker.
     */
    protected MultivariateOptimizer(ConvergenceChecker<PointValuePair> checker) {
        super(checker);
    }

    /**
     * {@inheritDoc}
     *
     * @param optData Optimization data. In addition to those documented in
     * {@link BaseMultivariateOptimizer#parseOptimizationData(OptimizationData[])
     * BaseMultivariateOptimizer}, this method will register the following data:
     * <ul>
     *  <li>{@link ObjectiveFunction}</li>
     *  <li>{@link GoalType}</li>
     * </ul>
     * @return {@inheritDoc}
     * @throws TooManyEvaluationsException if the maximal number of
     * evaluations is exceeded.
     */
    @Override
    public PointValuePair optimize(OptimizationData... optData) throws TooManyEvaluationsException {
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
     *  <li>{@link ObjectiveFunction}</li>
     *  <li>{@link GoalType}</li>
     * </ul>
     */
    @Override
    protected void parseOptimizationData(OptimizationData... optData) {
        // STUB: not implemented
    }

    /**
     * @return the optimization type.
     */
    public GoalType getGoalType() {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the objective function value.
     * This method <em>must</em> be called by subclasses to enforce the
     * evaluation counter limit.
     *
     * @param params Point at which the objective function must be evaluated.
     * @return the objective function value at the specified point.
     * @throws TooManyEvaluationsException if the maximal number of
     * evaluations is exceeded.
     */
    public double computeObjectiveValue(double[] params) {
        // STUB: not implemented
        return 0.0;
    }
}
