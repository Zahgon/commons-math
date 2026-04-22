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
 * This class implements the 8(5,3) Dormand-Prince integrator for Ordinary
 * Differential Equations.
 *
 * <p>This integrator is an embedded Runge-Kutta integrator
 * of order 8(5,3) used in local extrapolation mode (i.e. the solution
 * is computed using the high order formula) with stepsize control
 * (and automatic step initialization) and continuous output. This
 * method uses 12 functions evaluations per step for integration and 4
 * evaluations for interpolation. However, since the first
 * interpolation evaluation is the same as the first integration
 * evaluation of the next step, we have included it in the integrator
 * rather than in the interpolator and specified the method was an
 * <i>fsal</i>. Hence, despite we have 13 stages here, the cost is
 * really 12 evaluations per step even if no interpolation is done,
 * and the overcost of interpolation is only 3 evaluations.</p>
 *
 * <p>This method is based on an 8(6) method by Dormand and Prince
 * (i.e. order 8 for the integration and order 6 for error estimation)
 * modified by Hairer and Wanner to use a 5th order error estimator
 * with 3rd order correction. This modification was introduced because
 * the original method failed in some cases (wrong steps can be
 * accepted when step size is too large, for example in the
 * Brusselator problem) and also had <i>severe difficulties when
 * applied to problems with discontinuities</i>. This modification is
 * explained in the second edition of the first volume (Nonstiff
 * Problems) of the reference book by Hairer, Norsett and Wanner:
 * <i>Solving Ordinary Differential Equations</i> (Springer-Verlag,
 * ISBN 3-540-56670-8).</p>
 *
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class DormandPrince853FieldIntegrator<T extends RealFieldElement<T>> extends EmbeddedRungeKuttaFieldIntegrator<T> {

    /**
     * Integrator method name.
     */
    private static final String METHOD_NAME = "Dormand-Prince 8 (5, 3)";

    /**
     * First error weights array, element 1.
     */
    private final T e1_01;

    // elements 2 to 5 are zero, so they are neither stored nor used
    /**
     * First error weights array, element 6.
     */
    private final T e1_06;

    /**
     * First error weights array, element 7.
     */
    private final T e1_07;

    /**
     * First error weights array, element 8.
     */
    private final T e1_08;

    /**
     * First error weights array, element 9.
     */
    private final T e1_09;

    /**
     * First error weights array, element 10.
     */
    private final T e1_10;

    /**
     * First error weights array, element 11.
     */
    private final T e1_11;

    /**
     * First error weights array, element 12.
     */
    private final T e1_12;

    /**
     * Second error weights array, element 1.
     */
    private final T e2_01;

    // elements 2 to 5 are zero, so they are neither stored nor used
    /**
     * Second error weights array, element 6.
     */
    private final T e2_06;

    /**
     * Second error weights array, element 7.
     */
    private final T e2_07;

    /**
     * Second error weights array, element 8.
     */
    private final T e2_08;

    /**
     * Second error weights array, element 9.
     */
    private final T e2_09;

    /**
     * Second error weights array, element 10.
     */
    private final T e2_10;

    /**
     * Second error weights array, element 11.
     */
    private final T e2_11;

    /**
     * Second error weights array, element 12.
     */
    private final T e2_12;

    /**
     * Simple constructor.
     * Build an eighth order Dormand-Prince integrator with the given step bounds
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
    public DormandPrince853FieldIntegrator(final Field<T> field, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) {
        super(field, METHOD_NAME, 12, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        e1_01 = fraction(116092271.0, 8848465920.0);
        e1_06 = fraction(-1871647.0, 1527680.0);
        e1_07 = fraction(-69799717.0, 140793660.0);
        e1_08 = fraction(1230164450203.0, 739113984000.0);
        e1_09 = fraction(-1980813971228885.0, 5654156025964544.0);
        e1_10 = fraction(464500805.0, 1389975552.0);
        e1_11 = fraction(1606764981773.0, 19613062656000.0);
        e1_12 = fraction(-137909.0, 6168960.0);
        e2_01 = fraction(-364463.0, 1920240.0);
        e2_06 = fraction(3399327.0, 763840.0);
        e2_07 = fraction(66578432.0, 35198415.0);
        e2_08 = fraction(-1674902723.0, 288716400.0);
        e2_09 = fraction(-74684743568175.0, 176692375811392.0);
        e2_10 = fraction(-734375.0, 4826304.0);
        e2_11 = fraction(171414593.0, 851261400.0);
        e2_12 = fraction(69869.0, 3084480.0);
    }

    /**
     * Simple constructor.
     * Build an eighth order Dormand-Prince integrator with the given step bounds
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
    public DormandPrince853FieldIntegrator(final Field<T> field, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(field, METHOD_NAME, 12, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        e1_01 = fraction(116092271.0, 8848465920.0);
        e1_06 = fraction(-1871647.0, 1527680.0);
        e1_07 = fraction(-69799717.0, 140793660.0);
        e1_08 = fraction(1230164450203.0, 739113984000.0);
        e1_09 = fraction(-1980813971228885.0, 5654156025964544.0);
        e1_10 = fraction(464500805.0, 1389975552.0);
        e1_11 = fraction(1606764981773.0, 19613062656000.0);
        e1_12 = fraction(-137909.0, 6168960.0);
        e2_01 = fraction(-364463.0, 1920240.0);
        e2_06 = fraction(3399327.0, 763840.0);
        e2_07 = fraction(66578432.0, 35198415.0);
        e2_08 = fraction(-1674902723.0, 288716400.0);
        e2_09 = fraction(-74684743568175.0, 176692375811392.0);
        e2_10 = fraction(-734375.0, 4826304.0);
        e2_11 = fraction(171414593.0, 851261400.0);
        e2_12 = fraction(69869.0, 3084480.0);
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
    protected DormandPrince853FieldStepInterpolator<T> createInterpolator(final boolean forward, T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, final FieldEquationsMapper<T> mapper) {
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
