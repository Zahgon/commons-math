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

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/**
 * The class represent lines in a three dimensional space.
 *
 * <p>Each oriented line is intrinsically associated with an abscissa
 * which is a coordinate on the line. The point at abscissa 0 is the
 * orthogonal projection of the origin on the line, another equivalent
 * way to express this is to say that it is the point of the line
 * which is closest to the origin. Abscissa increases in the line
 * direction.</p>
 *
 * @since 3.0
 */
public class Line implements Embedding<Euclidean3D, Euclidean1D> {

    /**
     * Default value for tolerance.
     */
    private static final double DEFAULT_TOLERANCE = 1.0e-10;

    /**
     * Line direction.
     */
    private Vector3D direction;

    /**
     * Line point closest to the origin.
     */
    private Vector3D zero;

    /**
     * Tolerance below which points are considered identical.
     */
    private final double tolerance;

    /**
     * Build a line from two points.
     * @param p1 first point belonging to the line (this can be any point)
     * @param p2 second point belonging to the line (this can be any point, different from p1)
     * @param tolerance tolerance below which points are considered identical
     * @exception MathIllegalArgumentException if the points are equal
     * @since 3.3
     */
    public Line(final Vector3D p1, final Vector3D p2, final double tolerance) throws MathIllegalArgumentException {
        reset(p1, p2);
        this.tolerance = tolerance;
    }

    /**
     * Copy constructor.
     * <p>The created instance is completely independent from the
     * original instance, it is a deep copy.</p>
     * @param line line to copy
     */
    public Line(final Line line) {
        this.direction = line.direction;
        this.zero = line.zero;
        this.tolerance = line.tolerance;
    }

    /**
     * Build a line from two points.
     * @param p1 first point belonging to the line (this can be any point)
     * @param p2 second point belonging to the line (this can be any point, different from p1)
     * @exception MathIllegalArgumentException if the points are equal
     * @deprecated as of 3.3, replaced with {@link #Line(Vector3D, Vector3D, double)}
     */
    @Deprecated
    public Line(final Vector3D p1, final Vector3D p2) throws MathIllegalArgumentException {
        this(p1, p2, DEFAULT_TOLERANCE);
    }

    /**
     * Reset the instance as if built from two points.
     * @param p1 first point belonging to the line (this can be any point)
     * @param p2 second point belonging to the line (this can be any point, different from p1)
     * @exception MathIllegalArgumentException if the points are equal
     */
    public void reset(final Vector3D p1, final Vector3D p2) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Get the tolerance below which points are considered identical.
     * @return tolerance below which points are considered identical
     * @since 3.3
     */
    public double getTolerance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Get a line with reversed direction.
     * @return a new instance, with reversed direction
     */
    public Line revert() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the normalized direction vector.
     * @return normalized direction vector
     */
    public Vector3D getDirection() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the line point closest to the origin.
     * @return line point closest to the origin
     */
    public Vector3D getOrigin() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the abscissa of a point with respect to the line.
     * <p>The abscissa is 0 if the projection of the point and the
     * projection of the frame origin on the line are the same
     * point.</p>
     * @param point point to check
     * @return abscissa of the point
     */
    public double getAbscissa(final Vector3D point) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Get one point from the line.
     * @param abscissa desired abscissa for the point
     * @return one point belonging to the line, at specified abscissa
     */
    public Vector3D pointAt(final double abscissa) {
        // STUB: not implemented
        return null;
    }

    /**
     * Transform a space point into a sub-space point.
     * @param vector n-dimension point of the space
     * @return (n-1)-dimension point of the sub-space corresponding to
     * the specified space point
     */
    public Vector1D toSubSpace(Vector<Euclidean3D> vector) {
        // STUB: not implemented
        return null;
    }

    /**
     * Transform a sub-space point into a space point.
     * @param vector (n-1)-dimension point of the sub-space
     * @return n-dimension point of the space corresponding to the
     * specified sub-space point
     */
    public Vector3D toSpace(Vector<Euclidean1D> vector) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @see #getAbscissa(Vector3D)
     */
    public Vector1D toSubSpace(final Point<Euclidean3D> point) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     * @see #pointAt(double)
     */
    public Vector3D toSpace(final Point<Euclidean1D> point) {
        // STUB: not implemented
        return null;
    }

    /**
     * Check if the instance is similar to another line.
     * <p>Lines are considered similar if they contain the same
     * points. This does not mean they are equal since they can have
     * opposite directions.</p>
     * @param line line to which instance should be compared
     * @return true if the lines are similar
     */
    public boolean isSimilarTo(final Line line) {
        // STUB: not implemented
        return false;
    }

    /**
     * Check if the instance contains a point.
     * @param p point to check
     * @return true if p belongs to the line
     */
    public boolean contains(final Vector3D p) {
        // STUB: not implemented
        return false;
    }

    /**
     * Compute the distance between the instance and a point.
     * @param p to check
     * @return distance between the instance and the point
     */
    public double distance(final Vector3D p) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Compute the shortest distance between the instance and another line.
     * @param line line to check against the instance
     * @return shortest distance between the instance and the line
     */
    public double distance(final Line line) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Compute the point of the instance closest to another line.
     * @param line line to check against the instance
     * @return point of the instance closest to another line
     */
    public Vector3D closestPoint(final Line line) {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the intersection point of the instance and another line.
     * @param line other line
     * @return intersection point of the instance and the other line
     * or null if there are no intersection points
     */
    public Vector3D intersection(final Line line) {
        // STUB: not implemented
        return null;
    }

    /**
     * Build a sub-line covering the whole line.
     * @return a sub-line covering the whole line
     */
    public SubLine wholeLine() {
        // STUB: not implemented
        return null;
    }
}
