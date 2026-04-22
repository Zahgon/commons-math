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
import org.apache.commons.math3.util.MathArrays;

/**
 * This class implements the 3/8 fourth order Runge-Kutta
 * integrator for Ordinary Differential Equations.
 *
 * <p>This method is an explicit Runge-Kutta method, its Butcher-array
 * is the following one :
 * <pre>
 *    0  |  0    0    0    0
 *   1/3 | 1/3   0    0    0
 *   2/3 |-1/3   1    0    0
 *    1  |  1   -1    1    0
 *       |--------------------
 *       | 1/8  3/8  3/8  1/8
 * </pre>
 * </p>
 *
 * @see EulerFieldIntegrator
 * @see ClassicalRungeKuttaFieldIntegrator
 * @see GillFieldIntegrator
 * @see MidpointFieldIntegrator
 * @see LutherFieldIntegrator
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class ThreeEighthesFieldIntegrator<T extends RealFieldElement<T>> extends RungeKuttaFieldIntegrator<T> {

    /**
     * Simple constructor.
     * Build a 3/8 integrator with the given step.
     * @param field field to which the time and state vector elements belong
     * @param step integration step
     */
    public ThreeEighthesFieldIntegrator(final Field<T> field, final T step) {
        super(field, "3/8", step);
    }

    /**
     * {@inheritDoc}
     */
    public T[] getC() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T[][] getA() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T[] getB() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ThreeEighthesFieldStepInterpolator<T> createInterpolator(final boolean forward, T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, final FieldEquationsMapper<T> mapper) {
        // STUB: not implemented
        return null;
    }
}
