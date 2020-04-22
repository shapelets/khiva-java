
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
 * Class to change internal properties of the Khiva library.
 */
public class Library {

    static {
        System.loadLibrary("khiva_jni");
    }

    /**
     * Khiva Backend.
     */
    public enum Backend {
        /**
         * DEFAULT Backend.
         */
        KHIVA_BACKEND_DEFAULT(0),
        /**
         * CPU Backend.
         */
        KHIVA_BACKEND_CPU(1),
        /**
         * CUDA Backend.
         */
        KHIVA_BACKEND_CUDA(2),
        /**
         * OPENCL Backend.
         */
        KHIVA_BACKEND_OPENCL(4);

        private final int ordinal;

        Backend(int ordinal) {
            this.ordinal = ordinal;
        }

        /**
         * Gets the ordinal from the Khiva Backend.
         *
         * @return The ordinal of the Khiva Backend.
         */
        public int getKhivaOrdinal() {
            return ordinal;
        }

        /**
         * Gets the backend from the ordinal.
         *
         * @param ordinal Integer representing the Backend ordinal.
         * @return The corresponding Khiva BACKEND.
         */
        public static Backend getBackendFromOrdinal(int ordinal) {
            switch (ordinal) {
                case 1:
                    return Backend.KHIVA_BACKEND_CPU;
                case 2:
                    return Backend.KHIVA_BACKEND_CUDA;
                case 4:
                    return Backend.KHIVA_BACKEND_OPENCL;
                default:
                    return Backend.KHIVA_BACKEND_DEFAULT;
            }
        }
    }

    private native static String backendInfo();

    private native static void setBackend(int backend);

    private native static void setDevice(int device);

    private native static int getBackends();

    private native static int getDeviceID();

    private native static int getBackend();

    private native static int getDeviceCount();

    private native static String version();


    /**
     * Prints information from the current backend.
     *
     * @throws KhivaException If the native function call fails.
     */
    public static void printBackendInfo() {
        System.out.println(backendInfo());
    }

    /**
     * Gets information from the current backend.
     *
     * @return String with information from the active backend.
     * @throws KhivaException If the native function call fails.
     */
    public static String getBackendInfo() {
        return backendInfo();
    }

    /**
     * Sets the Khiva backend.
     *
     * @param khivaBE selected backend.
     * @throws KhivaException If the native function call fails.
     */
    public static void setKhivaBackend(Backend khivaBE) {
        setBackend(khivaBE.getKhivaOrdinal());
    }

    /**
     * Sets the Khiva device.
     *
     * @param device Device selected.
     * @throws KhivaException If the native function call fails.
     */
    public static void setKhivaDevice(int device) {
        setDevice(device);
    }


    /**
     * Gets the available backends.
     *
     * @return The available backends.
     * @throws KhivaException If the native function call fails.
     */
    public static int getKhivaBackends() {
        return getBackends();
    }

    /**
     * Get the device id.
     *
     * @return The device id.
     * @throws KhivaException If the native function call fails.
     */
    public static int getKhivaDeviceID() {
        return getDeviceID();
    }

    /**
     * Gets the active backend.
     *
     * @return The active backend.
     * @throws KhivaException If the native function call fails.
     */
    public static Backend getKhivaBackend() {
        return Backend.getBackendFromOrdinal(getBackend());
    }

    /**
     * Gets the devices count.
     *
     * @return The devices count.
     * @throws KhivaException If the native function call fails.
     */
    public static int getKhivaDeviceCount() {
        return getDeviceCount();
    }

    /**
     * Gets the vesion of the library.
     *
     * @return A string with the khiva version.
     * @throws KhivaException If the native function call fails.
     */
    public static String getKhivaVersion() {
        return version();
    }
}
