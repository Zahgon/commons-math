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
package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.BaseMultivariateOptimizer;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Base class for a multivariate vector function optimizer.
 *
 * @since 3.1
 */
@Deprecated
public abstract class MultivariateVectorOptimizer extends BaseMultivariateOptimizer<PointVectorValuePair> {

    /**
     * Target values for the model function at optimum.
     */
    private double[] target;

    /**
     * Weight matrix.
     */
    private RealMatrix weightMatrix;

    /**
     * Model function.
     */
    private MultivariateVectorFunction model;

    /**
     * @param checker Convergence checker.
     */
    protected MultivariateVectorOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        super(checker);
    }

    /**
     * Computes the objective function value.
     * This method <em>must</em> be called by subclasses to enforce the
     * evaluation counter limit.
     *
     * @param params Point at which the objective function must be evaluated.
     * @return the objective function value at the specified point.
     * @throws TooManyEvaluationsException if the maximal number of evaluations
     * (of the model vector function) is exceeded.
     */
    protected double[] computeObjectiveValue(double[] params) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param optData Optimization data. In addition to those documented in
     * {@link BaseMultivariateOptimizer#parseOptimizationData(OptimizationData[])
     * BaseMultivariateOptimizer}, this method will register the following data:
     * <ul>
     *  <li>{@link Target}</li>
     *  <li>{@link Weight}</li>
     *  <li>{@link ModelFunction}</li>
     * </ul>
     * @return {@inheritDoc}
     * @throws TooManyEvaluationsException if the maximal number of
     * evaluations is exceeded.
     * @throws DimensionMismatchException if the initial guess, target, and weight
     * arguments have inconsistent dimensions.
     */
    @Override
    public PointVectorValuePair optimize(OptimizationData... optData) throws TooManyEvaluationsException, DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Gets the weight matrix of the observations.
     *
     * @return the weight matrix.
     */
    public RealMatrix getWeight() {
        // STUB: not implemented
        return null;
    }

    /**
     * Gets the observed values to be matched by the objective vector
     * function.
     *
     * @return the target values.
     */
    public double[] getTarget() {
        // STUB: not implemented
        return null;
    }

    /**
     * Gets the number of observed values.
     *
     * @return the length of the target vector.
     */
    public int getTargetSize() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Scans the list of (required and optional) optimization data that
     * characterize the problem.
     *
     * @param optData Optimization data. The following data will be looked for:
     * <ul>
     *  <li>{@link Target}</li>
     *  <li>{@link Weight}</li>
     *  <li>{@link ModelFunction}</li>
     * </ul>
     */
    @Override
    protected void parseOptimizationData(OptimizationData... optData) {
        // STUB: not implemented
    }

    /**
     * Check parameters consistency.
     *
     * @throws DimensionMismatchException if {@link #target} and
     * {@link #weightMatrix} have inconsistent dimensions.
     */
    private void checkParameters() {
        if (target.length != weightMatrix.getColumnDimension()) {
            throw new DimensionMismatchException(target.length, weightMatrix.getColumnDimension());
        }
    }
}
