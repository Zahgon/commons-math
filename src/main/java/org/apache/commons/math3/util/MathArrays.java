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
package org.apache.commons.math3.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/**
 * Arrays utilities.
 *
 * @since 3.0
 */
public class MathArrays {

    /**
     * Private constructor.
     */
    private MathArrays() {
    }

    /**
     * Real-valued function that operate on an array or a part of it.
     * @since 3.1
     */
    public interface Function {

        /**
         * Operates on an entire array.
         *
         * @param array Array to operate on.
         * @return the result of the operation.
         */
        double evaluate(double[] array);

        /**
         * @param array Array to operate on.
         * @param startIndex Index of the first element to take into account.
         * @param numElements Number of elements to take into account.
         * @return the result of the operation.
         */
        double evaluate(double[] array, int startIndex, int numElements);
    }

    /**
     * Create a copy of an array scaled by a value.
     *
     * @param arr Array to scale.
     * @param val Scalar.
     * @return scaled copy of array with each entry multiplied by val.
     * @since 3.2
     */
    public static double[] scale(double val, final double[] arr) {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Multiply each element of an array by a value.</p>
     *
     * <p>The array is modified in place (no copy is created).</p>
     *
     * @param arr Array to scale
     * @param val Scalar
     * @since 3.2
     */
    public static void scaleInPlace(double val, final double[] arr) {
        // STUB: not implemented
    }

    /**
     * Creates an array whose contents will be the element-by-element
     * addition of the arguments.
     *
     * @param a First term of the addition.
     * @param b Second term of the addition.
     * @return a new array {@code r} where {@code r[i] = a[i] + b[i]}.
     * @throws DimensionMismatchException if the array lengths differ.
     * @since 3.1
     */
    public static double[] ebeAdd(double[] a, double[] b) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Creates an array whose contents will be the element-by-element
     * subtraction of the second argument from the first.
     *
     * @param a First term.
     * @param b Element to be subtracted.
     * @return a new array {@code r} where {@code r[i] = a[i] - b[i]}.
     * @throws DimensionMismatchException if the array lengths differ.
     * @since 3.1
     */
    public static double[] ebeSubtract(double[] a, double[] b) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Creates an array whose contents will be the element-by-element
     * multiplication of the arguments.
     *
     * @param a First factor of the multiplication.
     * @param b Second factor of the multiplication.
     * @return a new array {@code r} where {@code r[i] = a[i] * b[i]}.
     * @throws DimensionMismatchException if the array lengths differ.
     * @since 3.1
     */
    public static double[] ebeMultiply(double[] a, double[] b) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Creates an array whose contents will be the element-by-element
     * division of the first argument by the second.
     *
     * @param a Numerator of the division.
     * @param b Denominator of the division.
     * @return a new array {@code r} where {@code r[i] = a[i] / b[i]}.
     * @throws DimensionMismatchException if the array lengths differ.
     * @since 3.1
     */
    public static double[] ebeDivide(double[] a, double[] b) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Calculates the L<sub>1</sub> (sum of abs) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>1</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static double distance1(double[] p1, double[] p2) throws DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Calculates the L<sub>1</sub> (sum of abs) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>1</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static int distance1(int[] p1, int[] p2) throws DimensionMismatchException {
        // STUB: not implemented
        return 0;
    }

    /**
     * Calculates the L<sub>2</sub> (Euclidean) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>2</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static double distance(double[] p1, double[] p2) throws DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Calculates the cosine of the angle between two vectors.
     *
     * @param v1 Cartesian coordinates of the first vector.
     * @param v2 Cartesian coordinates of the second vector.
     * @return the cosine of the angle between the vectors.
     * @since 3.6
     */
    public static double cosAngle(double[] v1, double[] v2) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Calculates the L<sub>2</sub> (Euclidean) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>2</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static double distance(int[] p1, int[] p2) throws DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Calculates the L<sub>&infin;</sub> (max of abs) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>&infin;</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static double distanceInf(double[] p1, double[] p2) throws DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Calculates the L<sub>&infin;</sub> (max of abs) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>&infin;</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static int distanceInf(int[] p1, int[] p2) throws DimensionMismatchException {
        // STUB: not implemented
        return 0;
    }

    /**
     * Specification of ordering direction.
     */
    public enum OrderDirection {

        /**
         * Constant for increasing direction.
         */
        INCREASING,
        /**
         * Constant for decreasing direction.
         */
        DECREASING
    }

    /**
     * Check that an array is monotonically increasing or decreasing.
     *
     * @param <T> the type of the elements in the specified array
     * @param val Values.
     * @param dir Ordering direction.
     * @param strict Whether the order should be strict.
     * @return {@code true} if sorted, {@code false} otherwise.
     */
    public static <T extends Comparable<? super T>> boolean isMonotonic(T[] val, OrderDirection dir, boolean strict) {
        // STUB: not implemented
        return false;
    }

    /**
     * Check that an array is monotonically increasing or decreasing.
     *
     * @param val Values.
     * @param dir Ordering direction.
     * @param strict Whether the order should be strict.
     * @return {@code true} if sorted, {@code false} otherwise.
     */
    public static boolean isMonotonic(double[] val, OrderDirection dir, boolean strict) {
        // STUB: not implemented
        return false;
    }

    /**
     * Check that both arrays have the same length.
     *
     * @param a Array.
     * @param b Array.
     * @param abort Whether to throw an exception if the check fails.
     * @return {@code true} if the arrays have the same length.
     * @throws DimensionMismatchException if the lengths differ and
     * {@code abort} is {@code true}.
     * @since 3.6
     */
    public static boolean checkEqualLength(double[] a, double[] b, boolean abort) {
        // STUB: not implemented
        return false;
    }

    /**
     * Check that both arrays have the same length.
     *
     * @param a Array.
     * @param b Array.
     * @throws DimensionMismatchException if the lengths differ.
     * @since 3.6
     */
    public static void checkEqualLength(double[] a, double[] b) {
        // STUB: not implemented
    }

    /**
     * Check that both arrays have the same length.
     *
     * @param a Array.
     * @param b Array.
     * @param abort Whether to throw an exception if the check fails.
     * @return {@code true} if the arrays have the same length.
     * @throws DimensionMismatchException if the lengths differ and
     * {@code abort} is {@code true}.
     * @since 3.6
     */
    public static boolean checkEqualLength(int[] a, int[] b, boolean abort) {
        // STUB: not implemented
        return false;
    }

    /**
     * Check that both arrays have the same length.
     *
     * @param a Array.
     * @param b Array.
     * @throws DimensionMismatchException if the lengths differ.
     * @since 3.6
     */
    public static void checkEqualLength(int[] a, int[] b) {
        // STUB: not implemented
    }

    /**
     * Check that the given array is sorted.
     *
     * @param val Values.
     * @param dir Ordering direction.
     * @param strict Whether the order should be strict.
     * @param abort Whether to throw an exception if the check fails.
     * @return {@code true} if the array is sorted.
     * @throws NonMonotonicSequenceException if the array is not sorted
     * and {@code abort} is {@code true}.
     */
    public static boolean checkOrder(double[] val, OrderDirection dir, boolean strict, boolean abort) throws NonMonotonicSequenceException {
        // STUB: not implemented
        return false;
    }

    /**
     * Check that the given array is sorted.
     *
     * @param val Values.
     * @param dir Ordering direction.
     * @param strict Whether the order should be strict.
     * @throws NonMonotonicSequenceException if the array is not sorted.
     * @since 2.2
     */
    public static void checkOrder(double[] val, OrderDirection dir, boolean strict) throws NonMonotonicSequenceException {
        // STUB: not implemented
    }

    /**
     * Check that the given array is sorted in strictly increasing order.
     *
     * @param val Values.
     * @throws NonMonotonicSequenceException if the array is not sorted.
     * @since 2.2
     */
    public static void checkOrder(double[] val) throws NonMonotonicSequenceException {
        // STUB: not implemented
    }

    /**
     * Throws DimensionMismatchException if the input array is not rectangular.
     *
     * @param in array to be tested
     * @throws NullArgumentException if input array is null
     * @throws DimensionMismatchException if input array is not rectangular
     * @since 3.1
     */
    public static void checkRectangular(final long[][] in) throws NullArgumentException, DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Check that all entries of the input array are strictly positive.
     *
     * @param in Array to be tested
     * @throws NotStrictlyPositiveException if any entries of the array are not
     * strictly positive.
     * @since 3.1
     */
    public static void checkPositive(final double[] in) throws NotStrictlyPositiveException {
        // STUB: not implemented
    }

    /**
     * Check that no entry of the input array is {@code NaN}.
     *
     * @param in Array to be tested.
     * @throws NotANumberException if an entry is {@code NaN}.
     * @since 3.4
     */
    public static void checkNotNaN(final double[] in) throws NotANumberException {
        // STUB: not implemented
    }

    /**
     * Check that all entries of the input array are >= 0.
     *
     * @param in Array to be tested
     * @throws NotPositiveException if any array entries are less than 0.
     * @since 3.1
     */
    public static void checkNonNegative(final long[] in) throws NotPositiveException {
        // STUB: not implemented
    }

    /**
     * Check all entries of the input array are >= 0.
     *
     * @param in Array to be tested
     * @throws NotPositiveException if any array entries are less than 0.
     * @since 3.1
     */
    public static void checkNonNegative(final long[][] in) throws NotPositiveException {
        // STUB: not implemented
    }

    /**
     * Returns the Cartesian norm (2-norm), handling both overflow and underflow.
     * Translation of the minpack enorm subroutine.
     *
     * The redistribution policy for MINPACK is available
     * <a href="http://www.netlib.org/minpack/disclaimer">here</a>, for
     * convenience, it is reproduced below.</p>
     *
     * <table border="0" width="80%" cellpadding="10" align="center" bgcolor="#E0E0E0">
     * <tr><td>
     *    Minpack Copyright Notice (1999) University of Chicago.
     *    All rights reserved
     * </td></tr>
     * <tr><td>
     * Redistribution and use in source and binary forms, with or without
     * modification, are permitted provided that the following conditions
     * are met:
     * <ol>
     *  <li>Redistributions of source code must retain the above copyright
     *      notice, this list of conditions and the following disclaimer.</li>
     * <li>Redistributions in binary form must reproduce the above
     *     copyright notice, this list of conditions and the following
     *     disclaimer in the documentation and/or other materials provided
     *     with the distribution.</li>
     * <li>The end-user documentation included with the redistribution, if any,
     *     must include the following acknowledgment:
     *     {@code This product includes software developed by the University of
     *           Chicago, as Operator of Argonne National Laboratory.}
     *     Alternately, this acknowledgment may appear in the software itself,
     *     if and wherever such third-party acknowledgments normally appear.</li>
     * <li><strong>WARRANTY DISCLAIMER. THE SOFTWARE IS SUPPLIED "AS IS"
     *     WITHOUT WARRANTY OF ANY KIND. THE COPYRIGHT HOLDER, THE
     *     UNITED STATES, THE UNITED STATES DEPARTMENT OF ENERGY, AND
     *     THEIR EMPLOYEES: (1) DISCLAIM ANY WARRANTIES, EXPRESS OR
     *     IMPLIED, INCLUDING BUT NOT LIMITED TO ANY IMPLIED WARRANTIES
     *     OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE
     *     OR NON-INFRINGEMENT, (2) DO NOT ASSUME ANY LEGAL LIABILITY
     *     OR RESPONSIBILITY FOR THE ACCURACY, COMPLETENESS, OR
     *     USEFULNESS OF THE SOFTWARE, (3) DO NOT REPRESENT THAT USE OF
     *     THE SOFTWARE WOULD NOT INFRINGE PRIVATELY OWNED RIGHTS, (4)
     *     DO NOT WARRANT THAT THE SOFTWARE WILL FUNCTION
     *     UNINTERRUPTED, THAT IT IS ERROR-FREE OR THAT ANY ERRORS WILL
     *     BE CORRECTED.</strong></li>
     * <li><strong>LIMITATION OF LIABILITY. IN NO EVENT WILL THE COPYRIGHT
     *     HOLDER, THE UNITED STATES, THE UNITED STATES DEPARTMENT OF
     *     ENERGY, OR THEIR EMPLOYEES: BE LIABLE FOR ANY INDIRECT,
     *     INCIDENTAL, CONSEQUENTIAL, SPECIAL OR PUNITIVE DAMAGES OF
     *     ANY KIND OR NATURE, INCLUDING BUT NOT LIMITED TO LOSS OF
     *     PROFITS OR LOSS OF DATA, FOR ANY REASON WHATSOEVER, WHETHER
     *     SUCH LIABILITY IS ASSERTED ON THE BASIS OF CONTRACT, TORT
     *     (INCLUDING NEGLIGENCE OR STRICT LIABILITY), OR OTHERWISE,
     *     EVEN IF ANY OF SAID PARTIES HAS BEEN WARNED OF THE
     *     POSSIBILITY OF SUCH LOSS OR DAMAGES.</strong></li>
     * <ol></td></tr>
     * </table>
     *
     * @param v Vector of doubles.
     * @return the 2-norm of the vector.
     * @since 2.2
     */
    public static double safeNorm(double[] v) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * A helper data structure holding a double and an integer value.
     */
    private static class PairDoubleInteger {

        /**
         * Key
         */
        private final double key;

        /**
         * Value
         */
        private final int value;

        /**
         * @param key Key.
         * @param value Value.
         */
        PairDoubleInteger(double key, int value) {
            this.key = key;
            this.value = value;
        }

        /**
         * @return the key.
         */
        public double getKey() {
            // STUB: not implemented
            return 0.0;
        }

        /**
         * @return the value.
         */
        public int getValue() {
            // STUB: not implemented
            return 0;
        }
    }

    /**
     * Sort an array in ascending order in place and perform the same reordering
     * of entries on other arrays. For example, if
     * {@code x = [3, 1, 2], y = [1, 2, 3]} and {@code z = [0, 5, 7]}, then
     * {@code sortInPlace(x, y, z)} will update {@code x} to {@code [1, 2, 3]},
     * {@code y} to {@code [2, 3, 1]} and {@code z} to {@code [5, 7, 0]}.
     *
     * @param x Array to be sorted and used as a pattern for permutation
     * of the other arrays.
     * @param yList Set of arrays whose permutations of entries will follow
     * those performed on {@code x}.
     * @throws DimensionMismatchException if any {@code y} is not the same
     * size as {@code x}.
     * @throws NullArgumentException if {@code x} or any {@code y} is null.
     * @since 3.0
     */
    public static void sortInPlace(double[] x, double[]... yList) throws DimensionMismatchException, NullArgumentException {
        // STUB: not implemented
    }

    /**
     * Sort an array in place and perform the same reordering of entries on
     * other arrays.  This method works the same as the other
     * {@link #sortInPlace(double[], double[][]) sortInPlace} method, but
     * allows the order of the sort to be provided in the {@code dir}
     * parameter.
     *
     * @param x Array to be sorted and used as a pattern for permutation
     * of the other arrays.
     * @param dir Order direction.
     * @param yList Set of arrays whose permutations of entries will follow
     * those performed on {@code x}.
     * @throws DimensionMismatchException if any {@code y} is not the same
     * size as {@code x}.
     * @throws NullArgumentException if {@code x} or any {@code y} is null
     * @since 3.0
     */
    public static void sortInPlace(double[] x, final OrderDirection dir, double[]... yList) throws NullArgumentException, DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Creates a copy of the {@code source} array.
     *
     * @param source Array to be copied.
     * @return the copied array.
     */
    public static int[] copyOf(int[] source) {
        // STUB: not implemented
        return null;
    }

    /**
     * Creates a copy of the {@code source} array.
     *
     * @param source Array to be copied.
     * @return the copied array.
     */
    public static double[] copyOf(double[] source) {
        // STUB: not implemented
        return null;
    }

    /**
     * Creates a copy of the {@code source} array.
     *
     * @param source Array to be copied.
     * @param len Number of entries to copy. If smaller then the source
     * length, the copy will be truncated, if larger it will padded with
     * zeroes.
     * @return the copied array.
     */
    public static int[] copyOf(int[] source, int len) {
        // STUB: not implemented
        return null;
    }

    /**
     * Creates a copy of the {@code source} array.
     *
     * @param source Array to be copied.
     * @param len Number of entries to copy. If smaller then the source
     * length, the copy will be truncated, if larger it will padded with
     * zeroes.
     * @return the copied array.
     */
    public static double[] copyOf(double[] source, int len) {
        // STUB: not implemented
        return null;
    }

    /**
     * Creates a copy of the {@code source} array.
     *
     * @param source Array to be copied.
     * @param from Initial index of the range to be copied, inclusive.
     * @param to Final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return the copied array.
     */
    public static double[] copyOfRange(double[] source, int from, int to) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute a linear combination accurately.
     * This method computes the sum of the products
     * <code>a<sub>i</sub> b<sub>i</sub></code> to high accuracy.
     * It does so by using specific multiplication and addition algorithms to
     * preserve accuracy and reduce cancellation effects.
     * <br/>
     * It is based on the 2005 paper
     * <a href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita, Siegfried M. Rump,
     * and Shin'ichi Oishi published in SIAM J. Sci. Comput.
     *
     * @param a Factors.
     * @param b Factors.
     * @return <code>&Sigma;<sub>i</sub> a<sub>i</sub> b<sub>i</sub></code>.
     * @throws DimensionMismatchException if arrays dimensions don't match
     */
    public static double linearCombination(final double[] a, final double[] b) throws DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Compute a linear combination accurately.
     * <p>
     * This method computes a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub> to high accuracy. It does
     * so by using specific multiplication and addition algorithms to
     * preserve accuracy and reduce cancellation effects. It is based
     * on the 2005 paper <a
     * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita,
     * Siegfried M. Rump, and Shin'ichi Oishi published in SIAM J. Sci. Comput.
     * </p>
     * @param a1 first factor of the first term
     * @param b1 second factor of the first term
     * @param a2 first factor of the second term
     * @param b2 second factor of the second term
     * @return a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub>
     * @see #linearCombination(double, double, double, double, double, double)
     * @see #linearCombination(double, double, double, double, double, double, double, double)
     */
    public static double linearCombination(final double a1, final double b1, final double a2, final double b2) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Compute a linear combination accurately.
     * <p>
     * This method computes a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub> + a<sub>3</sub>&times;b<sub>3</sub>
     * to high accuracy. It does so by using specific multiplication and
     * addition algorithms to preserve accuracy and reduce cancellation effects.
     * It is based on the 2005 paper <a
     * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita,
     * Siegfried M. Rump, and Shin'ichi Oishi published in SIAM J. Sci. Comput.
     * </p>
     * @param a1 first factor of the first term
     * @param b1 second factor of the first term
     * @param a2 first factor of the second term
     * @param b2 second factor of the second term
     * @param a3 first factor of the third term
     * @param b3 second factor of the third term
     * @return a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub> + a<sub>3</sub>&times;b<sub>3</sub>
     * @see #linearCombination(double, double, double, double)
     * @see #linearCombination(double, double, double, double, double, double, double, double)
     */
    public static double linearCombination(final double a1, final double b1, final double a2, final double b2, final double a3, final double b3) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Compute a linear combination accurately.
     * <p>
     * This method computes a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub> + a<sub>3</sub>&times;b<sub>3</sub> +
     * a<sub>4</sub>&times;b<sub>4</sub>
     * to high accuracy. It does so by using specific multiplication and
     * addition algorithms to preserve accuracy and reduce cancellation effects.
     * It is based on the 2005 paper <a
     * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita,
     * Siegfried M. Rump, and Shin'ichi Oishi published in SIAM J. Sci. Comput.
     * </p>
     * @param a1 first factor of the first term
     * @param b1 second factor of the first term
     * @param a2 first factor of the second term
     * @param b2 second factor of the second term
     * @param a3 first factor of the third term
     * @param b3 second factor of the third term
     * @param a4 first factor of the third term
     * @param b4 second factor of the third term
     * @return a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub> + a<sub>3</sub>&times;b<sub>3</sub> +
     * a<sub>4</sub>&times;b<sub>4</sub>
     * @see #linearCombination(double, double, double, double)
     * @see #linearCombination(double, double, double, double, double, double)
     */
    public static double linearCombination(final double a1, final double b1, final double a2, final double b2, final double a3, final double b3, final double a4, final double b4) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns true iff both arguments are null or have same dimensions and all
     * their elements are equal as defined by
     * {@link Precision#equals(float,float)}.
     *
     * @param x first array
     * @param y second array
     * @return true if the values are both null or have same dimension
     * and equal elements.
     */
    public static boolean equals(float[] x, float[] y) {
        // STUB: not implemented
        return false;
    }

    /**
     * Returns true iff both arguments are null or have same dimensions and all
     * their elements are equal as defined by
     * {@link Precision#equalsIncludingNaN(double,double) this method}.
     *
     * @param x first array
     * @param y second array
     * @return true if the values are both null or have same dimension and
     * equal elements
     * @since 2.2
     */
    public static boolean equalsIncludingNaN(float[] x, float[] y) {
        // STUB: not implemented
        return false;
    }

    /**
     * Returns {@code true} iff both arguments are {@code null} or have same
     * dimensions and all their elements are equal as defined by
     * {@link Precision#equals(double,double)}.
     *
     * @param x First array.
     * @param y Second array.
     * @return {@code true} if the values are both {@code null} or have same
     * dimension and equal elements.
     */
    public static boolean equals(double[] x, double[] y) {
        // STUB: not implemented
        return false;
    }

    /**
     * Returns {@code true} iff both arguments are {@code null} or have same
     * dimensions and all their elements are equal as defined by
     * {@link Precision#equalsIncludingNaN(double,double) this method}.
     *
     * @param x First array.
     * @param y Second array.
     * @return {@code true} if the values are both {@code null} or have same
     * dimension and equal elements.
     * @since 2.2
     */
    public static boolean equalsIncludingNaN(double[] x, double[] y) {
        // STUB: not implemented
        return false;
    }

    /**
     * Normalizes an array to make it sum to a specified value.
     * Returns the result of the transformation
     * <pre>
     *    x |-> x * normalizedSum / sum
     * </pre>
     * applied to each non-NaN element x of the input array, where sum is the
     * sum of the non-NaN entries in the input array.
     * <p>
     * Throws IllegalArgumentException if {@code normalizedSum} is infinite
     * or NaN and ArithmeticException if the input array contains any infinite elements
     * or sums to 0.
     * <p>
     * Ignores (i.e., copies unchanged to the output array) NaNs in the input array.
     *
     * @param values Input array to be normalized
     * @param normalizedSum Target sum for the normalized array
     * @return the normalized array.
     * @throws MathArithmeticException if the input array contains infinite
     * elements or sums to zero.
     * @throws MathIllegalArgumentException if the target sum is infinite or {@code NaN}.
     * @since 2.1
     */
    public static double[] normalizeArray(double[] values, double normalizedSum) throws MathIllegalArgumentException, MathArithmeticException {
        // STUB: not implemented
        return null;
    }

    /**
     * Build an array of elements.
     * <p>
     * Arrays are filled with field.getZero()
     *
     * @param <T> the type of the field elements
     * @param field field to which array elements belong
     * @param length of the array
     * @return a new array
     * @since 3.2
     */
    public static <T> T[] buildArray(final Field<T> field, final int length) {
        // STUB: not implemented
        return null;
    }

    /**
     * Build a double dimension  array of elements.
     * <p>
     * Arrays are filled with field.getZero()
     *
     * @param <T> the type of the field elements
     * @param field field to which array elements belong
     * @param rows number of rows in the array
     * @param columns number of columns (may be negative to build partial
     * arrays in the same way <code>new Field[rows][]</code> works)
     * @return a new array
     * @since 3.2
     */
    @SuppressWarnings("unchecked")
    public static <T> T[][] buildArray(final Field<T> field, final int rows, final int columns) {
        // STUB: not implemented
        return null;
    }

    /**
     * Calculates the <a href="http://en.wikipedia.org/wiki/Convolution">
     * convolution</a> between two sequences.
     * <p>
     * The solution is obtained via straightforward computation of the
     * convolution sum (and not via FFT). Whenever the computation needs
     * an element that would be located at an index outside the input arrays,
     * the value is assumed to be zero.
     *
     * @param x First sequence.
     * Typically, this sequence will represent an input signal to a system.
     * @param h Second sequence.
     * Typically, this sequence will represent the impulse response of the system.
     * @return the convolution of {@code x} and {@code h}.
     * This array's length will be {@code x.length + h.length - 1}.
     * @throws NullArgumentException if either {@code x} or {@code h} is {@code null}.
     * @throws NoDataException if either {@code x} or {@code h} is empty.
     *
     * @since 3.3
     */
    public static double[] convolve(double[] x, double[] h) throws NullArgumentException, NoDataException {
        // STUB: not implemented
        return null;
    }

    /**
     * Specification for indicating that some operation applies
     * before or after a given index.
     */
    public enum Position {

        /**
         * Designates the beginning of the array (near index 0).
         */
        HEAD,
        /**
         * Designates the end of the array.
         */
        TAIL
    }

    /**
     * Shuffle the entries of the given array.
     * The {@code start} and {@code pos} parameters select which portion
     * of the array is randomized and which is left untouched.
     *
     * @see #shuffle(int[],int,Position,RandomGenerator)
     *
     * @param list Array whose entries will be shuffled (in-place).
     * @param start Index at which shuffling begins.
     * @param pos Shuffling is performed for index positions between
     * {@code start} and either the end (if {@link Position#TAIL})
     * or the beginning (if {@link Position#HEAD}) of the array.
     */
    public static void shuffle(int[] list, int start, Position pos) {
        // STUB: not implemented
    }

    /**
     * Shuffle the entries of the given array, using the
     * <a href="http://en.wikipedia.org/wiki/Fisher–Yates_shuffle#The_modern_algorithm">
     * Fisher–Yates</a> algorithm.
     * The {@code start} and {@code pos} parameters select which portion
     * of the array is randomized and which is left untouched.
     *
     * @param list Array whose entries will be shuffled (in-place).
     * @param start Index at which shuffling begins.
     * @param pos Shuffling is performed for index positions between
     * {@code start} and either the end (if {@link Position#TAIL})
     * or the beginning (if {@link Position#HEAD}) of the array.
     * @param rng Random number generator.
     */
    public static void shuffle(int[] list, int start, Position pos, RandomGenerator rng) {
        // STUB: not implemented
    }

    /**
     * Shuffle the entries of the given array.
     *
     * @see #shuffle(int[],int,Position,RandomGenerator)
     *
     * @param list Array whose entries will be shuffled (in-place).
     * @param rng Random number generator.
     */
    public static void shuffle(int[] list, RandomGenerator rng) {
        // STUB: not implemented
    }

    /**
     * Shuffle the entries of the given array.
     *
     * @see #shuffle(int[],int,Position,RandomGenerator)
     *
     * @param list Array whose entries will be shuffled (in-place).
     */
    public static void shuffle(int[] list) {
        // STUB: not implemented
    }

    /**
     * Returns an array representing the natural number {@code n}.
     *
     * @param n Natural number.
     * @return an array whose entries are the numbers 0, 1, ..., {@code n}-1.
     * If {@code n == 0}, the returned array is empty.
     */
    public static int[] natural(int n) {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns an array of {@code size} integers starting at {@code start},
     * skipping {@code stride} numbers.
     *
     * @param size Natural number.
     * @param start Natural number.
     * @param stride Natural number.
     * @return an array whose entries are the numbers
     * {@code start, start + stride, ..., start + (size - 1) * stride}.
     * If {@code size == 0}, the returned array is empty.
     *
     * @since 3.4
     */
    public static int[] sequence(int size, int start, int stride) {
        // STUB: not implemented
        return null;
    }

    /**
     * This method is used
     * to verify that the input parameters designate a subarray of positive length.
     * <p>
     * <ul>
     * <li>returns <code>true</code> iff the parameters designate a subarray of
     * positive length</li>
     * <li>throws <code>MathIllegalArgumentException</code> if the array is null or
     * or the indices are invalid</li>
     * <li>returns <code>false</li> if the array is non-null, but
     * <code>length</code> is 0.
     * </ul></p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return true if the parameters are valid and designate a subarray of positive length
     * @throws MathIllegalArgumentException if the indices are invalid or the array is null
     * @since 3.3
     */
    public static boolean verifyValues(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        // STUB: not implemented
        return false;
    }

    /**
     * This method is used
     * to verify that the input parameters designate a subarray of positive length.
     * <p>
     * <ul>
     * <li>returns <code>true</code> iff the parameters designate a subarray of
     * non-negative length</li>
     * <li>throws <code>IllegalArgumentException</code> if the array is null or
     * or the indices are invalid</li>
     * <li>returns <code>false</li> if the array is non-null, but
     * <code>length</code> is 0 unless <code>allowEmpty</code> is <code>true</code>
     * </ul></p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @param allowEmpty if <code>true</code> then zero length arrays are allowed
     * @return true if the parameters are valid
     * @throws MathIllegalArgumentException if the indices are invalid or the array is null
     * @since 3.3
     */
    public static boolean verifyValues(final double[] values, final int begin, final int length, final boolean allowEmpty) throws MathIllegalArgumentException {
        // STUB: not implemented
        return false;
    }

    /**
     * This method is used
     * to verify that the begin and length parameters designate a subarray of positive length
     * and the weights are all non-negative, non-NaN, finite, and not all zero.
     * <p>
     * <ul>
     * <li>returns <code>true</code> iff the parameters designate a subarray of
     * positive length and the weights array contains legitimate values.</li>
     * <li>throws <code>IllegalArgumentException</code> if any of the following are true:
     * <ul><li>the values array is null</li>
     *     <li>the weights array is null</li>
     *     <li>the weights array does not have the same length as the values array</li>
     *     <li>the weights array contains one or more infinite values</li>
     *     <li>the weights array contains one or more NaN values</li>
     *     <li>the weights array contains negative values</li>
     *     <li>the start and length arguments do not determine a valid array</li></ul>
     * </li>
     * <li>returns <code>false</li> if the array is non-null, but
     * <code>length</code> is 0.
     * </ul></p>
     *
     * @param values the input array
     * @param weights the weights array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return true if the parameters are valid and designate a subarray of positive length
     * @throws MathIllegalArgumentException if the indices are invalid or the array is null
     * @since 3.3
     */
    public static boolean verifyValues(final double[] values, final double[] weights, final int begin, final int length) throws MathIllegalArgumentException {
        // STUB: not implemented
        return false;
    }

    /**
     * This method is used
     * to verify that the begin and length parameters designate a subarray of positive length
     * and the weights are all non-negative, non-NaN, finite, and not all zero.
     * <p>
     * <ul>
     * <li>returns <code>true</code> iff the parameters designate a subarray of
     * non-negative length and the weights array contains legitimate values.</li>
     * <li>throws <code>MathIllegalArgumentException</code> if any of the following are true:
     * <ul><li>the values array is null</li>
     *     <li>the weights array is null</li>
     *     <li>the weights array does not have the same length as the values array</li>
     *     <li>the weights array contains one or more infinite values</li>
     *     <li>the weights array contains one or more NaN values</li>
     *     <li>the weights array contains negative values</li>
     *     <li>the start and length arguments do not determine a valid array</li></ul>
     * </li>
     * <li>returns <code>false</li> if the array is non-null, but
     * <code>length</code> is 0 unless <code>allowEmpty</code> is <code>true</code>.
     * </ul></p>
     *
     * @param values the input array.
     * @param weights the weights array.
     * @param begin index of the first array element to include.
     * @param length the number of elements to include.
     * @param allowEmpty if {@code true} than allow zero length arrays to pass.
     * @return {@code true} if the parameters are valid.
     * @throws NullArgumentException if either of the arrays are null
     * @throws MathIllegalArgumentException if the array indices are not valid,
     * the weights array contains NaN, infinite or negative elements, or there
     * are no positive weights.
     * @since 3.3
     */
    public static boolean verifyValues(final double[] values, final double[] weights, final int begin, final int length, final boolean allowEmpty) throws MathIllegalArgumentException {
        // STUB: not implemented
        return false;
    }

    /**
     * Concatenates a sequence of arrays. The return array consists of the
     * entries of the input arrays concatenated in the order they appear in
     * the argument list.  Null arrays cause NullPointerExceptions; zero
     * length arrays are allowed (contributing nothing to the output array).
     *
     * @param x list of double[] arrays to concatenate
     * @return a new array consisting of the entries of the argument arrays
     * @throws NullPointerException if any of the arrays are null
     * @since 3.6
     */
    public static double[] concatenate(double[]... x) {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns an array consisting of the unique values in {@code data}.
     * The return array is sorted in descending order.  Empty arrays
     * are allowed, but null arrays result in NullPointerException.
     * Infinities are allowed.  NaN values are allowed with maximum
     * sort order - i.e., if there are NaN values in {@code data},
     * {@code Double.NaN} will be the first element of the output array,
     * even if the array also contains {@code Double.POSITIVE_INFINITY}.
     *
     * @param data array to scan
     * @return descending list of values included in the input array
     * @throws NullPointerException if data is null
     * @since 3.6
     */
    public static double[] unique(double[] data) {
        // STUB: not implemented
        return null;
    }
}
