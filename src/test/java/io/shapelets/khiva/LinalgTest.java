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
public class LinalgTest {
    Logger logger = Logger.getGlobal();

    private static final double DELTA = 1e-6;

    @Parameters()
    public static Iterable<Object[]> backends() {
        return Arrays.asList(new Object[][]{
                {Array.Backend.KHIVA_BACKEND_OPENCL}, {Array.Backend.KHIVA_BACKEND_CPU}
        });
    }

    public LinalgTest(Library.Backend back) {
        Library.setKhivaBackend(back);
    }

    @Test
    public void testLls() throws Exception {
        if (Library.getKhivaBackend() == Array.Backend.KHIVA_BACKEND_CPU) {
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
        } else {
            logger.warning("testLls only executed in CPU backend because of problems with " +
                    "OpenMP while changing the backend");
        }

    }

}
