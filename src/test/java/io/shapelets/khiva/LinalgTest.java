/*
 * Copyright (c) 2019 Shapelets.io
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

public class LinalgTest {
    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() throws Exception {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testLls() throws Exception {
        double[] tss = {4, 3, -1, -2};
        long[] dims = {2, 2, 1, 1};
        double[] b = {3, 1};
        long[] dimsB = {2, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array c = Array.fromPrimitiveArray(b, dimsB);
             Array d = Linalg.lls(a, c)) {
            double[] result = d.getData();
            Assert.assertEquals(1, result[0], DELTA);
            Assert.assertEquals(1, result[1], DELTA);
        }
    }
}
