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

public class DoubleComplexTest {
    private static final double DELTA = 1e-6;

    @Test
    public void testConstructor() {
        DoubleComplex dc = new DoubleComplex();
        assertEquals(0, dc.getReal(), DELTA);
        assertEquals(0, dc.getImag(), DELTA);
    }

    @Test
    public void testSettersAndGetters() {
        DoubleComplex dc = new DoubleComplex();
        dc.setReal(1f);
        dc.setImag(2f);
        assertEquals(1, dc.getReal(), DELTA);
        assertEquals(2, dc.getImag(), DELTA);
    }

    @Test
    public void testToString() {
        DoubleComplex dc = new DoubleComplex();
        dc.setReal(1f);
        dc.setImag(2f);
        assertEquals("1.0 + 2.0i", dc.toString());
        dc.setImag(-2f);
        assertEquals("1.0 - 2.0i", dc.toString());
    }
}
