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

    private native static void lls(double[] aConcatenated, long aL, long aN, double[] b, long bL, double[] result);

    /**
     * Calculates the minimum norm least squares solution \(x\) \((\left\lVert{A·x − b}\right\rVert^2)\) to
     * \(A·x = b\). This function uses the singular value decomposition function of Arrayfire. The actual formula that
     * this function computes is \(x = V·D\dagger·U^T·b\). Where \(U\) and \(V\) are orthogonal matrices and
     * \(D\dagger\) contains the inverse values of the singular values contained in D if they are not zero, and zero
     * otherwise.
     *
     * @param a Coefficients of the linear equation problem to solve.
     * @param b Array with the measured values.
     * @return Contains the solution to the linear equation problem minimizing the norm 2.
     */
    public static double[] lls(double[][] a, double[] b) {
        long aL = a[0].length;
        long aN = a.length;
        double[] aConcatenated = new double[0];
        for (double[] time_series : a) {
            aConcatenated = ArrayUtils.addAll(aConcatenated, time_series);
        }
        long bL = b.length;
        double[] result = new double[(int) (aN)];
        lls(aConcatenated, aL, aN, b, bL, result);
        return result;
    }
}
