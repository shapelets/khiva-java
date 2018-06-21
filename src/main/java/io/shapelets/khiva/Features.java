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
 * Khiva Features class containing a number of features that can be extracted from time series. All the methods
 * operate with instances of the ARRAY class as input and output.
 */
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

    private native static long[] friedrichCoefficients(long ref, int m, float r);

    private native static long[] hasDuplicates(long ref);

    private native static long[] hasDuplicateMax(long ref);

    private native static long[] hasDuplicateMin(long ref);

    private native static long[] indexMassQuantile(long ref, float q);

    private native static long[] kurtosis(long ref);

    private native static long[] largeStandardDeviation(long ref, float r);

    private native static long[] lastLocationOfMaximum(long ref)
            ;

    private native static long[] lastLocationOfMinimum(long ref);

    private native static long[] length(long ref);

    private native static long[] linearTrend(long ref);

    private native static long[] localMaximals(long ref);

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

    private native static long[] numberCwtPeaks(long ref, int maxW);

    private native static long[] numberPeaks(long ref, int n);

    private native static long[] partialAutocorrelation(long ref, long refLags);

    private native static long[] percentageOfReoccurringDatapointsToAllDatapoints(long ref, boolean isSorted);

    private native static long[] percentageOfReoccurringValuesToAllValues(long ref, boolean isSorted);

    private native static long[] quantile(long ref, long refQ, float precision);

    private native static long[] rangeCount(long ref, float min, float max);

    private native static long[] ratioBeyondRSigma(long ref, float r);

    private native static long[] ratioValueNumberToTimeSeriesLength(long ref);

    private native static long[] sampleEntropy(long ref);

    private native static long[] skewness(long ref);

    private native static long[] spktWelchDensity(long ref, int coeff);

    private native static long[] standardDeviation(long ref);

    private native static long[] sumOfReoccurringDatapoints(long ref, boolean isSorted);

    private native static long[] sumOfReoccurringValues(long ref, boolean isSorted);

    private native static long[] sumValues(long ref);

    private native static long[] symmetryLooking(long ref, float r);

    private native static long[] timeReversalAsymmetryStatistic(long ref, int lag);

    private native static long[] valueCount(long ref, float v);

    private native static long[] variance(long ref);

    private native static long[] varianceLargerThanStandardDeviation(long ref);

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
        return new Array[]{new Array(refs[1]), new Array(refs[2]), new Array(refs[3]), new Array(refs[4]), new Array(refs[5])};

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
        return new Array[]{new Array(refs[1]), new Array(refs[2]), new Array(refs[3]), new Array(refs[4])};
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
     */
    public static Array friedrichCoefficients(Array arr, int m, float r) {
        long[] refs = friedrichCoefficients(arr.getReference(), m, r);
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
     * Calculates the index of the mass quantile.
     *
     * @param arr Array containing the input time series.
     * @param q   The quantile.
     * @return The index of the mass quantile q.
     */
    public static Array indexMassQuantile(Array arr, float q) {
        long[] refs = indexMassQuantile(arr.getReference(), q);
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
        return new Array[]{new Array(refs[1]), new Array(refs[2]), new Array(refs[3]), new Array(refs[4]), new Array(refs[5])};
    }

    /**
     * Calculates all Local Maximals fot the time series in arr.
     *
     * @param arr Array containing the input time series.
     * @return The calculated local maximals for each time series in array.
     */
    public static Array localMaximals(Array arr) {
        long[] refs = localMaximals(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
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
     * This feature calculator searches for different peaks. To do so, the time series is smoothed by a ricker
     * wavelet and for widths ranging from 1 to maxW. This feature calculator returns the number of peaks that occur at
     * enough width scales and with sufficiently high Signal-to-Noise-Ratio (SNR).
     *
     * @param arr  Array containing the input time series.
     * @param maxW The maximum width to consider.
     * @return The number of peaks for each time series.
     */
    public static Array numberCwtPeaks(Array arr, int maxW) {
        long[] refs = numberCwtPeaks(arr.getReference(), maxW);
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
     */
    public static Array partialAutocorrelation(Array arr, Array arrLags) {
        long[] refs = partialAutocorrelation(arr.getReference(), arrLags.getReference());
        arr.setReference(refs[0]);
        arrLags.setReference(refs[1]);
        return new Array(refs[2]);
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
     */
    public static Array percentageOfReoccurringValuesToAllValues(Array arr, boolean isSorted) {
        long[] refs = percentageOfReoccurringValuesToAllValues(arr.getReference(), isSorted);
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
     * Counts observed values within the interval [min, max).
     *
     * @param arr Array containing the input time series.
     * @param min Value that sets the lower limit.
     * @param max Value that sets the upper limit.
     * @return Values at the given range.
     */
    public static Array rangeCount(Array arr, float min, float max) {
        long[] refs = rangeCount(arr.getReference(), min, max);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
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
     * Calculates a factor which is 1 if all values in the time series occur only once, and below one if this is
     * not the case. In principle, it just returns:
     * <p>
     * \[
     * \frac{\textit{number_unique_values}}{\textit{number_values}}
     * \]
     *
     * @param arr Array containing the input time series.
     * @return The ratio of unique values with respect to the total number of values.
     */
    public static Array ratioValueNumberToTimeSeriesLength(Array arr) {
        long[] refs = ratioValueNumberToTimeSeriesLength(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates a vectorized sample entropy algorithm.
     * https://en.wikipedia.org/wiki/Sample_entropy
     * https://www.ncbi.nlm.nih.gov/pubmed/10843903?dopt=Abstract
     * For short time-series this method is highly dependent on the parameters, but should be stable for N &gt; 2000,
     * see: Yentes et al. (2012) - The Appropriate Use of Approximate Entropy and Sample Entropy with Short Data Sets
     * Other shortcomings and alternatives discussed in:
     * Richman {@literal &} Moorman (2000) - Physiological time-series analysis using approximate entropy and sample entropy.
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
     */
    public static Array spktWelchDensity(Array arr, int coeff) {
        long[] refs = spktWelchDensity(arr.getReference(), coeff);
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
     * Calculates the sum of all values, that are present in the time series more than once.
     *
     * @param arr Array containing the input time series.
     * @return Array containing the the sum of all values, that are present in the time series more than once.
     */
    public static Array sumOfReoccurringValues(Array arr) {
        long[] refs = sumOfReoccurringValues(arr.getReference(), false);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the sum of all values, that are present in the time series more than once.
     *
     * @param arr      Array containing the input time series.
     * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
     * @return Array containing the the sum of all values, that are present in the time series more than once.
     */
    public static Array sumOfReoccurringValues(Array arr, boolean isSorted) {
        long[] refs = sumOfReoccurringValues(arr.getReference(), isSorted);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates the sum over the time series array.
     *
     * @param arr Array containing the input time series.
     * @return Srray containing the sum of values in each time series.
     */
    public static Array sumValues(Array arr) {
        long[] refs = sumValues(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
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
     */
    public static Array symmetryLooking(Array arr, float r) {
        long[] refs = symmetryLooking(arr.getReference(), r);
        arr.setReference(refs[0]);
        return new Array(refs[1]);
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
     */
    public static Array timeReversalAsymmetryStatistic(Array arr, int lag) {
        long[] refs = timeReversalAsymmetryStatistic(arr.getReference(), lag);
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

    /**
     * Computes the variance for the time series array.
     *
     * @param arr Array containing the input time series.
     * @return An array containing the variance in each time series.
     */
    public static Array variance(Array arr) {
        long[] refs = variance(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }

    /**
     * Calculates if the variance of array is greater than the standard deviation. In other words, if the variance of
     * array is larger than 1.
     *
     * @param arr Array containing the input time series.
     * @return An array denoting if the variance of array is greater than the standard deviation.
     */
    public static Array varianceLargerThanStandardDeviation(Array arr) {
        long[] refs = varianceLargerThanStandardDeviation(arr.getReference());
        arr.setReference(refs[0]);
        return new Array(refs[1]);
    }
}
