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
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/**
 * This class represents an interpolator over the last step during an
 * ODE integration for the 5(4) Dormand-Prince integrator.
 *
 * @see DormandPrince54Integrator
 *
 * @param <T> the type of the field elements
 * @since 3.6
 */
class DormandPrince54FieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {

    /**
     * Last row of the Butcher-array internal weights, element 0.
     */
    private final T a70;

    // element 1 is zero, so it is neither stored nor used
    /**
     * Last row of the Butcher-array internal weights, element 2.
     */
    private final T a72;

    /**
     * Last row of the Butcher-array internal weights, element 3.
     */
    private final T a73;

    /**
     * Last row of the Butcher-array internal weights, element 4.
     */
    private final T a74;

    /**
     * Last row of the Butcher-array internal weights, element 5.
     */
    private final T a75;

    /**
     * Shampine (1986) Dense output, element 0.
     */
    private final T d0;

    // element 1 is zero, so it is neither stored nor used
    /**
     * Shampine (1986) Dense output, element 2.
     */
    private final T d2;

    /**
     * Shampine (1986) Dense output, element 3.
     */
    private final T d3;

    /**
     * Shampine (1986) Dense output, element 4.
     */
    private final T d4;

    /**
     * Shampine (1986) Dense output, element 5.
     */
    private final T d5;

    /**
     * Shampine (1986) Dense output, element 6.
     */
    private final T d6;

    /**
     * Simple constructor.
     * @param field field to which the time and state vector elements belong
     * @param forward integration direction indicator
     * @param yDotK slopes at the intermediate points
     * @param globalPreviousState start of the global step
     * @param globalCurrentState end of the global step
     * @param softPreviousState start of the restricted step
     * @param softCurrentState end of the restricted step
     * @param mapper equations mapper for the all equations
     */
    DormandPrince54FieldStepInterpolator(final Field<T> field, final boolean forward, final T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, final FieldODEStateAndDerivative<T> softPreviousState, final FieldODEStateAndDerivative<T> softCurrentState, final FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
        final T one = field.getOne();
        a70 = one.multiply(35.0).divide(384.0);
        a72 = one.multiply(500.0).divide(1113.0);
        a73 = one.multiply(125.0).divide(192.0);
        a74 = one.multiply(-2187.0).divide(6784.0);
        a75 = one.multiply(11.0).divide(84.0);
        d0 = one.multiply(-12715105075.0).divide(11282082432.0);
        d2 = one.multiply(87487479700.0).divide(32700410799.0);
        d3 = one.multiply(-10690763975.0).divide(1880347072.0);
        d4 = one.multiply(701980252875.0).divide(199316789632.0);
        d5 = one.multiply(-1453857185.0).divide(822651844.0);
        d6 = one.multiply(69997945.0).divide(29380423.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DormandPrince54FieldStepInterpolator<T> create(final Field<T> newField, final boolean newForward, final T[][] newYDotK, final FieldODEStateAndDerivative<T> newGlobalPreviousState, final FieldODEStateAndDerivative<T> newGlobalCurrentState, final FieldODEStateAndDerivative<T> newSoftPreviousState, final FieldODEStateAndDerivative<T> newSoftCurrentState, final FieldEquationsMapper<T> newMapper) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(final FieldEquationsMapper<T> mapper, final T time, final T theta, final T thetaH, final T oneMinusThetaH) {
        // STUB: not implemented
        return null;
    }
}
