/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package tsa;

import org.apache.commons.lang3.ArrayUtils;

public class Linalg extends Library {

    private native static void lls(double[] aConcatenated, long a_l, long a_n, double[] b, long b_l, double[] result);

    /**
     * Calculates the minimum norm least squares solution \f$x\f$ \f$(\left\lVert{A·x − b}\right\rVert^2)\f$ to
     * \f$A·x = b\f$. This function uses the singular value decomposition function of Arrayfire. The actual formula that
     * this function computes is \f$x = V·D\dagger·U^T·b\f$. Where \f$U\f$ and \f$V\f$ are orthogonal matrices and
     * \f$D\dagger\f$ contains the inverse values of the singular values contained in D if they are not zero, and zero
     * otherwise.
     *
     * @param a Coefficients of the linear equation problem to solve.
     * @param b Array with the measured values.
     * @return Contains the solution to the linear equation problem minimizing the norm 2.
     */
    public static double[] lls(double[][] a, double[] b) {
        long a_l = a[0].length;
        long a_n = a.length;
        double[] aConcatenated = new double[0];
        for (double[] time_series : a) {
            aConcatenated = ArrayUtils.addAll(aConcatenated, time_series);
        }
        long b_l = b.length;
        double[] result = new double[(int) (a_n)];
        lls(aConcatenated, a_l, a_n, b, b_l, result);
        return result;
    }
}
