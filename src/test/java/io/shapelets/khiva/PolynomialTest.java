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
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.logging.Logger;

@RunWith(Parameterized.class)
public class PolynomialTest {
    Logger logger = Logger.getGlobal();
    private static final double DELTA = 1e-6;

    @Parameters()
    public static Iterable<Object[]> backends() {
        return Arrays.asList(new Object[][]{
                {Array.Backend.KHIVA_BACKEND_OPENCL}, {Array.Backend.KHIVA_BACKEND_CPU}
        });
    }

    public PolynomialTest(Library.Backend back) {
        Library.setKhivaBackend(back);
    }

    @Test
    public void testPolyfit1() throws Exception {
        if (Library.getKhivaBackend() == Array.Backend.KHIVA_BACKEND_CPU) {
            double[] tss = {0, 1, 2, 3, 4, 5};
            long[] dims = {6, 1, 1, 1};
            Array x = new Array(tss, dims);
            Array y = new Array(tss, dims);
            double[] result = Polynomial.polyfit(x, y, 1).getData();
            double[] expected = {1.0, 0.0};
            Assert.assertArrayEquals(result, expected, DELTA);
        } else {
            logger.warning("testPolyfit1 only executed in CPU backend because of problems with " +
                    "OpenMP while changing the backend.");
        }
    }

    @Test
    public void testPolyfit3() throws Exception {

        if (Library.getKhivaBackend() == Array.Backend.KHIVA_BACKEND_CPU) {
            double[] tss1 = {0, 1, 2, 3, 4, 5};
            double[] tss2 = {0.0, 0.8, 0.9, 0.1, -0.8, -1.0};
            long[] dims = {6, 1, 1, 1};
            Array x = new Array(tss1, dims);
            Array y = new Array(tss2, dims);
            double[] result = Polynomial.polyfit(x, y, 3).getData();
            double[] expected = {0.08703704, -0.81349206, 1.69312169, -0.03968254};
            Assert.assertArrayEquals(result, expected, DELTA);
        } else {
            logger.warning("testPolyfit3 only executed in CPU backend because of problems with " +
                    "OpenMP while changing the backend.");
        }
    }

    @Test
    public void testRoots() throws Exception {
        double[] tss = {5, -20, 5, 50, -20, -40};
        long[] dims = {6, 1, 1, 1};
        Array p = new Array(tss, dims);

        FloatComplex[] result = Polynomial.roots(p).getData();
        FloatComplex[] expected = {new FloatComplex(2, 0), new FloatComplex(2, 0),
                new FloatComplex(2, 0), new FloatComplex(-1, 0), new FloatComplex(-1, 0)};
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(expected[i].getReal(), result[i].getReal(), 1e-2);
            Assert.assertEquals(expected[i].getImag(), result[i].getImag(), 1e-2);
        }
    }
}
