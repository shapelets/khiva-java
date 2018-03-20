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

    private native static void absoluteSumOfChanges(double[] timeSeries,
                                                    long timeSeriesLength, long numberOfTimeSeries, double[] jResult);

    private native static void absEnergy(double[] timeSeries,
                                         long timeSeriesLength, long numberOfTimeSeries, double[] jResult);

    private native static void cidCe(double[] tssConcatenated, long tssLength, long tssNumberOfTS, boolean zNormalize,
                                     double[] result);

    private native static void c3(double[] tssConcatenated, long tssLength, long tssNumberOfTS, long lag,
                                  double[] result);

    private native static void approximateEntropy(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                  int m, float r, float[] result);

    private native static void crossCovariance(double[] xssConcatenated, long xssLength, long xssNumberOfTS,
                                               double[] yssConcatenated, long yssLength, long yssNumberOfTS,
                                               boolean unbiased, double[] result);

    private native static void autoCovariance(double[] xssConcatenated, long xssLength, long xssNumberOfTS,
                                              boolean unbiased, double[] result);

    private native static void crossCorrelation(double[] xssConcatenated, long xssLength, long xssNumberOfTS,
                                                double[] yssConcatenated, long yssLength, long yssNumberOfTS,
                                                boolean unbiased, double[] result);


    /**
     * Calculates an estimate for the time series complexity defined by
     * Batista, Gustavo EAPA, et al (2014). (A more complex time series has more peaks,
     * valleys, etc.)
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
     * Calculates the value of an aggregation function f_agg (e.g. var or mean) of the autocorrelation
     * (Compare to http://en.wikipedia.org/wiki/Autocorrelation#Estimation), taken over different all possible
     * lags (1 to length of x)
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
     * Calculates the sum over the square values of the timeseries
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
     * Calculates the Schreiber, T. and Schmitz, A. (1997) measure of non-linearity
     * for the given time series
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

    /**
     * Calculates a vectorized Approximate entropy algorithm.
     * https://en.wikipedia.org/wiki/Approximate_entropy
     * For short time-series this method is highly dependent on the parameters, but should be stable for N > 2000,
     * see: Yentes et al. (2012) - The Appropriate Use of Approximate Entropy and Sample Entropy with Short Data Sets
     * Other shortcomings and alternatives discussed in:
     * Richman & Moorman (2000) - Physiological time-series analysis using approximate entropy and sample entropy
     *
     * @param tss Array of double arrays with the time series.
     * @param m   Length of compared run of data.
     * @param r   Filtering level, must be positive.
     * @return Float array with the vectorized approximate entropy for all the input time series in tss.
     */
    public static float[] approximateEntropy(double[][] tss, int m, float r) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        float[] result = new float[(int) (tssNumberOfTS)];
        approximateEntropy(tssConcatenated, tssLength, tssNumberOfTS, m, r, result);

        return result;
    }

    /**
     * Calculates the cross-covariance of the given time series.
     *
     * @param xss      Array of double arrays with the time series.
     * @param yss      Array of double arrays with the time series.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return Double array with the cross-covariance value for the given time series.
     */
    public static double[] crossCovariance(double[][] xss, double[][] yss, Boolean unbiased) {
        long xssLength = xss[0].length;
        long xssNumberOfTS = xss.length;
        double[] xssConcatenated = new double[0];
        for (double[] time_series : xss) {
            xssConcatenated = ArrayUtils.addAll(xssConcatenated, time_series);
        }
        long yssLength = yss[0].length;
        long yssNumberOfTS = yss.length;
        double[] yssConcatenated = new double[0];
        for (double[] time_series : yss) {
            yssConcatenated = ArrayUtils.addAll(yssConcatenated, time_series);
        }

        double[] result = new double[(int) (xssLength * yssLength)];
        crossCovariance(xssConcatenated, xssLength, xssNumberOfTS, yssConcatenated, yssLength, yssNumberOfTS, unbiased, result);

        return result;
    }

    /**
     * Calculates the auto-covariance the given time series.
     *
     * @param xss      Array of double arrays with the time series.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return Double array with the auto-covariance value for the given time series.
     */
    public static double[] autoCovariance(double[][] xss, Boolean unbiased) {
        long xssLength = xss[0].length;
        long xssNumberOfTS = xss.length;
        double[] xssConcatenated = new double[0];
        for (double[] time_series : xss) {
            xssConcatenated = ArrayUtils.addAll(xssConcatenated, time_series);
        }

        double[] result = new double[(int) (xssNumberOfTS * xssLength)];
        autoCovariance(xssConcatenated, xssLength, xssNumberOfTS, unbiased, result);

        return result;
    }

    /**
     * Calculates the cross-correlation of the given time series.
     *
     * @param xss      Array of double arrays with the time series.
     * @param yss      Array of double arrays with the time series.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return Double array with cross-correlation value for the given time series.
     */
    public static double[] crossCorrelation(double[][] xss, double[][] yss, Boolean unbiased) {
        long xssLength = xss[0].length;
        long xssNumberOfTS = xss.length;
        double[] xssConcatenated = new double[0];
        for (double[] time_series : xss) {
            xssConcatenated = ArrayUtils.addAll(xssConcatenated, time_series);
        }
        long yssLength = yss[0].length;
        long yssNumberOfTS = yss.length;
        double[] yssConcatenated = new double[0];
        for (double[] time_series : yss) {
            yssConcatenated = ArrayUtils.addAll(yssConcatenated, time_series);
        }

        double[] result = new double[(int) (Long.max(yssLength, xssLength))];
        crossCorrelation(xssConcatenated, xssLength, xssNumberOfTS, yssConcatenated, yssLength, yssNumberOfTS,
                unbiased, result);

        return result;
    }


}
