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

    private native static long[] maxMinNorm(long ref, double high, double low, double epsilon);

    private native static long maxMinNormInPlace(long ref, double high, double low, double epsilon);

    private native static long[] decimalScalingNorm(long ref);

    private native static long decimalScalingNormInPlace(long ref);

    /**
     * Calculates a new set of time series with zero mean and standard deviation one.
     *
     * @param arr Array containing the input time series.
     * @return Array with the same dimensions as arr where the time series have been adjusted for zero mean and
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
     * @return Array with the same dimensions as arr where the time series have been adjusted for zero mean and
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

    /**
     * Normalizes the given time series according to its minimum and maximum value and adjusts each value within the
     * range [low, high].
     *
     * @param arr Array containing the input time series.
     * @return Array with the same dimensions as ref, whose values (time series in dimension 0) have
     * been normalized by maximum and minimum values, and scaled as per high and low parameters.
     */
    public static Array maxMinNorm(Array arr) {
        return maxMinNorm(arr, 1.0, 0.0, 0.00000001);
    }

    /**
     * Normalizes the given time series according to its minimum and maximum value and adjusts each value within the
     * range [low, high].
     *
     * @param arr  Array containing the input time series.
     * @param high Maximum final value (Defaults to 1.0).
     * @return Array with the same dimensions as ref, whose values (time series in dimension 0) have
     * been normalized by maximum and minimum values, and scaled as per high and low parameters.
     */
    public static Array maxMinNorm(Array arr, double high) {
        return maxMinNorm(arr, high, 0.0, 0.00000001);
    }

    /**
     * Normalizes the given time series according to its minimum and maximum value and adjusts each value within the
     * range [low, high].
     *
     * @param arr  Array containing the input time series.
     * @param high Maximum final value (Defaults to 1.0).
     * @param low  Minimum final value (Defaults to 0.0).
     * @return Array with the same dimensions as ref, whose values (time series in dimension 0) have
     * been normalized by maximum and minimum values, and scaled as per high and low parameters.
     */
    public static Array maxMinNorm(Array arr, double high, double low) {
        return maxMinNorm(arr, high, low, 0.00000001);
    }

    /**
     * Normalizes the given time series according to its minimum and maximum value and adjusts each value within the
     * range [low, high].
     *
     * @param arr     Array containing the input time series.
     * @param high    Maximum final value (Defaults to 1.0).
     * @param low     Minimum final value (Defaults to 0.0).
     * @param epsilon Minimum standard deviation to consider.  It acts a a gatekeeper for
     *                those time series that may be constant or near constant.
     * @return Array with the same dimensions as ref, whose values (time series in dimension 0) have
     * been normalized by maximum and minimum values, and scaled as per high and low parameters.
     */
    public static Array maxMinNorm(Array arr, double high, double low, double epsilon) {
        long[] refs = maxMinNorm(arr.getReference(), high, low, epsilon);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Same as maxMinNorm, but it performs the operation in place, without allocating further memory.
     *
     * @param arr Array containing the input time series.
     */
    public static void maxMinNormInPlace(Array arr) {
        maxMinNormInPlace(arr, 1.0, 0.0, 0.00000001);
    }

    /**
     * Same as maxMinNorm, but it performs the operation in place, without allocating further memory.
     *
     * @param arr  Array containing the input time series.
     * @param high Maximum final value (Defaults to 1.0).
     */
    public static void maxMinNormInPlace(Array arr, double high) {
        maxMinNormInPlace(arr, high, 0.0, 0.00000001);
    }

    /**
     * Same as maxMinNorm, but it performs the operation in place, without allocating further memory.
     *
     * @param arr  Array containing the input time series.
     * @param low  Minimum final value (Defaults to 0.0).
     * @param high Maximum final value (Defaults to 1.0).
     */
    public static void maxMinNormInPlace(Array arr, double high, double low) {
        maxMinNormInPlace(arr, high, low, 0.00000001);
    }

    /**
     * Same as maxMinNorm, but it performs the operation in place, without allocating further memory.
     *
     * @param arr     Array containing the input time series.
     * @param high    Maximum final value (Defaults to 1.0).
     * @param low     Minimum final value (Defaults to 0.0).
     * @param epsilon epsilon Minimum standard deviation to consider.  It acts as a gatekeeper for
     *                those time series that may be constant or near constant.
     */
    public static void maxMinNormInPlace(Array arr, double high, double low, double epsilon) {
        long ref = maxMinNormInPlace(arr.getReference(), high, low, epsilon);
        arr.setReference(ref);
    }

    /**
     * Normalizes the given time series according to its maximum value and adjusts each value within the range (-1, 1).
     *
     * @param arr Array containing the input time series.
     * @return Array with the same dimensions as ref, whose values (time series in dimension 0) have been normalized by
     * dividing each number by 10^j, where j is the number of integer digits of the max number in the time
     * series.
     */
    public static Array decimalScalingNorm(Array arr) {
        long[] refs = decimalScalingNorm(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Same as decimalScalingNorm, but it performs the operation in place, without allocating further memory.
     *
     * @param arr Array containing the input time series.
     */
    public static void decimalScalingNormInPlace(Array arr) {
        long ref = decimalScalingNormInPlace(arr.getReference());
        arr.setReference(ref);
    }
}
