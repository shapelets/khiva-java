/*
 * Copyright (c) 2019 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

/**
 * Khiva Clustering class containing several clustering methods.
 */
public class Clustering extends Library{

    private native static long[] kMeans(long tss, int k, float tolerance, int maxIterations);

    private native static long[] kShape(long tss, int k, float tolerance, int maxIterations);

    /**
     * Calculates the KMeans algorithm.
     *
     * [1] S. Lloyd. 1982. Least squares quantization in PCM. IEEE Transactions on Information Theory, 28, 2,
     * Pages 129-137.
     *
     * @param tss               The set of time series to be clusterized.
     * @param k                 The number of centroids.
     * @param tolerance         The maximum error tolerance.
     * @param maxIterations     The maximum number of iterations.
     *                          
     * @return An Array of arrays with the resulting centroids and labels.
     */
    public static Array[] kMeans(Array tss, int k, float tolerance, int maxIterations) {
        long[] refs = kMeans(tss.getReference(), k, tolerance, maxIterations);
        tss.setReference(refs[0]);
        Array[] result = {new Array(refs[1]), new Array(refs[2])};
        return result;
    }

    /**
     * Calculates the KShape algorithm.
     *
     * [1] John Paparrizos and Luis Gravano. 2016. k-Shape: Efficient and Accurate Clustering of Time Series.
     * SIGMOD Rec. 45, 1 (June 2016), 69-76.
     *
     * @param tss               The set of time series to be clusterized.
     * @param k                 The number of centroids.
     * @param tolerance         The maximum error tolerance.
     * @param maxIterations     The maximum number of iterations.
     *
     * @return An Array of arrays with the resulting centroids and labels.
     */
    public static Array[] kShape(Array tss, int k, float tolerance, int maxIterations) {
        long[] refs = kShape(tss.getReference(), k, tolerance, maxIterations);
        tss.setReference(refs[0]);
        Array[] result = {new Array(refs[1]), new Array(refs[2])};
        return result;
    }
}
