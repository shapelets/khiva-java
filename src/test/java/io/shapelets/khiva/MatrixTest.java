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

public class MatrixTest {
    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }


    @Test
    public void testStompSelfJoin() throws Exception {
        double[] ta = {10, 10, 10, 11, 12, 11, 10, 10, 11, 12, 11, 10, 10, 10};
        long[] dims = {14, 1, 1, 1};
        Array a = new Array(ta, dims);
        Array[] stompSelfJoinResult = Matrix.stompSelfJoin(a, 3);
        double[] expectedIndex = {11, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 0};
        double[] matrix = stompSelfJoinResult[0].getData();
        int[] index = stompSelfJoinResult[1].getData();

        for (int i = 0; i < expectedIndex.length; i++) {
            Assert.assertEquals(matrix[i], 0, DELTA);
            Assert.assertEquals(index[i], expectedIndex[i], DELTA);
        }
    }

    @Test
    public void testStomp() throws Exception {
        double[] tss = {10, 10, 11, 11, 10, 11, 10, 10};

        long[] dims = {8, 1, 1, 1};

        Array a = new Array(tss, dims);
        Array b = new Array(tss, dims);

        double[] expectedIndex = {0, 1, 2, 3, 4, 5};
        Array[] stompResult = Matrix.stomp(a, b, 3);
        double[] matrix = stompResult[0].getData();
        int[] index = stompResult[1].getData();
        for (int i = 0; i < expectedIndex.length; i++) {
            Assert.assertEquals(matrix[i], 0, DELTA);
            Assert.assertEquals(index[i], expectedIndex[i], DELTA);
        }
    }

    @Test
    public void testFindBestNMotifs() throws Exception {
        float[][] tss = {{10, 10, 10, 10, 10, 10, 9, 10, 10, 10, 10, 10, 11, 10, 9},
                {10, 11, 10, 9}};
        long[] dims = {15, 1, 1, 1};
        long[] dimsB = {4, 1, 1, 1};

        Array a = new Array(tss[0], dims);
        Array b = new Array(tss[1], dimsB);

        Array[] stompResult = Matrix.stomp(a, b, 3);
        Array[] findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 2);

        int[] index = findMotifs[1].getData();
        int[] subsequenceIndex = findMotifs[2].getData();

        Assert.assertEquals(index[0], 12, DELTA);
        Assert.assertEquals(index[1], 11, DELTA);

        Assert.assertEquals(subsequenceIndex[0], 1, DELTA);
        Assert.assertEquals(subsequenceIndex[1], 0, DELTA);

    }

    @Test
    public void testFindBestNDiscords() throws Exception {
        double[][] tss = {{11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11},
                {9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 9}};

        long[] dims = {12, 1, 1, 1};
        Array a = new Array(tss[0], dims);
        Array b = new Array(tss[1], dims);

        Array[] stompResult = Matrix.stomp(a, b, 3);
        Array[] findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 2);
        int[] subsequenceIndex = findDiscords[2].getData();
        Assert.assertEquals(subsequenceIndex[0], 0, DELTA);
        Assert.assertEquals(subsequenceIndex[1], 9, DELTA);
    }
}
