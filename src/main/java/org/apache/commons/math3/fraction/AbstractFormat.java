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

import java.io.Serializable;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/**
 * Common part shared by both {@link FractionFormat} and {@link BigFractionFormat}.
 * @since 2.0
 */
public abstract class AbstractFormat extends NumberFormat implements Serializable {

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -6981118387974191891L;

    /**
     * The format used for the denominator.
     */
    private NumberFormat denominatorFormat;

    /**
     * The format used for the numerator.
     */
    private NumberFormat numeratorFormat;

    /**
     * Create an improper formatting instance with the default number format
     * for the numerator and denominator.
     */
    protected AbstractFormat() {
        this(getDefaultNumberFormat());
    }

    /**
     * Create an improper formatting instance with a custom number format for
     * both the numerator and denominator.
     * @param format the custom format for both the numerator and denominator.
     */
    protected AbstractFormat(final NumberFormat format) {
        this(format, (NumberFormat) format.clone());
    }

    /**
     * Create an improper formatting instance with a custom number format for
     * the numerator and a custom number format for the denominator.
     * @param numeratorFormat the custom format for the numerator.
     * @param denominatorFormat the custom format for the denominator.
     */
    protected AbstractFormat(final NumberFormat numeratorFormat, final NumberFormat denominatorFormat) {
        this.numeratorFormat = numeratorFormat;
        this.denominatorFormat = denominatorFormat;
    }

    /**
     * Create a default number format.  The default number format is based on
     * {@link NumberFormat#getNumberInstance(java.util.Locale)}. The only
     * customization is the maximum number of BigFraction digits, which is set to 0.
     * @return the default number format.
     */
    protected static NumberFormat getDefaultNumberFormat() {
        // STUB: not implemented
        return null;
    }

    /**
     * Create a default number format.  The default number format is based on
     * {@link NumberFormat#getNumberInstance(java.util.Locale)}. The only
     * customization is the maximum number of BigFraction digits, which is set to 0.
     * @param locale the specific locale used by the format.
     * @return the default number format specific to the given locale.
     */
    protected static NumberFormat getDefaultNumberFormat(final Locale locale) {
        // STUB: not implemented
        return null;
    }

    /**
     * Access the denominator format.
     * @return the denominator format.
     */
    public NumberFormat getDenominatorFormat() {
        // STUB: not implemented
        return null;
    }

    /**
     * Access the numerator format.
     * @return the numerator format.
     */
    public NumberFormat getNumeratorFormat() {
        // STUB: not implemented
        return null;
    }

    /**
     * Modify the denominator format.
     * @param format the new denominator format value.
     * @throws NullArgumentException if {@code format} is {@code null}.
     */
    public void setDenominatorFormat(final NumberFormat format) {
        // STUB: not implemented
    }

    /**
     * Modify the numerator format.
     * @param format the new numerator format value.
     * @throws NullArgumentException if {@code format} is {@code null}.
     */
    public void setNumeratorFormat(final NumberFormat format) {
        // STUB: not implemented
    }

    /**
     * Parses <code>source</code> until a non-whitespace character is found.
     * @param source the string to parse
     * @param pos input/output parsing parameter.  On output, <code>pos</code>
     *        holds the index of the next non-whitespace character.
     */
    protected static void parseAndIgnoreWhitespace(final String source, final ParsePosition pos) {
        // STUB: not implemented
    }

    /**
     * Parses <code>source</code> until a non-whitespace character is found.
     * @param source the string to parse
     * @param pos input/output parsing parameter.
     * @return the first non-whitespace character.
     */
    protected static char parseNextCharacter(final String source, final ParsePosition pos) {
        // STUB: not implemented
        return '\0';
    }

    /**
     * Formats a double value as a fraction and appends the result to a StringBuffer.
     *
     * @param value the double value to format
     * @param buffer StringBuffer to append to
     * @param position On input: an alignment field, if desired. On output: the
     *            offsets of the alignment field
     * @return a reference to the appended buffer
     * @see #format(Object, StringBuffer, FieldPosition)
     */
    @Override
    public StringBuffer format(final double value, final StringBuffer buffer, final FieldPosition position) {
        // STUB: not implemented
        return null;
    }

    /**
     * Formats a long value as a fraction and appends the result to a StringBuffer.
     *
     * @param value the long value to format
     * @param buffer StringBuffer to append to
     * @param position On input: an alignment field, if desired. On output: the
     *            offsets of the alignment field
     * @return a reference to the appended buffer
     * @see #format(Object, StringBuffer, FieldPosition)
     */
    @Override
    public StringBuffer format(final long value, final StringBuffer buffer, final FieldPosition position) {
        // STUB: not implemented
        return null;
    }
}
