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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

/**
 * This class implements an interpolator for the Gragg-Bulirsch-Stoer
 * integrator.
 *
 * <p>This interpolator compute dense output inside the last step
 * produced by a Gragg-Bulirsch-Stoer integrator.</p>
 *
 * <p>
 * This implementation is basically a reimplementation in Java of the
 * <a
 * href="http://www.unige.ch/math/folks/hairer/prog/nonstiff/odex.f">odex</a>
 * fortran code by E. Hairer and G. Wanner. The redistribution policy
 * for this code is available <a
 * href="http://www.unige.ch/~hairer/prog/licence.txt">here</a>, for
 * convenience, it is reproduced below.</p>
 * </p>
 *
 * <table border="0" width="80%" cellpadding="10" align="center" bgcolor="#E0E0E0">
 * <tr><td>Copyright (c) 2004, Ernst Hairer</td></tr>
 *
 * <tr><td>Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 * <ul>
 *  <li>Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.</li>
 *  <li>Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.</li>
 * </ul></td></tr>
 *
 * <tr><td><strong>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A  PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.</strong></td></tr>
 * </table>
 *
 * @see GraggBulirschStoerIntegrator
 * @since 1.2
 */
class GraggBulirschStoerStepInterpolator extends AbstractStepInterpolator {

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20110928L;

    /**
     * Slope at the beginning of the step.
     */
    private double[] y0Dot;

    /**
     * State at the end of the step.
     */
    private double[] y1;

    /**
     * Slope at the end of the step.
     */
    private double[] y1Dot;

    /**
     * Derivatives at the middle of the step.
     * element 0 is state at midpoint, element 1 is first derivative ...
     */
    private double[][] yMidDots;

    /**
     * Interpolation polynomials.
     */
    private double[][] polynomials;

    /**
     * Error coefficients for the interpolation.
     */
    private double[] errfac;

    /**
     * Degree of the interpolation polynomials.
     */
    private int currentDegree;

    /**
     * Simple constructor.
     * This constructor should not be used directly, it is only intended
     * for the serialization process.
     */
    // CHECKSTYLE: stop RedundantModifier
    // the public modifier here is needed for serialization
    public GraggBulirschStoerStepInterpolator() {
        y0Dot = null;
        y1 = null;
        y1Dot = null;
        yMidDots = null;
        resetTables(-1);
    }

    // CHECKSTYLE: resume RedundantModifier
    /**
     * Simple constructor.
     * @param y reference to the integrator array holding the current state
     * @param y0Dot reference to the integrator array holding the slope
     * at the beginning of the step
     * @param y1 reference to the integrator array holding the state at
     * the end of the step
     * @param y1Dot reference to the integrator array holding the slope
     * at the end of the step
     * @param yMidDots reference to the integrator array holding the
     * derivatives at the middle point of the step
     * @param forward integration direction indicator
     * @param primaryMapper equations mapper for the primary equations set
     * @param secondaryMappers equations mappers for the secondary equations sets
     */
    GraggBulirschStoerStepInterpolator(final double[] y, final double[] y0Dot, final double[] y1, final double[] y1Dot, final double[][] yMidDots, final boolean forward, final EquationsMapper primaryMapper, final EquationsMapper[] secondaryMappers) {
        super(y, forward, primaryMapper, secondaryMappers);
        this.y0Dot = y0Dot;
        this.y1 = y1;
        this.y1Dot = y1Dot;
        this.yMidDots = yMidDots;
        resetTables(yMidDots.length + 4);
    }

    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    GraggBulirschStoerStepInterpolator(final GraggBulirschStoerStepInterpolator interpolator) {
        super(interpolator);
        final int dimension = currentState.length;
        // the interpolator has been finalized,
        // the following arrays are not needed anymore
        y0Dot = null;
        y1 = null;
        y1Dot = null;
        yMidDots = null;
        // copy the interpolation polynomials (up to the current degree only)
        if (interpolator.polynomials == null) {
            polynomials = null;
            currentDegree = -1;
        } else {
            resetTables(interpolator.currentDegree);
            for (int i = 0; i < polynomials.length; ++i) {
                polynomials[i] = new double[dimension];
                System.arraycopy(interpolator.polynomials[i], 0, polynomials[i], 0, dimension);
            }
            currentDegree = interpolator.currentDegree;
        }
    }

    /**
     * Reallocate the internal tables.
     * Reallocate the internal tables in order to be able to handle
     * interpolation polynomials up to the given degree
     * @param maxDegree maximal degree to handle
     */
    private void resetTables(final int maxDegree) {
        if (maxDegree < 0) {
            polynomials = null;
            errfac = null;
            currentDegree = -1;
        } else {
            final double[][] newPols = new double[maxDegree + 1][];
            if (polynomials != null) {
                System.arraycopy(polynomials, 0, newPols, 0, polynomials.length);
                for (int i = polynomials.length; i < newPols.length; ++i) {
                    newPols[i] = new double[currentState.length];
                }
            } else {
                for (int i = 0; i < newPols.length; ++i) {
                    newPols[i] = new double[currentState.length];
                }
            }
            polynomials = newPols;
            // initialize the error factors array for interpolation
            if (maxDegree <= 4) {
                errfac = null;
            } else {
                errfac = new double[maxDegree - 4];
                for (int i = 0; i < errfac.length; ++i) {
                    final int ip5 = i + 5;
                    errfac[i] = 1.0 / (ip5 * ip5);
                    final double e = 0.5 * FastMath.sqrt(((double) (i + 1)) / ip5);
                    for (int j = 0; j <= i; ++j) {
                        errfac[i] *= e / (j + 1);
                    }
                }
            }
            currentDegree = 0;
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
     * Compute the interpolation coefficients for dense output.
     * @param mu degree of the interpolation polynomial
     * @param h current step
     */
    public void computeCoefficients(final int mu, final double h) {
        // STUB: not implemented
    }

    /**
     * Estimate interpolation error.
     * @param scale scaling array
     * @return estimate of the interpolation error
     */
    public double estimateError(final double[] scale) {
        // STUB: not implemented
        return 0.0;
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
