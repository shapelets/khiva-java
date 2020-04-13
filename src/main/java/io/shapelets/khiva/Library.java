
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

    private native static String backendInfo() throws Exception;

    private native static void setBackend(int backend) throws Exception;

    private native static void setDevice(int device) throws Exception;

    private native static int getBackends() throws Exception;

    private native static int getDeviceID() throws Exception;

    private native static int getBackend() throws Exception;

    private native static int getDeviceCount() throws Exception;

    private native static String version() throws Exception;


    /**
     * Prints information from the current backend.
     */
    public static void printBackendInfo() throws Exception {
        System.out.println(backendInfo());
    }

    /**
     * Gets information from the current backend.
     *
     * @return String with information from the active backend.
     */
    public static String getBackendInfo() throws Exception {
        return backendInfo();
    }

    /**
     * Sets the Khiva backend.
     *
     * @param khivaBE selected backend.
     */
    public static void setKhivaBackend(Backend khivaBE) throws Exception {
        setBackend(khivaBE.getKhivaOrdinal());
    }

    /**
     * Sets the Khiva device.
     *
     * @param device Device selected.
     */
    public static void setKhivaDevice(int device) throws Exception {
        setDevice(device);
    }


    /**
     * Gets the available backends.
     *
     * @return The available backends.
     */
    public static int getKhivaBackends() throws Exception {
        return getBackends();
    }

    /**
     * Get the device id.
     *
     * @return The device id.
     */
    public static int getKhivaDeviceID() throws Exception {
        return getDeviceID();
    }

    /**
     * Gets the active backend.
     *
     * @return The active backend.
     */
    public static Backend getKhivaBackend() throws Exception {
        return Backend.getBackendFromOrdinal(getBackend());
    }

    /**
     * Gets the devices count.
     *
     * @return The devices count.
     */
    public static int getKhivaDeviceCount() throws Exception {
        return getDeviceCount();
    }

    /**
     * Gets the vesion of the library.
     *
     * @return A string with the khiva version.
     */
    public static String getKhivaVersion() throws Exception {
        return version();
    }
}
