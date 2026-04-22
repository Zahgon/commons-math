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

import org.apache.commons.math3.exception.MathInternalError;

/**
 * Enumerate for {@link EventFilter filtering events}.
 *
 * @since 3.2
 */
public enum FilterType {

    /**
     * Constant for triggering only decreasing events.
     * <p>When this filter is used, the wrapped {@link EventHandler
     * event handler} {@link EventHandler#eventOccurred(double, double[],
     * boolean) eventOccurred} method will be called <em>only</em> with
     * its {@code increasing} argument set to false.</p>
     */
    TRIGGER_ONLY_DECREASING_EVENTS {

        /**
         *  {@inheritDoc}
         */
        @Override
        protected boolean getTriggeredIncreasing() {
            // STUB: not implemented
            return false;
        }

        /**
         * {@inheritDoc}
         * <p>
         * states scheduling for computing h(t,y) as an altered version of g(t, y)
         * <ul>
         * <li>0 are triggered events for which a zero is produced (here decreasing events)</li>
         * <li>X are ignored events for which zero is masked (here increasing events)</li>
         * </ul>
         * </p>
         * <pre>
         *  g(t)
         *             ___                     ___                     ___
         *            /   \                   /   \                   /   \
         *           /     \                 /     \                 /     \
         *          /  g>0  \               /  g>0  \               /  g>0  \
         *         /         \             /         \             /         \
         *  ----- X --------- 0 --------- X --------- 0 --------- X --------- 0 ---
         *       /             \         /             \         /             \
         *      /               \ g<0   /               \  g<0  /               \ g<0
         *     /                 \     /                 \     /                 \     /
         * ___/                   \___/                   \___/                   \___/
         * </pre>
         * <pre>
         *  h(t,y)) as an alteration of g(t,y)
         *             ___                                 ___         ___
         *    \       /   \                               /   \       /   \
         *     \     /     \ h=+g                        /     \     /     \
         *      \   /       \      h=min(-s,-g,+g)      /       \   /       \
         *       \_/         \                         /         \_/         \
         *  ------ ---------- 0 ----------_---------- 0 --------------------- 0 ---
         *                     \         / \         /                         \
         *   h=max(+s,-g,+g)    \       /   \       /       h=max(+s,-g,+g)     \
         *                       \     /     \     / h=-g                        \     /
         *                        \___/       \___/                               \___/
         * </pre>
         * <p>
         * As shown by the figure above, several expressions are used to compute h,
         * depending on the current state:
         * <ul>
         *   <li>h = max(+s,-g,+g)</li>
         *   <li>h = +g</li>
         *   <li>h = min(-s,-g,+g)</li>
         *   <li>h = -g</li>
         * </ul>
         * where s is a tiny positive value: {@link org.apache.commons.math3.util.Precision#SAFE_MIN}.
         * </p>
         */
        @Override
        protected Transformer selectTransformer(final Transformer previous, final double g, final boolean forward) {
            // STUB: not implemented
            return null;
        }
    }
    ,
    /**
     * Constant for triggering only increasing events.
     * <p>When this filter is used, the wrapped {@link EventHandler
     * event handler} {@link EventHandler#eventOccurred(double, double[],
     * boolean) eventOccurred} method will be called <em>only</em> with
     * its {@code increasing} argument set to true.</p>
     */
    TRIGGER_ONLY_INCREASING_EVENTS {

        /**
         *  {@inheritDoc}
         */
        @Override
        protected boolean getTriggeredIncreasing() {
            // STUB: not implemented
            return false;
        }

        /**
         * {@inheritDoc}
         * <p>
         * states scheduling for computing h(t,y) as an altered version of g(t, y)
         * <ul>
         * <li>0 are triggered events for which a zero is produced (here increasing events)</li>
         * <li>X are ignored events for which zero is masked (here decreasing events)</li>
         * </ul>
         * </p>
         * <pre>
         *  g(t)
         *             ___                     ___                     ___
         *            /   \                   /   \                   /   \
         *           /     \                 /     \                 /     \
         *          /  g>0  \               /  g>0  \               /  g>0  \
         *         /         \             /         \             /         \
         *  ----- 0 --------- X --------- 0 --------- X --------- 0 --------- X ---
         *       /             \         /             \         /             \
         *      /               \ g<0   /               \  g<0  /               \ g<0
         *     /                 \     /                 \     /                 \     /
         * ___/                   \___/                   \___/                   \___/
         * </pre>
         * <pre>
         *  h(t,y)) as an alteration of g(t,y)
         *                                     ___         ___
         *    \                               /   \       /   \
         *     \ h=-g                        /     \     /     \ h=-g
         *      \      h=min(-s,-g,+g)      /       \   /       \      h=min(-s,-g,+g)
         *       \                         /         \_/         \
         *  ------0 ----------_---------- 0 --------------------- 0 --------- _ ---
         *         \         / \         /                         \         / \
         *          \       /   \       /       h=max(+s,-g,+g)     \       /   \
         *           \     /     \     / h=+g                        \     /     \     /
         *            \___/       \___/                               \___/       \___/
         * </pre>
         * <p>
         * As shown by the figure above, several expressions are used to compute h,
         * depending on the current state:
         * <ul>
         *   <li>h = max(+s,-g,+g)</li>
         *   <li>h = +g</li>
         *   <li>h = min(-s,-g,+g)</li>
         *   <li>h = -g</li>
         * </ul>
         * where s is a tiny positive value: {@link org.apache.commons.math3.util.Precision#SAFE_MIN}.
         * </p>
         */
        @Override
        protected Transformer selectTransformer(final Transformer previous, final double g, final boolean forward) {
            // STUB: not implemented
            return null;
        }
    }
    ;

    /**
     * Get the increasing status of triggered events.
     * @return true if triggered events are increasing events
     */
    protected abstract boolean getTriggeredIncreasing();

    /**
     * Get next function transformer in the specified direction.
     * @param previous transformer active on the previous point with respect
     * to integration direction (may be null if no previous point is known)
     * @param g current value of the g function
     * @param forward true if integration goes forward
     * @return next transformer transformer
     */
    protected abstract Transformer selectTransformer(Transformer previous, double g, boolean forward);
}
