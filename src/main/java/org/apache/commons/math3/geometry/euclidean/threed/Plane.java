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

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.util.FastMath;

/**
 * The class represent planes in a three dimensional space.
 * @since 3.0
 */
public class Plane implements Hyperplane<Euclidean3D>, Embedding<Euclidean3D, Euclidean2D> {

    /**
     * Default value for tolerance.
     */
    private static final double DEFAULT_TOLERANCE = 1.0e-10;

    /**
     * Offset of the origin with respect to the plane.
     */
    private double originOffset;

    /**
     * Origin of the plane frame.
     */
    private Vector3D origin;

    /**
     * First vector of the plane frame (in plane).
     */
    private Vector3D u;

    /**
     * Second vector of the plane frame (in plane).
     */
    private Vector3D v;

    /**
     * Third vector of the plane frame (plane normal).
     */
    private Vector3D w;

    /**
     * Tolerance below which points are considered identical.
     */
    private final double tolerance;

    /**
     * Build a plane normal to a given direction and containing the origin.
     * @param normal normal direction to the plane
     * @param tolerance tolerance below which points are considered identical
     * @exception MathArithmeticException if the normal norm is too small
     * @since 3.3
     */
    public Plane(final Vector3D normal, final double tolerance) throws MathArithmeticException {
        setNormal(normal);
        this.tolerance = tolerance;
        originOffset = 0;
        setFrame();
    }

    /**
     * Build a plane from a point and a normal.
     * @param p point belonging to the plane
     * @param normal normal direction to the plane
     * @param tolerance tolerance below which points are considered identical
     * @exception MathArithmeticException if the normal norm is too small
     * @since 3.3
     */
    public Plane(final Vector3D p, final Vector3D normal, final double tolerance) throws MathArithmeticException {
        setNormal(normal);
        this.tolerance = tolerance;
        originOffset = -p.dotProduct(w);
        setFrame();
    }

    /**
     * Build a plane from three points.
     * <p>The plane is oriented in the direction of
     * {@code (p2-p1) ^ (p3-p1)}</p>
     * @param p1 first point belonging to the plane
     * @param p2 second point belonging to the plane
     * @param p3 third point belonging to the plane
     * @param tolerance tolerance below which points are considered identical
     * @exception MathArithmeticException if the points do not constitute a plane
     * @since 3.3
     */
    public Plane(final Vector3D p1, final Vector3D p2, final Vector3D p3, final double tolerance) throws MathArithmeticException {
        this(p1, p2.subtract(p1).crossProduct(p3.subtract(p1)), tolerance);
    }

    /**
     * Build a plane normal to a given direction and containing the origin.
     * @param normal normal direction to the plane
     * @exception MathArithmeticException if the normal norm is too small
     * @deprecated as of 3.3, replaced with {@link #Plane(Vector3D, double)}
     */
    @Deprecated
    public Plane(final Vector3D normal) throws MathArithmeticException {
        this(normal, DEFAULT_TOLERANCE);
    }

    /**
     * Build a plane from a point and a normal.
     * @param p point belonging to the plane
     * @param normal normal direction to the plane
     * @exception MathArithmeticException if the normal norm is too small
     * @deprecated as of 3.3, replaced with {@link #Plane(Vector3D, Vector3D, double)}
     */
    @Deprecated
    public Plane(final Vector3D p, final Vector3D normal) throws MathArithmeticException {
        this(p, normal, DEFAULT_TOLERANCE);
    }

    /**
     * Build a plane from three points.
     * <p>The plane is oriented in the direction of
     * {@code (p2-p1) ^ (p3-p1)}</p>
     * @param p1 first point belonging to the plane
     * @param p2 second point belonging to the plane
     * @param p3 third point belonging to the plane
     * @exception MathArithmeticException if the points do not constitute a plane
     * @deprecated as of 3.3, replaced with {@link #Plane(Vector3D, Vector3D, Vector3D, double)}
     */
    @Deprecated
    public Plane(final Vector3D p1, final Vector3D p2, final Vector3D p3) throws MathArithmeticException {
        this(p1, p2, p3, DEFAULT_TOLERANCE);
    }

    /**
     * Copy constructor.
     * <p>The instance created is completely independant of the original
     * one. A deep copy is used, none of the underlying object are
     * shared.</p>
     * @param plane plane to copy
     */
    public Plane(final Plane plane) {
        originOffset = plane.originOffset;
        origin = plane.origin;
        u = plane.u;
        v = plane.v;
        w = plane.w;
        tolerance = plane.tolerance;
    }

