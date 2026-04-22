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

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/**
 * Implements the Beta distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Beta_distribution">Beta distribution</a>
 * @since 2.0 (changed to concrete class in 3.0)
 */
public class BetaDistribution extends AbstractRealDistribution {

    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -1221965979403477668L;

    /**
     * First shape parameter.
     */
    private final double alpha;

    /**
     * Second shape parameter.
     */
    private final double beta;

    /**
     * Normalizing factor used in density computations.
     * updated whenever alpha or beta are changed.
     */
    private double z;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

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
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     */
    public BetaDistribution(double alpha, double beta) {
        this(alpha, beta, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
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
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @since 2.1
     */
    public BetaDistribution(double alpha, double beta, double inverseCumAccuracy) {
        this(new Well19937c(), alpha, beta, inverseCumAccuracy);
    }

    /**
     * Creates a &beta; distribution.
     *
     * @param rng Random number generator.
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     * @since 3.3
     */
    public BetaDistribution(RandomGenerator rng, double alpha, double beta) {
        this(rng, alpha, beta, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a &beta; distribution.
     *
     * @param rng Random number generator.
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @since 3.1
     */
    public BetaDistribution(RandomGenerator rng, double alpha, double beta, double inverseCumAccuracy) {
        super(rng);
        this.alpha = alpha;
        this.beta = beta;
        z = Double.NaN;
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Access the first shape parameter, {@code alpha}.
     *
     * @return the first shape parameter.
     */
    public double getAlpha() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Access the second shape parameter, {@code beta}.
     *
     * @return the second shape parameter.
     */
    public double getBeta() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Recompute the normalization factor.
     */
    private void recomputeZ() {
        if (Double.isNaN(z)) {
            z = Gamma.logGamma(alpha) + Gamma.logGamma(beta) - Gamma.logGamma(alpha + beta);
        }
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc} *
     */
    @Override
    public double logDensity(double x) {
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
     * Return the absolute accuracy setting of the solver used to estimate
     * inverse cumulative probabilities.
     *
     * @return the solver absolute accuracy.
     * @since 2.1
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * For first shape parameter {@code alpha} and second shape parameter
     * {@code beta}, the mean is {@code alpha / (alpha + beta)}.
     */
    public double getNumericalMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * For first shape parameter {@code alpha} and second shape parameter
     * {@code beta}, the variance is
     * {@code (alpha * beta) / [(alpha + beta)^2 * (alpha + beta + 1)]}.
     */
    public double getNumericalVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0 no matter the parameters.
     *
     * @return lower bound of the support (always 0)
     */
    public double getSupportLowerBound() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is always 1 no matter the parameters.
     *
     * @return upper bound of the support (always 1)
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
     *
     * The support of this distribution is connected.
     *
     * @return {@code true}
     */
    public boolean isSupportConnected() {
        // STUB: not implemented
        return false;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Sampling is performed using Cheng algorithms:
     * </p>
     * <p>
     * R. C. H. Cheng, "Generating beta variates with nonintegral shape parameters.".
     *                 Communications of the ACM, 21, 317â€“322, 1978.
     * </p>
     */
    @Override
    public double sample() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Utility class implementing Cheng's algorithms for beta distribution sampling.
     * <p>
     * R. C. H. Cheng, "Generating beta variates with nonintegral shape parameters.".
     *                 Communications of the ACM, 21, 317â€“322, 1978.
     * </p>
     * @since 3.6
     */
    private static final class ChengBetaSampler {

        /**
         * Returns one sample using Cheng's sampling algorithm.
         * @param random random generator to use
         * @param alpha distribution first shape parameter
         * @param beta distribution second shape parameter
         * @return sampled value
         */
        static double sample(RandomGenerator random, final double alpha, final double beta) {
            // STUB: not implemented
            return 0.0;
        }

        /**
         * Returns one sample using Cheng's BB algorithm, when both &alpha; and &beta; are greater than 1.
         * @param random random generator to use
         * @param a0 distribution first shape parameter (&alpha;)
         * @param a min(&alpha;, &beta;) where &alpha;, &beta; are the two distribution shape parameters
         * @param b max(&alpha;, &beta;) where &alpha;, &beta; are the two distribution shape parameters
         * @return sampled value
         */
        private static double algorithmBB(RandomGenerator random, final double a0, final double a, final double b) {
            final double alpha = a + b;
            final double beta = FastMath.sqrt((alpha - 2.) / (2. * a * b - alpha));
            final double gamma = a + 1. / beta;
            double r;
            double w;
            double t;
            do {
                final double u1 = random.nextDouble();
                final double u2 = random.nextDouble();
                final double v = beta * (FastMath.log(u1) - FastMath.log1p(-u1));
                w = a * FastMath.exp(v);
                final double z = u1 * u1 * u2;
                r = gamma * v - 1.3862944;
                final double s = a + r - w;
                if (s + 2.609438 >= 5 * z) {
                    break;
                }
                t = FastMath.log(z);
                if (s >= t) {
                    break;
                }
            } while (r + alpha * (FastMath.log(alpha) - FastMath.log(b + w)) < t);
            w = FastMath.min(w, Double.MAX_VALUE);
            return Precision.equals(a, a0) ? w / (b + w) : b / (b + w);
        }

        /**
         * Returns one sample using Cheng's BC algorithm, when at least one of &alpha; and &beta; is smaller than 1.
         * @param random random generator to use
         * @param a0 distribution first shape parameter (&alpha;)
         * @param a max(&alpha;, &beta;) where &alpha;, &beta; are the two distribution shape parameters
         * @param b min(&alpha;, &beta;) where &alpha;, &beta; are the two distribution shape parameters
         * @return sampled value
         */
        private static double algorithmBC(RandomGenerator random, final double a0, final double a, final double b) {
            final double alpha = a + b;
            final double beta = 1. / b;
            final double delta = 1. + a - b;
            final double k1 = delta * (0.0138889 + 0.0416667 * b) / (a * beta - 0.777778);
            final double k2 = 0.25 + (0.5 + 0.25 / delta) * b;
            double w;
            for (; ; ) {
                final double u1 = random.nextDouble();
                final double u2 = random.nextDouble();
                final double y = u1 * u2;
                final double z = u1 * y;
                if (u1 < 0.5) {
                    if (0.25 * u2 + z - y >= k1) {
                        continue;
                    }
                } else {
                    if (z <= 0.25) {
                        final double v = beta * (FastMath.log(u1) - FastMath.log1p(-u1));
                        w = a * FastMath.exp(v);
                        break;
                    }
                    if (z >= k2) {
                        continue;
                    }
                }
                final double v = beta * (FastMath.log(u1) - FastMath.log1p(-u1));
                w = a * FastMath.exp(v);
                if (alpha * (FastMath.log(alpha) - FastMath.log(b + w) + v) - 1.3862944 >= FastMath.log(z)) {
                    break;
                }
            }
            w = FastMath.min(w, Double.MAX_VALUE);
            return Precision.equals(a, a0) ? w / (b + w) : b / (b + w);
        }
    }
}
