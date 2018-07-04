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

public class FloatComplexTest {
    private static final double DELTA = 1e-6;

    @Test
    public void testConstructor() throws Exception {
        FloatComplex fc = new FloatComplex();
        Assert.assertEquals(fc.getReal(), 0, DELTA);
        Assert.assertEquals(fc.getImag(), 0, DELTA);
    }

    @Test
    public void testSettersAndGetters() throws Exception {
        FloatComplex fc = new FloatComplex();
        fc.setReal(1f);
        fc.setImag(2f);
        Assert.assertEquals(fc.getReal(), 1, DELTA);
        Assert.assertEquals(fc.getImag(), 2, DELTA);
    }

    @Test
    public void testToString() throws Exception {
        FloatComplex fc = new FloatComplex();
        fc.setReal(1f);
        fc.setImag(2f);
        Assert.assertEquals(fc.toString(), "1.0 + 2.0i");
        fc.setImag(-2f);
        Assert.assertEquals(fc.toString(), "1.0 - 2.0i");
    }
}
