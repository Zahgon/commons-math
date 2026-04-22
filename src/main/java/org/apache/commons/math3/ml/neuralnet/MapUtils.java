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
package org.apache.commons.math3.ml.neuralnet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.util.Pair;

/**
 * Utilities for network maps.
 *
 * @since 3.3
 */
public class MapUtils {

    /**
     * Class contains only static methods.
     */
    private MapUtils() {
    }

    /**
     * Finds the neuron that best matches the given features.
     *
     * @param features Data.
     * @param neurons List of neurons to scan. If the list is empty
     * {@code null} will be returned.
     * @param distance Distance function. The neuron's features are
     * passed as the first argument to {@link DistanceMeasure#compute(double[],double[])}.
     * @return the neuron whose features are closest to the given data.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if the size of the input is not compatible with the neurons features
     * size.
     */
    public static Neuron findBest(double[] features, Iterable<Neuron> neurons, DistanceMeasure distance) {
        // STUB: not implemented
        return null;
    }

    /**
     * Finds the two neurons that best match the given features.
     *
     * @param features Data.
     * @param neurons List of neurons to scan. If the list is empty
     * {@code null} will be returned.
     * @param distance Distance function. The neuron's features are
     * passed as the first argument to {@link DistanceMeasure#compute(double[],double[])}.
     * @return the two neurons whose features are closest to the given data.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if the size of the input is not compatible with the neurons features
     * size.
     */
    public static Pair<Neuron, Neuron> findBestAndSecondBest(double[] features, Iterable<Neuron> neurons, DistanceMeasure distance) {
        // STUB: not implemented
        return null;
    }

    /**
     * Creates a list of neurons sorted in increased order of the distance
     * to the given {@code features}.
     *
     * @param features Data.
     * @param neurons List of neurons to scan. If it is empty, an empty array
     * will be returned.
     * @param distance Distance function.
     * @return the neurons, sorted in increasing order of distance in data
     * space.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if the size of the input is not compatible with the neurons features
     * size.
     *
     * @see #findBest(double[],Iterable,DistanceMeasure)
     * @see #findBestAndSecondBest(double[],Iterable,DistanceMeasure)
     *
     * @since 3.6
     */
    public static Neuron[] sort(double[] features, Iterable<Neuron> neurons, DistanceMeasure distance) {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the <a href="http://en.wikipedia.org/wiki/U-Matrix">
     *  U-matrix</a> of a two-dimensional map.
     *
     * @param map Network.
     * @param distance Function to use for computing the average
     * distance from a neuron to its neighbours.
     * @return the matrix of average distances.
     */
    public static double[][] computeU(NeuronSquareMesh2D map, DistanceMeasure distance) {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the "hit" histogram of a two-dimensional map.
     *
     * @param data Feature vectors.
     * @param map Network.
     * @param distance Function to use for determining the best matching unit.
     * @return the number of hits for each neuron in the map.
     */
    public static int[][] computeHitHistogram(Iterable<double[]> data, NeuronSquareMesh2D map, DistanceMeasure distance) {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the quantization error.
     * The quantization error is the average distance between a feature vector
     * and its "best matching unit" (closest neuron).
     *
     * @param data Feature vectors.
     * @param neurons List of neurons to scan.
     * @param distance Distance function.
     * @return the error.
     * @throws NoDataException if {@code data} is empty.
     */
    public static double computeQuantizationError(Iterable<double[]> data, Iterable<Neuron> neurons, DistanceMeasure distance) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Computes the topographic error.
     * The topographic error is the proportion of data for which first and
     * second best matching units are not adjacent in the map.
     *
     * @param data Feature vectors.
     * @param net Network.
     * @param distance Distance function.
     * @return the error.
     * @throws NoDataException if {@code data} is empty.
     */
    public static double computeTopographicError(Iterable<double[]> data, Network net, DistanceMeasure distance) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Helper data structure holding a (Neuron, double) pair.
     */
    private static class PairNeuronDouble {

        /**
         * Comparator.
         */
        static final Comparator<PairNeuronDouble> COMPARATOR = new Comparator<PairNeuronDouble>() {

            /**
             * {@inheritDoc}
             */
            public int compare(PairNeuronDouble o1, PairNeuronDouble o2) {
                // STUB: not implemented
                return 0;
            }
        };

        /**
         * Key.
         */
        private final Neuron neuron;

        /**
         * Value.
         */
        private final double value;

        /**
         * @param neuron Neuron.
         * @param value Value.
         */
        PairNeuronDouble(Neuron neuron, double value) {
            this.neuron = neuron;
            this.value = value;
        }

        /**
         * @return the neuron.
         */
        public Neuron getNeuron() {
            // STUB: not implemented
            return null;
        }
    }
}
