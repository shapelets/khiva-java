
/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package com.gcatsoft.tsa;

/**
 * Class to change internal properties of the TSA library.
 */
public class Library {

    private static String OS;

    static {
        OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("mac") >= 0) {
            System.load("/usr/local/lib/libtsa_jni.dylib");
        } else if (OS.indexOf("win") >= 0) {
            System.load("C:\\Program Files\\TSA\\lib\\tsa_jni.dll");
        } else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0) {
            System.load("/usr/local/lib/libtsa_jni.so");
        }
    }

    /**
     * TSA Backend.
     */
    public enum Backend {
        /**
         * DEFAULT Backend.
         */
        TSA_BACKEND_DEFAULT(0),
        /**
         * CPU Backend.
         */
        TSA_BACKEND_CPU(1),
        /**
         * CUDA Backend.
         */
        TSA_BACKEND_CUDA(2),
        /**
         * OPENCL Backend.
         */
        TSA_BACKEND_OPENCL(4);

        private final int ordinal;

        Backend(int ordinal) {
            this.ordinal = ordinal;
        }

        /**
         * Gets the ordinal from the TSA Backend.
         *
         * @return The ordinal of the TSA Backend.
         */
        public int getTSAOrdinal() {
            return ordinal;
        }

        /**
         * Gets the backend from the ordinal.
         *
         * @param ordinal Integer representing the Backend ordinal.
         * @return The corresponding TSA BACKEND.
         */
        public static Backend getBackendFromOrdinal(int ordinal) {
            switch (ordinal) {
                case 0:
                    return Backend.TSA_BACKEND_DEFAULT;
                case 1:
                    return Backend.TSA_BACKEND_CPU;
                case 2:
                    return Backend.TSA_BACKEND_CUDA;
                case 4:
                    return Backend.TSA_BACKEND_OPENCL;
                default:
                    return Backend.TSA_BACKEND_DEFAULT;
            }
        }
    }

    private native static void info();

    private native static void setBackend(int backend);

    private native static void setDevice(int device);

    private native static int getBackends();

    private native static int getDeviceID();

    private native static int getBackend();

    private native static int getDeviceCount();

    private native static byte[] version();


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
    public static void setTSABackend(Backend tsaBE) {
        setBackend(tsaBE.getTSAOrdinal());
    }

    /**
     * Sets the TSA device.
     *
     * @param device Device selected.
     */
    public static void setTSADevice(int device) {
        setDevice(device);
    }


    /**
     * Get the available backends.
     *
     * @return The available backends.
     */
    public static int getTSABackends() {
        return getBackends();
    }

    /**
     * Get the device id.
     *
     * @return The device id.
     */
    public static int getTSADeviceID() {
        return getDeviceID();
    }

    /**
     * Get the active backend.
     *
     * @return The active backend.
     */
    public static Backend getTSABackend() {
        return Backend.getBackendFromOrdinal(getBackend());
    }

    /**
     * Get the devices count.
     *
     * @return The devices count.
     */
    public static int getTSADeviceCount() {
        return getDeviceCount();
    }

    public static String getTSAVersion() {
        return new String(version());
    }

}