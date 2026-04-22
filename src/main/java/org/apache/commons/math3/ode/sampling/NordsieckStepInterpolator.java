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
package org.apache.commons.math3.ode.sampling;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.util.FastMath;

/**
 * This class implements an interpolator for integrators using Nordsieck representation.
 *
 * <p>This interpolator computes dense output around the current point.
 * The interpolation equation is based on Taylor series formulas.
 *
 * @see org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator
 * @see org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator
 * @since 2.0
 */
public class NordsieckStepInterpolator extends AbstractStepInterpolator {

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -7179861704951334960L;

    /**
     * State variation.
     */
    protected double[] stateVariation;

    /**
     * Step size used in the first scaled derivative and Nordsieck vector.
     */
    private double scalingH;

    /**
     * Reference time for all arrays.
     * <p>Sometimes, the reference time is the same as previousTime,
     * sometimes it is the same as currentTime, so we use a separate
     * field to avoid any confusion.
     * </p>
     */
    private double referenceTime;

    /**
     * First scaled derivative.
     */
    private double[] scaled;

    /**
     * Nordsieck vector.
     */
    private Array2DRowRealMatrix nordsieck;

    /**
     * Simple constructor.
     * This constructor builds an instance that is not usable yet, the
     * {@link AbstractStepInterpolator#reinitialize} method should be called
     * before using the instance in order to initialize the internal arrays. This
     * constructor is used only in order to delay the initialization in
     * some cases.
     */
    public NordsieckStepInterpolator() {
    }

    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    public NordsieckStepInterpolator(final NordsieckStepInterpolator interpolator) {
        super(interpolator);
        scalingH = interpolator.scalingH;
        referenceTime = interpolator.referenceTime;
        if (interpolator.scaled != null) {
            scaled = interpolator.scaled.clone();
        }
        if (interpolator.nordsieck != null) {
            nordsieck = new Array2DRowRealMatrix(interpolator.nordsieck.getDataRef(), true);
        }
        if (interpolator.stateVariation != null) {
            stateVariation = interpolator.stateVariation.clone();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected StepInterpolator doCopy() {
        // STUB: not implemented
        return null;
    }

    /**
     * Reinitialize the instance.
     * <p>Beware that all arrays <em>must</em> be references to integrator
     * arrays, in order to ensure proper update without copy.</p>
     * @param y reference to the integrator array holding the state at
     * the end of the step
     * @param forward integration direction indicator
     * @param primaryMapper equations mapper for the primary equations set
     * @param secondaryMappers equations mappers for the secondary equations sets
     */
    @Override
    public void reinitialize(final double[] y, final boolean forward, final EquationsMapper primaryMapper, final EquationsMapper[] secondaryMappers) {
        // STUB: not implemented
    }

    /**
     * Reinitialize the instance.
     * <p>Beware that all arrays <em>must</em> be references to integrator
     * arrays, in order to ensure proper update without copy.</p>
     * @param time time at which all arrays are defined
     * @param stepSize step size used in the scaled and Nordsieck arrays
     * @param scaledDerivative reference to the integrator array holding the first
     * scaled derivative
     * @param nordsieckVector reference to the integrator matrix holding the
     * Nordsieck vector
     */
    public void reinitialize(final double time, final double stepSize, final double[] scaledDerivative, final Array2DRowRealMatrix nordsieckVector) {
        // STUB: not implemented
    }

    /**
     * Rescale the instance.
     * <p>Since the scaled and Nordsieck arrays are shared with the caller,
     * this method has the side effect of rescaling this arrays in the caller too.</p>
     * @param stepSize new step size to use in the scaled and Nordsieck arrays
     */
    public void rescale(final double stepSize) {
        // STUB: not implemented
    }

    /**
     * Get the state vector variation from current to interpolated state.
     * <p>This method is aimed at computing y(t<sub>interpolation</sub>)
     * -y(t<sub>current</sub>) accurately by avoiding the cancellation errors
     * that would occur if the subtraction were performed explicitly.</p>
     * <p>The returned vector is a reference to a reused array, so
     * it should not be modified and it should be copied if it needs
     * to be preserved across several calls.</p>
     * @return state vector at time {@link #getInterpolatedTime}
     * @see #getInterpolatedDerivatives()
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     */
    public double[] getInterpolatedStateVariation() throws MaxCountExceededException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void computeInterpolatedStateAndDerivatives(final double theta, final double oneMinusThetaH) {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        // STUB: not implemented
    }
}
