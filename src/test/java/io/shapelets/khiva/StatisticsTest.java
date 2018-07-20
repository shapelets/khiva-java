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

public class StatisticsTest {
    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testCovarianceUnbiased() throws Exception {
        float[] timeSeries = {-2.1f, -1f, 4.3f, 3f, 1.1f, 0.12f, 3f, 1.1f, 0.12f};
        long[] dims = {3, 3, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Statistics.covariance(arrayOfTimeSeries)
        ) {
            float[] result = b.getData();
            float[] expected = {11.70999999f, -4.286f, -4.286f, -4.286f, 2.14413333f,
                    2.14413333f, -4.286f, 2.14413333f, 2.14413333f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], DELTA);
            }
        }
    }

    @Test
    public void testCovarianceBiased() throws Exception {
        float[] timeSeries = {-2.1f, -1f, 4.3f, 3f, 1.1f, 0.12f, 3f, 1.1f, 0.12f};
        long[] dims = {3, 3, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Statistics.covariance(arrayOfTimeSeries, false)
        ) {
            float[] result = b.getData();
            float[] expected = {7.80666667f, -2.85733333f, -2.85733333f, -2.85733333f, 1.42942222f,
                    1.42942222f, -2.85733333f, 1.42942222f, 1.42942222f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], DELTA);
            }
        }
    }

    @Test
    public void testKurtosis() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 2, 2, 2, 20, 30, 25};
        long[] dims = {6, 2, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Statistics.kurtosis(arrayOfTimeSeries)
        ) {
            float[] result = b.getData();
            float[] expected = {-1.2f, -2.66226722f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], 1e-2);
            }
        }
    }

    @Test
    public void testLjungBox() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7};
        long[] dims = {4, 2, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Statistics.ljungBox(arrayOfTimeSeries, 3)
        ) {
            float[] result = b.getData();
            float[] expected = {6.4400f, 6.4400f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], 1e-2);
            }
        }
    }

    @Test
    public void testMoment() throws Exception {
        float[] timeSeries = {0f, 1f, 2f, 3f, 4f, 5f, 0f, 1f, 2f, 3f, 4f, 5f};
        long[] dims = {6, 2, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Statistics.moment(arrayOfTimeSeries, 2)
        ) {
            float[] result = b.getData();
            float[] expected = {9.166666666f, 9.166666666f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], DELTA);
            }
            result = Statistics.moment(arrayOfTimeSeries, 4).getData();
            expected[0] = 163.1666666666f;
            expected[1] = 163.1666666666f;

            Assert.assertEquals(expected.length, result.length, 1e-2);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], 1e-2);
            }
        }
    }

    @Test
    public void testQuantile() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        float[] q = {0.1f, 0.2f};
        long[] dimsQ = {2, 1, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array qa = new Array(q, dimsQ);
                Array b = Statistics.quantile(arrayOfTimeSeries, qa)
        ) {
            float[] result = b.getData();
            float[] expected = {0.5f, 1.0f, 6.5f, 7.0f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], 1e-2);
            }
        }
    }

    @Test
    public void testQuantileCut2() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Statistics.quantilesCut(arrayOfTimeSeries, 2)
        ) {
            float[] result = b.getData();
            float[] expected = {-1.0E-8f, -1.0E-8f, -1.0E-8f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 5.0f, 5.0f, 5.0f, 6.0f,
                    6.0f, 6.0f, 8.5f, 8.5f, 8.5f, 8.5f, 8.5f, 8.5f, 11.0f, 11.0f, 11.0f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], 1e-2);
            }
        }
    }

    @Test
    public void testQuantileCut3() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Statistics.quantilesCut(arrayOfTimeSeries, 3)
        ) {
            float[] result = b.getData();

            float[] expected = {-1.0E-8f, -1.0E-8f, 1.6666667f, 1.6666667f, 3.3333335f, 3.3333335f, 1.6666667f,
                    1.6666667f, 3.3333335f, 3.3333335f, 5.0f, 5.0f, 6.0f, 6.0f, 7.6666665f, 7.6666665f,
                    9.333333f, 9.333333f, 7.6666665f, 7.6666665f, 9.333333f, 9.333333f, 11.0f, 11.0f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], 1e-2);
            }
        }
    }

    @Test
    public void testQuantileCut7() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Statistics.quantilesCut(arrayOfTimeSeries, 7)
        ) {
            float[] result = b.getData();
            float[] expected = {-1.0E-8f, 0.71428573f, 1.4285715f, 2.857143f, 3.5714288f, 4.2857146f, 0.71428573f,
                    1.4285715f, 2.1428573f, 3.5714288f, 4.2857146f, 5.0f, 6.0f, 6.714286f, 7.4285717f,
                    8.857143f, 9.571428f, 10.285714f, 6.714286f, 7.4285717f, 8.142858f, 9.571428f,
                    10.285714f, 11.0f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], 1e-2);
            }
        }
    }

    @Test
    public void testSampleStdev() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 2, 2, 2, 20, 30, 25};
        long[] dims = {6, 2, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Statistics.sampleStdev(arrayOfTimeSeries)
        ) {
            float[] result = b.getData();
            float[] expected = {1.870828693f, 12.988456413f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], DELTA);
            }
        }
    }

    @Test
    public void testSkewness() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 2, 2, 2, 20, 30, 25};
        long[] dims = {6, 2, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Statistics.skewness(arrayOfTimeSeries)
        ) {
            float[] result = b.getData();
            float[] expected = {0.0f, 0.236177069879499f};
            Assert.assertEquals(expected.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expected[i], result[i], 1e-2);
            }
        }
    }
}
