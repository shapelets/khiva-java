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

public class DistancesTest {

    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testEuclidean() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {4, 3, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Distances.euclidean(arrayOfTimeSeries)
        ) {
            float[] result = b.getData();
            Assert.assertEquals(result[0], 0, DELTA);
            Assert.assertEquals(result[1], 0, DELTA);
            Assert.assertEquals(result[2], 0, DELTA);
            Assert.assertEquals(result[3], 8, DELTA);
            Assert.assertEquals(result[4], 0, DELTA);
            Assert.assertEquals(result[5], 0, DELTA);
            Assert.assertEquals(result[6], 16, DELTA);
            Assert.assertEquals(result[7], 8, DELTA);
            Assert.assertEquals(result[8], 0, DELTA);
        }
    }

    @Test
    public void testSquaredEuclidean() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {4, 3, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Distances.squaredEuclidean(arrayOfTimeSeries)
        ) {
            float[] result = b.getData();
            Assert.assertEquals(result[0], 0, DELTA);
            Assert.assertEquals(result[1], 0, DELTA);
            Assert.assertEquals(result[2], 0, DELTA);
            Assert.assertEquals(result[3], 64, DELTA);
            Assert.assertEquals(result[4], 0, DELTA);
            Assert.assertEquals(result[5], 0, DELTA);
            Assert.assertEquals(result[6], 256, DELTA);
            Assert.assertEquals(result[7], 64, DELTA);
            Assert.assertEquals(result[8], 0, DELTA);
        }
    }

    @Test
    public void testDwt() throws Exception {
        float[] timeSeries = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        long[] dims = {5, 5, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array resultArray = Distances.dtw(arrayOfTimeSeries)
        ) {
            float[] result = resultArray.getData();
            float[] expectedResult = {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 10, 5, 0, 0, 0, 15, 10, 5, 0, 0, 20, 15, 10, 5, 0};
            Assert.assertEquals(expectedResult.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expectedResult[i], result[i], DELTA);
            }
        }
    }

    @Test
    public void testHamming() throws Exception {
        float[] timeSeries = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        long[] dims = {5, 5, 1, 1};
        try (
                Array a = new Array(timeSeries, dims);
                Array b = Distances.hamming(a)
        ) {
            float[] result = b.getData();
            float[] expectedResult = {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 5, 5, 5, 5, 0};
            Assert.assertEquals(expectedResult.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expectedResult[i], result[i], DELTA);
            }
        }
    }

    @Test
    public void testManhattan() throws Exception {
        float[] timeSeries = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        long[] dims = {5, 5, 1, 1};
        try (
                Array a = new Array(timeSeries, dims);
                Array b = Distances.manhattan(a)
        ) {
            float[] result = b.getData();
            float[] expectedResult = {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 10, 5, 0, 0, 0, 15, 10, 5, 0, 0, 20, 15, 10, 5, 0};
            Assert.assertEquals(expectedResult.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(expectedResult[i], result[i], DELTA);
            }
        }
    }
}