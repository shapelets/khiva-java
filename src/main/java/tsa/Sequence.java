/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package tsa;

/**
 * Sequence Class
 */
public class Sequence {
    private double[] distances;

    private int[] index;

    private int[] subsequenceIndex;

    /**
     * Sequence Index constructor
     *
     * @param distances The distances
     * @param index The index
     * @param subsequenceIndex The subsequence indices
     */
    public Sequence(double[] distances, int[] index, int[] subsequenceIndex) {
        this.distances = distances;
        this.index = index;
        this.subsequenceIndex = subsequenceIndex;
    }

    /**
     * Gets distances
     *
     * @return distances
     */
    public double[] getDistances() {
        return distances;
    }

    /**
     * Sets the distances
     *
     * @param distances The distances
     */
    public void setDistances(double[] distances) {
        this.distances = distances;
    }

    /**
     * Gets index
     *
     * @return index
     */
    public int[] getIndex() {
        return index;
    }

    /**
     * Sets index
     *
     * @param index The index
     */
    public void setIndex(int[] index) {
        this.index = index;
    }

    /**
     * GetsIndex
     *
     * @return subsequenceIndex
     */
    public int[] getSubsequenceIndex() {
        return subsequenceIndex;
    }

    /**
     * SetsIndex
     *
     * @param subsequenceIndex The subsequence indices
     */
    public void setSubsequenceIndex(int[] subsequenceIndex) {
        this.subsequenceIndex = subsequenceIndex;
    }
}
