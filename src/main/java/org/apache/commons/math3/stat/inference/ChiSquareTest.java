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
package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/**
 * Implements Chi-Square test statistics.
 *
 * <p>This implementation handles both known and unknown distributions.</p>
 *
 * <p>Two samples tests can be used when the distribution is unknown <i>a priori</i>
 * but provided by one sample, or when the hypothesis under test is that the two
 * samples come from the same underlying distribution.</p>
 */
public class ChiSquareTest {

    /**
     * Construct a ChiSquareTest
     */
    public ChiSquareTest() {
        super();
    }

    /**
     * Computes the <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda35f.htm">
     * Chi-Square statistic</a> comparing <code>observed</code> and <code>expected</code>
     * frequency counts.
     * <p>
     * This statistic can be used to perform a Chi-Square test evaluating the null
     * hypothesis that the observed counts follow the expected distribution.</p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Expected counts must all be positive.
     * </li>
     * <li>Observed counts must all be &ge; 0.
     * </li>
     * <li>The observed and expected arrays must have the same length and
     * their common length must be at least 2.
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     * <p><strong>Note: </strong>This implementation rescales the
     * <code>expected</code> array if necessary to ensure that the sum of the
     * expected and observed counts are equal.</p>
     *
     * @param observed array of observed frequency counts
     * @param expected array of expected frequency counts
     * @return chiSquare test statistic
     * @throws NotPositiveException if <code>observed</code> has negative entries
     * @throws NotStrictlyPositiveException if <code>expected</code> has entries that are
     * not strictly positive
     * @throws DimensionMismatchException if the arrays length is less than 2
     */
    public double chiSquare(final double[] expected, final long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the <i>observed significance level</i>, or <a href=
     * "http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue">
     * p-value</a>, associated with a
     * <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda35f.htm">
     * Chi-square goodness of fit test</a> comparing the <code>observed</code>
     * frequency counts to those in the <code>expected</code> array.
     * <p>
     * The number returned is the smallest significance level at which one can reject
     * the null hypothesis that the observed counts conform to the frequency distribution
     * described by the expected counts.</p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Expected counts must all be positive.
     * </li>
     * <li>Observed counts must all be &ge; 0.
     * </li>
     * <li>The observed and expected arrays must have the same length and
     * their common length must be at least 2.
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     * <p><strong>Note: </strong>This implementation rescales the
     * <code>expected</code> array if necessary to ensure that the sum of the
     * expected and observed counts are equal.</p>
     *
     * @param observed array of observed frequency counts
     * @param expected array of expected frequency counts
     * @return p-value
     * @throws NotPositiveException if <code>observed</code> has negative entries
     * @throws NotStrictlyPositiveException if <code>expected</code> has entries that are
     * not strictly positive
     * @throws DimensionMismatchException if the arrays length is less than 2
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double chiSquareTest(final double[] expected, final long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Performs a <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda35f.htm">
     * Chi-square goodness of fit test</a> evaluating the null hypothesis that the
     * observed counts conform to the frequency distribution described by the expected
     * counts, with significance level <code>alpha</code>.  Returns true iff the null
     * hypothesis can be rejected with 100 * (1 - alpha) percent confidence.
     * <p>
     * <strong>Example:</strong><br>
     * To test the hypothesis that <code>observed</code> follows
     * <code>expected</code> at the 99% level, use </p><p>
     * <code>chiSquareTest(expected, observed, 0.01) </code></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Expected counts must all be positive.
     * </li>
     * <li>Observed counts must all be &ge; 0.
     * </li>
     * <li>The observed and expected arrays must have the same length and
     * their common length must be at least 2.
     * <li> <code> 0 &lt; alpha &lt; 0.5 </code>
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     * <p><strong>Note: </strong>This implementation rescales the
     * <code>expected</code> array if necessary to ensure that the sum of the
     * expected and observed counts are equal.</p>
     *
     * @param observed array of observed frequency counts
     * @param expected array of expected frequency counts
     * @param alpha significance level of the test
     * @return true iff null hypothesis can be rejected with confidence
     * 1 - alpha
     * @throws NotPositiveException if <code>observed</code> has negative entries
     * @throws NotStrictlyPositiveException if <code>expected</code> has entries that are
     * not strictly positive
     * @throws DimensionMismatchException if the arrays length is less than 2
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public boolean chiSquareTest(final double[] expected, final long[] observed, final double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        // STUB: not implemented
        return false;
    }

    /**
     *  Computes the Chi-Square statistic associated with a
     * <a href="http://www.itl.nist.gov/div898/handbook/prc/section4/prc45.htm">
     *  chi-square test of independence</a> based on the input <code>counts</code>
     *  array, viewed as a two-way table.
     * <p>
     * The rows of the 2-way table are
     * <code>count[0], ... , count[count.length - 1] </code></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>All counts must be &ge; 0.
     * </li>
     * <li>The count array must be rectangular (i.e. all count[i] subarrays
     *  must have the same length).
     * </li>
     * <li>The 2-way table represented by <code>counts</code> must have at
     *  least 2 columns and at least 2 rows.
     * </li>
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param counts array representation of 2-way table
     * @return chiSquare test statistic
     * @throws NullArgumentException if the array is null
     * @throws DimensionMismatchException if the array is not rectangular
     * @throws NotPositiveException if {@code counts} has negative entries
     */
    public double chiSquare(final long[][] counts) throws NullArgumentException, NotPositiveException, DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the <i>observed significance level</i>, or <a href=
     * "http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue">
     * p-value</a>, associated with a
     * <a href="http://www.itl.nist.gov/div898/handbook/prc/section4/prc45.htm">
     * chi-square test of independence</a> based on the input <code>counts</code>
     * array, viewed as a two-way table.
     * <p>
     * The rows of the 2-way table are
     * <code>count[0], ... , count[count.length - 1] </code></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>All counts must be &ge; 0.
     * </li>
     * <li>The count array must be rectangular (i.e. all count[i] subarrays must have
     *     the same length).
     * </li>
     * <li>The 2-way table represented by <code>counts</code> must have at least 2
     *     columns and at least 2 rows.
     * </li>
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param counts array representation of 2-way table
     * @return p-value
     * @throws NullArgumentException if the array is null
     * @throws DimensionMismatchException if the array is not rectangular
     * @throws NotPositiveException if {@code counts} has negative entries
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double chiSquareTest(final long[][] counts) throws NullArgumentException, DimensionMismatchException, NotPositiveException, MaxCountExceededException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Performs a <a href="http://www.itl.nist.gov/div898/handbook/prc/section4/prc45.htm">
     * chi-square test of independence</a> evaluating the null hypothesis that the
     * classifications represented by the counts in the columns of the input 2-way table
     * are independent of the rows, with significance level <code>alpha</code>.
     * Returns true iff the null hypothesis can be rejected with 100 * (1 - alpha) percent
     * confidence.
     * <p>
     * The rows of the 2-way table are
     * <code>count[0], ... , count[count.length - 1] </code></p>
     * <p>
     * <strong>Example:</strong><br>
     * To test the null hypothesis that the counts in
     * <code>count[0], ... , count[count.length - 1] </code>
     *  all correspond to the same underlying probability distribution at the 99% level, use</p>
     * <p><code>chiSquareTest(counts, 0.01)</code></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>All counts must be &ge; 0.
     * </li>
     * <li>The count array must be rectangular (i.e. all count[i] subarrays must have the
     *     same length).</li>
     * <li>The 2-way table represented by <code>counts</code> must have at least 2 columns and
     *     at least 2 rows.</li>
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param counts array representation of 2-way table
     * @param alpha significance level of the test
     * @return true iff null hypothesis can be rejected with confidence
     * 1 - alpha
     * @throws NullArgumentException if the array is null
     * @throws DimensionMismatchException if the array is not rectangular
     * @throws NotPositiveException if {@code counts} has any negative entries
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public boolean chiSquareTest(final long[][] counts, final double alpha) throws NullArgumentException, DimensionMismatchException, NotPositiveException, OutOfRangeException, MaxCountExceededException {
        // STUB: not implemented
        return false;
    }

    /**
     * <p>Computes a
     * <a href="http://www.itl.nist.gov/div898/software/dataplot/refman1/auxillar/chi2samp.htm">
     * Chi-Square two sample test statistic</a> comparing bin frequency counts
     * in <code>observed1</code> and <code>observed2</code>.  The
     * sums of frequency counts in the two samples are not required to be the
     * same.  The formula used to compute the test statistic is</p>
     * <code>
     * &sum;[(K * observed1[i] - observed2[i]/K)<sup>2</sup> / (observed1[i] + observed2[i])]
     * </code> where
     * <br/><code>K = &sqrt;[&sum(observed2 / &sum;(observed1)]</code>
     * </p>
     * <p>This statistic can be used to perform a Chi-Square test evaluating the
     * null hypothesis that both observed counts follow the same distribution.</p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Observed counts must be non-negative.
     * </li>
     * <li>Observed counts for a specific bin must not both be zero.
     * </li>
     * <li>Observed counts for a specific sample must not all be 0.
     * </li>
     * <li>The arrays <code>observed1</code> and <code>observed2</code> must have
     * the same length and their common length must be at least 2.
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param observed1 array of observed frequency counts of the first data set
     * @param observed2 array of observed frequency counts of the second data set
     * @return chiSquare test statistic
     * @throws DimensionMismatchException the the length of the arrays does not match
     * @throws NotPositiveException if any entries in <code>observed1</code> or
     * <code>observed2</code> are negative
     * @throws ZeroException if either all counts of <code>observed1</code> or
     * <code>observed2</code> are zero, or if the count at some index is zero
     * for both arrays
     * @since 1.2
     */
    public double chiSquareDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * <p>Returns the <i>observed significance level</i>, or <a href=
     * "http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue">
     * p-value</a>, associated with a Chi-Square two sample test comparing
     * bin frequency counts in <code>observed1</code> and
     * <code>observed2</code>.
     * </p>
     * <p>The number returned is the smallest significance level at which one
     * can reject the null hypothesis that the observed counts conform to the
     * same distribution.
     * </p>
     * <p>See {@link #chiSquareDataSetsComparison(long[], long[])} for details
     * on the formula used to compute the test statistic. The degrees of
     * of freedom used to perform the test is one less than the common length
     * of the input observed count arrays.
     * </p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Observed counts must be non-negative.
     * </li>
     * <li>Observed counts for a specific bin must not both be zero.
     * </li>
     * <li>Observed counts for a specific sample must not all be 0.
     * </li>
     * <li>The arrays <code>observed1</code> and <code>observed2</code> must
     * have the same length and
     * their common length must be at least 2.
     * </li></ul><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param observed1 array of observed frequency counts of the first data set
     * @param observed2 array of observed frequency counts of the second data set
     * @return p-value
     * @throws DimensionMismatchException the the length of the arrays does not match
     * @throws NotPositiveException if any entries in <code>observed1</code> or
     * <code>observed2</code> are negative
     * @throws ZeroException if either all counts of <code>observed1</code> or
     * <code>observed2</code> are zero, or if the count at the same index is zero
     * for both arrays
     * @throws MaxCountExceededException if an error occurs computing the p-value
     * @since 1.2
     */
    public double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * <p>Performs a Chi-Square two sample test comparing two binned data
     * sets. The test evaluates the null hypothesis that the two lists of
     * observed counts conform to the same frequency distribution, with
     * significance level <code>alpha</code>.  Returns true iff the null
     * hypothesis can be rejected with 100 * (1 - alpha) percent confidence.
     * </p>
     * <p>See {@link #chiSquareDataSetsComparison(long[], long[])} for
     * details on the formula used to compute the Chisquare statistic used
     * in the test. The degrees of of freedom used to perform the test is
     * one less than the common length of the input observed count arrays.
     * </p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Observed counts must be non-negative.
     * </li>
     * <li>Observed counts for a specific bin must not both be zero.
     * </li>
     * <li>Observed counts for a specific sample must not all be 0.
     * </li>
     * <li>The arrays <code>observed1</code> and <code>observed2</code> must
     * have the same length and their common length must be at least 2.
     * </li>
     * <li> <code> 0 < alpha < 0.5 </code>
     * </li></ul><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param observed1 array of observed frequency counts of the first data set
     * @param observed2 array of observed frequency counts of the second data set
     * @param alpha significance level of the test
     * @return true iff null hypothesis can be rejected with confidence
     * 1 - alpha
     * @throws DimensionMismatchException the the length of the arrays does not match
     * @throws NotPositiveException if any entries in <code>observed1</code> or
     * <code>observed2</code> are negative
     * @throws ZeroException if either all counts of <code>observed1</code> or
     * <code>observed2</code> are zero, or if the count at the same index is zero
     * for both arrays
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs performing the test
     * @since 1.2
     */
    public boolean chiSquareTestDataSetsComparison(final long[] observed1, final long[] observed2, final double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        // STUB: not implemented
        return false;
    }

    /**
     * Checks to make sure that the input long[][] array is rectangular,
     * has at least 2 rows and 2 columns, and has all non-negative entries.
     *
     * @param in input 2-way table to check
     * @throws NullArgumentException if the array is null
     * @throws DimensionMismatchException if the array is not valid
     * @throws NotPositiveException if the array contains any negative entries
     */
    private void checkArray(final long[][] in) throws NullArgumentException, DimensionMismatchException, NotPositiveException {
        if (in.length < 2) {
            throw new DimensionMismatchException(in.length, 2);
        }
        if (in[0].length < 2) {
            throw new DimensionMismatchException(in[0].length, 2);
        }
        MathArrays.checkRectangular(in);
        MathArrays.checkNonNegative(in);
    }
}
