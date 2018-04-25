/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package tsa;

public class Features extends Library {

    private native static long[] absEnergy(long ref);

    private native static long[] absoluteSumOfChanges(long ref);

    private native static long[] aggregatedAutocorrelation(long ref, int aggregationFunction);

    private native static long[] aggregatedLinearTrend(long ref, long chunkSize, int aggregationFunction);

    private native static long[] approximateEntropy(long ref, int m, float r);

    private native static long[] crossCovariance(long refXss, long refYss, boolean unbiased);

    private native static long[] autoCovariance(long ref, boolean unbiased);

    private native static long[] crossCorrelation(long refXss, long refYss, boolean unbiased);

    private native static long[] autoCorrelation(long ref, long maxLag, boolean unbiased);

    private native static long[] binnedEntropy(long ref, long maxBins);

    private native static long[] c3(long ref, long lag);

    private native static long[] cidCe(long ref, boolean zNormalize);

    private native static long[] countAboveMean(long ref);

    private native static long[] countBelowMean(long ref);

    private native static long[] cwtCoefficients(long ref, long refW, int coeff, int w);

    private native static long[] energyRatioByChunks(long ref, long numSegments, long segmentFocus);

    private native static long[] fftAggregated(long ref);

    private native static long[] fftCoefficient(long ref, long coefficient);

    private native static long[] firstLocationOfMaximum(long ref);

    private native static long[] firstLocationOfMinimum(long ref);

    private native static long[] hasDuplicates(long ref);

    private native static long[] hasDuplicateMax(long ref);

    private native static long[] hasDuplicateMin(long ref);

    private native static long[] indexMaxQuantile(long ref, float q);

    private native static long[] kurtosis(long ref);

    private native static long[] largeStandardDeviation(long ref, float r);

    private native static long[] lastLocationOfMaximum(long ref)
            ;

    private native static long[] lastLocationOfMinimum(long ref);

    private native static long[] length(long ref);

    private native static long[] linearTrend(long ref);

    private native static long[] longestStrikeAboveMean(long ref);

    private native static long[] longestStrikeBelowMean(long ref);

    private native static long[] maxLangevinFixedPoint(long ref, int m, float r);

    private native static long[] maximum(long ref);

    private native static long[] mean(long ref);

    private native static long[] meanAbsoluteChange(long ref);

    private native static long[] meanChange(long ref);

    private native static long[] meanSecondDerivativeCentral(long ref);

    private native static long[] median(long ref);

    private native static long[] minimum(long ref);

    private native static long[] numberCrossingM(long ref, int m);

    private native static long[] numberPeaks(long ref, int n);

    private native static long[] percentageOfReoccurringDatapointsToAllDatapoints(long ref, boolean isSorted);

    private native static long[] quantile(long ref, long refQ, float precision);

    private native static long[] ratioBeyondRSigma(long ref, float r);

    private native static long[] sampleEntropy(long ref);

    private native static long[] skewness(long ref);

    private native static long[] standardDeviation(long ref);

    private native static long[] sumOfReoccurringDatapoints(long ref, boolean isSorted);

    private native static long[] symmetryLooking(long ref, float r);

    private native static long[] valueCount(long ref, float v);


