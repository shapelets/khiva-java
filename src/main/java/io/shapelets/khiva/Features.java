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
 * Khiva Features class containing a number of features that can be extracted from time series. All the methods
 * operate with instances of the ARRAY class as input and output.
 */
public class Features extends Library {

    private native static long absEnergy(long ref) throws Exception;

    private native static long absoluteSumOfChanges(long ref) throws Exception;

    private native static long aggregatedAutocorrelation(long ref, int aggregationFunction) throws Exception;

    private native static long[] aggregatedLinearTrend(long ref, long chunkSize, int aggregationFunction)
        throws Exception;

    private native static long approximateEntropy(long ref, int m, float r) throws Exception;

    private native static long crossCovariance(long refXss, long refYss, boolean unbiased) throws Exception;

    private native static long autoCovariance(long ref, boolean unbiased) throws Exception;

    private native static long crossCorrelation(long refXss, long refYss, boolean unbiased) throws Exception;

    private native static long autoCorrelation(long ref, long maxLag, boolean unbiased) throws Exception;

    private native static long binnedEntropy(long ref, long maxBins) throws Exception;

    private native static long c3(long ref, long lag) throws Exception;

    private native static long cidCe(long ref, boolean zNormalize) throws Exception;

    private native static long countAboveMean(long ref) throws Exception;

    private native static long countBelowMean(long ref) throws Exception;

    private native static long cwtCoefficients(long ref, long refW, int coeff, int w) throws Exception;

    private native static long energyRatioByChunks(long ref, long numSegments, long segmentFocus) throws Exception;

    private native static long fftAggregated(long ref) throws Exception;

    private native static long[] fftCoefficient(long ref, long coefficient) throws Exception;

    private native static long firstLocationOfMaximum(long ref) throws Exception;

    private native static long firstLocationOfMinimum(long ref) throws Exception;

    private native static long friedrichCoefficients(long ref, int m, float r) throws Exception;

    private native static long hasDuplicates(long ref) throws Exception;

    private native static long hasDuplicateMax(long ref) throws Exception;

    private native static long hasDuplicateMin(long ref) throws Exception;

    private native static long indexMassQuantile(long ref, float q) throws Exception;

    private native static long kurtosis(long ref) throws Exception;

    private native static long largeStandardDeviation(long ref, float r) throws Exception;

    private native static long lastLocationOfMaximum(long ref) throws Exception;

    private native static long lastLocationOfMinimum(long ref) throws Exception;

    private native static long length(long ref) throws Exception;

    private native static long[] linearTrend(long ref) throws Exception;

    private native static long localMaximals(long ref) throws Exception;

    private native static long longestStrikeAboveMean(long ref) throws Exception;

    private native static long longestStrikeBelowMean(long ref) throws Exception;

    private native static long maxLangevinFixedPoint(long ref, int m, float r) throws Exception;

    private native static long maximum(long ref) throws Exception;

    private native static long mean(long ref) throws Exception;

    private native static long meanAbsoluteChange(long ref) throws Exception;

    private native static long meanChange(long ref) throws Exception;

    private native static long meanSecondDerivativeCentral(long ref) throws Exception;

    private native static long median(long ref) throws Exception;

    private native static long minimum(long ref) throws Exception;

    private native static long numberCrossingM(long ref, int m) throws Exception;

    private native static long numberCwtPeaks(long ref, int maxW) throws Exception;

    private native static long numberPeaks(long ref, int n) throws Exception;

    private native static long partialAutocorrelation(long ref, long refLags) throws Exception;

    private native static long percentageOfReoccurringDatapointsToAllDatapoints(long ref, boolean isSorted)
        throws Exception;

    private native static long percentageOfReoccurringValuesToAllValues(long ref, boolean isSorted) throws Exception;

    private native static long quantile(long ref, long refQ, float precision) throws Exception;

    private native static long rangeCount(long ref, float min, float max) throws Exception;

    private native static long ratioBeyondRSigma(long ref, float r) throws Exception;

