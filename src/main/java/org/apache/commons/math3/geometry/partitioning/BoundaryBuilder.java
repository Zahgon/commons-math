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
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;

/**
 * Visitor building boundary shell tree.
 * <p>
 * The boundary shell is represented as {@link BoundaryAttribute boundary attributes}
 * at each internal node.
 * </p>
 * @param <S> Type of the space.
 * @since 3.4
 */
class BoundaryBuilder<S extends Space> implements BSPTreeVisitor<S> {

    /**
     * {@inheritDoc}
     */
    public Order visitOrder(BSPTree<S> node) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void visitInternalNode(BSPTree<S> node) {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public void visitLeafNode(BSPTree<S> node) {
        // STUB: not implemented
    }
}
