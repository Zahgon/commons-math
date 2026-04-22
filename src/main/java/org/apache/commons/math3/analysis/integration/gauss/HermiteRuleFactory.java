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
package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;
import org.apache.commons.math3.util.FastMath;

/**
 * Factory that creates a
 * <a href="http://en.wikipedia.org/wiki/Gauss-Hermite_quadrature">
 * Gauss-type quadrature rule using Hermite polynomials</a>
 * of the first kind.
 * Such a quadrature rule allows the calculation of improper integrals
 * of a function
 * <p>
 *  \(f(x) e^{-x^2}\)
 * </p><p>
 * Recurrence relation and weights computation follow
 * <a href="http://en.wikipedia.org/wiki/Abramowitz_and_Stegun">
 * Abramowitz and Stegun, 1964</a>.
 * </p><p>
 * The coefficients of the standard Hermite polynomials grow very rapidly.
 * In order to avoid overflows, each Hermite polynomial is normalized with
 * respect to the underlying scalar product.
 * The initial interval for the application of the bisection method is
 * based on the roots of the previous Hermite polynomial (interlacing).
 * Upper and lower bounds of these roots are provided by </p>
 * <blockquote>
 *  I. Krasikov,
 *  <em>Nonnegative quadratic forms and bounds on orthogonal polynomials</em>,
 *  Journal of Approximation theory <b>111</b>, 31-49
 * </blockquote>
 *
 * @since 3.3
 */
public class HermiteRuleFactory extends BaseRuleFactory<Double> {

    /**
     * &pi;<sup>1/2</sup>
     */
    private static final double SQRT_PI = 1.77245385090551602729;

    /**
     * &pi;<sup>-1/4</sup>
     */
    private static final double H0 = 7.5112554446494248286e-1;

    /**
     * &pi;<sup>-1/4</sup> &radic;2
     */
    private static final double H1 = 1.0622519320271969145;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Pair<Double[], Double[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }
}
