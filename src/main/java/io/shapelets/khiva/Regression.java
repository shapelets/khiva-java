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
 * Khiva Regression class containing regression methods.
 */
public class Regression extends Library {

    private native static long[] linear(long xssRef, long yssRef) throws Exception;

    /**
     * Calculates a linear least-squares regression for two sets of measurements. Both arrays should have the same
     * length.
     *
     * @param xss Array containing the input time series.
     * @param yss Array containing the input time series.
     * @return An Array of Khiva Arrays whose components are:
     * {
     * slope Slope of the regression line.
     * intercept Intercept of the regression line.
     * rvalue Correlation coefficient.
     * pvalue Two-sided p-value for a hypothesis test whose null hypothesis is that the slope is zero, using Wald
     * Test with t-distribution of the test statistic.
     * stderrest Standard error of the estimated gradient.
     * }
     * @throws java.lang.Exception If the native function call fails.
     */
    public static Array[] linear(Array xss, Array yss) throws Exception {
        long[] refs = linear(xss.getReference(), yss.getReference());
        return new Array[]{Array.fromNative(refs[0]), Array.fromNative(refs[1]), Array.fromNative(refs[2]), Array.fromNative(refs[3]),
                           Array.fromNative(refs[4])};
    }
}
