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
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

/**
 * Implementation of the Zipf distribution.
 * <p>
 * <strong>Parameters:</strong>
 * For a random variable {@code X} whose values are distributed according to this
 * distribution, the probability mass function is given by
 * <pre>
 *   P(X = k) = H(N,s) * 1 / k^s    for {@code k = 1,2,...,N}.
 * </pre>
 * {@code H(N,s)} is the normalizing constant
 * which corresponds to the generalized harmonic number of order N of s.
 * <p>
 * <ul>
 * <li>{@code N} is the number of elements</li>
 * <li>{@code s} is the exponent</li>
 * </ul>
 * @see <a href="https://en.wikipedia.org/wiki/Zipf's_law">Zipf's law (Wikipedia)</a>
 * @see <a href="https://en.wikipedia.org/wiki/Harmonic_number#Generalized_harmonic_numbers">Generalized harmonic numbers</a>
 */
public class ZipfDistribution extends AbstractIntegerDistribution {

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -140627372283420404L;

    /**
     * Number of elements.
     */
    private final int numberOfElements;

    /**
     * Exponent parameter of the distribution.
     */
    private final double exponent;

    /**
     * Cached numerical mean
     */
    private double numericalMean = Double.NaN;

    /**
     * Whether or not the numerical mean has been calculated
     */
    private boolean numericalMeanIsCalculated = false;

    /**
     * Cached numerical variance
     */
    private double numericalVariance = Double.NaN;

    /**
     * Whether or not the numerical variance has been calculated
     */
    private boolean numericalVarianceIsCalculated = false;

    /**
     * The sampler to be used for the sample() method
     */
    private transient ZipfRejectionInversionSampler sampler;

    /**
     * Create a new Zipf distribution with the given number of elements and
     * exponent.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param numberOfElements Number of elements.
     * @param exponent Exponent.
     * @exception NotStrictlyPositiveException if {@code numberOfElements <= 0}
     * or {@code exponent <= 0}.
     */
    public ZipfDistribution(final int numberOfElements, final double exponent) {
        this(new Well19937c(), numberOfElements, exponent);
    }

