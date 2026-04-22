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
package org.apache.commons.math3.analysis.polynomials;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/**
 * Immutable representation of a real polynomial function with real coefficients.
 * <p>
 * <a href="http://mathworld.wolfram.com/HornersMethod.html">Horner's Method</a>
 * is used to evaluate the function.</p>
 */
public class PolynomialFunction implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction, Serializable {

    /**
     * Serialization identifier
     */
    private static final long serialVersionUID = -7726511984200295583L;

    /**
     * The coefficients of the polynomial, ordered by degree -- i.e.,
     * coefficients[0] is the constant term and coefficients[n] is the
     * coefficient of x^n where n is the degree of the polynomial.
     */
    private final double[] coefficients;

    /**
     * Construct a polynomial with the given coefficients.  The first element
     * of the coefficients array is the constant term.  Higher degree
     * coefficients follow in sequence.  The degree of the resulting polynomial
     * is the index of the last non-null element of the array, or 0 if all elements
     * are null.
     * <p>
     * The constructor makes a copy of the input array and assigns the copy to
     * the coefficients property.</p>
     *
     * @param c Polynomial coefficients.
     * @throws NullArgumentException if {@code c} is {@code null}.
     * @throws NoDataException if {@code c} is empty.
     */
    public PolynomialFunction(double[] c) throws NullArgumentException, NoDataException {
        super();
        MathUtils.checkNotNull(c);
        int n = c.length;
        if (n == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        while ((n > 1) && (c[n - 1] == 0)) {
            --n;
        }
        this.coefficients = new double[n];
        System.arraycopy(c, 0, this.coefficients, 0, n);
    }

    /**
     * Compute the value of the function for the given argument.
     * <p>
     *  The value returned is </p><p>
     *  {@code coefficients[n] * x^n + ... + coefficients[1] * x  + coefficients[0]}
     * </p>
     *
     * @param x Argument for which the function value should be computed.
     * @return the value of the polynomial at the given point.
     * @see UnivariateFunction#value(double)
     */
    public double value(double x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the degree of the polynomial.
     *
     * @return the degree of the polynomial.
     */
    public int degree() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns a copy of the coefficients array.
     * <p>
     * Changes made to the returned copy will not affect the coefficients of
     * the polynomial.</p>
     *
     * @return a fresh copy of the coefficients array.
     */
    public double[] getCoefficients() {
        // STUB: not implemented
        return null;
    }

    /**
     * Uses Horner's Method to evaluate the polynomial with the given coefficients at
     * the argument.
     *
     * @param coefficients Coefficients of the polynomial to evaluate.
     * @param argument Input value.
     * @return the value of the polynomial.
     * @throws NoDataException if {@code coefficients} is empty.
     * @throws NullArgumentException if {@code coefficients} is {@code null}.
     */
    protected static double evaluate(double[] coefficients, double argument) throws NullArgumentException, NoDataException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     * @throws NoDataException if {@code coefficients} is empty.
     * @throws NullArgumentException if {@code coefficients} is {@code null}.
     */
    public DerivativeStructure value(final DerivativeStructure t) throws NullArgumentException, NoDataException {
        // STUB: not implemented
        return null;
    }

    /**
     * Add a polynomial to the instance.
     *
     * @param p Polynomial to add.
     * @return a new polynomial which is the sum of the instance and {@code p}.
     */
    public PolynomialFunction add(final PolynomialFunction p) {
        // STUB: not implemented
        return null;
    }

    /**
     * Subtract a polynomial from the instance.
     *
     * @param p Polynomial to subtract.
     * @return a new polynomial which is the instance minus {@code p}.
     */
    public PolynomialFunction subtract(final PolynomialFunction p) {
        // STUB: not implemented
        return null;
    }

    /**
     * Negate the instance.
     *
     * @return a new polynomial with all coefficients negated
     */
    public PolynomialFunction negate() {
        // STUB: not implemented
        return null;
    }

    /**
     * Multiply the instance by a polynomial.
     *
     * @param p Polynomial to multiply by.
     * @return a new polynomial equal to this times {@code p}
     */
    public PolynomialFunction multiply(final PolynomialFunction p) {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the coefficients of the derivative of the polynomial with the given coefficients.
     *
     * @param coefficients Coefficients of the polynomial to differentiate.
     * @return the coefficients of the derivative or {@code null} if coefficients has length 1.
     * @throws NoDataException if {@code coefficients} is empty.
     * @throws NullArgumentException if {@code coefficients} is {@code null}.
     */
    protected static double[] differentiate(double[] coefficients) throws NullArgumentException, NoDataException {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the derivative as a {@link PolynomialFunction}.
     *
     * @return the derivative polynomial.
     */
    public PolynomialFunction polynomialDerivative() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the derivative as a {@link UnivariateFunction}.
     *
     * @return the derivative function.
     */
    public UnivariateFunction derivative() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns a string representation of the polynomial.
     *
     * <p>The representation is user oriented. Terms are displayed lowest
     * degrees first. The multiplications signs, coefficients equals to
     * one and null terms are not displayed (except if the polynomial is 0,
     * in which case the 0 constant term is displayed). Addition of terms
     * with negative coefficients are replaced by subtraction of terms
     * with positive coefficients except for the first displayed term
     * (i.e. we display <code>-3</code> for a constant negative polynomial,
     * but <code>1 - 3 x + x^2</code> if the negative coefficient is not
     * the first one displayed).</p>
     *
     * @return a string representation of the polynomial.
     */
    @Override
    public String toString() {
        // STUB: not implemented
        return null;
    }

    /**
     * Creates a string representing a coefficient, removing ".0" endings.
     *
     * @param coeff Coefficient.
     * @return a string representation of {@code coeff}.
     */
    private static String toString(double coeff) {
        final String c = Double.toString(coeff);
        if (c.endsWith(".0")) {
            return c.substring(0, c.length() - 2);
        } else {
            return c;
        }
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
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        // STUB: not implemented
        return false;
    }

    /**
     * Dedicated parametric polynomial class.
     *
     * @since 3.0
     */
    public static class Parametric implements ParametricUnivariateFunction {

        /**
         * {@inheritDoc}
         */
        public double[] gradient(double x, double... parameters) {
            // STUB: not implemented
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public double value(final double x, final double... parameters) throws NoDataException {
            // STUB: not implemented
            return 0.0;
        }
    }
}
