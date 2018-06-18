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
 * Khiva Statistics class containing statistics methods.
 */
public class Statistics extends Library {

    private native static long[] covariance(long ref, boolean unbiased);

    private native static long[] moment(long ref, int k);

    private native static long[] sampleStdev(long ref);

    private native static long[] kurtosis(long ref);

    private native static long[] ljungBox(long ref, long lags);

    private native static long[] skewness(long ref);

    private native static long[] quantile(long ref, long refQ, float precision);

    private native static long[] quantilesCut(long ref, float quantiles, float precision);

    /**
     * Returns the covariance matrix of the time series contained in tss.
     *
     * @param tss Array containing the input time series.
     * @return The covariance matrix of the time series.
     */
    public static Array covariance(Array tss) {
        return covariance(tss, true);
    }

    /**
     * Returns the covariance matrix of the time series contained in tss.
     *
     * @param tss      Array containing the input time series.
     * @param unbiased Determines whether it divides by n - 1 (if false) or n (if true).
     * @return The covariance matrix of the time series.
     */
    public static Array covariance(Array tss, boolean unbiased) {
        long[] refs = covariance(tss.getReference(), unbiased);
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Returns the kurtosis of tss (calculated with the adjusted Fisher-Pearson standardized moment coefficient G2).
     *
     * @param tss Array containing the input time series.
     * @return The kurtosis of tss.
     */
    public static Array kurtosis(Array tss) {
        long[] refs = kurtosis(tss.getReference());
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * The Ljung-Box test checks that data whithin the time series are independently distributed (i.e. the correlations in
     * the population from which the sample is taken are 0, so that any observed correlations in the data result from
     * randomness of the sampling process). Data are no independently distributed, if they exhibit serial correlation.
     * <p>
     * The test statistic is:
     * <p>
     * \[
     * Q = n\left(n+2\right)\sum_{k=1}^h\frac{\hat{\rho}^2_k}{n-k}
     * \]
     * <p>
     * where ''n'' is the sample size, \(\hat{\rho}k \) is the sample autocorrelation at lag ''k'', and ''h'' is the
     * number of lags being tested. Under \( H_0 \) the statistic Q follows a \(\chi^2{(h)} \). For significance level
     * \(\alpha\), the \(critical region\) for rejection of the hypothesis of randomness is:
     * <p>
     * \[
     * Q &gt; \chi_{1-\alpha,h}^2
     * \]
     * <p>
     * where \( \chi_{1-\alpha,h}^2 \) is the \(\alpha\)-quantile of the chi-squared distribution with ''h'' degrees of
     * freedom.
     * <p>
     * [1] G. M. Ljung  G. E. P. Box (1978). On a measure of lack of fit in time series models.
     * Biometrika, Volume 65, Issue 2, 1 August 1978, Pages 297-303.
     *
     * @param tss  Expects an input array whose dimension zero is the length of the time series (all the same) and dimension
     *             one indicates the number of time series.
     * @param lags Number of lags being tested.
     * @return The updated ref and the Ljung-Box statistic test.
     */
    public static Array ljungBox(Array tss, long lags) {
        long[] refs = ljungBox(tss.getReference(), lags);
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Returns the kth moment of the given time series.
     *
     * @param tss Array containing the input time series.
     * @param k   The specific moment to be calculated.
     * @return The kth moment of the given time series.
     */
    public static Array moment(Array tss, int k) {
        long[] refs = moment(tss.getReference(), k);
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Returns values at the given quantile.
     *
     * @param tss Array containing the input time series.
     * @param q   Percentile(s) at which to extract score(s). One or many.
     * @return Values at the given quantile.
     */
    public static Array quantile(Array tss, Array q) {
        return quantile(tss, q, 1e8f);
    }

    /**
     * Returns values at the given quantile.
     *
     * @param tss       Array containing the input time series.
     * @param q         Percentile(s) at which to extract score(s). One or many.
     * @param precision Number of decimals expected.
     * @return Values at the given quantile.
     */
    public static Array quantile(Array tss, Array q, float precision) {
        long[] refs = quantile(tss.getReference(), q.getReference(), precision);
        tss.setReference(refs[0]);
        q.setReference(refs[1]);
        return new Array(refs[2]);
    }

    /**
     * Discretizes the time series into equal-sized buckets based on sample quantiles.
     *
     * @param tss       Array containing the input time series.
     * @param quantiles Number of quantiles to extract. From 0 to 1, step 1/quantiles.
     * @return Array with a matrix with the categories, one category per row, the start of the category in the first
     * column and the end in the second category.
     */
    public static Array quantilesCut(Array tss, float quantiles) {
        return quantilesCut(tss, quantiles, 1e-8f);

    }

    /**
     * Discretizes the time series into equal-sized buckets based on sample quantiles.
     *
     * @param tss       Array containing the input time series.
     * @param quantiles Number of quantiles to extract. From 0 to 1, step 1/quantiles.
     * @param precision Number of decimals expected.
     * @return Array with a matrix with the categories, one category per row, the start of the category in the first
     * column and the end in the second category.
     */
    public static Array quantilesCut(Array tss, float quantiles, float precision) {
        long[] refs = quantilesCut(tss.getReference(), quantiles, precision);
        tss.setReference(refs[0]);
        return new Array((refs[1]));
    }

    /**
     * Estimates standard deviation based on a sample. The standard deviation is calculated using the "n-1" method.
     *
     * @param tss Array containing the input time series.
     * @return The sample standard deviation.
     */
    public static Array sampleStdev(Array tss) {
        long[] refs = sampleStdev(tss.getReference());
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the sample skewness of tss (calculated with the adjusted Fisher-Pearson standardized moment
     * coefficient G1).
     *
     * @param tss Array containing the input time series.
     * @return Array containing the skewness of each time series in tss.
     */
    public static Array skewness(Array tss) {
        long[] refs = skewness(tss.getReference());
        tss.setReference(refs[0]);
        return new Array(refs[1]);
    }
}
