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
package org.apache.commons.math3.stat;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;

/**
 * Maintains a frequency distribution.
 * <p>
 * Accepts int, long, char or Comparable values.  New values added must be
 * comparable to those that have been added, otherwise the add method will
 * throw an IllegalArgumentException.</p>
 * <p>
 * Integer values (int, long, Integer, Long) are not distinguished by type --
 * i.e. <code>addValue(Long.valueOf(2)), addValue(2), addValue(2l)</code> all have
 * the same effect (similarly for arguments to <code>getCount,</code> etc.).</p>
 * <p>NOTE: byte and short values will be implicitly converted to int values
 * by the compiler, thus there are no explicit overloaded methods for these
 * primitive types.</p>
 * <p>
 * char values are converted by <code>addValue</code> to Character instances.
 * As such, these values are not comparable to integral values, so attempts
 * to combine integral types with chars in a frequency distribution will fail.
 * </p>
 * <p>
 * Float is not coerced to Double.
 * Since they are not Comparable with each other the user must do any necessary coercion.
 * Float.NaN and Double.NaN are not treated specially; they may occur in input and will
 * occur in output if appropriate.
 * </b>
 * <p>
 * The values are ordered using the default (natural order), unless a
 * <code>Comparator</code> is supplied in the constructor.</p>
 */
public class Frequency implements Serializable {

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -3845586908418844111L;

    /**
     * underlying collection
     */
    private final SortedMap<Comparable<?>, Long> freqTable;

    /**
     * Default constructor.
     */
    public Frequency() {
        freqTable = new TreeMap<Comparable<?>, Long>();
    }

    /**
     * Constructor allowing values Comparator to be specified.
     *
     * @param comparator Comparator used to order values
     */
    // TODO is the cast OK?
    @SuppressWarnings("unchecked")
    public Frequency(Comparator<?> comparator) {
        freqTable = new TreeMap<Comparable<?>, Long>((Comparator<? super Comparable<?>>) comparator);
    }

    /**
     * Return a string representation of this frequency distribution.
     *
     * @return a string representation.
     */
    @Override
    public String toString() {
        // STUB: not implemented
        return null;
    }

    /**
     * Adds 1 to the frequency count for v.
     * <p>
     * If other objects have already been added to this Frequency, v must
     * be comparable to those that have already been added.
     * </p>
     *
     * @param v the value to add.
     * @throws MathIllegalArgumentException if <code>v</code> is not comparable with previous entries
     */
    public void addValue(Comparable<?> v) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Adds 1 to the frequency count for v.
     *
     * @param v the value to add.
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Long
     */
    public void addValue(int v) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Adds 1 to the frequency count for v.
     *
     * @param v the value to add.
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Long
     */
    public void addValue(long v) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Adds 1 to the frequency count for v.
     *
     * @param v the value to add.
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Char
     */
    public void addValue(char v) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Increments the frequency count for v.
     * <p>
     * If other objects have already been added to this Frequency, v must
     * be comparable to those that have already been added.
     * </p>
     *
     * @param v the value to add.
     * @param increment the amount by which the value should be incremented
     * @throws MathIllegalArgumentException if <code>v</code> is not comparable with previous entries
     * @since 3.1
     */
    public void incrementValue(Comparable<?> v, long increment) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Increments the frequency count for v.
     * <p>
     * If other objects have already been added to this Frequency, v must
     * be comparable to those that have already been added.
     * </p>
     *
     * @param v the value to add.
     * @param increment the amount by which the value should be incremented
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Long
     * @since 3.3
     */
    public void incrementValue(int v, long increment) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Increments the frequency count for v.
     * <p>
     * If other objects have already been added to this Frequency, v must
     * be comparable to those that have already been added.
     * </p>
     *
     * @param v the value to add.
     * @param increment the amount by which the value should be incremented
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Long
     * @since 3.3
     */
    public void incrementValue(long v, long increment) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Increments the frequency count for v.
     * <p>
     * If other objects have already been added to this Frequency, v must
     * be comparable to those that have already been added.
     * </p>
     *
     * @param v the value to add.
     * @param increment the amount by which the value should be incremented
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Char
     * @since 3.3
     */
    public void incrementValue(char v, long increment) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Clears the frequency table
     */
    public void clear() {
        // STUB: not implemented
    }

    /**
     * Returns an Iterator over the set of values that have been added.
     * <p>
     * If added values are integral (i.e., integers, longs, Integers, or Longs),
     * they are converted to Longs when they are added, so the objects returned
     * by the Iterator will in this case be Longs.</p>
     *
     * @return values Iterator
     */
    public Iterator<Comparable<?>> valuesIterator() {
        // STUB: not implemented
        return null;
    }

    /**
     * Return an Iterator over the set of keys and values that have been added.
     * Using the entry set to iterate is more efficient in the case where you
     * need to access respective counts as well as values, since it doesn't
     * require a "get" for every key...the value is provided in the Map.Entry.
     * <p>
     * If added values are integral (i.e., integers, longs, Integers, or Longs),
     * they are converted to Longs when they are added, so the values of the
     * map entries returned by the Iterator will in this case be Longs.</p>
     *
     * @return entry set Iterator
     * @since 3.1
     */
    public Iterator<Map.Entry<Comparable<?>, Long>> entrySetIterator() {
        // STUB: not implemented
        return null;
    }

