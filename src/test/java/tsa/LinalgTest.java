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

public class LinalgTest {
    private static final double DELTA = 1e-6;

    @Test
    public void testLls() {
        double[][] tss = {{4, 3}, {-1, -2}};
        double[] b = {3, 1};
        double[] result = Linalg.lls(tss, b);
        Assert.assertEquals(result[0], 1, DELTA);
        Assert.assertEquals(result[1], 1, DELTA);
    }
}
