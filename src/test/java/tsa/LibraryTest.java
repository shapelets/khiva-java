/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package tsa;

import org.junit.Assert;
import org.junit.Test;

public class LibraryTest {

    @Test
    public void testSetBackend() {
        int backends = Library.getTSABackends();
        int cuda = backends & Library.Backend.TSA_BACKEND_CUDA.getTSAOrdinal();
        int opencl = backends & Library.Backend.TSA_BACKEND_OPENCL.getTSAOrdinal();
        int cpu = backends & Library.Backend.TSA_BACKEND_CPU.getTSAOrdinal();


        if (cuda != 0) {
            Library.setTSABackend(Library.Backend.TSA_BACKEND_CUDA);
            Assert.assertEquals(Library.getTSABackend(), Library.Backend.TSA_BACKEND_CUDA);
        }

        if (opencl != 0) {
            Library.setTSABackend(Library.Backend.TSA_BACKEND_OPENCL);
            Assert.assertEquals(Library.getTSABackend(), Library.Backend.TSA_BACKEND_OPENCL);
        }
        if (cpu != 0) {
            Library.setTSABackend(Library.Backend.TSA_BACKEND_CPU);
            Assert.assertEquals(Library.getTSABackend(), Library.Backend.TSA_BACKEND_CPU);
        }
    }

    @Test
    public void testGetDevice() {
        int backends = Library.getTSABackends();
        int cuda = backends & Library.Backend.TSA_BACKEND_CUDA.getTSAOrdinal();
        int opencl = backends & Library.Backend.TSA_BACKEND_OPENCL.getTSAOrdinal();
        int cpu = backends & Library.Backend.TSA_BACKEND_CPU.getTSAOrdinal();

        if (cuda != 0) {
            Library.setTSABackend(Library.Backend.TSA_BACKEND_CUDA);
            for (int i = 0; i < Library.getTSADeviceCount(); i++) {
                Library.setTSADevice(i);
                Assert.assertEquals(Library.getTSADeviceID(), i);
            }

        }
        if (opencl != 0) {
            Library.setTSABackend(Library.Backend.TSA_BACKEND_OPENCL);
            for (int i = 0; i < Library.getTSADeviceCount(); i++) {
                Library.setTSADevice(i);
                Assert.assertEquals(Library.getTSADeviceID(), i);
            }
        }
        if (cpu != 0) {
            Library.setTSABackend(Library.Backend.TSA_BACKEND_CPU);
            for (int i = 0; i < Library.getTSADeviceCount(); i++) {
                Library.setTSADevice(i);
                Assert.assertEquals(Library.getTSADeviceID(), i);
            }
        }
    }

    @Test
    public void testGetTSAVersion() {
        Assert.assertEquals(Library.getTSAVersion(), "0.0.1");
    }
}