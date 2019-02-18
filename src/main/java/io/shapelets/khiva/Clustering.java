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

    private native static long[] kMeans(long ref, int bins);

    private native static long[] kShape(long ref, int bins);

    /**
     * Piecewise Aggregate Approximation (PAA) approximates a time series \(X\) of length \(n\) into vector
     * \(\bar{X}=(\bar{x}_{1},{@literal ...},\bar{x}_{M})\) of any arbitrary length \(M \leq n\) where each of \(\bar{x_{i}}\) is
     * calculated as follows:
     * \[
     * \bar{x}_{i} = \frac{M}{n} \sum_{j=n/M(i-1)+1}^{(n/M)i} x_{j}.
     * \]
     * Which simply means that in order to reduce the dimensionality from \f$n\f$ to \f$M\f$, we first divide the original
     * time series into \f$M\f$ equally sized frames and secondly compute the mean values for each frame. The sequence
     * assembled from the mean values is the PAA approximation (i.e., transform) of the original time series.
     *
     * @param arr  Set of points.
     * @param bins Sets the total number of divisions.
     * @return Array of points with the reduced dimensionality.
     */
    public static Array paa(Array arr, int bins) {
        long[] refs = kMeans(arr.getReference(), bins);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the number of Perceptually Important Points (PIP) in the time series.
     * <p>
     * [1] Fu TC, Chung FL, Luk R, and Ng CM. Representing financial time series based on data point importance.
     * Engineering Applications of Artificial Intelligence, 21(2):277-300, 2008.
     *
     * @param arr       Expects an input array whose dimension zero is the length of the time series.
     * @param numberIPs The number of points to be returned.
     * @return The updated ref and an array with the most Perceptually Important numberIPs.
     */
    public static Array pip(Array arr, int numberIPs) {
        long[] refs = kShape(arr.getReference(), numberIPs);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }
}
