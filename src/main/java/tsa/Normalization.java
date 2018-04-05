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

public class Normalization extends Library {

    private native static void znorm(double[] tssConcatenated, long tss_l, long tss_n, double epsilon, double[] result);

    /**
     * Calculates a new set of timeseries with zero mean and standard deviation one.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return Array with the same dimensions as tss where the time series have been adjusted for zero mean and
     * one as standard deviation.
     */
    public static double[] znorm(double[][] tss) {
        return znorm(tss, 0.00000001);
    }

    /**
     * Calculates a new set of timeseries with zero mean and standard deviation one.
     *
     * @param tss     Array of arrays of type double containing the input time series.
     * @param epsilon Minimum standard deviation to consider.  It acts a a gatekeeper for
     *                those time series that may be constant or near constant.
     * @return Array with the same dimensions as tss where the time series have been adjusted for zero mean and
     * one as standard deviation.
     */
    public static double[] znorm(double[][] tss, double epsilon) {
        long tss_l = tss[0].length;
        long tss_n = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tss_n * tss_l)];
        znorm(tssConcatenated, tss_l, tss_n, epsilon, result);
        return result;
    }
}
