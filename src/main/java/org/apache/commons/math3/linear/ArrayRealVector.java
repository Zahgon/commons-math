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
package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.FastMath;

/**
 * This class implements the {@link RealVector} interface with a double array.
 * @since 2.0
 */
public class ArrayRealVector extends RealVector implements Serializable {

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -1097961340710804027L;

    /**
     * Default format.
     */
    private static final RealVectorFormat DEFAULT_FORMAT = RealVectorFormat.getInstance();

    /**
     * Entries of the vector.
     */
    private double[] data;

    /**
     * Build a 0-length vector.
     * Zero-length vectors may be used to initialized construction of vectors
     * by data gathering. We start with zero-length and use either the {@link
     * #ArrayRealVector(ArrayRealVector, ArrayRealVector)} constructor
     * or one of the {@code append} method ({@link #append(double)},
     * {@link #append(ArrayRealVector)}) to gather data into this vector.
     */
    public ArrayRealVector() {
        data = new double[0];
    }

    /**
     * Construct a vector of zeroes.
     *
     * @param size Size of the vector.
     */
    public ArrayRealVector(int size) {
        data = new double[size];
    }

    /**
     * Construct a vector with preset values.
     *
     * @param size Size of the vector
     * @param preset All entries will be set with this value.
     */
    public ArrayRealVector(int size, double preset) {
        data = new double[size];
        Arrays.fill(data, preset);
    }

    /**
     * Construct a vector from an array, copying the input array.
     *
     * @param d Array.
     */
    public ArrayRealVector(double[] d) {
        data = d.clone();
    }

    /**
     * Create a new ArrayRealVector using the input array as the underlying
     * data array.
     * If an array is built specially in order to be embedded in a
     * ArrayRealVector and not used directly, the {@code copyArray} may be
     * set to {@code false}. This will prevent the copying and improve
     * performance as no new array will be built and no data will be copied.
     *
     * @param d Data for the new vector.
     * @param copyArray if {@code true}, the input array will be copied,
     * otherwise it will be referenced.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @see #ArrayRealVector(double[])
     */
    public ArrayRealVector(double[] d, boolean copyArray) throws NullArgumentException {
        if (d == null) {
            throw new NullArgumentException();
        }
        data = copyArray ? d.clone() : d;
    }

    /**
     * Construct a vector from part of a array.
     *
     * @param d Array.
     * @param pos Position of first entry.
     * @param size Number of entries to copy.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @throws NumberIsTooLargeException if the size of {@code d} is less
     * than {@code pos + size}.
     */
    public ArrayRealVector(double[] d, int pos, int size) throws NullArgumentException, NumberIsTooLargeException {
        if (d == null) {
            throw new NullArgumentException();
        }
        if (d.length < pos + size) {
            throw new NumberIsTooLargeException(pos + size, d.length, true);
        }
        data = new double[size];
        System.arraycopy(d, pos, data, 0, size);
    }

    /**
     * Construct a vector from an array.
     *
     * @param d Array of {@code Double}s.
     */
    public ArrayRealVector(Double[] d) {
        data = new double[d.length];
        for (int i = 0; i < d.length; i++) {
            data[i] = d[i].doubleValue();
        }
    }

    /**
     * Construct a vector from part of an array.
     *
     * @param d Array.
     * @param pos Position of first entry.
     * @param size Number of entries to copy.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @throws NumberIsTooLargeException if the size of {@code d} is less
     * than {@code pos + size}.
     */
    public ArrayRealVector(Double[] d, int pos, int size) throws NullArgumentException, NumberIsTooLargeException {
        if (d == null) {
            throw new NullArgumentException();
        }
        if (d.length < pos + size) {
            throw new NumberIsTooLargeException(pos + size, d.length, true);
        }
        data = new double[size];
        for (int i = pos; i < pos + size; i++) {
            data[i - pos] = d[i].doubleValue();
        }
    }

    /**
     * Construct a vector from another vector, using a deep copy.
     *
     * @param v vector to copy.
     * @throws NullArgumentException if {@code v} is {@code null}.
     */
    public ArrayRealVector(RealVector v) throws NullArgumentException {
        if (v == null) {
            throw new NullArgumentException();
        }
        data = new double[v.getDimension()];
        for (int i = 0; i < data.length; ++i) {
            data[i] = v.getEntry(i);
        }
    }

    /**
     * Construct a vector from another vector, using a deep copy.
     *
     * @param v Vector to copy.
     * @throws NullArgumentException if {@code v} is {@code null}.
     */
    public ArrayRealVector(ArrayRealVector v) throws NullArgumentException {
        this(v, true);
    }

