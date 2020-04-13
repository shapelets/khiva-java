/*
 * Copyright (c) 2019 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class LibraryTest {

    @Test
    public void testPrintBackendInfo() throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final PrintStream ps = new PrintStream(baos);
        final PrintStream old = System.out;
        System.setOut(ps);

        Library.printBackendInfo();

        // Put things back
        System.out.flush();
        System.setOut(old);

        final String info = baos.toString();
        final String[] words = info.split(" ");
        assertEquals(words[0], "ArrayFire");
    }

    @Test
    public void testBackendInfo() throws Exception {
        final String info = Library.getBackendInfo();
        assertNotNull(info);
        final String[] words = info.split(" ");
        assertEquals(words[0], "ArrayFire");
    }

    @Test
    public void testSetBackend() throws Exception {
        int backends = Library.getKhivaBackends();
        int cuda = backends & Library.Backend.KHIVA_BACKEND_CUDA.getKhivaOrdinal();
        int opencl = backends & Library.Backend.KHIVA_BACKEND_OPENCL.getKhivaOrdinal();
        int cpu = backends & Library.Backend.KHIVA_BACKEND_CPU.getKhivaOrdinal();

        if (cuda != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CUDA);
            assertEquals(Library.getKhivaBackend(), Library.Backend.KHIVA_BACKEND_CUDA);
        }
        if (opencl != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_OPENCL);
            assertEquals(Library.getKhivaBackend(), Library.Backend.KHIVA_BACKEND_OPENCL);
        }
        if (cpu != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
            assertEquals(Library.getKhivaBackend(), Library.Backend.KHIVA_BACKEND_CPU);
        }
    }

    @Test
    public void testGetDevice() throws Exception {
        int backends = Library.getKhivaBackends();
        int cuda = backends & Library.Backend.KHIVA_BACKEND_CUDA.getKhivaOrdinal();
        int opencl = backends & Library.Backend.KHIVA_BACKEND_OPENCL.getKhivaOrdinal();
        int cpu = backends & Library.Backend.KHIVA_BACKEND_CPU.getKhivaOrdinal();

        if (cuda != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CUDA);
            for (int i = 0; i < Library.getKhivaDeviceCount(); i++) {
                Library.setKhivaDevice(i);
                assertEquals(Library.getKhivaDeviceID(), i);
            }
        }
        if (opencl != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_OPENCL);
            for (int i = 0; i < Library.getKhivaDeviceCount(); i++) {
                Library.setKhivaDevice(i);
                assertEquals(Library.getKhivaDeviceID(), i);
            }
        }
        if (cpu != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
            for (int i = 0; i < Library.getKhivaDeviceCount(); i++) {
                Library.setKhivaDevice(i);
                assertEquals(Library.getKhivaDeviceID(), i);
            }
        }
    }

    @Test
    public void testGetKhivaVersion() throws Exception {
        assertNotNull(Library.getKhivaVersion());
        assertFalse(Library.getKhivaVersion().isEmpty());
    }
}
