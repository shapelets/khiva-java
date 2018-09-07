/*
 * Copyright (c) 2018 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

/**
 * Khiva Matrix Profile class containing matrix profile methods.
 */
public class Matrix extends Library {

    private native static long[] stomp(long a, long b, long m);

    private native static long[] stompSelfJoin(long a, long m);

    private native static long[] findBestNMotifs(long profile, long index, long m, long n, boolean selfJoin);

    private native static long[] findBestNDiscords(long profile, long index, long m, long n, boolean selfJoin);

    /**
     * STOMP algorithm to calculate the matrix profile between 'arrA' and 'arrB' using a subsequence length
     * of 'm'.
     *
     * (1) Yan Zhu, Zachary Zimmerman, Nader Shakibay Senobari, Chin-Chia Michael Yeh, Gareth Funning, Abdullah Mueen,
     * Philip Brisk and Eamonn Keogh (2016). Matrix Profile II: Exploiting a Novel Algorithm and GPUs to break
     * the one Hundred Million Barrier for Time Series Motifs and Joins. IEEE ICDM 2016.
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
     * (1) Yan Zhu, Zachary Zimmerman, Nader Shakibay Senobari, Chin-Chia Michael Yeh, Gareth Funning, Abdullah Mueen,
     * Philip Brisk and Eamonn Keogh (2016). Matrix Profile II: Exploiting a Novel Algorithm and GPUs to break
     * the one Hundred Million Barrier for Time Series Motifs and Joins. IEEE ICDM 2016.
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
     * @param profile  The matrix profile containing the minimum distance of each subsequence.
     * @param index    The matrix profile index
     * @param m        Subsequence length value used to calculate the input matrix profile.
     * @param n        Number of motifs to extract.
     * @param selfJoin Indicates whether the input profile comes from a self join operation or not. It determines
     *                 whether the mirror similar region is included in the output or not.
     * @return Array of arrays with the distances, the indices and the indices in the compared time series.
     */
    public static Array[] findBestNMotifs(Array profile, Array index, long m, long n, boolean selfJoin) {
        long[] refs = findBestNMotifs(profile.getReference(), index.getReference(), m, n, selfJoin);
        profile.setReference(refs[0]);
        index.setReference(refs[1]);
        Array[] result = {new Array(refs[2]), new Array(refs[3]), new Array(refs[4])};
        return result;
    }

    /**
     * This function extracts the best N discords from a previously calculated matrix profile.
     *
     * @param profile The matrix profile containing the minimum distance of each subsequence.
     * @param index   The matrix profile index
     * @param m       Subsequence length value used to calculate the input matrix profile.
     * @param n       Number of motifs to extract.
     * @return Array of arrays with the distances, the indices and the indices in the compared time series.
     */
    public static Array[] findBestNMotifs(Array profile, Array index, long m, long n) {
        return findBestNMotifs(profile, index, m, n, false);
    }

    /**
     * This function extracts the best N motifs from a previously calculated matrix profile.
     *
     * @param profile  The matrix profile containing the minimum distance of each subsequence.
     * @param index    The matrix profile index.
     * @param m        Subsequence length value used to calculate the input matrix profile.
     * @param n        Number of discords to extract.
     * @param selfJoin Indicates whether the input profile comes from a self join operation or not. It determines
     *                 whether the mirror similar region is included in the output or not.
     * @return Array of arrays with the distances, the indices and the indices in the compared time series.
     */
    public static Array[] findBestNDiscords(Array profile, Array index, long m, long n, boolean selfJoin) {
        long[] refs = findBestNDiscords(profile.getReference(), index.getReference(), m, n, selfJoin);
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
     * @param m       Subsequence length value used to calculate the input matrix profile.
     * @param n       Number of discords to extract.
     * @return Array of arrays with the distances, the indices and the indices in the compared time series.
     */
    public static Array[] findBestNDiscords(Array profile, Array index, long m, long n) {
        return findBestNDiscords(profile, index, m, n, false);
    }
}
