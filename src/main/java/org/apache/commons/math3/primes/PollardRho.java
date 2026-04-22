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
package org.apache.commons.math3.primes;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.util.FastMath;

/**
 * Implementation of the Pollard's rho factorization algorithm.
 * @since 3.2
 */
class PollardRho {

    /**
     * Hide utility class.
     */
    private PollardRho() {
    }

    /**
     * Factorization using Pollard's rho algorithm.
     * @param n number to factors, must be &gt; 0
     * @return the list of prime factors of n.
     */
    public static List<Integer> primeFactors(int n) {
        // STUB: not implemented
        return null;
    }

    /**
     * Implementation of the Pollard's rho factorization algorithm.
     * <p>
     * This implementation follows the paper "An improved Monte Carlo factorization algorithm"
     * by Richard P. Brent. This avoids the triple computation of f(x) typically found in Pollard's
     * rho implementations. It also batches several gcd computation into 1.
     * <p>
     * The backtracking is not implemented as we deal only with semi-primes.
     *
     * @param n number to factor, must be semi-prime.
     * @return a prime factor of n.
     */
    static int rhoBrent(final int n) {
        // STUB: not implemented
        return 0;
    }

    /**
     * Gcd between two positive numbers.
     * <p>
     * Gets the greatest common divisor of two numbers, using the "binary gcd" method,
     * which avoids division and modulo operations. See Knuth 4.5.2 algorithm B.
     * This algorithm is due to Josef Stein (1961).
     * </p>
     * Special cases:
     * <ul>
     * <li>The result of {@code gcd(x, x)}, {@code gcd(0, x)} and {@code gcd(x, 0)} is the value of {@code x}.</li>
     * <li>The invocation {@code gcd(0, 0)} is the only one which returns {@code 0}.</li>
     * </ul>
     *
     * @param a first number, must be &ge; 0
     * @param b second number, must be &ge; 0
     * @return gcd(a,b)
     */
    static int gcdPositive(int a, int b) {
        // STUB: not implemented
        return 0;
    }
}
