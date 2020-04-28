/*
 * Copyright (c) 2019 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FloatComplexTest {
    private static final double DELTA = 1e-6;

    @Test
    public void testConstructor() {
        FloatComplex fc = new FloatComplex();
        assertEquals(0, fc.getReal(), DELTA);
        assertEquals(0, fc.getImag(), DELTA);
    }

    @Test
    public void testSettersAndGetters() {
        FloatComplex fc = new FloatComplex();
        fc.setReal(1f);
        fc.setImag(2f);
        assertEquals(1, fc.getReal(), DELTA);
        assertEquals(2, fc.getImag(), DELTA);
    }

    @Test
    public void testToString() {
        FloatComplex fc = new FloatComplex();
        fc.setReal(1f);
        fc.setImag(2f);
        assertEquals("1.0 + 2.0i", fc.toString());
        fc.setImag(-2f);
        assertEquals("1.0 - 2.0i", fc.toString());
    }
}
