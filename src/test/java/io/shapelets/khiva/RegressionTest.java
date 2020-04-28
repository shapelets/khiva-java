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

import static org.junit.Assert.assertEquals;

public class RegressionTest {

    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() throws Exception {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test
    public void testLinear() throws Exception {
        float[] timeSeriesXss = {0.24580423f, 0.59642861f, 0.35879163f, 0.37891011f, 0.02445137f, 0.23830957f,
                                 0.38793433f, 0.68054104f, 0.83934083f, 0.76073689f};
        float[] timeSeriesYss = {0.2217416f, 0.06344161f, 0.77944375f, 0.72174137f, 0.19413884f, 0.51146167f,
                                 0.06880307f, 0.39414268f, 0.98172767f, 0.30490851f};
        long[] dims = {10, 1, 1, 1};
        try (Array xss = Array.fromPrimitiveArray(timeSeriesXss, dims);
             Array yss = Array.fromPrimitiveArray(timeSeriesYss, dims)) {
            Array[] result = Regression.linear(xss, yss);
            float[] slope = result[0].getData();
            float[] intercept = result[1].getData();
            float[] rvalue = result[2].getData();
            float[] pvalue = result[3].getData();
            float[] stderrest = result[4].getData();

            assertEquals(0.344864266, slope[0], DELTA);
            assertEquals(0.268578232, intercept[0], DELTA);
            assertEquals(0.283552942, rvalue[0], DELTA);
            assertEquals(0.427239418, pvalue[0], DELTA);
            assertEquals(0.412351891, stderrest[0], DELTA);

            for (Array a : result) {
                a.close();
            }
        }
    }

    @Test
    public void testLinearMultipleTimeSeries() throws Exception {
        float[] timeSeriesXss = {0.24580423f, 0.59642861f, 0.35879163f, 0.37891011f, 0.02445137f, 0.23830957f,
                                 0.38793433f, 0.68054104f, 0.83934083f, 0.76073689f, 0.24580423f, 0.59642861f,
                                 0.35879163f, 0.37891011f, 0.02445137f, 0.23830957f, 0.38793433f, 0.68054104f,
                                 0.83934083f, 0.76073689f};
        float[] timeSeriesYss = {0.2217416f, 0.06344161f, 0.77944375f, 0.72174137f, 0.19413884f, 0.51146167f,
                                 0.06880307f, 0.39414268f, 0.98172767f, 0.30490851f, 0.2217416f, 0.06344161f,
                                 0.77944375f, 0.72174137f, 0.19413884f, 0.51146167f, 0.06880307f, 0.39414268f,
                                 0.98172767f, 0.30490851f};
        long[] dims = {10, 2, 1, 1};
        try (Array xss = Array.fromPrimitiveArray(timeSeriesXss, dims);
             Array yss = Array.fromPrimitiveArray(timeSeriesYss, dims)) {
            Array[] result = Regression.linear(xss, yss);
            float[] slope = result[0].getData();
            float[] intercept = result[1].getData();
            float[] rvalue = result[2].getData();
            float[] pvalue = result[3].getData();
            float[] stderrest = result[4].getData();

            assertEquals(0.344864266, slope[0], DELTA);
            assertEquals(0.268578232, intercept[0], DELTA);
            assertEquals(0.283552942, rvalue[0], DELTA);
            assertEquals(0.427239418, pvalue[0], DELTA);
            assertEquals(0.412351891, stderrest[0], DELTA);
            assertEquals(0.344864266, slope[1], DELTA);
            assertEquals(0.268578232, intercept[1], DELTA);
            assertEquals(0.283552942, rvalue[1], DELTA);
            assertEquals(0.427239418, pvalue[1], DELTA);
            assertEquals(0.412351891, stderrest[1], DELTA);

            for (Array a : result) {
                a.close();
            }
        }
    }
}
