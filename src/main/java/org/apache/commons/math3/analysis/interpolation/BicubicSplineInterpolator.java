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
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

/**
 * Generates a bicubic interpolating function. Due to numerical accuracy issues this should not
 * be used.
 *
 * @since 2.2
 * @deprecated as of 3.4 replaced by {@link org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator}
 */
@Deprecated
public class BicubicSplineInterpolator implements BivariateGridInterpolator {

    /**
     * Whether to initialize internal data used to compute the analytical
     *        derivatives of the splines.
     */
    private final boolean initializeDerivatives;

    /**
     * Default constructor.
     * The argument {@link #BicubicSplineInterpolator(boolean) initializeDerivatives}
     * is set to {@code false}.
     */
    public BicubicSplineInterpolator() {
        this(false);
    }

    /**
     * Creates an interpolator.
     *
     * @param initializeDerivatives Whether to initialize the internal data
     * needed for calling any of the methods that compute the partial derivatives
     * of the {@link BicubicSplineInterpolatingFunction function} returned from
     * the call to {@link #interpolate(double[],double[],double[][]) interpolate}.
     */
    public BicubicSplineInterpolator(boolean initializeDerivatives) {
        this.initializeDerivatives = initializeDerivatives;
    }

    /**
     * {@inheritDoc}
     */
    public BicubicSplineInterpolatingFunction interpolate(final double[] xval, final double[] yval, final double[][] fval) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException, NumberIsTooSmallException {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the next index of an array, clipping if necessary.
     * It is assumed (but not checked) that {@code i >= 0}.
     *
     * @param i Index.
     * @param max Upper limit of the array.
     * @return the next index.
     */
    private int nextIndex(int i, int max) {
        final int index = i + 1;
        return index < max ? index : index - 1;
    }

    /**
     * Computes the previous index of an array, clipping if necessary.
     * It is assumed (but not checked) that {@code i} is smaller than the size
     * of the array.
     *
     * @param i Index.
     * @return the previous index.
     */
    private int previousIndex(int i) {
        final int index = i - 1;
        return index >= 0 ? index : 0;
    }
}
