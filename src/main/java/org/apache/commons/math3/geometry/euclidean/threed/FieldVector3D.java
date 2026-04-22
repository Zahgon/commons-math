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
package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.text.NumberFormat;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/**
 * This class is a re-implementation of {@link Vector3D} using {@link RealFieldElement}.
 * <p>Instance of this class are guaranteed to be immutable.</p>
 * @param <T> the type of the field elements
 * @since 3.2
 */
public class FieldVector3D<T extends RealFieldElement<T>> implements Serializable {

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20130224L;

    /**
     * Abscissa.
     */
    private final T x;

    /**
     * Ordinate.
     */
    private final T y;

    /**
     * Height.
     */
    private final T z;

    /**
     * Simple constructor.
     * Build a vector from its coordinates
     * @param x abscissa
     * @param y ordinate
     * @param z height
     * @see #getX()
     * @see #getY()
     * @see #getZ()
     */
    public FieldVector3D(final T x, final T y, final T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Simple constructor.
     * Build a vector from its coordinates
     * @param v coordinates array
     * @exception DimensionMismatchException if array does not have 3 elements
     * @see #toArray()
     */
    public FieldVector3D(final T[] v) throws DimensionMismatchException {
        if (v.length != 3) {
            throw new DimensionMismatchException(v.length, 3);
        }
        this.x = v[0];
        this.y = v[1];
        this.z = v[2];
    }

    /**
     * Simple constructor.
     * Build a vector from its azimuthal coordinates
     * @param alpha azimuth (&alpha;) around Z
     *              (0 is +X, &pi;/2 is +Y, &pi; is -X and 3&pi;/2 is -Y)
     * @param delta elevation (&delta;) above (XY) plane, from -&pi;/2 to +&pi;/2
     * @see #getAlpha()
     * @see #getDelta()
     */
    public FieldVector3D(final T alpha, final T delta) {
        T cosDelta = delta.cos();
        this.x = alpha.cos().multiply(cosDelta);
        this.y = alpha.sin().multiply(cosDelta);
        this.z = delta.sin();
    }

    /**
     * Multiplicative constructor
     * Build a vector from another one and a scale factor.
     * The vector built will be a * u
     * @param a scale factor
     * @param u base (unscaled) vector
     */
    public FieldVector3D(final T a, final FieldVector3D<T> u) {
        this.x = a.multiply(u.x);
        this.y = a.multiply(u.y);
        this.z = a.multiply(u.z);
    }

    /**
     * Multiplicative constructor
     * Build a vector from another one and a scale factor.
     * The vector built will be a * u
     * @param a scale factor
     * @param u base (unscaled) vector
     */
    public FieldVector3D(final T a, final Vector3D u) {
        this.x = a.multiply(u.getX());
        this.y = a.multiply(u.getY());
        this.z = a.multiply(u.getZ());
    }

    /**
     * Multiplicative constructor
     * Build a vector from another one and a scale factor.
     * The vector built will be a * u
     * @param a scale factor
     * @param u base (unscaled) vector
     */
    public FieldVector3D(final double a, final FieldVector3D<T> u) {
        this.x = u.x.multiply(a);
        this.y = u.y.multiply(a);
        this.z = u.z.multiply(a);
    }

    /**
     * Linear constructor
     * Build a vector from two other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     */
    public FieldVector3D(final T a1, final FieldVector3D<T> u1, final T a2, final FieldVector3D<T> u2) {
        final T prototype = a1;
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ());
    }

