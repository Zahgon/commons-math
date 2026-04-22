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
package org.apache.commons.math3.distribution.fitting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;

/**
 * Expectation-Maximization</a> algorithm for fitting the parameters of
 * multivariate normal mixture model distributions.
 *
 * This implementation is pure original code based on <a
 * href="https://www.ee.washington.edu/techsite/papers/documents/UWEETR-2010-0002.pdf">
 * EM Demystified: An Expectation-Maximization Tutorial</a> by Yihua Chen and Maya R. Gupta,
 * Department of Electrical Engineering, University of Washington, Seattle, WA 98195.
 * It was verified using external tools like <a
 * href="http://cran.r-project.org/web/packages/mixtools/index.html">CRAN Mixtools</a>
 * (see the JUnit test cases) but it is <strong>not</strong> based on Mixtools code at all.
 * The discussion of the origin of this class can be seen in the comments of the <a
 * href="https://issues.apache.org/jira/browse/MATH-817">MATH-817</a> JIRA issue.
 * @since 3.2
 */
public class MultivariateNormalMixtureExpectationMaximization {

    /**
     * Default maximum number of iterations allowed per fitting process.
     */
    private static final int DEFAULT_MAX_ITERATIONS = 1000;

    /**
     * Default convergence threshold for fitting.
     */
    private static final double DEFAULT_THRESHOLD = 1E-5;

    /**
     * The data to fit.
     */
    private final double[][] data;

    /**
     * The model fit against the data.
     */
    private MixtureMultivariateNormalDistribution fittedModel;

    /**
     * The log likelihood of the data given the fitted model.
     */
    private double logLikelihood = 0d;

    /**
     * Creates an object to fit a multivariate normal mixture model to data.
     *
     * @param data Data to use in fitting procedure
     * @throws NotStrictlyPositiveException if data has no rows
     * @throws DimensionMismatchException if rows of data have different numbers
     *             of columns
     * @throws NumberIsTooSmallException if the number of columns in the data is
     *             less than 2
     */
    public MultivariateNormalMixtureExpectationMaximization(double[][] data) throws NotStrictlyPositiveException, DimensionMismatchException, NumberIsTooSmallException {
        if (data.length < 1) {
            throw new NotStrictlyPositiveException(data.length);
        }
        this.data = new double[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            if (data[i].length != data[0].length) {
                // Jagged arrays not allowed
                throw new DimensionMismatchException(data[i].length, data[0].length);
            }
            if (data[i].length < 2) {
                throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, data[i].length, 2, true);
            }
            this.data[i] = MathArrays.copyOf(data[i], data[i].length);
        }
    }

    /**
     * Fit a mixture model to the data supplied to the constructor.
     *
     * The quality of the fit depends on the concavity of the data provided to
     * the constructor and the initial mixture provided to this function. If the
     * data has many local optima, multiple runs of the fitting function with
     * different initial mixtures may be required to find the optimal solution.
     * If a SingularMatrixException is encountered, it is possible that another
     * initialization would work.
     *
     * @param initialMixture Model containing initial values of weights and
     *            multivariate normals
     * @param maxIterations Maximum iterations allowed for fit
     * @param threshold Convergence threshold computed as difference in
     *             logLikelihoods between successive iterations
     * @throws SingularMatrixException if any component's covariance matrix is
     *             singular during fitting
     * @throws NotStrictlyPositiveException if numComponents is less than one
     *             or threshold is less than Double.MIN_VALUE
     * @throws DimensionMismatchException if initialMixture mean vector and data
     *             number of columns are not equal
     */
    public void fit(final MixtureMultivariateNormalDistribution initialMixture, final int maxIterations, final double threshold) throws SingularMatrixException, NotStrictlyPositiveException, DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Fit a mixture model to the data supplied to the constructor.
     *
     * The quality of the fit depends on the concavity of the data provided to
     * the constructor and the initial mixture provided to this function. If the
     * data has many local optima, multiple runs of the fitting function with
     * different initial mixtures may be required to find the optimal solution.
     * If a SingularMatrixException is encountered, it is possible that another
     * initialization would work.
     *
     * @param initialMixture Model containing initial values of weights and
     *            multivariate normals
     * @throws SingularMatrixException if any component's covariance matrix is
     *             singular during fitting
     * @throws NotStrictlyPositiveException if numComponents is less than one or
     *             threshold is less than Double.MIN_VALUE
     */
    public void fit(MixtureMultivariateNormalDistribution initialMixture) throws SingularMatrixException, NotStrictlyPositiveException {
        // STUB: not implemented
    }

    /**
     * Helper method to create a multivariate normal mixture model which can be
     * used to initialize {@link #fit(MixtureMultivariateNormalDistribution)}.
     *
     * This method uses the data supplied to the constructor to try to determine
     * a good mixture model at which to start the fit, but it is not guaranteed
     * to supply a model which will find the optimal solution or even converge.
     *
     * @param data Data to estimate distribution
     * @param numComponents Number of components for estimated mixture
     * @return Multivariate normal mixture model estimated from the data
     * @throws NumberIsTooLargeException if {@code numComponents} is greater
     * than the number of data rows.
     * @throws NumberIsTooSmallException if {@code numComponents < 2}.
     * @throws NotStrictlyPositiveException if data has less than 2 rows
     * @throws DimensionMismatchException if rows of data have different numbers
     *             of columns
     */
    public static MixtureMultivariateNormalDistribution estimate(final double[][] data, final int numComponents) throws NotStrictlyPositiveException, DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Gets the log likelihood of the data under the fitted model.
     *
     * @return Log likelihood of data or zero of no data has been fit
     */
    public double getLogLikelihood() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Gets the fitted model.
     *
     * @return fitted model or {@code null} if no fit has been performed yet.
     */
    public MixtureMultivariateNormalDistribution getFittedModel() {
        // STUB: not implemented
        return null;
    }

    /**
     * Class used for sorting user-supplied data.
     */
    private static class DataRow implements Comparable<DataRow> {

        /**
         * One data row.
         */
        private final double[] row;

        /**
         * Mean of the data row.
         */
        private Double mean;

        /**
         * Create a data row.
         * @param data Data to use for the row
         */
        DataRow(final double[] data) {
            // Store reference.
            row = data;
            // Compute mean.
            mean = 0d;
            for (int i = 0; i < data.length; i++) {
                mean += data[i];
            }
            mean /= data.length;
        }

        /**
         * Compare two data rows.
         * @param other The other row
         * @return int for sorting
         */
        public int compareTo(final DataRow other) {
            // STUB: not implemented
            return 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object other) {
            // STUB: not implemented
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            // STUB: not implemented
            return 0;
        }

        /**
         * Get a data row.
         * @return data row array
         */
        public double[] getRow() {
            // STUB: not implemented
            return null;
        }
    }
}
