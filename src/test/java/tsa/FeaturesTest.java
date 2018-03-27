/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package tsa;

import org.junit.Assert;
import org.junit.Test;


public class FeaturesTest {
    private static final double DELTA = 1e-6;
    double[][] arrayOfTimeSeries = {{0, 1, 2, 3, 4, 5}, {6, 7, 8, 9, 10, 11}};

    @Test
    public void testCidCe() {
        double[] cidCe = Features.cidCe(arrayOfTimeSeries, true);
        Assert.assertEquals(cidCe[0], 1.30930734141595, DELTA);
        Assert.assertEquals(cidCe[1], 1.30930734141595, DELTA);
        cidCe = Features.cidCe(arrayOfTimeSeries, false);
        Assert.assertEquals(cidCe[0], 2.23606797749979, DELTA);
        Assert.assertEquals(cidCe[1], 2.23606797749979, DELTA);

    }

    @Test
    public void testC3() {
        double[] c3 = Features.c3(arrayOfTimeSeries, 2);
        Assert.assertEquals(c3[0], 7.5, DELTA);
        Assert.assertEquals(c3[1], 586.5, DELTA);
    }

    @Test
    public void testAbsSumOfChanges() {
        double[][] tss = {{0, 1, 2, 3}, {4, 6, 8, 10}, {11, 14, 17, 20}};
        double[] absSum = Features.absoluteSumOfChanges(tss);
        Assert.assertEquals(absSum[0], 3, DELTA);
        Assert.assertEquals(absSum[1], 6, DELTA);
        Assert.assertEquals(absSum[2], 9, DELTA);
    }

