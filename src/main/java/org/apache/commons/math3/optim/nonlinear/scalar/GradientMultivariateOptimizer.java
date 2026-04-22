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

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

/**
 * Base class for implementing optimizers for multivariate scalar
 * differentiable functions.
 * It contains boiler-plate code for dealing with gradient evaluation.
 *
 * @since 3.1
 */
public abstract class GradientMultivariateOptimizer extends MultivariateOptimizer {

    /**
     * Gradient of the objective function.
     */
    private MultivariateVectorFunction gradient;

    /**
     * @param checker Convergence checker.
     */
    protected GradientMultivariateOptimizer(ConvergenceChecker<PointValuePair> checker) {
        super(checker);
    }

    /**
     * Compute the gradient vector.
     *
     * @param params Point at which the gradient must be evaluated.
     * @return the gradient at the specified point.
     */
    protected double[] computeObjectiveGradient(final double[] params) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param optData Optimization data. In addition to those documented in
     * {@link MultivariateOptimizer#parseOptimizationData(OptimizationData[])
     * MultivariateOptimizer}, this method will register the following data:
     * <ul>
     *  <li>{@link ObjectiveFunctionGradient}</li>
     * </ul>
     * @return {@inheritDoc}
     * @throws TooManyEvaluationsException if the maximal number of
     * evaluations (of the objective function) is exceeded.
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
     *  <li>{@link ObjectiveFunctionGradient}</li>
     * </ul>
     */
    @Override
    protected void parseOptimizationData(OptimizationData... optData) {
        // STUB: not implemented
    }
}
