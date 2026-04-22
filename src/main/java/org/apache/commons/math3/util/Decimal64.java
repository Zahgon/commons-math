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

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.exception.DimensionMismatchException;

/**
 * This class wraps a {@code double} value in an object. It is similar to the
 * standard class {@link Double}, while also implementing the
 * {@link RealFieldElement} interface.
 *
 * @since 3.1
 */
public class Decimal64 extends Number implements RealFieldElement<Decimal64>, Comparable<Decimal64> {

    /**
     * The constant value of {@code 0d} as a {@code Decimal64}.
     */
    public static final Decimal64 ZERO;

    /**
     * The constant value of {@code 1d} as a {@code Decimal64}.
     */
    public static final Decimal64 ONE;

    /**
     * The constant value of {@link Double#NEGATIVE_INFINITY} as a
     * {@code Decimal64}.
     */
    public static final Decimal64 NEGATIVE_INFINITY;

    /**
     * The constant value of {@link Double#POSITIVE_INFINITY} as a
     * {@code Decimal64}.
     */
    public static final Decimal64 POSITIVE_INFINITY;

    /**
     * The constant value of {@link Double#NaN} as a {@code Decimal64}.
     */
    public static final Decimal64 NAN;

    /**
     */
    private static final long serialVersionUID = 20120227L;

    static {
        ZERO = new Decimal64(0d);
        ONE = new Decimal64(1d);
        NEGATIVE_INFINITY = new Decimal64(Double.NEGATIVE_INFINITY);
        POSITIVE_INFINITY = new Decimal64(Double.POSITIVE_INFINITY);
        NAN = new Decimal64(Double.NaN);
    }

    /**
     * The primitive {@code double} value of this object.
     */
    private final double value;

    /**
     * Creates a new instance of this class.
     *
     * @param x the primitive {@code double} value of the object to be created
     */
    public Decimal64(final double x) {
        this.value = x;
    }

    /*
     * Methods from the FieldElement interface.
     */
    /**
     * {@inheritDoc}
     */
    public Field<Decimal64> getField() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.add(a).equals(new Decimal64(this.doubleValue()
     * + a.doubleValue()))}.
     */
    public Decimal64 add(final Decimal64 a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.subtract(a).equals(new Decimal64(this.doubleValue()
     * - a.doubleValue()))}.
     */
    public Decimal64 subtract(final Decimal64 a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.negate().equals(new Decimal64(-this.doubleValue()))}.
     */
    public Decimal64 negate() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.multiply(a).equals(new Decimal64(this.doubleValue()
     * * a.doubleValue()))}.
     */
    public Decimal64 multiply(final Decimal64 a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.multiply(n).equals(new Decimal64(n * this.doubleValue()))}.
     */
    public Decimal64 multiply(final int n) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.divide(a).equals(new Decimal64(this.doubleValue()
     * / a.doubleValue()))}.
     */
    public Decimal64 divide(final Decimal64 a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.reciprocal().equals(new Decimal64(1.0
     * / this.doubleValue()))}.
     */
    public Decimal64 reciprocal() {
        // STUB: not implemented
        return null;
    }

    /*
     * Methods from the Number abstract class
     */
    /**
     * {@inheritDoc}
     *
     * The current implementation performs casting to a {@code byte}.
     */
    @Override
    public byte byteValue() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation performs casting to a {@code short}.
     */
    @Override
    public short shortValue() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation performs casting to a {@code int}.
     */
    @Override
    public int intValue() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation performs casting to a {@code long}.
     */
    @Override
    public long longValue() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation performs casting to a {@code float}.
     */
    @Override
    public float floatValue() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double doubleValue() {
        // STUB: not implemented
        return 0.0;
    }

    /*
     * Methods from the Comparable interface.
     */
    /**
     * {@inheritDoc}
     *
     * The current implementation returns the same value as
     * <center> {@code new Double(this.doubleValue()).compareTo(new
     * Double(o.doubleValue()))} </center>
     *
     * @see Double#compareTo(Double)
     */
    public int compareTo(final Decimal64 o) {
        // STUB: not implemented
        return 0;
    }

    /*
     * Methods from the Object abstract class.
     */
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        // STUB: not implemented
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation returns the same value as
     * {@code new Double(this.doubleValue()).hashCode()}
     *
     * @see Double#hashCode()
     */
    @Override
    public int hashCode() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The returned {@code String} is equal to
     * {@code Double.toString(this.doubleValue())}
     *
     * @see Double#toString(double)
     */
    @Override
    public String toString() {
        // STUB: not implemented
        return null;
    }

    /*
     * Methods inspired by the Double class.
     */
    /**
     * Returns {@code true} if {@code this} double precision number is infinite
     * ({@link Double#POSITIVE_INFINITY} or {@link Double#NEGATIVE_INFINITY}).
     *
     * @return {@code true} if {@code this} number is infinite
     */
    public boolean isInfinite() {
        // STUB: not implemented
        return false;
    }

    /**
     * Returns {@code true} if {@code this} double precision number is
     * Not-a-Number ({@code NaN}), false otherwise.
     *
     * @return {@code true} if {@code this} is {@code NaN}
     */
    public boolean isNaN() {
        // STUB: not implemented
        return false;
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
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 add(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 subtract(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 multiply(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 divide(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 remainder(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 remainder(final Decimal64 a) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 abs() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 ceil() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 floor() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 rint() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public long round() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 signum() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 copySign(final Decimal64 sign) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 copySign(final double sign) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 scalb(final int n) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 hypot(final Decimal64 y) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 sqrt() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 cbrt() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 rootN(final int n) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 pow(final double p) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 pow(final int n) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 pow(final Decimal64 e) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 exp() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 expm1() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 log() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 log1p() {
        // STUB: not implemented
        return null;
    }

    /**
     * Base 10 logarithm.
     * @return base 10 logarithm of the instance
     * @since 3.2
     */
    public Decimal64 log10() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 cos() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 sin() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 tan() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 acos() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 asin() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 atan() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 atan2(final Decimal64 x) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 cosh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 sinh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 tanh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 acosh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 asinh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 atanh() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final Decimal64[] a, final Decimal64[] b) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final double[] a, final Decimal64[] b) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final Decimal64 a1, final Decimal64 b1, final Decimal64 a2, final Decimal64 b2) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final double a1, final Decimal64 b1, final double a2, final Decimal64 b2) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final Decimal64 a1, final Decimal64 b1, final Decimal64 a2, final Decimal64 b2, final Decimal64 a3, final Decimal64 b3) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final double a1, final Decimal64 b1, final double a2, final Decimal64 b2, final double a3, final Decimal64 b3) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final Decimal64 a1, final Decimal64 b1, final Decimal64 a2, final Decimal64 b2, final Decimal64 a3, final Decimal64 b3, final Decimal64 a4, final Decimal64 b4) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final double a1, final Decimal64 b1, final double a2, final Decimal64 b2, final double a3, final Decimal64 b3, final double a4, final Decimal64 b4) {
        // STUB: not implemented
        return null;
    }
}
