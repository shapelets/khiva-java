/*
 * Copyright (c) 2018 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

/**
 * Khiva Array Class.
 */
public class Array extends Library implements AutoCloseable {

    private long reference;

    /**
     * Data type of the Array
     */
    public enum Dtype {
        /**
         * Floating point of single precision. khiva.dtype.
         */
        f32,
        /**
         * Complex floating point of single precision. khiva.dtype.
         */
        c32,
        /**
         * Floating point of double precision. khiva.dtype.
         */
        f64,
        /**
         * Complex floating point of double precision. khiva.dtype.
         */
        c64,
        /**
         * Boolean. khiva.dtype.
         */
        b8,
        /**
         * 32 bits Int. khiva.dtype.
         */
        s32,
        /**
         * 32 bits Unsigned Int. khiva.dtype.
         */
        u32,
        /**
         * 8 bits Unsigned Int. khiva.dtype.
         */
        u8,
        /**
         * 64 bits Integer. khiva.dtype.
         */
        s64,
        /**
         * 64 bits Unsigned Int. khiva.dtype.
         */
        u64,
        /**
         * 16 bits Int. khiva.dtype.
         */
        s16,
        /**
         * 16 bits Unsigned Int. khiva.dtype.
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

    public Array(Array other) {
        this.reference = other.copy().reference;
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

    private native void deleteArray();

    private native long[] nativeGetDims();

    private native long nativePrint();

    private native DoubleComplex[] getDoubleComplexFromArray();

    private native FloatComplex[] getFloatComplexFromArray();

    private native double[] getDoubleFromArray();

    private native float[] getFloatFromArray();

    private native short[] getShortFromArray();

    private native byte[] getByteFromArray();

    private native boolean[] getBooleanFromArray();

    private native int[] getIntFromArray();

    private native long[] getLongFromArray();

    private native int nativeGetType();

    private native long[] add(long refRhs);

    private native long[] mul(long refRhs);

    private native long[] sub(long refRhs);

    private native long[] div(long refRhs);

    private native long[] mod(long refRhs);

    private native long[] pow(long refRhs);

    private native long[] lt(long refRhs);

    private native long[] gt(long refRhs);

    private native long[] le(long refRhs);

    private native long[] ge(long refRhs);

    private native long[] eq(long refRhs);

    private native long[] ne(long refRhs);

    private native long[] bitAnd(long refRhs);

    private native long[] bitOr(long refRhs);

    private native long[] bitXor(long refRhs);

    private native long nativeBitShiftL(int n);

    private native long nativeBitShiftR(int n);

    private native long nativeNot();

    private native long nativeTranspose(boolean conjugate);

    private native long nativeCol(int index);

    private native long nativeCols(int first, int last);

    private native long nativeRow(int index);

    private native long nativeRows(int first, int last);

    private native long[] matmul(long refRhs);

    private native long nativeCopy();

    private native long as(int type);


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
        return Dtype.values()[nativeGetType()];
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
        switch (getType()) {
            case f32:
                return (Any) getFloatFromArray();
            case c32:
                return (Any) getFloatComplexFromArray();
            case f64:
                return (Any) getDoubleFromArray();
            case c64:
                return (Any) getDoubleComplexFromArray();
            case b8:
                return (Any) getBooleanFromArray();
            case s32:
                return (Any) getIntFromArray();
            case u32:
                return (Any) getIntFromArray();
            case u8:
                return (Any) getByteFromArray();
            case s64:
                return (Any) getLongFromArray();
            case u64:
                return (Any) getLongFromArray();
            case s16:
                return (Any) getShortFromArray();
            case u16:
                return (Any) getShortFromArray();
            default:
                return (Any) getFloatFromArray();
        }
    }

    /**
     * Prints the Array.
     */
    public void print() {
        nativePrint();
    }

    /**
     * Gets the Array dimensions.
     *
     * @return The dimensions.
     */
    public long[] getDims() {
        return nativeGetDims();
    }