    /**
     * Calculates the sum over the square values of the time series.
     *
     * @param arr Array containing the input time series.
     * @return Array with the Absolute Energy.
     */
    public static Array absEnergy(Array arr) {
        long[] refs = absEnergy(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the value of an aggregation function f_agg (e.g. var or mean) of the autocorrelation
     * (Compare to http://en.wikipedia.org/wiki/Autocorrelation#Estimation), taken over different all possible
     * lags (1 to length of x).
     *
     * @param arr Array containing the input time series.
     * @return Array with the absolute sum of changes.
     */
    public static Array absoluteSumOfChanges(Array arr) {
        long[] refs = absoluteSumOfChanges(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates a linear least-squares regression for values of the time series that were aggregated
     * over chunks versus the sequence from 0 up to the number of chunks minus one.
     *
     * @param arr                 Array containing the input time series.
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
    public static Array aggregatedAutocorrelation(Array arr, int aggregationFunction) {
        long[] refs = aggregatedAutocorrelation(arr.getReference(), aggregationFunction);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates a linear least-squares regression for values of the time series that were aggregated
     * over chunks versus the sequence from 0 up to the number of chunks minus one.
     *
     * @param arr                 Array containing the input time series.
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
     * @return The aggregated Linear Trend.
     */
    public static Array[] aggregatedLinearTrend(Array arr, long chunkSize, int aggregationFunction) {
        long[] refs = aggregatedLinearTrend(arr.getReference(), chunkSize, aggregationFunction);
        arr.setReference(refs[0]);
        Array[] result = {new Array(refs[1]), new Array(refs[2]), new Array(refs[3]), new Array(refs[4]), new Array(refs[5])};
        return result;

    }

    /**
     * Calculates a vectorized Approximate entropy algorithm.
     * https://en.wikipedia.org/wiki/Approximate_entropy
     * For short time-series this method is highly dependent on the parameters, but should be stable for N greater than 2000,
     * see: Yentes et al. (2012) - The Appropriate Use of Approximate Entropy and Sample Entropy with Short Data Sets
     * Other shortcomings and alternatives discussed in:
     * Richman and Moorman (2000) - Physiological time-series analysis using approximate entropy and sample entropy
     *
     * @param arr Array containing the input time series.
     * @param m   Length of compared run of data.
     * @param r   Filtering level, must be positive.
     * @return Array with the vectorized approximate entropy for all the input time series in arr.
     */

    public static Array approximateEntropy(Array arr, int m, float r) {
        long[] refs = approximateEntropy(arr.getReference(), m, r);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the cross-covariance of the given time series.
     *
     * @param arrXss   Array containing the input time series.
     * @param arrYss   Array containing the input time series.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return Array with the cross-covariance value for the given time series.
     */
    public static Array crossCovariance(Array arrXss, Array arrYss, Boolean unbiased) {
        long[] refs = crossCovariance(arrXss.getReference(), arrYss.getReference(), unbiased);
        arrXss.setReference(refs[0]);
        arrYss.setReference(refs[1]);
        return new Array(refs[2]);
    }

    /**
     * Calculates the auto-covariance the given time series.
     *
     * @param arr      Array containing the input time series.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return Array with the auto-covariance value for the given time series.
     */
    public static Array autoCovariance(Array arr, Boolean unbiased) {
        long[] refs = autoCovariance(arr.getReference(), unbiased);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the cross-correlation of the given time series.
     *
     * @param arrXss   Array containing the input time series.
     * @param arrYss   Array containing the input time series.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return Double array with cross-correlation value for the given time series.
     */
    public static Array crossCorrelation(Array arrXss, Array arrYss, Boolean unbiased) {

        long[] refs = crossCorrelation(arrXss.getReference(), arrYss.getReference(),
                unbiased);
        arrXss.setReference(refs[0]);
        arrYss.setReference(refs[1]);

        return new Array(refs[2]);
    }

    /**
     * Calculates the autocorrelation of the specified lag for the given time series.
     *
     * @param arr      Array containing the input time series.
     * @param maxLag   The maximum lag to compute.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return The autocorrelation value for the given time series.
     */
    public static Array autoCorrelation(Array arr, long maxLag, Boolean unbiased) {
        long[] refs = autoCorrelation(arr.getReference(), maxLag, unbiased);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the binned entropy for the given time series and number of bins.
     *
     * @param arr     Array containing the input time series.
     * @param maxBins The number of bins.
     * @return The binned entropy value for the given time series.
     */
    public static Array binnedEntropy(Array arr, int maxBins) {
        long[] refs = binnedEntropy(arr.getReference(), maxBins);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the Schreiber, T. and Schmitz, A. (1997) measure of non-linearity
     * for the given time series.
     *
     * @param arr Array containing the input time series.
     * @param lag The lag.
     * @return The non-linearity value for the given time series.
     */
    public static Array c3(Array arr, long lag) {
        long[] refs = c3(arr.getReference(), lag);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates an estimate for the time series complexity defined by
     * Batista, Gustavo EAPA, et al (2014). (A more complex time series has more peaks,
     * valleys, etc.)
     *
     * @param arr        Array containing the input time series.
     * @param zNormalize Controls whether the time series should be z-normalized or not.
     * @return The complexity value for the given time series.
     */
    public static Array cidCe(Array arr, Boolean zNormalize) {

        long[] refs = cidCe(arr.getReference(), zNormalize);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the number of values in the time series that are higher than
     * the mean.
     *
     * @param arr Array containing the input time series.
     * @return The number of values in the time series that are higher than the mean.
     */
    public static Array countAboveMean(Array arr) {

        long[] refs = countAboveMean(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the number of values in the time series that are lower than
     * the mean.
     *
     * @param arr Array containing the input time series.
     * @return The number of values in the time series that are lower than the mean.
     */
    public static Array countBelowMean(Array arr) {

        long[] refs = countBelowMean(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates a Continuous wavelet transform for the Ricker wavelet, also known as
     * the "Mexican hat wavelet".
     *
     * @param arr   Array containing the input time series.
     * @param arrW  Widths. It accepts a list of lists or a numpy array with one or several widths.
     * @param coeff Coefficient of interest.
     * @param w     Width of interest.
     * @return Result of calculated coefficients.
     */
    public static Array cwtCoefficients(Array arr, Array arrW, int coeff, int w) {
        long[] refs = cwtCoefficients(arr.getReference(), arrW.getReference(), coeff, w);
        arr.setReference(refs[0]);
        arrW.setReference(refs[1]);
        return new Array(refs[2]);
    }

    /**
     * Calculates the sum of squares of chunk i out of N chunks expressed as a ratio
     * with the sum of squares over the whole series. segmentFocus should be lower
     * than the number of segments.
     *
     * @param arr          Array containing the input time series.
     * @param numSegments  The number of segments to divide the series into.
     * @param segmentFocus The segment number (starting at zero) to return a feature on.
     * @return The energy ratio by chunk of the time series.
     */
    public static Array energyRatioByChunks(Array arr, long numSegments, long segmentFocus) {
        long[] refs = energyRatioByChunks(arr.getReference(), numSegments, segmentFocus);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the spectral centroid(mean), variance, skew, and kurtosis of the absolute fourier transform
     * spectrum.
     *
     * @param arr Array containing the input time series.
     * @return The spectral centroid (mean), variance, skew, and kurtosis of the absolute fourier transform
     * spectrum.
     */
    public static Array fftAggregated(Array arr) {

        long[] refs = fftAggregated(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the fourier coefficients of the one-dimensional discrete
     * Fourier Transform for real input by fast fourier transformation algorithm.
     *
     * @param arr         Array containing the input time series.
     * @param coefficient The coefficient to extract from the FFT.
     * @return Array of Arrays with:
     * <p>
     * real        The real part of the coefficient.
     * imag        The imaginary part of the coefficient.
     * abs         The absolute value of the coefficient.
     * angle       The angle of the coefficient.
     */
    public static Array[] fftCoefficient(Array arr, long coefficient) {
        long[] refs = fftCoefficient(arr.getReference(), coefficient);
        arr.setReference(refs[0]);
        Array[] result = {new Array(refs[1]), new Array(refs[2]), new Array(refs[3]), new Array(refs[4])};
        return result;
    }

    /**
     * Calculates the first relative location of the maximal value for each time series.
     *
     * @param arr Array containing the input time series.
     * @return The first relative location of the maximum value to the length of the times series,
     * for each time series.
     */
    public static Array firstLocationOfMaximum(Array arr) {
        long[] refs = firstLocationOfMaximum(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the first location of the minimal value of each time series. The position
     * is calculated relatively to the length of the series.
     *
     * @param arr Array containing the input time series.
     * @return The first relative location of the minimal value of each series.
     */
    public static Array firstLocationOfMinimum(Array arr) {
        long[] refs = firstLocationOfMinimum(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates if the input time series contain duplicated elements.
     *
     * @param arr Array containing the input time series.
     * @return Array containing True if the time series contains duplicated elements
     * and false otherwise.
     */
    public static Array hasDuplicates(Array arr) {
        long[] refs = hasDuplicates(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates if the maximum within input time series is duplicated.
     *
     * @param arr Array containing the input time series.
     * @return Calculates if the maximum within input time series is duplicated.
     */
    public static Array hasDuplicateMax(Array arr) {

        long[] refs = hasDuplicateMax(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates if the minimum of the input time series is duplicated.
     *
     * @param arr Array containing the input time series.
     * @return Array containing True if the minimum of the time series is duplicated and False otherwise.
     */
    public static Array hasDuplicateMin(Array arr) {

        long[] refs = hasDuplicateMin(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the index of the max quantile.
     *
     * @param arr Array containing the input time series.
     * @param q   The quantile.
     * @return The index of the max quantile q.
     */
    public static Array indexMaxQuantile(Array arr, float q) {
        long[] refs = indexMaxQuantile(arr.getReference(), q);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Returns the kurtosis of arr (calculated with the adjusted Fisher-Pearson standardized moment coefficient G2).
     *
     * @param arr Array containing the input time series.
     * @return The kurtosis of each arr.
     */
    public static Array kurtosis(Array arr) {

        long[] refs = kurtosis(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Checks if the time series within arr have a large standard deviation.
     *
     * @param arr Array containing the input time series.
     * @param r   The threshold.
     * @return Array containing True for those time series in arr that have a large standard deviation.
     */
    public static Array largeStandardDeviation(Array arr, float r) {

        long[] refs = largeStandardDeviation(arr.getReference(), r);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the last location of the maximum value of each time series. The position
     * is calculated relatively to the length of the series.
     *
     * @param arr Array containing the input time series.
     * @return The last relative location of the maximum value of each series.
     */
    public static Array lastLocationOfMaximum(Array arr) {

        long[] refs = lastLocationOfMaximum(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the last location of the minimum value of each time series. The position
     * is calculated relatively to the length of the series.
     *
     * @param arr Array containing the input time series.
     * @return The last relative location of the minimum value of each series.
     */
    public static Array lastLocationOfMinimum(Array arr) {
        long[] refs = lastLocationOfMinimum(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Returns the length of the input time series.
     *
     * @param arr Array containing the input time series.
     * @return The length of arr.
     */
    public static Array length(Array arr) {
        long[] refs = length(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates a linear least-squares regression for the values of the time series versus the sequence from 0 to
     * length of the time series minus one.
     *
     * @param arr Array containing the input time series.
     * @return Array of Arrays with:
     * pvalue    The pvalues for all time series.
     * rvalue    The rvalues for all time series.
     * intercept The intercept values for all time series.
     * slope     The slope for all time series.
     * stdrr     The stdrr values for all time series.
     */
    public static Array[] linearTrend(Array arr) {
        long[] refs = linearTrend(arr.getReference());
        arr.setReference(refs[0]);
        Array[] result = {new Array(refs[1]), new Array(refs[2]), new Array(refs[3]), new Array(refs[4]), new Array(refs[5])};
        return result;
    }

    /**
     * Calculates the length of the longest consecutive subsequence in arr that is bigger than the mean of arr.
     *
     * @param arr Array containing the input time series.
     * @return The length of the longest consecutive subsequence in the input time series that is bigger than the
     * mean.
     */
    public static Array longestStrikeAboveMean(Array arr) {
        long[] refs = longestStrikeAboveMean(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the length of the longest consecutive subsequence in arr that is below the mean of arr.
     *
     * @param arr Array containing the input time series.
     * @return The length of the longest consecutive subsequence in the input time series that is below the mean.
     */
    public static Array longestStrikeBelowMean(Array arr) {
        long[] refs = longestStrikeBelowMean(arr.getReference());
        arr.setReference(refs[0]);

        return new Array(refs[1]);
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
     * @param arr Array containing the input time series.
     * @param m   Order of polynom to fit for estimating fixed points of dynamics.
     * @param r   Number of quantiles to use for averaging.
     * @return Largest fixed point of deterministic dynamics.
     */
    public static Array maxLangevinFixedPoint(Array arr, int m, float r) {
        long[] refs = maxLangevinFixedPoint(arr.getReference(), m, r);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the maximum value for each time series within arr.
     *
     * @param arr Array containing the input time series.
     * @return The maximum value of each time series within arr.
     */
    public static Array maximum(Array arr) {

        long[] refs = maximum(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the mean value for each time series within arr.
     *
     * @param arr Array containing the input time series.
     * @return The mean value of each time series within arr.
     */
    public static Array mean(Array arr) {

        long[] refs = mean(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the mean over the absolute differences between subsequent time series values in arr.
     *
     * @param arr Array containing the input time series.
     * @return The mean over the absolute differences between subsequent time series values.
     */
    public static Array meanAbsoluteChange(Array arr) {
        long[] refs = meanAbsoluteChange(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the mean over the differences between subsequent time series values in arr.
     *
     * @param arr Array containing the input time series.
     * @return The mean over the differences between subsequent time series values.
     */
    public static Array meanChange(Array arr) {
        long[] refs = meanChange(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates mean value of a central approximation of the second derivative for each time series in arr.
     *
     * @param arr Array containing the input time series.
     * @return The mean value of a central approximation of the second derivative for each time series.
     */
    public static Array meanSecondDerivativeCentral(Array arr) {
        long[] refs = meanSecondDerivativeCentral(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the median value for each time series within arr.
     *
     * @param arr Array containing the input time series.
     * @return The median value of each time series within arr.
     */
    public static Array median(Array arr) {
        long[] refs = median(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the minimum value for each time series within arr.
     *
     * @param arr Array containing the input time series.
     * @return The minimum value of each time series within arr.
     */
    public static Array minimum(Array arr) {
        long[] refs = minimum(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the number of m-crossings. A m-crossing is defined as two sequential values where the first
     * value is lower than m and the next is greater, or viceversa. If you set m to zero, you will get the number of
     * zero crossings.
     *
     * @param arr Array containing the input time series.
     * @param m   The m value.
     * @return The number of m-crossings of each time series within arr.
     */
    public static Array numberCrossingM(Array arr, int m) {
        long[] refs = numberCrossingM(arr.getReference(), m);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the number of peaks of at least support \(n\) in the time series \(arr\). A peak of support
     * \(n\) is defined as a subsequence of \(arr\) where a value occurs, which is bigger than
     * its \(n\) neighbours to the left and to the right.
     *
     * @param arr Array containing the input time series.
     * @param n   The support of the peak.
     * @return The number of peaks of at least support \(n\).
     */
    public static Array numberPeaks(Array arr, int n) {
        long[] refs = numberPeaks(arr.getReference(), n);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the percentage of unique values, that are present in the time series more than once.
     * \[
     * len(different values occurring more than once) / len(different values)
     * \]
     * This means the percentage is normalized to the number of unique values, in contrast to the
     * percentageOfReoccurringValuesToAllValues.
     *
     * @param arr      Array containing the input time series.
     * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
     * @return Returns the percentage of unique values, that are present in the time series more than once.
     */
    public static Array percentageOfReoccurringDatapointsToAllDatapoints(Array arr, boolean isSorted) {
        long[] refs = percentageOfReoccurringDatapointsToAllDatapoints(arr.getReference(), isSorted);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Returns values at the given quantile.
     *
     * @param arr  Array containing the input time series.
     * @param arrQ Percentile(s) at which to extract score(s). One or many.
     * @return Values at the given quantile.
     */
    public static Array quantile(Array arr, Array arrQ) {
        return quantile(arr, arrQ, (float) 1e8);
    }

    /**
     * Returns values at the given quantile.
     *
     * @param arr       Array containing the input time series.
     * @param arrQ      Percentile(s) at which to extract score(s). One or many.
     * @param precision Number of decimals expected.
     * @return Values at the given quantile.
     */
    public static Array quantile(Array arr, Array arrQ, float precision) {
        long[] refs = quantile(arr.getReference(), arrQ.getReference(), precision);
        arr.setReference(refs[0]);
        arrQ.setReference(refs[1]);
        return new Array(refs[2]);
    }

    /**
     * Calculates the ratio of values that are more than  \(r*std(x)\] (so \(r\) sigma) away from the mean of
     * \(x\).
     *
     * @param arr Array containing the input time series.
     * @param r   Number of times that the values should be away from.
     * @return The ratio of values that are more than \(r*std(x)\) (so \(r\) sigma) away from the mean of
     * \(x\).
     */
    public static Array ratioBeyondRSigma(Array arr, float r) {

        long[] refs = ratioBeyondRSigma(arr.getReference(), r);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
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
     * @param arr Array containing the input time series.
     * @return An array with the same dimensions as arr, whose values (time series in dimension 0)
     * contains the vectorized sample entropy for all the input time series in arr.
     */
    public static Array sampleEntropy(Array arr) {
        long[] refs = sampleEntropy(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the sample skewness of arr (calculated with the adjusted Fisher-Pearson standardized
     * moment coefficient G1).
     *
     * @param arr Array containing the input time series.
     * @return Array containing the skewness of each time series in arr.
     */
    public static Array skewness(Array arr) {
        long[] refs = skewness(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the standard deviation of each time series within arr.
     *
     * @param arr Array containing the input time series.
     * @return The standard deviation of each time series within arr.
     */
    public static Array standardDeviation(Array arr) {
        long[] refs = standardDeviation(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the sum of all data points, that are present in the time series more than once.
     *
     * @param arr Array containing the input time series.
     * @return Returns the sum of all data points, that are present in the time series more than once.
     */
    public static Array sumOfReoccurringDatapoints(Array arr) {
        return sumOfReoccurringDatapoints(arr, false);
    }

    /**
     * Calculates the sum of all data points, that are present in the time series more than once.
     *
     * @param arr      Array containing the input time series.
     * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
     * @return Returns the sum of all data points, that are present in the time series more than once.
     */
    public static Array sumOfReoccurringDatapoints(Array arr, boolean isSorted) {
        long[] refs = sumOfReoccurringDatapoints(arr.getReference(), isSorted);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates if the distribution of arr *looks symmetric*. This is the case if
     * \[
     * | mean(arr)-median(arr)| < r * (max(arr)-min(arr))
     * \]
     *
     * @param arr Array containing the input time series.
     * @param r   The percentage of the range to compare with.
     * @return An array denoting if the input time series look symmetric.
     */
    public static Array symmetryLooking(Array arr, float r) {
        long[] refs = symmetryLooking(arr.getReference(), r);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Counts occurrences of value in the time series arr.
     *
     * @param arr Array containing the input time series.
     * @param v   The value to be counted.
     * @return An array containing the count of the given value in each time series.
     */
    public static Array valueCount(Array arr, float v) {
        long[] refs = valueCount(arr.getReference(), v);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }
}
