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

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer;
import org.apache.commons.math3.fitting.PolynomialFitter;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;

/**
 * Generates a bicubic interpolation function.
 * Prior to generating the interpolating function, the input is smoothed using
 * polynomial fitting.
 *
 * @since 2.2
 * @deprecated To be removed in 4.0 (see MATH-1166).
 */
@Deprecated
public class SmoothingPolynomialBicubicSplineInterpolator extends BicubicSplineInterpolator {

    /**
     * Fitter for x.
     */
    private final PolynomialFitter xFitter;

    /**
     * Degree of the fitting polynomial.
     */
    private final int xDegree;

    /**
     * Fitter for y.
     */
    private final PolynomialFitter yFitter;

    /**
     * Degree of the fitting polynomial.
     */
    private final int yDegree;

    /**
     * Default constructor. The degree of the fitting polynomials is set to 3.
     */
    public SmoothingPolynomialBicubicSplineInterpolator() {
        this(3);
    }

    /**
     * @param degree Degree of the polynomial fitting functions.
     * @exception NotPositiveException if degree is not positive
     */
    public SmoothingPolynomialBicubicSplineInterpolator(int degree) throws NotPositiveException {
        this(degree, degree);
    }

    /**
     * @param xDegree Degree of the polynomial fitting functions along the
     * x-dimension.
     * @param yDegree Degree of the polynomial fitting functions along the
     * y-dimension.
     * @exception NotPositiveException if degrees are not positive
     */
    public SmoothingPolynomialBicubicSplineInterpolator(int xDegree, int yDegree) throws NotPositiveException {
        if (xDegree < 0) {
            throw new NotPositiveException(xDegree);
        }
        if (yDegree < 0) {
            throw new NotPositiveException(yDegree);
        }
        this.xDegree = xDegree;
        this.yDegree = yDegree;
        final double safeFactor = 1e2;
        final SimpleVectorValueChecker checker = new SimpleVectorValueChecker(safeFactor * Precision.EPSILON, safeFactor * Precision.SAFE_MIN);
        xFitter = new PolynomialFitter(new GaussNewtonOptimizer(false, checker));
        yFitter = new PolynomialFitter(new GaussNewtonOptimizer(false, checker));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BicubicSplineInterpolatingFunction interpolate(final double[] xval, final double[] yval, final double[][] fval) throws NoDataException, NullArgumentException, DimensionMismatchException, NonMonotonicSequenceException {
        // STUB: not implemented
        return null;
    }
}
