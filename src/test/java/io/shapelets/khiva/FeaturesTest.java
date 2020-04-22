/*
 * Copyright (c) 2019 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeaturesTest {
    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() throws Exception {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testCidCe() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (Array arrayOfTimeSeries = Array.fromPrimitiveArray(timeSeries, dims);
             Array b = Features.cidCe(arrayOfTimeSeries, true)) {
            float[] cidCe = b.getData();
            assertEquals(1.30930734141595, cidCe[0], DELTA);
            assertEquals(1.30930734141595, cidCe[1], DELTA);
            cidCe = Features.cidCe(arrayOfTimeSeries, false).getData();
            assertEquals(2.23606797749979, cidCe[0], DELTA);
            assertEquals(2.23606797749979, cidCe[1], DELTA);
        }
    }

    @Test
    public void testC3() throws Exception {
        float[] timeSeries = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (Array arrayOfTimeSeries = Array.fromPrimitiveArray(timeSeries, dims);
             Array b = Features.c3(arrayOfTimeSeries, 2)) {

            float[] c3 = b.getData();
            assertEquals(7.5, c3[0], DELTA);
            assertEquals(586.5, c3[1], DELTA);
        }
    }

    @Test
    public void testAbsSumOfChanges() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 6, 8, 10, 11, 14, 17, 20};
        long[] dims = {4, 3, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array absSum = Features.absoluteSumOfChanges(a)) {
            double[] absSumR = absSum.getData();
            assertEquals(3, absSumR[0], DELTA);
            assertEquals(6, absSumR[1], DELTA);
            assertEquals(9, absSumR[2], DELTA);
        }
    }

    @Test
    public void testAbsEnergy() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long[] dims = {10, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array absEnergy = Features.absEnergy(a)) {
            double[] result = absEnergy.getData();
            assertEquals(385, result[0], DELTA);
        }
    }

    @Test
    public void testApproximateEntropy() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        long[] dims = {10, 2, 1, 1};
        float r = (float) 0.5;

        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.approximateEntropy(a, 4, r)) {
            double[] approximateEntropy = b.getData();
            assertEquals(0.13484281753639338, approximateEntropy[0], DELTA);
            assertEquals(0.13484281753639338, approximateEntropy[1], DELTA);
        }
    }

    @Test
    public void testCrossCovariance() throws Exception {
        double[] tss1 = {0, 1, 2, 3, 10, 11, 12, 13};
        double[] tss2 = {4, 6, 8, 10, 12, 14, 16, 18, 20, 22};
        long[] dims1 = {4, 2, 1, 1};
        long[] dims2 = {5, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss1, dims1); Array b = Array.fromPrimitiveArray(tss2, dims2);
             Array c = Features.crossCovariance(a, b, false)) {
            double[] crossCovariance = c.getData();
            for (int i = 0; i < 4; i++) {
                assertEquals(2.5, crossCovariance[(i * 5)], DELTA);
                assertEquals(2.5, crossCovariance[(i * 5) + 1], DELTA);
                assertEquals(0.25, crossCovariance[(i * 5) + 2], DELTA);
                assertEquals(-1.25, crossCovariance[(i * 5) + 3], DELTA);
                assertEquals(-1.5, crossCovariance[(i * 5) + 4], DELTA);
            }
        }
    }

    @Test
    public void testAutoCovariance() throws Exception {
        double[] tss = {0, 1, 2, 3, 10, 11, 12, 13};
        long[] dims = {4, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.autoCovariance(a, false)) {
            double[] autoCovariance = b.getData();
            assertEquals(1.25, autoCovariance[0], DELTA);
            assertEquals(0.3125, autoCovariance[1], DELTA);
            assertEquals(-0.375, autoCovariance[2], DELTA);
            assertEquals(-0.5625, autoCovariance[3], DELTA);
            assertEquals(1.25, autoCovariance[4], DELTA);
            assertEquals(0.3125, autoCovariance[5], DELTA);
            assertEquals(-0.375, autoCovariance[6], DELTA);
            assertEquals(-0.5625, autoCovariance[7], DELTA);
        }
    }

    @Test
    public void testCrossCorrelation() throws Exception {
        double[] tss1 = {1, 2, 3, 4};
        double[] tss2 = {4, 6, 8, 10, 12};
        long[] dims1 = {4, 1, 1, 1};
        long[] dims2 = {5, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss1, dims1); Array b = Array.fromPrimitiveArray(tss2, dims2);
             Array c = Features.crossCorrelation(a, b, false)) {
            double[] crossCorrelation = c.getData();
            assertEquals(0.790569415, crossCorrelation[0], 1e-9);
            assertEquals(0.790569415, crossCorrelation[1], 1e-9);
            assertEquals(0.079056941, crossCorrelation[2], 1e-9);
            assertEquals(-0.395284707, crossCorrelation[3], 1e-9);
            assertEquals(-0.474341649, crossCorrelation[4], 1e-9);
        }
    }

    @Test
    public void testAutoCorrelation() throws Exception {
        double[] tss = {0, 1, 2, 3, 10, 11, 12, 13};
        long[] dims = {4, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.autoCorrelation(a, 4, false)) {
            double[] autoCorrelationResult = b.getData();
            assertEquals(1, autoCorrelationResult[0], DELTA);
            assertEquals(0.25, autoCorrelationResult[1], DELTA);
            assertEquals(-0.3, autoCorrelationResult[2], DELTA);
            assertEquals(-0.45, autoCorrelationResult[3], DELTA);
            assertEquals(1.0, autoCorrelationResult[4], DELTA);
            assertEquals(0.25, autoCorrelationResult[5], DELTA);
            assertEquals(-0.3, autoCorrelationResult[6], DELTA);
            assertEquals(-0.45, autoCorrelationResult[7], DELTA);
        }
    }

    @Test
    public void testBinnedCorrelation() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 1, 3, 10, 5, 6, 1, 8,
                        9, 10, 11, 1, 13, 14, 10, 16, 17, 10, 19, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.binnedEntropy(a, 5)) {

            double[] binnedEntropyResult = b.getData();
            assertEquals(1.6094379124341005, binnedEntropyResult[0], DELTA);
            assertEquals(1.5614694247763998, binnedEntropyResult[1], DELTA);
        }
    }

    @Test
    public void testCountAboveMean() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.countAboveMean(a)) {
            int[] countAboveMeanResult = b.getData();
            assertEquals(3, countAboveMeanResult[0], DELTA);
            assertEquals(3, countAboveMeanResult[1], DELTA);
        }
    }

    @Test
    public void testCountBelowMean() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.countBelowMean(a)) {
            int[] countBelowMeanResult = b.getData();

            assertEquals(3, countBelowMeanResult[0], DELTA);
            assertEquals(3, countBelowMeanResult[1], DELTA);
        }
    }

    @Test
    public void testEnergyRatioByChunks() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.energyRatioByChunks(a, 2, 0)) {
            double[] energyRatioByChunksResult = b.getData();

            assertEquals(0.090909091, energyRatioByChunksResult[0], DELTA);
            assertEquals(0.330376940, energyRatioByChunksResult[1], DELTA);
            energyRatioByChunksResult = Features.energyRatioByChunks(a, 2, 1).getData();
            assertEquals(0.909090909, energyRatioByChunksResult[0], DELTA);
            assertEquals(0.669623060, energyRatioByChunksResult[1], DELTA);
        }
    }

    @Test
    public void testFirstLocationOfMaximum() throws Exception {
        double[] tss = {5, 4, 3, 5, 0, 1, 5, 3, 2, 1, 2, 4, 3, 5, 2, 5, 4, 3, 5, 2};
        long[] dims = {10, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.firstLocationOfMaximum(a)) {
            double[] firstLocationOfMaximumResult = b.getData();
            assertEquals(0.0, firstLocationOfMaximumResult[0], DELTA);
            assertEquals(0.3, firstLocationOfMaximumResult[1], DELTA);
        }
    }

    @Test
    public void testFirstLocationOfMinimum() throws Exception {
        double[] tss = {5, 4, 3, 0, 0, 1, 5, 4, 3, 0, 2, 1};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.firstLocationOfMinimum(a)) {
            double[] firstLocationOfMinimumResult = b.getData();
            assertEquals(0.5, firstLocationOfMinimumResult[0], DELTA);
            assertEquals(0.5, firstLocationOfMinimumResult[1], DELTA);
        }
    }

    @Test
    public void testFriedrichCoefficients() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.friedrichCoefficients(a, 4, 2)) {
            double[] expected = {-0.0009912563255056738, -0.0027067768387496471, -0.00015192681166809052,
                                 0.10512571036815643, 0.89872437715530396, -0.0009912563255056738,
                                 -0.0027067768387496471, -0.00015192681166809052, 0.10512571036815643,
                                 0.89872437715530396};
            double[] friedrichCoefficientsResult = b.getData();
            assertArrayEquals(expected, friedrichCoefficientsResult, DELTA);
        }
    }

    @Test
    public void testHasDuplicates() throws Exception {
        double[] tss = {5, 4, 3, 0, 0, 1, 5, 4, 3, 0, 2, 1};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.hasDuplicates(a)) {
            boolean[] hasDuplicatesResult = b.getData();
            assertTrue(hasDuplicatesResult[0]);
            assertFalse(hasDuplicatesResult[1]);
        }
    }

    @Test
    public void testHasDuplicateMax() throws Exception {
        double[] tss = {5, 4, 3, 0, 5, 1, 5, 4, 3, 0, 2, 1};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.hasDuplicateMax(a)) {
            boolean[] hasDuplicateMaxResult = b.getData();
            assertTrue(hasDuplicateMaxResult[0]);
            assertFalse(hasDuplicateMaxResult[1]);
        }
    }

    @Test
    public void testIndexMaxQuantile() throws Exception {
        double[] tss = {5, 4, 3, 0, 0, 1, 5, 4, 0, 0, 2, 1};
        long[] dims = {6, 2, 1, 1};
        float q = (float) 0.5;

        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.indexMassQuantile(a, q)) {
            double[] indexMaxQuantileResult = b.getData();
            assertEquals(0.333333333, indexMaxQuantileResult[0], DELTA);
            assertEquals(0.333333333, indexMaxQuantileResult[1], DELTA);
        }
    }

    @Test
    public void testKurtosis() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 2, 2, 2, 20, 30, 25};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.kurtosis(a)) {
            double[] kurtosisResult = b.getData();
            assertEquals(-1.2, kurtosisResult[0], DELTA);
            assertEquals(-2.66226722, kurtosisResult[1], DELTA);
        }
    }

    @Test
    public void testLargeStandardDeviation() throws Exception {
        double[] tss = {-1, -1, -1, 1, 1, 1, 4, 6, 8, 4, 5, 4};
        long[] dims = {6, 2, 1, 1};
        float r = (float) 0.4;
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.largeStandardDeviation(a, r)) {
            boolean[] largeStandardDeviationResult = b.getData();
            assertTrue(largeStandardDeviationResult[0]);
            assertFalse(largeStandardDeviationResult[1]);
        }
    }

    @Test
    public void testLastLocationOfMaximum() throws Exception {
        double[] tss = {0, 4, 3, 5, 5, 1, 0, 4, 3, 2, 5, 1};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.lastLocationOfMaximum(a)) {
            double[] locationOfMaximumResult = b.getData();
            assertEquals(0.8333333333333334, locationOfMaximumResult[0], DELTA);
            assertEquals(0.8333333333333334, locationOfMaximumResult[1], DELTA);
        }
    }

    @Test
    public void testLastLocationOfMinimum() throws Exception {
        double[] tss = {0, 4, 3, 5, 5, 1, 0, 4, 3, 2, 5, 1, 4, 5, 1, 2};
        long[] dims = {8, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.lastLocationOfMinimum(a)) {
            double[] locationOfMinimumResult = b.getData();
            assertEquals(0.875, locationOfMinimumResult[0], DELTA);
            assertEquals(0.875, locationOfMinimumResult[1], DELTA);
        }
    }

    @Test
    public void testLength() throws Exception {
        double[] tss = {0, 4, 3, 5, 5, 1, 0, 4, 3, 2, 5, 1};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.length(a)) {
            int[] lengthResult = b.getData();
            assertEquals(6, lengthResult[0], DELTA);
            assertEquals(6, lengthResult[1], DELTA);
        }
    }

    @Test
    public void testLinearTrend() throws Exception {
        double[] tss = {0, 4, 3, 5, 5, 1, 2, 4, 1, 2, 5, 3};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            Array[] b = Features.linearTrend(a);
            double[] pvalue = b[0].getData();
            double[] rvalue = b[1].getData();
            double[] intercept = b[2].getData();
            double[] slope = b[3].getData();
            double[] stdrr = b[4].getData();

            assertEquals(0.6260380997892747, pvalue[0], DELTA);
            assertEquals(0.5272201945463578, pvalue[1], DELTA);

            assertEquals(0.2548235957188128, rvalue[0], DELTA);
            assertEquals(0.3268228676411533, rvalue[1], DELTA);

            assertEquals(2.2857142857142856, intercept[0], DELTA);
            assertEquals(2.1904761904761907, intercept[1], DELTA);

            assertEquals(0.2857142857142857, slope[0], DELTA);
            assertEquals(0.2571428571428572, slope[1], DELTA);

            assertEquals(0.5421047417431507, stdrr[0], DELTA);
            assertEquals(0.37179469135129783, stdrr[1], DELTA);

            for (Array res : b) {
                res.close();
            }
        }
    }

    @Test
    public void testHasDuplicateMin() throws Exception {
        double[] tss = {5, 4, 3, 0, 0, 1, 5, 4, 3, 0, 2, 1};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.hasDuplicateMin(a)) {
            boolean[] hasDuplicateMinResult = b.getData();
            assertTrue(hasDuplicateMinResult[0]);
            assertFalse(hasDuplicateMinResult[1]);
        }
    }

    @Test
    public void testLongestStrikeAboveMean() throws Exception {
        double[] tss = {20, 20, 20, 1, 1, 1, 20, 20, 20, 20, 1, 1, 1, 1, 1, 1, 1, 1, 20, 20, 20, 20, 20, 1, 1, 1, 20,
                        20, 20, 1, 1, 1, 1, 1, 1, 1, 1, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.longestStrikeAboveMean(a)) {
            double[] longestStrikeAboveMeanResult = b.getData();
            assertEquals(4, longestStrikeAboveMeanResult[0], DELTA);
            assertEquals(3, longestStrikeAboveMeanResult[1], DELTA);
        }
    }

    @Test
    public void testLongestStrikeBelowMean() throws Exception {
        double[] tss = {20, 20, 20, 1, 1, 1, 20, 20, 20, 20, 1, 1, 1, 1, 1, 1, 1, 1, 20, 20, 20, 20, 20, 1, 1, 1, 20,
                        20, 20, 1, 1, 1, 1, 1, 1, 1, 1, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.longestStrikeBelowMean(a)) {
            double[] longestStrikeBelowMeanResult = b.getData();
            assertEquals(8, longestStrikeBelowMeanResult[0], DELTA);
            assertEquals(9, longestStrikeBelowMeanResult[1], DELTA);
        }
    }

    @Test
    public void testMaximum() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2, 19, 1,
                        20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.maximum(a)) {
            double[] maximumResult = b.getData();
            assertEquals(50, maximumResult[0], DELTA);
            assertEquals(30, maximumResult[1], DELTA);
        }
    }

    @Test
    public void testMeanAbsoluteChange() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 8, 10, 12, 14, 16, 18};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.meanAbsoluteChange(a)) {
            double[] meanAbsoluteResult = b.getData();
            assertEquals((float) 5 / 6, meanAbsoluteResult[0], DELTA);
            assertEquals((float) 10 / 6, meanAbsoluteResult[1], DELTA);
        }
    }

    @Test
    public void testFftCoefficient() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            Array[] b = Features.fftCoefficient(a, 0);

            double[] real = b[0].getData();
            double[] imag = b[1].getData();
            double[] abs = b[2].getData();
            double[] angle = b[3].getData();


            assertEquals(15, real[0], DELTA);
            assertEquals(51, real[1], DELTA);

            assertEquals(0, imag[0], DELTA);
            assertEquals(0, imag[1], DELTA);

            assertEquals(15, abs[0], DELTA);
            assertEquals(51, abs[1], DELTA);

            assertEquals(0, angle[0], DELTA);
            assertEquals(0, angle[1], DELTA);

            for (Array res : b) {
                res.close();
            }
        }
    }

    @Test
    public void testAggregatedAutocorrelationMean() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.aggregatedAutocorrelation(a, 0)) {
            double[] aggregatedAutocorrelationResult = b.getData();
            assertEquals(-0.6571428571428571, aggregatedAutocorrelationResult[0], DELTA);
            assertEquals(-0.6571428571428571, aggregatedAutocorrelationResult[1], DELTA);
        }
    }

    @Test
    public void testAggregatedAutocorrelationMedian() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.aggregatedAutocorrelation(a, 1)) {
            double[] aggregatedAutocorrelationResult = b.getData();
            assertEquals(-0.54285717010498047, aggregatedAutocorrelationResult[0], DELTA);
            assertEquals(-0.54285717010498047, aggregatedAutocorrelationResult[1], DELTA);
        }
    }

    @Test
    public void testAggregatedAutocorrelationMin() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.aggregatedAutocorrelation(a, 2)) {
            double[] aggregatedAutocorrelationResult = b.getData();
            assertEquals(-2.142857142857143, aggregatedAutocorrelationResult[0], DELTA);
            assertEquals(-2.142857142857143, aggregatedAutocorrelationResult[1], DELTA);
        }
    }

    @Test
    public void testAggregatedAutocorrelationMax() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.aggregatedAutocorrelation(a, 3)) {
            double[] aggregatedAutocorrelationResult = b.getData();
            assertEquals(0.6, aggregatedAutocorrelationResult[0], DELTA);
            assertEquals(0.6, aggregatedAutocorrelationResult[1], DELTA);
        }
    }

    @Test
    public void testAggregatedAutocorrelationStdev() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.aggregatedAutocorrelation(a, 4)) {
            double[] aggregatedAutocorrelationResult = b.getData();
            assertEquals(0.9744490855905009, aggregatedAutocorrelationResult[0], DELTA);
            assertEquals(0.9744490855905009, aggregatedAutocorrelationResult[1], DELTA);
        }
    }

    @Test
    public void testAggregatedAutocorrelationVar() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.aggregatedAutocorrelation(a, 5)) {
            double[] aggregatedAutocorrelationResult = b.getData();
            assertEquals(0.9495510204081633, aggregatedAutocorrelationResult[0], DELTA);
            assertEquals(0.9495510204081633, aggregatedAutocorrelationResult[1], DELTA);
        }
    }

    @Test
    public void testAggregatedLinearTrendMean() throws Exception {
        double[] tss = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5};
        long[] dims = {12, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            Array[] b = Features.aggregatedLinearTrend(a, 3, 0);

            double[] pvalue = b[0].getData();
            double[] rvalue = b[1].getData();
            double[] intercept = b[2].getData();
            double[] slope = b[3].getData();
            double[] stdrr = b[4].getData();

            assertEquals(1, pvalue[0], DELTA);
            assertEquals(2, rvalue[0], DELTA);
            assertEquals(1, intercept[0], DELTA);
            assertEquals(0, slope[0], DELTA);
            assertEquals(0, stdrr[0], DELTA);

            for (Array res : b) {
                res.close();
            }
        }
    }

    @Test
    public void testAggregatedLinearTrendMin() throws Exception {
        double[] tss = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5};
        long[] dims = {12, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            Array[] b = Features.aggregatedLinearTrend(a, 3, 1);

            double[] pvalue = b[0].getData();
            double[] rvalue = b[1].getData();
            double[] intercept = b[2].getData();
            double[] slope = b[3].getData();
            double[] stdrr = b[4].getData();

            assertEquals(1, pvalue[0], DELTA);
            assertEquals(2, rvalue[0], DELTA);
            assertEquals(1, intercept[0], DELTA);
            assertEquals(0, slope[0], DELTA);
            assertEquals(0, stdrr[0], DELTA);

            for (Array res : b) {
                res.close();
            }
        }
    }

    @Test
    public void testCwtCoefficients() throws Exception {
        float[] tss = {0.1f, 0.2f, 0.3f, 0.1f, 0.2f, 0.3f};
        long[] dims = {3, 2, 1, 1};
        int[] width = {1, 2, 3};
        long[] dimsW = {3, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array w = Array.fromPrimitiveArray(width, dimsW);
             Array b = Features.cwtCoefficients(a, w, 2, 2)) {
            float[] cwtCoefficientsResult = b.getData();
            assertEquals(0.26, cwtCoefficientsResult[0], 1e-2);
            assertEquals(0.26, cwtCoefficientsResult[1], 1e-2);
        }
    }

    @Test
    public void localMaximals() throws Exception {
        double[] tss = {0.0, 4.0, 3.0, 5.0, 4.0, 1.0, 0.0, 6.0, 0.0, 4.0, 3.0, 5.0, 4.0, 1.0, 0.0, 6.0};
        long[] dims = {8, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.localMaximals(a)) {
            double[] lm = b.getData();
            assertEquals((float) 0.0, lm[0], DELTA);
            assertEquals((float) 1.0, lm[1], DELTA);
            assertEquals((float) 0.0, lm[2], DELTA);
            assertEquals((float) 1.0, lm[3], DELTA);
            assertEquals((float) 0.0, lm[4], DELTA);
            assertEquals((float) 0.0, lm[5], DELTA);
            assertEquals((float) 0.0, lm[6], DELTA);
            assertEquals((float) 1.0, lm[7], DELTA);
        }
    }

    @Test
    public void testMeanSecondDerivativeCentral() throws Exception {
        double[] tss = {1, 3, 7, 4, 8, 2, 5, 1, 7, 4};
        long[] dims = {5, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.meanSecondDerivativeCentral(a)) {
            double[] meanSecondDerivativeCentralResult = b.getData();
            assertEquals((float) 1.0 / 5.0, meanSecondDerivativeCentralResult[0], DELTA);
            assertEquals((float) -3.0 / 5.0, meanSecondDerivativeCentralResult[1], DELTA);
        }
    }

    @Test
    public void testMinimum() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 13, 15, 5, 16, 20, 20, 20, 20, 20, 2, 19,
                        4, 20, 20, 20, 4, 15, 6, 30, 7, 9, 18, 4, 10, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.minimum(a)) {
            double[] minimumResult = b.getData();
            assertEquals(1, minimumResult[0], DELTA);
            assertEquals(2, minimumResult[1], DELTA);
        }
    }

    @Test
    public void testNumberCrossingM() throws Exception {
        double[] tss = {1, 2, 1, 1, -3, -4, 7, 8, 9, 10, -2, 1, -3, 5, 6, 7, -10, 1, 2, 1, 1, -3, -4, 7, 8, 9, 10, -2,
                        1, -3, 5, 6, 7, -10};
        long[] dims = {17, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.numberCrossingM(a, 0)) {
            double[] numberCrossingMResult = b.getData();
            assertEquals(7, numberCrossingMResult[0], DELTA);
            assertEquals(7, numberCrossingMResult[1], DELTA);
        }
    }

    @Test
    public void testMedian() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 13, 15, 5, 16, 20, 20, 20, 20, 20, 2, 19,
                        4, 20, 20, 20, 4, 15, 6, 30, 7, 9, 18, 4, 10, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.median(a)) {
            double[] medianResult = b.getData();
            assertEquals(20, medianResult[0], DELTA);
            assertEquals(18.5, medianResult[1], DELTA);
        }
    }

    @Test
    public void testMean() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2, 19, 1,
                        20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.mean(a)) {
            double[] result = b.getData();
            assertEquals(18.55, result[0], DELTA);
            assertEquals(12.7, result[1], DELTA);
        }
    }

    @Test
    public void testMeanChange() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 8, 10, 12, 14, 16, 18};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.meanChange(a)) {
            double[] result = b.getData();
            assertEquals((float) 5 / 6, result[0], DELTA);
            assertEquals((float) 10 / 6, result[1], DELTA);
        }
    }

    @Test
    public void testMaxLangevinFixedPoint() throws Exception {
        float[] tss = {0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5};
        long[] dims = {6, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.maxLangevinFixedPoint(a, 7, 2)) {
            float[] result = b.getData();
            assertEquals(4.562970585, result[0], 1e-4);
            assertEquals(4.562970585, result[1], 1e-4);
        }

    }

    @Test
    public void testNumberCwtPeaks() throws Exception {
        double[] tss = {1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1,
                        1, 1, 1, 5, 1, 1, 1, 1, 1, 1};
        long[] dims = {21, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.numberCwtPeaks(a, 2)) {
            double[] result = b.getData();
            assertEquals(2, result[0], DELTA);
            assertEquals(2, result[1], DELTA);
        }
    }

    @Test
    public void testNumberPeaks() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 0, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.numberPeaks(a, 2)) {
            double[] result = b.getData();
            assertEquals(1, result[0], DELTA);
            assertEquals(1, result[1], DELTA);
        }
    }

    @Test
    public void testFftAggregated() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        long[] dims = {10, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.fftAggregated(a)) {
            double[] result = b.getData();
            assertEquals(1.135143, result[0], DELTA);
            assertEquals(2.368324, result[1], DELTA);
            assertEquals(1.248777, result[2], DELTA);
            assertEquals(3.642666, result[3], DELTA);

            assertEquals(1.135143, result[4], DELTA);
            assertEquals(2.368324, result[5], DELTA);
            assertEquals(1.248777, result[6], DELTA);
            assertEquals(3.642666, result[7], DELTA);
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

        try (Array a = Array.fromPrimitiveArray(input, dims); Array b = Array.fromPrimitiveArray(lags, dimsLags);
             Array c = Features.partialAutocorrelation(a, b)) {

            double[] expected = {1.0, 0.9993331432342529, -0.0006701064994559, -0.0006701068487018, -0.0008041285327636,
                                 -0.0005360860959627, -0.0007371186511591, -0.0004690756904893, -0.0008041299879551,
                                 -0.0007371196406893, 1.0, 0.9993331432342529, -0.0006701064994559, -0.0006701068487018,
                                 -0.0008041285327636, -0.0005360860959627, -0.0007371186511591, -0.0004690756904893,
                                 -0.0008041299879551, -0.0007371196406893};
            double[] result = c.getData();
            assertArrayEquals(expected, result, 1e-3f);
        }
    }

    @Test
    public void testPercentageOfReocurringDatapointsToAllDatapoints() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 0, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims);
             Array b = Features.percentageOfReoccurringDatapointsToAllDatapoints(a, false)) {
            double[] result = b.getData();
            assertEquals(0.25, result[0], DELTA);
            assertEquals(0.25, result[1], DELTA);
        }
    }

    @Test
    public void testPercentageOfReocurringValuesToAllValues() throws Exception {
        double[] tss = {1, 1, 2, 3, 4, 4, 5, 6, 1, 2, 2, 3, 4, 5, 6, 7};
        long[] dims = {8, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims);
             Array b = Features.percentageOfReoccurringValuesToAllValues(a, false)) {
            double[] result = b.getData();
            assertEquals(4.0 / 8.0, result[0], DELTA);
            assertEquals(2.0 / 8.0, result[1], DELTA);
        }
    }

    @Test
    public void testQuantile() throws Exception {
        double[] tss = {0, 0, 0, 0, 3, 4, 13, 0, 0, 0, 0, 3, 4, 13};
        double[] q = {0.6};
        long[] dims = {7, 2, 1, 1};
        long[] dimsQ = {1, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array arrayQ = Array.fromPrimitiveArray(q, dimsQ);
             Array b = Features.quantile(a, arrayQ)) {
            double[] result = b.getData();
            assertEquals(1.79999999, result[0], DELTA);
            assertEquals(1.79999999, result[1], DELTA);
        }
    }

    @Test
    public void testRangeCount() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 5, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.rangeCount(a, 2, 12)) {
            double[] result = b.getData();
            assertEquals(2, result[0], DELTA);
            assertEquals(3, result[1], DELTA);
        }
    }

    @Test
    public void testRatioBeyondRSigma() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 0, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.ratioBeyondRSigma(a, (float) 0.5)) {
            double[] result = b.getData();
            assertEquals(0.7142857142857143, result[0], DELTA);
            assertEquals(0.7142857142857143, result[1], DELTA);
        }
    }

    @Test
    public void testRatioValueNumberToTimeSeriesLength() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 5, 0, 4, 6, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.ratioValueNumberToTimeSeriesLength(a)) {
            double[] result = b.getData();
            assertEquals(4.0 / 7.0, result[0], DELTA);
            assertEquals(6.0 / 7.0, result[1], DELTA);
        }
    }

    @Test
    public void testSampleEntropy() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 0, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.sampleEntropy(a)) {
            double[] result = b.getData();
            assertEquals(1.252762968495368, result[0], DELTA);
            assertEquals(1.252762968495368, result[1], DELTA);
        }
    }

    @Test
    public void testSkewness() throws Exception {
        double[] tss = {3, 0, 0, 4, 0, 0, 13, 3, 0, 0, 4, 0, 0, 13};
        long[] dims = {7, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.skewness(a)) {
            double[] result = b.getData();
            assertEquals(2.038404735373753, result[0], DELTA);
            assertEquals(2.038404735373753, result[1], DELTA);
        }
    }

    @Test
    public void testSpktWelchDensity() throws Exception {
        double[] tss = {0, 1, 1, 3, 4, 5, 6, 7, 8, 9, 0, 1, 1, 3, 4, 5, 6, 7, 8, 9};
        long[] dims = {10, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            float[] result = Features.spktWelchDensity(a, 0).getData();
            assertEquals(1.6666667461395264, result[0], DELTA);
            assertEquals(1.6666667461395264, result[1], DELTA);
        }
    }

    @Test
    public void testStandardDeviation() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2, 19, 1,
                        20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.standardDeviation(a)) {
            double[] result = b.getData();
            assertEquals(12.363150892875165, result[0], DELTA);
            assertEquals(9.51367436903324, result[1], DELTA);
        }
    }

    @Test
    public void testSumOfReoccurringDatapoints() throws Exception {
        double[] tss = {3, 3, 0, 4, 0, 13, 13, 3, 3, 0, 4, 0, 13, 13};
        long[] dims = {7, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.sumOfReoccurringDatapoints(a, false)) {
            double[] result = b.getData();
            assertEquals(32, result[0], DELTA);
            assertEquals(32, result[1], DELTA);
        }
    }

    @Test
    public void testSumOfReoccurringDatapoints2() throws Exception {
        double[] tss = {3, 3, 0, 4, 0, 13, 13, 3, 3, 0, 4, 0, 13, 13};
        long[] dims = {7, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.sumOfReoccurringDatapoints(a)) {
            double[] result = b.getData();
            assertEquals(32, result[0], DELTA);
            assertEquals(32, result[1], DELTA);
        }
    }

    @Test
    public void testSumOfReoccurringValues() throws Exception {
        double[] tss = {4, 4, 6, 6, 7, 4, 7, 7, 8, 8};
        long[] dims = {5, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.sumOfReoccurringValues(a, false)) {
            double[] result = b.getData();
            assertEquals(10, result[0], DELTA);
            assertEquals(15, result[1], DELTA);
        }
    }

    @Test
    public void testSumOfReoccurringValues2() throws Exception {
        double[] tss = {4, 4, 6, 6, 7, 4, 7, 7, 8, 8};
        long[] dims = {5, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.sumOfReoccurringValues(a)) {
            double[] result = b.getData();
            assertEquals(10, result[0], DELTA);
            assertEquals(15, result[1], DELTA);
        }
    }

    @Test
    public void testSumValues() throws Exception {
        double[] tss = {1, 2, 3, 4.1, -1.2, -2, -3, -4};
        long[] dims = {4, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.sumValues(a)) {
            double[] result = b.getData();
            assertEquals(10.1, result[0], DELTA);
            assertEquals(-10.2, result[1], DELTA);
        }
    }

    @Test
    public void testSymmetryLooking() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2, 19, 1,
                        20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.symmetryLooking(a, (float) 0.1)) {
            boolean[] result = b.getData();
            assertTrue(result[0]);
            assertFalse(result[1]);
        }
    }

    @Test
    public void testTimeReversalAsymmetryStatistic() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 20, 20, 20, 2, 19, 1, 20,
                        20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};

        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.timeReversalAsymmetryStatistic(a, 2)) {
            double[] result = b.getData();
            assertEquals(1052, result[0], DELTA);
            assertEquals(-150.625, result[1], DELTA);
        }
    }

    @Test
    public void testValueCount() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2, 19, 1,
                        20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.valueCount(a, 20)) {
            int[] result = b.getData();
            assertEquals(9, result[0], DELTA);
            assertEquals(8, result[1], DELTA);
        }
    }

    @Test
    public void testVariance() throws Exception {
        double[] tss = {1, 1, -1, -1, 1, 2, -2, -1};
        long[] dims = {4, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.variance(a)) {
            double[] result = b.getData();
            assertEquals(1.0, result[0], DELTA);
            assertEquals(2.5, result[1], DELTA);
        }
    }

    @Test
    public void testVarianceLargerThanStandardDeviation() throws Exception {
        double[] tss = {20, 20, 20, 18, 25, 19, 20, 20, 20, 20, 40, 30, 1, 50, 1, 1, 5, 1, 20, 20, 20, 20, 20, 2, 19, 1,
                        20, 20, 20, 1, 15, 1, 30, 1, 1, 18, 4, 1, 20, 20};
        long[] dims = {20, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Features.varianceLargerThanStandardDeviation(a)) {
            boolean[] result = b.getData();
            assertTrue(result[0]);
            assertTrue(result[1]);
        }
    }
}
