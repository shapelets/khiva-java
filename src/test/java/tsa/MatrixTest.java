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

public class MatrixTest {
    private static final double DELTA = 1e-6;
    Matrix matrix = new Matrix();

    @Test
    public void testStompSelfJoin() {
        double[] ta = {10, 10, 10, 11, 12, 11, 10, 10, 11, 12, 11, 10, 10, 10};
        MatrixProfile stompSelfJoinResult = matrix.stompSelfJoin(ta, 3);
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
        MatrixProfile stompResult = matrix.stomp(tss[0], tss[1], 3);
        for (int i = 0; i < expectedIndex.length; i++) {
            Assert.assertEquals(stompResult.getProfile()[i], 0, DELTA);
            Assert.assertEquals(stompResult.getIndex()[i], expectedIndex[i], DELTA);

        }


    }

    @Test
    public void testFindBestNMotifs() {
        double[][] tss = {{10, 11, 10, 10, 10, 10, 9, 10, 10, 10, 10, 10, 11, 10},
                {10, 11, 10, 300, 20, 30, 40, 50, 60, 70, 80, 90, 80, 90}};
        MatrixProfile stompResult = matrix.stomp(tss[0], tss[1], 3);
        Sequence findMotifs = matrix.findBestNMotifs(stompResult.getProfile(), stompResult.getIndex(), 3);

        Assert.assertEquals(findMotifs.getIndex()[0], 0, DELTA);
        Assert.assertEquals(findMotifs.getIndex()[1], 0, DELTA);

        Assert.assertEquals(findMotifs.getSubsequenceIndex()[0], 0, DELTA);
        Assert.assertEquals(findMotifs.getSubsequenceIndex()[1], 10, DELTA);


    }

    @Test
    public void testFindBestNDiscords() {
        double[][] tss = {{10, 11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 10},
                {10, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 9, 10}};
        MatrixProfile stompResult = matrix.stomp(tss[0], tss[1], 3);
        Sequence findMotifs = matrix.findBestNDiscords(stompResult.getProfile(), stompResult.getIndex(), 3);

        Assert.assertEquals(findMotifs.getSubsequenceIndex()[0], 0, DELTA);
        Assert.assertEquals(findMotifs.getSubsequenceIndex()[1], 11, DELTA);


    }

}
