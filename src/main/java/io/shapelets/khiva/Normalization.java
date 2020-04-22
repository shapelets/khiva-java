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
 * Khiva Normalization class containing several normalization methods.
 */
public class Normalization extends Library {

    private native static long znorm(long ref, double epsilon) throws Exception;

    private native static void znormInPlace(long ref, double epsilon) throws Exception;

    private native static long maxMinNorm(long ref, double high, double low, double epsilon) throws Exception;

    private native static void maxMinNormInPlace(long ref, double high, double low, double epsilon) throws Exception;

    private native static long decimalScalingNorm(long ref) throws Exception;

    private native static void decimalScalingNormInPlace(long ref) throws Exception;

    private native static long meanNorm(long ref) throws Exception;

    private native static void meanNormInPlace(long ref) throws Exception;


    /**
     * Normalizes the given time series according to its maximum value and adjusts each value within the range (-1, 1).
     *
     * @param arr Array containing the input time series.
     * @return Array with the same dimensions as ref, whose values (time series in dimension 0) have been normalized by
     * dividing each number by \(10^j\), where j is the number of integer digits of the max number in the time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array decimalScalingNorm(Array arr) throws Exception {
        long ref = decimalScalingNorm(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Same as decimalScalingNorm, but it performs the operation in place, without allocating further memory.
     *
     * @param arr Array containing the input time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static void decimalScalingNormInPlace(Array arr) throws Exception {
        decimalScalingNormInPlace(arr.getReference());
    }

    /**
     * Normalizes the given time series according to its minimum and maximum value and adjusts each value within the
     * range [low, high].
     *
     * @param arr Array containing the input time series.
     * @return Array with the same dimensions as ref, whose values (time series in dimension 0) have
     * been normalized by maximum and minimum values, and scaled as per high and low parameters.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array maxMinNorm(Array arr) throws Exception {
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array maxMinNorm(Array arr, double high) throws Exception {
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array maxMinNorm(Array arr, double high, double low) throws Exception {
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array maxMinNorm(Array arr, double high, double low, double epsilon) throws Exception {
        long ref = maxMinNorm(arr.getReference(), high, low, epsilon);
        return Array.fromNative(ref);
    }

    /**
     * Same as maxMinNorm, but it performs the operation in place, without allocating further memory.
     *
     * @param arr Array containing the input time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static void maxMinNormInPlace(Array arr) throws Exception {
        maxMinNormInPlace(arr, 1.0, 0.0, 0.00000001);
    }

    /**
     * Same as maxMinNorm, but it performs the operation in place, without allocating further memory.
     *
     * @param arr  Array containing the input time series.
     * @param high Maximum final value (Defaults to 1.0).
     * @throws java.lang.Exception If the native function call fails.
     */
    public static void maxMinNormInPlace(Array arr, double high) throws Exception {
        maxMinNormInPlace(arr, high, 0.0, 0.00000001);
    }

    /**
     * Same as maxMinNorm, but it performs the operation in place, without allocating further memory.
     *
     * @param arr  Array containing the input time series.
     * @param low  Minimum final value (Defaults to 0.0).
     * @param high Maximum final value (Defaults to 1.0).
     * @throws java.lang.Exception If the native function call fails.
     */
    public static void maxMinNormInPlace(Array arr, double high, double low) throws Exception {
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static void maxMinNormInPlace(Array arr, double high, double low, double epsilon) throws Exception {
        maxMinNormInPlace(arr.getReference(), high, low, epsilon);
    }

    /**
     * Normalizes the given time series according to its maximum-minimum value and its mean. It follows the following
     * formulae:
     * \[
     * \acute{x} = \frac{x - mean(x)}{max(x) - min(x)}.
     * \]
     *
     * @param arr Array containing the input time series.
     * @return An array with the same dimensions as tss, whose values (time series in dimension 0) have been
     * normalized by substracting the mean from each number and dividing each number by \(max(x) - min(x)\), in the
     * time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array meanNorm(Array arr) throws Exception {
        long ref = meanNorm(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Normalizes the given time series according to its maximum-minimum value and its mean. It follows the following
     * formulae:
     * \[
     * \acute{x} = \frac{x - mean(x)}{max(x) - min(x)}.
     * \]
     *
     * @param arr Array containing the input time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static void meanNormInPlace(Array arr) throws Exception {
        meanNormInPlace(arr.getReference());
    }

    /**
     * Calculates a new set of time series with zero mean and standard deviation one.
     *
     * @param arr Array containing the input time series.
     * @return Array with the same dimensions as arr where the time series have been adjusted for zero mean and
     * one as standard deviation.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array znorm(Array arr) throws Exception {
        return znorm(arr, 0.00000001);
    }

    /**
     * Calculates a new set of time series with zero mean and standard deviation one.
     *
     * @param arr     Array containing the input time series.
     * @param epsilon Minimum standard deviation to consider. It acts a a gatekeeper for
     *                those time series that may be constant or near constant.
     * @return Array with the same dimensions as arr where the time series have been adjusted for zero mean and
     * one as standard deviation.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array znorm(Array arr, double epsilon) throws Exception {
        long ref = znorm(arr.getReference(), epsilon);
        return Array.fromNative(ref);
    }

    /**
     * Adjusts the time series in the given input and performs z-norm
     * in place (without allocating further memory).
     *
     * @param arr Array containing the input time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static void znormInPlace(Array arr) throws Exception {
        znormInPlace(arr, 0.00000001);
    }

    /**
     * Adjusts the time series in the given input and performs z-norm
     * in place (without allocating further memory).
     *
     * @param arr     Array containing the input time series.
     * @param epsilon epsilon Minimum standard deviation to consider. It acts as a gatekeeper for
     *                those time series that may be constant or near constant.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static void znormInPlace(Array arr, double epsilon) throws Exception {
        znormInPlace(arr.getReference(), epsilon);
    }
}
