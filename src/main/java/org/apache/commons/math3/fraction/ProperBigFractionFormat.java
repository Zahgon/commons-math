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
package org.apache.commons.math3.fraction;

import java.math.BigInteger;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.exception.NullArgumentException;

/**
 * Formats a BigFraction number in proper format.  The number format for each of
 * the whole number, numerator and, denominator can be configured.
 * <p>
 * Minus signs are only allowed in the whole number part - i.e.,
 * "-3 1/2" is legitimate and denotes -7/2, but "-3 -1/2" is invalid and
 * will result in a <code>ParseException</code>.</p>
 *
 * @since 1.1
 */
public class ProperBigFractionFormat extends BigFractionFormat {

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -6337346779577272307L;

    /**
     * The format used for the whole number.
     */
    private NumberFormat wholeFormat;

    /**
     * Create a proper formatting instance with the default number format for
     * the whole, numerator, and denominator.
     */
    public ProperBigFractionFormat() {
        this(getDefaultNumberFormat());
    }

    /**
     * Create a proper formatting instance with a custom number format for the
     * whole, numerator, and denominator.
     * @param format the custom format for the whole, numerator, and
     *        denominator.
     */
    public ProperBigFractionFormat(final NumberFormat format) {
        this(format, (NumberFormat) format.clone(), (NumberFormat) format.clone());
    }

    /**
     * Create a proper formatting instance with a custom number format for each
     * of the whole, numerator, and denominator.
     * @param wholeFormat the custom format for the whole.
     * @param numeratorFormat the custom format for the numerator.
     * @param denominatorFormat the custom format for the denominator.
     */
    public ProperBigFractionFormat(final NumberFormat wholeFormat, final NumberFormat numeratorFormat, final NumberFormat denominatorFormat) {
        super(numeratorFormat, denominatorFormat);
        setWholeFormat(wholeFormat);
    }

    /**
     * Formats a {@link BigFraction} object to produce a string.  The BigFraction
     * is output in proper format.
     *
     * @param fraction the object to format.
     * @param toAppendTo where the text is to be appended
     * @param pos On input: an alignment field, if desired. On output: the
     *            offsets of the alignment field
     * @return the value passed in as toAppendTo.
     */
    @Override
    public StringBuffer format(final BigFraction fraction, final StringBuffer toAppendTo, final FieldPosition pos) {
        // STUB: not implemented
        return null;
    }

    /**
     * Access the whole format.
     * @return the whole format.
     */
    public NumberFormat getWholeFormat() {
        // STUB: not implemented
        return null;
    }

    /**
     * Parses a string to produce a {@link BigFraction} object.  This method
     * expects the string to be formatted as a proper BigFraction.
     * <p>
     * Minus signs are only allowed in the whole number part - i.e.,
     * "-3 1/2" is legitimate and denotes -7/2, but "-3 -1/2" is invalid and
     * will result in a <code>ParseException</code>.</p>
     *
     * @param source the string to parse
     * @param pos input/ouput parsing parameter.
     * @return the parsed {@link BigFraction} object.
     */
    @Override
    public BigFraction parse(final String source, final ParsePosition pos) {
        // STUB: not implemented
        return null;
    }

    /**
     * Modify the whole format.
     * @param format The new whole format value.
     * @throws NullArgumentException if {@code format} is {@code null}.
     */
    public void setWholeFormat(final NumberFormat format) {
        // STUB: not implemented
    }
}