    /**
     * Copy the instance.
     * <p>The instance created is completely independant of the original
     * one. A deep copy is used, none of the underlying objects are
     * shared (except for immutable objects).</p>
     * @return a new hyperplane, copy of the instance
     */
    public Plane copySelf() {
        // STUB: not implemented
        return null;
    }

    /**
     * Reset the instance as if built from a point and a normal.
     * @param p point belonging to the plane
     * @param normal normal direction to the plane
     * @exception MathArithmeticException if the normal norm is too small
     */
    public void reset(final Vector3D p, final Vector3D normal) throws MathArithmeticException {
        // STUB: not implemented
    }

    /**
     * Reset the instance from another one.
     * <p>The updated instance is completely independant of the original
     * one. A deep reset is used none of the underlying object is
     * shared.</p>
     * @param original plane to reset from
     */
    public void reset(final Plane original) {
        // STUB: not implemented
    }

    /**
     * Set the normal vactor.
     * @param normal normal direction to the plane (will be copied)
     * @exception MathArithmeticException if the normal norm is too small
     */
    private void setNormal(final Vector3D normal) throws MathArithmeticException {
        final double norm = normal.getNorm();
        if (norm < 1.0e-10) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        w = new Vector3D(1.0 / norm, normal);
    }

    /**
     * Reset the plane frame.
     */
    private void setFrame() {
        origin = new Vector3D(-originOffset, w);
        u = w.orthogonal();
        v = Vector3D.crossProduct(w, u);
    }

