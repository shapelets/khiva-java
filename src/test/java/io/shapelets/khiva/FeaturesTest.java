/*
 * Copyright (c) 2018 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FeaturesTest {
    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testCidCe() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Features.cidCe(arrayOfTimeSeries, true)
        ) {

            float[] cidCe = b.getData();
            Assert.assertEquals(cidCe[0], 1.30930734141595, DELTA);
            Assert.assertEquals(cidCe[1], 1.30930734141595, DELTA);
            cidCe = Features.cidCe(arrayOfTimeSeries, false).getData();
            Assert.assertEquals(cidCe[0], 2.23606797749979, DELTA);
            Assert.assertEquals(cidCe[1], 2.23606797749979, DELTA);
        }
    }

    @Test
    public void testC3() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (
                Array arrayOfTimeSeries = new Array(timeSeries, dims);
                Array b = Features.c3(arrayOfTimeSeries, 2)
        ) {

            float[] c3 = b.getData();
            Assert.assertEquals(c3[0], 7.5, DELTA);
            Assert.assertEquals(c3[1], 586.5, DELTA);
        }
    }

    @Test
    public void testAbsSumOfChanges() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 6, 8, 10,
                11, 14, 17, 20};
        long[] dims = {4, 3, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array absSum = Features.absoluteSumOfChanges(a)
        ) {
            double[] absSumR = absSum.getData();
            Assert.assertEquals(absSumR[0], 3, DELTA);
            Assert.assertEquals(absSumR[1], 6, DELTA);
            Assert.assertEquals(absSumR[2], 9, DELTA);
        }
    }

    @Test
    public void testAbsEnergy() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long[] dims = {10, 1, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array absEnergy = Features.absEnergy(a)
        ) {
            double[] result = absEnergy.getData();
            Assert.assertEquals(result[0], 385, DELTA);
        }
    }

    @Test
    public void testApproximateEntropy() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        long[] dims = {10, 2, 1, 1};
        float r = (float) 0.5;

        try (
                Array a = new Array(tss, dims);
                Array b = Features.approximateEntropy(a, 4, r)
        ) {
            double[] approximateEntropy = b.getData();

            Assert.assertEquals(approximateEntropy[0], 0.13484281753639338, DELTA);
            Assert.assertEquals(approximateEntropy[1], 0.13484281753639338, DELTA);
        }
    }

    @Test
    public void testCrossCovariance() throws Exception {
        double[] tss1 = {0, 1, 2, 3, 10, 11, 12, 13};
        double[] tss2 = {4, 6, 8, 10, 12, 14, 16, 18, 20, 22};
        long[] dims1 = {4, 2, 1, 1};
        long[] dims2 = {5, 2, 1, 1};
        try (
                Array a = new Array(tss1, dims1);
                Array b = new Array(tss2, dims2);
                Array c = Features.crossCovariance(a, b, false)
        ) {
            double[] crossCovariance = c.getData();
            for (int i = 0; i < 4; i++) {
                Assert.assertEquals(crossCovariance[(i * 5)], 2.5, DELTA);
                Assert.assertEquals(crossCovariance[(i * 5) + 1], 2.5, DELTA);
                Assert.assertEquals(crossCovariance[(i * 5) + 2], 0.25, DELTA);
                Assert.assertEquals(crossCovariance[(i * 5) + 3], -1.25, DELTA);
                Assert.assertEquals(crossCovariance[(i * 5) + 4], -1.5, DELTA);
            }
        }
    }

    @Test
    public void testAutoCovariance() throws Exception {
        double[] tss = {0, 1, 2, 3, 10, 11, 12, 13};
        long[] dims = {4, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.autoCovariance(a, false)
        ) {
            double[] autoCovariance = b.getData();
            Assert.assertEquals(autoCovariance[0], 1.25, DELTA);
            Assert.assertEquals(autoCovariance[1], 0.3125, DELTA);
            Assert.assertEquals(autoCovariance[2], -0.375, DELTA);
            Assert.assertEquals(autoCovariance[3], -0.5625, DELTA);
            Assert.assertEquals(autoCovariance[4], 1.25, DELTA);
            Assert.assertEquals(autoCovariance[5], 0.3125, DELTA);
            Assert.assertEquals(autoCovariance[6], -0.375, DELTA);
            Assert.assertEquals(autoCovariance[7], -0.5625, DELTA);
        }
    }

    @Test
    public void testCrossCorrelation() throws Exception {
        double[] tss1 = {1, 2, 3, 4};
        double[] tss2 = {4, 6, 8, 10, 12};
        long[] dims1 = {4, 1, 1, 1};
        long[] dims2 = {5, 1, 1, 1};
        try (
                Array a = new Array(tss1, dims1);
                Array b = new Array(tss2, dims2);
                Array c = Features.crossCorrelation(a, b, false)
        ) {
            double[] crossCorrelation = c.getData();
            Assert.assertEquals(crossCorrelation[0], 0.790569415, 1e-9);
            Assert.assertEquals(crossCorrelation[1], 0.790569415, 1e-9);
            Assert.assertEquals(crossCorrelation[2], 0.079056941, 1e-9);
            Assert.assertEquals(crossCorrelation[3], -0.395284707, 1e-9);
            Assert.assertEquals(crossCorrelation[4], -0.474341649, 1e-9);
        }
    }

    @Test
    public void testAutoCorrelation() throws Exception {
        double[] tss = {0, 1, 2, 3, 10, 11, 12, 13};
        long[] dims = {4, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.autoCorrelation(a, 4, false)
        ) {
            double[] autoCorrelationResult = b.getData();
            Assert.assertEquals(autoCorrelationResult[0], 1, DELTA);
            Assert.assertEquals(autoCorrelationResult[1], 0.25, DELTA);
            Assert.assertEquals(autoCorrelationResult[2], -0.3, DELTA);
            Assert.assertEquals(autoCorrelationResult[3], -0.45, DELTA);
            Assert.assertEquals(autoCorrelationResult[4], 1.0, DELTA);
            Assert.assertEquals(autoCorrelationResult[5], 0.25, DELTA);
            Assert.assertEquals(autoCorrelationResult[6], -0.3, DELTA);
            Assert.assertEquals(autoCorrelationResult[7], -0.45, DELTA);
        }
    }

    @Test
    public void testBinnedCorrelation() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                14, 15, 16, 17, 18, 19, 20, 1, 1, 3, 10, 5, 6, 1, 8, 9, 10, 11, 1, 13, 14, 10, 16, 17, 10, 19, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.binnedEntropy(a, 5)
        ) {

            double[] binnedEntropyResult = b.getData();
            Assert.assertEquals(binnedEntropyResult[0], 1.6094379124341005, DELTA);
            Assert.assertEquals(binnedEntropyResult[1], 1.5614694247763998, DELTA);
        }
    }

    @Test
    public void testCountAboveMean() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.countAboveMean(a)
        ) {
            int[] countAboveMeanResult = b.getData();
            Assert.assertEquals(countAboveMeanResult[0], 3, DELTA);
            Assert.assertEquals(countAboveMeanResult[1], 3, DELTA);
        }
    }

    @Test
    public void testCountBelowMean() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.countBelowMean(a)
        ) {
            int[] countBelowMeanResult = b.getData();

            Assert.assertEquals(countBelowMeanResult[0], 3, DELTA);
            Assert.assertEquals(countBelowMeanResult[1], 3, DELTA);
        }
    }

    @Test
    public void testEnergyRatioByChunks() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.energyRatioByChunks(a, 2, 0)
        ) {
            double[] energyRatioByChunksResult = b.getData();

            Assert.assertEquals(energyRatioByChunksResult[0], 0.090909091, DELTA);
            Assert.assertEquals(energyRatioByChunksResult[1], 0.330376940, DELTA);
            energyRatioByChunksResult = Features.energyRatioByChunks(a, 2, 1).getData();
            Assert.assertEquals(energyRatioByChunksResult[0], 0.909090909, DELTA);
            Assert.assertEquals(energyRatioByChunksResult[1], 0.669623060, DELTA);
        }
    }

    @Test
    public void testFirstLocationOfMaximum() throws Exception {
        double[] tss = {5, 4, 3, 5, 0, 1, 5, 3, 2, 1, 2, 4, 3, 5, 2, 5, 4, 3, 5, 2};
        long[] dims = {10, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.firstLocationOfMaximum(a)
        ) {
            double[] firstLocationOfMaximumResult = b.getData();
            Assert.assertEquals(firstLocationOfMaximumResult[0], 0.0, DELTA);
            Assert.assertEquals(firstLocationOfMaximumResult[1], 0.3, DELTA);
        }
    }

    @Test
    public void testFirstLocationOfMinimum() throws Exception {
        double[] tss = {5, 4, 3, 0, 0, 1, 5, 4, 3, 0, 2, 1};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.firstLocationOfMinimum(a)
        ) {
            double[] firstLocationOfMinimumResult = b.getData();
            Assert.assertEquals(firstLocationOfMinimumResult[0], 0.5, DELTA);
            Assert.assertEquals(firstLocationOfMinimumResult[1], 0.5, DELTA);
        }
    }

    @Test
    public void testFriedrichCoefficients() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5};
        long[] dims = {6, 2, 1, 1};
        try (Array a = new Array(tss, dims); Array b = Features.friedrichCoefficients(a, 4, 2)) {
            double[] expected = {-0.0009912563255056738, -0.0027067768387496471, -0.00015192681166809052,
                    0.10512571036815643, 0.89872437715530396,
                    -0.0009912563255056738, -0.0027067768387496471, -0.00015192681166809052,
                    0.10512571036815643, 0.89872437715530396};
            double[] friedrichCoefficientsResult = b.getData();
            Assert.assertArrayEquals(friedrichCoefficientsResult, expected, DELTA);
        }
    }

    @Test
    public void testHasDuplicates() throws Exception {
        double[] tss = {5, 4, 3, 0, 0, 1, 5, 4, 3, 0, 2, 1};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.hasDuplicates(a)
        ) {
            boolean[] hasDuplicatesResult = b.getData();
            Assert.assertTrue(hasDuplicatesResult[0]);
            Assert.assertFalse(hasDuplicatesResult[1]);
        }
    }

    @Test
    public void testHasDuplicateMax() throws Exception {
        double[] tss = {5, 4, 3, 0, 5, 1, 5, 4, 3, 0, 2, 1};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.hasDuplicateMax(a)
        ) {
            boolean[] hasDuplicateMaxResult = b.getData();
            Assert.assertTrue(hasDuplicateMaxResult[0]);
            Assert.assertFalse(hasDuplicateMaxResult[1]);
        }
    }

    @Test
    public void testIndexMaxQuantile() throws Exception {
        double[] tss = {5, 4, 3, 0, 0, 1, 5, 4, 0, 0, 2, 1};
        long[] dims = {6, 2, 1, 1};
        float q = (float) 0.5;

        try (
                Array a = new Array(tss, dims);
                Array b = Features.indexMassQuantile(a, q)
        ) {
            double[] indexMaxQuantileResult = b.getData();
            Assert.assertEquals(indexMaxQuantileResult[0], 0.333333333, DELTA);
            Assert.assertEquals(indexMaxQuantileResult[1], 0.333333333, DELTA);
        }
    }

    @Test
    public void testKurtosis() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 2, 2, 2, 20, 30, 25};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.kurtosis(a)
        ) {
            double[] kurtosisResult = b.getData();
            Assert.assertEquals(kurtosisResult[0], -1.2, DELTA);
            Assert.assertEquals(kurtosisResult[1], -2.66226722, DELTA);
        }
    }

    @Test
    public void testLargeStandardDeviation() throws Exception {
        double[] tss = {-1, -1, -1, 1, 1, 1, 4, 6, 8, 4, 5, 4};
        long[] dims = {6, 2, 1, 1};
        float r = (float) 0.4;
        try (
                Array a = new Array(tss, dims);
                Array b = Features.largeStandardDeviation(a, r)
        ) {
            boolean[] largeStandardDeviationResult = b.getData();
            Assert.assertTrue(largeStandardDeviationResult[0]);
            Assert.assertFalse(largeStandardDeviationResult[1]);
        }
    }

    @Test
    public void testLastLocationOfMaximum() throws Exception {
        double[] tss = {0, 4, 3, 5, 5, 1, 0, 4, 3, 2, 5, 1};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.lastLocationOfMaximum(a)
        ) {
            double[] locationOfMaximumResult = b.getData();
            Assert.assertEquals(locationOfMaximumResult[0], 0.8333333333333334, DELTA);
            Assert.assertEquals(locationOfMaximumResult[1], 0.8333333333333334, DELTA);
        }
    }

    @Test
    public void testLastLocationOfMinimum() throws Exception {
        double[] tss = {0, 4, 3, 5, 5, 1, 0, 4, 3, 2, 5, 1, 4, 5, 1, 2};
        long[] dims = {8, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.lastLocationOfMinimum(a)
        ) {
            double[] locationOfMinimumResult = b.getData();
            Assert.assertEquals(locationOfMinimumResult[0], 0.875, DELTA);
            Assert.assertEquals(locationOfMinimumResult[1], 0.875, DELTA);
        }
    }

    @Test
    public void testLength() throws Exception {
        double[] tss = {0, 4, 3, 5, 5, 1, 0, 4, 3, 2, 5, 1};
        long dims[] = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.length(a)
        ) {
            int[] lengthResult = b.getData();
            Assert.assertEquals(lengthResult[0], 6, DELTA);
            Assert.assertEquals(lengthResult[1], 6, DELTA);
        }
    }

    @Test
    public void testLinearTrend() throws Exception {
        double[] tss = {0, 4, 3, 5, 5, 1, 2, 4, 1, 2, 5, 3};
        long dims[] = {6, 2, 1, 1};
        try (Array a = new Array(tss, dims)) {
            Array[] b = Features.linearTrend(a);
            double[] pvalue = b[0].getData();
            double[] rvalue = b[1].getData();
            double[] intercept = b[2].getData();
            double[] slope = b[3].getData();
            double[] stdrr = b[4].getData();


            Assert.assertEquals(pvalue[0], 0.6260380997892747, DELTA);
            Assert.assertEquals(pvalue[1], 0.5272201945463578, DELTA);

            Assert.assertEquals(rvalue[0], 0.2548235957188128, DELTA);
            Assert.assertEquals(rvalue[1], 0.3268228676411533, DELTA);

            Assert.assertEquals(intercept[0], 2.2857142857142856, DELTA);
            Assert.assertEquals(intercept[1], 2.1904761904761907, DELTA);

            Assert.assertEquals(slope[0], 0.2857142857142857, DELTA);
            Assert.assertEquals(slope[1], 0.2571428571428572, DELTA);

            Assert.assertEquals(stdrr[0], 0.5421047417431507, DELTA);
            Assert.assertEquals(stdrr[1], 0.37179469135129783, DELTA);

            b[0].close();
            b[1].close();
            b[2].close();
            b[3].close();
            b[4].close();
        }
    }

    @Test
    public void testHasDuplicateMin() throws Exception {
        double[] tss = {5, 4, 3, 0, 0, 1, 5, 4, 3, 0, 2, 1};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.hasDuplicateMin(a)
        ) {
            boolean[] hasDuplicateMinResult = b.getData();
            Assert.assertTrue(hasDuplicateMinResult[0]);
            Assert.assertFalse(hasDuplicateMinResult[1]);
        }
    }

    @Test
    public void testLongestStrikeAboveMean() throws Exception {
        double[] tss = {20, 20, 20, 1, 1, 1, 20, 20, 20, 20, 1, 1, 1, 1,
                1, 1, 1, 1, 20, 20, 20, 20, 20, 1, 1, 1, 20, 20,
                20, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.longestStrikeAboveMean(a)
        ) {
            double[] longestStrikeAboveMeanResult = b.getData();
            Assert.assertEquals(longestStrikeAboveMeanResult[0], 4, DELTA);
            Assert.assertEquals(longestStrikeAboveMeanResult[1], 3, DELTA);
        }
    }

    @Test
    public void testLongestStrikeBelowMean() throws Exception {
        double[] tss = {20, 20, 20, 1, 1, 1, 20, 20, 20, 20, 1, 1, 1, 1,
                1, 1, 1, 1, 20, 20, 20, 20, 20, 1, 1, 1, 20, 20,
                20, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.longestStrikeBelowMean(a)
        ) {
            double[] longestStrikeBelowMeanResult = b.getData();
            Assert.assertEquals(longestStrikeBelowMeanResult[0], 8, DELTA);
            Assert.assertEquals(longestStrikeBelowMeanResult[1], 9, DELTA);
        }
    }

    @Test
    public void testMaximum() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20,
                2, 19, 1, 20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.maximum(a)
        ) {
            double[] maximumResult = b.getData();
            Assert.assertEquals(maximumResult[0], 50, DELTA);
            Assert.assertEquals(maximumResult[1], 30, DELTA);
        }
    }

    @Test
    public void testMeanAbsoluteChange() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 8, 10, 12, 14, 16, 18};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.meanAbsoluteChange(a)
        ) {
            double[] meanAbsoluteResult = b.getData();
            Assert.assertEquals(meanAbsoluteResult[0], (float) 5 / 6, DELTA);
            Assert.assertEquals(meanAbsoluteResult[1], (float) 10 / 6, DELTA);
        }
    }

    @Test
    public void testFftCoefficient() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (Array a = new Array(tss, dims)) {
            Array[] b = Features.fftCoefficient(a, 0);

            double[] real = b[0].getData();
            double[] imag = b[1].getData();
            double[] abs = b[2].getData();
            double[] angle = b[3].getData();


            Assert.assertEquals(real[0], 15, DELTA);
            Assert.assertEquals(real[1], 51, DELTA);

            Assert.assertEquals(imag[0], 0, DELTA);
            Assert.assertEquals(imag[1], 0, DELTA);

            Assert.assertEquals(abs[0], 15, DELTA);
            Assert.assertEquals(abs[1], 51, DELTA);

            Assert.assertEquals(angle[0], 0, DELTA);
            Assert.assertEquals(angle[1], 0, DELTA);

            b[0].close();
            b[1].close();
            b[2].close();
            b[3].close();
        }
    }

    @Test
    public void testAggregatedAutocorrelationMean() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.aggregatedAutocorrelation(a, 0)
        ) {
            double[] aggregatedAutocorrelationResult = b.getData();
            Assert.assertEquals(aggregatedAutocorrelationResult[0], -0.6571428571428571, DELTA);
            Assert.assertEquals(aggregatedAutocorrelationResult[1], -0.6571428571428571, DELTA);
        }
    }

    @Test
    public void testAggregatedAutocorrelationMedian() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.aggregatedAutocorrelation(a, 1)
        ) {
            double[] aggregatedAutocorrelationResult = b.getData();
            Assert.assertEquals(aggregatedAutocorrelationResult[0], -0.54285717010498047, DELTA);
            Assert.assertEquals(aggregatedAutocorrelationResult[1], -0.54285717010498047, DELTA);
        }
    }

    @Test
    public void testAggregatedAutocorrelationMin() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.aggregatedAutocorrelation(a, 2)
        ) {
            double[] aggregatedAutocorrelationResult = b.getData();
            Assert.assertEquals(aggregatedAutocorrelationResult[0], -2.142857142857143, DELTA);
            Assert.assertEquals(aggregatedAutocorrelationResult[1], -2.142857142857143, DELTA);
        }
    }

    @Test
    public void testAggregatedAutocorrelationMax() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.aggregatedAutocorrelation(a, 3)
        ) {
            double[] aggregatedAutocorrelationResult = b.getData();
            Assert.assertEquals(aggregatedAutocorrelationResult[0], 0.6, DELTA);
            Assert.assertEquals(aggregatedAutocorrelationResult[1], 0.6, DELTA);
        }
    }

    @Test
    public void testAggregatedAutocorrelationStdev() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.aggregatedAutocorrelation(a, 4)
        ) {
            double[] aggregatedAutocorrelationResult = b.getData();
            Assert.assertEquals(aggregatedAutocorrelationResult[0], 0.9744490855905009, DELTA);
            Assert.assertEquals(aggregatedAutocorrelationResult[1], 0.9744490855905009, DELTA);
        }
    }

    @Test
    public void testAggregatedAutocorrelationVar() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.aggregatedAutocorrelation(a, 5)
        ) {
            double[] aggregatedAutocorrelationResult = b.getData();
            Assert.assertEquals(aggregatedAutocorrelationResult[0], 0.9495510204081633, DELTA);
            Assert.assertEquals(aggregatedAutocorrelationResult[1], 0.9495510204081633, DELTA);
        }
    }

    @Test
    public void testAggregatedLinearTrendMean() throws Exception {
        double[] tss = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5};
        long[] dims = {12, 1, 1, 1};
        try (Array a = new Array(tss, dims)) {
            Array[] b = Features.aggregatedLinearTrend(a, 3, 0);

            double[] pvalue = b[0].getData();
            double[] rvalue = b[1].getData();
            double[] intercept = b[2].getData();
            double[] slope = b[3].getData();
            double[] stdrr = b[4].getData();


            Assert.assertEquals(pvalue[0], 1, DELTA);
            Assert.assertEquals(rvalue[0], 2, DELTA);
            Assert.assertEquals(intercept[0], 1, DELTA);
            Assert.assertEquals(slope[0], 0, DELTA);
            Assert.assertEquals(stdrr[0], 0, DELTA);

            b[0].close();
            b[1].close();
            b[2].close();
            b[3].close();
            b[4].close();
        }
    }

    @Test
    public void testAggregatedLinearTrendMin() throws Exception {
        double[] tss = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5};
        long[] dims = {12, 1, 1, 1};
        try (Array a = new Array(tss, dims)) {
            Array[] b = Features.aggregatedLinearTrend(a, 3, 1);

            double[] pvalue = b[0].getData();
            double[] rvalue = b[1].getData();
            double[] intercept = b[2].getData();
            double[] slope = b[3].getData();
            double[] stdrr = b[4].getData();


            Assert.assertEquals(pvalue[0], 1, DELTA);
            Assert.assertEquals(rvalue[0], 2, DELTA);
            Assert.assertEquals(intercept[0], 1, DELTA);
            Assert.assertEquals(slope[0], 0, DELTA);
            Assert.assertEquals(stdrr[0], 0, DELTA);

            b[0].close();
            b[1].close();
            b[2].close();
            b[3].close();
            b[4].close();
        }
    }

    @Test
    public void testCwtCoefficients() throws Exception {
        float[] tss = {0.1f, 0.2f, 0.3f, 0.1f, 0.2f, 0.3f};
        long[] dims = {3, 2, 1, 1};
        int[] width = {1, 2, 3};
        long[] dimsW = {3, 1, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array w = new Array(width, dimsW);
                Array b = Features.cwtCoefficients(a, w, 2, 2)
        ) {
            float[] cwtCoefficientsResult = b.getData();
            Assert.assertEquals(cwtCoefficientsResult[0], 0.26, 1e-2);
            Assert.assertEquals(cwtCoefficientsResult[1], 0.26, 1e-2);
        }
    }

    @Test
    public void testMeanSecondDerivativeCentral() throws Exception {
        double[] tss = {1, 3, 7, 4, 8, 2, 5, 1, 7, 4};
        long[] dims = {5, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.meanSecondDerivativeCentral(a)
        ) {
            double[] meanSecondDerivativeCentralResult = b.getData();
            Assert.assertEquals(meanSecondDerivativeCentralResult[0], (float) 1.0 / 5.0, DELTA);
            Assert.assertEquals(meanSecondDerivativeCentralResult[1], (float) -3.0 / 5.0, DELTA);
        }
    }

    @Test
    public void testMinimum() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 13, 15, 5, 16, 20, 20, 20, 20,
                20, 2, 19, 4, 20, 20, 20, 4, 15, 6, 30, 7, 9, 18, 4, 10, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.minimum(a)
        ) {
            double[] minimumResult = b.getData();
            Assert.assertEquals(minimumResult[0], 1, DELTA);
            Assert.assertEquals(minimumResult[1], 2, DELTA);
        }
    }

    @Test
    public void testNumberCrossingM() throws Exception {
        double[] tss = {1, 2, 1, 1, -3, -4, 7, 8, 9, 10, -2, 1, -3, 5, 6, 7, -10, 1, 2, 1, 1, -3, -4, 7, 8, 9,
                10, -2, 1, -3, 5, 6, 7, -10};
        long[] dims = {17, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.numberCrossingM(a, 0)
        ) {
            double[] numberCrossingMResult = b.getData();
            Assert.assertEquals(numberCrossingMResult[0], 7, DELTA);
            Assert.assertEquals(numberCrossingMResult[1], 7, DELTA);
        }
    }

    @Test
    public void testMedian() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 13, 15, 5, 16, 20, 20, 20, 20, 20,
                2, 19, 4, 20, 20, 20, 4, 15, 6, 30, 7, 9, 18, 4, 10, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.median(a)
        ) {
            double[] medianResult = b.getData();
            Assert.assertEquals(medianResult[0], 20, DELTA);
            Assert.assertEquals(medianResult[1], 18.5, DELTA);
        }
    }

    @Test
    public void testMean() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2,
                19, 1, 20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.mean(a)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 18.55, DELTA);
            Assert.assertEquals(result[1], 12.7, DELTA);
        }
    }

    @Test
    public void testMeanChange() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 8, 10, 12, 14, 16, 18};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.meanChange(a)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], (float) 5 / 6, DELTA);
            Assert.assertEquals(result[1], (float) 10 / 6, DELTA);
        }
    }

    @Test
    public void testMaxLangevinFixedPoint() throws Exception {
        float[] tss = {0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5};
        long[] dims = {6, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.maxLangevinFixedPoint(a, 7, 2)
        ) {
            float[] result = b.getData();
            Assert.assertEquals(result[0], 4.562970585, 1e-4);
            Assert.assertEquals(result[1], 4.562970585, 1e-4);
        }

    }

    @Test
    public void testNumberCwtPeaks() throws Exception {
        double[] tss = {1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1};
        long[] dims = {21, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.numberCwtPeaks(a, 2)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 2, DELTA);
            Assert.assertEquals(result[1], 2, DELTA);
        }
    }

    @Test
    public void testNumberPeaks() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 0, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.numberPeaks(a, 2)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 1, DELTA);
            Assert.assertEquals(result[1], 1, DELTA);
        }
    }

    @Test
    public void testFftAggregated() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        long[] dims = {10, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.fftAggregated(a)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 1.135143, DELTA);
            Assert.assertEquals(result[1], 2.368324, DELTA);
            Assert.assertEquals(result[2], 1.248777, DELTA);
            Assert.assertEquals(result[3], 3.642666, DELTA);

            Assert.assertEquals(result[4], 1.135143, DELTA);
            Assert.assertEquals(result[5], 2.368324, DELTA);
            Assert.assertEquals(result[6], 1.248777, DELTA);
            Assert.assertEquals(result[7], 3.642666, DELTA);
        }
    }

    @Test
    public void testPartialAutocorrelation() throws Exception {
        double len = 3000.0f;
        double[] input = new double[(int) (2 * len)];
        double step = 1.0f / (len - 1);
        for (int i = 0; i < 2 * len; i++) {
            input[i] = step * (i % len);
        }

        long[] dims = {3000, 2, 1, 1};
        int[] lags = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        long[] dimsLags = {10, 1, 1, 1};

        try (
                Array a = new Array(input, dims);
                Array b = new Array(lags, dimsLags);
                Array c = Features.partialAutocorrelation(a, b)
        ) {

            double[] expected = {1.0, 0.9993331432342529, -0.0006701064994559, -0.0006701068487018, -0.0008041285327636,
                    -0.0005360860959627, -0.0007371186511591, -0.0004690756904893, -0.0008041299879551,
                    -0.0007371196406893, 1.0, 0.9993331432342529, -0.0006701064994559, -0.0006701068487018, -0.0008041285327636,
                    -0.0005360860959627, -0.0007371186511591, -0.0004690756904893, -0.0008041299879551,
                    -0.0007371196406893};
            double[] result = c.getData();
            Assert.assertArrayEquals(result, expected, 1e-3f);
        }
    }

    @Test
    public void testPercentageOfReocurringDatapointsToAllDatapoints() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 0, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.percentageOfReoccurringDatapointsToAllDatapoints(a, false)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 0.25, DELTA);
            Assert.assertEquals(result[1], 0.25, DELTA);
        }
    }

    @Test
    public void testPercentageOfReocurringValuesToAllValues() throws Exception {
        double[] tss = {1, 1, 2, 3, 4, 4, 5, 6, 1, 2, 2, 3, 4, 5, 6, 7};
        long[] dims = {8, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.percentageOfReoccurringValuesToAllValues(a, false)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 4.0 / 8.0, DELTA);
            Assert.assertEquals(result[1], 2.0 / 8.0, DELTA);
        }
    }

    @Test
    public void testQuantile() throws Exception {
        double[] tss = {0, 0, 0, 0, 3, 4, 13, 0, 0, 0, 0, 3, 4, 13};
        double[] q = {0.6};
        long[] dims = {7, 2, 1, 1};
        long[] dimsQ = {1, 1, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array arrayQ = new Array(q, dimsQ);
                Array b = Features.quantile(a, arrayQ);
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 1.79999999, DELTA);
            Assert.assertEquals(result[1], 1.79999999, DELTA);
        }
    }

    @Test
    public void testRangeCount() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 5, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.rangeCount(a, 2, 12)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 2, DELTA);
            Assert.assertEquals(result[1], 3, DELTA);
        }
    }

    @Test
    public void testRatioBeyondRSigma() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 0, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.ratioBeyondRSigma(a, (float) 0.5)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 0.7142857142857143, DELTA);
            Assert.assertEquals(result[1], 0.7142857142857143, DELTA);
        }
    }

    @Test
    public void testRatioValueNumberToTimeSeriesLength() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 5, 0, 4, 6, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.ratioValueNumberToTimeSeriesLength(a)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 4.0 / 7.0, DELTA);
            Assert.assertEquals(result[1], 6.0 / 7.0, DELTA);
        }
    }

    @Test
    public void testSampleEntropy() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 0, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.sampleEntropy(a)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 1.252762968495368, DELTA);
            Assert.assertEquals(result[1], 1.252762968495368, DELTA);
        }
    }

    @Test
    public void testSkewness() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 0, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.skewness(a)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 2.038404735373753, DELTA);
            Assert.assertEquals(result[1], 2.038404735373753, DELTA);
        }
    }

    @Test
    public void testSpktWelchDensity() throws Exception {
        double[] tss = {0, 1, 1, 3, 4, 5, 6, 7, 8, 9, 0, 1, 1, 3, 4, 5, 6, 7, 8, 9};
        long[] dims = {10, 2, 1, 1};
        try (Array a = new Array(tss, dims)) {
            float[] result = Features.spktWelchDensity(a, 0).getData();
            Assert.assertEquals(result[0], 1.6666667461395264, DELTA);
            Assert.assertEquals(result[1], 1.6666667461395264, DELTA);
        }
    }

    @Test
    public void testStandardDeviation() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2,
                19, 1, 20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.standardDeviation(a)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 12.363150892875165, DELTA);
            Assert.assertEquals(result[1], 9.51367436903324, DELTA);
        }
    }

    @Test
    public void testSumOfReoccurringDatapoints() throws Exception {
        double[] tss = {3, 3, 0, 4, 0, 13, 13, 3, 3, 0, 4, 0, 13, 13};
        long[] dims = {7, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.sumOfReoccurringDatapoints(a, false)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 32, DELTA);
            Assert.assertEquals(result[1], 32, DELTA);
        }
    }

    @Test
    public void testSumOfReoccurringValues() throws Exception {
        double[] tss = {4, 4, 6, 6, 7, 4, 7, 7, 8, 8};
        long[] dims = {5, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.sumOfReoccurringValues(a, false)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 10, DELTA);
            Assert.assertEquals(result[1], 15, DELTA);
        }
    }

    @Test
    public void testSumValues() throws Exception {
        double[] tss = {1, 2, 3, 4.1, -1.2, -2, -3, -4};
        long[] dims = {4, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.sumValues(a)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 10.1, DELTA);
            Assert.assertEquals(result[1], -10.2, DELTA);
        }
    }

    @Test
    public void testSymmetryLooking() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2,
                19, 1, 20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.symmetryLooking(a, (float) 0.1)
        ) {
            boolean[] result = b.getData();
            Assert.assertTrue(result[0]);
            Assert.assertFalse(result[1]);
        }
    }

    @Test
    public void testTimeReversalAsymmetryStatistic() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                20, 20, 20, 2, 19, 1, 20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};

        try (
                Array a = new Array(tss, dims);
                Array b = Features.timeReversalAsymmetryStatistic(a, 2)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 1052, DELTA);
            Assert.assertEquals(result[1], -150.625, DELTA);
        }
    }

    @Test
    public void testValueCount() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2,
                19, 1, 20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.valueCount(a, 20)
        ) {
            int[] result = b.getData();
            Assert.assertEquals(result[0], 9, DELTA);
            Assert.assertEquals(result[1], 8, DELTA);
        }
    }

    @Test
    public void testVariance() throws Exception {
        double[] tss = {1, 1, -1, -1, 1, 2, -2, -1};
        long[] dims = {4, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.variance(a)
        ) {
            double[] result = b.getData();
            Assert.assertEquals(result[0], 1.0, DELTA);
            Assert.assertEquals(result[1], 2.5, DELTA);
        }
    }

    @Test
    public void testVarianceLargerThanStandardDeviation() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2,
                19, 1, 20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (
                Array a = new Array(tss, dims);
                Array b = Features.varianceLargerThanStandardDeviation(a)
        ) {
            boolean[] result = b.getData();
            Assert.assertTrue(result[0]);
            Assert.assertTrue(result[1]);
        }
    }
}
