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
package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/**
 * Class representing both the value and the differentials of a function.
 * <p>This class is the workhorse of the differentiation package.</p>
 * <p>This class is an implementation of the extension to Rall's
 * numbers described in Dan Kalman's paper <a
 * href="http://www1.american.edu/cas/mathstat/People/kalman/pdffiles/mmgautodiff.pdf">Doubly
 * Recursive Multivariate Automatic Differentiation</a>, Mathematics Magazine, vol. 75,
 * no. 3, June 2002. Rall's numbers are an extension to the real numbers used
 * throughout mathematical expressions; they hold the derivative together with the
 * value of a function. Dan Kalman's derivative structures hold all partial derivatives
 * up to any specified order, with respect to any number of free parameters. Rall's
 * numbers therefore can be seen as derivative structures for order one derivative and
 * one free parameter, and real numbers can be seen as derivative structures with zero
 * order derivative and no free parameters.</p>
 * <p>{@link DerivativeStructure} instances can be used directly thanks to
 * the arithmetic operators to the mathematical functions provided as
 * methods by this class (+, -, *, /, %, sin, cos ...).</p>
 * <p>Implementing complex expressions by hand using these classes is
 * a tedious and error-prone task but has the advantage of having no limitation
 * on the derivation order despite no requiring users to compute the derivatives by
 * themselves. Implementing complex expression can also be done by developing computation
 * code using standard primitive double values and to use {@link
 * UnivariateFunctionDifferentiator differentiators} to create the {@link
 * DerivativeStructure}-based instances. This method is simpler but may be limited in
 * the accuracy and derivation orders and may be computationally intensive (this is
 * typically the case for {@link FiniteDifferencesDifferentiator finite differences
 * differentiator}.</p>
 * <p>Instances of this class are guaranteed to be immutable.</p>
 * @see DSCompiler
 * @since 3.1
 */
