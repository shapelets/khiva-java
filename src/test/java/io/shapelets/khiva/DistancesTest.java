/*
 * Copyright (c) 2019 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DistancesTest {

    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() throws Exception {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testEuclidean() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {4, 3, 1, 1};
        try (Array arrayOfTimeSeries = Array.fromPrimitiveArray(timeSeries, dims); Array b = Distances.euclidean(arrayOfTimeSeries)) {
            float[] result = b.getData();
            assertEquals(0, result[0], DELTA);
            assertEquals(0, result[1], DELTA);
            assertEquals(0, result[2], DELTA);
            assertEquals(8, result[3], DELTA);
            assertEquals(0, result[4], DELTA);
            assertEquals(0, result[5], DELTA);
            assertEquals(16, result[6], DELTA);
            assertEquals(8, result[7], DELTA);
            assertEquals(0, result[8], DELTA);
        }
    }

    @Test
    public void testDwt() throws Exception {
        float[] timeSeries = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        long[] dims = {5, 5, 1, 1};
        try (Array arrayOfTimeSeries = Array.fromPrimitiveArray(timeSeries, dims);
             Array resultArray = Distances.dtw(arrayOfTimeSeries)) {
            float[] result = resultArray.getData();
            float[] expectedResult = {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 10, 5, 0, 0, 0, 15, 10, 5, 0, 0, 20, 15, 10, 5, 0};
            assertEquals(expectedResult.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                assertEquals(expectedResult[i], result[i], DELTA);
            }
        }
    }

    @Test
    public void testHamming() throws Exception {
        float[] timeSeries = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        long[] dims = {5, 5, 1, 1};
        try (Array a = Array.fromPrimitiveArray(timeSeries, dims); Array b = Distances.hamming(a)) {
            float[] result = b.getData();
            float[] expectedResult = {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 5, 5, 5, 5, 0};
            assertEquals(expectedResult.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                assertEquals(expectedResult[i], result[i], DELTA);
            }
        }
    }

    @Test
    public void testManhattan() throws Exception {
        float[] timeSeries = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        long[] dims = {5, 5, 1, 1};
        try (Array a = Array.fromPrimitiveArray(timeSeries, dims); Array b = Distances.manhattan(a)) {
            float[] result = b.getData();
            float[] expectedResult = {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 10, 5, 0, 0, 0, 15, 10, 5, 0, 0, 20, 15, 10, 5, 0};
            assertEquals(expectedResult.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                assertEquals(expectedResult[i], result[i], DELTA);
            }
        }
    }

    @Test
    public void testSBD() throws Exception {
        float[] timeSeries = {1, 2, 3, 4, 5, 1, 1, 0, 1, 1, 10, 12, 0, 0, 1};
        long[] dims = {5, 3, 1, 1};
        try (Array a = Array.fromPrimitiveArray(timeSeries, dims); Array b = Distances.sbd(a)) {
            float[] result = b.getData();
            float[] expectedResult = {0, 0, 0, 0.505025f, 0, 0, 0.458583f, 0.564093f, 0};
            assertEquals(expectedResult.length, result.length, DELTA);
            for (int i = 0; i < result.length; i++) {
                assertEquals(expectedResult[i], result[i], DELTA);
            }
        }
    }

    @Test
    public void testSquaredEuclidean() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {4, 3, 1, 1};
        try (Array arrayOfTimeSeries = Array.fromPrimitiveArray(timeSeries, dims);
             Array b = Distances.squaredEuclidean(arrayOfTimeSeries)) {
            float[] result = b.getData();
            assertEquals(0, result[0], DELTA);
            assertEquals(0, result[1], DELTA);
            assertEquals(0, result[2], DELTA);
            assertEquals(64, result[3], DELTA);
            assertEquals(0, result[4], DELTA);
            assertEquals(0, result[5], DELTA);
            assertEquals(256, result[6], DELTA);
            assertEquals(64, result[7], DELTA);
            assertEquals(0, result[8], DELTA);
        }
    }
}
