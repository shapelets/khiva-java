
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
        System.loadLibrary("tsa_jni");
    }


    private native static void info();

    private native static void setBackend(int backend);

    private native static void setDevice(int device);

    private native static void getBackends(int backends);

    private native static void getDevice(int device);

    private native static void getBackend(int backend);

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
    public static void setTSABackend(int tsaBE) {
        setBackend(tsaBE);
    }

    /**
     * Sets the TSA device.
     *
     * @param device Device selected.
     */
    public static void setTSADevice(int device) {
        setDevice(device);
    }

    public static int getTSABackends() {
        int backends = 0;

        getBackends(backends);

        return backends;
    }

    public static int getTSADevice() {
        int device = 0;
        getDevice(device);
        return device;

    }

    public static int getTSABackend() {
        int backend = 0;
        getBackend(backend);
        return backend;
    }

}