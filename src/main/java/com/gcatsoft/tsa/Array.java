/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package com.gcatsoft.tsa;

/**
 * TSA Array Class.
 */
public class Array extends Library implements AutoCloseable {

    private long reference;

    /**
     * Data type of the Array
     */
    public enum Dtype {
        /**
         * Floating point of single precision. tsa.dtype.
         */
        f32,
        /**
         * Complex floating point of single precision. tsa.dtype.
         */
        c32,
        /**
         * Floating point of double precision. tsa.dtype.
         */
        f64,
        /**
         * Complex floating point of double precision. tsa.dtype.
         */
        c64,
        /**
         * Boolean. tsa.dtype.
         */
        b8,
        /**
         * 32 bits Int. tsa.dtype.
         */
        s32,
        /**
         * 32 bits Unsigned Int. tsa.dtype.
         */
        u32,
        /**
         * 32 bits Unsigned Int. tsa.dtype.
         */
        u8,
        /**
         * 64 bits Integer. tsa.dtype.
         */
        s64,
        /**
         * 64 bits Unsigned Int. tsa.dtype.
         */
        u64,
        /**
         * 16 bits Int. tsa.dtype.
         */
        s16,
        /**
         * 16 bits Unsigned Int. tsa.dtype.
         */
        u16
    }

    public Array(double[] arr, long[] dims) throws Exception {
        long[] adims = Array.dim4(dims);

        int totalSize = 1;
        for (int i = 0; i < adims.length; i++) totalSize *= adims[i];

        if (arr == null) {
            throw new Exception("Null elems object provided");
        }

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }

