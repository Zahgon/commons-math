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
package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;

/**
 * This class stores all information provided by an ODE integrator
 * during the integration process and build a continuous model of the
 * solution from this.
 *
 * <p>This class act as a step handler from the integrator point of
 * view. It is called iteratively during the integration process and
 * stores a copy of all steps information in a sorted collection for
 * later use. Once the integration process is over, the user can use
 * the {@link #getInterpolatedState(RealFieldElement) getInterpolatedState}
 * method to retrieve this information at any time. It is important to wait
 * for the integration to be over before attempting to call {@link
 * #getInterpolatedState(RealFieldElement)} because some internal
 * variables are set only once the last step has been handled.</p>
 *
 * <p>This is useful for example if the main loop of the user
 * application should remain independent from the integration process
 * or if one needs to mimic the behaviour of an analytical model
 * despite a numerical model is used (i.e. one needs the ability to
 * get the model value at any time or to navigate through the
 * data).</p>
 *
 * <p>If problem modeling is done with several separate
 * integration phases for contiguous intervals, the same
 * ContinuousOutputModel can be used as step handler for all
 * integration phases as long as they are performed in order and in
 * the same direction. As an example, one can extrapolate the
 * trajectory of a satellite with one model (i.e. one set of
 * differential equations) up to the beginning of a maneuver, use
 * another more complex model including thrusters modeling and
 * accurate attitude control during the maneuver, and revert to the
 * first model after the end of the maneuver. If the same continuous
 * output model handles the steps of all integration phases, the user
 * do not need to bother when the maneuver begins or ends, he has all
 * the data available in a transparent manner.</p>
 *
 * <p>One should be aware that the amount of data stored in a
 * ContinuousOutputFieldModel instance can be important if the state vector
 * is large, if the integration interval is long or if the steps are
 * small (which can result from small tolerance settings in {@link
 * org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator adaptive
 * step size integrators}).</p>
 *
 * @see FieldStepHandler
 * @see FieldStepInterpolator
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class ContinuousOutputFieldModel<T extends RealFieldElement<T>> implements FieldStepHandler<T> {

    /**
     * Initial integration time.
     */
    private T initialTime;

    /**
     * Final integration time.
     */
    private T finalTime;

    /**
     * Integration direction indicator.
     */
    private boolean forward;

    /**
     * Current interpolator index.
     */
    private int index;

    /**
     * Steps table.
     */
    private List<FieldStepInterpolator<T>> steps;

    /**
     * Simple constructor.
     * Build an empty continuous output model.
     */
    public ContinuousOutputFieldModel() {
        steps = new ArrayList<FieldStepInterpolator<T>>();
        initialTime = null;
        finalTime = null;
        forward = true;
        index = 0;
    }

    /**
     * Append another model at the end of the instance.
     * @param model model to add at the end of the instance
     * @exception MathIllegalArgumentException if the model to append is not
     * compatible with the instance (dimension of the state vector,
     * propagation direction, hole between the dates)
     * @exception DimensionMismatchException if the dimensions of the states or
     * the number of secondary states do not match
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * during step finalization
     */
    public void append(final ContinuousOutputFieldModel<T> model) throws MathIllegalArgumentException, MaxCountExceededException {
        // STUB: not implemented
    }

    /**
     * Check dimensions equality.
     * @param d1 first dimension
     * @param d2 second dimansion
     * @exception DimensionMismatchException if dimensions do not match
     */
    private void checkDimensionsEquality(final int d1, final int d2) throws DimensionMismatchException {
        if (d1 != d2) {
            throw new DimensionMismatchException(d2, d1);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void init(final FieldODEStateAndDerivative<T> initialState, final T t) {
        // STUB: not implemented
    }

    /**
     * Handle the last accepted step.
     * A copy of the information provided by the last step is stored in
     * the instance for later use.
     * @param interpolator interpolator for the last accepted step.
     * @param isLast true if the step is the last one
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * during step finalization
     */
    public void handleStep(final FieldStepInterpolator<T> interpolator, final boolean isLast) throws MaxCountExceededException {
        // STUB: not implemented
    }

    /**
     * Get the initial integration time.
     * @return initial integration time
     */
    public T getInitialTime() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the final integration time.
     * @return final integration time
     */
    public T getFinalTime() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the state at interpolated time.
     * @param time time of the interpolated point
     * @return state at interpolated time
     */
    public FieldODEStateAndDerivative<T> getInterpolatedState(final T time) {
        // STUB: not implemented
        return null;
    }

    /**
     * Compare a step interval and a double.
     * @param time point to locate
     * @param interval step interval
     * @return -1 if the double is before the interval, 0 if it is in
     * the interval, and +1 if it is after the interval, according to
     * the interval direction
     */
    private int locatePoint(final T time, final FieldStepInterpolator<T> interval) {
        if (forward) {
            if (time.subtract(interval.getPreviousState().getTime()).getReal() < 0) {
                return -1;
            } else if (time.subtract(interval.getCurrentState().getTime()).getReal() > 0) {
                return +1;
            } else {
                return 0;
            }
        }
        if (time.subtract(interval.getPreviousState().getTime()).getReal() > 0) {
            return -1;
        } else if (time.subtract(interval.getCurrentState().getTime()).getReal() < 0) {
            return +1;
        } else {
            return 0;
        }
    }
}
