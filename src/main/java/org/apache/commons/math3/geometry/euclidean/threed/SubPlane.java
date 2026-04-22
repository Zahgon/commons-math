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

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/**
 * This class represents a sub-hyperplane for {@link Plane}.
 * @since 3.0
 */
public class SubPlane extends AbstractSubHyperplane<Euclidean3D, Euclidean2D> {

    /**
     * Simple constructor.
     * @param hyperplane underlying hyperplane
     * @param remainingRegion remaining region of the hyperplane
     */
    public SubPlane(final Hyperplane<Euclidean3D> hyperplane, final Region<Euclidean2D> remainingRegion) {
        super(hyperplane, remainingRegion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractSubHyperplane<Euclidean3D, Euclidean2D> buildNew(final Hyperplane<Euclidean3D> hyperplane, final Region<Euclidean2D> remainingRegion) {
        // STUB: not implemented
        return null;
    }

    /**
     * Split the instance in two parts by an hyperplane.
     * @param hyperplane splitting hyperplane
     * @return an object containing both the part of the instance
     * on the plus side of the instance and the part of the
     * instance on the minus side of the instance
     */
    @Override
    public SplitSubHyperplane<Euclidean3D> split(Hyperplane<Euclidean3D> hyperplane) {
        // STUB: not implemented
        return null;
    }
}
