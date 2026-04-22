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

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.util.FastMath;

/**
 * This abstract class holds the common part of all adaptive
 * stepsize integrators for Ordinary Differential Equations.
 *
 * <p>These algorithms perform integration with stepsize control, which
 * means the user does not specify the integration step but rather a
 * tolerance on error. The error threshold is computed as
 * <pre>
 * threshold_i = absTol_i + relTol_i * max (abs (ym), abs (ym+1))
 * </pre>
 * where absTol_i is the absolute tolerance for component i of the
 * state vector and relTol_i is the relative tolerance for the same
 * component. The user can also use only two scalar values absTol and
 * relTol which will be used for all components.
 * </p>
 * <p>
 * If the Ordinary Differential Equations is an {@link ExpandableStatefulODE
 * extended ODE} rather than a {@link
 * org.apache.commons.math3.ode.FirstOrderDifferentialEquations basic ODE}, then
 * <em>only</em> the {@link ExpandableStatefulODE#getPrimaryState() primary part}
 * of the state vector is used for stepsize control, not the complete state vector.
 * </p>
 *
 * <p>If the estimated error for ym+1 is such that
 * <pre>
 * sqrt((sum (errEst_i / threshold_i)^2 ) / n) < 1
 * </pre>
 *
 * (where n is the main set dimension) then the step is accepted,
 * otherwise the step is rejected and a new attempt is made with a new
 * stepsize.</p>
 *
 * @since 1.2
 */
public abstract class AdaptiveStepsizeIntegrator extends AbstractIntegrator {

    /**
     * Allowed absolute scalar error.
     */
    protected double scalAbsoluteTolerance;

    /**
     * Allowed relative scalar error.
     */
    protected double scalRelativeTolerance;

    /**
     * Allowed absolute vectorial error.
     */
    protected double[] vecAbsoluteTolerance;

    /**
     * Allowed relative vectorial error.
     */
    protected double[] vecRelativeTolerance;

    /**
     * Main set dimension.
     */
    protected int mainSetDimension;

    /**
     * User supplied initial step.
     */
    private double initialStep;

    /**
     * Minimal step.
     */
    private double minStep;

    /**
     * Maximal step.
     */
    private double maxStep;

    /**
     * Build an integrator with the given stepsize bounds.
     * The default step handler does nothing.
     * @param name name of the method
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param scalAbsoluteTolerance allowed absolute error
     * @param scalRelativeTolerance allowed relative error
     */
    public AdaptiveStepsizeIntegrator(final String name, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) {
        super(name);
        setStepSizeControl(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        resetInternalState();
    }

    /**
     * Build an integrator with the given stepsize bounds.
     * The default step handler does nothing.
     * @param name name of the method
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param vecAbsoluteTolerance allowed absolute error
     * @param vecRelativeTolerance allowed relative error
     */
    public AdaptiveStepsizeIntegrator(final String name, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(name);
        setStepSizeControl(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        resetInternalState();
    }

    /**
     * Set the adaptive step size control parameters.
     * <p>
     * A side effect of this method is to also reset the initial
     * step so it will be automatically computed by the integrator
     * if {@link #setInitialStepSize(double) setInitialStepSize}
     * is not called by the user.
     * </p>
     * @param minimalStep minimal step (must be positive even for backward
     * integration), the last step can be smaller than this
     * @param maximalStep maximal step (must be positive even for backward
     * integration)
     * @param absoluteTolerance allowed absolute error
     * @param relativeTolerance allowed relative error
     */
    public void setStepSizeControl(final double minimalStep, final double maximalStep, final double absoluteTolerance, final double relativeTolerance) {
        // STUB: not implemented
    }

    /**
     * Set the adaptive step size control parameters.
     * <p>
     * A side effect of this method is to also reset the initial
     * step so it will be automatically computed by the integrator
     * if {@link #setInitialStepSize(double) setInitialStepSize}
     * is not called by the user.
     * </p>
     * @param minimalStep minimal step (must be positive even for backward
     * integration), the last step can be smaller than this
     * @param maximalStep maximal step (must be positive even for backward
     * integration)
     * @param absoluteTolerance allowed absolute error
     * @param relativeTolerance allowed relative error
     */
    public void setStepSizeControl(final double minimalStep, final double maximalStep, final double[] absoluteTolerance, final double[] relativeTolerance) {
        // STUB: not implemented
    }

    /**
     * Set the initial step size.
     * <p>This method allows the user to specify an initial positive
     * step size instead of letting the integrator guess it by
     * itself. If this method is not called before integration is
     * started, the initial step size will be estimated by the
     * integrator.</p>
     * @param initialStepSize initial step size to use (must be positive even
     * for backward integration ; providing a negative value or a value
     * outside of the min/max step interval will lead the integrator to
     * ignore the value and compute the initial step size by itself)
     */
    public void setInitialStepSize(final double initialStepSize) {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void sanityChecks(final ExpandableStatefulODE equations, final double t) throws DimensionMismatchException, NumberIsTooSmallException {
        // STUB: not implemented
    }

    /**
     * Initialize the integration step.
     * @param forward forward integration indicator
     * @param order order of the method
     * @param scale scaling vector for the state vector (can be shorter than state vector)
     * @param t0 start time
     * @param y0 state vector at t0
     * @param yDot0 first time derivative of y0
     * @param y1 work array for a state vector
     * @param yDot1 work array for the first time derivative of y1
     * @return first integration step
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * @exception DimensionMismatchException if arrays dimensions do not match equations settings
     */
    public double initializeStep(final boolean forward, final int order, final double[] scale, final double t0, final double[] y0, final double[] yDot0, final double[] y1, final double[] yDot1) throws MaxCountExceededException, DimensionMismatchException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Filter the integration step.
     * @param h signed step
     * @param forward forward integration indicator
     * @param acceptSmall if true, steps smaller than the minimal value
     * are silently increased up to this value, if false such small
     * steps generate an exception
     * @return a bounded integration step (h if no bound is reach, or a bounded value)
     * @exception NumberIsTooSmallException if the step is too small and acceptSmall is false
     */
    protected double filterStep(final double h, final boolean forward, final boolean acceptSmall) throws NumberIsTooSmallException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void integrate(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentStepStart() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Reset internal state to dummy values.
     */
    protected void resetInternalState() {
        // STUB: not implemented
    }

    /**
     * Get the minimal step.
     * @return minimal step
     */
    public double getMinStep() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Get the maximal step.
     * @return maximal step
     */
    public double getMaxStep() {
        // STUB: not implemented
        return 0.0;
    }
}
