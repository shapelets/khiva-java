/*
 * Copyright (c) 2019 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

import java.util.Objects;

/**
 * Khiva Array Class.
 */
public class Array extends Library implements AutoCloseable {

    private final long reference;

    // Constructor accepting a native pointer address.
    //
    private Array(long ref) {
        this.reference = ref;
    }

    /**
     * Creates a Khiva array from a primitive array of doubles.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input Array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws Exception                if the native function call fails.
     */
    static public Array fromPrimitiveArray(double[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }
        return fromNative(createArrayFromDouble(arr, dims));
    }

    // Returns the number of elements for the specified dimensions.
    //
    private static int getNumElements(long[] dims) throws Exception {
        long[] arrayDims = Array.dim4(dims);
        int totalSize = 1;
        for (long dim : arrayDims) totalSize *= dim;
        return totalSize;
    }

    /**
     * Creates a Khiva array from a native pointer address.
     *
     * @return A Khiva Array.
     */
    static public Array fromNative(long reference) {
        return new Array(reference);
    }

    /**
     * Creates a Khiva array from a primitive array of floats.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input Array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws Exception                if the native function call fails.
     */
    static public Array fromPrimitiveArray(float[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromFloat(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of ints.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input Array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws Exception                if the native function call fails.
     */
    static public Array fromPrimitiveArray(int[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromInt(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of FloatComplex.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input Array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws Exception                if the native function call fails.
     */
    static public Array fromPrimitiveArray(FloatComplex[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromFloatComplex(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of DoubleComplex.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input Array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws Exception                if the native function call fails.
     */
    static public Array fromPrimitiveArray(DoubleComplex[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromDoubleComplex(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of boolean.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input Array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws Exception                if the native function call fails.
     */
    static public Array fromPrimitiveArray(boolean[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromBoolean(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of short.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input Array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws Exception                if the native function call fails.
     */
    static public Array fromPrimitiveArray(short[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromShort(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of byte.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input Array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws Exception                if the native function call fails.
     */
    static public Array fromPrimitiveArray(byte[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromByte(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of long.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input Array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mistmatch between input array size and dimensions.
     * @throws Exception                if the native function call fails.
     */
    static public Array fromPrimitiveArray(long[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromLong(arr, dims));
    }

    private native static long createArrayFromDouble(double[] arr, long[] dims) throws Exception;

    private native static long createArrayFromFloat(float[] arr, long[] dims) throws Exception;

    private native static long createArrayFromBoolean(boolean[] arr, long[] dims) throws Exception;

    private native static long createArrayFromInt(int[] arr, long[] dims) throws Exception;

    private native static long createArrayFromByte(byte[] arr, long[] dims) throws Exception;

    private native static long createArrayFromShort(short[] arr, long[] dims) throws Exception;

    private native static long createArrayFromLong(long[] arr, long[] dims) throws Exception;

    private native static long createArrayFromFloatComplex(FloatComplex[] arr, long[] dims) throws Exception;

    private native static long createArrayFromDoubleComplex(DoubleComplex[] arr, long[] dims) throws Exception;

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
        }
        else if (dims.length > 4) {
            throw new Exception("ArrayFire supports up to 4 dimensions only");
        }

        long[] arrayDims;
        arrayDims = new long[]{1, 1, 1, 1};
        System.arraycopy(dims, 0, arrayDims, 0, dims.length);

        return arrayDims;
    }

    private native void deleteArray() throws Exception;

    private native long[] nativeGetDims() throws Exception;

    private native int nativeGetType() throws Exception;

    private native long nativePrint() throws Exception;

    private native DoubleComplex[] getDoubleComplexFromArray() throws Exception;

    private native FloatComplex[] getFloatComplexFromArray() throws Exception;

    private native double[] getDoubleFromArray() throws Exception;

    private native float[] getFloatFromArray() throws Exception;

    private native short[] getShortFromArray() throws Exception;

    private native byte[] getByteFromArray() throws Exception;

    private native boolean[] getBooleanFromArray() throws Exception;

    private native int[] getIntFromArray() throws Exception;

    private native long[] getLongFromArray() throws Exception;

    private native long join(int dim, long refRhs) throws Exception;

    private native long add(long refRhs) throws Exception;

    private native long mul(long refRhs) throws Exception;

    private native long sub(long refRhs) throws Exception;

    private native long div(long refRhs) throws Exception;

    private native long mod(long refRhs) throws Exception;

    private native long pow(long refRhs) throws Exception;

    private native long lt(long refRhs) throws Exception;

    private native long gt(long refRhs) throws Exception;

    private native long le(long refRhs) throws Exception;

    private native long ge(long refRhs) throws Exception;

    private native long eq(long refRhs) throws Exception;

    private native long ne(long refRhs) throws Exception;

    private native long bitAnd(long refRhs) throws Exception;

    private native long bitOr(long refRhs) throws Exception;

    private native long bitXor(long refRhs) throws Exception;

    private native long nativeBitShiftL(int n) throws Exception;

    private native long nativeBitShiftR(int n) throws Exception;

    private native long nativeNot() throws Exception;

    private native long nativeTranspose(boolean conjugate) throws Exception;

    private native long nativeCol(int index) throws Exception;

    private native long nativeCols(int first, int last) throws Exception;

    private native long nativeRow(int index) throws Exception;

    private native long nativeRows(int first, int last) throws Exception;

    private native long matmul(long refRhs) throws Exception;

    private native long nativeCopy() throws Exception;

    private native long as(int type) throws Exception;

    /**
     * Gets the Array reference.
     *
     * @return Reference.
     */
    public long getReference() {
        return reference;
    }

    /**
     * Gets the Array type.
     *
     * @return Array data type.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Dtype getType() throws Exception {
        return Dtype.values()[nativeGetType()];
    }

    /**
     * Gets the data stored in the array.
     *
     * @param <Any> The data type to be returned.
     * @return The data to an array of its type.
     * @throws java.lang.Exception If the native function call fails.
     */
    public <Any> Any getData() throws Exception {
        switch (getType()) {
            case c32:
                return (Any) getFloatComplexFromArray();
            case f64:
                return (Any) getDoubleFromArray();
            case c64:
                return (Any) getDoubleComplexFromArray();
            case b8:
                return (Any) getBooleanFromArray();
            case s32:
            case u32:
                return (Any) getIntFromArray();
            case u8:
                return (Any) getByteFromArray();
            case s64:
            case u64:
                return (Any) getLongFromArray();
            case s16:
            case u16:
                return (Any) getShortFromArray();
            default:
                return (Any) getFloatFromArray();
        }
    }

    /**
     * Prints the Array.
     *
     * @throws java.lang.Exception If the native function call fails.
     */
    public void print() throws Exception {
        nativePrint();
    }

    /**
     * Gets the Array dimensions.
     *
     * @return The dimensions.
     * @throws java.lang.Exception If the native function call fails.
     */
    public long[] getDims() throws Exception {
        return nativeGetDims();
    }

    /**
     * Joins this array with the one specified as parameter along the specified dimension.
     *
     * @param dim The dimension along which the join occurs.
     * @param rhs Right-hand side array for the operation.
     * @return The result of joining the given arrays along the specified dimension.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array join(int dim, Array rhs) throws Exception {
        long ref = join(dim, rhs.reference);
        return fromNative(ref);
    }

    /**
     * Adds this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The sum of both arrays.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array add(Array rhs) throws Exception {
        long ref = add(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Multiplies this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The product of both arrays.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array mul(Array rhs) throws Exception {
        long ref = mul(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Subtracts this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The subtraction of both arrays.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array sub(Array rhs) throws Exception {
        long ref = sub(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Divides this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The division of both arrays.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array div(Array rhs) throws Exception {
        long ref = div(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs the modulo operation of this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The modulo of this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array mod(Array rhs) throws Exception {
        long ref = mod(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Powers this array with the one specified as exponent parameter.
     *
     * @param exponent Exponent for the power operation.
     * @return The power of this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array pow(Array exponent) throws Exception {
        long ref = pow(exponent.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is lower than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array lt(Array rhs) throws Exception {
        long ref = lt(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is greater than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array gt(Array rhs) throws Exception {
        long ref = gt(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is lower or equal than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array le(Array rhs) throws Exception {
        long ref = le(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is greater or equal than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array ge(Array rhs) throws Exception {
        long ref = ge(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is equal to the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array eq(Array rhs) throws Exception {
        long ref = eq(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is not equal to the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array ne(Array rhs) throws Exception {
        long ref = ne(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs an AND operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an AND operation of this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array bitAnd(Array rhs) throws Exception {
        long ref = bitAnd(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs an OR operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an OR operation of this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array bitOr(Array rhs) throws Exception {
        long ref = bitOr(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs an eXclusive-OR operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an eXclusive-OR operation of this array with the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array bitXor(Array rhs) throws Exception {
        long ref = bitXor(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs a left bit shift operation to this array (element-wise) as many times as specified in the parameter n.
     *
     * @param n Number of bits to be shifted.
     * @return The result of a left bit shift operation to this array as many times as specified in the
     * parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array bitShiftL(int n) throws Exception {
        long ref = nativeBitShiftL(n);
        return fromNative(ref);
    }

    /**
     * Performs a right bit shift operation to this array (element-wise) as many times as specified in the parameter n.
     *
     * @param n Number of bits to be shifted.
     * @return The result of a right bit shift operation to this array as many times as specified in the
     * parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array bitShiftR(int n) throws Exception {
        long ref = nativeBitShiftR(n);
        return fromNative(ref);
    }

    /**
     * Logical NOT operation to this array.
     *
     * @return The result of a logical NOT operation to this array.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array not() throws Exception {
        long ref = nativeNot();
        return fromNative(ref);
    }

    /**
     * Transposes this array.
     *
     * @param conjugate If true a conjugate transposition is performed.
     * @return The transposed (conjugate) array.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array transpose(boolean conjugate) throws Exception {
        long ref = nativeTranspose(conjugate);
        return fromNative(ref);
    }

    /**
     * Transposes this array without conjugating it.
     *
     * @return The transposed array.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array transpose() throws Exception {
        return transpose(false);
    }

    /**
     * Returns the specified column by index from this array.
     *
     * @param index The column to be retrieved.
     * @return The specified column of this array.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array col(int index) throws Exception {
        long ref = nativeCol(index);
        return fromNative(ref);
    }

    /**
     * Retrieves a subset of columns from this array, starting at first and finishing at last, both inclusive.
     *
     * @param first Start of the subset of columns to be retrieved.
     * @param last  End of the subset of columns to be retrieved.
     * @return The subset of columns of this array starting at first and finishing at last, both inclusive.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array cols(int first, int last) throws Exception {
        long ref = nativeCols(first, last);
        return fromNative(ref);
    }

    /**
     * Retrieves a given row from this array.
     *
     * @param index The row to be retrieved.
     * @return The specified row of this array.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array row(int index) throws Exception {
        long ref = nativeRow(index);
        return fromNative(ref);
    }

    /**
     * Retrieves a subset of rows from this array, starting at first and finishing at last, both inclusive.
     *
     * @param first Start of the subset of rows to be retrieved.
     * @param last  End of the subset of rows to be retrieved.
     * @return The subset of rows of this array starting at first and finishing at last, both inclusive.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array rows(int first, int last) throws Exception {
        long ref = nativeRows(first, last);
        return fromNative(ref);
    }

    /**
     * Performs a matrix multiplication of this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of a matrix multiplication of both arrays.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array matmul(Array rhs) throws Exception {
        long ref = matmul(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs a deep copy of this array. Both the data stored in the device and all the object properties in Java.
     *
     * @return A deep copy of this array.
     * @throws java.lang.Exception If the native function call fails.
     */
    private Array copy() throws Exception {
        long ref = nativeCopy();
        return fromNative(ref);
    }

    /**
     * Changes the type of this array.
     *
     * @param type Target type of the output array.
     * @return The result of changing the type of the input array to the one passed as parameter.
     * @throws java.lang.Exception If the native function call fails.
     */
    public Array as(Dtype type) throws Exception {
        long ref = as(type.ordinal());
        return fromNative(ref);
    }

    @Override
    public void close() throws Exception {
        deleteArray();
    }
}