    /**
     * Get the origin point of the plane frame.
     * <p>The point returned is the orthogonal projection of the
     * 3D-space origin in the plane.</p>
     * @return the origin point of the plane frame (point closest to the
     * 3D-space origin)
     */
    public Vector3D getOrigin() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the normalized normal vector.
     * <p>The frame defined by ({@link #getU getU}, {@link #getV getV},
     * {@link #getNormal getNormal}) is a rigth-handed orthonormalized
     * frame).</p>
     * @return normalized normal vector
     * @see #getU
     * @see #getV
     */
    public Vector3D getNormal() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the plane first canonical vector.
     * <p>The frame defined by ({@link #getU getU}, {@link #getV getV},
     * {@link #getNormal getNormal}) is a rigth-handed orthonormalized
     * frame).</p>
     * @return normalized first canonical vector
     * @see #getV
     * @see #getNormal
     */
    public Vector3D getU() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the plane second canonical vector.
     * <p>The frame defined by ({@link #getU getU}, {@link #getV getV},
     * {@link #getNormal getNormal}) is a rigth-handed orthonormalized
     * frame).</p>
     * @return normalized second canonical vector
     * @see #getU
     * @see #getNormal
     */
    public Vector3D getV() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.3
     */
    public Point<Euclidean3D> project(Point<Euclidean3D> point) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @since 3.3
     */
    public double getTolerance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Revert the plane.
     * <p>Replace the instance by a similar plane with opposite orientation.</p>
     * <p>The new plane frame is chosen in such a way that a 3D point that had
     * {@code (x, y)} in-plane coordinates and {@code z} offset with
     * respect to the plane and is unaffected by the change will have
     * {@code (y, x)} in-plane coordinates and {@code -z} offset with
     * respect to the new plane. This means that the {@code u} and {@code v}
     * vectors returned by the {@link #getU} and {@link #getV} methods are exchanged,
     * and the {@code w} vector returned by the {@link #getNormal} method is
     * reversed.</p>
     */
    public void revertSelf() {
        // STUB: not implemented
    }

    /**
     * Transform a space point into a sub-space point.
     * @param vector n-dimension point of the space
     * @return (n-1)-dimension point of the sub-space corresponding to
     * the specified space point
     */
    public Vector2D toSubSpace(Vector<Euclidean3D> vector) {
        // STUB: not implemented
        return null;
    }

    /**
     * Transform a sub-space point into a space point.
     * @param vector (n-1)-dimension point of the sub-space
     * @return n-dimension point of the space corresponding to the
     * specified sub-space point
     */
    public Vector3D toSpace(Vector<Euclidean2D> vector) {
        // STUB: not implemented
        return null;
    }

    /**
     * Transform a 3D space point into an in-plane point.
     * @param point point of the space (must be a {@link Vector3D
     * Vector3D} instance)
     * @return in-plane point (really a {@link
     * org.apache.commons.math3.geometry.euclidean.twod.Vector2D Vector2D} instance)
     * @see #toSpace
     */
    public Vector2D toSubSpace(final Point<Euclidean3D> point) {
        // STUB: not implemented
        return null;
    }

    /**
     * Transform an in-plane point into a 3D space point.
     * @param point in-plane point (must be a {@link
     * org.apache.commons.math3.geometry.euclidean.twod.Vector2D Vector2D} instance)
     * @return 3D space point (really a {@link Vector3D Vector3D} instance)
     * @see #toSubSpace
     */
    public Vector3D toSpace(final Point<Euclidean2D> point) {
        // STUB: not implemented
        return null;
    }

    /**
     * Get one point from the 3D-space.
     * @param inPlane desired in-plane coordinates for the point in the
     * plane
     * @param offset desired offset for the point
     * @return one point in the 3D-space, with given coordinates and offset
     * relative to the plane
     */
    public Vector3D getPointAt(final Vector2D inPlane, final double offset) {
        // STUB: not implemented
        return null;
    }

    /**
     * Check if the instance is similar to another plane.
     * <p>Planes are considered similar if they contain the same
     * points. This does not mean they are equal since they can have
     * opposite normals.</p>
     * @param plane plane to which the instance is compared
     * @return true if the planes are similar
     */
    public boolean isSimilarTo(final Plane plane) {
        // STUB: not implemented
        return false;
    }

    /**
     * Rotate the plane around the specified point.
     * <p>The instance is not modified, a new instance is created.</p>
     * @param center rotation center
     * @param rotation vectorial rotation operator
     * @return a new plane
     */
    public Plane rotate(final Vector3D center, final Rotation rotation) {
        // STUB: not implemented
        return null;
    }

    /**
     * Translate the plane by the specified amount.
     * <p>The instance is not modified, a new instance is created.</p>
     * @param translation translation to apply
     * @return a new plane
     */
    public Plane translate(final Vector3D translation) {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the intersection of a line with the instance.
     * @param line line intersecting the instance
     * @return intersection point between between the line and the
     * instance (null if the line is parallel to the instance)
     */
    public Vector3D intersection(final Line line) {
        // STUB: not implemented
        return null;
    }

    /**
     * Build the line shared by the instance and another plane.
     * @param other other plane
     * @return line at the intersection of the instance and the
     * other plane (really a {@link Line Line} instance)
     */
    public Line intersection(final Plane other) {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the intersection point of three planes.
     * @param plane1 first plane1
     * @param plane2 second plane2
     * @param plane3 third plane2
     * @return intersection point of three planes, null if some planes are parallel
     */
    public static Vector3D intersection(final Plane plane1, final Plane plane2, final Plane plane3) {
        // STUB: not implemented
        return null;
    }

    /**
     * Build a region covering the whole hyperplane.
     * @return a region covering the whole hyperplane
     */
    public SubPlane wholeHyperplane() {
        // STUB: not implemented
        return null;
    }

    /**
     * Build a region covering the whole space.
     * @return a region containing the instance (really a {@link
     * PolyhedronsSet PolyhedronsSet} instance)
     */
    public PolyhedronsSet wholeSpace() {
        // STUB: not implemented
        return null;
    }

    /**
     * Check if the instance contains a point.
     * @param p point to check
     * @return true if p belongs to the plane
     */
    public boolean contains(final Vector3D p) {
        // STUB: not implemented
        return false;
    }

    /**
     * Get the offset (oriented distance) of a parallel plane.
     * <p>This method should be called only for parallel planes otherwise
     * the result is not meaningful.</p>
     * <p>The offset is 0 if both planes are the same, it is
     * positive if the plane is on the plus side of the instance and
     * negative if it is on the minus side, according to its natural
     * orientation.</p>
     * @param plane plane to check
     * @return offset of the plane
     */
    public double getOffset(final Plane plane) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Get the offset (oriented distance) of a vector.
     * @param vector vector to check
     * @return offset of the vector
     */
    public double getOffset(Vector<Euclidean3D> vector) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Get the offset (oriented distance) of a point.
     * <p>The offset is 0 if the point is on the underlying hyperplane,
     * it is positive if the point is on one particular side of the
     * hyperplane, and it is negative if the point is on the other side,
     * according to the hyperplane natural orientation.</p>
     * @param point point to check
     * @return offset of the point
     */
    public double getOffset(final Point<Euclidean3D> point) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Check if the instance has the same orientation as another hyperplane.
     * @param other other hyperplane to check against the instance
     * @return true if the instance and the other hyperplane have
     * the same orientation
     */
    public boolean sameOrientationAs(final Hyperplane<Euclidean3D> other) {
        // STUB: not implemented
        return false;
    }
}
