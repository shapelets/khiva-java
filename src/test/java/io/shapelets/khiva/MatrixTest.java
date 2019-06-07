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

public class MatrixTest {
    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    private double getSingleValueDouble(Array arr, long dim0, long dim1, long dim2, long dim3) {
        double[] data = arr.getData();

        long[] dims4 = arr.getDims();
        long offset = (dims4[0] * dims4[1] * dims4[2]) * dim3;
        offset += (dims4[0] * dims4[1]) * dim2;
        offset += dims4[0] * dim1;
        offset += dim0;

        return data[(int) offset];
    }

    private int getSingleValueInt(Array arr, long dim0, long dim1, long dim2, long dim3) {
        int[] data = arr.getData();

        long[] dims4 = arr.getDims();
        long offset = (dims4[0] * dims4[1] * dims4[2]) * dim3;
        offset += (dims4[0] * dims4[1]) * dim2;
        offset += dims4[0] * dim1;
        offset += dim0;

        return data[(int) offset];
    }

    @Test
    public void testMass() throws Exception {
        double[] tss = {10, 10, 10, 11, 12, 11, 10, 10, 11, 12, 11, 14, 10, 10};
        long[] dimsTss = {14, 1, 1, 1};

        double[] query = {4, 3, 8};
        long[] dimsQuery = {3, 1, 1, 1};

        try (
                Array t = new Array(tss, dimsTss);
                Array q = new Array(query, dimsQuery)
        ) {

            double[] expectedDistance = {1.732051, 0.328954, 1.210135, 3.150851, 3.245858, 2.822044,
                    0.328954, 1.210135, 3.150851, 0.248097, 3.30187, 2.82205};
            Array result = Matrix.mass(q, t);
            double[] distances = result.getData();

            Assert.assertArrayEquals(expectedDistance, distances, 1e-3);

            result.close();
        }

    }

    @Test
    public void testMassMultiple() throws Exception {
        double[] tss = {10, 10, 10, 11, 12, 11, 10, 10, 11, 12, 11, 14, 10, 10};
        long[] dimsTss = {7, 2, 1, 1};

        double[] query = {10, 10, 11, 11, 10, 11, 10, 10};
        long[] dimsQuery = {4, 2, 1, 1};

        try (
                Array t = new Array(tss, dimsTss);
                Array q = new Array(query, dimsQuery)
        ) {

            double[] expectedDistance = {1.8388, 0.8739, 1.5307, 3.6955, 3.2660, 3.4897, 2.8284, 1.2116, 1.5307,
                    2.1758, 2.5783, 3.7550, 2.8284, 2.8284, 3.2159, 0.5020};
            Array result = Matrix.mass(q, t);
            double[] distances = result.getData();

            Assert.assertArrayEquals(expectedDistance, distances, 1e-3);

            result.close();
        }

    }

    @Test
    public void testFindBestNOccurrences() throws Exception {
        double[] tss = {10, 10, 11, 11, 12, 11, 10, 10, 11, 12, 11, 10, 10, 11, 10, 10, 11,
                11, 12, 11, 10, 10, 11, 12, 11, 10, 10, 11};
        long[] dimsTss = {28, 1, 1, 1};

        double[] query = {10, 11, 12};
        long[] dimsQuery = {3, 1, 1, 1};

        try (
                Array t = new Array(tss, dimsTss);
                Array q = new Array(query, dimsQuery)
        ) {
            Array[] result = Matrix.findBestNOccurrences(q, t, 1);
            double[] distances = result[0].getData();
            int[] indexes = result[1].getData();

            Assert.assertEquals(distances[0], 0, DELTA);
            Assert.assertEquals(indexes[0], 7);

            result[0].close();
            result[1].close();
        }

    }

    @Test
    public void testFindBestNOccurrencesMultipleQueries() throws Exception {
        double[] tss = {10, 10, 11, 11, 10, 11, 10, 10, 11, 11, 10, 11, 10, 10,
                11, 10, 10, 11, 10, 11, 11, 10, 11, 11, 14, 10, 11, 10};
        long[] dimsTss = {14, 2, 1, 1};

        double[] query = {11, 11, 10, 11, 10, 11, 11, 12};
        long[] dimsQuery = {4, 2, 1, 1};

        try (
                Array t = new Array(tss, dimsTss);
                Array q = new Array(query, dimsQuery)
        ) {
            Array[] result = Matrix.findBestNOccurrences(q, t, 4);

            double distance = getSingleValueDouble(result[0], 2, 0, 1, 0);
            Assert.assertEquals(distance, 1.83880, 1e-3);

            int index = getSingleValueInt(result[1], 3, 1, 0, 0);
            Assert.assertEquals(index, 2);

            result[0].close();
            result[1].close();
        }

    }

