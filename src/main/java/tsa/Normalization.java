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

    private native static void znorm(double[] tssConcatenated, long tssL, long tssN, double epsilon, double[] result);

    private native static void znormInPlace(double[] tssConcatenated, long tssL, long tssN, double epsilon);

    /**
     * Calculates a new set of time series with zero mean and standard deviation one.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return Array with the same dimensions as tss where the time series have been adjusted for zero mean and
     * one as standard deviation.
     */
    public static double[] znorm(double[][] tss) {
        return znorm(tss, 0.00000001);
    }

    /**
     * Calculates a new set of time series with zero mean and standard deviation one.
     *
     * @param tss     Array of arrays of type double containing the input time series.
     * @param epsilon Minimum standard deviation to consider.  It acts a a gatekeeper for
     *                those time series that may be constant or near constant.
     * @return Array with the same dimensions as tss where the time series have been adjusted for zero mean and
     * one as standard deviation.
     */
    public static double[] znorm(double[][] tss, double epsilon) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN * tssL)];
        znorm(tssConcatenated, tssL, tssN, epsilon, result);
        return result;
    }

    /**
     * Adjusts the time series in the given input and performs z-norm
     * inplace (without allocating further memory).
     *
     * @param tss Array of arrays of type double containing the input time series.
     */
    public static void znormInPlace(double[][] tss) {
        znormInPlace(tss, 0.00000001);
    }

    /**
     * Adjusts the time series in the given input and performs z-norm
     * inplace (without allocating further memory).
     *
     * @param tss     Array of arrays of type double containing the input time series.
     * @param epsilon epsilon Minimum standard deviation to consider.  It acts as a gatekeeper for
     *                those time series that may be constant or near constant.
     */
    public static void znormInPlace(double[][] tss, double epsilon) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }
        znormInPlace(tssConcatenated, tssL, tssN, epsilon);

        for (int i = 0; i < tssN; i++) {
            tss[i] = Arrays.copyOfRange(tssConcatenated, (int) (i * tssL), (int) ((i + 1) * tssL));
        }
    }
}
