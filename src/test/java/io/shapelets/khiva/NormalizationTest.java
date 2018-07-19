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

public class NormalizationTest {
    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testZnorm() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7};
        long[] dims = {4, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Normalization.znorm(a)
        ) {
            double[] result = b.getData();
            double[] expected = {-1.341640786499870, -0.447213595499958, 0.447213595499958, 1.341640786499870};
            for (int i = 0; i < expected.length; i++) {
                Assert.assertEquals(result[i], expected[i], DELTA);
                Assert.assertEquals(result[i + 4], expected[i], DELTA);
            }
        }
    }

    @Test
    public void testZnormInPlace() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7};
        long[] dims = {4, 2, 1, 1};
        try (Array a = new Array(tss, dims)) {
            Normalization.znormInPlace(a);
            double[] result = a.getData();
            Assert.assertEquals(result[0], -1.341640786499870, DELTA);
            Assert.assertEquals(result[1], -0.447213595499958, DELTA);
            Assert.assertEquals(result[2], 0.447213595499958, DELTA);
            Assert.assertEquals(result[3], 1.341640786499870, DELTA);

            Assert.assertEquals(result[4], -1.341640786499870, DELTA);
            Assert.assertEquals(result[5], -0.447213595499958, DELTA);
            Assert.assertEquals(result[6], 0.447213595499958, DELTA);
            Assert.assertEquals(result[7], 1.341640786499870, DELTA);
        }
    }

    @Test
    public void testMaxMinNorm() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7};
        long[] dims = {4, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Normalization.maxMinNorm(a, 2.0, 1.0)
        ) {
            double[] result = b.getData();
            double[] expected = {1.0, 1.3333333333333, 1.66666667, 2.0, 1.0, 1.3333333333333, 1.66666667, 2.0};
            Assert.assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void testMaxMinNormInPlace() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7};
        long[] dims = {4, 2, 1, 1};
        try (Array a = new Array(tss, dims)) {
            Normalization.maxMinNormInPlace(a, 2.0, 1.0);
            double[] result = a.getData();
            double[] expected = {1.0, 1.3333333333333, 1.66666667, 2.0, 1.0, 1.3333333333333, 1.66666667, 2.0};
            Assert.assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void decimalScalingNorm() throws Exception {
        double[] tss = {0, 1, -2, 3, 40, 50, 60, -70};
        long[] dims = {4, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Normalization.decimalScalingNorm(a)
        ) {
            double[] result = b.getData();
            double[] expected = {0.0, 0.1, -0.2, 0.3, 0.4, 0.5, 0.6, -0.7};
            Assert.assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void decimalScalingNormInPlace() throws Exception {
        double[] tss = {0, 1, -2, 3, 40, 50, 60, -70};
        long[] dims = {4, 2, 1, 1};
        try (Array a = new Array(tss, dims)) {
            Normalization.decimalScalingNormInPlace(a);
            double[] result = a.getData();
            double[] expected = {0.0, 0.1, -0.2, 0.3, 0.4, 0.5, 0.6, -0.7};
            Assert.assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void testMeanNorm() throws Exception {
        float[] tss = {0, 1, 2, 3, 4, 5, 6, 7};
        long[] dims = {4, 2, 1, 1};
        try (Array a = new Array(tss, dims); Array b = Normalization.meanNorm(a)) {
            float[] result = b.getData();
            float[] expectedResult = {-0.5f, -0.166666667f, 0.166666667f, 0.5f, -0.5f, -0.166666667f, 0.166666667f, 0.5f};
            Assert.assertEquals(expectedResult.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expectedResult[i], result[i], DELTA);
            }
        }
    }

    @Test
    public void testMeanNormInPlace() throws Exception {
        float[] tss = {0, 1, 2, 3, 4, 5, 6, 7};
        long[] dims = {4, 2, 1, 1};
        try (Array a = new Array(tss, dims)) {
            Normalization.meanNormInPlace(a);
            float[] result = a.getData();
            float[] expectedResult = {-0.5f, -0.166666667f, 0.166666667f, 0.5f, -0.5f, -0.166666667f, 0.166666667f, 0.5f};
            Assert.assertEquals(expectedResult.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expectedResult[i], result[i], DELTA);
            }
        }
    }
}
