/*
 * Copyright (c) 2018 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package com.shapelets.khiva;

/**
 * Khiva Matrix Profile class containing matrix profile methods.
 */
public class Matrix extends Library {

    private native static long[] stomp(long a, long b, long m);

    private native static long[] stompSelfJoin(long a, long m);

    private native static long[] findBestNMotifs(long profile, long index, long n);

    private native static long[] findBestNDiscords(long profile, long index, long n);

    /**
     * STOMP algorithm to calculate the matrix profile between 'arrA' and 'arrB' using a subsequence length
     * of 'm'.
     *
     * @param arrA Array containing the input time series.
     * @param arrB Array containing the input time series.
     * @param m    Long with the subsequence length.
     * @return Array of arrays with the Matrix profile and index.
     */
    public static Array[] stomp(Array arrA, Array arrB, long m) {

        long[] refs = stomp(arrA.getReference(), arrB.getReference(), m);
        arrA.setReference(refs[0]);
        arrB.setReference(refs[1]);
        Array[] result = {new Array(refs[2]), new Array(refs[3])};
        return result;
    }

    /**
     * STOMP algorithm to calculate the matrix profile between 't' and itself using a subsequence length
     * of 'm'. This method filters the trivial matches.
     *
     * @param arr Array containing the input time series.
     * @param m   Long with the subsequence length.
     * @return Array of arrays with the Matrix profile and index.
     */
    public static Array[] stompSelfJoin(Array arr, long m) {
        long[] refs = stompSelfJoin(arr.getReference(), m);
        arr.setReference(refs[0]);
        Array[] result = {new Array(refs[1]), new Array(refs[2])};
        return result;
    }

    /**
     * This function extracts the best N discords from a previously calculated matrix profile.
     *
     * @param profile The matrix profile containing the minimum distance of each subsequence.
     * @param index   The matrix profile index
     * @param n       Number of motifs to extract.
     * @return Array of arrays with the distances, the indices and the indices in the compared time series.
     */
    public static Array[] findBestNMotifs(Array profile, Array index, long n) {
        long[] refs = findBestNMotifs(profile.getReference(), index.getReference(), n);
        profile.setReference(refs[0]);
        index.setReference(refs[1]);
        Array[] result = {new Array(refs[2]), new Array(refs[3]), new Array(refs[4])};
        return result;
    }

    /**
     * This function extracts the best N motifs from a previously calculated matrix profile.
     *
     * @param profile The matrix profile containing the minimum distance of each subsequence.
     * @param index   The matrix profile index.
     * @param n       Number of discords to extract.
     * @return Array of arrays with the distances, the indices and the indices in the compared time series.
     */
    public static Array[] findBestNDiscords(Array profile, Array index, long n) {
        long[] refs = findBestNDiscords(profile.getReference(), index.getReference(), n);
        profile.setReference(refs[0]);
        index.setReference(refs[1]);
        Array[] result = {new Array(refs[2]), new Array(refs[3]), new Array(refs[4])};
        return result;
    }
}
