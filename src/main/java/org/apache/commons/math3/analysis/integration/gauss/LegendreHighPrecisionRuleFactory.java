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
package org.apache.commons.math3.analysis.integration.gauss;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;

/**
 * Factory that creates Gauss-type quadrature rule using Legendre polynomials.
 * In this implementation, the lower and upper bounds of the natural interval
 * of integration are -1 and 1, respectively.
 * The Legendre polynomials are evaluated using the recurrence relation
 * presented in <a href="http://en.wikipedia.org/wiki/Abramowitz_and_Stegun">
 * Abramowitz and Stegun, 1964</a>.
 *
 * @since 3.1
 */
public class LegendreHighPrecisionRuleFactory extends BaseRuleFactory<BigDecimal> {

    /**
     * Settings for enhanced precision computations.
     */
    private final MathContext mContext;

    /**
     * The number {@code 2}.
     */
    private final BigDecimal two;

    /**
     * The number {@code -1}.
     */
    private final BigDecimal minusOne;

    /**
     * The number {@code 0.5}.
     */
    private final BigDecimal oneHalf;

    /**
     * Default precision is {@link MathContext#DECIMAL128 DECIMAL128}.
     */
    public LegendreHighPrecisionRuleFactory() {
        this(MathContext.DECIMAL128);
    }

    /**
     * @param mContext Precision setting for computing the quadrature rules.
     */
    public LegendreHighPrecisionRuleFactory(MathContext mContext) {
        this.mContext = mContext;
        two = new BigDecimal("2", mContext);
        minusOne = new BigDecimal("-1", mContext);
        oneHalf = new BigDecimal("0.5", mContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Pair<BigDecimal[], BigDecimal[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }
}