    /**
     * Creates a Zipf distribution.
     *
     * @param rng Random number generator.
     * @param numberOfElements Number of elements.
     * @param exponent Exponent.
     * @exception NotStrictlyPositiveException if {@code numberOfElements <= 0}
     * or {@code exponent <= 0}.
     * @since 3.1
     */
    public ZipfDistribution(RandomGenerator rng, int numberOfElements, double exponent) throws NotStrictlyPositiveException {
        super(rng);
        if (numberOfElements <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, numberOfElements);
        }
        if (exponent <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.EXPONENT, exponent);
        }
        this.numberOfElements = numberOfElements;
        this.exponent = exponent;
    }

    /**
     * Get the number of elements (e.g. corpus size) for the distribution.
     *
     * @return the number of elements
     */
    public int getNumberOfElements() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Get the exponent characterizing the distribution.
     *
     * @return the exponent
     */
    public double getExponent() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double probability(final int x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logProbability(int x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(final int x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * For number of elements {@code N} and exponent {@code s}, the mean is
     * {@code Hs1 / Hs}, where
     * <ul>
     *  <li>{@code Hs1 = generalizedHarmonic(N, s - 1)},</li>
     *  <li>{@code Hs = generalizedHarmonic(N, s)}.</li>
     * </ul>
     */
    public double getNumericalMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Used by {@link #getNumericalMean()}.
     *
     * @return the mean of this distribution
     */
    protected double calculateNumericalMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * For number of elements {@code N} and exponent {@code s}, the mean is
     * {@code (Hs2 / Hs) - (Hs1^2 / Hs^2)}, where
     * <ul>
     *  <li>{@code Hs2 = generalizedHarmonic(N, s - 2)},</li>
     *  <li>{@code Hs1 = generalizedHarmonic(N, s - 1)},</li>
     *  <li>{@code Hs = generalizedHarmonic(N, s)}.</li>
     * </ul>
     */
    public double getNumericalVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Used by {@link #getNumericalVariance()}.
     *
     * @return the variance of this distribution
     */
    protected double calculateNumericalVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Calculates the Nth generalized harmonic number. See
     * <a href="http://mathworld.wolfram.com/HarmonicSeries.html">Harmonic
     * Series</a>.
     *
     * @param n Term in the series to calculate (must be larger than 1)
     * @param m Exponent (special case {@code m = 1} is the harmonic series).
     * @return the n<sup>th</sup> generalized harmonic number.
     */
    private double generalizedHarmonic(final int n, final double m) {
        double value = 0;
        for (int k = n; k > 0; --k) {
            value += 1.0 / FastMath.pow(k, m);
        }
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 1 no matter the parameters.
     *
     * @return lower bound of the support (always 1)
     */
    public int getSupportLowerBound() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is the number of elements.
     *
     * @return upper bound of the support
     */
    public int getSupportUpperBound() {
        // STUB: not implemented
        return 0;
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
     */
    @Override
    public int sample() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Utility class implementing a rejection inversion sampling method for a discrete,
     * bounded Zipf distribution that is based on the method described in
     * <p>
     * Wolfgang Hörmann and Gerhard Derflinger
     * "Rejection-inversion to generate variates from monotone discrete distributions."
     * ACM Transactions on Modeling and Computer Simulation (TOMACS) 6.3 (1996): 169-184.
     * <p>
     * The paper describes an algorithm for exponents larger than 1 (Algorithm ZRI).
     * The original method uses {@code H(x) := (v + x)^(1 - q) / (1 - q)}
     * as the integral of the hat function. This function is undefined for
     * q = 1, which is the reason for the limitation of the exponent.
     * If instead the integral function
     * {@code H(x) := ((v + x)^(1 - q) - 1) / (1 - q)} is used,
     * for which a meaningful limit exists for q = 1,
     * the method works for all positive exponents.
     * <p>
     * The following implementation uses v := 0 and generates integral numbers
     * in the range [1, numberOfElements]. This is different to the original method
     * where v is defined to be positive and numbers are taken from [0, i_max].
     * This explains why the implementation looks slightly different.
     *
     * @since 3.6
     */
    static final class ZipfRejectionInversionSampler {

        /**
         * Exponent parameter of the distribution.
         */
        private final double exponent;

        /**
         * Number of elements.
         */
        private final int numberOfElements;

        /**
         * Constant equal to {@code hIntegral(1.5) - 1}.
         */
        private final double hIntegralX1;

        /**
         * Constant equal to {@code hIntegral(numberOfElements + 0.5)}.
         */
        private final double hIntegralNumberOfElements;

        /**
         * Constant equal to {@code 2 - hIntegralInverse(hIntegral(2.5) - h(2)}.
         */
        private final double s;

        /**
         * Simple constructor.
         * @param numberOfElements number of elements
         * @param exponent exponent parameter of the distribution
         */
        ZipfRejectionInversionSampler(final int numberOfElements, final double exponent) {
            this.exponent = exponent;
            this.numberOfElements = numberOfElements;
            this.hIntegralX1 = hIntegral(1.5) - 1d;
            this.hIntegralNumberOfElements = hIntegral(numberOfElements + 0.5);
            this.s = 2d - hIntegralInverse(hIntegral(2.5) - h(2));
        }

        /**
         * Generate one integral number in the range [1, numberOfElements].
         * @param random random generator to use
         * @return generated integral number in the range [1, numberOfElements]
         */
        int sample(final RandomGenerator random) {
            // STUB: not implemented
            return 0;
        }

        /**
         * {@code H(x) :=}
         * <ul>
         * <li>{@code (x^(1-exponent) - 1)/(1 - exponent)}, if {@code exponent != 1}</li>
         * <li>{@code log(x)}, if {@code exponent == 1}</li>
         * </ul>
         * H(x) is an integral function of h(x),
         * the derivative of H(x) is h(x).
         *
         * @param x free parameter
         * @return {@code H(x)}
         */
        private double hIntegral(final double x) {
            final double logX = FastMath.log(x);
            return helper2((1d - exponent) * logX) * logX;
        }

        /**
         * {@code h(x) := 1/x^exponent}
         *
         * @param x free parameter
         * @return h(x)
         */
        private double h(final double x) {
            return FastMath.exp(-exponent * FastMath.log(x));
        }

        /**
         * The inverse function of H(x).
         *
         * @param x free parameter
         * @return y for which {@code H(y) = x}
         */
        private double hIntegralInverse(final double x) {
            double t = x * (1d - exponent);
            if (t < -1d) {
                // Limit value to the range [-1, +inf).
                // t could be smaller than -1 in some rare cases due to numerical errors.
                t = -1;
            }
            return FastMath.exp(helper1(t) * x);
        }

        /**
         * Helper function that calculates {@code log(1+x)/x}.
         * <p>
         * A Taylor series expansion is used, if x is close to 0.
         *
         * @param x a value larger than or equal to -1
         * @return {@code log(1+x)/x}
         */
        static double helper1(final double x) {
            // STUB: not implemented
            return 0.0;
        }

        /**
         * Helper function to calculate {@code (exp(x)-1)/x}.
         * <p>
         * A Taylor series expansion is used, if x is close to 0.
         *
         * @param x free parameter
         * @return {@code (exp(x)-1)/x} if x is non-zero, or 1 if x=0
         */
        static double helper2(final double x) {
            // STUB: not implemented
            return 0.0;
        }
    }
}
