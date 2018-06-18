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
 * Khiva Regression class containing regression methods.
 */
public class Regression extends Library {

    private native static long[] linear(long xssRef, long yssRef);

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
     */
    public static Array[] linear(Array xss, Array yss) {
        long[] refs = linear(xss.getReference(), yss.getReference());
        xss.setReference(refs[0]);
        yss.setReference(refs[1]);
        Array[] result = {new Array(refs[2]), new Array(refs[3]), new Array(refs[4]), new Array(refs[5]), new Array(refs[6])};
        return result;
    }
}
