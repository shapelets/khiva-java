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

public class RegressionTest {

    private static final double DELTA = 1e-6;


    @BeforeClass
    public static void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testLinear() throws Exception {
        float[] timeSeriesXss = {0.24580423f, 0.59642861f, 0.35879163f, 0.37891011f, 0.02445137f,
                0.23830957f, 0.38793433f, 0.68054104f, 0.83934083f, 0.76073689f};
        float[] timeSeriesYss = {0.2217416f, 0.06344161f, 0.77944375f, 0.72174137f, 0.19413884f,
                0.51146167f, 0.06880307f, 0.39414268f, 0.98172767f, 0.30490851f};
        long[] dims = {10, 1, 1, 1};
        try (Array xss = new Array(timeSeriesXss, dims); Array yss = new Array(timeSeriesYss, dims)) {
            Array[] result = Regression.linear(xss, yss);
            float[] slope = result[0].getData();
            float[] intercept = result[1].getData();
            float[] rvalue = result[2].getData();
            float[] pvalue = result[3].getData();
            float[] stderrest = result[4].getData();

            Assert.assertEquals(slope[0], 0.344864266, DELTA);
            Assert.assertEquals(intercept[0], 0.268578232, DELTA);
            Assert.assertEquals(rvalue[0], 0.283552942, DELTA);
            Assert.assertEquals(pvalue[0], 0.427239418, DELTA);
            Assert.assertEquals(stderrest[0], 0.412351891, DELTA);

            result[0].close();
            result[1].close();
            result[2].close();
            result[3].close();
            result[4].close();

        }
    }

    @Test
    public void testLinearMultipleTimeSeries() throws Exception {
        float[] timeSeriesXss = {0.24580423f, 0.59642861f, 0.35879163f, 0.37891011f, 0.02445137f,
                0.23830957f, 0.38793433f, 0.68054104f, 0.83934083f, 0.76073689f, 0.24580423f, 0.59642861f, 0.35879163f, 0.37891011f, 0.02445137f,
                0.23830957f, 0.38793433f, 0.68054104f, 0.83934083f, 0.76073689f};
        float[] timeSeriesYss = {0.2217416f, 0.06344161f, 0.77944375f, 0.72174137f, 0.19413884f,
                0.51146167f, 0.06880307f, 0.39414268f, 0.98172767f, 0.30490851f, 0.2217416f, 0.06344161f, 0.77944375f, 0.72174137f, 0.19413884f,
                0.51146167f, 0.06880307f, 0.39414268f, 0.98172767f, 0.30490851f};
        long[] dims = {10, 2, 1, 1};
        try (Array xss = new Array(timeSeriesXss, dims); Array yss = new Array(timeSeriesYss, dims)) {
            Array[] result = Regression.linear(xss, yss);
            float[] slope = result[0].getData();
            float[] intercept = result[1].getData();
            float[] rvalue = result[2].getData();
            float[] pvalue = result[3].getData();
            float[] stderrest = result[4].getData();

            Assert.assertEquals(slope[0], 0.344864266, DELTA);
            Assert.assertEquals(intercept[0], 0.268578232, DELTA);
            Assert.assertEquals(rvalue[0], 0.283552942, DELTA);
            Assert.assertEquals(pvalue[0], 0.427239418, DELTA);
            Assert.assertEquals(stderrest[0], 0.412351891, DELTA);
            Assert.assertEquals(slope[1], 0.344864266, DELTA);
            Assert.assertEquals(intercept[1], 0.268578232, DELTA);
            Assert.assertEquals(rvalue[1], 0.283552942, DELTA);
            Assert.assertEquals(pvalue[1], 0.427239418, DELTA);
            Assert.assertEquals(stderrest[1], 0.412351891, DELTA);

            result[0].close();
            result[1].close();
            result[2].close();
            result[3].close();
            result[4].close();
        }
    }
}