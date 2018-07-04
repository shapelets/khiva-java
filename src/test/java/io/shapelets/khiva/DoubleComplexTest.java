/*
 * Copyright (c) 2018 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DoubleComplexTest {
    private static final double DELTA = 1e-6;

    @Test
    public void testConstructor() throws Exception {
        DoubleComplex dc = new DoubleComplex();
        Assert.assertEquals(dc.getReal(), 0, DELTA);
        Assert.assertEquals(dc.getImag(), 0, DELTA);
    }

    @Test
    public void testSettersAndGetters() throws Exception {
        DoubleComplex dc = new DoubleComplex();
        dc.setReal(1f);
        dc.setImag(2f);
        Assert.assertEquals(dc.getReal(), 1, DELTA);
        Assert.assertEquals(dc.getImag(), 2, DELTA);
    }

    @Test
    public void testToString() throws Exception {
        DoubleComplex dc = new DoubleComplex();
        dc.setReal(1f);
        dc.setImag(2f);
        Assert.assertEquals(dc.toString(), "1.0 + 2.0i");
        dc.setImag(-2f);
        Assert.assertEquals(dc.toString(), "1.0 - 2.0i");
    }
}
