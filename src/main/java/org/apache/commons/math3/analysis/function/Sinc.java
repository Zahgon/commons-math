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
package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

/**
 * <a href="http://en.wikipedia.org/wiki/Sinc_function">Sinc</a> function,
 * defined by
 * <pre><code>
 *   sinc(x) = 1            if x = 0,
 *             sin(x) / x   otherwise.
 * </code></pre>
 *
 * @since 3.0
 */
public class Sinc implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {

    /**
     * Value below which the computations are done using Taylor series.
     * <p>
     * The Taylor series for sinc even order derivatives are:
     * <pre>
     * d^(2n)sinc/dx^(2n)     = Sum_(k>=0) (-1)^(n+k) / ((2k)!(2n+2k+1)) x^(2k)
     *                        = (-1)^n     [ 1/(2n+1) - x^2/(4n+6) + x^4/(48n+120) - x^6/(1440n+5040) + O(x^8) ]
     * </pre>
     * </p>
     * <p>
     * The Taylor series for sinc odd order derivatives are:
     * <pre>
     * d^(2n+1)sinc/dx^(2n+1) = Sum_(k>=0) (-1)^(n+k+1) / ((2k+1)!(2n+2k+3)) x^(2k+1)
     *                        = (-1)^(n+1) [ x/(2n+3) - x^3/(12n+30) + x^5/(240n+840) - x^7/(10080n+45360) + O(x^9) ]
     * </pre>
     * </p>
     * <p>
     * So the ratio of the fourth term with respect to the first term
     * is always smaller than x^6/720, for all derivative orders.
     * This implies that neglecting this term and using only the first three terms induces
     * a relative error bounded by x^6/720. The SHORTCUT value is chosen such that this
     * relative error is below double precision accuracy when |x| <= SHORTCUT.
     * </p>
     */
    private static final double SHORTCUT = 6.0e-3;

    /**
     * For normalized sinc function.
     */
    private final boolean normalized;

    /**
     * The sinc function, {@code sin(x) / x}.
     */
    public Sinc() {
        this(false);
    }

    /**
     * Instantiates the sinc function.
     *
     * @param normalized If {@code true}, the function is
     * <code> sin(&pi;x) / &pi;x</code>, otherwise {@code sin(x) / x}.
     */
    public Sinc(boolean normalized) {
        this.normalized = normalized;
    }

    /**
     * {@inheritDoc}
     */
    public double value(final double x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     * @deprecated as of 3.1, replaced by {@link #value(DerivativeStructure)}
     */
    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public DerivativeStructure value(final DerivativeStructure t) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }
}
