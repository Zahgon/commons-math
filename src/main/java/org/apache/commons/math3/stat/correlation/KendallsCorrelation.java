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
package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Implementation of Kendall's Tau-b rank correlation</a>.
 * <p>
 * A pair of observations (x<sub>1</sub>, y<sub>1</sub>) and
 * (x<sub>2</sub>, y<sub>2</sub>) are considered <i>concordant</i> if
 * x<sub>1</sub> &lt; x<sub>2</sub> and y<sub>1</sub> &lt; y<sub>2</sub>
 * or x<sub>2</sub> &lt; x<sub>1</sub> and y<sub>2</sub> &lt; y<sub>1</sub>.
 * The pair is <i>discordant</i> if x<sub>1</sub> &lt; x<sub>2</sub> and
 * y<sub>2</sub> &lt; y<sub>1</sub> or x<sub>2</sub> &lt; x<sub>1</sub> and
 * y<sub>1</sub> &lt; y<sub>2</sub>.  If either x<sub>1</sub> = x<sub>2</sub>
 * or y<sub>1</sub> = y<sub>2</sub>, the pair is neither concordant nor
 * discordant.
 * <p>
 * Kendall's Tau-b is defined as:
 * <pre>
 * tau<sub>b</sub> = (n<sub>c</sub> - n<sub>d</sub>) / sqrt((n<sub>0</sub> - n<sub>1</sub>) * (n<sub>0</sub> - n<sub>2</sub>))
 * </pre>
 * <p>
 * where:
 * <ul>
 *     <li>n<sub>0</sub> = n * (n - 1) / 2</li>
 *     <li>n<sub>c</sub> = Number of concordant pairs</li>
 *     <li>n<sub>d</sub> = Number of discordant pairs</li>
 *     <li>n<sub>1</sub> = sum of t<sub>i</sub> * (t<sub>i</sub> - 1) / 2 for all i</li>
 *     <li>n<sub>2</sub> = sum of u<sub>j</sub> * (u<sub>j</sub> - 1) / 2 for all j</li>
 *     <li>t<sub>i</sub> = Number of tied values in the i<sup>th</sup> group of ties in x</li>
 *     <li>u<sub>j</sub> = Number of tied values in the j<sup>th</sup> group of ties in y</li>
 * </ul>
 * <p>
 * This implementation uses the O(n log n) algorithm described in
 * William R. Knight's 1966 paper "A Computer Method for Calculating
 * Kendall's Tau with Ungrouped Data" in the Journal of the American
 * Statistical Association.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Kendall_tau_rank_correlation_coefficient">
 * Kendall tau rank correlation coefficient (Wikipedia)</a>
 * @see <a href="http://www.jstor.org/stable/2282833">A Computer
 * Method for Calculating Kendall's Tau with Ungrouped Data</a>
 *
 * @since 3.3
 */
public class KendallsCorrelation {

    /**
     * correlation matrix
     */
    private final RealMatrix correlationMatrix;

    /**
     * Create a KendallsCorrelation instance without data.
     */
    public KendallsCorrelation() {
        correlationMatrix = null;
    }

    /**
     * Create a KendallsCorrelation from a rectangular array
     * whose columns represent values of variables to be correlated.
     *
     * @param data rectangular array with columns representing variables
     * @throws IllegalArgumentException if the input data array is not
     * rectangular with at least two rows and two columns.
     */
    public KendallsCorrelation(double[][] data) {
        this(MatrixUtils.createRealMatrix(data));
    }

    /**
     * Create a KendallsCorrelation from a RealMatrix whose columns
     * represent variables to be correlated.
     *
     * @param matrix matrix with columns representing variables to correlate
     */
    public KendallsCorrelation(RealMatrix matrix) {
        correlationMatrix = computeCorrelationMatrix(matrix);
    }

    /**
     * Returns the correlation matrix.
     *
     * @return correlation matrix
     */
    public RealMatrix getCorrelationMatrix() {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the Kendall's Tau rank correlation matrix for the columns of
     * the input matrix.
     *
     * @param matrix matrix with columns representing variables to correlate
     * @return correlation matrix
     */
    public RealMatrix computeCorrelationMatrix(final RealMatrix matrix) {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the Kendall's Tau rank correlation matrix for the columns of
     * the input rectangular array.  The columns of the array represent values
     * of variables to be correlated.
     *
     * @param matrix matrix with columns representing variables to correlate
     * @return correlation matrix
     */
    public RealMatrix computeCorrelationMatrix(final double[][] matrix) {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the Kendall's Tau rank correlation coefficient between the two arrays.
     *
     * @param xArray first data array
     * @param yArray second data array
     * @return Returns Kendall's Tau rank correlation coefficient for the two arrays
     * @throws DimensionMismatchException if the arrays lengths do not match
     */
    public double correlation(final double[] xArray, final double[] yArray) throws DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the sum of the number from 1 .. n according to Gauss' summation formula:
     * \[ \sum\limits_{k=1}^n k = \frac{n(n + 1)}{2} \]
     *
     * @param n the summation end
     * @return the sum of the number from 1 to n
     */
    private static long sum(long n) {
        return n * (n + 1) / 2l;
    }
}
