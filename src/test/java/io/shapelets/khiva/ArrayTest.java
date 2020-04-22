/*
 * Copyright (c) 2019 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ArrayTest {
    private static final double DELTA = 1e-6;

    @BeforeClass
    public static void setUp() {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test(expected = NullPointerException.class)
    public void testDimsNull() {
        final double[] a = {1.0};
        Array.fromPrimitiveArray(a, null);
        fail("testDimsNull should throw");
    }

    @Test(expected = NullPointerException.class)
    public void testDoubleNull() {
        final long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray((double[]) null, dims);
        fail("testDoubleNull should throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoubleMismatchingDims() {
        double[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray(tss, dims);
        fail("testDoubleMismatchingDims should throw");
    }

    @Test(expected = NullPointerException.class)
    public void testFloatNull() {
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray((float[]) null, dims);
        fail("testFloatNull should throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFloatMismatchingDims() {
        float[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray(tss, dims);
        fail("testFloatMismatchingDims should throw");
    }

    @Test(expected = NullPointerException.class)
    public void testIntNull() {
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray((long[]) null, dims);
        fail("testIntNull should throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntMismatchingDims() {
        int[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray(tss, dims);
        fail("testIntMismatchingDims should throw");
    }

    @Test(expected = NullPointerException.class)
    public void testFloatComplexNull() {
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray((FloatComplex[]) null, dims);
        fail("testFloatComplexNull should throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFloatComplexMismatchingDims() {
        FloatComplex[] tss = {new FloatComplex(1, 2), new FloatComplex(3, 4)};
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray(tss, dims);
        fail("testFloatComplexMismatchingDims should throw");
    }

    @Test(expected = NullPointerException.class)
    public void testDoubleComplexNull() {
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray((DoubleComplex[]) null, dims);
        fail("testDoubleComplexNull should throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoubleComplexMismatchingDims() {
        DoubleComplex[] tss = {new DoubleComplex(1, 2), new DoubleComplex(3, 4)};
        long[] dims = {1, 1, 1, 1};
        try {
            Array.fromPrimitiveArray(tss, dims);
            fail("testDoubleComplexMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals("Mismatching dims and array size", e.getMessage());
            throw e;
        }
    }

    @Test(expected = NullPointerException.class)
    public void testBooleanNull() {
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray((boolean[]) null, dims);
        fail("testBooleanNull should throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBooleanMismatchingDims() {
        boolean[] tss = {true, false};
        long[] dims = {1, 1, 1, 1};
        try {
            Array.fromPrimitiveArray(tss, dims);
            fail("testBooleanMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals("Mismatching dims and array size", e.getMessage());
            throw e;
        }
    }

    @Test(expected = NullPointerException.class)
    public void testShortNull() {
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray((short[]) null, dims);
        fail("testShortNull should throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShortMismatchingDims() {
        short[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        try {
            Array.fromPrimitiveArray(tss, dims);
            fail("testShortMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals("Mismatching dims and array size", e.getMessage());
            throw e;
        }
    }

    @Test(expected = NullPointerException.class)
    public void testByteNull() {
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray((byte[]) null, dims);
        fail("testByteNull should throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testByteMismatchingDims() {
        byte[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        try {
            Array.fromPrimitiveArray(tss, dims);
            fail("testByteMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals("Mismatching dims and array size", e.getMessage());
            throw e;
        }
    }

    @Test(expected = NullPointerException.class)
    public void testLongNull() {
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray((long[]) null, dims);
        fail("testLongNull should throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongMismatchingDims() {
        long[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        Array.fromPrimitiveArray(tss, dims);
        fail("testLongMismatchingDims should throw");
    }

    @Test
    public void testByte() {
        byte[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            byte[] result = a.getData();
            byte[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testShort() {
        short[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            short[] result = a.getData();
            short[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testUnsignedShort() {
        short[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = a.as(Dtype.u16)) {
            short[] result = b.getData();
            short[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testLong() {
        long[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            long[] result = a.getData();
            long[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testUnsignedLong() {
        long[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = a.as(Dtype.u64)) {
            long[] result = b.getData();
            long[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(expected, result);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testDim4Null() {
        try {
            Array.dim4(null);
            fail("testDim4Null should throw");
        }
        catch (Exception e) {
            assertEquals("Null dimensions object provided", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDim4FiveDimensions() {
        long[] dims = {1, 1, 1, 1, 1};
        try {
            Array.dim4(dims);
            fail("testDim4FiveDimensions should throw");
        }
        catch (Exception e) {
            assertEquals("ArrayFire supports up to 4 dimensions only", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testGetDims() {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {2, 2, 2, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            long[] result = a.getDims();
            assertArrayEquals(dims, result);
        }
    }

    @Test
    public void testReal1D() {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            double[] result = a.getData();
            double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(expected, result, DELTA);
        }
    }

    @Test
    public void testReal2D() {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {4, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            double[] result = a.getData();
            double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(expected, result, DELTA);
        }
    }

    @Test
    public void testReal3D() {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {2, 2, 2, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            double[] result = a.getData();
            double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(expected, result, DELTA);
        }
    }

    @Test
    public void testReal4D() {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        long[] dims = {2, 2, 2, 2};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            double[] result = a.getData();
            double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
            assertArrayEquals(expected, result, DELTA);
        }
    }

    @Test
    public void testFloatComplex1D() {
        FloatComplex[] tss = {new FloatComplex(1, 5), new FloatComplex(2, 6), new FloatComplex(3, 7),
                              new FloatComplex(4, 8)};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            FloatComplex[] result = a.getData();
            FloatComplex[] expected = {new FloatComplex(1, 5), new FloatComplex(2, 6), new FloatComplex(3, 7),
                                       new FloatComplex(4, 8)};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testFloatComplex2D() {
        FloatComplex[] tss = {new FloatComplex(1, 5), new FloatComplex(2, 6), new FloatComplex(3, 7),
                              new FloatComplex(4, 8), new FloatComplex(9, 13), new FloatComplex(10, 14),
                              new FloatComplex(11, 15), new FloatComplex(12, 16)};
        long[] dims = {4, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            FloatComplex[] result = a.getData();
            FloatComplex[] expected = {new FloatComplex(1, 5), new FloatComplex(2, 6), new FloatComplex(3, 7),
                                       new FloatComplex(4, 8), new FloatComplex(9, 13), new FloatComplex(10, 14),
                                       new FloatComplex(11, 15), new FloatComplex(12, 16)};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testFloatComplex3D() {
        FloatComplex[] tss = {new FloatComplex(1, 1), new FloatComplex(2, 2), new FloatComplex(3, 3),
                              new FloatComplex(4, 4), new FloatComplex(5, 5), new FloatComplex(6, 6),
                              new FloatComplex(7, 7), new FloatComplex(8, 8)};
        long[] dims = {2, 2, 2, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            FloatComplex[] result = a.getData();
            FloatComplex[] expected = {new FloatComplex(1, 1), new FloatComplex(2, 2), new FloatComplex(3, 3),
                                       new FloatComplex(4, 4), new FloatComplex(5, 5), new FloatComplex(6, 6),
                                       new FloatComplex(7, 7), new FloatComplex(8, 8)};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testFloatComplex4D() {
        FloatComplex[] tss = {new FloatComplex(1, 1), new FloatComplex(2, 2), new FloatComplex(3, 3),
                              new FloatComplex(4, 4), new FloatComplex(5, 5), new FloatComplex(6, 6),
                              new FloatComplex(7, 7), new FloatComplex(8, 8), new FloatComplex(9, 9),
                              new FloatComplex(10, 10), new FloatComplex(11, 11), new FloatComplex(12, 12),
                              new FloatComplex(13, 13), new FloatComplex(14, 14), new FloatComplex(15, 15),
                              new FloatComplex(16, 16)};
        long[] dims = {2, 2, 2, 2};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            FloatComplex[] result = a.getData();
            FloatComplex[] expected = {new FloatComplex(1, 1), new FloatComplex(2, 2), new FloatComplex(3, 3),
                                       new FloatComplex(4, 4), new FloatComplex(5, 5), new FloatComplex(6, 6),
                                       new FloatComplex(7, 7), new FloatComplex(8, 8), new FloatComplex(9, 9),
                                       new FloatComplex(10, 10), new FloatComplex(11, 11), new FloatComplex(12, 12),
                                       new FloatComplex(13, 13), new FloatComplex(14, 14), new FloatComplex(15, 15),
                                       new FloatComplex(16, 16)};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testDoubleComplex1D() {
        DoubleComplex[] tss = {new DoubleComplex(1, 5), new DoubleComplex(2, 6), new DoubleComplex(3, 7),
                               new DoubleComplex(4, 8)};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            DoubleComplex[] result = a.getData();
            DoubleComplex[] expected = {new DoubleComplex(1, 5), new DoubleComplex(2, 6), new DoubleComplex(3, 7),
                                        new DoubleComplex(4, 8)};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testDoubleComplex2D() {
        DoubleComplex[] tss = {new DoubleComplex(1, 5), new DoubleComplex(2, 6), new DoubleComplex(3, 7),
                               new DoubleComplex(4, 8), new DoubleComplex(9, 13), new DoubleComplex(10, 14),
                               new DoubleComplex(11, 15), new DoubleComplex(12, 16)};
        long[] dims = {4, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            DoubleComplex[] result = a.getData();
            DoubleComplex[] expected = {new DoubleComplex(1, 5), new DoubleComplex(2, 6), new DoubleComplex(3, 7),
                                        new DoubleComplex(4, 8), new DoubleComplex(9, 13), new DoubleComplex(10, 14),
                                        new DoubleComplex(11, 15), new DoubleComplex(12, 16)};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testDoubleComplex3D() {
        DoubleComplex[] tss = {new DoubleComplex(1, 1), new DoubleComplex(2, 2), new DoubleComplex(3, 3),
                               new DoubleComplex(4, 4), new DoubleComplex(5, 5), new DoubleComplex(6, 6),
                               new DoubleComplex(7, 7), new DoubleComplex(8, 8)};
        long[] dims = {2, 2, 2, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            DoubleComplex[] result = a.getData();
            DoubleComplex[] expected = {new DoubleComplex(1, 1), new DoubleComplex(2, 2), new DoubleComplex(3, 3),
                                        new DoubleComplex(4, 4), new DoubleComplex(5, 5), new DoubleComplex(6, 6),
                                        new DoubleComplex(7, 7), new DoubleComplex(8, 8)};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testDoubleComplex4D() {
        DoubleComplex[] tss = {new DoubleComplex(1, 1), new DoubleComplex(2, 2), new DoubleComplex(3, 3),
                               new DoubleComplex(4, 4), new DoubleComplex(5, 5), new DoubleComplex(6, 6),
                               new DoubleComplex(7, 7), new DoubleComplex(8, 8), new DoubleComplex(9, 9),
                               new DoubleComplex(10, 10), new DoubleComplex(11, 11), new DoubleComplex(12, 12),
                               new DoubleComplex(13, 13), new DoubleComplex(14, 14), new DoubleComplex(15, 15),
                               new DoubleComplex(16, 16)};
        long[] dims = {2, 2, 2, 2};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            DoubleComplex[] result = a.getData();
            DoubleComplex[] expected = {new DoubleComplex(1, 1), new DoubleComplex(2, 2), new DoubleComplex(3, 3),
                                        new DoubleComplex(4, 4), new DoubleComplex(5, 5), new DoubleComplex(6, 6),
                                        new DoubleComplex(7, 7), new DoubleComplex(8, 8), new DoubleComplex(9, 9),
                                        new DoubleComplex(10, 10), new DoubleComplex(11, 11), new DoubleComplex(12, 12),
                                        new DoubleComplex(13, 13), new DoubleComplex(14, 14), new DoubleComplex(15, 15),
                                        new DoubleComplex(16, 16)};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testGetType() {
        long[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims)) {
            assertEquals(a.getType(), Dtype.s64);
        }
    }

    @Test
    public void testJoin() {
        float[] data1 = {1f, 2f, 3f, 4f};
        float[] data2 = {5f, 6f, 7f, 8f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data1, dims); Array b = Array.fromPrimitiveArray(data2, dims); Array c = a.join(1, b)) {
            float[] result = c.getData();
            float[] expected = {1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testAdd() {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = Array.fromPrimitiveArray(data, dims); Array c = a.add(b)) {
            float[] result = c.getData();
            float[] expected = {2f, 4f, 6f, 8f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test(expected = KhivaException.class)
    public void testMulException() {
        float[] data = {1f, 2f, 3f, 4f, 5f};
        long[] dims = {5, 1, 1, 1};
        float[] data2 = {1f, 2f, 3f, 4f};
        long[] dims2 = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = Array.fromPrimitiveArray(data2, dims2)) {
            a.mul(b);
        }
    }

    @Test
    public void testMul() {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = Array.fromPrimitiveArray(data, dims); Array c = a.mul(b)) {
            float[] result = c.getData();
            float[] expected = {1f, 4f, 9f, 16f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testSub() {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = Array.fromPrimitiveArray(data, dims); Array c = a.sub(b)) {
            float[] result = c.getData();
            float[] expected = {0f, 0f, 0f, 0f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testDiv() {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = Array.fromPrimitiveArray(data, dims); Array c = a.div(b)) {
            float[] result = c.getData();
            float[] expected = {1f, 1f, 1f, 1f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testMod() {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.mod(b)) {
            float[] result = c.getData();
            float[] expected = {1f, 0f, 1f, 0f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testPow() {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.pow(b)) {
            float[] result = c.getData();
            float[] expected = {1f, 4f, 9f, 16f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testLt() {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.lt(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, false, false, false};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testGt() {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.gt(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {false, false, true, true};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testLe() {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.le(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, true, false, false};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testGe() {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.ge(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {false, true, true, true};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testEq() {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {1f, 2f, 3f, 5f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.eq(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, true, true, false};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testNe() {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {1f, 2f, 3f, 5f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.ne(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {false, false, false, true};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testBitAnd() {
        boolean[] dataA = {true, true, true, true};
        boolean[] dataB = {true, false, true, false};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.bitAnd(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, false, true, false};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testBitOr() {
        boolean[] dataA = {true, true, true, true};
        boolean[] dataB = {true, false, true, false};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.bitOr(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, true, true, true};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testBitXor() {
        boolean[] dataA = {true, true, true, true};
        boolean[] dataB = {true, false, true, false};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(dataA, dims); Array b = Array.fromPrimitiveArray(dataB, dims); Array c = a.bitXor(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {false, true, false, true};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testBitShiftL() {
        int[] data = {2, 4, 6, 8};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = a.bitShiftL(1)) {
            int[] result = b.getData();
            int[] expected = {4, 8, 12, 16};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testBitShiftR() {
        int[] data = {2, 4, 6, 8};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = a.bitShiftR(1)) {
            int[] result = b.getData();
            int[] expected = {1, 2, 3, 4};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testNot() {
        boolean[] data = {true, false};
        long[] dims = {2, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = a.not()) {
            boolean[] result = b.getData();
            boolean[] expected = {false, true};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testTransposeConjugate() {
        DoubleComplex[] data = {new DoubleComplex(0, -1), new DoubleComplex(2, 1), new DoubleComplex(4, 2),
                                new DoubleComplex(0, -2)};
        long[] dims = {2, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = a.transpose(true)) {
            DoubleComplex[] result = b.getData();
            DoubleComplex[] expected = {new DoubleComplex(0, 1), new DoubleComplex(4, -2), new DoubleComplex(2, -1),
                                        new DoubleComplex(0, 2)};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testTranspose() {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {2, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = a.transpose()) {
            float[] result = b.getData();
            float[] expected = {1f, 3f, 2f, 4f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testCol() {
        float[] data = {1f, 3f, 2f, 4f};
        long[] dims = {2, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = a.col(0)) {
            float[] result = b.getData();
            float[] expected = {1f, 3f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testCols() {
        float[] data = {1f, 4f, 2f, 5f, 3f, 6f};
        long[] dims = {2, 3, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = a.cols(0, 1)) {
            float[] result = b.getData();
            float[] expected = {1f, 4f, 2f, 5f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testRow() {
        float[] data = {1f, 3f, 2f, 4f};
        long[] dims = {2, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = a.row(0)) {
            float[] result = b.getData();
            float[] expected = {1f, 2f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testRows() {
        float[] data = {1f, 3f, 5f, 2f, 4f, 6f};
        long[] dims = {3, 2, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = a.rows(0, 1)) {
            float[] result = b.getData();
            float[] expected = {1f, 3f, 2f, 4f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testMatmul() {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dimsA = {4, 1, 1, 1};
        long[] dimsB = {1, 4, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dimsA); Array b = Array.fromPrimitiveArray(data, dimsB); Array c = a.matmul(b)) {
            float[] result = c.getData();
            float[] expected = {1f, 2f, 3f, 4f, 2f, 4f, 6f, 8f, 3f, 6f, 9f, 12f, 4f, 8f, 12f, 16f};
            assertArrayEquals(expected, result, 1e-6f);
        }
    }

    @Test
    public void testCopy() {
        float[] data = {1.1f, 2.1f, 3.1f, 4.1f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = Array.copy(a); Array c = a.eq(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, true, true, true};
            assertArrayEquals(expected, result);
        }
    }

    @Test
    public void testCopyConstructor() {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(tss, dims); Array b = Array.copy(a)) {
            double[] result = b.getData();
            double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(expected, result, DELTA);
        }
    }

    @Test
    public void testClose() {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        Array a = Array.fromPrimitiveArray(tss, dims);
        a.close();
    }

    @Test
    public void testAs() {
        float[] data = {1.1f, 2.1f, 3.1f, 4.1f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = Array.fromPrimitiveArray(data, dims); Array b = a.as(Dtype.s32)) {
            int[] result = b.getData();
            int[] expected = {1, 2, 3, 4};
            assertArrayEquals(expected, result);
        }
    }
}
