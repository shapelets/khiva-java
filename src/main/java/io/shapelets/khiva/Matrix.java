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
 * Khiva Matrix Profile class containing matrix profile methods.
 */
public class Matrix extends Library {

    private native static long mass(long query, long tss) throws Exception;

    private native static long[] findBestNOccurrences(long query, long tss, long n) throws Exception;

    private native static long[] stomp(long a, long b, long m) throws Exception;

    private native static long[] stompSelfJoin(long a, long m) throws Exception;

    private native static long[] findBestNMotifs(long profile, long index, long m, long n, boolean selfJoin)
        throws Exception;

    private native static long[] findBestNDiscords(long profile, long index, long m, long n, boolean selfJoin)
        throws Exception;

    /**
     * Mueen's Algorithm for Similarity Search.
     * <p>
     * The result has the following structure:
     * - 1st dimension corresponds to the index of the subsequence in the time series.
     * - 2nd dimension corresponds to the number of queries.
     * - 3rd dimension corresponds to the number of time series.
     * <p>
     * For example, the distance in the position (1, 2, 3) correspond to the distance of the third query to the
     * fourth time
     * series for the second subsequence in the time series.
     * <p>
     * [1] Chin-Chia Michael Yeh, Yan Zhu, Liudmila Ulanova, Nurjahan Begum, Yifei Ding, Hoang Anh Dau, Diego Furtado
     * Silva,
     * Abdullah Mueen, Eamonn Keogh (2016). Matrix Profile I: All Pairs Similarity Joins for Time Series: A Unifying
     * View
     * that Includes Motifs, Discords and Shapelets. IEEE ICDM 2016.
     *
     * @param query Array whose first dimension is the length of the query time series and the second dimension is the
     *              number of queries.
     * @param tss   Array whose first dimension is the length of the time series and the second dimension is the
     *              number of
     *              time series.
     * @return Array with the distances.
     */
    public static Array mass(Array query, Array tss) throws Exception {
        long ref = mass(query.getReference(), tss.getReference());
        return new Array(ref);
    }

    /**
     * Calculates the N best matches of several queries in several time series.
     * <p>
     * The result has the following structure:
     * - 1st dimension corresponds to the nth best match.
     * - 2nd dimension corresponds to the number of queries.
     * - 3rd dimension corresponds to the number of time series.
     * <p>
     * For example, the distance in the position (1, 2, 3) corresponds to the second best distance of the third query
     * in the
     * fourth time series. The index in the position (1, 2, 3) is the is the index of the subsequence which leads to the
     * second best distance of the third query in the fourth time series.
     *
     * @param query Array whose first dimension is the length of the query time series and the second dimension is the
     *              number of queries.
     * @param tss   Array whose first dimension is the length of the time series and the second dimension is the
     *              number of
     *              time series.
     * @param n     Number of matches to return.
     * @return Array or arrays with the distances and indexes.
     */
    public static Array[] findBestNOccurrences(Array query, Array tss, long n) throws Exception {
        long[] refs = findBestNOccurrences(query.getReference(), tss.getReference(), n);
        return new Array[]{new Array(refs[0]), new Array(refs[1])};
    }

    /**
     * STOMP algorithm to calculate the matrix profile between 'arrA' and 'arrB' using a subsequence length
     * of 'm'.
     * <p>
     * (1) Yan Zhu, Zachary Zimmerman, Nader Shakibay Senobari, Chin-Chia Michael Yeh, Gareth Funning, Abdullah Mueen,
     * Philip Brisk and Eamonn Keogh (2016). Matrix Profile II: Exploiting a Novel Algorithm and GPUs to break
     * the one Hundred Million Barrier for Time Series Motifs and Joins. IEEE ICDM 2016.
     *
     * @param arrA Array containing the input time series.
     * @param arrB Array containing the input time series.
     * @param m    Long with the subsequence length.
     * @return Array of arrays with the Matrix profile and index.
     */
    public static Array[] stomp(Array arrA, Array arrB, long m) throws Exception {
        long[] refs = stomp(arrA.getReference(), arrB.getReference(), m);
        return new Array[]{new Array(refs[0]), new Array(refs[1])};
    }

    /**
     * STOMP algorithm to calculate the matrix profile between 't' and itself using a subsequence length
     * of 'm'. This method filters the trivial matches.
     * <p>
     * (1) Yan Zhu, Zachary Zimmerman, Nader Shakibay Senobari, Chin-Chia Michael Yeh, Gareth Funning, Abdullah Mueen,
     * Philip Brisk and Eamonn Keogh (2016). Matrix Profile II: Exploiting a Novel Algorithm and GPUs to break
     * the one Hundred Million Barrier for Time Series Motifs and Joins. IEEE ICDM 2016.
     *
     * @param arr Array containing the input time series.
     * @param m   Long with the subsequence length.
     * @return Array of arrays with the Matrix profile and index.
     */
    public static Array[] stompSelfJoin(Array arr, long m) throws Exception {
        long[] refs = stompSelfJoin(arr.getReference(), m);
        return new Array[]{new Array(refs[0]), new Array(refs[1])};
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
    public static Array[] findBestNMotifs(Array profile, Array index, long m, long n, boolean selfJoin)
        throws Exception {
        long[] refs = findBestNMotifs(profile.getReference(), index.getReference(), m, n, selfJoin);
        return new Array[]{new Array(refs[0]), new Array(refs[1]), new Array(refs[2])};
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
    public static Array[] findBestNMotifs(Array profile, Array index, long m, long n) throws Exception {
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
    public static Array[] findBestNDiscords(Array profile, Array index, long m, long n, boolean selfJoin)
        throws Exception {
        long[] refs = findBestNDiscords(profile.getReference(), index.getReference(), m, n, selfJoin);
        return new Array[]{new Array(refs[0]), new Array(refs[1]), new Array(refs[2])};
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
    public static Array[] findBestNDiscords(Array profile, Array index, long m, long n) throws Exception {
        return findBestNDiscords(profile, index, m, n, false);
    }
}
