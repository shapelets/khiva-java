
/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package tsa;

import java.util.Arrays;

import static java.lang.Math.toIntExact;

/**
 *TSA class for exposing the C++ TSA Library
 */
public class TSA {

    private static TSA instance = null;


    private TSA() {
        System.loadLibrary("TSALIB");
    }

    /**
     *getInstance function for using this class as a Singleton. Following the singleton Design Pattern.
     * @return TSA instance.
     */
    public static TSA getInstance() {
        if (instance == null) {
            instance = new TSA();
        }
        return instance;
    }

    /**
     * Stomp TSA's native function exposed by using JNI.
     * @param ta Array of doubles with the first time series.
     * @param tb Array of doubles with the second time series.
     * @param lta Integer with the ta length.
     * @param ltb Integer with the tb length.
     * @param m Long with the subsequence length.
     * @param p Array of doubles for storing the distances.
     * @param i Array of integers for storing the indexes.
     */
    private native void stomp(double[] ta, double[] tb, int lta, int ltb, long m, double[] p, int[] i);

    /**
     * StompSelfJoin TSA's native function exposed by using JNI.
     * @param ta Array of doubles with the time series.
     * @param lta Integer with the ta length.
     * @param m Long with the subsequence length.
     * @param p Array of doubles for storing the distances.
     * @param i Array of integers for storing the indexes.
     */
    private native void stompSelfJoin(double[] ta, int lta, long m, double[] p, int[] i);

    /**
     * Stomp algorithm.
     * @param ta Array of doubles with the first time series.
     * @param tb Array of doubles with the second time series.
     * @param m Long with the subsequence length.
     * @return MatrixProfile object.
     */
    public MatrixProfile stomp(double[] ta, double[] tb, long m) {
        int n = tb.length;
        double[] p = new double[n - toIntExact(m) + 1];
        int[] i = new int[n - toIntExact(m) + 1];


        Arrays.fill(p, 0);
        Arrays.fill(i, 0);


        stomp(ta, tb, ta.length, tb.length, m, p, i);

        return new MatrixProfile(p, i);
    }

    /**
     * Stomp Self Join algorithm.
     * @param ta Array of doubles with the first time series.
     * @param m Long with the subsequence length.
     * @return MatrixProfile object.
     */
    public MatrixProfile stompSelfJoin(double[] ta, long m) {
        int n = ta.length;
        double[] p = new double[n - toIntExact(m) + 1];
        int[] i = new int[n - toIntExact(m) + 1];

        Arrays.fill(p, 0);
        Arrays.fill(i, 0);

        stompSelfJoin(ta, ta.length, m, p, i);

        return new MatrixProfile(p, i);
    }
}