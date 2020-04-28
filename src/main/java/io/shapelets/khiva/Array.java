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
     * Creates a Khiva array from a primitive array of floats.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input array.
     * @return A Khiva array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws KhivaException           if the native function call fails.
     */
    static public Array fromPrimitiveArray(double[] arr, long[] dims) {
        Objects.requireNonNull(arr, "Null array input provided");
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromDouble(arr, dims));
    }

    // Returns the number of elements for the specified dimensions.
    //
    private static int getNumElements(long[] dims) {
        long[] arrayDims = Array.dim4(dims);
        int totalSize = 1;
        for (long dim : arrayDims) totalSize *= dim;
        return totalSize;
    }

    /**
     * Creates a Khiva array from a native pointer address.
     *
     * @param reference the address of the native pointer.
     * @return A Khiva array.
     */
    static public Array fromNative(long reference) {
        return new Array(reference);
    }

    /**
     * Creates a Khiva array from a primitive array of floats.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input array.
     * @return A Khiva array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws KhivaException           if the native function call fails.
     */
    static public Array fromPrimitiveArray(float[] arr, long[] dims) {
        Objects.requireNonNull(arr, "Null array input provided");
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromFloat(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of floats.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input array.
     * @return A Khiva array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws KhivaException           if the native function call fails.
     */
    static public Array fromPrimitiveArray(int[] arr, long[] dims) {
        Objects.requireNonNull(arr, "Null array input provided");
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromInt(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of floats.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input array.
     * @return A Khiva array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws KhivaException           if the native function call fails.
     */
    static public Array fromPrimitiveArray(FloatComplex[] arr, long[] dims) {
        Objects.requireNonNull(arr, "Null array input provided");
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromFloatComplex(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of floats.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input array.
     * @return A Khiva array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws KhivaException           if the native function call fails.
     */
    static public Array fromPrimitiveArray(DoubleComplex[] arr, long[] dims) {
        Objects.requireNonNull(arr, "Null array input provided");
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromDoubleComplex(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of floats.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input array.
     * @return A Khiva array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws KhivaException           if the native function call fails.
     */
    static public Array fromPrimitiveArray(boolean[] arr, long[] dims) {
        Objects.requireNonNull(arr, "Null array input provided");
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromBoolean(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of floats.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input array.
     * @return A Khiva array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws KhivaException           if the native function call fails.
     */
    static public Array fromPrimitiveArray(short[] arr, long[] dims) {
        Objects.requireNonNull(arr, "Null array input provided");
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromShort(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of floats.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input array.
     * @return A Khiva array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws KhivaException           if the native function call fails.
     */
    static public Array fromPrimitiveArray(byte[] arr, long[] dims) {
        Objects.requireNonNull(arr, "Null array input provided");
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromByte(arr, dims));
    }

    /**
     * Creates a Khiva array from a primitive array of floats.
     *
     * @param arr  input array to initialize from.
     * @param dims an array specifying the dimensions of the input array.
     * @return A Khiva array.
     * @throws NullPointerException     if the input array or dimensions are null.
     * @throws IllegalArgumentException if there is a mismatch between input array size and dimensions.
     * @throws KhivaException           if the native function call fails.
     */
    static public Array fromPrimitiveArray(long[] arr, long[] dims) {
        Objects.requireNonNull(arr, "Null array input provided");
        int totalSize = getNumElements(dims);
        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        return fromNative(createArrayFromLong(arr, dims));
    }

    /**
     * Performs a deep copy of this array. Both the data stored in the device and all the object properties in Java.
     *
     * @param other the input array to be copied.
     * @return A deep copy of the input array.
     * @throws KhivaException If the native function call fails.
     */
    static public Array copy(Array other) {
        return other.copy();
    }

    private native static long createArrayFromDouble(double[] arr, long[] dims);

    private native static long createArrayFromFloat(float[] arr, long[] dims);

    private native static long createArrayFromBoolean(boolean[] arr, long[] dims);

    private native static long createArrayFromInt(int[] arr, long[] dims);

    private native static long createArrayFromByte(byte[] arr, long[] dims);

    private native static long createArrayFromShort(short[] arr, long[] dims);

    private native static long createArrayFromLong(long[] arr, long[] dims);

    private native static long createArrayFromFloatComplex(FloatComplex[] arr, long[] dims);

    private native static long createArrayFromDoubleComplex(DoubleComplex[] arr, long[] dims);

    /**
     * Gets the dim4 in order to construct the Array.
     *
     * @param dims The dimensions.
     * @return The dim4.
     * @throws KhivaException When the input parameter is null or the length is greater than 4, because ArrayFire
     *                             supports up to 4 dimension.
     */
    protected static long[] dim4(long[] dims) {
        Objects.requireNonNull(dims, "Null dimensions object provided");
        if (dims.length > 4) {
            throw new IllegalArgumentException("ArrayFire supports up to 4 dimensions only");
        }

        long[] arrayDims;
        arrayDims = new long[]{1, 1, 1, 1};
        System.arraycopy(dims, 0, arrayDims, 0, dims.length);
        return arrayDims;
    }

    private native void deleteArray();

    private native long[] nativeGetDims();

    private native int nativeGetType();

    private native void nativePrint();

    private native DoubleComplex[] getDoubleComplexFromArray();

    private native FloatComplex[] getFloatComplexFromArray();

    private native double[] getDoubleFromArray();

    private native float[] getFloatFromArray();

    private native short[] getShortFromArray();

    private native byte[] getByteFromArray();

    private native boolean[] getBooleanFromArray();

    private native int[] getIntFromArray();

    private native long[] getLongFromArray();

    private native long join(int dim, long refRhs);

    private native long add(long refRhs);

    private native long mul(long refRhs);

    private native long sub(long refRhs);

    private native long div(long refRhs);

    private native long mod(long refRhs);

    private native long pow(long refRhs);

    private native long lt(long refRhs);

    private native long gt(long refRhs);

    private native long le(long refRhs);

    private native long ge(long refRhs);

    private native long eq(long refRhs);

    private native long ne(long refRhs);

    private native long bitAnd(long refRhs);

    private native long bitOr(long refRhs);

    private native long bitXor(long refRhs);

    private native long nativeBitShiftL(int n);

    private native long nativeBitShiftR(int n);

    private native long nativeNot();

    private native long nativeTranspose(boolean conjugate);

    private native long nativeCol(int index);

    private native long nativeCols(int first, int last);

    private native long nativeRow(int index);

    private native long nativeRows(int first, int last);

    private native long matmul(long refRhs);

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
     * Gets the Array type.
     *
     * @return Array data type.
     */
    public Dtype getType() {
        return Dtype.values()[nativeGetType()];
    }

    /**
     * Gets the data stored in the array.
     *
     * @param <Any> The data type to be returned.
     * @return The data to an array of its type.
     */
    public <Any> Any getData() {
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
     */
    public void print() {
        nativePrint();
    }

    /**
     * Gets the Array dimensions.
     *
     * @return The dimensions.
     * @throws KhivaException If the native function call fails.
     */
    public long[] getDims() {
        return nativeGetDims();
    }

    /**
     * Joins this array with the one specified as parameter along the specified dimension.
     *
     * @param dim The dimension along which the join occurs.
     * @param rhs Right-hand side array for the operation.
     * @return The result of joining the given arrays along the specified dimension.
     * @throws KhivaException If the native function call fails.
     */
    public Array join(int dim, Array rhs) {
        long ref = join(dim, rhs.reference);
        return fromNative(ref);
    }

    /**
     * Adds this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The sum of both arrays.
     * @throws KhivaException If the native function call fails.
     */
    public Array add(Array rhs) {
        long ref = add(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Multiplies this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The product of both arrays.
     * @throws KhivaException If the native function call fails.
     */
    public Array mul(Array rhs) {
        long ref = mul(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Subtracts this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The subtraction of both arrays.
     * @throws KhivaException If the native function call fails.
     */
    public Array sub(Array rhs) {
        long ref = sub(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Divides this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The division of both arrays.
     * @throws KhivaException If the native function call fails.
     */
    public Array div(Array rhs) {
        long ref = div(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs the modulo operation of this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The modulo of this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array mod(Array rhs) {
        long ref = mod(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Powers this array with the one specified as exponent parameter.
     *
     * @param exponent Exponent for the power operation.
     * @return The power of this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array pow(Array exponent) {
        long ref = pow(exponent.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is lower than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array lt(Array rhs) {
        long ref = lt(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is greater than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array gt(Array rhs) {
        long ref = gt(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is lower or equal than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array le(Array rhs) {
        long ref = le(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is greater or equal than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array ge(Array rhs) {
        long ref = ge(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is equal to the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array eq(Array rhs) {
        long ref = eq(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Compares (element-wise) if this array is not equal to the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array ne(Array rhs) {
        long ref = ne(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs an AND operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an AND operation of this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array bitAnd(Array rhs) {
        long ref = bitAnd(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs an OR operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an OR operation of this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array bitOr(Array rhs) {
        long ref = bitOr(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs an eXclusive-OR operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an eXclusive-OR operation of this array with the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array bitXor(Array rhs) {
        long ref = bitXor(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs a left bit shift operation to this array (element-wise) as many times as specified in the parameter n.
     *
     * @param n Number of bits to be shifted.
     * @return The result of a left bit shift operation to this array as many times as specified in the
     * parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array bitShiftL(int n) {
        long ref = nativeBitShiftL(n);
        return fromNative(ref);
    }

    /**
     * Performs a right bit shift operation to this array (element-wise) as many times as specified in the parameter n.
     *
     * @param n Number of bits to be shifted.
     * @return The result of a right bit shift operation to this array as many times as specified in the
     * parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array bitShiftR(int n) {
        long ref = nativeBitShiftR(n);
        return fromNative(ref);
    }

    /**
     * Logical NOT operation to this array.
     *
     * @return The result of a logical NOT operation to this array.
     * @throws KhivaException If the native function call fails.
     */
    public Array not() {
        long ref = nativeNot();
        return fromNative(ref);
    }

    /**
     * Transposes this array.
     *
     * @param conjugate If true a conjugate transposition is performed.
     * @return The transposed (conjugate) array.
     * @throws KhivaException If the native function call fails.
     */
    public Array transpose(boolean conjugate) {
        long ref = nativeTranspose(conjugate);
        return fromNative(ref);
    }

    /**
     * Transposes this array without conjugating it.
     *
     * @return The transposed array.
     * @throws KhivaException If the native function call fails.
     */
    public Array transpose() {
        return transpose(false);
    }

    /**
     * Returns the specified column by index from this array.
     *
     * @param index The column to be retrieved.
     * @return The specified column of this array.
     * @throws KhivaException If the native function call fails.
     */
    public Array col(int index) {
        long ref = nativeCol(index);
        return fromNative(ref);
    }

    /**
     * Retrieves a subset of columns from this array, starting at first and finishing at last, both inclusive.
     *
     * @param first Start of the subset of columns to be retrieved.
     * @param last  End of the subset of columns to be retrieved.
     * @return The subset of columns of this array starting at first and finishing at last, both inclusive.
     * @throws KhivaException If the native function call fails.
     */
    public Array cols(int first, int last) {
        long ref = nativeCols(first, last);
        return fromNative(ref);
    }

    /**
     * Retrieves a given row from this array.
     *
     * @param index The row to be retrieved.
     * @return The specified row of this array.
     * @throws KhivaException If the native function call fails.
     */
    public Array row(int index) {
        long ref = nativeRow(index);
        return fromNative(ref);
    }

    /**
     * Retrieves a subset of rows from this array, starting at first and finishing at last, both inclusive.
     *
     * @param first Start of the subset of rows to be retrieved.
     * @param last  End of the subset of rows to be retrieved.
     * @return The subset of rows of this array starting at first and finishing at last, both inclusive.
     * @throws KhivaException If the native function call fails.
     */
    public Array rows(int first, int last) {
        long ref = nativeRows(first, last);
        return fromNative(ref);
    }

    /**
     * Performs a matrix multiplication of this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of a matrix multiplication of both arrays.
     * @throws KhivaException If the native function call fails.
     */
    public Array matmul(Array rhs) {
        long ref = matmul(rhs.reference);
        return fromNative(ref);
    }

    /**
     * Performs a deep copy of this array. Both the data stored in the device and all the object properties in Java.
     *
     * @return A deep copy of this array.
     * @throws KhivaException If the native function call fails.
     */
    private Array copy() {
        long ref = nativeCopy();
        return fromNative(ref);
    }

    /**
     * Changes the type of this array.
     *
     * @param type Target type of the output array.
     * @return The result of changing the type of the input array to the one passed as parameter.
     * @throws KhivaException If the native function call fails.
     */
    public Array as(Dtype type) {
        long ref = as(type.ordinal());
        return fromNative(ref);
    }

    @Override
    public void close() {
        deleteArray();
    }
}
