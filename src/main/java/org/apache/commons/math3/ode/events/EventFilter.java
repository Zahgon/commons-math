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

import java.util.Arrays;

/**
 * Wrapper used to detect only increasing or decreasing events.
 *
 * <p>General {@link EventHandler events} are defined implicitly
 * by a {@link EventHandler#g(double, double[]) g function} crossing
 * zero. This function needs to be continuous in the event neighborhood,
 * and its sign must remain consistent between events. This implies that
 * during an ODE integration, events triggered are alternately events
 * for which the function increases from negative to positive values,
 * and events for which the function decreases from positive to
 * negative values.
 * </p>
 *
 * <p>Sometimes, users are only interested in one type of event (say
 * increasing events for example) and not in the other type. In these
 * cases, looking precisely for all events location and triggering
 * events that will later be ignored is a waste of computing time.</p>
 *
 * <p>Users can wrap a regular {@link EventHandler event handler} in
 * an instance of this class and provide this wrapping instance to
 * the {@link org.apache.commons.math3.ode.FirstOrderIntegrator ODE solver}
 * in order to avoid wasting time looking for uninteresting events.
 * The wrapper will intercept the calls to the {@link
 * EventHandler#g(double, double[]) g function} and to the {@link
 * EventHandler#eventOccurred(double, double[], boolean)
 * eventOccurred} method in order to ignore uninteresting events. The
 * wrapped regular {@link EventHandler event handler} will the see only
 * the interesting events, i.e. either only {@code increasing} events or
 * {@code decreasing} events. the number of calls to the {@link
 * EventHandler#g(double, double[]) g function} will also be reduced.</p>
 *
 * @since 3.2
 */
public class EventFilter implements EventHandler {

    /**
     * Number of past transformers updates stored.
     */
    private static final int HISTORY_SIZE = 100;

    /**
     * Wrapped event handler.
     */
    private final EventHandler rawHandler;

    /**
     * Filter to use.
     */
    private final FilterType filter;

    /**
     * Transformers of the g function.
     */
    private final Transformer[] transformers;

    /**
     * Update time of the transformers.
     */
    private final double[] updates;

    /**
     * Indicator for forward integration.
     */
    private boolean forward;

    /**
     * Extreme time encountered so far.
     */
    private double extremeT;

    /**
     * Wrap an {@link EventHandler event handler}.
     * @param rawHandler event handler to wrap
     * @param filter filter to use
     */
    public EventFilter(final EventHandler rawHandler, final FilterType filter) {
        this.rawHandler = rawHandler;
        this.filter = filter;
        this.transformers = new Transformer[HISTORY_SIZE];
        this.updates = new double[HISTORY_SIZE];
    }

    /**
     *  {@inheritDoc}
     */
    public void init(double t0, double[] y0, double t) {
        // STUB: not implemented
    }

    /**
     *  {@inheritDoc}
     */
    public double g(double t, double[] y) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     *  {@inheritDoc}
     */
    public Action eventOccurred(double t, double[] y, boolean increasing) {
        // STUB: not implemented
        return null;
    }

    /**
     *  {@inheritDoc}
     */
    public void resetState(double t, double[] y) {
        // STUB: not implemented
    }
}
