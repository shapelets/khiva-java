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
 * Khiva Polynomial class containing a number of polynomial methods.
 */
public class Polynomial extends Library {

    private native static long[] polyfit(long refX, long refY, int deg);

    private native static long[] roots(long ref);

    /**
     * Least squares polynomial fit. Fit a polynomial \(p(x) = p[0] * x^{deg} + ... + p[deg]\) of degree \(deg\)
     * to points \((x, y)\). Returns a vector of coefficients \(p\) that minimises the squared error.
     *
     * @param x   Array containing the x-coordinates of the M sample points \((x[i], y[i])\).
     * @param y   Array containing the y-coordinates of the sample points.
     * @param deg Degree of the fitting polynomial.
     * @return Array with the polynomial coefficients, highest power first.
     */
    public static Array polyfit(Array x, Array y, int deg) {
        long[] refs = polyfit(x.getReference(), y.getReference(), deg);
        x.setReference(refs[0]);
        y.setReference(refs[1]);
        return new Array(refs[2]);
    }

    /**
     * Calculates the roots of a polynomial with coefficients given in \(ref\). The values in the rank-1 array
     * \(ref\) are coefficients of a polynomial. If the length of \(ref\) is \(n+1\) then the polynomial is described
     * by:
     * \[
     * ref[0] * x^n + ref[1] * x^{n-1} + ... + ref[n-1] * x + ref[n]
     * \]
     *
     * @param p Array of polynomial coefficients.
     * @return Array with the roots of the polynomial.
     */
    public static Array roots(Array p) {
        long[] refs = roots(p.getReference());
        p.setReference(refs[0]);
        return new Array(refs[1]);
    }
}
