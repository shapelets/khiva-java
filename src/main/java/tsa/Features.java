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

public class Features extends Library {


    /**
     * absoluteSumOfChanges Library's native function.
     *
     * @param timeSeries         Time series concatenated in a single row.
     * @param timeSeriesLength   Length of each time series.
     * @param numberOfTimeSeries Number of time series into timeSeries.
     * @param jResult            Absolute sum of changes.
     */
    private native static void absoluteSumOfChanges(double[] timeSeries,
                                                    long timeSeriesLength, long numberOfTimeSeries, double[] jResult);

    /**
     * absEnergy Library's native function.
     *
     * @param timeSeries         Time series concatenated in a single row.
     * @param timeSeriesLength   Length of each time series.
     * @param numberOfTimeSeries Number of time series into timeSeries.
     * @param jResult            Absolute Energy
     */
    private native static void absEnergy(double[] timeSeries,
                                         long timeSeriesLength, long numberOfTimeSeries, double[] jResult);

    /**
     * cidCe Library's native function
     *
     * @param tssConcatenated Time series concatenated in a single row.
     * @param tssLength       Length of each time series ( All the time series need to have the same length).
     * @param tssNumberOfTS   Number of time series in tssConcatenated.
     * @param zNormalize      Controls whether the time series should be z-normalized or not.
     * @param result          The complexity value for the given time series.
     */
    private native static void cidCe(double[] tssConcatenated, long tssLength, long tssNumberOfTS, boolean zNormalize,
                                     double[] result);

    /**
     * c3 Library's native function.
     *
     * @param tssConcatenated Time series concatenated in a single row.
     * @param tssLength       Length of each time series (All the time series need to have the same length).
     * @param tssNumberOfTS   Number of time series in tssConcatenated.
     * @param lag             The lag.
     * @param result          The non-linearity value for the given time series.
     */
    private native static void c3(double[] tssConcatenated, long tssLength, long tssNumberOfTS, long lag, double[] result);

    /**
     * cidCe function.
     *
     * @param tss        Array of arrays with the time series.
     * @param zNormalize Controls whether the time series should be z-normalized or not.
     * @return The complexity value for the given time series.
     */
    public static double[] cidCe(double[][] tss, Boolean zNormalize) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        cidCe(tssConcatenated, tssLength, tssNumberOfTS, zNormalize, result);

        return result;
    }

    /**
     * absoluteSumOfChanges function.
     *
     * @param timeSeriesMatrix Array of double arrays representing each time series.
     * @return Double array with the absolute sum of changes.
     */
    public static double[] absoluteSumOfChanges(double[][] timeSeriesMatrix) {
        long timeSeriesLength = timeSeriesMatrix[0].length;
        long numberOfTimeSeries = timeSeriesMatrix.length;

        double[] concatenatedTimeSeries = new double[0];
        double[] result = new double[(int) (numberOfTimeSeries)];

        for (double[] time_series : timeSeriesMatrix) {
            concatenatedTimeSeries = ArrayUtils.addAll(concatenatedTimeSeries, time_series);
        }

        absoluteSumOfChanges(concatenatedTimeSeries, timeSeriesLength, numberOfTimeSeries,
                result);

        return result;
    }


    /**
     * absEnergy function.
     *
     * @param timeSeriesMatrix Array of double arrays representing each Time Series.
     * @return Double array with the Absolute Energy.
     */
    public static double[] absEnergy(double[][] timeSeriesMatrix) {
        long timeSeriesLength = timeSeriesMatrix[0].length;
        long numberOfTimeSeries = timeSeriesMatrix.length;

        double[] concatenatedTimeSeries = new double[0];
        double[] result = new double[(int) (numberOfTimeSeries)];

        for (double[] time_series : timeSeriesMatrix) {
            concatenatedTimeSeries = ArrayUtils.addAll(concatenatedTimeSeries, time_series);
        }

        absEnergy(concatenatedTimeSeries, timeSeriesLength, numberOfTimeSeries,
                result);

        return result;
    }

    /**
     * c3 function.
     *
     * @param tss Array of double arrays representing each time series.
     * @param lag The lag
     * @return The non-linearity value for the given time series.
     */
    public static double[] c3(double[][] tss, long lag) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        c3(tssConcatenated, tssLength, tssNumberOfTS, lag, result);

        return result;
    }

}
