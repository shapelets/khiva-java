/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package tsa;

public class Matrix extends Library {
    /**
     * Stomp Library's native function.
     *
     * @param ta  Array of doubles with the first time series.
     * @param tb  Array of doubles with the second time series.
     * @param lta Integer with the ta length.
     * @param ltb Integer with the tb length.
     * @param m   Long with the subsequence length.
     * @param p   Array of doubles for storing the distances.
     * @param i   Array of integers for storing the indexes.
     */
    private native static void stomp(double[] ta, double[] tb, long lta, long ltb, long m, double[] p, int[] i);

    /**
     * StompSelfJoin Library's native function.
     *
     * @param ta  Array of doubles with the time series.
     * @param lta Integer with the ta length.
     * @param m   Long with the subsequence length.
     * @param p   Array of doubles for storing the distances.
     * @param i   Array of integers for storing the indexes.
     */
    private native static void stompSelfJoin(double[] ta, long lta, long m, double[] p, int[] i);

    /**
     * findBestMotifs Library's native function.
     *
     * @param profile            The matrix profile containing the minimum distance of each sequence.
     * @param index              The matrix profile index containing where each minimum occurs
     * @param lengthProfile      Length of the matrix profile
     * @param n                  Number of motifs to extract
     * @param motifDistances     The distance of the best N motifs
     * @param motifIndices       The indices of the best N motifs
     * @param subsequenceIndices The indices of the query sequences that produced the minimum reported in the motifs.
     */
    private native static void findBestNMotifs(double[] profile, int[] index, long lengthProfile, long n,
                                               double[] motifDistances, int[] motifIndices, int[] subsequenceIndices);

    /**
     * findBestNDiscords Library's native function.
     *
     * @param profile            The matrix profile containing the minimum distance of each subsequence
     * @param index              The matrix profile index containing where each maximum occurs
     * @param lengthProfile      Length of the matrix profile
     * @param n                  Number of discords to extract
     * @param motifDistances     The distance of the best N discords
     * @param motifIndices       The indices of the best N discords
     * @param subsequenceIndices The indices of the query sequences that produced the "N" bigger discord.
     */
    private native static void findBestNDiscords(double[] profile, int[] index, long lengthProfile, long n,
                                                 double[] motifDistances, int[] motifIndices, int[] subsequenceIndices);

    /**
     * Stomp algorithm.
     *
     * @param ta Array of doubles with the first time series.
     * @param tb Array of doubles with the second time series.
     * @param m  Long with the subsequence length.
     * @return MatrixProfile object.
     */
    public static MatrixProfile stomp(double[] ta, double[] tb, long m) {
        int n = tb.length;
        double[] p = new double[n - (int) (m) + 1];
        int[] i = new int[n - (int) (m) + 1];

        stomp(ta, tb, (long) ta.length, (long) tb.length, m, p, i);

        return new MatrixProfile(p, i);
    }

    /**
     * Stomp Self Join algorithm.
     *
     * @param ta Array of doubles with the first time series.
     * @param m  Long with the subsequence length.
     * @return MatrixProfile object.
     */
    public static MatrixProfile stompSelfJoin(double[] ta, long m) {
        int n = ta.length;
        double[] p = new double[n - (int) (m) + 1];
        int[] i = new int[n - (int) (m) + 1];


        stompSelfJoin(ta, (long) ta.length, m, p, i);

        return new MatrixProfile(p, i);
    }

    /**
     * findBestNMotifs function.
     *
     * @param profile The matrix profile containing the minimum distance of each subsequence.
     * @param index   The matrix profile index
     * @param n       Number of motifs to extract.
     * @return Sequence object.
     */
    public static Sequence findBestNMotifs(double[] profile, int[] index, long n) {
        int nMotifs = (int) (n);

        double[] motifDistances = new double[nMotifs];
        int[] motifIndices = new int[nMotifs];
        int[] subsequenceIndices = new int[nMotifs];

        findBestNMotifs(profile, index, profile.length, n, motifDistances, motifIndices, subsequenceIndices);

        return new Sequence(motifDistances, motifIndices, subsequenceIndices);

    }

    /**
     * findBestNDiscords function.
     *
     * @param profile The matrix profile containing the minimum distance of each subsequence.
     * @param index   The matrix profile index
     * @param n       Number of discords to extract.
     * @return Sequence Object.
     */
    public static Sequence findBestNDiscords(double[] profile, int[] index, long n) {
        int nMotifs = (int) (n);

        double[] motifDistances = new double[nMotifs];
        int[] motifIndices = new int[nMotifs];
        int[] subsequenceIndices = new int[nMotifs];

        findBestNDiscords(profile, index, profile.length, n, motifDistances, motifIndices, subsequenceIndices);

        return new Sequence(motifDistances, motifIndices, subsequenceIndices);

    }


}