    private native static long ratioValueNumberToTimeSeriesLength(long ref) throws Exception;

    private native static long sampleEntropy(long ref) throws Exception;

    private native static long skewness(long ref) throws Exception;

    private native static long spktWelchDensity(long ref, int coeff) throws Exception;

    private native static long standardDeviation(long ref) throws Exception;

    private native static long sumOfReoccurringDatapoints(long ref, boolean isSorted) throws Exception;

    private native static long sumOfReoccurringValues(long ref, boolean isSorted) throws Exception;

    private native static long sumValues(long ref) throws Exception;

    private native static long symmetryLooking(long ref, float r) throws Exception;

    private native static long timeReversalAsymmetryStatistic(long ref, int lag) throws Exception;

    private native static long valueCount(long ref, float v) throws Exception;

    private native static long variance(long ref) throws Exception;

    private native static long varianceLargerThanStandardDeviation(long ref) throws Exception;

    /**
     * Calculates the sum over the square values of the time series.
     *
     * @param arr Array containing the input time series.
     * @return Array with the Absolute Energy.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array absEnergy(Array arr) throws Exception {
        long ref = absEnergy(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the value of an aggregation function f_agg (e.g. var or mean) of the autocorrelation
     * (Compare to http://en.wikipedia.org/wiki/Autocorrelation#Estimation), taken over different all possible
     * lags (1 to length of x).
     *
     * @param arr Array containing the input time series.
     * @return Array with the absolute sum of changes.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array absoluteSumOfChanges(Array arr) throws Exception {
        long ref = absoluteSumOfChanges(arr.getReference());
        return Array.fromNative(ref);
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array aggregatedAutocorrelation(Array arr, int aggregationFunction) throws Exception {
        long ref = aggregatedAutocorrelation(arr.getReference(), aggregationFunction);
        return Array.fromNative(ref);
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array[] aggregatedLinearTrend(Array arr, long chunkSize, int aggregationFunction) throws Exception {
        long[] refs = aggregatedLinearTrend(arr.getReference(), chunkSize, aggregationFunction);
        return new Array[]{Array.fromNative(refs[0]), Array.fromNative(refs[1]), Array.fromNative(refs[2]),
                           Array.fromNative(refs[3]), Array.fromNative(refs[4])};

    }

    /**
     * Calculates a vectorized Approximate entropy algorithm.
     * https://en.wikipedia.org/wiki/Approximate_entropy
     * For short time-series this method is highly dependent on the parameters, but should be stable for N greater
     * than 2000,
     * see: Yentes et al. (2012) - The Appropriate Use of Approximate Entropy and Sample Entropy with Short Data Sets
     * Other shortcomings and alternatives discussed in:
     * Richman and Moorman (2000) - Physiological time-series analysis using approximate entropy and sample entropy
     *
     * @param arr Array containing the input time series.
     * @param m   Length of compared run of data.
     * @param r   Filtering level, must be positive.
     * @return Array with the vectorized approximate entropy for all the input time series in arr.
     * @throws java.lang.Exception If the native function call fails.
     */

