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

import java.io.Serializable;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;

/**
 * Implements the <a href=
 * "http://mathworld.wolfram.com/NewtonsDividedDifferenceInterpolationFormula.html">
 * Divided Difference Algorithm</a> for interpolation of real univariate
 * functions. For reference, see <b>Introduction to Numerical Analysis</b>,
 * ISBN 038795452X, chapter 2.
 * <p>
 * The actual code of Neville's evaluation is in PolynomialFunctionLagrangeForm,
 * this class provides an easy-to-use interface to it.</p>
 *
 * @since 1.2
 */
public class DividedDifferenceInterpolator implements UnivariateInterpolator, Serializable {

    /**
     * serializable version identifier
     */
    private static final long serialVersionUID = 107049519551235069L;

    /**
     * Compute an interpolating function for the dataset.
     *
     * @param x Interpolating points array.
     * @param y Interpolating values array.
     * @return a function which interpolates the dataset.
     * @throws DimensionMismatchException if the array lengths are different.
     * @throws NumberIsTooSmallException if the number of points is less than 2.
     * @throws NonMonotonicSequenceException if {@code x} is not sorted in
     * strictly increasing order.
     */
    public PolynomialFunctionNewtonForm interpolate(double[] x, double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        // STUB: not implemented
        return null;
    }

    /**
     * Return a copy of the divided difference array.
     * <p>
     * The divided difference array is defined recursively by <pre>
     * f[x0] = f(x0)
     * f[x0,x1,...,xk] = (f[x1,...,xk] - f[x0,...,x[k-1]]) / (xk - x0)
     * </pre>
     * <p>
     * The computational complexity is \(O(n^2)\) where \(n\) is the common
     * length of {@code x} and {@code y}.</p>
     *
     * @param x Interpolating points array.
     * @param y Interpolating values array.
     * @return a fresh copy of the divided difference array.
     * @throws DimensionMismatchException if the array lengths are different.
     * @throws NumberIsTooSmallException if the number of points is less than 2.
     * @throws NonMonotonicSequenceException
     * if {@code x} is not sorted in strictly increasing order.
     */
    protected static double[] computeDividedDifference(final double[] x, final double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        // STUB: not implemented
        return null;
    }
}
