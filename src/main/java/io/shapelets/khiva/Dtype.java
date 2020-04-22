/*
 * Copyright (c) 2019 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

/**
 * Data types of a Khiva Array
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