    /**
     * Linear constructor
     * Build a vector from two other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     */
    public FieldVector3D(final T a1, final Vector3D u1, final T a2, final Vector3D u2) {
        final T prototype = a1;
        this.x = prototype.linearCombination(u1.getX(), a1, u2.getX(), a2);
        this.y = prototype.linearCombination(u1.getY(), a1, u2.getY(), a2);
        this.z = prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2);
    }

    /**
     * Linear constructor
     * Build a vector from two other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     */
    public FieldVector3D(final double a1, final FieldVector3D<T> u1, final double a2, final FieldVector3D<T> u2) {
        final T prototype = u1.getX();
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ());
    }

    /**
     * Linear constructor
     * Build a vector from three other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     */
    public FieldVector3D(final T a1, final FieldVector3D<T> u1, final T a2, final FieldVector3D<T> u2, final T a3, final FieldVector3D<T> u3) {
        final T prototype = a1;
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
    }

    /**
     * Linear constructor
     * Build a vector from three other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     */
    public FieldVector3D(final T a1, final Vector3D u1, final T a2, final Vector3D u2, final T a3, final Vector3D u3) {
        final T prototype = a1;
        this.x = prototype.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3);
        this.y = prototype.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3);
        this.z = prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3);
    }

    /**
     * Linear constructor
     * Build a vector from three other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     */
    public FieldVector3D(final double a1, final FieldVector3D<T> u1, final double a2, final FieldVector3D<T> u2, final double a3, final FieldVector3D<T> u3) {
        final T prototype = u1.getX();
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
    }

    /**
     * Linear constructor
     * Build a vector from four other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3 + a4 * u4
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     * @param a4 fourth scale factor
     * @param u4 fourth base (unscaled) vector
     */
    public FieldVector3D(final T a1, final FieldVector3D<T> u1, final T a2, final FieldVector3D<T> u2, final T a3, final FieldVector3D<T> u3, final T a4, final FieldVector3D<T> u4) {
        final T prototype = a1;
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
    }

    /**
     * Linear constructor
     * Build a vector from four other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3 + a4 * u4
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     * @param a4 fourth scale factor
     * @param u4 fourth base (unscaled) vector
     */
    public FieldVector3D(final T a1, final Vector3D u1, final T a2, final Vector3D u2, final T a3, final Vector3D u3, final T a4, final Vector3D u4) {
        final T prototype = a1;
        this.x = prototype.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3, u4.getX(), a4);
        this.y = prototype.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3, u4.getY(), a4);
        this.z = prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3, u4.getZ(), a4);
    }

    /**
     * Linear constructor
     * Build a vector from four other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3 + a4 * u4
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     * @param a4 fourth scale factor
     * @param u4 fourth base (unscaled) vector
     */
    public FieldVector3D(final double a1, final FieldVector3D<T> u1, final double a2, final FieldVector3D<T> u2, final double a3, final FieldVector3D<T> u3, final double a4, final FieldVector3D<T> u4) {
        final T prototype = u1.getX();
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
    }

    /**
     * Get the abscissa of the vector.
     * @return abscissa of the vector
     * @see #FieldVector3D(RealFieldElement, RealFieldElement, RealFieldElement)
     */
    public T getX() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the ordinate of the vector.
     * @return ordinate of the vector
     * @see #FieldVector3D(RealFieldElement, RealFieldElement, RealFieldElement)
     */
    public T getY() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the height of the vector.
     * @return height of the vector
     * @see #FieldVector3D(RealFieldElement, RealFieldElement, RealFieldElement)
     */
    public T getZ() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the vector coordinates as a dimension 3 array.
     * @return vector coordinates
     * @see #FieldVector3D(RealFieldElement[])
     */
    public T[] toArray() {
        // STUB: not implemented
        return null;
    }

    /**
     * Convert to a constant vector without derivatives.
     * @return a constant vector
     */
    public Vector3D toVector3D() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the L<sub>1</sub> norm for the vector.
     * @return L<sub>1</sub> norm for the vector
     */
    public T getNorm1() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the L<sub>2</sub> norm for the vector.
     * @return Euclidean norm for the vector
     */
    public T getNorm() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the square of the norm for the vector.
     * @return square of the Euclidean norm for the vector
     */
    public T getNormSq() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the L<sub>&infin;</sub> norm for the vector.
     * @return L<sub>&infin;</sub> norm for the vector
     */
    public T getNormInf() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the azimuth of the vector.
     * @return azimuth (&alpha;) of the vector, between -&pi; and +&pi;
     * @see #FieldVector3D(RealFieldElement, RealFieldElement)
     */
    public T getAlpha() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the elevation of the vector.
     * @return elevation (&delta;) of the vector, between -&pi;/2 and +&pi;/2
     * @see #FieldVector3D(RealFieldElement, RealFieldElement)
     */
    public T getDelta() {
        // STUB: not implemented
        return null;
    }

    /**
     * Add a vector to the instance.
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Add a vector to the instance.
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Add a scaled vector to the instance.
     * @param factor scale factor to apply to v before adding it
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final T factor, final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Add a scaled vector to the instance.
     * @param factor scale factor to apply to v before adding it
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final T factor, final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Add a scaled vector to the instance.
     * @param factor scale factor to apply to v before adding it
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final double factor, final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Add a scaled vector to the instance.
     * @param factor scale factor to apply to v before adding it
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final double factor, final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Subtract a vector from the instance.
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Subtract a vector from the instance.
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Subtract a scaled vector from the instance.
     * @param factor scale factor to apply to v before subtracting it
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final T factor, final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Subtract a scaled vector from the instance.
     * @param factor scale factor to apply to v before subtracting it
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final T factor, final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Subtract a scaled vector from the instance.
     * @param factor scale factor to apply to v before subtracting it
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final double factor, final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Subtract a scaled vector from the instance.
     * @param factor scale factor to apply to v before subtracting it
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final double factor, final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Get a normalized vector aligned with the instance.
     * @return a new normalized vector
     * @exception MathArithmeticException if the norm is zero
     */
    public FieldVector3D<T> normalize() throws MathArithmeticException {
        // STUB: not implemented
        return null;
    }

    /**
     * Get a vector orthogonal to the instance.
     * <p>There are an infinite number of normalized vectors orthogonal
     * to the instance. This method picks up one of them almost
     * arbitrarily. It is useful when one needs to compute a reference
     * frame with one of the axes in a predefined direction. The
     * following example shows how to build a frame having the k axis
     * aligned with the known vector u :
     * <pre><code>
     *   Vector3D k = u.normalize();
     *   Vector3D i = k.orthogonal();
     *   Vector3D j = Vector3D.crossProduct(k, i);
     * </code></pre></p>
     * @return a new normalized vector orthogonal to the instance
     * @exception MathArithmeticException if the norm of the instance is null
     */
    public FieldVector3D<T> orthogonal() throws MathArithmeticException {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the angular separation between two vectors.
     * <p>This method computes the angular separation between two
     * vectors using the dot product for well separated vectors and the
     * cross product for almost aligned vectors. This allows to have a
     * good accuracy in all cases, even for vectors very close to each
     * other.</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return angular separation between v1 and v2
     * @exception MathArithmeticException if either vector has a null norm
     */
    public static <T extends RealFieldElement<T>> T angle(final FieldVector3D<T> v1, final FieldVector3D<T> v2) throws MathArithmeticException {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the angular separation between two vectors.
     * <p>This method computes the angular separation between two
     * vectors using the dot product for well separated vectors and the
     * cross product for almost aligned vectors. This allows to have a
     * good accuracy in all cases, even for vectors very close to each
     * other.</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return angular separation between v1 and v2
     * @exception MathArithmeticException if either vector has a null norm
     */
    public static <T extends RealFieldElement<T>> T angle(final FieldVector3D<T> v1, final Vector3D v2) throws MathArithmeticException {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the angular separation between two vectors.
     * <p>This method computes the angular separation between two
     * vectors using the dot product for well separated vectors and the
     * cross product for almost aligned vectors. This allows to have a
     * good accuracy in all cases, even for vectors very close to each
     * other.</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return angular separation between v1 and v2
     * @exception MathArithmeticException if either vector has a null norm
     */
    public static <T extends RealFieldElement<T>> T angle(final Vector3D v1, final FieldVector3D<T> v2) throws MathArithmeticException {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the opposite of the instance.
     * @return a new vector which is opposite to the instance
     */
    public FieldVector3D<T> negate() {
        // STUB: not implemented
        return null;
    }

    /**
     * Multiply the instance by a scalar.
     * @param a scalar
     * @return a new vector
     */
    public FieldVector3D<T> scalarMultiply(final T a) {
        // STUB: not implemented
        return null;
    }

    /**
     * Multiply the instance by a scalar.
     * @param a scalar
     * @return a new vector
     */
    public FieldVector3D<T> scalarMultiply(final double a) {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns true if any coordinate of this vector is NaN; false otherwise
     * @return  true if any coordinate of this vector is NaN; false otherwise
     */
    public boolean isNaN() {
        // STUB: not implemented
        return false;
    }

    /**
     * Returns true if any coordinate of this vector is infinite and none are NaN;
     * false otherwise
     * @return  true if any coordinate of this vector is infinite and none are NaN;
     * false otherwise
     */
    public boolean isInfinite() {
        // STUB: not implemented
        return false;
    }

    /**
     * Test for the equality of two 3D vectors.
     * <p>
     * If all coordinates of two 3D vectors are exactly the same, and none of their
     * {@link RealFieldElement#getReal() real part} are <code>NaN</code>, the
     * two 3D vectors are considered to be equal.
     * </p>
     * <p>
     * <code>NaN</code> coordinates are considered to affect globally the vector
     * and be equals to each other - i.e, if either (or all) real part of the
     * coordinates of the 3D vector are <code>NaN</code>, the 3D vector is <code>NaN</code>.
     * </p>
     *
     * @param other Object to test for equality to this
     * @return true if two 3D vector objects are equal, false if
     *         object is null, not an instance of Vector3D, or
     *         not equal to this Vector3D instance
     */
    @Override
    public boolean equals(Object other) {
        // STUB: not implemented
        return false;
    }

    /**
     * Get a hashCode for the 3D vector.
     * <p>
     * All NaN values have the same hash code.</p>
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Compute the dot-product of the instance and another vector.
     * <p>
     * The implementation uses specific multiplication and addition
     * algorithms to preserve accuracy and reduce cancellation effects.
     * It should be very accurate even for nearly orthogonal vectors.
     * </p>
     * @see MathArrays#linearCombination(double, double, double, double, double, double)
     * @param v second vector
     * @return the dot product this.v
     */
    public T dotProduct(final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the dot-product of the instance and another vector.
     * <p>
     * The implementation uses specific multiplication and addition
     * algorithms to preserve accuracy and reduce cancellation effects.
     * It should be very accurate even for nearly orthogonal vectors.
     * </p>
     * @see MathArrays#linearCombination(double, double, double, double, double, double)
     * @param v second vector
     * @return the dot product this.v
     */
    public T dotProduct(final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the cross-product of the instance with another vector.
     * @param v other vector
     * @return the cross product this ^ v as a new Vector3D
     */
    public FieldVector3D<T> crossProduct(final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the cross-product of the instance with another vector.
     * @param v other vector
     * @return the cross product this ^ v as a new Vector3D
     */
    public FieldVector3D<T> crossProduct(final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>1</sub> norm
     */
    public T distance1(final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>1</sub> norm
     */
    public T distance1(final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>2</sub> norm
     */
    public T distance(final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>2</sub> norm
     */
    public T distance(final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>&infin;</sub> norm
     */
    public T distanceInf(final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>&infin;</sub> norm
     */
    public T distanceInf(final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the square of the distance between the instance and another vector.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the square of the distance between the instance and p
     */
    public T distanceSq(final FieldVector3D<T> v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the square of the distance between the instance and another vector.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the square of the distance between the instance and p
     */
    public T distanceSq(final Vector3D v) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the dot-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the dot product v1.v2
     */
    public static <T extends RealFieldElement<T>> T dotProduct(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the dot-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the dot product v1.v2
     */
    public static <T extends RealFieldElement<T>> T dotProduct(final FieldVector3D<T> v1, final Vector3D v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the dot-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the dot product v1.v2
     */
    public static <T extends RealFieldElement<T>> T dotProduct(final Vector3D v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the cross-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the cross product v1 ^ v2 as a new Vector
     */
    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the cross-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the cross product v1 ^ v2 as a new Vector
     */
    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(final FieldVector3D<T> v1, final Vector3D v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the cross-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the cross product v1 ^ v2 as a new Vector
     */
    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(final Vector3D v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between two vectors according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>1</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance1(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between two vectors according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>1</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance1(final FieldVector3D<T> v1, final Vector3D v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between two vectors according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>1</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance1(final Vector3D v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between two vectors according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>2</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between two vectors according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>2</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance(final FieldVector3D<T> v1, final Vector3D v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between two vectors according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>2</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance(final Vector3D v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between two vectors according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>&infin;</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distanceInf(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between two vectors according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>&infin;</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distanceInf(final FieldVector3D<T> v1, final Vector3D v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the distance between two vectors according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>&infin;</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distanceInf(final Vector3D v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the square of the distance between two vectors.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the square of the distance between v1 and v2
     */
    public static <T extends RealFieldElement<T>> T distanceSq(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the square of the distance between two vectors.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the square of the distance between v1 and v2
     */
    public static <T extends RealFieldElement<T>> T distanceSq(final FieldVector3D<T> v1, final Vector3D v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the square of the distance between two vectors.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the square of the distance between v1 and v2
     */
    public static <T extends RealFieldElement<T>> T distanceSq(final Vector3D v1, final FieldVector3D<T> v2) {
        // STUB: not implemented
        return null;
    }

    /**
     * Get a string representation of this vector.
     * @return a string representation of this vector
     */
    @Override
    public String toString() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get a string representation of this vector.
     * @param format the custom format for components
     * @return a string representation of this vector
     */
    public String toString(final NumberFormat format) {
        // STUB: not implemented
        return null;
    }
}
