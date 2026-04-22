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

import org.apache.commons.math3.ode.sampling.StepInterpolator;

/**
 * This class represents an interpolator over the last step during an
 * ODE integration for the 5(4) Higham and Hall integrator.
 *
 * @see HighamHall54Integrator
 *
 * @since 1.2
 */
class HighamHall54StepInterpolator extends RungeKuttaStepInterpolator {

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 20111120L;

    /**
     * Simple constructor.
     * This constructor builds an instance that is not usable yet, the
     * {@link
     * org.apache.commons.math3.ode.sampling.AbstractStepInterpolator#reinitialize}
     * method should be called before using the instance in order to
     * initialize the internal arrays. This constructor is used only
     * in order to delay the initialization in some cases. The {@link
     * EmbeddedRungeKuttaIntegrator} uses the prototyping design pattern
     * to create the step interpolators by cloning an uninitialized model
     * and later initializing the copy.
     */
    // CHECKSTYLE: stop RedundantModifier
    // the public modifier here is needed for serialization
    public HighamHall54StepInterpolator() {
        super();
    }

    // CHECKSTYLE: resume RedundantModifier
    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    HighamHall54StepInterpolator(final HighamHall54StepInterpolator interpolator) {
        super(interpolator);
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
     * {@inheritDoc}
     */
    @Override
    protected void computeInterpolatedStateAndDerivatives(final double theta, final double oneMinusThetaH) {
        // STUB: not implemented
    }
}