    /**
     * Adds this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The sum of both arrays.
     */
    public Array add(Array rhs) {
        long[] refs = add(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Multiplies this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The product of both arrays.
     */
    public Array mul(Array rhs) {
        long[] refs = mul(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Subtracts this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The subtraction of both arrays.
     */
    public Array sub(Array rhs) {
        long[] refs = sub(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Divides this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The division of both arrays.
     */
    public Array div(Array rhs) {
        long[] refs = div(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Performs the modulo operation of this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The modulo of this array with the one passed as parameter.
     */
    public Array mod(Array rhs) {
        long[] refs = mod(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Powers this array with the one specified as exponent parameter.
     *
     * @param exponent Exponent for the power operation.
     * @return The power of this array with the one passed as parameter.
     */
    public Array pow(Array exponent) {
        long[] refs = pow(exponent.reference);
        exponent.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Compares (element-wise) if this array is lower than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array lt(Array rhs) {
        long[] refs = lt(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Compares (element-wise) if this array is greater than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array gt(Array rhs) {
        long[] refs = gt(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Compares (element-wise) if this array is lower or equal than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array le(Array rhs) {
        long[] refs = le(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Compares (element-wise) if this array is greater or equal than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array ge(Array rhs) {
        long[] refs = ge(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Compares (element-wise) if this array is equal to the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array eq(Array rhs) {
        long[] refs = eq(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Compares (element-wise) if this array is not equal to the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array ne(Array rhs) {
        long[] refs = ne(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Performs an AND operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an AND operation of this array with the one passed as parameter.
     */
    public Array bitAnd(Array rhs) {
        long[] refs = bitAnd(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Performs an OR operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an OR operation of this array with the one passed as parameter.
     */
    public Array bitOr(Array rhs) {
        long[] refs = bitOr(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Performs an eXclusive-OR operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an eXclusive-OR operation of this array with the one passed as parameter.
     */
    public Array bitXor(Array rhs) {
        long[] refs = bitXor(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Performs a left bit shift operation to this array (element-wise) as many times as specified in the parameter n.
     *
     * @param n Number of bits to be shifted.
     * @return The result of a left bit shift operation to this array as many times as specified in the
     * parameter.
     */
    public Array bitShiftL(int n) {
        long ref = nativeBitShiftL(n);
        return new Array(ref);
    }

    /**
     * Performs a right bit shift operation to this array (element-wise) as many times as specified in the parameter n.
     *
     * @param n Number of bits to be shifted.
     * @return The result of a right bit shift operation to this array as many times as specified in the
     * parameter.
     */
    public Array bitShiftR(int n) {
        long ref = nativeBitShiftR(n);
        return new Array(ref);
    }

    /**
     * Logical NOT operation to this array.
     *
     * @return The result of a logical NOT operation to this array.
     */
    public Array not() {
        long ref = nativeNot();
        return new Array(ref);
    }

    /**
     * Transposes this array.
     *
     * @param conjugate If true a conjugate transposition is performed.
     * @return The transposed (conjugate) array.
     */
    public Array transpose(boolean conjugate) {
        long ref = nativeTranspose(conjugate);
        return new Array(ref);
    }

    /**
     * Transposes this array without conjugating it.
     *
     * @return The transposed array.
     */
    public Array transpose() {
        return transpose(false);
    }

    /**
     * Returns the specified column by index from this array.
     *
     * @param index The column to be retrieved.
     * @return The specified column of this array.
     */
    public Array col(int index) {
        long ref = nativeCol(index);
        return new Array(ref);
    }

    /**
     * Retrieves a subset of columns from this array, starting at first and finishing at last, both inclusive.
     *
     * @param first Start of the subset of columns to be retrieved.
     * @param last  End of the subset of columns to be retrieved.
     * @return The subset of columns of this array starting at first and finishing at last, both inclusive.
     */
    public Array cols(int first, int last) {
        long ref = nativeCols(first, last);
        return new Array(ref);
    }

    /**
     * Retrieves a given row from this array.
     *
     * @param index The row to be retrieved.
     * @return The specified row of this array.
     */
    public Array row(int index) {
        long ref = nativeRow(index);
        return new Array(ref);
    }

    /**
     * Retrieves a subset of rows from this array, starting at first and finishing at last, both inclusive.
     *
     * @param first Start of the subset of rows to be retrieved.
     * @param last  End of the subset of rows to be retrieved.
     * @return The subset of rows of this array starting at first and finishing at last, both inclusive.
     */
    public Array rows(int first, int last) {
        long ref = nativeRows(first, last);
        return new Array(ref);
    }

    /**
     * Performs a matrix multiplication of this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of a matrix multiplication of both arrays.
     */
    public Array matmul(Array rhs) {
        long[] refs = matmul(rhs.reference);
        rhs.reference = refs[0];
        return new Array(refs[1]);
    }

    /**
     * Performs a deep copy of this array. Both the data stored in the device and all the object properties in Java.
     *
     * @return A deep copy of this array.
     */
    public Array copy() {
        long ref = nativeCopy();
        return new Array(ref);
    }

    /**
     * Changes the type of this array.
     *
     * @param type Target type of the output array.
     * @return The result of changing the type of the input array to the one passed as parameter.
     */
    public Array as(Dtype type) {
        long ref = as(type.ordinal());
        return new Array(ref);
    }

    @Override
    public void close() throws Exception {
        deleteArray();
    }
}