    @Test
    public void testStompSelfJoin() throws Exception {
        double[] ta = {10, 10, 10, 11, 12, 11, 10, 10, 11, 12, 11, 10, 10, 10};
        long[] dims = {14, 1, 1, 1};
        try (Array a = new Array(ta, dims)) {
            Array[] stompSelfJoinResult = Matrix.stompSelfJoin(a, 3);
            double[] expectedIndex = {11, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 0};
            double[] matrix = stompSelfJoinResult[0].getData();
            int[] index = stompSelfJoinResult[1].getData();

            for (int i = 0; i < expectedIndex.length; i++) {
                Assert.assertEquals(matrix[i], 0, DELTA);
                Assert.assertEquals(index[i], expectedIndex[i], DELTA);
            }
            stompSelfJoinResult[0].close();
            stompSelfJoinResult[1].close();
        }
    }

    @Test
    public void testStomp() throws Exception {
        double[] tss = {10, 10, 11, 11, 10, 11, 10, 10};

        long[] dims = {8, 1, 1, 1};

        try (
                Array a = new Array(tss, dims);
                Array b = new Array(tss, dims)
        ) {

            double[] expectedIndex = {0, 1, 2, 3, 4, 5};
            Array[] stompResult = Matrix.stomp(a, b, 3);
            double[] matrix = stompResult[0].getData();
            int[] index = stompResult[1].getData();
            for (int i = 0; i < expectedIndex.length; i++) {
                Assert.assertEquals(matrix[i], 0, DELTA);
                Assert.assertEquals(index[i], expectedIndex[i], DELTA);
            }
            stompResult[0].close();
            stompResult[1].close();
        }
    }

