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
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

/**
 * Generates a tricubic interpolating function.
 *
 * @since 2.2
 * @deprecated To be removed in 4.0 (see MATH-1166).
 */
@Deprecated
public class TricubicSplineInterpolator implements TrivariateGridInterpolator {

    /**
     * {@inheritDoc}
     */
    public TricubicSplineInterpolatingFunction interpolate(final double[] xval, final double[] yval, final double[] zval, final double[][][] fval) throws NoDataException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the next index of an array, clipping if necessary.
     * It is assumed (but not checked) that {@code i} is larger than or equal to 0.
     *
     * @param i Index
     * @param max Upper limit of the array
     * @return the next index
     */
    private int nextIndex(int i, int max) {
        final int index = i + 1;
        return index < max ? index : index - 1;
    }

    /**
     * Compute the previous index of an array, clipping if necessary.
     * It is assumed (but not checked) that {@code i} is smaller than the size of the array.
     *
     * @param i Index
     * @return the previous index
     */
    private int previousIndex(int i) {
        final int index = i - 1;
        return index >= 0 ? index : 0;
    }
}