    //-------------------------------------------------------------------------
    /**
     * Returns the sum of all frequencies.
     *
     * @return the total frequency count.
     */
    public long getSumFreq() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns the number of values equal to v.
     * Returns 0 if the value is not comparable.
     *
     * @param v the value to lookup.
     * @return the frequency of v.
     */
    public long getCount(Comparable<?> v) {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns the number of values equal to v.
     *
     * @param v the value to lookup.
     * @return the frequency of v.
     */
    public long getCount(int v) {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns the number of values equal to v.
     *
     * @param v the value to lookup.
     * @return the frequency of v.
     */
    public long getCount(long v) {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns the number of values equal to v.
     *
     * @param v the value to lookup.
     * @return the frequency of v.
     */
    public long getCount(char v) {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns the number of values in the frequency table.
     *
     * @return the number of unique values that have been added to the frequency table.
     * @see #valuesIterator()
     */
    public int getUniqueCount() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns the percentage of values that are equal to v
     * (as a proportion between 0 and 1).
     * <p>
     * Returns <code>Double.NaN</code> if no values have been added.
     * Returns 0 if at least one value has been added, but v is not comparable
     * to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public double getPct(Comparable<?> v) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the percentage of values that are equal to v
     * (as a proportion between 0 and 1).
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public double getPct(int v) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the percentage of values that are equal to v
     * (as a proportion between 0 and 1).
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public double getPct(long v) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the percentage of values that are equal to v
     * (as a proportion between 0 and 1).
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public double getPct(char v) {
        // STUB: not implemented
        return 0.0;
    }

    //-----------------------------------------------------------------------------------------
    /**
     * Returns the cumulative frequency of values less than or equal to v.
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup.
     * @return the proportion of values equal to v
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public long getCumFreq(Comparable<?> v) {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns the cumulative frequency of values less than or equal to v.
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public long getCumFreq(int v) {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns the cumulative frequency of values less than or equal to v.
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public long getCumFreq(long v) {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns the cumulative frequency of values less than or equal to v.
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public long getCumFreq(char v) {
        // STUB: not implemented
        return 0;
    }

    //----------------------------------------------------------------------------------------------
    /**
     * Returns the cumulative percentage of values less than or equal to v
     * (as a proportion between 0 and 1).
     * <p>
     * Returns <code>Double.NaN</code> if no values have been added.
     * Returns 0 if at least one value has been added, but v is not comparable
     * to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values less than or equal to v
     */
    public double getCumPct(Comparable<?> v) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the cumulative percentage of values less than or equal to v
     * (as a proportion between 0 and 1).
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values less than or equal to v
     */
    public double getCumPct(int v) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the cumulative percentage of values less than or equal to v
     * (as a proportion between 0 and 1).
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values less than or equal to v
     */
    public double getCumPct(long v) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the cumulative percentage of values less than or equal to v
     * (as a proportion between 0 and 1).
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values less than or equal to v
     */
    public double getCumPct(char v) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the mode value(s) in comparator order.
     *
     * @return a list containing the value(s) which appear most often.
     * @since 3.3
     */
    public List<Comparable<?>> getMode() {
        // STUB: not implemented
        return null;
    }

    //----------------------------------------------------------------------------------------------
    /**
     * Merge another Frequency object's counts into this instance.
     * This Frequency's counts will be incremented (or set when not already set)
     * by the counts represented by other.
     *
     * @param other the other {@link Frequency} object to be merged
     * @throws NullArgumentException if {@code other} is null
     * @since 3.1
     */
    public void merge(final Frequency other) throws NullArgumentException {
        // STUB: not implemented
    }

    /**
     * Merge a {@link Collection} of {@link Frequency} objects into this instance.
     * This Frequency's counts will be incremented (or set when not already set)
     * by the counts represented by each of the others.
     *
     * @param others the other {@link Frequency} objects to be merged
     * @throws NullArgumentException if the collection is null
     * @since 3.1
     */
    public void merge(final Collection<Frequency> others) throws NullArgumentException {
        // STUB: not implemented
    }

    //----------------------------------------------------------------------------------------------
    /**
     * A Comparator that compares comparable objects using the
     * natural order.  Copied from Commons Collections ComparableComparator.
     * @param <T> the type of the objects compared
     */
    private static class NaturalComparator<T extends Comparable<T>> implements Comparator<Comparable<T>>, Serializable {

        /**
         * Serializable version identifier
         */
        private static final long serialVersionUID = -3852193713161395148L;

        /**
         * Compare the two {@link Comparable Comparable} arguments.
         * This method is equivalent to:
         * <pre>(({@link Comparable Comparable})o1).{@link Comparable#compareTo compareTo}(o2)</pre>
         *
         * @param  o1 the first object
         * @param  o2 the second object
         * @return  result of comparison
         * @throws NullPointerException when <i>o1</i> is <code>null</code>,
         *         or when <code>((Comparable)o1).compareTo(o2)</code> does
         * @throws ClassCastException when <i>o1</i> is not a {@link Comparable Comparable},
         *         or when <code>((Comparable)o1).compareTo(o2)</code> does
         */
        // cast to (T) may throw ClassCastException, see Javadoc
        @SuppressWarnings("unchecked")
        public int compare(Comparable<T> o1, Comparable<T> o2) {
            // STUB: not implemented
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        // STUB: not implemented
        return false;
    }
}
