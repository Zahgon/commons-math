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
package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;

/**
 * This class handles the state for one {@link EventHandler
 * event handler} during integration steps.
 *
 * <p>Each time the integrator proposes a step, the event handler
 * switching function should be checked. This class handles the state
 * of one handler during one integration step, with references to the
 * state at the end of the preceding step. This information is used to
 * decide if the handler should trigger an event or not during the
 * proposed step.</p>
 *
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class FieldEventState<T extends RealFieldElement<T>> {

    /**
     * Event handler.
     */
    private final FieldEventHandler<T> handler;

    /**
     * Maximal time interval between events handler checks.
     */
    private final double maxCheckInterval;

    /**
     * Convergence threshold for event localization.
     */
    private final T convergence;

    /**
     * Upper limit in the iteration count for event localization.
     */
    private final int maxIterationCount;

    /**
     * Time at the beginning of the step.
     */
    private T t0;

    /**
     * Value of the events handler at the beginning of the step.
     */
    private T g0;

    /**
     * Simulated sign of g0 (we cheat when crossing events).
     */
    private boolean g0Positive;

    /**
     * Indicator of event expected during the step.
     */
    private boolean pendingEvent;

    /**
     * Occurrence time of the pending event.
     */
    private T pendingEventTime;

    /**
     * Occurrence time of the previous event.
     */
    private T previousEventTime;

    /**
     * Integration direction.
     */
    private boolean forward;

    /**
     * Variation direction around pending event.
     *  (this is considered with respect to the integration direction)
     */
    private boolean increasing;

    /**
     * Next action indicator.
     */
    private Action nextAction;

    /**
     * Root-finding algorithm to use to detect state events.
     */
    private final BracketedRealFieldUnivariateSolver<T> solver;

    /**
     * Simple constructor.
     * @param handler event handler
     * @param maxCheckInterval maximal time interval between switching
     * function checks (this interval prevents missing sign changes in
     * case the integration steps becomes very large)
     * @param convergence convergence threshold in the event time search
     * @param maxIterationCount upper limit of the iteration count in
     * the event time search
     * @param solver Root-finding algorithm to use to detect state events
     */
    public FieldEventState(final FieldEventHandler<T> handler, final double maxCheckInterval, final T convergence, final int maxIterationCount, final BracketedRealFieldUnivariateSolver<T> solver) {
        this.handler = handler;
        this.maxCheckInterval = maxCheckInterval;
        this.convergence = convergence.abs();
        this.maxIterationCount = maxIterationCount;
        this.solver = solver;
        // some dummy values ...
        t0 = null;
        g0 = null;
        g0Positive = true;
        pendingEvent = false;
        pendingEventTime = null;
        previousEventTime = null;
        increasing = true;
        nextAction = Action.CONTINUE;
    }

    /**
     * Get the underlying event handler.
     * @return underlying event handler
     */
    public FieldEventHandler<T> getEventHandler() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the maximal time interval between events handler checks.
     * @return maximal time interval between events handler checks
     */
    public double getMaxCheckInterval() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Get the convergence threshold for event localization.
     * @return convergence threshold for event localization
     */
    public T getConvergence() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the upper limit in the iteration count for event localization.
     * @return upper limit in the iteration count for event localization
     */
    public int getMaxIterationCount() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Reinitialize the beginning of the step.
     * @param interpolator valid for the current step
     * @exception MaxCountExceededException if the interpolator throws one because
     * the number of functions evaluations is exceeded
     */
    public void reinitializeBegin(final FieldStepInterpolator<T> interpolator) throws MaxCountExceededException {
        // STUB: not implemented
    }

    /**
     * Evaluate the impact of the proposed step on the event handler.
     * @param interpolator step interpolator for the proposed step
     * @return true if the event handler triggers an event before
     * the end of the proposed step
     * @exception MaxCountExceededException if the interpolator throws one because
     * the number of functions evaluations is exceeded
     * @exception NoBracketingException if the event cannot be bracketed
     */
    public boolean evaluateStep(final FieldStepInterpolator<T> interpolator) throws MaxCountExceededException, NoBracketingException {
        // STUB: not implemented
        return false;
    }

    /**
     * Get the occurrence time of the event triggered in the current step.
     * @return occurrence time of the event triggered in the current
     * step or infinity if no events are triggered
     */
    public T getEventTime() {
        // STUB: not implemented
        return null;
    }

    /**
     * Acknowledge the fact the step has been accepted by the integrator.
     * @param state state at the end of the step
     */
    public void stepAccepted(final FieldODEStateAndDerivative<T> state) {
        // STUB: not implemented
    }

    /**
     * Check if the integration should be stopped at the end of the
     * current step.
     * @return true if the integration should be stopped
     */
    public boolean stop() {
        // STUB: not implemented
        return false;
    }

    /**
     * Let the event handler reset the state if it wants.
     * @param state state at the beginning of the next step
     * @return reset state (may by the same as initial state if only
     * derivatives should be reset), or null if nothing is reset
     */
    public FieldODEState<T> reset(final FieldODEStateAndDerivative<T> state) {
        // STUB: not implemented
        return null;
    }
}
