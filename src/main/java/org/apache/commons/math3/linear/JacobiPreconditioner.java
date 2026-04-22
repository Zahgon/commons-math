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
package org.apache.commons.math3.linear;

import org.apache.commons.math3.analysis.function.Sqrt;
import org.apache.commons.math3.util.MathArrays;

/**
 * This class implements the standard Jacobi (diagonal) preconditioner. For a
 * matrix A<sub>ij</sub>, this preconditioner is
 * M = diag(1 / A<sub>11</sub>, 1 / A<sub>22</sub>, &hellip;).
 *
 * @since 3.0
 */
public class JacobiPreconditioner extends RealLinearOperator {

    /**
     * The diagonal coefficients of the preconditioner.
     */
    private final ArrayRealVector diag;

    /**
     * Creates a new instance of this class.
     *
     * @param diag the diagonal coefficients of the linear operator to be
     * preconditioned
     * @param deep {@code true} if a deep copy of the above array should be
     * performed
     */
    public JacobiPreconditioner(final double[] diag, final boolean deep) {
        this.diag = new ArrayRealVector(diag, deep);
    }

    /**
     * Creates a new instance of this class. This method extracts the diagonal
     * coefficients of the specified linear operator. If {@code a} does not
     * extend {@link AbstractRealMatrix}, then the coefficients of the
     * underlying matrix are not accessible, coefficient extraction is made by
     * matrix-vector products with the basis vectors (and might therefore take
     * some time). With matrices, direct entry access is carried out.
     *
     * @param a the linear operator for which the preconditioner should be built
     * @return the diagonal preconditioner made of the inverse of the diagonal
     * coefficients of the specified linear operator
     * @throws NonSquareOperatorException if {@code a} is not square
     */
    public static JacobiPreconditioner create(final RealLinearOperator a) throws NonSquareOperatorException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnDimension() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowDimension() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector operate(final RealVector x) {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the square root of {@code this} diagonal operator. More
     * precisely, this method returns
     * P = diag(1 / &radic;A<sub>11</sub>, 1 / &radic;A<sub>22</sub>, &hellip;).
     *
     * @return the square root of {@code this} preconditioner
     * @since 3.1
     */
    public RealLinearOperator sqrt() {
        // STUB: not implemented
        return null;
    }
}
