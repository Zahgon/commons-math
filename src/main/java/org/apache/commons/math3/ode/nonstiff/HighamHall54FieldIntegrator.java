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
import org.apache.commons.math3.util.MathUtils;

/**
 * This class implements the 5(4) Higham and Hall integrator for
 * Ordinary Differential Equations.
 *
 * <p>This integrator is an embedded Runge-Kutta integrator
 * of order 5(4) used in local extrapolation mode (i.e. the solution
 * is computed using the high order formula) with stepsize control
 * (and automatic step initialization) and continuous output. This
 * method uses 7 functions evaluations per step.</p>
 *
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class HighamHall54FieldIntegrator<T extends RealFieldElement<T>> extends EmbeddedRungeKuttaFieldIntegrator<T> {

    /**
     * Integrator method name.
     */
    private static final String METHOD_NAME = "Higham-Hall 5(4)";

    /**
     * Error weights Butcher array.
     */
    private final T[] e;

    /**
     * Simple constructor.
     * Build a fifth order Higham and Hall integrator with the given step bounds
     * @param field field to which the time and state vector elements belong
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param scalAbsoluteTolerance allowed absolute error
     * @param scalRelativeTolerance allowed relative error
     */
    public HighamHall54FieldIntegrator(final Field<T> field, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) {
        super(field, METHOD_NAME, -1, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        e = MathArrays.buildArray(field, 7);
        e[0] = fraction(-1, 20);
        e[1] = field.getZero();
        e[2] = fraction(81, 160);
        e[3] = fraction(-6, 5);
        e[4] = fraction(25, 32);
        e[5] = fraction(1, 16);
        e[6] = fraction(-1, 10);
    }

    /**
     * Simple constructor.
     * Build a fifth order Higham and Hall integrator with the given step bounds
     * @param field field to which the time and state vector elements belong
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param vecAbsoluteTolerance allowed absolute error
     * @param vecRelativeTolerance allowed relative error
     */
    public HighamHall54FieldIntegrator(final Field<T> field, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(field, METHOD_NAME, -1, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        e = MathArrays.buildArray(field, 7);
        e[0] = fraction(-1, 20);
        e[1] = field.getZero();
        e[2] = fraction(81, 160);
        e[3] = fraction(-6, 5);
        e[4] = fraction(25, 32);
        e[5] = fraction(1, 16);
        e[6] = fraction(-1, 10);
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
    protected HighamHall54FieldStepInterpolator<T> createInterpolator(final boolean forward, T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, final FieldEquationsMapper<T> mapper) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected T estimateError(final T[][] yDotK, final T[] y0, final T[] y1, final T h) {
        // STUB: not implemented
        return null;
    }
}
