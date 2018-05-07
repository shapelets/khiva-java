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

public class DimensionalityTest {
    private static final double DELTA = 1e-6;

    @Test
    public void testRamerDouglasPeucker() throws Exception {
        float[] tss = {0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 0f, 0.1f,
                -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f};
        long[] dims = {10, 2, 1, 1};
        Array a = new Array(tss, dims);
        float[] result = Dimensionality.ramerDouglasPeucker(a, 1.0).getData();
        float[] expected = {0f, 2f, 3f, 6f, 9f, 0f, -0.1f, 5.0f, 8.1f, 9.0f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testVisvalingam() throws Exception {
        float[] tss = {0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 0f, 0.1f,
                -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f};
        long[] dims = {10, 2, 1, 1};
        Array a = new Array(tss, dims);
        float[] result = Dimensionality.visvalingam(a, 5).getData();
        float[] expected = {0f, 2f, 5f, 7f, 9f, 0f, -0.1f, 7.0f, 9.0f, 9.0f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testPaa() throws Exception {
        float[] tss = {0.0f, 0.1f, -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f,
                0.0f, 0.1f, -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f};
        long[] dims = {10, 2, 1, 1};
        Array a = new Array(tss, dims);
        float[] result = Dimensionality.paa(a, 5).getData();
        float[] expected = {0.05f, 2.45f, 6.5f, 8.55f, 9.0f, 0.05f, 2.45f, 6.5f, 8.55f, 9.0f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testSax() throws Exception {
        float[] tss = {0.05f, 2.45f, 6.5f, 8.55f, 9.0f, 0.05f, 2.45f, 6.5f, 8.55f, 9.0f};
        long[] dims = {5, 2, 1, 1};
        Array a = new Array(tss, dims);
        int[] result = Dimensionality.sax(a, 3).getData();
        int[] expected = {0, 0, 1, 2, 2, 0, 0, 1, 2, 2};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testPip() throws Exception {
        float[] tss = {0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f,
                0.0f, 0.1f, -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f};
        long[] dims = {10, 2, 1, 1};
        Array a = new Array(tss, dims);
        float[] result = Dimensionality.pip(a, 6).getData();
        float[] expected = {0.0f, 2.0f, 4.0f, 5.0f, 6.0f, 9.0f, 0.0f, -0.1f, 6.0f, 7.0f, 8.1f, 9.0f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }
}