public class DerivativeStructure implements RealFieldElement<DerivativeStructure>, Serializable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20120730L;

    /**
     * Compiler for the current dimensions.
     */
    private transient DSCompiler compiler;

    /**
     * Combined array holding all values.
     */
    private final double[] data;

    /**
     * Build an instance with all values and derivatives set to 0.
     * @param compiler compiler to use for computation
     */
    private DerivativeStructure(final DSCompiler compiler) {
        this.compiler = compiler;
        this.data = new double[compiler.getSize()];
    }

    /**
     * Build an instance with all values and derivatives set to 0.
     * @param parameters number of free parameters
     * @param order derivation order
     * @throws NumberIsTooLargeException if order is too large
     */
    public DerivativeStructure(final int parameters, final int order) throws NumberIsTooLargeException {
        this(DSCompiler.getCompiler(parameters, order));
    }

    /**
     * Build an instance representing a constant value.
     * @param parameters number of free parameters
     * @param order derivation order
     * @param value value of the constant
     * @throws NumberIsTooLargeException if order is too large
     * @see #DerivativeStructure(int, int, int, double)
     */
    public DerivativeStructure(final int parameters, final int order, final double value) throws NumberIsTooLargeException {
        this(parameters, order);
        this.data[0] = value;
    }

    /**
     * Build an instance representing a variable.
     * <p>Instances built using this constructor are considered
     * to be the free variables with respect to which differentials
     * are computed. As such, their differential with respect to
     * themselves is +1.</p>
     * @param parameters number of free parameters
     * @param order derivation order
     * @param index index of the variable (from 0 to {@code parameters - 1})
     * @param value value of the variable
     * @exception NumberIsTooLargeException if {@code index >= parameters}.
     * @see #DerivativeStructure(int, int, double)
     */
    public DerivativeStructure(final int parameters, final int order, final int index, final double value) throws NumberIsTooLargeException {
        this(parameters, order, value);
        if (index >= parameters) {
            throw new NumberIsTooLargeException(index, parameters, false);
        }
        if (order > 0) {
            // the derivative of the variable with respect to itself is 1.
            data[DSCompiler.getCompiler(index, order).getSize()] = 1.0;
        }
    }

    /**
     * Linear combination constructor.
     * The derivative structure built will be a1 * ds1 + a2 * ds2
     * @param a1 first scale factor
     * @param ds1 first base (unscaled) derivative structure
     * @param a2 second scale factor
     * @param ds2 second base (unscaled) derivative structure
     * @exception DimensionMismatchException if number of free parameters or orders are inconsistent
     */
    public DerivativeStructure(final double a1, final DerivativeStructure ds1, final double a2, final DerivativeStructure ds2) throws DimensionMismatchException {
        this(ds1.compiler);
        compiler.checkCompatibility(ds2.compiler);
        compiler.linearCombination(a1, ds1.data, 0, a2, ds2.data, 0, data, 0);
    }

    /**
     * Linear combination constructor.
     * The derivative structure built will be a1 * ds1 + a2 * ds2 + a3 * ds3
     * @param a1 first scale factor
     * @param ds1 first base (unscaled) derivative structure
     * @param a2 second scale factor
     * @param ds2 second base (unscaled) derivative structure
     * @param a3 third scale factor
     * @param ds3 third base (unscaled) derivative structure
     * @exception DimensionMismatchException if number of free parameters or orders are inconsistent
     */
    public DerivativeStructure(final double a1, final DerivativeStructure ds1, final double a2, final DerivativeStructure ds2, final double a3, final DerivativeStructure ds3) throws DimensionMismatchException {
        this(ds1.compiler);
        compiler.checkCompatibility(ds2.compiler);
        compiler.checkCompatibility(ds3.compiler);
        compiler.linearCombination(a1, ds1.data, 0, a2, ds2.data, 0, a3, ds3.data, 0, data, 0);
    }

    /**
     * Linear combination constructor.
     * The derivative structure built will be a1 * ds1 + a2 * ds2 + a3 * ds3 + a4 * ds4
     * @param a1 first scale factor
     * @param ds1 first base (unscaled) derivative structure
     * @param a2 second scale factor
     * @param ds2 second base (unscaled) derivative structure
     * @param a3 third scale factor
     * @param ds3 third base (unscaled) derivative structure
     * @param a4 fourth scale factor
     * @param ds4 fourth base (unscaled) derivative structure
     * @exception DimensionMismatchException if number of free parameters or orders are inconsistent
     */
    public DerivativeStructure(final double a1, final DerivativeStructure ds1, final double a2, final DerivativeStructure ds2, final double a3, final DerivativeStructure ds3, final double a4, final DerivativeStructure ds4) throws DimensionMismatchException {
        this(ds1.compiler);
        compiler.checkCompatibility(ds2.compiler);
        compiler.checkCompatibility(ds3.compiler);
        compiler.checkCompatibility(ds4.compiler);
        compiler.linearCombination(a1, ds1.data, 0, a2, ds2.data, 0, a3, ds3.data, 0, a4, ds4.data, 0, data, 0);
    }

    /**
     * Build an instance from all its derivatives.
     * @param parameters number of free parameters
     * @param order derivation order
     * @param derivatives derivatives sorted according to
     * {@link DSCompiler#getPartialDerivativeIndex(int...)}
     * @exception DimensionMismatchException if derivatives array does not match the
     * {@link DSCompiler#getSize() size} expected by the compiler
     * @throws NumberIsTooLargeException if order is too large
     * @see #getAllDerivatives()
     */
    public DerivativeStructure(final int parameters, final int order, final double... derivatives) throws DimensionMismatchException, NumberIsTooLargeException {
        this(parameters, order);
        if (derivatives.length != data.length) {
            throw new DimensionMismatchException(derivatives.length, data.length);
        }
        System.arraycopy(derivatives, 0, data, 0, data.length);
    }

    /**
     * Copy constructor.
     * @param ds instance to copy
     */
    private DerivativeStructure(final DerivativeStructure ds) {
        this.compiler = ds.compiler;
        this.data = ds.data.clone();
    }

    /**
     * Get the number of free parameters.
     * @return number of free parameters
     */
    public int getFreeParameters() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Get the derivation order.
     * @return derivation order
     */
    public int getOrder() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Create a constant compatible with instance order and number of parameters.
     * <p>
     * This method is a convenience factory method, it simply calls
     * {@code new DerivativeStructure(getFreeParameters(), getOrder(), c)}
     * </p>
     * @param c value of the constant
     * @return a constant compatible with instance order and number of parameters
     * @see #DerivativeStructure(int, int, double)
     * @since 3.3
     */
    public DerivativeStructure createConstant(final double c) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public double getReal() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Get the value part of the derivative structure.
     * @return value part of the derivative structure
     * @see #getPartialDerivative(int...)
     */
    public double getValue() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Get a partial derivative.
     * @param orders derivation orders with respect to each variable (if all orders are 0,
     * the value is returned)
     * @return partial derivative
     * @see #getValue()
     * @exception DimensionMismatchException if the numbers of variables does not
     * match the instance
     * @exception NumberIsTooLargeException if sum of derivation orders is larger
     * than the instance limits
     */
    public double getPartialDerivative(final int... orders) throws DimensionMismatchException, NumberIsTooLargeException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Get all partial derivatives.
     * @return a fresh copy of partial derivatives, in an array sorted according to
     * {@link DSCompiler#getPartialDerivativeIndex(int...)}
     */
    public double[] getAllDerivatives() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure add(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     */
    public DerivativeStructure add(final DerivativeStructure a) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure subtract(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     */
    public DerivativeStructure subtract(final DerivativeStructure a) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public DerivativeStructure multiply(final int n) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure multiply(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     */
    public DerivativeStructure multiply(final DerivativeStructure a) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure divide(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     */
    public DerivativeStructure divide(final DerivativeStructure a) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public DerivativeStructure remainder(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure remainder(final DerivativeStructure a) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public DerivativeStructure negate() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure abs() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure ceil() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure floor() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure rint() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public long round() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure signum() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure copySign(final DerivativeStructure sign) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure copySign(final double sign) {
        // STUB: not implemented
        return null;
    }

    /**
     * Return the exponent of the instance value, removing the bias.
     * <p>
     * For double numbers of the form 2<sup>x</sup>, the unbiased
     * exponent is exactly x.
     * </p>
     * @return exponent for instance in IEEE754 representation, without bias
     */
    public int getExponent() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure scalb(final int n) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure hypot(final DerivativeStructure y) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the hypotenuse of a triangle with sides {@code x} and {@code y}
     * - sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)
     * avoiding intermediate overflow or underflow.
     *
     * <ul>
     * <li> If either argument is infinite, then the result is positive infinity.</li>
     * <li> else, if either argument is NaN then the result is NaN.</li>
     * </ul>
     *
     * @param x a value
     * @param y a value
     * @return sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public static DerivativeStructure hypot(final DerivativeStructure x, final DerivativeStructure y) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute composition of the instance by a univariate function.
     * @param f array of value and derivatives of the function at
     * the current point (i.e. [f({@link #getValue()}),
     * f'({@link #getValue()}), f''({@link #getValue()})...]).
     * @return f(this)
     * @exception DimensionMismatchException if the number of derivatives
     * in the array is not equal to {@link #getOrder() order} + 1
     */
    public DerivativeStructure compose(final double... f) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public DerivativeStructure reciprocal() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure sqrt() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure cbrt() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure rootN(final int n) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Field<DerivativeStructure> getField() {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute a<sup>x</sup> where a is a double and x a {@link DerivativeStructure}
     * @param a number to exponentiate
     * @param x power to apply
     * @return a<sup>x</sup>
     * @since 3.3
     */
    public static DerivativeStructure pow(final double a, final DerivativeStructure x) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure pow(final double p) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure pow(final int n) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure pow(final DerivativeStructure e) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure exp() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure expm1() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure log() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure log1p() {
        // STUB: not implemented
        return null;
    }

    /**
     * Base 10 logarithm.
     * @return base 10 logarithm of the instance
     */
    public DerivativeStructure log10() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure cos() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure sin() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure tan() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure acos() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure asin() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure atan() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure atan2(final DerivativeStructure x) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Two arguments arc tangent operation.
     * @param y first argument of the arc tangent
     * @param x second argument of the arc tangent
     * @return atan2(y, x)
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public static DerivativeStructure atan2(final DerivativeStructure y, final DerivativeStructure x) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure cosh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure sinh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure tanh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure acosh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure asinh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public DerivativeStructure atanh() {
        // STUB: not implemented
        return null;
    }

    /**
     * Convert radians to degrees, with error of less than 0.5 ULP
     *  @return instance converted into degrees
     */
    public DerivativeStructure toDegrees() {
        // STUB: not implemented
        return null;
    }

    /**
     * Convert degrees to radians, with error of less than 0.5 ULP
     *  @return instance converted into radians
     */
    public DerivativeStructure toRadians() {
        // STUB: not implemented
        return null;
    }

    /**
     * Evaluate Taylor expansion a derivative structure.
     * @param delta parameters offsets (&Delta;x, &Delta;y, ...)
     * @return value of the Taylor expansion at x + &Delta;x, y + &Delta;y, ...
     * @throws MathArithmeticException if factorials becomes too large
     */
    public double taylor(final double... delta) throws MathArithmeticException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure linearCombination(final DerivativeStructure[] a, final DerivativeStructure[] b) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure linearCombination(final double[] a, final DerivativeStructure[] b) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure linearCombination(final DerivativeStructure a1, final DerivativeStructure b1, final DerivativeStructure a2, final DerivativeStructure b2) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure linearCombination(final double a1, final DerivativeStructure b1, final double a2, final DerivativeStructure b2) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure linearCombination(final DerivativeStructure a1, final DerivativeStructure b1, final DerivativeStructure a2, final DerivativeStructure b2, final DerivativeStructure a3, final DerivativeStructure b3) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure linearCombination(final double a1, final DerivativeStructure b1, final double a2, final DerivativeStructure b2, final double a3, final DerivativeStructure b3) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure linearCombination(final DerivativeStructure a1, final DerivativeStructure b1, final DerivativeStructure a2, final DerivativeStructure b2, final DerivativeStructure a3, final DerivativeStructure b3, final DerivativeStructure a4, final DerivativeStructure b4) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @exception DimensionMismatchException if number of free parameters
     * or orders do not match
     * @since 3.2
     */
    public DerivativeStructure linearCombination(final double a1, final DerivativeStructure b1, final double a2, final DerivativeStructure b2, final double a3, final DerivativeStructure b3, final double a4, final DerivativeStructure b4) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Test for the equality of two derivative structures.
     * <p>
     * Derivative structures are considered equal if they have the same number
     * of free parameters, the same derivation order, and the same derivatives.
     * </p>
     * @param other Object to test for equality to this
     * @return true if two derivative structures are equal
     * @since 3.2
     */
    @Override
    public boolean equals(Object other) {
        // STUB: not implemented
        return false;
    }

    /**
     * Get a hashCode for the derivative structure.
     * @return a hash code value for this object
     * @since 3.2
     */
    @Override
    public int hashCode() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Replace the instance with a data transfer object for serialization.
     * @return data transfer object that will be serialized
     */
    private Object writeReplace() {
        return new DataTransferObject(compiler.getFreeParameters(), compiler.getOrder(), data);
    }

    /**
     * Internal class used only for serialization.
     */
    private static class DataTransferObject implements Serializable {

        /**
         * Serializable UID.
         */
        private static final long serialVersionUID = 20120730L;

        /**
         * Number of variables.
         * @serial
         */
        private final int variables;

        /**
         * Derivation order.
         * @serial
         */
        private final int order;

        /**
         * Partial derivatives.
         * @serial
         */
        private final double[] data;

        /**
         * Simple constructor.
         * @param variables number of variables
         * @param order derivation order
         * @param data partial derivatives
         */
        DataTransferObject(final int variables, final int order, final double[] data) {
            this.variables = variables;
            this.order = order;
            this.data = data;
        }

        /**
         * Replace the deserialized data transfer object with a {@link DerivativeStructure}.
         * @return replacement {@link DerivativeStructure}
         */
        private Object readResolve() {
            return new DerivativeStructure(variables, order, data);
        }
    }
}
