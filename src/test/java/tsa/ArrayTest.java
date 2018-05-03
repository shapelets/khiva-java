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

public class ArrayTest {
    private static final double DELTA = 1e-6;

    @Test
    public void testReal1D() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        Array a = new Array(tss, dims);
        double[] result = a.getData();
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
        Assert.assertArrayEquals(result, expected, DELTA);
    }

    @Test
    public void testReal2D() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {4, 2, 1, 1};
        Array a = new Array(tss, dims);
        double[] result = a.getData();
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
        Assert.assertArrayEquals(result, expected, DELTA);
    }

    @Test
    public void testReal3D() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {2, 2, 2, 1};
        Array a = new Array(tss, dims);
        double[] result = a.getData();
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
        Assert.assertArrayEquals(result, expected, DELTA);
    }

    @Test
    public void testReal4D() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        long[] dims = {2, 2, 2, 2};
        Array a = new Array(tss, dims);
        double[] result = a.getData();
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        Assert.assertArrayEquals(result, expected, DELTA);
    }

    @Test
    public void testFloatComplex1D() throws Exception {
        FloatComplex[] tss = {new FloatComplex(1, 5), new FloatComplex(2, 6),
                new FloatComplex(3, 7), new FloatComplex(4, 8)};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(tss, dims);
        FloatComplex[] result = a.getData();
        FloatComplex[] expected = {new FloatComplex(1, 5), new FloatComplex(2, 6),
                new FloatComplex(3, 7), new FloatComplex(4, 8)};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testFloatComplex2D() throws Exception {
        FloatComplex[] tss = {new FloatComplex(1, 5), new FloatComplex(2, 6),
                new FloatComplex(3, 7), new FloatComplex(4, 8), new FloatComplex(9, 13),
                new FloatComplex(10, 14), new FloatComplex(11, 15), new FloatComplex(12, 16)};
        long[] dims = {4, 2, 1, 1};
        Array a = new Array(tss, dims);
        FloatComplex[] result = a.getData();
        FloatComplex[] expected = {new FloatComplex(1, 5), new FloatComplex(2, 6),
                new FloatComplex(3, 7), new FloatComplex(4, 8), new FloatComplex(9, 13),
                new FloatComplex(10, 14), new FloatComplex(11, 15), new FloatComplex(12, 16)};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testFloatComplex3D() throws Exception {
        FloatComplex[] tss = {new FloatComplex(1, 1), new FloatComplex(2, 2),
                new FloatComplex(3, 3), new FloatComplex(4, 4), new FloatComplex(5, 5),
                new FloatComplex(6, 6), new FloatComplex(7, 7), new FloatComplex(8, 8)};
        long[] dims = {2, 2, 2, 1};
        Array a = new Array(tss, dims);
        FloatComplex[] result = a.getData();
        FloatComplex[] expected = {new FloatComplex(1, 1), new FloatComplex(2, 2),
                new FloatComplex(3, 3), new FloatComplex(4, 4), new FloatComplex(5, 5),
                new FloatComplex(6, 6), new FloatComplex(7, 7), new FloatComplex(8, 8)};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testFloatComplex4D() throws Exception {
        FloatComplex[] tss = {new FloatComplex(1, 1), new FloatComplex(2, 2),
                new FloatComplex(3, 3), new FloatComplex(4, 4), new FloatComplex(5, 5),
                new FloatComplex(6, 6), new FloatComplex(7, 7), new FloatComplex(8, 8),
                new FloatComplex(9, 9), new FloatComplex(10, 10), new FloatComplex(11, 11),
                new FloatComplex(12, 12), new FloatComplex(13, 13), new FloatComplex(14, 14),
                new FloatComplex(15, 15), new FloatComplex(16, 16)};
        long[] dims = {2, 2, 2, 2};
        Array a = new Array(tss, dims);
        FloatComplex[] result = a.getData();
        FloatComplex[] expected = {new FloatComplex(1, 1), new FloatComplex(2, 2),
                new FloatComplex(3, 3), new FloatComplex(4, 4), new FloatComplex(5, 5),
                new FloatComplex(6, 6), new FloatComplex(7, 7), new FloatComplex(8, 8),
                new FloatComplex(9, 9), new FloatComplex(10, 10), new FloatComplex(11, 11),
                new FloatComplex(12, 12), new FloatComplex(13, 13), new FloatComplex(14, 14),
                new FloatComplex(15, 15), new FloatComplex(16, 16)};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testDoubleComplex1D() throws Exception {
        DoubleComplex[] tss = {new DoubleComplex(1, 5), new DoubleComplex(2, 6),
                new DoubleComplex(3, 7), new DoubleComplex(4, 8)};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(tss, dims);
        DoubleComplex[] result = a.getData();
        DoubleComplex[] expected = {new DoubleComplex(1, 5), new DoubleComplex(2, 6),
                new DoubleComplex(3, 7), new DoubleComplex(4, 8)};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testDoubleComplex2D() throws Exception {
        DoubleComplex[] tss = {new DoubleComplex(1, 5), new DoubleComplex(2, 6),
                new DoubleComplex(3, 7), new DoubleComplex(4, 8), new DoubleComplex(9, 13),
                new DoubleComplex(10, 14), new DoubleComplex(11, 15), new DoubleComplex(12, 16)};
        long[] dims = {4, 2, 1, 1};
        Array a = new Array(tss, dims);
        DoubleComplex[] result = a.getData();
        DoubleComplex[] expected = {new DoubleComplex(1, 5), new DoubleComplex(2, 6),
                new DoubleComplex(3, 7), new DoubleComplex(4, 8), new DoubleComplex(9, 13),
                new DoubleComplex(10, 14), new DoubleComplex(11, 15), new DoubleComplex(12, 16)};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testDoubleComplex3D() throws Exception {
        DoubleComplex[] tss = {new DoubleComplex(1, 1), new DoubleComplex(2, 2),
                new DoubleComplex(3, 3), new DoubleComplex(4, 4), new DoubleComplex(5, 5),
                new DoubleComplex(6, 6), new DoubleComplex(7, 7), new DoubleComplex(8, 8)};
        long[] dims = {2, 2, 2, 1};
        Array a = new Array(tss, dims);
        DoubleComplex[] result = a.getData();
        DoubleComplex[] expected = {new DoubleComplex(1, 1), new DoubleComplex(2, 2),
                new DoubleComplex(3, 3), new DoubleComplex(4, 4), new DoubleComplex(5, 5),
                new DoubleComplex(6, 6), new DoubleComplex(7, 7), new DoubleComplex(8, 8)};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testDoubleComplex4D() throws Exception {
        DoubleComplex[] tss = {new DoubleComplex(1, 1), new DoubleComplex(2, 2),
                new DoubleComplex(3, 3), new DoubleComplex(4, 4), new DoubleComplex(5, 5),
                new DoubleComplex(6, 6), new DoubleComplex(7, 7), new DoubleComplex(8, 8),
                new DoubleComplex(9, 9), new DoubleComplex(10, 10), new DoubleComplex(11, 11),
                new DoubleComplex(12, 12), new DoubleComplex(13, 13), new DoubleComplex(14, 14),
                new DoubleComplex(15, 15), new DoubleComplex(16, 16)};
        long[] dims = {2, 2, 2, 2};
        Array a = new Array(tss, dims);
        DoubleComplex[] result = a.getData();
        DoubleComplex[] expected = {new DoubleComplex(1, 1), new DoubleComplex(2, 2),
                new DoubleComplex(3, 3), new DoubleComplex(4, 4), new DoubleComplex(5, 5),
                new DoubleComplex(6, 6), new DoubleComplex(7, 7), new DoubleComplex(8, 8),
                new DoubleComplex(9, 9), new DoubleComplex(10, 10), new DoubleComplex(11, 11),
                new DoubleComplex(12, 12), new DoubleComplex(13, 13), new DoubleComplex(14, 14),
                new DoubleComplex(15, 15), new DoubleComplex(16, 16)};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testGetType() throws Exception {
        long[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        Array a = new Array(tss, dims);
        Assert.assertEquals(a.getType(), Array.Dtype.s64);
    }
}