    public static Array approximateEntropy(Array arr, int m, float r) throws Exception {
        long ref = approximateEntropy(arr.getReference(), m, r);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the cross-covariance of the given time series.
     *
     * @param arrXss   Array containing the input time series.
     * @param arrYss   Array containing the input time series.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return Array with the cross-covariance value for the given time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array crossCovariance(Array arrXss, Array arrYss, Boolean unbiased) throws Exception {
        long ref = crossCovariance(arrXss.getReference(), arrYss.getReference(), unbiased);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the auto-covariance the given time series.
     *
     * @param arr      Array containing the input time series.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return Array with the auto-covariance value for the given time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array autoCovariance(Array arr, Boolean unbiased) throws Exception {
        long ref = autoCovariance(arr.getReference(), unbiased);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the cross-correlation of the given time series.
     *
     * @param arrXss   Array containing the input time series.
     * @param arrYss   Array containing the input time series.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return Double array with cross-correlation value for the given time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array crossCorrelation(Array arrXss, Array arrYss, Boolean unbiased) throws Exception {
        long ref = crossCorrelation(arrXss.getReference(), arrYss.getReference(), unbiased);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the autocorrelation of the specified lag for the given time series.
     *
     * @param arr      Array containing the input time series.
     * @param maxLag   The maximum lag to compute.
     * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
     * @return The autocorrelation value for the given time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array autoCorrelation(Array arr, long maxLag, Boolean unbiased) throws Exception {
        long ref = autoCorrelation(arr.getReference(), maxLag, unbiased);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the binned entropy for the given time series and number of bins.
     *
     * @param arr     Array containing the input time series.
     * @param maxBins The number of bins.
     * @return The binned entropy value for the given time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array binnedEntropy(Array arr, int maxBins) throws Exception {
        long ref = binnedEntropy(arr.getReference(), maxBins);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the Schreiber, T. and Schmitz, A. (1997) measure of non-linearity
     * for the given time series.
     *
     * @param arr Array containing the input time series.
     * @param lag The lag.
     * @return The non-linearity value for the given time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array c3(Array arr, long lag) throws Exception {
        long ref = c3(arr.getReference(), lag);
        return Array.fromNative(ref);
    }

    /**
     * Calculates an estimate for the time series complexity defined by
     * Batista, Gustavo EAPA, et al (2014). (A more complex time series has more peaks,
     * valleys, etc.)
     *
     * @param arr        Array containing the input time series.
     * @param zNormalize Controls whether the time series should be z-normalized or not.
     * @return The complexity value for the given time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array cidCe(Array arr, Boolean zNormalize) throws Exception {
        long ref = cidCe(arr.getReference(), zNormalize);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the number of values in the time series that are higher than
     * the mean.
     *
     * @param arr Array containing the input time series.
     * @return The number of values in the time series that are higher than the mean.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array countAboveMean(Array arr) throws Exception {
        long ref = countAboveMean(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the number of values in the time series that are lower than
     * the mean.
     *
     * @param arr Array containing the input time series.
     * @return The number of values in the time series that are lower than the mean.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array countBelowMean(Array arr) throws Exception {
        long ref = countBelowMean(arr.getReference());
        return Array.fromNative(ref);
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array cwtCoefficients(Array arr, Array arrW, int coeff, int w) throws Exception {
        long ref = cwtCoefficients(arr.getReference(), arrW.getReference(), coeff, w);
        return Array.fromNative(ref);
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array energyRatioByChunks(Array arr, long numSegments, long segmentFocus) throws Exception {
        long ref = energyRatioByChunks(arr.getReference(), numSegments, segmentFocus);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the spectral centroid(mean), variance, skew, and kurtosis of the absolute fourier transform
     * spectrum.
     *
     * @param arr Array containing the input time series.
     * @return The spectral centroid (mean), variance, skew, and kurtosis of the absolute fourier transform
     * spectrum.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array fftAggregated(Array arr) throws Exception {
        long ref = fftAggregated(arr.getReference());
        return Array.fromNative(ref);
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array[] fftCoefficient(Array arr, long coefficient) throws Exception {
        long[] refs = fftCoefficient(arr.getReference(), coefficient);
        return new Array[]{Array.fromNative(refs[0]), Array.fromNative(refs[1]), Array.fromNative(refs[2]),
                           Array.fromNative(refs[3])};
    }

    /**
     * Calculates the first relative location of the maximal value for each time series.
     *
     * @param arr Array containing the input time series.
     * @return The first relative location of the maximum value to the length of the times series,
     * for each time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array firstLocationOfMaximum(Array arr) throws Exception {
        long ref = firstLocationOfMaximum(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the first location of the minimal value of each time series. The position
     * is calculated relatively to the length of the series.
     *
     * @param arr Array containing the input time series.
     * @return The first relative location of the minimal value of each series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array firstLocationOfMinimum(Array arr) throws Exception {
        long ref = firstLocationOfMinimum(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Coefficients of polynomial \(h(x)\), which has been fitted to the deterministic
     * dynamics of Langevin model:
     * \[
     * \dot(x)(t) = h(x(t)) + R \mathcal(N)(0,1)
     * \]
     * as described by [1]. For short time series this method is highly dependent on the parameters.
     * *
     * [1] Friedrich et al. (2000): Physics Letters A 271, p. 217-222
     * Extracting model equations from experimental data.
     *
     * @param arr Array containing the input time series.
     * @param m   Order of polynom to fit for estimating fixed points of dynamics.
     * @param r   Number of quantils to use for averaging.
     * @return Array containing the coefficients for each time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array friedrichCoefficients(Array arr, int m, float r) throws Exception {
        long ref = friedrichCoefficients(arr.getReference(), m, r);
        return Array.fromNative(ref);
    }

    /**
     * Calculates if the input time series contain duplicated elements.
     *
     * @param arr Array containing the input time series.
     * @return Array containing True if the time series contains duplicated elements
     * and false otherwise.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array hasDuplicates(Array arr) throws Exception {
        long ref = hasDuplicates(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates if the maximum within input time series is duplicated.
     *
     * @param arr Array containing the input time series.
     * @return Calculates if the maximum within input time series is duplicated.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array hasDuplicateMax(Array arr) throws Exception {
        long ref = hasDuplicateMax(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates if the minimum of the input time series is duplicated.
     *
     * @param arr Array containing the input time series.
     * @return Array containing True if the minimum of the time series is duplicated and False otherwise.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array hasDuplicateMin(Array arr) throws Exception {
        long ref = hasDuplicateMin(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the index of the mass quantile.
     *
     * @param arr Array containing the input time series.
     * @param q   The quantile.
     * @return The index of the mass quantile q.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array indexMassQuantile(Array arr, float q) throws Exception {
        long ref = indexMassQuantile(arr.getReference(), q);
        return Array.fromNative(ref);
    }

    /**
     * Returns the kurtosis of arr (calculated with the adjusted Fisher-Pearson standardized moment coefficient G2).
     *
     * @param arr Array containing the input time series.
     * @return The kurtosis of each arr.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array kurtosis(Array arr) throws Exception {
        long ref = kurtosis(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Checks if the time series within arr have a large standard deviation.
     *
     * @param arr Array containing the input time series.
     * @param r   The threshold.
     * @return Array containing True for those time series in arr that have a large standard deviation.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array largeStandardDeviation(Array arr, float r) throws Exception {
        long ref = largeStandardDeviation(arr.getReference(), r);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the last location of the maximum value of each time series. The position
     * is calculated relatively to the length of the series.
     *
     * @param arr Array containing the input time series.
     * @return The last relative location of the maximum value of each series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array lastLocationOfMaximum(Array arr) throws Exception {
        long ref = lastLocationOfMaximum(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the last location of the minimum value of each time series. The position
     * is calculated relatively to the length of the series.
     *
     * @param arr Array containing the input time series.
     * @return The last relative location of the minimum value of each series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array lastLocationOfMinimum(Array arr) throws Exception {
        long ref = lastLocationOfMinimum(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Returns the length of the input time series.
     *
     * @param arr Array containing the input time series.
     * @return The length of arr.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array length(Array arr) throws Exception {
        long ref = length(arr.getReference());
        return Array.fromNative(ref);
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array[] linearTrend(Array arr) throws Exception {
        long[] refs = linearTrend(arr.getReference());
        return new Array[]{Array.fromNative(refs[0]), Array.fromNative(refs[1]), Array.fromNative(refs[2]),
                           Array.fromNative(refs[3]), Array.fromNative(refs[4])};
    }

    /**
     * Calculates all Local Maximals fot the time series in arr.
     *
     * @param arr Array containing the input time series.
     * @return The calculated local maximals for each time series in array.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array localMaximals(Array arr) throws Exception {
        long ref = localMaximals(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the length of the longest consecutive subsequence in arr that is bigger than the mean of arr.
     *
     * @param arr Array containing the input time series.
     * @return The length of the longest consecutive subsequence in the input time series that is bigger than the
     * mean.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array longestStrikeAboveMean(Array arr) throws Exception {
        long ref = longestStrikeAboveMean(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the length of the longest consecutive subsequence in arr that is below the mean of arr.
     *
     * @param arr Array containing the input time series.
     * @return The length of the longest consecutive subsequence in the input time series that is below the mean.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array longestStrikeBelowMean(Array arr) throws Exception {
        long ref = longestStrikeBelowMean(arr.getReference());
        return Array.fromNative(ref);
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array maxLangevinFixedPoint(Array arr, int m, float r) throws Exception {
        long ref = maxLangevinFixedPoint(arr.getReference(), m, r);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the maximum value for each time series within arr.
     *
     * @param arr Array containing the input time series.
     * @return The maximum value of each time series within arr.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array maximum(Array arr) throws Exception {
        long ref = maximum(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the mean value for each time series within arr.
     *
     * @param arr Array containing the input time series.
     * @return The mean value of each time series within arr.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array mean(Array arr) throws Exception {
        long ref = mean(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the mean over the absolute differences between subsequent time series values in arr.
     *
     * @param arr Array containing the input time series.
     * @return The mean over the absolute differences between subsequent time series values.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array meanAbsoluteChange(Array arr) throws Exception {
        long ref = meanAbsoluteChange(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the mean over the differences between subsequent time series values in arr.
     *
     * @param arr Array containing the input time series.
     * @return The mean over the differences between subsequent time series values.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array meanChange(Array arr) throws Exception {
        long ref = meanChange(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates mean value of a central approximation of the second derivative for each time series in arr.
     *
     * @param arr Array containing the input time series.
     * @return The mean value of a central approximation of the second derivative for each time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array meanSecondDerivativeCentral(Array arr) throws Exception {
        long ref = meanSecondDerivativeCentral(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the median value for each time series within arr.
     *
     * @param arr Array containing the input time series.
     * @return The median value of each time series within arr.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array median(Array arr) throws Exception {
        long ref = median(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the minimum value for each time series within arr.
     *
     * @param arr Array containing the input time series.
     * @return The minimum value of each time series within arr.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array minimum(Array arr) throws Exception {
        long ref = minimum(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the number of m-crossings. A m-crossing is defined as two sequential values where the first
     * value is lower than m and the next is greater, or viceversa. If you set m to zero, you will get the number of
     * zero crossings.
     *
     * @param arr Array containing the input time series.
     * @param m   The m value.
     * @return The number of m-crossings of each time series within arr.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array numberCrossingM(Array arr, int m) throws Exception {
        long ref = numberCrossingM(arr.getReference(), m);
        return Array.fromNative(ref);
    }

    /**
     * This feature calculator searches for different peaks. To do so, the time series is smoothed by a ricker
     * wavelet and for widths ranging from 1 to maxW. This feature calculator returns the number of peaks that occur at
     * enough width scales and with sufficiently high Signal-to-Noise-Ratio (SNR).
     *
     * @param arr  Array containing the input time series.
     * @param maxW The maximum width to consider.
     * @return The number of peaks for each time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array numberCwtPeaks(Array arr, int maxW) throws Exception {
        long ref = numberCwtPeaks(arr.getReference(), maxW);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the number of peaks of at least support \(n\) in the time series \(arr\). A peak of support
     * \(n\) is defined as a subsequence of \(arr\) where a value occurs, which is bigger than
     * its \(n\) neighbours to the left and to the right.
     *
     * @param arr Array containing the input time series.
     * @param n   The support of the peak.
     * @return The number of peaks of at least support \(n\).
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array numberPeaks(Array arr, int n) throws Exception {
        long ref = numberPeaks(arr.getReference(), n);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the value of the partial autocorrelation function at the given lag. The lag \(k\) partial
     * autocorrelation of a time series \(\lbrace x_t, t = 1 \ldots T \rbrace\) equals the partial correlation of
     * \(x_t\) and \(x_{t-k}\), adjusted for the intermediate variables \(\lbrace x_{t-1}, \ldots, x_{t-k+1}
     * \rbrace\) ([1]). Following [2], it can be defined as:
     * <p>
     * \[
     * \alpha_k = \frac{ Cov(x_t, x_{t-k} | x_{t-1}, \ldots, x_{t-k+1})}
     * {\sqrt{ Var(x_t | x_{t-1}, \ldots, x_{t-k+1}) Var(x_{t-k} | x_{t-1}, \ldots, x_{t-k+1} )}}
     * \]
     * with (a) \(x_t = f(x_{t-1}, \ldots, x_{t-k+1})\) and (b) \( x_{t-k} = f(x_{t-1}, \ldots, x_{t-k+1})\)
     * being AR(k-1) models that can be fitted by OLS. Be aware that in (a), the regression is done on past values to
     * predict \( x_t \) whereas in (b), future values are used to calculate the past value \(x_{t-k}\).
     * It is said in [1] that "for an AR(p), the partial autocorrelations \( \alpha_k \) will be nonzero for
     * \( k \leq p \) and zero for \( k&gt;p \)."
     * With this property, it is used to determine the lag of an AR-Process.
     * <p>
     * [1] Box, G. E., Jenkins, G. M., Reinsel, G. C., {@literal &} Ljung, G. M. (2015).
     * Time series analysis: forecasting and control. John Wiley {@literal &} Sons.
     * [2] https://onlinecourses.science.psu.edu/stat510/node/62
     *
     * @param arr     Array containing the input time series.
     * @param arrLags Array containing the lags to be calculated.
     * @return Returns the partial autocorrelation for each time series for the given lags.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array partialAutocorrelation(Array arr, Array arrLags) throws Exception {
        long ref = partialAutocorrelation(arr.getReference(), arrLags.getReference());
        return Array.fromNative(ref);
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
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array percentageOfReoccurringDatapointsToAllDatapoints(Array arr, boolean isSorted) throws Exception {
        long ref = percentageOfReoccurringDatapointsToAllDatapoints(arr.getReference(), isSorted);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the percentage of unique values, that are present in the time series more than once.
     * \[
     * \frac{\textit{number of data points occurring more than once}}{\textit{number of all data points})}
     * \]
     * This means the percentage is normalized to the number of unique values, in contrast to the
     * percentageOfReoccurringDatapointsToAllDatapoints.
     *
     * @param arr      Array containing the input time series.
     * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
     * @return Returns the percentage of unique values, that are present in the time series more than once.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array percentageOfReoccurringValuesToAllValues(Array arr, boolean isSorted) throws Exception {
        long ref = percentageOfReoccurringValuesToAllValues(arr.getReference(), isSorted);
        return Array.fromNative(ref);
    }

    /**
     * Returns values at the given quantile.
     *
     * @param arr  Array containing the input time series.
     * @param arrQ Percentile(s) at which to extract score(s). One or many.
     * @return Values at the given quantile.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array quantile(Array arr, Array arrQ) throws Exception {
        return quantile(arr, arrQ, (float) 1e8);
    }

    /**
     * Returns values at the given quantile.
     *
     * @param arr       Array containing the input time series.
     * @param arrQ      Percentile(s) at which to extract score(s). One or many.
     * @param precision Number of decimals expected.
     * @return Values at the given quantile.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array quantile(Array arr, Array arrQ, float precision) throws Exception {
        long ref = quantile(arr.getReference(), arrQ.getReference(), precision);
        return Array.fromNative(ref);
    }

    /**
     * Counts observed values within the interval [min, max).
     *
     * @param arr Array containing the input time series.
     * @param min Value that sets the lower limit.
     * @param max Value that sets the upper limit.
     * @return Values at the given range.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array rangeCount(Array arr, float min, float max) throws Exception {
        long ref = rangeCount(arr.getReference(), min, max);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the ratio of values that are more than  \(r*std(x)\] (so \(r\) sigma) away from the mean of
     * \(x\).
     *
     * @param arr Array containing the input time series.
     * @param r   Number of times that the values should be away from.
     * @return The ratio of values that are more than \(r*std(x)\) (so \(r\) sigma) away from the mean of
     * \(x\).
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array ratioBeyondRSigma(Array arr, float r) throws Exception {
        long ref = ratioBeyondRSigma(arr.getReference(), r);
        return Array.fromNative(ref);
    }

    /**
     * Calculates a factor which is 1 if all values in the time series occur only once, and below one if this is
     * not the case. In principle, it just returns:
     * <p>
     * \[
     * \frac{\textit{number_unique_values}}{\textit{number_values}}
     * \]
     *
     * @param arr Array containing the input time series.
     * @return The ratio of unique values with respect to the total number of values.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array ratioValueNumberToTimeSeriesLength(Array arr) throws Exception {
        long ref = ratioValueNumberToTimeSeriesLength(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates a vectorized sample entropy algorithm.
     * https://en.wikipedia.org/wiki/Sample_entropy
     * https://www.ncbi.nlm.nih.gov/pubmed/10843903?dopt=Abstract
     * For short time-series this method is highly dependent on the parameters, but should be stable for N &gt; 2000,
     * see: Yentes et al. (2012) - The Appropriate Use of Approximate Entropy and Sample Entropy with Short Data Sets
     * Other shortcomings and alternatives discussed in:
     * Richman {@literal &} Moorman (2000) - Physiological time-series analysis using approximate entropy and sample
     * entropy.
     *
     * @param arr Array containing the input time series.
     * @return An array with the same dimensions as arr, whose values (time series in dimension 0)
     * contains the vectorized sample entropy for all the input time series in arr.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array sampleEntropy(Array arr) throws Exception {
        long ref = sampleEntropy(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the sample skewness of arr (calculated with the adjusted Fisher-Pearson standardized
     * moment coefficient G1).
     *
     * @param arr Array containing the input time series.
     * @return Array containing the skewness of each time series in arr.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array skewness(Array arr) throws Exception {
        long ref = skewness(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Estimates the cross power spectral density of the time series array at different frequencies. To do so, the
     * time series is first shifted from the time domain to the frequency domain.
     * <p>
     * Welch's method computes an estimate of the power spectral density by dividing the data into overlapping
     * segments, computing a modified periodogram for each segment and averaging the periodograms.
     * [1] P. Welch, "The use of the fast Fourier transform for the estimation of power spectra: A method based on time
     * averaging over short, modified periodograms", IEEE Trans. Audio Electroacoust. vol. 15, pp. 70-73, 1967.
     * [2] M.S. Bartlett, "Periodogram Analysis and Continuous Spectra", Biometrika, vol. 37, pp. 1-16, 1950.
     * [3] Rabiner, Lawrence R., and B. Gold. "Theory and Application of Digital Signal Processing" Prentice-Hall, pp.
     * 414-419, 1975.
     *
     * @param arr   Array containing the input time series.
     * @param coeff The coefficient to be returned.
     * @return Array containing the power spectrum of the different frequencies for each time series in array.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array spktWelchDensity(Array arr, int coeff) throws Exception {
        long ref = spktWelchDensity(arr.getReference(), coeff);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the standard deviation of each time series within arr.
     *
     * @param arr Array containing the input time series.
     * @return The standard deviation of each time series within arr.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array standardDeviation(Array arr) throws Exception {
        long ref = standardDeviation(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates the sum of all data points, that are present in the time series more than once.
     *
     * @param arr Array containing the input time series.
     * @return Returns the sum of all data points, that are present in the time series more than once.
     */
    public static Array sumOfReoccurringDatapoints(Array arr) throws Exception {
        return sumOfReoccurringDatapoints(arr, false);
    }

    /**
     * Calculates the sum of all data points, that are present in the time series more than once.
     *
     * @param arr      Array containing the input time series.
     * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
     * @return Returns the sum of all data points, that are present in the time series more than once.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array sumOfReoccurringDatapoints(Array arr, boolean isSorted) throws Exception {
        long ref = sumOfReoccurringDatapoints(arr.getReference(), isSorted);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the sum of all values, that are present in the time series more than once.
     *
     * @param arr Array containing the input time series.
     * @return Array containing the the sum of all values, that are present in the time series more than once.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array sumOfReoccurringValues(Array arr) throws Exception {
        long ref = sumOfReoccurringValues(arr.getReference(), false);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the sum of all values, that are present in the time series more than once.
     *
     * @param arr      Array containing the input time series.
     * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
     * @return Array containing the the sum of all values, that are present in the time series more than once.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array sumOfReoccurringValues(Array arr, boolean isSorted) throws Exception {
        long ref = sumOfReoccurringValues(arr.getReference(), isSorted);
        return Array.fromNative(ref);
    }

    /**
     * Calculates the sum over the time series array.
     *
     * @param arr Array containing the input time series.
     * @return Srray containing the sum of values in each time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array sumValues(Array arr) throws Exception {
        long ref = sumValues(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates if the distribution of arr *looks symmetric*. This is the case if
     * \[
     * | mean(arr)-median(arr)| &lt; r * (max(arr)-min(arr))
     * \]
     *
     * @param arr Array containing the input time series.
     * @param r   The percentage of the range to compare with.
     * @return An array denoting if the input time series look symmetric.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array symmetryLooking(Array arr, float r) throws Exception {
        long ref = symmetryLooking(arr.getReference(), r);
        return Array.fromNative(ref);
    }

    /**
     * This function calculates the value of:
     * \[
     * \frac{1}{n-2lag} \sum_{i=0}^{n-2lag} x_{i + 2 \cdot lag}^2 \cdot x_{i + lag} - x_{i + lag} \cdot  x_{i}^2
     * \]
     * which is
     * \[
     * \mathbb{E}[L^2(X)^2 \cdot L(X) - L(X) \cdot X^2]
     * \]
     * where \f$ \mathbb{E} \f$ is the mean and \f$ L \f$ is the lag operator. It was proposed in [1] as a promising
     * feature to extract from time series.
     * <p>
     * [1] Fulcher, B.D., Jones, N.S. (2014). Highly comparative feature-based time-series classification.
     * Knowledge and Data Engineering, IEEE Transactions on 26, 3026-3037.
     *
     * @param arr Array containing the input time series.
     * @param lag The lag to be computed.
     * @return An array containing the time reversal asymetry statistic value in each time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array timeReversalAsymmetryStatistic(Array arr, int lag) throws Exception {
        long ref = timeReversalAsymmetryStatistic(arr.getReference(), lag);
        return Array.fromNative(ref);
    }

    /**
     * Counts occurrences of value in the time series arr.
     *
     * @param arr Array containing the input time series.
     * @param v   The value to be counted.
     * @return An array containing the count of the given value in each time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array valueCount(Array arr, float v) throws Exception {
        long ref = valueCount(arr.getReference(), v);
        return Array.fromNative(ref);
    }

    /**
     * Computes the variance for the time series array.
     *
     * @param arr Array containing the input time series.
     * @return An array containing the variance in each time series.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array variance(Array arr) throws Exception {
        long ref = variance(arr.getReference());
        return Array.fromNative(ref);
    }

    /**
     * Calculates if the variance of array is greater than the standard deviation. In other words, if the variance of
     * array is larger than 1.
     *
     * @param arr Array containing the input time series.
     * @return An array denoting if the variance of array is greater than the standard deviation.
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array varianceLargerThanStandardDeviation(Array arr) throws Exception {
        long ref = varianceLargerThanStandardDeviation(arr.getReference());
        return Array.fromNative(ref);
    }
}
