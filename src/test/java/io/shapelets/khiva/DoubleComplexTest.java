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
        assertEquals(dc.getReal(), 0, DELTA);
        assertEquals(dc.getImag(), 0, DELTA);
    }

    @Test
    public void testSettersAndGetters() {
        DoubleComplex dc = new DoubleComplex();
        dc.setReal(1f);
        dc.setImag(2f);
        assertEquals(dc.getReal(), 1, DELTA);
        assertEquals(dc.getImag(), 2, DELTA);
    }

    @Test
    public void testToString() {
        DoubleComplex dc = new DoubleComplex();
        dc.setReal(1f);
        dc.setImag(2f);
        assertEquals(dc.toString(), "1.0 + 2.0i");
        dc.setImag(-2f);
        assertEquals(dc.toString(), "1.0 - 2.0i");
    }
}
