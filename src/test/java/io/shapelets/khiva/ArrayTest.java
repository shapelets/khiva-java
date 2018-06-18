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
import org.junit.Before;
import org.junit.Test;

public class ArrayTest {
    private static final double DELTA = 1e-6;

    @Before
    public void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }


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

    @Test
    public void testAdd() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(data, dims);
        Array b = new Array(data, dims);
        Array c = a.add(b);

        float[] result = c.getData();
        float[] expected = {2f, 4f, 6f, 8f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testMul() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(data, dims);
        Array b = new Array(data, dims);
        Array c = a.mul(b);

        float[] result = c.getData();
        float[] expected = {1f, 4f, 9f, 16f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testSub() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(data, dims);
        Array b = new Array(data, dims);
        Array c = a.sub(b);

        float[] result = c.getData();
        float[] expected = {0f, 0f, 0f, 0f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testDiv() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(data, dims);
        Array b = new Array(data, dims);
        Array c = a.div(b);

        float[] result = c.getData();
        float[] expected = {1f, 1f, 1f, 1f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testMod() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.mod(b);

        float[] result = c.getData();
        float[] expected = {1f, 0f, 1f, 0f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testPow() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.pow(b);

        float[] result = c.getData();
        float[] expected = {1f, 4f, 9f, 16f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testLt() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.lt(b);

        boolean[] result = c.getData();
        boolean[] expected = {true, false, false, false};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testGt() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.gt(b);

        boolean[] result = c.getData();
        boolean[] expected = {false, false, true, true};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testLe() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.le(b);

        boolean[] result = c.getData();
        boolean[] expected = {true, true, false, false};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testGe() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.ge(b);

        boolean[] result = c.getData();
        boolean[] expected = {false, true, true, true};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testEq() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {1f, 2f, 3f, 5f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.eq(b);

        boolean[] result = c.getData();
        boolean[] expected = {true, true, true, false};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testNe() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {1f, 2f, 3f, 5f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.ne(b);

        boolean[] result = c.getData();
        boolean[] expected = {false, false, false, true};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testBitAnd() throws Exception {
        boolean[] dataA = {true, true, true, true};
        boolean[] dataB = {true, false, true, false};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.bitAnd(b);

        boolean[] result = c.getData();
        boolean[] expected = {true, false, true, false};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testBitOr() throws Exception {
        boolean[] dataA = {true, true, true, true};
        boolean[] dataB = {true, false, true, false};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.bitOr(b);

        boolean[] result = c.getData();
        boolean[] expected = {true, true, true, true};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testBitXor() throws Exception {
        boolean[] dataA = {true, true, true, true};
        boolean[] dataB = {true, false, true, false};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(dataA, dims);
        Array b = new Array(dataB, dims);
        Array c = a.bitXor(b);

        boolean[] result = c.getData();
        boolean[] expected = {false, true, false, true};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testBitShiftL() throws Exception {
        int[] data = {2, 4, 6, 8};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(data, dims);
        Array b = a.bitShiftL(1);

        int[] result = b.getData();
        int[] expected = {4, 8, 12, 16};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testBitShiftR() throws Exception {
        int[] data = {2, 4, 6, 8};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(data, dims);
        Array b = a.bitShiftR(1);

        int[] result = b.getData();
        int[] expected = {1, 2, 3, 4};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testTransposeConjugate() throws Exception {
        DoubleComplex[] data = {new DoubleComplex(0, -1), new DoubleComplex(2, 1),
                new DoubleComplex(4, 2), new DoubleComplex(0, -2)};
        long[] dims = {2, 2, 1, 1};
        Array a = new Array(data, dims);
        Array b = a.transpose(true);

        DoubleComplex[] result = b.getData();
        DoubleComplex[] expected = {new DoubleComplex(0, 1), new DoubleComplex(4, -2),
                new DoubleComplex(2, -1), new DoubleComplex(0, 2)};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testTranspose() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {2, 2, 1, 1};
        Array a = new Array(data, dims);
        Array b = a.transpose();

        float[] result = b.getData();
        float[] expected = {1f, 3f, 2f, 4f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testCol() throws Exception {
        float[] data = {1f, 3f, 2f, 4f};
        long[] dims = {2, 2, 1, 1};
        Array a = new Array(data, dims);
        Array b = a.col(0);

        float[] result = b.getData();
        float[] expected = {1f, 3f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testCols() throws Exception {
        float[] data = {1f, 4f, 2f, 5f, 3f, 6f};
        long[] dims = {2, 3, 1, 1};
        Array a = new Array(data, dims);
        Array b = a.cols(0, 1);

        float[] result = b.getData();
        float[] expected = {1f, 4f, 2f, 5f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testRow() throws Exception {
        float[] data = {1f, 3f, 2f, 4f};
        long[] dims = {2, 2, 1, 1};
        Array a = new Array(data, dims);
        Array b = a.row(0);

        float[] result = b.getData();
        float[] expected = {1f, 2f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testRows() throws Exception {
        float[] data = {1f, 3f, 5f, 2f, 4f, 6f};
        long[] dims = {3, 2, 1, 1};
        Array a = new Array(data, dims);
        Array b = a.rows(0, 1);

        float[] result = b.getData();
        float[] expected = {1f, 3f, 2f, 4f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testMatmul() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dimsA = {4, 1, 1, 1};
        long[] dimsB = {1, 4, 1, 1};
        Array a = new Array(data, dimsA);
        Array b = new Array(data, dimsB);
        Array c = a.matmul(b);

        float[] result = c.getData();
        float[] expected = {1f, 2f, 3f, 4f, 2f, 4f, 6f, 8f, 3f, 6f, 9f, 12f, 4f, 8f, 12f, 16f};
        Assert.assertArrayEquals(result, expected, 1e-6f);
    }

    @Test
    public void testCopy() throws Exception {
        float[] data = {1.1f, 2.1f, 3.1f, 4.1f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(data, dims);
        Array b = a.copy();
        Array c = a.eq(b);

        boolean[] result = c.getData();
        boolean[] expected = {true, true, true, true};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void testAs() throws Exception {
        float[] data = {1.1f, 2.1f, 3.1f, 4.1f};
        long[] dims = {4, 1, 1, 1};
        Array a = new Array(data, dims);
        Array b = a.as(Array.Dtype.s32);

        int[] result = b.getData();
        int[] expected = {1, 2, 3, 4};
        Assert.assertArrayEquals(result, expected);
    }
}
