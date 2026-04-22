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
package org.apache.commons.math3.stat.clustering;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.math3.util.MathArrays;

/**
 * A simple implementation of {@link Clusterable} for points with integer coordinates.
 * @since 2.0
 * @deprecated As of 3.2 (to be removed in 4.0),
 * use {@link org.apache.commons.math3.ml.clustering.DoublePoint} instead
 */
@Deprecated
public class EuclideanIntegerPoint implements Clusterable<EuclideanIntegerPoint>, Serializable {

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 3946024775784901369L;

    /**
     * Point coordinates.
     */
    private final int[] point;

    /**
     * Build an instance wrapping an integer array.
     * <p>The wrapped array is referenced, it is <em>not</em> copied.</p>
     * @param point the n-dimensional point in integer space
     */
    public EuclideanIntegerPoint(final int[] point) {
        this.point = point;
    }

    /**
     * Get the n-dimensional point in integer space.
     * @return a reference (not a copy!) to the wrapped array
     */
    public int[] getPoint() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public double distanceFrom(final EuclideanIntegerPoint p) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public EuclideanIntegerPoint centroidOf(final Collection<EuclideanIntegerPoint> points) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
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
     * {@inheritDoc}
     * @since 2.1
     */
    @Override
    public String toString() {
        // STUB: not implemented
        return null;
    }
}
