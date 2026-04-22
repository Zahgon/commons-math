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
package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Region.Location;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;

/**
 * This class represents a sub-hyperplane for {@link Line}.
 * @since 3.0
 */
public class SubLine extends AbstractSubHyperplane<Euclidean2D, Euclidean1D> {

    /**
     * Default value for tolerance.
     */
    private static final double DEFAULT_TOLERANCE = 1.0e-10;

    /**
     * Simple constructor.
     * @param hyperplane underlying hyperplane
     * @param remainingRegion remaining region of the hyperplane
     */
    public SubLine(final Hyperplane<Euclidean2D> hyperplane, final Region<Euclidean1D> remainingRegion) {
        super(hyperplane, remainingRegion);
    }

    /**
     * Create a sub-line from two endpoints.
     * @param start start point
     * @param end end point
     * @param tolerance tolerance below which points are considered identical
     * @since 3.3
     */
    public SubLine(final Vector2D start, final Vector2D end, final double tolerance) {
        super(new Line(start, end, tolerance), buildIntervalSet(start, end, tolerance));
    }

    /**
     * Create a sub-line from two endpoints.
     * @param start start point
     * @param end end point
     * @deprecated as of 3.3, replaced with {@link #SubLine(Vector2D, Vector2D, double)}
     */
    @Deprecated
    public SubLine(final Vector2D start, final Vector2D end) {
        this(start, end, DEFAULT_TOLERANCE);
    }

    /**
     * Create a sub-line from a segment.
     * @param segment single segment forming the sub-line
     */
    public SubLine(final Segment segment) {
        super(segment.getLine(), buildIntervalSet(segment.getStart(), segment.getEnd(), segment.getLine().getTolerance()));
    }

    /**
     * Get the endpoints of the sub-line.
     * <p>
     * A subline may be any arbitrary number of disjoints segments, so the endpoints
     * are provided as a list of endpoint pairs. Each element of the list represents
     * one segment, and each segment contains a start point at index 0 and an end point
     * at index 1. If the sub-line is unbounded in the negative infinity direction,
     * the start point of the first segment will have infinite coordinates. If the
     * sub-line is unbounded in the positive infinity direction, the end point of the
     * last segment will have infinite coordinates. So a sub-line covering the whole
     * line will contain just one row and both elements of this row will have infinite
     * coordinates. If the sub-line is empty, the returned list will contain 0 segments.
     * </p>
     * @return list of segments endpoints
     */
    public List<Segment> getSegments() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the intersection of the instance and another sub-line.
     * <p>
     * This method is related to the {@link Line#intersection(Line)
     * intersection} method in the {@link Line Line} class, but in addition
     * to compute the point along infinite lines, it also checks the point
     * lies on both sub-line ranges.
     * </p>
     * @param subLine other sub-line which may intersect instance
     * @param includeEndPoints if true, endpoints are considered to belong to
     * instance (i.e. they are closed sets) and may be returned, otherwise endpoints
     * are considered to not belong to instance (i.e. they are open sets) and intersection
     * occurring on endpoints lead to null being returned
     * @return the intersection point if there is one, null if the sub-lines don't intersect
     */
    public Vector2D intersection(final SubLine subLine, final boolean includeEndPoints) {
        // STUB: not implemented
        return null;
    }

    /**
     * Build an interval set from two points.
     * @param start start point
     * @param end end point
     * @param tolerance tolerance below which points are considered identical
     * @return an interval set
     */
    private static IntervalsSet buildIntervalSet(final Vector2D start, final Vector2D end, final double tolerance) {
        final Line line = new Line(start, end, tolerance);
        return new IntervalsSet(line.toSubSpace((Point<Euclidean2D>) start).getX(), line.toSubSpace((Point<Euclidean2D>) end).getX(), tolerance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractSubHyperplane<Euclidean2D, Euclidean1D> buildNew(final Hyperplane<Euclidean2D> hyperplane, final Region<Euclidean1D> remainingRegion) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SplitSubHyperplane<Euclidean2D> split(final Hyperplane<Euclidean2D> hyperplane) {
        // STUB: not implemented
        return null;
    }
}
