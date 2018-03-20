
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
 * Library class for loading the C++ Library Library, gets information and sets devices and back-end.
 */
public class Library {

    static {
        System.loadLibrary("TSALIB");
    }

    public enum BACKEND {
        TSA_BACKEND_CPU,
        TSA_BACKEND_OPENCL,
        TSA_BACKEND_CUDA,
    }

    private native static void info();

    private native static void setBackend(int backend);

    private native static void setDevice(int device);

    /**
     * Gets the devices info.
     */
    public static void infoTSA() {
        info();
    }

    /**
     * Sets the TSA back-end.
     *
     * @param tsaBE Back-end selected.
     */
    public static void setTSABackend(BACKEND tsaBE) {
        setBackend(tsaBE.ordinal());
    }

    /**
     * Sets the TSA device.
     *
     * @param device Device selected.
     */
    public static void setTSADevice(int device) {
        setDevice(device);
    }

}