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
import org.apache.commons.math3.util.FastMath;

/**
 * This class represents an interpolator over the last step during an
 * ODE integration for the 6th order Luther integrator.
 *
 * <p>This interpolator computes dense output inside the last
 * step computed. The interpolation equation is consistent with the
 * integration scheme.</p>
 *
 * @see LutherIntegrator
 * @since 3.3
 */
class LutherStepInterpolator extends RungeKuttaStepInterpolator {

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 20140416L;

    /**
     * Square root.
     */
    private static final double Q = FastMath.sqrt(21);

    /**
     * Simple constructor.
     * This constructor builds an instance that is not usable yet, the
     * {@link
     * org.apache.commons.math3.ode.sampling.AbstractStepInterpolator#reinitialize}
     * method should be called before using the instance in order to
     * initialize the internal arrays. This constructor is used only
     * in order to delay the initialization in some cases. The {@link
     * RungeKuttaIntegrator} class uses the prototyping design pattern
     * to create the step interpolators by cloning an uninitialized model
     * and later initializing the copy.
     */
    // CHECKSTYLE: stop RedundantModifier
    // the public modifier here is needed for serialization
    public LutherStepInterpolator() {
    }

    // CHECKSTYLE: resume RedundantModifier
    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    LutherStepInterpolator(final LutherStepInterpolator interpolator) {
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