    @Test
    public void testAbsEnergy() {
        double[][] tss = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}};
        double[] absEnergy = Features.absEnergy(tss);
        Assert.assertEquals(absEnergy[0], 385, DELTA);
    }

    @Test
    public void testApproximateEntropy() {
        double[][] tss = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {11, 12, 13, 14, 15, 16, 17, 18, 19, 20}};


        float r = (float) 0.5;
        double[] approximateEntropy = Features.approximateEntropy(tss, 4, r);

        Assert.assertEquals(approximateEntropy[0], 0.13484281753639338, DELTA);
        Assert.assertEquals(approximateEntropy[1], 0.13484281753639338, DELTA);
    }

    @Test
    public void testCrossCovariance() {
        double[][] tss1 = {{0, 1, 2, 3}, {10, 11, 12, 13}};
        double[][] tss2 = {{4, 6, 8, 10, 12}, {14, 16, 18, 20, 22}};
        double[] crossCovariance = Features.crossCovariance(tss1, tss2, false);
        for (int i = 0; i < 4; i++) {
            Assert.assertEquals(crossCovariance[(i * 5)], 2.5, DELTA);
            Assert.assertEquals(crossCovariance[(i * 5) + 1], 2.5, DELTA);
            Assert.assertEquals(crossCovariance[(i * 5) + 2], 0.25, DELTA);
            Assert.assertEquals(crossCovariance[(i * 5) + 3], -1.25, DELTA);
            Assert.assertEquals(crossCovariance[(i * 5) + 4], -1.5, DELTA);

        }
    }

    @Test
    public void testAutoCovariance() {
        double[][] tss = {{0, 1, 2, 3}, {10, 11, 12, 13}};

        double[] autoCovariance = Features.autoCovariance(tss, false);
        Assert.assertEquals(autoCovariance[0], 1.25, DELTA);
        Assert.assertEquals(autoCovariance[1], 0.3125, DELTA);
        Assert.assertEquals(autoCovariance[2], -0.375, DELTA);
        Assert.assertEquals(autoCovariance[3], -0.5625, DELTA);
        Assert.assertEquals(autoCovariance[4], 1.25, DELTA);
        Assert.assertEquals(autoCovariance[5], 0.3125, DELTA);
        Assert.assertEquals(autoCovariance[6], -0.375, DELTA);
        Assert.assertEquals(autoCovariance[7], -0.5625, DELTA);

    }

    @Test
    public void testCrossCorrelation() {
        double[][] tss1 = {{1, 2, 3, 4}};
        double[][] tss2 = {{4, 6, 8, 10, 12}};

        double[] crossCorrelation = Features.crossCorrelation(tss1, tss2, false);
        Assert.assertEquals(crossCorrelation[0], 0.790569415, 1e-9);
        Assert.assertEquals(crossCorrelation[1], 0.790569415, 1e-9);
        Assert.assertEquals(crossCorrelation[2], 0.079056941, 1e-9);
        Assert.assertEquals(crossCorrelation[3], -0.395284707, 1e-9);
        Assert.assertEquals(crossCorrelation[4], -0.474341649, 1e-9);
    }

    @Test
    public void testAutoCorrelation() {
        double[][] tss = {{0, 1, 2, 3}, {10, 11, 12, 13}};
        double[] autoCorrelationResult = Features.autoCorrelation(tss, 4, false);
        Assert.assertEquals(autoCorrelationResult[0], 1, DELTA);
        Assert.assertEquals(autoCorrelationResult[1], 0.25, DELTA);
        Assert.assertEquals(autoCorrelationResult[2], -0.3, DELTA);
        Assert.assertEquals(autoCorrelationResult[3], -0.45, DELTA);
        Assert.assertEquals(autoCorrelationResult[4], 1.0, DELTA);
        Assert.assertEquals(autoCorrelationResult[5], 0.25, DELTA);
        Assert.assertEquals(autoCorrelationResult[6], -0.3, DELTA);
        Assert.assertEquals(autoCorrelationResult[7], -0.45, DELTA);
    }

    @Test
    public void testBinnedCorrelation() {
        double[][] tss = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                14, 15, 16, 17, 18, 19, 20}, {1, 1, 3, 10, 5, 6, 1, 8, 9, 10, 11, 1, 13, 14, 10, 16, 17, 10, 19, 20}};

        double[] binnedEntropyResult = Features.binnedEntropy(tss, 5);
        Assert.assertEquals(binnedEntropyResult[0], 1.6094379124341005, DELTA);
        Assert.assertEquals(binnedEntropyResult[1], 1.5614694247763998, DELTA);

    }

    @Test
    public void testCountAboveMean() {
        double[][] tss = {{0, 1, 2, 3, 4, 5}, {6, 7, 8, 9, 10, 11}};
        int[] countAboveMeanResult = Features.countAboveMean(tss);
        Assert.assertEquals(countAboveMeanResult[0], 3, DELTA);
        Assert.assertEquals(countAboveMeanResult[1], 3, DELTA);
    }

    @Test
    public void testCountBelowMean() {
        double[][] tss = {{0, 1, 2, 3, 4, 5}, {6, 7, 8, 9, 10, 11}};
        int[] countBelowMeanResult = Features.countBelowMean(tss);

        Assert.assertEquals(countBelowMeanResult[0], 3, DELTA);
        Assert.assertEquals(countBelowMeanResult[1], 3, DELTA);

    }

    @Test
    public void testEnergyRatioByChunks() {
        double[][] tss = {{0, 1, 2, 3, 4, 5}, {6, 7, 8, 9, 10, 11}};
        double[] energyRatioByChunksResult = Features.energyRatioByChunks(tss, 2, 0);

        Assert.assertEquals(energyRatioByChunksResult[0], 0.090909091, DELTA);
        Assert.assertEquals(energyRatioByChunksResult[1], 0.330376940, DELTA);
        energyRatioByChunksResult = Features.energyRatioByChunks(tss, 2, 1);
        Assert.assertEquals(energyRatioByChunksResult[0], 0.909090909, DELTA);
        Assert.assertEquals(energyRatioByChunksResult[1], 0.669623060, DELTA);

    }

    @Test
    public void testFirstLocationOfMaximum() {
        double[][] tss = {{5, 4, 3, 5, 0, 1, 5, 3, 2, 1}, {2, 4, 3, 5, 2, 5, 4, 3, 5, 2}};
        double[] firstLocationOfMaximumResult = Features.firstLocationOfMaximum(tss);
        Assert.assertEquals(firstLocationOfMaximumResult[0], 0.0, DELTA);
        Assert.assertEquals(firstLocationOfMaximumResult[1], 0.3, DELTA);

    }

    @Test
    public void testFirstLocationOfMinimum() {
        double[][] tss = {{5, 4, 3, 0, 0, 1}, {5, 4, 3, 0, 2, 1}};
        double[] firstLocationOfMinimumResult = Features.firstLocationOfMinimum(tss);
        Assert.assertEquals(firstLocationOfMinimumResult[0], 0.5, DELTA);
        Assert.assertEquals(firstLocationOfMinimumResult[1], 0.5, DELTA);

    }

    @Test
    public void testHasDuplicates() {
        double[][] tss = {{5, 4, 3, 0, 0, 1}, {5, 4, 3, 0, 2, 1}};
        boolean[] hasDuplicatesResult = Features.hasDuplicates(tss);
        Assert.assertEquals(hasDuplicatesResult[0], true);
        Assert.assertEquals(hasDuplicatesResult[1], false);

    }

    @Test
    public void testHasDuplicateMax() {
        double[][] tss = {{5, 4, 3, 0, 5, 1}, {5, 4, 3, 0, 2, 1}};
        boolean[] hasDuplicateMaxResult = Features.hasDuplicateMax(tss);
        Assert.assertEquals(hasDuplicateMaxResult[0], true);
        Assert.assertEquals(hasDuplicateMaxResult[1], false);

    }

    @Test
    public void testIndexMaxQuantile() {
        double[][] tss = {{5, 4, 3, 0, 0, 1}, {5, 4, 0, 0, 2, 1}};
        float q = (float) 0.5;
        double[] indexMaxQuantileResult = Features.indexMaxQuantile(tss, q);
        Assert.assertEquals(indexMaxQuantileResult[0], 0.333333333, DELTA);
        Assert.assertEquals(indexMaxQuantileResult[1], 0.333333333, DELTA);
    }

    @Test
    public void testKurtosis() {
        double[][] tss = {{0, 1, 2, 3, 4, 5}, {2, 2, 2, 20, 30, 25}};
        double[] kurtosisResult = Features.kurtosis(tss);
        Assert.assertEquals(kurtosisResult[0], -1.2, DELTA);
        Assert.assertEquals(kurtosisResult[1], -2.66226722, DELTA);
    }

    @Test
    public void testLargeStandardDeviation() {
        double[][] tss = {{-1, -1, -1, 1, 1, 1}, {4, 6, 8, 4, 5, 4}};
        float r = (float) 0.4;
        boolean[] largeStandardDeviationResult = Features.largeStandardDeviation(tss, r);
        Assert.assertEquals(largeStandardDeviationResult[0], true);
        Assert.assertEquals(largeStandardDeviationResult[1], false);
    }

    @Test
    public void testLastLocationOfMaximum() {
        double[][] tss = {{0, 4, 3, 5, 5, 1}, {0, 4, 3, 2, 5, 1}};
        double[] locationOfMaximumResult = Features.lastLocationOfMaximum(tss);
        Assert.assertEquals(locationOfMaximumResult[0], 0.8333333333333334, DELTA);
        Assert.assertEquals(locationOfMaximumResult[1], 0.8333333333333334, DELTA);
    }

    @Test
    public void testLastLocationOfMinimum() {
        double[][] tss = {{0, 4, 3, 5, 5, 1, 0, 4}, {3, 2, 5, 1, 4, 5, 1, 2}};
        double[] locationOfMinimumResult = Features.lastLocationOfMinimum(tss);
        Assert.assertEquals(locationOfMinimumResult[0], 0.875, DELTA);
        Assert.assertEquals(locationOfMinimumResult[1], 0.875, DELTA);
    }

    @Test
    public void testLength() {
        double[][] tss = {{0, 4, 3, 5, 5, 1}, {0, 4, 3, 2, 5, 1}};
        int[] lengthResult = Features.length(tss);
        Assert.assertEquals(lengthResult[0], 6, DELTA);
        Assert.assertEquals(lengthResult[1], 6, DELTA);
    }

    @Test
    public void testLinearTrend() {
        double[][] tss = {{0, 4, 3, 5, 5, 1}, {2, 4, 1, 2, 5, 3}};

        double[] pvalue = new double[tss.length];
        double[] rvalue = new double[tss.length];
        double[] intercept = new double[tss.length];
        double[] slope = new double[tss.length];
        double[] stdrr = new double[tss.length];


        Features.linearTrend(tss, pvalue, rvalue, intercept, slope, stdrr);

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
    }
}
