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

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

/**
 * This class implements the Nakagami distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Nakagami_distribution">Nakagami Distribution (Wikipedia)</a>
 *
 * @since 3.4
 */
public class NakagamiDistribution extends AbstractRealDistribution {

    /**
     * Default inverse cumulative probability accuracy.
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20141003;

    /**
     * The shape parameter.
     */
    private final double mu;

    /**
     * The scale parameter.
     */
    private final double omega;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double inverseAbsoluteAccuracy;

    /**
     * Build a new instance.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mu shape parameter
     * @param omega scale parameter (must be positive)
     * @throws NumberIsTooSmallException if {@code mu < 0.5}
     * @throws NotStrictlyPositiveException if {@code omega <= 0}
     */
    public NakagamiDistribution(double mu, double omega) {
        this(mu, omega, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Build a new instance.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mu shape parameter
     * @param omega scale parameter (must be positive)
     * @param inverseAbsoluteAccuracy the maximum absolute error in inverse
     * cumulative probability estimates (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NumberIsTooSmallException if {@code mu < 0.5}
     * @throws NotStrictlyPositiveException if {@code omega <= 0}
     */
    public NakagamiDistribution(double mu, double omega, double inverseAbsoluteAccuracy) {
        this(new Well19937c(), mu, omega, inverseAbsoluteAccuracy);
    }

    /**
     * Build a new instance.
     *
     * @param rng Random number generator
     * @param mu shape parameter
     * @param omega scale parameter (must be positive)
     * @param inverseAbsoluteAccuracy the maximum absolute error in inverse
     * cumulative probability estimates (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NumberIsTooSmallException if {@code mu < 0.5}
     * @throws NotStrictlyPositiveException if {@code omega <= 0}
     */
    public NakagamiDistribution(RandomGenerator rng, double mu, double omega, double inverseAbsoluteAccuracy) {
        super(rng);
        if (mu < 0.5) {
            throw new NumberIsTooSmallException(mu, 0.5, true);
        }
        if (omega <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, omega);
        }
        this.mu = mu;
        this.omega = omega;
        this.inverseAbsoluteAccuracy = inverseAbsoluteAccuracy;
    }

    /**
     * Access the shape parameter, {@code mu}.
     *
     * @return the shape parameter.
     */
    public double getShape() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Access the scale parameter, {@code omega}.
     *
     * @return the scale parameter.
     */
    public double getScale() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        // STUB: not implemented
        return 0.0;
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
}
