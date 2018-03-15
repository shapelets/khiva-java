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

public class TSAtest {
    private static final double DELTA = 1e-9;

    @Test
    public void testCidCe() {
        TSA tsa = TSA.getInstance();
        double[][] array_of_time_series = {{0, 1, 2, 3, 4, 5}, {6, 7, 8, 9, 10, 11}};
        double[] cidCe = tsa.cidCe(array_of_time_series, Boolean.TRUE);
        Assert.assertEquals(cidCe[0], 1.30930734141595, DELTA);
        Assert.assertEquals(cidCe[1], 1.30930734141595, DELTA);
        cidCe = tsa.cidCe(array_of_time_series, Boolean.FALSE);
        Assert.assertEquals(cidCe[0], 2.23606797749979, DELTA);
        Assert.assertEquals(cidCe[1], 2.23606797749979, DELTA);

    }
}
