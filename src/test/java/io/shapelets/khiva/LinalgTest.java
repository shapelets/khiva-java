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

@RunWith(Parameterized.class)
public class LinalgTest {

    private static final double DELTA = 1e-6;

    @Parameters()
    public static Iterable<Object[]> backends() {
        String OS;

        OS = System.getProperty("os.name").toLowerCase();

        if (OS.indexOf("mac") >= 0) {
            return Arrays.asList(new Object[][]{
                    {Array.Backend.KHIVA_BACKEND_CPU}
            });
        } else
            return Arrays.asList(new Object[][]{
                    {Array.Backend.KHIVA_BACKEND_OPENCL}, {Array.Backend.KHIVA_BACKEND_CPU}
            });
    }

    public LinalgTest(Library.Backend back) {
        Library.setKhivaBackend(back);
    }

    @Test
    public void testLls() throws Exception {
            double[] tss = {4, 3, -1, -2};
            long[] dims = {2, 2, 1, 1};
            double[] b = {3, 1};
            long[] dimsB = {2, 1, 1, 1};
            Array a = new Array(tss, dims);
            Array c = new Array(b, dimsB);
            double[] result = Linalg.lls(a, c).getData();
            Assert.assertEquals(result[0], 1, DELTA);
            Assert.assertEquals(result[1], 1, DELTA);
            a.close();
            c.close();
        }
}
