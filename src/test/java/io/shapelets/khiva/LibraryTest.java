/*
 * Copyright (c) 2018 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryTest {

    @Test
    public void testPrintBackendInfo() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        Library.printBackendInfo();

        // Put things back
        System.out.flush();
        System.setOut(old);

        String info = baos.toString();
        String words[] = info.split(" ");
        Assert.assertEquals(words[0], "ArrayFire");
    }

    @Test
    public void testBackendInfo() {
        String info = Library.getBackendInfo();
        String words[] = info.split(" ");
        Assert.assertEquals(words[0], "ArrayFire");
    }

    @Test
    public void testSetBackend() {
        int backends = Library.getKhivaBackends();
        int cuda = backends & Library.Backend.KHIVA_BACKEND_CUDA.getKhivaOrdinal();
        int opencl = backends & Library.Backend.KHIVA_BACKEND_OPENCL.getKhivaOrdinal();
        int cpu = backends & Library.Backend.KHIVA_BACKEND_CPU.getKhivaOrdinal();

        if (cuda != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CUDA);
            Assert.assertEquals(Library.getKhivaBackend(), Library.Backend.KHIVA_BACKEND_CUDA);
        }
        if (opencl != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_OPENCL);
            Assert.assertEquals(Library.getKhivaBackend(), Library.Backend.KHIVA_BACKEND_OPENCL);
        }
        if (cpu != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
            Assert.assertEquals(Library.getKhivaBackend(), Library.Backend.KHIVA_BACKEND_CPU);
        }
    }

    @Test
    public void testGetDevice() {
        int backends = Library.getKhivaBackends();
        int cuda = backends & Library.Backend.KHIVA_BACKEND_CUDA.getKhivaOrdinal();
        int opencl = backends & Library.Backend.KHIVA_BACKEND_OPENCL.getKhivaOrdinal();
        int cpu = backends & Library.Backend.KHIVA_BACKEND_CPU.getKhivaOrdinal();

        if (cuda != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CUDA);
            for (int i = 0; i < Library.getKhivaDeviceCount(); i++) {
                Library.setKhivaDevice(i);
                Assert.assertEquals(Library.getKhivaDeviceID(), i);
            }
        }
        if (opencl != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_OPENCL);
            for (int i = 0; i < Library.getKhivaDeviceCount(); i++) {
                Library.setKhivaDevice(i);
                Assert.assertEquals(Library.getKhivaDeviceID(), i);
            }
        }
        if (cpu != 0) {
            Library.setKhivaBackend(Library.Backend.KHIVA_BACKEND_CPU);
            for (int i = 0; i < Library.getKhivaDeviceCount(); i++) {
                Library.setKhivaDevice(i);
                Assert.assertEquals(Library.getKhivaDeviceID(), i);
            }
        }
    }

    @Test
    public void testGetKhivaVersion() {
        Assert.assertEquals(Library.getKhivaVersion(), getKhivaVersionFromFile());
    }

    private String getKhivaVersionFromFile() {
        String version = "";
        String filePath;

        if (System.getProperty("os.name").startsWith("Windows")) {
            filePath = "C:/Program Files/Khiva/v0/include/khiva/version.h";
        } else {
            filePath = "/usr/local/include/khiva/version.h";
        }

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Matcher m = Pattern.compile("([0-9]+\\.[0-9]+\\.[0-9]+)").matcher(data);
        if (m.find()) {
            version = m.group(1);
        }

        return version;
    }
}