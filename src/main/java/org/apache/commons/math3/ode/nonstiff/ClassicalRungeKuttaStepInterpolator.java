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
 * This class implements a step interpolator for the classical fourth
 * order Runge-Kutta integrator.
 *
 * <p>This interpolator allows to compute dense output inside the last
 * step computed. The interpolation equation is consistent with the
 * integration scheme :
 * <ul>
 *   <li>Using reference point at step start:<br>
 *   y(t<sub>n</sub> + &theta; h) = y (t<sub>n</sub>)
 *                    + &theta; (h/6) [  (6 - 9 &theta; + 4 &theta;<sup>2</sup>) y'<sub>1</sub>
 *                                     + (    6 &theta; - 4 &theta;<sup>2</sup>) (y'<sub>2</sub> + y'<sub>3</sub>)
 *                                     + (   -3 &theta; + 4 &theta;<sup>2</sup>) y'<sub>4</sub>
 *                                    ]
 *   </li>
 *   <li>Using reference point at step end:<br>
 *   y(t<sub>n</sub> + &theta; h) = y (t<sub>n</sub> + h)
 *                    + (1 - &theta;) (h/6) [ (-4 &theta;^2 + 5 &theta; - 1) y'<sub>1</sub>
 *                                          +(4 &theta;^2 - 2 &theta; - 2) (y'<sub>2</sub> + y'<sub>3</sub>)
 *                                          -(4 &theta;^2 +   &theta; + 1) y'<sub>4</sub>
 *                                        ]
 *   </li>
 * </ul>
 * </p>
 *
 * where &theta; belongs to [0 ; 1] and where y'<sub>1</sub> to y'<sub>4</sub> are the four
 * evaluations of the derivatives already computed during the
 * step.</p>
 *
 * @see ClassicalRungeKuttaIntegrator
 * @since 1.2
 */
class ClassicalRungeKuttaStepInterpolator extends RungeKuttaStepInterpolator {

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20111120L;

    /**
     * Simple constructor.
     * This constructor builds an instance that is not usable yet, the
     * {@link RungeKuttaStepInterpolator#reinitialize} method should be
     * called before using the instance in order to initialize the
     * internal arrays. This constructor is used only in order to delay
     * the initialization in some cases. The {@link RungeKuttaIntegrator}
     * class uses the prototyping design pattern to create the step
     * interpolators by cloning an uninitialized model and latter initializing
     * the copy.
     */
    // CHECKSTYLE: stop RedundantModifier
    // the public modifier here is needed for serialization
    public ClassicalRungeKuttaStepInterpolator() {
    }

    // CHECKSTYLE: resume RedundantModifier
    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    ClassicalRungeKuttaStepInterpolator(final ClassicalRungeKuttaStepInterpolator interpolator) {
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
