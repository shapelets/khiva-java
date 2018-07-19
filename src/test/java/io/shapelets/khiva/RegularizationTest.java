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

public class RegularizationTest {
    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testGroupBySingleColumn() throws Exception {
        double[] tss = {0, 1, 1, 2, 2, 3, 0, 3, 3, 1, 1, 2};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Regularization.groupBy(a, 0)
        ) {
            double[] result = b.getData();
            double[] expected = {0, 3, 1, 2};
            Assert.assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void testGroupByDoubleKeyColumn() throws Exception {
        double[] tss = {0, 1, 1, 2, 2, 3, 1, 2, 2, 3, 3, 4, 0, 3, 3, 1, 1, 2};
        long[] dims = {6, 3, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Regularization.groupBy(a, 0, 2)
        ) {
            double[] result = b.getData();
            double[] expected = {0, 3, 1, 2};
            Assert.assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void testGroupByDoubleKeyColumn2() throws Exception {
        double[] tss = {0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 2, 3, 4, 5};
        long[] dims = {5, 3, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Regularization.groupBy(a, 0, 2)
        ) {
            double[] result = b.getData();
            double[] expected = {1, 2, 3.5, 5};
            Assert.assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void testGroupByDoubleKeyDoubleValueColumn() throws Exception {
        double[] tss = {0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 0, 1, 2, 3, 4, 1, 1, 1, 1, 1};
        long[] dims = {5, 4, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Regularization.groupBy(a, 0, 2, 2)
        ) {
            double[] result = b.getData();
            double[] expected = {1, 3.5, 1, 1};
            Assert.assertArrayEquals(result, expected, DELTA);
        }
    }
}
