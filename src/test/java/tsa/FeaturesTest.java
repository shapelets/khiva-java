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
    Features features = new Features();
    double[][] arrayOfTimeSeries = {{0, 1, 2, 3, 4, 5}, {6, 7, 8, 9, 10, 11}};


    @Test
    public void testCidCe() {
        double[] cidCe = features.cidCe(arrayOfTimeSeries, Boolean.TRUE);
        Assert.assertEquals(cidCe[0], 1.30930734141595, DELTA);
        Assert.assertEquals(cidCe[1], 1.30930734141595, DELTA);
        cidCe = features.cidCe(arrayOfTimeSeries, Boolean.FALSE);
        Assert.assertEquals(cidCe[0], 2.23606797749979, DELTA);
        Assert.assertEquals(cidCe[1], 2.23606797749979, DELTA);

    }

    @Test
    public void testC3() {
        double[] c3 = features.c3(arrayOfTimeSeries, 2);
        Assert.assertEquals(c3[0], 7.5, DELTA);
        Assert.assertEquals(c3[1], 586.5, DELTA);
    }

    @Test
    public void testAbsSumOfChanges() {
        double[][] tss = {{0, 1, 2, 3}, {4, 6, 8, 10}, {11, 14, 17, 20}};
        double[] absSum = features.absoluteSumOfChanges(tss);
        Assert.assertEquals(absSum[0], 3, DELTA);
        Assert.assertEquals(absSum[1], 6, DELTA);
        Assert.assertEquals(absSum[2], 9, DELTA);
    }

    @Test
    public void testAbsEnergy() {
        double[][] tss = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}};
        double[] absEnergy = features.absEnergy(tss);
        Assert.assertEquals(absEnergy[0], 385, DELTA);
    }
}
