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
    public static void setUp() throws Exception {
        Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
    }

    @Test (expected = Exception.class)
    public void testDoubleNull() throws Exception {
        final long[] dims = {1, 1, 1, 1};
        try {
            new Array((double[]) null, dims);
            fail("testDoubleNull should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Null elems object provided");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testDoubleMismatchingDims() throws Exception {
        double[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        try {
            new Array(tss, dims);
            fail("testDoubleMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Mismatching dims and array size");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testFloatNull() throws Exception {
        long[] dims = {1, 1, 1, 1};
        try {
            new Array((float[]) null, dims);
            fail("testFloatNull should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Null elems object provided");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testFloatMismatchingDims() throws Exception {
        float[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        try {
            new Array(tss, dims);
            fail("testFloatMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Mismatching dims and array size");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testIntNull() throws Exception {
        long[] dims = {1, 1, 1, 1};
        try {
            new Array((long[]) null, dims);
            fail("testIntNull should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Null elems object provided");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testIntMismatchingDims() throws Exception {
        int[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        try {
            new Array(tss, dims);
            fail("testIntMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Mismatching dims and array size");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testFloatComplexNull() throws Exception {
        long[] dims = {1, 1, 1, 1};
        try {
            new Array((FloatComplex[]) null, dims);
            fail("testFloatComplexNull should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Null elems object provided");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testFloatComplexMismatchingDims() throws Exception {
        FloatComplex[] tss = {new FloatComplex(1, 2), new FloatComplex(3, 4)};
        long[] dims = {1, 1, 1, 1};
        try {
            new Array(tss, dims);
            fail("testFloatComplexMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Mismatching dims and array size");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testDoubleComplexNull() throws Exception {
        long[] dims = {1, 1, 1, 1};
        try {
            new Array((DoubleComplex[]) null, dims);
            fail("testDoubleComplexNull should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Null elems object provided");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testDoubleComplexMismatchingDims() throws Exception {
        DoubleComplex[] tss = {new DoubleComplex(1, 2), new DoubleComplex(3, 4)};
        long[] dims = {1, 1, 1, 1};
        try {
            new Array(tss, dims);
            fail("testDoubleComplexMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Mismatching dims and array size");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testBooleanNull() throws Exception {
        long[] dims = {1, 1, 1, 1};
        try {
            new Array((boolean[]) null, dims);
            fail("testBooleanNull should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Null elems object provided");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testBooleanMismatchingDims() throws Exception {
        boolean[] tss = {true, false};
        long[] dims = {1, 1, 1, 1};
        try {
            new Array(tss, dims);
            fail("testBooleanMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Mismatching dims and array size");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testShortNull() throws Exception {
        long[] dims = {1, 1, 1, 1};
        try {
            new Array((short[]) null, dims);
            fail("testShortNull should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Null elems object provided");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testShortMismatchingDims() throws Exception {
        short[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        try {
            new Array(tss, dims);
            fail("testShortMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Mismatching dims and array size");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testByteNull() throws Exception {
        long[] dims = {1, 1, 1, 1};
        try {
            new Array((byte[]) null, dims);
            fail("testByteNull should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Null elems object provided");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testByteMismatchingDims() throws Exception {
        byte[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        try {
            new Array(tss, dims);
            fail("testByteMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Mismatching dims and array size");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testLongNull() throws Exception {
        long[] dims = {1, 1, 1, 1};
        try {
            new Array((long[]) null, dims);
            fail("testLongNull should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Null elems object provided");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testLongMismatchingDims() throws Exception {
        long[] tss = {1, 2};
        long[] dims = {1, 1, 1, 1};
        try {
            new Array(tss, dims);
            fail("testLongMismatchingDims should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Mismatching dims and array size");
            throw e;
        }
    }

    @Test
    public void testByte() throws Exception {
        byte[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = new Array(tss, dims)) {
            byte[] result = a.getData();
            byte[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testShort() throws Exception {
        short[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = new Array(tss, dims)) {
            short[] result = a.getData();
            short[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testUnsignedShort() throws Exception {
        short[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = new Array(tss, dims); Array b = a.as(Array.Dtype.u16)) {
            short[] result = b.getData();
            short[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testLong() throws Exception {
        long[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = new Array(tss, dims)) {
            long[] result = a.getData();
            long[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testUnsignedLong() throws Exception {
        long[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = new Array(tss, dims); Array b = a.as(Array.Dtype.u64)) {
            long[] result = b.getData();
            long[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(result, expected);
        }
    }

    @Test (expected = Exception.class)
    public void testDim4Null() throws Exception {
        try {
            Array.dim4(null);
            fail("testDim4Null should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Null dimensions object provided");
            throw e;
        }
    }

    @Test (expected = Exception.class)
    public void testDim4FiveDimensions() throws Exception {
        long[] dims = {1, 1, 1, 1, 1};
        try {
            Array.dim4(dims);
            fail("testDim4FiveDimensions should throw");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "ArrayFire supports up to 4 dimensions only");
            throw e;
        }
    }

    @Test
    public void testGetDims() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {2, 2, 2, 1};
        try (Array a = new Array(tss, dims)) {
            long[] result = a.getDims();
            assertArrayEquals(result, dims);
        }
    }

    @Test
    public void testReal1D() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = new Array(tss, dims)) {
            double[] result = a.getData();
            double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void testReal2D() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {4, 2, 1, 1};
        try (Array a = new Array(tss, dims)) {
            double[] result = a.getData();
            double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void testReal3D() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {2, 2, 2, 1};
        try (Array a = new Array(tss, dims)) {
            double[] result = a.getData();
            double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void testReal4D() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        long[] dims = {2, 2, 2, 2};
        try (Array a = new Array(tss, dims)) {
            double[] result = a.getData();
            double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
            assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void testFloatComplex1D() throws Exception {
        FloatComplex[] tss = {new FloatComplex(1, 5), new FloatComplex(2, 6), new FloatComplex(3, 7),
                              new FloatComplex(4, 8)};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(tss, dims)) {
            FloatComplex[] result = a.getData();
            FloatComplex[] expected = {new FloatComplex(1, 5), new FloatComplex(2, 6), new FloatComplex(3, 7),
                                       new FloatComplex(4, 8)};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testFloatComplex2D() throws Exception {
        FloatComplex[] tss = {new FloatComplex(1, 5), new FloatComplex(2, 6), new FloatComplex(3, 7),
                              new FloatComplex(4, 8), new FloatComplex(9, 13), new FloatComplex(10, 14),
                              new FloatComplex(11, 15), new FloatComplex(12, 16)};
        long[] dims = {4, 2, 1, 1};
        try (Array a = new Array(tss, dims)) {
            FloatComplex[] result = a.getData();
            FloatComplex[] expected = {new FloatComplex(1, 5), new FloatComplex(2, 6), new FloatComplex(3, 7),
                                       new FloatComplex(4, 8), new FloatComplex(9, 13), new FloatComplex(10, 14),
                                       new FloatComplex(11, 15), new FloatComplex(12, 16)};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testFloatComplex3D() throws Exception {
        FloatComplex[] tss = {new FloatComplex(1, 1), new FloatComplex(2, 2), new FloatComplex(3, 3),
                              new FloatComplex(4, 4), new FloatComplex(5, 5), new FloatComplex(6, 6),
                              new FloatComplex(7, 7), new FloatComplex(8, 8)};
        long[] dims = {2, 2, 2, 1};
        try (Array a = new Array(tss, dims)) {
            FloatComplex[] result = a.getData();
            FloatComplex[] expected = {new FloatComplex(1, 1), new FloatComplex(2, 2), new FloatComplex(3, 3),
                                       new FloatComplex(4, 4), new FloatComplex(5, 5), new FloatComplex(6, 6),
                                       new FloatComplex(7, 7), new FloatComplex(8, 8)};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testFloatComplex4D() throws Exception {
        FloatComplex[] tss = {new FloatComplex(1, 1), new FloatComplex(2, 2), new FloatComplex(3, 3),
                              new FloatComplex(4, 4), new FloatComplex(5, 5), new FloatComplex(6, 6),
                              new FloatComplex(7, 7), new FloatComplex(8, 8), new FloatComplex(9, 9),
                              new FloatComplex(10, 10), new FloatComplex(11, 11), new FloatComplex(12, 12),
                              new FloatComplex(13, 13), new FloatComplex(14, 14), new FloatComplex(15, 15),
                              new FloatComplex(16, 16)};
        long[] dims = {2, 2, 2, 2};
        try (Array a = new Array(tss, dims)) {
            FloatComplex[] result = a.getData();
            FloatComplex[] expected = {new FloatComplex(1, 1), new FloatComplex(2, 2), new FloatComplex(3, 3),
                                       new FloatComplex(4, 4), new FloatComplex(5, 5), new FloatComplex(6, 6),
                                       new FloatComplex(7, 7), new FloatComplex(8, 8), new FloatComplex(9, 9),
                                       new FloatComplex(10, 10), new FloatComplex(11, 11), new FloatComplex(12, 12),
                                       new FloatComplex(13, 13), new FloatComplex(14, 14), new FloatComplex(15, 15),
                                       new FloatComplex(16, 16)};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testDoubleComplex1D() throws Exception {
        DoubleComplex[] tss = {new DoubleComplex(1, 5), new DoubleComplex(2, 6), new DoubleComplex(3, 7),
                               new DoubleComplex(4, 8)};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(tss, dims)) {
            DoubleComplex[] result = a.getData();
            DoubleComplex[] expected = {new DoubleComplex(1, 5), new DoubleComplex(2, 6), new DoubleComplex(3, 7),
                                        new DoubleComplex(4, 8)};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testDoubleComplex2D() throws Exception {
        DoubleComplex[] tss = {new DoubleComplex(1, 5), new DoubleComplex(2, 6), new DoubleComplex(3, 7),
                               new DoubleComplex(4, 8), new DoubleComplex(9, 13), new DoubleComplex(10, 14),
                               new DoubleComplex(11, 15), new DoubleComplex(12, 16)};
        long[] dims = {4, 2, 1, 1};
        try (Array a = new Array(tss, dims)) {
            DoubleComplex[] result = a.getData();
            DoubleComplex[] expected = {new DoubleComplex(1, 5), new DoubleComplex(2, 6), new DoubleComplex(3, 7),
                                        new DoubleComplex(4, 8), new DoubleComplex(9, 13), new DoubleComplex(10, 14),
                                        new DoubleComplex(11, 15), new DoubleComplex(12, 16)};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testDoubleComplex3D() throws Exception {
        DoubleComplex[] tss = {new DoubleComplex(1, 1), new DoubleComplex(2, 2), new DoubleComplex(3, 3),
                               new DoubleComplex(4, 4), new DoubleComplex(5, 5), new DoubleComplex(6, 6),
                               new DoubleComplex(7, 7), new DoubleComplex(8, 8)};
        long[] dims = {2, 2, 2, 1};
        try (Array a = new Array(tss, dims)) {
            DoubleComplex[] result = a.getData();
            DoubleComplex[] expected = {new DoubleComplex(1, 1), new DoubleComplex(2, 2), new DoubleComplex(3, 3),
                                        new DoubleComplex(4, 4), new DoubleComplex(5, 5), new DoubleComplex(6, 6),
                                        new DoubleComplex(7, 7), new DoubleComplex(8, 8)};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testDoubleComplex4D() throws Exception {
        DoubleComplex[] tss = {new DoubleComplex(1, 1), new DoubleComplex(2, 2), new DoubleComplex(3, 3),
                               new DoubleComplex(4, 4), new DoubleComplex(5, 5), new DoubleComplex(6, 6),
                               new DoubleComplex(7, 7), new DoubleComplex(8, 8), new DoubleComplex(9, 9),
                               new DoubleComplex(10, 10), new DoubleComplex(11, 11), new DoubleComplex(12, 12),
                               new DoubleComplex(13, 13), new DoubleComplex(14, 14), new DoubleComplex(15, 15),
                               new DoubleComplex(16, 16)};
        long[] dims = {2, 2, 2, 2};
        try (Array a = new Array(tss, dims)) {
            DoubleComplex[] result = a.getData();
            DoubleComplex[] expected = {new DoubleComplex(1, 1), new DoubleComplex(2, 2), new DoubleComplex(3, 3),
                                        new DoubleComplex(4, 4), new DoubleComplex(5, 5), new DoubleComplex(6, 6),
                                        new DoubleComplex(7, 7), new DoubleComplex(8, 8), new DoubleComplex(9, 9),
                                        new DoubleComplex(10, 10), new DoubleComplex(11, 11), new DoubleComplex(12, 12),
                                        new DoubleComplex(13, 13), new DoubleComplex(14, 14), new DoubleComplex(15, 15),
                                        new DoubleComplex(16, 16)};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testGetType() throws Exception {
        long[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = new Array(tss, dims)) {
            assertEquals(a.getType(), Array.Dtype.s64);
        }
    }

    @Test
    public void testJoin() throws Exception {
        float[] data1 = {1f, 2f, 3f, 4f};
        float[] data2 = {5f, 6f, 7f, 8f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(data1, dims); Array b = new Array(data2, dims); Array c = a.join(1, b)) {
            float[] result = c.getData();
            float[] expected = {1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testAdd() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(data, dims); Array b = new Array(data, dims); Array c = a.add(b)) {
            float[] result = c.getData();
            float[] expected = {2f, 4f, 6f, 8f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test(expected = Exception.class)
    public void testMulException() throws Exception {
        float[] data = {1f, 2f, 3f, 4f, 5f};
        long[] dims = {5, 1, 1, 1};
        float[] data2 = {1f, 2f, 3f, 4f};
        long[] dims2 = {4, 1, 1, 1};
        try (Array a = new Array(data, dims); Array b = new Array(data2, dims2)) {
            a.mul(b);
        }
    }

    @Test
    public void testMul() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(data, dims); Array b = new Array(data, dims); Array c = a.mul(b)) {
            float[] result = c.getData();
            float[] expected = {1f, 4f, 9f, 16f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testSub() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(data, dims); Array b = new Array(data, dims); Array c = a.sub(b)) {
            float[] result = c.getData();
            float[] expected = {0f, 0f, 0f, 0f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testDiv() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(data, dims); Array b = new Array(data, dims); Array c = a.div(b)) {
            float[] result = c.getData();
            float[] expected = {1f, 1f, 1f, 1f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testMod() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.mod(b)) {
            float[] result = c.getData();
            float[] expected = {1f, 0f, 1f, 0f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testPow() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.pow(b)) {
            float[] result = c.getData();
            float[] expected = {1f, 4f, 9f, 16f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testLt() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.lt(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, false, false, false};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testGt() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.gt(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {false, false, true, true};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testLe() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.le(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, true, false, false};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testGe() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {2f, 2f, 2f, 2f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.ge(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {false, true, true, true};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testEq() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {1f, 2f, 3f, 5f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.eq(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, true, true, false};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testNe() throws Exception {
        float[] dataA = {1f, 2f, 3f, 4f};
        float[] dataB = {1f, 2f, 3f, 5f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.ne(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {false, false, false, true};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testBitAnd() throws Exception {
        boolean[] dataA = {true, true, true, true};
        boolean[] dataB = {true, false, true, false};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.bitAnd(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, false, true, false};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testBitOr() throws Exception {
        boolean[] dataA = {true, true, true, true};
        boolean[] dataB = {true, false, true, false};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.bitOr(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, true, true, true};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testBitXor() throws Exception {
        boolean[] dataA = {true, true, true, true};
        boolean[] dataB = {true, false, true, false};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(dataA, dims); Array b = new Array(dataB, dims); Array c = a.bitXor(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {false, true, false, true};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testBitShiftL() throws Exception {
        int[] data = {2, 4, 6, 8};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.bitShiftL(1)) {
            int[] result = b.getData();
            int[] expected = {4, 8, 12, 16};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testBitShiftR() throws Exception {
        int[] data = {2, 4, 6, 8};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.bitShiftR(1)) {
            int[] result = b.getData();
            int[] expected = {1, 2, 3, 4};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testNot() throws Exception {
        boolean[] data = {true, false};
        long[] dims = {2, 1, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.not()) {
            boolean[] result = b.getData();
            boolean[] expected = {false, true};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testTransposeConjugate() throws Exception {
        DoubleComplex[] data = {new DoubleComplex(0, -1), new DoubleComplex(2, 1), new DoubleComplex(4, 2),
                                new DoubleComplex(0, -2)};
        long[] dims = {2, 2, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.transpose(true)) {
            DoubleComplex[] result = b.getData();
            DoubleComplex[] expected = {new DoubleComplex(0, 1), new DoubleComplex(4, -2), new DoubleComplex(2, -1),
                                        new DoubleComplex(0, 2)};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testTranspose() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dims = {2, 2, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.transpose()) {
            float[] result = b.getData();
            float[] expected = {1f, 3f, 2f, 4f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testCol() throws Exception {
        float[] data = {1f, 3f, 2f, 4f};
        long[] dims = {2, 2, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.col(0)) {
            float[] result = b.getData();
            float[] expected = {1f, 3f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testCols() throws Exception {
        float[] data = {1f, 4f, 2f, 5f, 3f, 6f};
        long[] dims = {2, 3, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.cols(0, 1)) {
            float[] result = b.getData();
            float[] expected = {1f, 4f, 2f, 5f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testRow() throws Exception {
        float[] data = {1f, 3f, 2f, 4f};
        long[] dims = {2, 2, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.row(0)) {
            float[] result = b.getData();
            float[] expected = {1f, 2f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testRows() throws Exception {
        float[] data = {1f, 3f, 5f, 2f, 4f, 6f};
        long[] dims = {3, 2, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.rows(0, 1)) {
            float[] result = b.getData();
            float[] expected = {1f, 3f, 2f, 4f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testMatmul() throws Exception {
        float[] data = {1f, 2f, 3f, 4f};
        long[] dimsA = {4, 1, 1, 1};
        long[] dimsB = {1, 4, 1, 1};
        try (Array a = new Array(data, dimsA); Array b = new Array(data, dimsB); Array c = a.matmul(b)) {
            float[] result = c.getData();
            float[] expected = {1f, 2f, 3f, 4f, 2f, 4f, 6f, 8f, 3f, 6f, 9f, 12f, 4f, 8f, 12f, 16f};
            assertArrayEquals(result, expected, 1e-6f);
        }
    }

    @Test
    public void testCopy() throws Exception {
        float[] data = {1.1f, 2.1f, 3.1f, 4.1f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.copy(); Array c = a.eq(b)) {
            boolean[] result = c.getData();
            boolean[] expected = {true, true, true, true};
            assertArrayEquals(result, expected);
        }
    }

    @Test
    public void testCopyConstructor() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        try (Array a = new Array(tss, dims); Array b = new Array(a)) {
            double[] result = b.getData();
            double[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
            assertArrayEquals(result, expected, DELTA);
        }
    }

    @Test
    public void testClose() throws Exception {
        double[] tss = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] dims = {8, 1, 1, 1};
        Array a = new Array(tss, dims);
        a.close();
    }

    @Test
    public void testAs() throws Exception {
        float[] data = {1.1f, 2.1f, 3.1f, 4.1f};
        long[] dims = {4, 1, 1, 1};
        try (Array a = new Array(data, dims); Array b = a.as(Array.Dtype.s32)) {
            int[] result = b.getData();
            int[] expected = {1, 2, 3, 4};
            assertArrayEquals(result, expected);
        }
    }
}
