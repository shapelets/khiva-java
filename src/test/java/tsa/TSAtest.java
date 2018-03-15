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
    private static final double DELTA = 1e-6;
    TSA tsa = TSA.getInstance();
    double[][] arrayOfTimeSeries = {{0, 1, 2, 3, 4, 5}, {6, 7, 8, 9, 10, 11}};


    @Test
    public void testCidCe() {
        double[] cidCe = tsa.cidCe(arrayOfTimeSeries, Boolean.TRUE);
        Assert.assertEquals(cidCe[0], 1.30930734141595, DELTA);
        Assert.assertEquals(cidCe[1], 1.30930734141595, DELTA);
        cidCe = tsa.cidCe(arrayOfTimeSeries, Boolean.FALSE);
        Assert.assertEquals(cidCe[0], 2.23606797749979, DELTA);
        Assert.assertEquals(cidCe[1], 2.23606797749979, DELTA);

    }

    @Test
    public void testC3() {
        double[] c3 = tsa.c3(arrayOfTimeSeries, 2);
        Assert.assertEquals(c3[0], 7.5, DELTA);
        Assert.assertEquals(c3[1], 586.5, DELTA);
    }

    @Test
    public void testAbsSumOfChanges() {
        double[][] tss = {{0, 1, 2, 3}, {4, 6, 8, 10}, {11, 14, 17, 20}};
        double[] absSum = tsa.absoluteSumOfChanges(tss);
        Assert.assertEquals(absSum[0], 3, DELTA);
        Assert.assertEquals(absSum[1], 6, DELTA);
        Assert.assertEquals(absSum[2], 9, DELTA);

    }

    @Test
    public void testAbsEnergy() {
        double[][] tss = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}};
        double[] absEnergy = tsa.absEnergy(tss);
        Assert.assertEquals(absEnergy[0], 385, DELTA);
    }

    @Test
    public void testStompSelfJoin() {
        double[] ta = {10, 10, 10, 11, 12, 11, 10, 10, 11, 12, 11, 10, 10, 10};
        MatrixProfile stompSelfJoinResult = tsa.stompSelfJoin(ta, 3);
        double[] expectedIndex = {11, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 0};

        for (int i = 0; i < expectedIndex.length; i++) {
            Assert.assertEquals(stompSelfJoinResult.getProfile()[i], 0, DELTA);
            Assert.assertEquals(stompSelfJoinResult.getIndex()[i], expectedIndex[i], DELTA);

        }

    }

    @Test
    public void testStomp() {
        double[][] tss = {{10, 10, 10, 11, 12, 11, 10, 10, 11, 12, 11, 10, 10, 10},
                {10, 10, 10, 11, 12, 11, 10, 10, 11, 12, 11, 10, 10, 10}};

        double[] expectedIndex = {11, 1, 2, 8, 9, 10, 1, 2, 8, 9, 10, 11};
        MatrixProfile stompResult = tsa.stomp(tss[0], tss[1], 3);
        for (int i = 0; i < expectedIndex.length; i++) {
            Assert.assertEquals(stompResult.getProfile()[i], 0, DELTA);
            Assert.assertEquals(stompResult.getIndex()[i], expectedIndex[i], DELTA);

        }


    }

    @Test
    public void testFindBestNMotifs() {
        double[][] tss = {{10, 11, 10, 10, 10, 10, 9, 10, 10, 10, 10, 10, 11, 10},
                {10, 11, 10, 300, 20, 30, 40, 50, 60, 70, 80, 90, 80, 90}};
        MatrixProfile stompResult = tsa.stomp(tss[0], tss[1], 3);
        Sequence findMotifs = tsa.findBestNMotifs(stompResult.getProfile(), stompResult.getIndex(), 3);

        Assert.assertEquals(findMotifs.getIndex()[0], 0, DELTA);
        Assert.assertEquals(findMotifs.getIndex()[1], 0, DELTA);

        Assert.assertEquals(findMotifs.getSubsequenceIndex()[0], 0, DELTA);
        Assert.assertEquals(findMotifs.getSubsequenceIndex()[1], 10, DELTA);


    }

    @Test
    public void testFindBestNDiscords() {
        double[][] tss = {{10, 11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 10},
                {10, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 9, 10}};
        MatrixProfile stompResult = tsa.stomp(tss[0], tss[1], 3);
        Sequence findMotifs = tsa.findBestNDiscords(stompResult.getProfile(), stompResult.getIndex(), 3);

        Assert.assertEquals(findMotifs.getSubsequenceIndex()[0], 0, DELTA);
        Assert.assertEquals(findMotifs.getSubsequenceIndex()[1], 11, DELTA);


    }
}
