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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MatrixTest {
    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() throws Exception {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    private double getSingleValueDouble(Array arr, long dim0, long dim1, long dim2, long dim3) throws Exception {
        double[] data = arr.getData();
        long[] dims4 = arr.getDims();
        long offset = (dims4[0] * dims4[1] * dims4[2]) * dim3;

        offset += (dims4[0] * dims4[1]) * dim2;
        offset += dims4[0] * dim1;
        offset += dim0;

        return data[(int) offset];
    }

    private int getSingleValueInt(Array arr, long dim0, long dim1, long dim2, long dim3) throws Exception {
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

        try (Array t = Array.fromPrimitiveArray(tss, dimsTss); Array q = Array.fromPrimitiveArray(query, dimsQuery)) {

            double[] expectedDistance = {1.732051, 0.328954, 1.210135, 3.150851, 3.245858, 2.822044, 0.328954, 1.210135,
                                         3.150851, 0.248097, 3.30187, 2.82205};
            Array result = Matrix.mass(q, t);
            double[] distances = result.getData();

            assertArrayEquals(expectedDistance, distances, 1e-3);

            result.close();
        }

    }

    @Test
    public void testMassMultiple() throws Exception {
        double[] tss = {10, 10, 10, 11, 12, 11, 10, 10, 11, 12, 11, 14, 10, 10};
        long[] dimsTss = {7, 2, 1, 1};

        double[] query = {10, 10, 11, 11, 10, 11, 10, 10};
        long[] dimsQuery = {4, 2, 1, 1};

        try (Array t = Array.fromPrimitiveArray(tss, dimsTss); Array q = Array.fromPrimitiveArray(query, dimsQuery)) {

            double[] expectedDistance = {1.8388, 0.8739, 1.5307, 3.6955, 3.2660, 3.4897, 2.8284, 1.2116, 1.5307, 2.1758,
                                         2.5783, 3.7550, 2.8284, 2.8284, 3.2159, 0.5020};
            Array result = Matrix.mass(q, t);
            double[] distances = result.getData();

            assertArrayEquals(expectedDistance, distances, 1e-3);
            result.close();
        }

    }

    @Test
    public void testFindBestNOccurrences() throws Exception {
        double[] tss = {10, 10, 11, 11, 12, 11, 10, 10, 11, 12, 11, 10, 10, 11, 10, 10, 11, 11, 12, 11, 10, 10, 11, 12,
                        11, 10, 10, 11};
        long[] dimsTss = {28, 1, 1, 1};

        double[] query = {10, 11, 12};
        long[] dimsQuery = {3, 1, 1, 1};

        try (Array t = Array.fromPrimitiveArray(tss, dimsTss); Array q = Array.fromPrimitiveArray(query, dimsQuery)) {
            Array[] result = Matrix.findBestNOccurrences(q, t, 1);
            double[] distances = result[0].getData();
            int[] indexes = result[1].getData();

            assertEquals(0, distances[0], DELTA);
            assertEquals(7, indexes[0]);

            result[0].close();
            result[1].close();
        }

    }

    @Test
    public void testFindBestNOccurrencesMultipleQueries() throws Exception {
        double[] tss = {10, 10, 11, 11, 10, 11, 10, 10, 11, 11, 10, 11, 10, 10, 11, 10, 10, 11, 10, 11, 11, 10, 11, 11,
                        14, 10, 11, 10};
        long[] dimsTss = {14, 2, 1, 1};

        double[] query = {11, 11, 10, 11, 10, 11, 11, 12};
        long[] dimsQuery = {4, 2, 1, 1};

        try (Array t = Array.fromPrimitiveArray(tss, dimsTss); Array q = Array.fromPrimitiveArray(query, dimsQuery)) {
            Array[] result = Matrix.findBestNOccurrences(q, t, 4);

            double distance = getSingleValueDouble(result[0], 2, 0, 1, 0);
            assertEquals(1.83880, distance, 1e-3);

            int index = getSingleValueInt(result[1], 3, 1, 0, 0);
            assertEquals(2, index);

            result[0].close();
            result[1].close();
        }

    }

    @Test
    public void testStompSelfJoin() throws Exception {
        double[] ta = {10, 10, 10, 11, 12, 11, 10, 10, 11, 12, 11, 10, 10, 10};
        long[] dims = {14, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(ta, dims)) {
            Array[] stompSelfJoinResult = Matrix.stompSelfJoin(a, 3);
            double[] expectedIndex = {11, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 0};
            double[] matrix = stompSelfJoinResult[0].getData();
            int[] index = stompSelfJoinResult[1].getData();

            for (int i = 0; i < expectedIndex.length; i++) {
                assertEquals(0, matrix[i], DELTA);
                assertEquals(expectedIndex[i], index[i], DELTA);
            }
            stompSelfJoinResult[0].close();
            stompSelfJoinResult[1].close();
        }
    }

    @Test
    public void testStomp() throws Exception {
        double[] tss = {10, 10, 11, 11, 10, 11, 10, 10};

        long[] dims = {8, 1, 1, 1};

        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Array.fromPrimitiveArray(tss, dims)) {

            double[] expectedIndex = {0, 1, 2, 3, 4, 5};
            Array[] stompResult = Matrix.stomp(a, b, 3);
            double[] matrix = stompResult[0].getData();
            int[] index = stompResult[1].getData();
            for (int i = 0; i < expectedIndex.length; i++) {
                assertEquals(0, matrix[i], DELTA);
                assertEquals(expectedIndex[i], index[i], DELTA);
            }
            stompResult[0].close();
            stompResult[1].close();
        }
    }

    @Test
    public void testFindBestNMotifs() throws Exception {
        float[][] tss = {{10, 10, 10, 10, 10, 10, 9, 10, 10, 10, 10, 10, 11, 10, 9}, {10, 11, 10, 9}};
        long[] dims = {15, 1, 1, 1};
        long[] dimsB = {4, 1, 1, 1};

        try (Array a = Array.fromPrimitiveArray(tss[0], dims); Array b = Array.fromPrimitiveArray(tss[1], dimsB)) {

            Array[] stompResult = Matrix.stomp(a, b, 3);
            Array[] findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 1);

            int[] index = findMotifs[1].getData();
            int[] subsequenceIndex = findMotifs[2].getData();

            assertEquals(12, index[0], DELTA);
            assertEquals(1, subsequenceIndex[0], DELTA);

            stompResult[0].close();
            stompResult[1].close();
            findMotifs[0].close();
            findMotifs[1].close();
            findMotifs[2].close();
        }
    }

    @Test
    public void testFindBestNMotifsMultipleProfiles() throws Exception {
        float[] dataA = {10, 10, 10, 10, 10, 10, 9, 10, 10, 10, 10, 10, 11, 10, 9, 10, 10, 10, 10, 10, 10, 9, 10, 10,
                         10, 10, 10, 11, 10, 9};
        float[] dataB = {10, 11, 10, 9, 10, 11, 10, 9};
        long[] dims = {15, 2, 1, 1};
        long[] dimsB = {4, 2, 1, 1};

        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dimsB)) {

            Array[] stompResult = Matrix.stomp(a, b, 3);
            Array[] findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 1);

            int[] index = findMotifs[1].getData();
            int[] subsequenceIndex = findMotifs[2].getData();

            int[] expectedIndex = {12, 12, 12, 12};
            int[] expectedSubsequenceIndex = {1, 1, 1, 1};

            assertArrayEquals(expectedIndex, index);
            assertArrayEquals(expectedSubsequenceIndex, subsequenceIndex);

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

        try (Array a = Array.fromPrimitiveArray(tss, dims)) {

            Array[] stompResult = Matrix.stompSelfJoin(a, 3);
            Array[] findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 2, true);

            int[] index = findMotifs[1].getData();
            int[] subsequenceIndex = findMotifs[2].getData();

            int[] expectedIndex = {0, 0};
            int[] expectedSubsequenceIndex = {5, 3};

            assertArrayEquals(expectedIndex, index);
            assertArrayEquals(expectedSubsequenceIndex, subsequenceIndex);

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

        try (Array a = Array.fromPrimitiveArray(tss, dims)) {

            Array[] stompResult = Matrix.stompSelfJoin(a, 3);
            Array[] findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 2, true);

            int[] index = findMotifs[1].getData();
            int[] subsequenceIndex = findMotifs[2].getData();

            assertEquals(6, index[1], DELTA);
            assertEquals(3, subsequenceIndex[1], DELTA);

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
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims)) {

            Array[] stompResult = Matrix.stomp(a, b, 3);
            Array[] findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 2);
            int[] subsequenceIndex = findDiscords[2].getData();

            assertEquals(0, subsequenceIndex[0], DELTA);
            assertEquals(10, subsequenceIndex[1], DELTA);

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
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims)) {

            Array[] stompResult = Matrix.stomp(a, b, 3);
            Array[] findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 2);
            int[] subsequenceIndex = findDiscords[2].getData();

            int[] expectedSubsequenceIndex = {0, 10, 0, 10, 0, 10, 0, 10};

            assertArrayEquals(expectedSubsequenceIndex, subsequenceIndex);

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
        try (Array a = Array.fromPrimitiveArray(dataA, dims)) {

            Array[] stompResult = Matrix.stompSelfJoin(a, 3);
            Array[] findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 1, true);
            int[] index = findDiscords[1].getData();
            int[] subsequenceIndex = findDiscords[2].getData();

            assertEquals(3, index[0], DELTA);
            assertEquals(1, subsequenceIndex[0], DELTA);

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
        try (Array a = Array.fromPrimitiveArray(dataA, dims)) {

            Array[] stompResult = Matrix.stompSelfJoin(a, 3);
            Array[] findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 2, true);
            int[] subsequenceIndex = findDiscords[2].getData();

            assertEquals(12, subsequenceIndex[0], DELTA);
            String os = System.getenv("TRAVIS");
            if (os == null || !os.equals("true")) {
                assertNotEquals(11, subsequenceIndex[1], DELTA);
            }
            else {
                assertEquals(11, subsequenceIndex[1], DELTA);
            }
            stompResult[0].close();
            stompResult[1].close();
            findDiscords[0].close();
            findDiscords[1].close();
            findDiscords[2].close();
        }
    }
}
