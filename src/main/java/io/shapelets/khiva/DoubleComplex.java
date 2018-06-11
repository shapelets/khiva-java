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
 * DoubleComplex Class.
 */
public class DoubleComplex {
    private double real;
    private double imag;

    public DoubleComplex(double re, double im) {
        set(re, im);
    }

    public DoubleComplex() {
        set(0, 0);
    }

    /**
     * Sets the real and imaginary values.
     *
     * @param re Real value.
     * @param im Imaginary value.
     */
    public void set(double re, double im) {
        real = re;
        imag = im;
    }

    /**
     * Sets the real value.
     *
     * @param re Real value.
     */
    public void setReal(double re) {
        real = re;
    }

    /**
     * Sets the imaginary value.
     *
     * @param im Imaginary value.
     */
    public void setImag(double im) {
        imag = im;
    }

    /**
     * Gets the real value.
     *
     * @return The real value.
     */
    public double getReal() {
        return real;
    }

    /**
     * Gets the imaginary value.
     *
     * @return The imaginary value.
     */
    public double getImag() {
        return imag;
    }

    /**
     * toString function.
     *
     * @return String with the Object representation.
     */
    @Override
    public String toString() {
        String str = String.valueOf(real);

        if (imag < 0) str = str + " - ";
        else str = str + " + ";

        return str + String.valueOf(Math.abs(imag)) + "i";
    }

    /**
     * equals function.
     *
     * @return True if obj is an instance of this class and the values are equal to the ones of the current object.
     * False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        DoubleComplex dc = null;
        if(obj instanceof DoubleComplex) {
            dc = (DoubleComplex) obj;
        }
        return dc != null && this.real == dc.real && this.imag == dc.imag;
    }
}