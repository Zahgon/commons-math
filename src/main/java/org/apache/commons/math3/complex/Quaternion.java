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
package org.apache.commons.math3.complex;

import java.io.Serializable;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/**
 * This class implements <a href="http://mathworld.wolfram.com/Quaternion.html">
 * quaternions</a> (Hamilton's hypercomplex numbers).
 * <br/>
 * Instance of this class are guaranteed to be immutable.
 *
 * @since 3.1
 */
public final class Quaternion implements Serializable {

    /**
     * Identity quaternion.
     */
    public static final Quaternion IDENTITY = new Quaternion(1, 0, 0, 0);

    /**
     * Zero quaternion.
     */
    public static final Quaternion ZERO = new Quaternion(0, 0, 0, 0);

    /**
     * i
     */
    public static final Quaternion I = new Quaternion(0, 1, 0, 0);

    /**
     * j
     */
    public static final Quaternion J = new Quaternion(0, 0, 1, 0);

    /**
     * k
     */
    public static final Quaternion K = new Quaternion(0, 0, 0, 1);

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20092012L;

    /**
     * First component (scalar part).
     */
    private final double q0;

    /**
     * Second component (first vector part).
     */
    private final double q1;

    /**
     * Third component (second vector part).
     */
    private final double q2;

    /**
     * Fourth component (third vector part).
     */
    private final double q3;

    /**
     * Builds a quaternion from its components.
     *
     * @param a Scalar component.
     * @param b First vector component.
     * @param c Second vector component.
     * @param d Third vector component.
     */
    public Quaternion(final double a, final double b, final double c, final double d) {
        this.q0 = a;
        this.q1 = b;
        this.q2 = c;
        this.q3 = d;
    }

    /**
     * Builds a quaternion from scalar and vector parts.
     *
     * @param scalar Scalar part of the quaternion.
     * @param v Components of the vector part of the quaternion.
     *
     * @throws DimensionMismatchException if the array length is not 3.
     */
    public Quaternion(final double scalar, final double[] v) throws DimensionMismatchException {
        if (v.length != 3) {
            throw new DimensionMismatchException(v.length, 3);
        }
        this.q0 = scalar;
        this.q1 = v[0];
        this.q2 = v[1];
        this.q3 = v[2];
    }

    /**
     * Builds a pure quaternion from a vector (assuming that the scalar
     * part is zero).
     *
     * @param v Components of the vector part of the pure quaternion.
     */
    public Quaternion(final double[] v) {
        this(0, v);
    }

    /**
     * Returns the conjugate quaternion of the instance.
     *
     * @return the conjugate quaternion
     */
    public Quaternion getConjugate() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the Hamilton product of two quaternions.
     *
     * @param q1 First quaternion.
     * @param q2 Second quaternion.
     * @return the product {@code q1} and {@code q2}, in that order.
     */
    public static Quaternion multiply(final Quaternion q1, final Quaternion q2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the Hamilton product of the instance by a quaternion.
     *
     * @param q Quaternion.
     * @return the product of this instance with {@code q}, in that order.
     */
    public Quaternion multiply(final Quaternion q) {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the sum of two quaternions.
     *
     * @param q1 Quaternion.
     * @param q2 Quaternion.
     * @return the sum of {@code q1} and {@code q2}.
     */
    public static Quaternion add(final Quaternion q1, final Quaternion q2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the sum of the instance and another quaternion.
     *
     * @param q Quaternion.
     * @return the sum of this instance and {@code q}
     */
    public Quaternion add(final Quaternion q) {
        // STUB: not implemented
        return null;
    }

    /**
     * Subtracts two quaternions.
     *
     * @param q1 First Quaternion.
     * @param q2 Second quaternion.
     * @return the difference between {@code q1} and {@code q2}.
     */
    public static Quaternion subtract(final Quaternion q1, final Quaternion q2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Subtracts a quaternion from the instance.
     *
     * @param q Quaternion.
     * @return the difference between this instance and {@code q}.
     */
    public Quaternion subtract(final Quaternion q) {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the dot-product of two quaternions.
     *
     * @param q1 Quaternion.
     * @param q2 Quaternion.
     * @return the dot product of {@code q1} and {@code q2}.
     */
    public static double dotProduct(final Quaternion q1, final Quaternion q2) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Computes the dot-product of the instance by a quaternion.
     *
     * @param q Quaternion.
     * @return the dot product of this instance and {@code q}.
     */
    public double dotProduct(final Quaternion q) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Computes the norm of the quaternion.
     *
     * @return the norm.
     */
    public double getNorm() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Computes the normalized quaternion (the versor of the instance).
     * The norm of the quaternion must not be zero.
     *
     * @return a normalized quaternion.
     * @throws ZeroException if the norm of the quaternion is zero.
     */
    public Quaternion normalize() {
        // STUB: not implemented
        return null;
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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Checks whether this instance is equal to another quaternion
     * within a given tolerance.
     *
     * @param q Quaternion with which to compare the current quaternion.
     * @param eps Tolerance.
     * @return {@code true} if the each of the components are equal
     * within the allowed absolute error.
     */
    public boolean equals(final Quaternion q, final double eps) {
        // STUB: not implemented
        return false;
    }

    /**
     * Checks whether the instance is a unit quaternion within a given
     * tolerance.
     *
     * @param eps Tolerance (absolute error).
     * @return {@code true} if the norm is 1 within the given tolerance,
     * {@code false} otherwise
     */
    public boolean isUnitQuaternion(double eps) {
        // STUB: not implemented
        return false;
    }

    /**
     * Checks whether the instance is a pure quaternion within a given
     * tolerance.
     *
     * @param eps Tolerance (absolute error).
     * @return {@code true} if the scalar part of the quaternion is zero.
     */
    public boolean isPureQuaternion(double eps) {
        // STUB: not implemented
        return false;
    }

    /**
     * Returns the polar form of the quaternion.
     *
     * @return the unit quaternion with positive scalar part.
     */
    public Quaternion getPositivePolarForm() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the inverse of this instance.
     * The norm of the quaternion must not be zero.
     *
     * @return the inverse.
     * @throws ZeroException if the norm (squared) of the quaternion is zero.
     */
    public Quaternion getInverse() {
        // STUB: not implemented
        return null;
    }

    /**
     * Gets the first component of the quaternion (scalar part).
     *
     * @return the scalar part.
     */
    public double getQ0() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Gets the second component of the quaternion (first component
     * of the vector part).
     *
     * @return the first component of the vector part.
     */
    public double getQ1() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Gets the third component of the quaternion (second component
     * of the vector part).
     *
     * @return the second component of the vector part.
     */
    public double getQ2() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Gets the fourth component of the quaternion (third component
     * of the vector part).
     *
     * @return the third component of the vector part.
     */
    public double getQ3() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Gets the scalar part of the quaternion.
     *
     * @return the scalar part.
     * @see #getQ0()
     */
    public double getScalarPart() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Gets the three components of the vector part of the quaternion.
     *
     * @return the vector part.
     * @see #getQ1()
     * @see #getQ2()
     * @see #getQ3()
     */
    public double[] getVectorPart() {
        // STUB: not implemented
        return null;
    }

    /**
     * Multiplies the instance by a scalar.
     *
     * @param alpha Scalar factor.
     * @return a scaled quaternion.
     */
    public Quaternion multiply(final double alpha) {
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
}