    /**
     * Construct a vector from another vector.
     *
     * @param v Vector to copy.
     * @param deep If {@code true} perform a deep copy, otherwise perform a
     * shallow copy.
     */
    public ArrayRealVector(ArrayRealVector v, boolean deep) {
        data = deep ? v.data.clone() : v.data;
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     */
    public ArrayRealVector(ArrayRealVector v1, ArrayRealVector v2) {
        data = new double[v1.data.length + v2.data.length];
        System.arraycopy(v1.data, 0, data, 0, v1.data.length);
        System.arraycopy(v2.data, 0, data, v1.data.length, v2.data.length);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     */
    public ArrayRealVector(ArrayRealVector v1, RealVector v2) {
        final int l1 = v1.data.length;
        final int l2 = v2.getDimension();
        data = new double[l1 + l2];
        System.arraycopy(v1.data, 0, data, 0, l1);
        for (int i = 0; i < l2; ++i) {
            data[l1 + i] = v2.getEntry(i);
        }
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     */
    public ArrayRealVector(RealVector v1, ArrayRealVector v2) {
        final int l1 = v1.getDimension();
        final int l2 = v2.data.length;
        data = new double[l1 + l2];
        for (int i = 0; i < l1; ++i) {
            data[i] = v1.getEntry(i);
        }
        System.arraycopy(v2.data, 0, data, l1, l2);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     */
    public ArrayRealVector(ArrayRealVector v1, double[] v2) {
        final int l1 = v1.getDimension();
        final int l2 = v2.length;
        data = new double[l1 + l2];
        System.arraycopy(v1.data, 0, data, 0, l1);
        System.arraycopy(v2, 0, data, l1, l2);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     */
    public ArrayRealVector(double[] v1, ArrayRealVector v2) {
        final int l1 = v1.length;
        final int l2 = v2.getDimension();
        data = new double[l1 + l2];
        System.arraycopy(v1, 0, data, 0, l1);
        System.arraycopy(v2.data, 0, data, l1, l2);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 first vector (will be put in front of the new vector)
     * @param v2 second vector (will be put at back of the new vector)
     */
    public ArrayRealVector(double[] v1, double[] v2) {
        final int l1 = v1.length;
        final int l2 = v2.length;
        data = new double[l1 + l2];
        System.arraycopy(v1, 0, data, 0, l1);
        System.arraycopy(v2, 0, data, l1, l2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector copy() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector add(RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector subtract(RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector map(UnivariateFunction function) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector mapToSelf(UnivariateFunction function) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector mapAddToSelf(double d) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector mapSubtractToSelf(double d) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector mapMultiplyToSelf(double d) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector mapDivideToSelf(double d) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector ebeMultiply(RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector ebeDivide(RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Get a reference to the underlying data array.
     * This method does not make a fresh copy of the underlying data.
     *
     * @return the array of entries.
     */
    public double[] getDataRef() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double dotProduct(RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getNorm() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getL1Norm() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLInfNorm() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDistance(RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getL1Distance(RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLInfDistance(RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealMatrix outerProduct(RealVector v) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getEntry(int index) throws OutOfRangeException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDimension() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector append(RealVector v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Construct a vector by appending a vector to this vector.
     *
     * @param v Vector to append to this one.
     * @return a new vector.
     */
    public ArrayRealVector append(ArrayRealVector v) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector append(double in) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntry(int index, double value) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToEntry(int index, double increment) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSubVector(int index, RealVector v) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * Set a set of consecutive elements.
     *
     * @param index Index of first element to be set.
     * @param v Vector containing the values to set.
     * @throws OutOfRangeException if the index is inconsistent with the vector
     * size.
     */
    public void setSubVector(int index, double[] v) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(double value) {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        // STUB: not implemented
        return null;
    }

    /**
     * Check if instance and specified vectors have the same dimension.
     *
     * @param v Vector to compare instance with.
     * @throws DimensionMismatchException if the vectors do not
     * have the same dimension.
     */
    @Override
    protected void checkVectorDimensions(RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Check if instance dimension is equal to some expected value.
     *
     * @param n Expected dimension.
     * @throws DimensionMismatchException if the dimension is
     * inconsistent with vector size.
     */
    @Override
    protected void checkVectorDimensions(int n) throws DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Check if any coordinate of this vector is {@code NaN}.
     *
     * @return {@code true} if any coordinate of this vector is {@code NaN},
     * {@code false} otherwise.
     */
    @Override
    public boolean isNaN() {
        // STUB: not implemented
        return false;
    }

    /**
     * Check whether any coordinate of this vector is infinite and none
     * are {@code NaN}.
     *
     * @return {@code true} if any coordinate of this vector is infinite and
     * none are {@code NaN}, {@code false} otherwise.
     */
    @Override
    public boolean isInfinite() {
        // STUB: not implemented
        return false;
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
     * {@inheritDoc} All {@code NaN} values have the same hash code.
     */
    @Override
    public int hashCode() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector combine(double a, double b, RealVector y) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector combineToSelf(double a, double b, RealVector y) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInDefaultOrder(final RealVectorPreservingVisitor visitor) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInDefaultOrder(final RealVectorPreservingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * In this implementation, the optimized order is the default order.
     */
    @Override
    public double walkInOptimizedOrder(final RealVectorPreservingVisitor visitor) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * In this implementation, the optimized order is the default order.
     */
    @Override
    public double walkInOptimizedOrder(final RealVectorPreservingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInDefaultOrder(final RealVectorChangingVisitor visitor) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInDefaultOrder(final RealVectorChangingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * In this implementation, the optimized order is the default order.
     */
    @Override
    public double walkInOptimizedOrder(final RealVectorChangingVisitor visitor) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * In this implementation, the optimized order is the default order.
     */
    @Override
    public double walkInOptimizedOrder(final RealVectorChangingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return 0.0;
    }
}
