/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package tsa;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class Normalization extends Library {

    private native static long[] znorm(long ref, double epsilon);

    private native static long znormInPlace(long ref, double epsilon);

    /**
     * Calculates a new set of time series with zero mean and standard deviation one.
     *
     * @param arr Array containing the input time series.
     * @return Array with the same dimensions as tss where the time series have been adjusted for zero mean and
     * one as standard deviation.
     */
    public static Array znorm(Array arr) {
        return znorm(arr, 0.00000001);
    }

    /**
     * Calculates a new set of time series with zero mean and standard deviation one.
     *
     * @param arr     Array containing the input time series.
     * @param epsilon Minimum standard deviation to consider.  It acts a a gatekeeper for
     *                those time series that may be constant or near constant.
     * @return Array with the same dimensions as tss where the time series have been adjusted for zero mean and
     * one as standard deviation.
     */
    public static Array znorm(Array arr, double epsilon) {

        long[] refs = znorm(arr.getReference(), epsilon);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Adjusts the time series in the given input and performs z-norm
     * inplace (without allocating further memory).
     *
     * @param arr Array containing the input time series.
     */
    public static void znormInPlace(Array arr) {
        znormInPlace(arr, 0.00000001);
    }

    /**
     * Adjusts the time series in the given input and performs z-norm
     * inplace (without allocating further memory).
     *
     * @param arr     Array containing the input time series.
     * @param epsilon epsilon Minimum standard deviation to consider.  It acts as a gatekeeper for
     *                those time series that may be constant or near constant.
     */
    public static void znormInPlace(Array arr, double epsilon) {
        long ref = znormInPlace(arr.getReference(), epsilon);
        arr.setReference(ref);
    }
}
