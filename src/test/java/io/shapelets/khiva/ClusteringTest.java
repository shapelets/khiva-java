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

public class ClusteringTest {

    @BeforeClass
    public static void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testKMeans() throws Exception {
        float[] tss = {0.0f, 1.0f, 2.0f, 3.0f, 6.0f, 7.0f,  8.0f, 9.0f, 2.0f, -2.0f, 4.0f, -4.0f,
                8.0f, 5.0f,  3.0f, 1.0f, 15.0f, 10.0f, 5.0f, 0.0f, 7.0f, -7.0f,  1.0f, -1.0f};
        long[] dims = {4, 6, 1, 1};

        float[] expected = {0.0f, 0.1667f, 0.3333f, 0.5f,
                            1.5f, -1.5f, 0.8333f, -0.8333f,
                            4.8333f, 3.6667f, 2.6667f, 1.6667f};

        float tolerance = 1e-10f;
        int maxIterations = 100;
        int k = 3;
        try (
                Array a = new Array(tss, dims)
        ){
            Array[] result = Clustering.kMeans(a, k, tolerance, maxIterations);
            float[] centroids = result[0].getData();

            for (int i = 0; i < 4; i++){
                Assert.assertEquals(expected[i] + expected[i + 4] + expected[i + 8],
                        centroids[i] + centroids[i + 4] + centroids[i + 8], 1e-4f);
            }
            result[0].close();
            result[1].close();
        }
    }

    @Test
    public void testKShape() throws Exception {
        float[] tss = {1.0f,  2.0f,  3.0f,  4.0f,  5.0f,   6.0f,  7.0f,  0.0f, 10.0f, 4.0f, 5.0f, 7.0f,
                -3.0f, 0.0f,  -1.0f, 15.0f, -12.0f, 8.0f,  9.0f,  4.0f, 5.0f,  2.0f, 8.0f, 7.0f,
                -6.0f, -1.0f, 2.0f,  9.0f,  -5.0f,  -5.0f, -6.0f, 7.0f, 9.0f,  9.0f, 0.0f};
        long[] dims = {7, 5, 1, 1};

        float[] expected_c = {-0.5234f, 0.1560f, -0.3627f, -1.2764f, -0.7781f,  0.9135f,  1.8711f,
                -0.7825f, 1.5990f,  0.1701f,  0.4082f,  0.8845f, -1.4969f, -0.7825f,
                -0.6278f, 1.3812f, -2.0090f,  0.5022f,  0.6278f, -0.0000f,  0.1256f};


        float tolerance = 1e-10f;
        int maxIterations = 100;
        int k = 3;
        try (
                Array a = new Array(tss, dims)
        ){
            Array[] result = Clustering.kShape(a, k, tolerance, maxIterations);
            float[] centroids = result[0].getData();

            for (int i = 0; i < centroids.length; i++){
                Assert.assertEquals(expected_c[i], centroids[i],1e-4f);
            }
            result[0].close();
            result[1].close();
        }
    }
}
