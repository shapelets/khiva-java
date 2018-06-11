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
 * Khiva Distances class containing distances methods.
 */
public class Distances extends Library {

    private native static long[] euclidean(long ref);

    private native static long[] dtw(long ref);

    private native static long[] hamming(long ref);

    private native static long[] manhattan(long ref);

    private native static long[] squaredEuclidean(long ref);


    /**
     * Calculates euclidean distances between time series.
     *
     * @param tss Array containing the input time series.
     * @return Array with an upper triangular matrix where each position corresponds to the distance between two
     * time series. Diagonal elements will be zero. For example: Position row 0 column 1 records the distance
     * between time series 0 and time series 1.
     */
    public static Array euclidean(Array tss) {
        long[] refs = euclidean(tss.getReference());
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the Dynamic Time Warping Distance.
     *
     * @param tss Expects an input array whose dimension zero is the length of the time series (all the same) and
     *            dimension one indicates the number of time series.
     * @return Array with an upper triangular matrix where each position corresponds to the distance between
     * two time series. Diagonal elements will be zero. For example: Position row 0 column 1 records the
     * distance between time series 0 and time series 1.
     */
    public static Array dtw(Array tss) {
        long[] refs = dtw(tss.getReference());
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates Hamming distances between time series.
     *
     * @param tss Expects an input array whose dimension zero is the length of the time series (all the same) and
     *            dimension one indicates the number of time series.
     * @return Array with an upper triangular matrix where each position corresponds to the
     * distance between two time series. Diagonal elements will be zero. For example: Position row 0 column 1 records the
     * distance between time series 0 and time series 1.
     */
    public static Array hamming(Array tss) {
        long[] refs = hamming(tss.getReference());
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates Manhattan distances between time series.
     *
     * @param tss Expects an input array whose dimension zero is the length of the time series (all the same) and
     *            dimension one indicates the number of time series.
     * @return Array with an upper triangular matrix where each position corresponds to the distance between two
     * time series. Diagonal elements will be zero. For example: Position row 0 column 1 records the distance between time
     * series 0 and time series 1.
     */
    public static Array manhattan(Array tss) {
        long[] refs = manhattan(tss.getReference());
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the non squared version of the euclidean distance.
     *
     * @param tss Array containing the input time series.
     * @return Array with an upper triangular matrix where each position corresponds to the distance between two
     * time series. Diagonal elements will be zero. For example: Position row 0 column 1 records the distance
     * between time series 0 and time series 1.
     */
    public static Array squaredEuclidean(Array tss) {
        long[] refs = squaredEuclidean(tss.getReference());
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }
}