        this.reference = createArrayFromDouble(arr, dims);
    }


    public Array(float[] arr, long[] dims) throws Exception {
        long[] adims = Array.dim4(dims);

        int totalSize = 1;
        for (int i = 0; i < adims.length; i++) totalSize *= adims[i];

        if (arr == null) {
            throw new Exception("Null elems object provided");
        }

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }

        this.reference = createArrayFromFloat(arr, dims);
    }

    public Array(int[] arr, long[] dims) throws Exception {
        long[] adims = Array.dim4(dims);

        int totalSize = 1;
        for (int i = 0; i < adims.length; i++) totalSize *= adims[i];

        if (arr == null) {
            throw new Exception("Null elems object provided");
        }

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }

        this.reference = createArrayFromInt(arr, dims);
    }

    public Array(FloatComplex[] arr, long[] dims) throws Exception {

        long[] adims = Array.dim4(dims);

        int totalSize = 1;
        for (int i = 0; i < adims.length; i++) totalSize *= adims[i];

        if (arr == null) {
            throw new Exception("Null elems object provided");
        }

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }

        this.reference = createArrayFromFloatComplex(arr, dims);
    }

    public Array(DoubleComplex[] arr, long[] dims) throws Exception {
        long[] adims = Array.dim4(dims);

        int totalSize = 1;
        for (int i = 0; i < adims.length; i++) totalSize *= adims[i];

        if (arr == null) {
            throw new Exception("Null elems object provided");
        }

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }

        this.reference = createArrayFromDoubleComplex(arr, dims);
    }

    public Array(boolean[] arr, long[] dims) throws Exception {
        long[] adims = Array.dim4(dims);

        int totalSize = 1;
        for (int i = 0; i < adims.length; i++) totalSize *= adims[i];

        if (arr == null) {
            throw new Exception("Null elems object provided");
        }

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }

        this.reference = createArrayFromBoolean(arr, dims);
    }

    public Array(short[] arr, long[] dims) throws Exception {
        long[] adims = Array.dim4(dims);

        int totalSize = 1;
        for (int i = 0; i < adims.length; i++) totalSize *= adims[i];

        if (arr == null) {
            throw new Exception("Null elems object provided");
        }

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }

        this.reference = createArrayFromShort(arr, dims);
    }

    public Array(byte[] arr, long[] dims) throws Exception {
        long[] adims = Array.dim4(dims);

        int totalSize = 1;
        for (int i = 0; i < adims.length; i++) totalSize *= adims[i];

        if (arr == null) {
            throw new Exception("Null elems object provided");
        }

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }

        this.reference = createArrayFromByte(arr, dims);
    }


    public Array(long[] arr, long[] dims) throws Exception {
        long[] adims = Array.dim4(dims);

        int totalSize = 1;
        for (int i = 0; i < adims.length; i++) totalSize *= adims[i];

        if (arr == null) {
            throw new Exception("Null elems object provided");
        }

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }

        this.reference = createArrayFromLong(arr, dims);
    }

    public Array(long ref) {
        this.reference = ref;

    }

    private native long createArrayFromDouble(double[] arr, long[] dims);

    private native long createArrayFromFloat(float[] arr, long[] dims);

    private native long createArrayFromBoolean(boolean[] arr, long[] dims);

    private native long createArrayFromInt(int[] arr, long[] dims);

    private native long createArrayFromByte(byte[] arr, long[] dims);

    private native long createArrayFromShort(short[] arr, long[] dims);

    private native long createArrayFromLong(long[] arr, long[] dims);

    private native long createArrayFromFloatComplex(FloatComplex[] arr, long[] dims);

    private native long createArrayFromDoubleComplex(DoubleComplex[] arr, long[] dims);

    private native void deleteArray(long ref);

    private native long[] getDims(long ref);

    private native long print(long ref);

    private native DoubleComplex[] getDoubleComplexFromArray(long ref);

    private native FloatComplex[] getFloatComplexFromArray(long ref);

    private native double[] getDoubleFromArray(long ref);

    private native float[] getFloatFromArray(long ref);

    private native short[] getShortFromArray(long ref);

    private native byte[] getByteFromArray(long ref);

    private native boolean[] getBooleanFromArray(long ref);

    private native int[] getIntFromArray(long ref);

    private native long[] getLongFromArray(long ref);

    private native int getType(long ref);

    /**
     * Gets the Array reference.
     *
     * @return Reference.
     */
    public long getReference() {
        return reference;
    }

    /**
     * Sets the Array reference.
     *
     * @param ref Reference.
     */
    public void setReference(long ref) {
        this.reference = ref;
    }

    /**
     * Gets the Array type.
     *
     * @return Array data type.
     */
    public Dtype getType() {
        return Dtype.values()[getType(this.reference)];
    }

    /**
     * Gets the dim4 in order to construct the Array.
     *
     * @param dims The dimensions.
     * @return The dim4.
     * @throws java.lang.Exception When the input parameter is null or the length is greater than 4, because ArrayFire
     *                             supports up to 4 dimension.
     */
    protected static long[] dim4(long[] dims) throws Exception {

        if (dims == null) {
            throw new Exception("Null dimensions object provided");
        } else if (dims.length > 4) {
            throw new Exception("ArrayFire supports up to 4 dimensions only");
        }

        long[] adims;
        adims = new long[]{1, 1, 1, 1};
        for (int i = 0; i < dims.length; i++) adims[i] = dims[i];

        return adims;
    }

    /**
     * Gets the data stored in the array.
     *
     * @param <Any> The data type to be returned.
     * @return The data to an array of its type.
     */
    public <Any> Any getData() {
        switch (getType().ordinal()) {
            case 0:
                return (Any) getFloatFromArray(this.reference);
            case 1:
                return (Any) getFloatComplexFromArray(this.reference);
            case 2:
                return (Any) getDoubleFromArray(this.reference);
            case 3:
                return (Any) getDoubleComplexFromArray(this.reference);
            case 4:
                return (Any) getBooleanFromArray(this.reference);
            case 5:
                return (Any) getIntFromArray(this.reference);
            case 6:
                return (Any) getIntFromArray(this.reference);
            case 7:
                return (Any) getByteFromArray(this.reference);
            case 8:
                return (Any) getLongFromArray(this.reference);
            case 9:
                return (Any) getLongFromArray(this.reference);
            case 10:
                return (Any) getShortFromArray(this.reference);
            case 11:
                return (Any) getShortFromArray(this.reference);
            default:
                return (Any) getFloatFromArray(this.reference);
        }
    }

    /**
     * Prints the Array.
     */
    public void print() {
        this.reference = print(this.reference);
    }

    /**
     * Gets the Array dimensions.
     *
     * @return The dimensions.
     */
    public long[] getDims() {
        return getDims(this.reference);
    }


    @Override
    public void close() throws Exception {
        deleteArray(this.reference);
    }
}
