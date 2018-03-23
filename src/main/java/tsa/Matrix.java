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

    private native static void stomp(double[] ta, double[] tb, long lta, long ltb, long m, double[] p, int[] i);

    private native static void stompSelfJoin(double[] ta, long lta, long m, double[] p, int[] i);

    private native static void findBestNMotifs(double[] profile, int[] index, long lengthProfile, long n,
                                               double[] motifDistances, int[] motifIndices, int[] subsequenceIndices);

    private native static void findBestNDiscords(double[] profile, int[] index, long lengthProfile, long n,
                                                 double[] motifDistances, int[] motifIndices, int[] subsequenceIndices);

    /**
     * STOMP algorithm to calculate the matrix profile between 'ta' and 'tb' using a subsequence length
     * of 'm'.
     *
     * @param ta Array of arrays of type double containing the input time series.
     * @param tb Array of arrays of type double containing the input time series.
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
     * STOMP algorithm to calculate the matrix profile between 't' and itself using a subsequence length
     * of 'm'. This method filters the trivial matches.
     *
     * @param ta Array of arrays of type double containing the input time series.
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
     * This function extracts the best N discords from a previously calculated matrix profile.
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
     * This function extracts the best N motifs from a previously calculated matrix profile.
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
