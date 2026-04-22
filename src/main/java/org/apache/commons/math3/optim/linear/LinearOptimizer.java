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
package org.apache.commons.math3.optim.linear;

import java.util.Collection;
import java.util.Collections;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;

/**
 * Base class for implementing linear optimizers.
 *
 * @since 3.1
 */
public abstract class LinearOptimizer extends MultivariateOptimizer {

    /**
     * Linear objective function.
     */
    private LinearObjectiveFunction function;

    /**
     * Linear constraints.
     */
    private Collection<LinearConstraint> linearConstraints;

    /**
     * Whether to restrict the variables to non-negative values.
     */
    private boolean nonNegative;

    /**
     * Simple constructor with default settings.
     */
    protected LinearOptimizer() {
        // No convergence checker.
        super(null);
    }

    /**
     * @return {@code true} if the variables are restricted to non-negative values.
     */
    protected boolean isRestrictedToNonNegative() {
        // STUB: not implemented
        return false;
    }

    /**
     * @return the optimization type.
     */
    protected LinearObjectiveFunction getFunction() {
        // STUB: not implemented
        return null;
    }

    /**
     * @return the optimization type.
     */
    protected Collection<LinearConstraint> getConstraints() {
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
     *  <li>{@link LinearObjectiveFunction}</li>
     *  <li>{@link LinearConstraintSet}</li>
     *  <li>{@link NonNegativeConstraint}</li>
     * </ul>
     * @return {@inheritDoc}
     * @throws TooManyIterationsException if the maximal number of
     * iterations is exceeded.
     */
    @Override
    public PointValuePair optimize(OptimizationData... optData) throws TooManyIterationsException {
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
     *  <li>{@link LinearObjectiveFunction}</li>
     *  <li>{@link LinearConstraintSet}</li>
     *  <li>{@link NonNegativeConstraint}</li>
     * </ul>
     */
    @Override
    protected void parseOptimizationData(OptimizationData... optData) {
        // STUB: not implemented
    }
}