    @Test
    public void testFindBestNMotifs() throws Exception {
        float[][] tss = {{10, 10, 10, 10, 10, 10, 9, 10, 10, 10, 10, 10, 11, 10, 9},
                {10, 11, 10, 9}};
        long[] dims = {15, 1, 1, 1};
        long[] dimsB = {4, 1, 1, 1};

        try (
                Array a = new Array(tss[0], dims);
                Array b = new Array(tss[1], dimsB)
        ) {

            Array[] stompResult = Matrix.stomp(a, b, 3);
            Array[] findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 1);

            int[] index = findMotifs[1].getData();
            int[] subsequenceIndex = findMotifs[2].getData();

            Assert.assertEquals(index[0], 12, DELTA);
            Assert.assertEquals(subsequenceIndex[0], 1, DELTA);

            stompResult[0].close();
            stompResult[1].close();
            findMotifs[0].close();
            findMotifs[1].close();
            findMotifs[2].close();
        }
    }

    @Test
    public void testFindBestNMotifsMultipleProfiles() throws Exception {
        float[] dataA = {10, 10, 10, 10, 10, 10, 9, 10, 10, 10, 10, 10, 11, 10, 9, 10, 10, 10, 10, 10, 10, 9, 10, 10, 10, 10, 10, 11, 10, 9};
        float[] dataB = {10, 11, 10, 9, 10, 11, 10, 9};
        long[] dims = {15, 2, 1, 1};
        long[] dimsB = {4, 2, 1, 1};

        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dimsB)) {

            Array[] stompResult = Matrix.stomp(a, b, 3);
            Array[] findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 1);

            int[] index = findMotifs[1].getData();
            int[] subsequenceIndex = findMotifs[2].getData();

            int[] expectedIndex = {12, 12, 12, 12};
            int[] expectedSubsequenceIndex = {1, 1, 1, 1};

            Assert.assertArrayEquals(index, expectedIndex);
            Assert.assertArrayEquals(subsequenceIndex, expectedSubsequenceIndex);

            stompResult[0].close();
            stompResult[1].close();
            findMotifs[0].close();
            findMotifs[1].close();
            findMotifs[2].close();
        }
    }

    @Test
    public void testFindBestNMotifsMirror() throws Exception {
        float[] tss = {10.1f, 11, 10.2f, 10.15f, 10.775f, 10.1f, 11, 10.2f};
        long[] dims = {8, 1, 1, 1};

        try (Array a = new Array(tss, dims)) {

            Array[] stompResult = Matrix.stompSelfJoin(a, 3);
            Array[] findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 2, true);

            int[] index = findMotifs[1].getData();
            int[] subsequenceIndex = findMotifs[2].getData();

            int[] expectedIndex = {0, 0};
            int[] expectedSubsequenceIndex = {5, 3};

            Assert.assertArrayEquals(index, expectedIndex);
            Assert.assertArrayEquals(subsequenceIndex, expectedSubsequenceIndex);

            stompResult[0].close();
            stompResult[1].close();
            findMotifs[0].close();
            findMotifs[1].close();
            findMotifs[2].close();
        }
    }

    @Test
    public void testFindBestNMotifsConsecutive() throws Exception {
        float[] tss = {10.1f, 11, 10.1f, 10.15f, 10.075f, 10.1f, 11, 10.1f, 10.15f};
        long[] dims = {9, 1, 1, 1};

        try (Array a = new Array(tss, dims)) {

            Array[] stompResult = Matrix.stompSelfJoin(a, 3);
            Array[] findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 2, true);

            int[] index = findMotifs[1].getData();
            int[] subsequenceIndex = findMotifs[2].getData();

            Assert.assertEquals(index[1], 6, DELTA);
            Assert.assertEquals(subsequenceIndex[1], 3, DELTA);

            stompResult[0].close();
            stompResult[1].close();
            findMotifs[0].close();
            findMotifs[1].close();
            findMotifs[2].close();
        }
    }

    @Test
    public void testFindBestNDiscords() throws Exception {
        float[] dataA = {11, 10, 11, 10, 11, 10, 11, 10, 11, 10, 11, 10, 1};
        float[] dataB = {9, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 9};

        long[] dims = {13, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims)) {

            Array[] stompResult = Matrix.stomp(a, b, 3);
            Array[] findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 2);
            int[] subsequenceIndex = findDiscords[2].getData();

            Assert.assertEquals(subsequenceIndex[0], 0, DELTA);
            Assert.assertEquals(subsequenceIndex[1], 10, DELTA);

            stompResult[0].close();
            stompResult[1].close();
            findDiscords[0].close();
            findDiscords[1].close();
            findDiscords[2].close();
        }
    }

    @Test
    public void testFindBestNDiscordsMultipleProfiles() throws Exception {
        float[] dataA = {11, 10, 11, 10, 11, 10, 11, 10, 11, 10, 11, 10, 1, 11, 10, 11, 10, 11, 10, 11, 10, 11, 10, 11,
                10, 1};
        float[] dataB = {9, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 9, 9, 10.1f,
                10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 9};

        long[] dims = {13, 2, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims)) {

            Array[] stompResult = Matrix.stomp(a, b, 3);
            Array[] findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 2);
            int[] subsequenceIndex = findDiscords[2].getData();

            int[] expectedSubsequenceIndex = {0, 10, 0, 10, 0, 10, 0, 10};

            Assert.assertArrayEquals(subsequenceIndex, expectedSubsequenceIndex);

            stompResult[0].close();
            stompResult[1].close();
            findDiscords[0].close();
            findDiscords[1].close();
            findDiscords[2].close();
        }
    }

    @Test
    public void testFindBestNDiscordsMirror() throws Exception {
        float[] dataA = {10, 11, 10, 10, 11, 10};

        long[] dims = {6, 1, 1, 1};
        try (Array a = new Array(dataA, dims)) {

            Array[] stompResult = Matrix.stompSelfJoin(a, 3);
            Array[] findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 1, true);
            int[] index = findDiscords[1].getData();
            int[] subsequenceIndex = findDiscords[2].getData();

            Assert.assertEquals(index[0], 3, DELTA);
            Assert.assertEquals(subsequenceIndex[0], 1, DELTA);

            stompResult[0].close();
            stompResult[1].close();
            findDiscords[0].close();
            findDiscords[1].close();
            findDiscords[2].close();
        }
    }

    @Test
    public void testFindBestNDiscordsConsecutive() throws Exception {
        float[] dataA = {10, 11, 10, 11, 10, 11, 10, 11, 10, 11, 10, 11, 10, 9.999f, 9.998f};

        long[] dims = {15, 1, 1, 1};
        try (Array a = new Array(dataA, dims)) {

            Array[] stompResult = Matrix.stompSelfJoin(a, 3);
            Array[] findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 2, true);
            int[] subsequenceIndex = findDiscords[2].getData();

            Assert.assertEquals(subsequenceIndex[0], 12, DELTA);
            String os = System.getenv("TRAVIS");
            if (os == null || !os.equals("true")) {
                Assert.assertNotEquals(subsequenceIndex[1], 11, DELTA);
            } else {
                Assert.assertEquals(subsequenceIndex[1], 11, DELTA);
            }
            stompResult[0].close();
            stompResult[1].close();
            findDiscords[0].close();
            findDiscords[1].close();
            findDiscords[2].close();
        }
    }
}
