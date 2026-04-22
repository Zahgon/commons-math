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
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.OutOfRangeException;

/**
 * Implementation of the constant real distribution.
 *
 * @since 3.4
 */
public class ConstantRealDistribution extends AbstractRealDistribution {

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = -4157745166772046273L;

    /**
     * Constant value of the distribution
     */
    private final double value;

    /**
     * Create a constant real distribution with the given value.
     *
     * @param value the constant value of this distribution
     */
    public ConstantRealDistribution(double value) {
        // Avoid creating RandomGenerator
        super(null);
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double getSupportLowerBound() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double getSupportUpperBound() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportLowerBoundInclusive() {
        // STUB: not implemented
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportUpperBoundInclusive() {
        // STUB: not implemented
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportConnected() {
        // STUB: not implemented
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double sample() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Override with no-op (there is no generator).
     * @param seed (ignored)
     */
    @Override
    public void reseedRandomGenerator(long seed) {
        // STUB: not implemented
    }
}
