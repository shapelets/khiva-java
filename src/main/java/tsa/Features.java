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

    private native static void absEnergy(double[] timeSeries,
                                         long timeSeriesLength, long numberOfTimeSeries, double[] jResult);

    private native static void absoluteSumOfChanges(double[] timeSeries,
                                                    long timeSeriesLength, long numberOfTimeSeries, double[] jResult);

    private native static void aggregatedAutocorrelation(double[] tssConcatenated, long tssL, long tssN,
                                                         int aggregationFunction, double[] result);

    private native static void aggregatedLinearTrend(double[] tssConcatenated, long tssL, long tssN, long chunkSize,
                                                     int aggregationFunction, double[] slope, double[] intercept,
                                                     double[] rvalue, double[] pvalue, double[] stderrest);

    private native static void approximateEntropy(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                  int m, float r, double[] result);

    private native static void crossCovariance(double[] xssConcatenated, long xssLength, long xssNumberOfTS,
                                               double[] yssConcatenated, long yssLength, long yssNumberOfTS,
                                               boolean unbiased, double[] result);

    private native static void autoCovariance(double[] xssConcatenated, long xssLength, long xssNumberOfTS,
                                              boolean unbiased, double[] result);

    private native static void crossCorrelation(double[] xssConcatenated, long xssLength, long xssNumberOfTS,
                                                double[] yssConcatenated, long yssLength, long yssNumberOfTS,
                                                boolean unbiased, double[] result);

    private native static void autoCorrelation(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                               long maxLag, boolean unbiased, double[] result);

    private native static void binnedEntropy(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                             long max_bins, double[] result);

    private native static void c3(double[] tssConcatenated, long tssLength, long tssNumberOfTS, long lag,
                                  double[] result);

    private native static void cidCe(double[] tssConcatenated, long tssLength, long tssNumberOfTS, boolean zNormalize,
                                     double[] result);

    private native static void countAboveMean(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                              int[] result);

    private native static void countBelowMean(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                              int[] result);

    private native static void cwtCoefficients(double[] tssConcatenated, long tssL, long tssN,
                                               int[] widthsConcatenated, long widths_l, long widths_n,
                                               int coeff, int w, double[] result);

    private native static void energyRatioByChunks(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                   long numSegments, long segmentFocus, double[] result);

    private native static void fftAggregated(double[] tssConcatenated, long tssL, long tssN, double[] result);

    private native static void fftCoefficient(double[] tssConcatenated, long tssL, long tssN, long coefficient,
                                              double[] real, double[] imag, double[] abs, double[] angle);

    private native static void firstLocationOfMaximum(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                      double[] result);

    private native static void firstLocationOfMinimum(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                      double[] result);

    private native static void hasDuplicates(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                             boolean[] result);

    private native static void hasDuplicateMax(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                               boolean[] result);

    private native static void hasDuplicateMin(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                               boolean[] result);

    private native static void indexMaxQuantile(double[] tssConcatenated, long tssLength, long tssNumberOfTS, float q,
                                                double[] result);

    private native static void kurtosis(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                        double[] result);

    private native static void largeStandardDeviation(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                      float r, boolean[] result);

    private native static void lastLocationOfMaximum(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                     double[] result);

    private native static void lastLocationOfMinimum(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                     double[] result);

    private native static void length(double[] tssConcatenated, long tssLength, long tssNumberOfTS, int[] result);

    private native static void linearTrend(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                           double[] pvalue, double[] rvalue, double[] intercept, double[] slope,
                                           double[] stdrr);

    private native static void longestStrikeAboveMean(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                      double[] result);

    private native static void longestStrikeBelowMean(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                      double[] result);

    private native static void maxLangevinFixedPoint(double[] tssConcatenated, long tssL, long tssN, int m, float r,
                                                     double[] result);

    private native static void maximum(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                       double[] result);

    private native static void mean(double[] tssConcatenated, long tssL, long tssN, double[] result);

    private native static void meanAbsoluteChange(double[] tssConcatenated, long tssLength, long tssNumberOfTS,
                                                  double[] result);

    private native static void meanChange(double[] tssConcatenated, long tssL, long tssN, double[] result);

    private native static void meanSecondDerivativeCentral(double[] tssConcatenated, long tss_l,
                                                           long tssN, double[] result);

    private native static void median(double[] tssConcatenated, long tssL, long tssN, double[] result);

    private native static void minimum(double[] tssConcatenated, long tssL, long tssN, double[] result);

    private native static void numberCrossingM(double[] tssConcatenated, long tssL, long tssN, int m,
                                               double[] result);

    private native static void numberPeaks(double[] tssConcatenated, long tssL, long tssN, int n, double[] result);

    private native static void percentageOfReoccurringDatapointsToAllDatapoints(double[] tssConcatenated,
                                                                                long tssL, long tssN, boolean isSorted,
                                                                                double[] result);

    private native static void quantile(double[] tssConcatenated, long tssL, long tssN, double[] qConcatenated, long qL,
                                        float precision, double[] result);

    private native static void ratioBeyondRSigma(double[] tssConcatenated, long tssL, long tssN, float r,
                                                 double[] result);

    private native static void sampleEntropy(double[] tssConcatenated, long tss_l, long tss_n, double[] result);

    private native static void skewness(double[] tssConcatenated, long tss_l, long tss_n, double[] result);

    private native static void standardDeviation(double[] tssConcatenated, long tss_l, long tss_n, double[] result);

    private native static void sumOfReoccurringDatapoints(double[] tssConcatenated, long tss_l, long tss_n,
                                                          boolean isSorted, double[] result);

    private native static void symmetryLooking(double[] tssConcatenated, long tss_l, long tss_n, float r,
                                               boolean[] result);

    private native static void valueCount(double[] tssConcatenated, long tss_l, long tss_n, float v, int[] result);


    /**
     * Calculates the sum over the square values of the time series
     *
     * @param timeSeriesMatrix Array of arrays of type double containing the input time series.
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
     * Calculates the value of an aggregation function f_agg (e.g. var or mean) of the autocorrelation
     * (Compare to http://en.wikipedia.org/wiki/Autocorrelation#Estimation), taken over different all possible
     * lags (1 to length of x)
     *
     * @param timeSeriesMatrix Array of arrays of type double containing the input time series.
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
     * Calculates a linear least-squares regression for values of the time series that were aggregated
     * over chunks versus the sequence from 0 up to the number of chunks minus one.
     *
     * @param tss                 Array of arrays of type double containing the input time series.
     * @param aggregationFunction Function to be used in the aggregation. It receives an integer which indicates the
     *                            function to be applied:
     *                            {
     *                            0 : mean,
     *                            1 : median
     *                            2 : min,
     *                            3 : max,
     *                            4 : stdev,
     *                            5 : var,
     *                            default : mean
     *                            }
     * @return Array whose values contains the aggregated correlation for each time series.
     */
    public static double[] aggregatedAutocorrelation(double[][] tss, int aggregationFunction) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        aggregatedAutocorrelation(tssConcatenated, tssL, tssN, aggregationFunction, result);
        return result;
    }

    /**
     * Calculates a linear least-squares regression for values of the time series that were aggregated
     * over chunks versus the sequence from 0 up to the number of chunks minus one.
     *
     * @param tss                 Array of arrays of type double containing the input time series.
     * @param chunkSize           The chunk size used to aggregate the data.
     * @param aggregationFunction Function to be used in the aggregation. It receives an integer which indicates the
     *                            function to be applied:
     *                            {
     *                            0 : mean,
     *                            1 : median
     *                            2 : min,
     *                            3 : max,
     *                            4 : stdev,
     *                            default : mean
     *                            }
     * @param slope               The slope for all time series.
     * @param intercept           The intercept values for all time series.
     * @param rvalue              The rvalues for all time series.
     * @param pvalue              The pvalues for all time series.
     * @param stderrest           The stderr values for all time series.
     */
    public static void aggregatedLinearTrend(double[][] tss, long chunkSize, int aggregationFunction,
                                             double[] slope, double[] intercept, double[] rvalue,
                                             double[] pvalue, double[] stderrest) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        aggregatedLinearTrend(tssConcatenated, tssL, tssN, chunkSize, aggregationFunction, slope,
                intercept, rvalue, pvalue, stderrest);

    }

    /**
     * Calculates a vectorized Approximate entropy algorithm.
     * https://en.wikipedia.org/wiki/Approximate_entropy
     * For short time-series this method is highly dependent on the parameters, but should be stable for N greater than 2000,
     * see: Yentes et al. (2012) - The Appropriate Use of Approximate Entropy and Sample Entropy with Short Data Sets
     * Other shortcomings and alternatives discussed in:
     * Richman and Moorman (2000) - Physiological time-series analysis using approximate entropy and sample entropy
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @param m   Length of compared run of data.
     * @param r   Filtering level, must be positive.
     * @return Float array with the vectorized approximate entropy for all the input time series in tss.
     */

    public static double[] approximateEntropy(double[][] tss, int m, float r) {

        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }


        double[] result = new double[(int) (tssNumberOfTS)];

        approximateEntropy(tssConcatenated, tssLength, tssNumberOfTS, m, r, result);

        return result;
    }

    /**
     * Calculates the cross-covariance of the given time series.
     *
     * @param xss      Array of arrays of type double containing the input time series.
     * @param yss      Array of arrays of type double containing the input time series.
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
     * @param xss      Array of arrays of type double containing the input time series.
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
     * @param xss      Array of arrays of type double containing the input time series.
     * @param yss      Array of arrays of type double containing the input time series.
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

    /**
     * Calculates the autocorrelation of the specified lag for the given time series.
     *
     * @param tss      Array of arrays of type double containing the input time series.
     * @param maxLag   The maximum lag to compute.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return The autocorrelation value for the given time series
     */
    public static double[] autoCorrelation(double[][] tss, long maxLag, Boolean unbiased) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS * tssLength)];
        autoCorrelation(tssConcatenated, tssLength, tssNumberOfTS, maxLag, unbiased, result);

        return result;
    }

    /**
     * Calculates the binned entropy for the given time series and number of bins.
     *
     * @param tss      Array of arrays of type double containing the input time series.
     * @param max_bins The number of bins
     * @return The binned entropy value for the given time series.
     */
    public static double[] binnedEntropy(double[][] tss, int max_bins) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        binnedEntropy(tssConcatenated, tssLength, tssNumberOfTS, max_bins, result);

        return result;
    }

    /**
     * Calculates the Schreiber, T. and Schmitz, A. (1997) measure of non-linearity
     * for the given time series
     *
     * @param tss Array of arrays of type double containing the input time series.
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
     * Calculates an estimate for the time series complexity defined by
     * Batista, Gustavo EAPA, et al (2014). (A more complex time series has more peaks,
     * valleys, etc.)
     *
     * @param tss        Array of arrays of type double containing the input time series.
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
     * Calculates the number of values in the time series that are higher than
     * the mean
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The number of values in the time series that are higher than the mean.
     */
    public static int[] countAboveMean(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        int[] result = new int[(int) (tssNumberOfTS)];
        countAboveMean(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates the number of values in the time series that are lower than
     * the mean
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The number of values in the time series that are lower than the mean.
     */
    public static int[] countBelowMean(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        int[] result = new int[(int) (tssNumberOfTS)];
        countBelowMean(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates a Continuous wavelet transform for the Ricker wavelet, also known as
     * the "Mexican hat wavelet".
     *
     * @param tss    Array of arrays of type double containing the input time series.
     * @param widths Widths. It accepts a list of lists or a numpy array with one or several widths.
     * @param coeff  Coefficient of interest.
     * @param w      Width of interest.
     * @return Result of calculated coefficients.
     */
    public static double[] cwtCoefficients(double[][] tss, int[][] widths, int coeff, int w) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }
        long widthsL = widths[0].length;
        long widthsN = widths.length;
        int[] widthsConcatenated = new int[0];
        for (int[] width : widths) {
            widthsConcatenated = ArrayUtils.addAll(widthsConcatenated, width);
        }

        double[] result = new double[(int) (tssN)];
        cwtCoefficients(tssConcatenated, tssL, tssN, widthsConcatenated, widthsL, widthsN, coeff, w, result);
        return result;
    }

    /**
     * Calculates the sum of squares of chunk i out of N chunks expressed as a ratio
     * with the sum of squares over the whole series. segmentFocus should be lower
     * than the number of segments
     *
     * @param tss          Array of arrays of type double containing the input time series.
     * @param numSegments  The number of segments to divide the series into.
     * @param segmentFocus The segment number (starting at zero) to return a feature on.
     * @return The energy ratio by chunk of the time series.
     */
    public static double[] energyRatioByChunks(double[][] tss, long numSegments, long segmentFocus) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        energyRatioByChunks(tssConcatenated, tssLength, tssNumberOfTS, numSegments, segmentFocus, result);

        return result;
    }

    /**
     * Calculates the spectral centroid(mean), variance, skew, and kurtosis of the absolute fourier transform
     * spectrum.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The spectral centroid (mean), variance, skew, and kurtosis of the absolute fourier transform
     * spectrum.
     */
    public static double[] fftAggregated(double[][] tss) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN * tssL)];
        fftAggregated(tssConcatenated, tssL, tssN, result);
        return result;
    }

    /**
     * Calculates the fourier coefficients of the one-dimensional discrete
     * Fourier Transform for real input by fast fourier transformation algorithm.
     *
     * @param tss         Array of arrays of type double containing the input time series.
     * @param coefficient The coefficient to extract from the FFT.
     * @param real        The real part of the coefficient.
     * @param imag        The imaginary part of the coefficient.
     * @param abs         The absolute value of the coefficient.
     * @param angle       The angle of the coefficient.
     */
    public static void fftCoefficient(double[][] tss, long coefficient, double[] real, double[] imag, double[] abs, double[] angle) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        fftCoefficient(tssConcatenated, tssL, tssN, coefficient, real, imag, abs, angle);
    }

    /**
     * Calculates the first relative location of the maximal value for each time series.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The first relative location of the maximum value to the length of the times eries,
     * for each time series.
     */
    public static double[] firstLocationOfMaximum(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        firstLocationOfMaximum(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates the first location of the minimal value of each time series. The position
     * is calculated relatively to the length of the series.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The first relative location of the minimal value of each series.
     */
    public static double[] firstLocationOfMinimum(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        firstLocationOfMinimum(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates if the input time series contain duplicated elements.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return Array containing True if the time series contains duplicated elements
     * and false otherwise.
     */
    public static boolean[] hasDuplicates(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        boolean[] result = new boolean[(int) (tssNumberOfTS)];
        hasDuplicates(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates if the maximum within input time series is duplicated.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return Calculates if the maximum within input time series is duplicated.
     */
    public static boolean[] hasDuplicateMax(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        boolean[] result = new boolean[(int) (tssNumberOfTS)];
        hasDuplicateMax(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates if the minimum of the input time series is duplicated.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return Array containing True if the minimum of the time series is duplicated
     * and False otherwise.
     */
    public static boolean[] hasDuplicateMin(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        boolean[] result = new boolean[(int) (tssNumberOfTS)];
        hasDuplicateMin(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates the index of the max quantile.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @param q   The quantile.
     * @return The index of the max quantile q.
     */
    public static double[] indexMaxQuantile(double[][] tss, float q) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        indexMaxQuantile(tssConcatenated, tssLength, tssNumberOfTS, q, result);

        return result;
    }

    /**
     * Returns the kurtosis of tss (calculated with the adjusted Fisher-Pearson
     * standardized moment coefficient G2).
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The kurtosis of each tss.
     */
    public static double[] kurtosis(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        kurtosis(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Checks if the time series within tss have a large standard deviation.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @param r   The threshold.
     * @return Array containing True for those time series in tss that have a large standard deviation.
     */
    public static boolean[] largeStandardDeviation(double[][] tss, float r) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        boolean[] result = new boolean[(int) (tssNumberOfTS)];
        largeStandardDeviation(tssConcatenated, tssLength, tssNumberOfTS, r, result);

        return result;
    }

    /**
     * Calculates the last location of the maximum value of each time series. The position
     * is calculated relatively to the length of the series.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The last relative location of the maximum value of each series.
     */
    public static double[] lastLocationOfMaximum(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        lastLocationOfMaximum(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates the last location of the minimum value of each time series. The position
     * is calculated relatively to the length of the series.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The last relative location of the minimum value of each series.
     */
    public static double[] lastLocationOfMinimum(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        lastLocationOfMinimum(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Returns the length of the input time series.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The length of tss.
     */
    public static int[] length(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        int[] result = new int[(int) (tssNumberOfTS)];
        length(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculate a linear least-squares regression for the values of the time series versus the sequence from 0 to
     * length of the time series minus one.
     *
     * @param tss       Array of arrays of type double containing the input time series.
     * @param pvalue    The pvalues for all time series.
     * @param rvalue    The rvalues for all time series.
     * @param intercept The intercept values for all time series.
     * @param slope     The slope for all time series.
     * @param stdrr     The stdrr values for all time series.
     */
    public static void linearTrend(double[][] tss, double[] pvalue, double[] rvalue, double[] intercept,
                                   double[] slope, double[] stdrr) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];

        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }
        linearTrend(tssConcatenated, tssLength, tssNumberOfTS, pvalue, rvalue, intercept, slope, stdrr);
    }

    /**
     * Calculates the length of the longest consecutive subsequence in tss that is bigger than the mean of tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The length of the longest consecutive subsequence in the input time series that is bigger than the
     * mean.
     */
    public static double[] longestStrikeAboveMean(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        longestStrikeAboveMean(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates the length of the longest consecutive subsequence in tss that is below the mean of tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The length of the longest consecutive subsequence in the input time series that is below the mean.
     */
    public static double[] longestStrikeBelowMean(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        longestStrikeBelowMean(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Largest fixed point of dynamics \(\max_x {h(x)=0}\) estimated from polynomial
     * \(h(x)\), which has been fitted to the deterministic dynamics of Langevin model
     * \[
     * \dot(x)(t) = h(x(t)) + R \mathcal(N)(0,1)
     * \]
     * as described by
     * Friedrich et al. (2000): Physics Letters A 271, p. 217-222 *Extracting model equations from experimental data.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @param m   Order of polynom to fit for estimating fixed points of dynamics.
     * @param r   Number of quantiles to use for averaging.
     * @return Largest fixed point of deterministic dynamics.
     */
    public static double[] maxLangevinFixedPoint(double[][] tss, int m, float r) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        maxLangevinFixedPoint(tssConcatenated, tssL, tssN, m, r, result);
        return result;
    }

    /**
     * Calculates the maximum value for each time series within tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The maximum value of each time series within tss.
     */
    public static double[] maximum(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        maximum(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates the mean value for each time series within tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The mean value of each time series within tss.
     */
    public static double[] mean(double[][] tss) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        mean(tssConcatenated, tssL, tssN, result);
        return result;
    }

    /**
     * Calculates the mean over the absolute differences between subsequent time series values in tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The mean over the absolute differences between subsequent time series values.
     */
    public static double[] meanAbsoluteChange(double[][] tss) {
        long tssLength = tss[0].length;
        long tssNumberOfTS = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssNumberOfTS)];
        meanAbsoluteChange(tssConcatenated, tssLength, tssNumberOfTS, result);

        return result;
    }

    /**
     * Calculates the mean over the differences between subsequent time series values in tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The mean over the differences between subsequent time series values.
     */
    public static double[] meanChange(double[][] tss) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        meanChange(tssConcatenated, tssL, tssN, result);
        return result;
    }

    /**
     * Calculates mean value of a central approximation of the second derivative for each time series in tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The mean value of a central approximation of the second derivative for each time series.
     */
    public static double[] meanSecondDerivativeCentral(double[][] tss) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        meanSecondDerivativeCentral(tssConcatenated, tssL, tssN, result);
        return result;
    }

    /**
     * Calculates the median value for each time series within tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The median value of each time series within tss.
     */
    public static double[] median(double[][] tss) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        median(tssConcatenated, tssL, tssN, result);
        return result;
    }

    /**
     * Calculates the minimum value for each time series within tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The minimum value of each time series within tss.
     */
    public static double[] minimum(double[][] tss) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        minimum(tssConcatenated, tssL, tssN, result);
        return result;
    }

    /**
     * Calculates the number of m-crossings. A m-crossing is defined as two sequential values where the first
     * value is lower than m and the next is greater, or viceversa. If you set m to zero, you will get the number of
     * zero crossings.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @param m   The m value.
     * @return The number of m-crossings of each time series within tss.
     */
    public static double[] numberCrossingM(double[][] tss, int m) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        numberCrossingM(tssConcatenated, tssL, tssN, m, result);
        return result;
    }

    /**
     * Calculates the number of peaks of at least support \(n\) in the time series \(tss\). A peak of support
     * \(n\) is defined as a subsequence of \(tss\) where a value occurs, which is bigger than
     * its \(n\) neighbours to the left and to the right.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @param n   The support of the peak.
     * @return The number of peaks of at least support \(n\).
     */
    public static double[] numberPeaks(double[][] tss, int n) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        numberPeaks(tssConcatenated, tssL, tssN, n, result);
        return result;
    }

    /**
     * Calculates the percentage of unique values, that are present in the time series more than once.
     * \[
     * len(different values occurring more than once) / len(different values)
     * \]
     * This means the percentage is normalized to the number of unique values, in contrast to the
     * percentageOfReoccurringValuesToAllValues.
     *
     * @param tss      Array of arrays of type double containing the input time series.
     * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
     * @return Returns the percentage of unique values, that are present in the time series more than once.
     */
    public static double[] percentageOfReoccurringDatapointsToAllDatapoints(double[][] tss, boolean isSorted) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        percentageOfReoccurringDatapointsToAllDatapoints(tssConcatenated, tssL, tssN, isSorted, result);
        return result;
    }

    /**
     * Returns values at the given quantile.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @param q   Percentile(s) at which to extract score(s). One or many.
     * @return Values at the given quantile.
     */
    public static double[] quantile(double[][] tss, double[] q) {
        return quantile(tss, q, (float) 1e8);
    }

    /**
     * Returns values at the given quantile.
     *
     * @param tss       Array of arrays of type double containing the input time series.
     * @param q         Percentile(s) at which to extract score(s). One or many.
     * @param precision Number of decimals expected.
     * @return Values at the given quantile.
     */
    public static double[] quantile(double[][] tss, double[] q, float precision) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }
        long q_l = q.length;

        double[] result = new double[(int) (tssN)];
        quantile(tssConcatenated, tssL, tssN, q, q_l, precision, result);
        return result;
    }

    /**
     * Calculates the ratio of values that are more than  \(r*std(x)\] (so \(r\) sigma) away from the mean of
     * \(x\).
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @param r   Number of times that the values should be away from.
     * @return The ratio of values that are more than \(r*std(x)\) (so \(r\) sigma) away from the mean of
     * \(x\).
     */
    public static double[] ratioBeyondRSigma(double[][] tss, float r) {
        long tssL = tss[0].length;
        long tssN = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tssN)];
        ratioBeyondRSigma(tssConcatenated, tssL, tssN, r, result);
        return result;
    }

    /**
     * Calculates a vectorized sample entropy algorithm.
     * https://en.wikipedia.org/wiki/Sample_entropy
     * https://www.ncbi.nlm.nih.gov/pubmed/10843903?dopt=Abstract
     * For short time-series this method is highly dependent on the parameters, but should be stable for N > 2000,
     * see: Yentes et al. (2012) - The Appropriate Use of Approximate Entropy and Sample Entropy with Short Data Sets
     * Other shortcomings and alternatives discussed in:
     * Richman & Moorman (2000) - Physiological time-series analysis using approximate entropy and sample entropy.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return An array with the same dimensions as tss, whose values (time series in dimension 0)
     * contains the vectorized sample entropy for all the input time series in tss.
     */
    public static double[] sampleEntropy(double[][] tss) {
        long tss_l = tss[0].length;
        long tss_n = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tss_n)];
        sampleEntropy(tssConcatenated, tss_l, tss_n, result);
        return result;
    }

    /**
     * Calculates the sample skewness of tss (calculated with the adjusted Fisher-Pearson standardized
     * moment coefficient G1).
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return Array containing the skewness of each time series in tss.
     */
    public static double[] skewness(double[][] tss) {
        long tss_l = tss[0].length;
        long tss_n = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tss_n)];
        skewness(tssConcatenated, tss_l, tss_n, result);
        return result;
    }

    /**
     * Calculates the standard deviation of each time series within tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return The standard deviation of each time series within tss.
     */
    public static double[] standardDeviation(double[][] tss) {
        long tss_l = tss[0].length;
        long tss_n = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tss_n)];
        standardDeviation(tssConcatenated, tss_l, tss_n, result);
        return result;
    }

    /**
     * Calculates the sum of all data points, that are present in the time series more than once.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @return Returns the sum of all data points, that are present in the time series more than once.
     */
    public static double[] sumOfReoccurringDatapoints(double[][] tss) {
        return sumOfReoccurringDatapoints(tss, false);
    }

    /**
     * Calculates the sum of all data points, that are present in the time series more than once.
     *
     * @param tss      Array of arrays of type double containing the input time series.
     * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
     * @return Returns the sum of all data points, that are present in the time series more than once.
     */
    public static double[] sumOfReoccurringDatapoints(double[][] tss, boolean isSorted) {
        long tss_l = tss[0].length;
        long tss_n = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        double[] result = new double[(int) (tss_n)];
        sumOfReoccurringDatapoints(tssConcatenated, tss_l, tss_n, isSorted, result);
        return result;
    }

    /**
     * Calculates if the distribution of tss *looks symmetric*. This is the case if
     * \[
     * | mean(tss)-median(tss)| < r * (max(tss)-min(tss))
     * \]
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @param r   The percentage of the range to compare with.
     * @return An array denoting if the input time series look symmetric.
     */
    public static boolean[] symmetryLooking(double[][] tss, float r) {
        long tss_l = tss[0].length;
        long tss_n = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        boolean[] result = new boolean[(int) (tss_n)];
        symmetryLooking(tssConcatenated, tss_l, tss_n, r, result);
        return result;
    }

    /**
     * Counts occurrences of value in the time series tss.
     *
     * @param tss Array of arrays of type double containing the input time series.
     * @param v   The value to be counted.
     * @return An array containing the count of the given value in each time series.
     */
    public static int[] valueCount(double[][] tss, float v) {
        long tss_l = tss[0].length;
        long tss_n = tss.length;
        double[] tssConcatenated = new double[0];
        for (double[] time_series : tss) {
            tssConcatenated = ArrayUtils.addAll(tssConcatenated, time_series);
        }

        int[] result = new int[(int) (tss_n)];
        valueCount(tssConcatenated, tss_l, tss_n, v, result);
        return result;
    }
}
