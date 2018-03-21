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
    public void test_set_backend() {
        int backends = Library.getTSABackends();
        int cuda = backends & 2;
        int opencl = backends & 4;
        int cpu = backends & 1;

        if (cuda != 0) {
            Library.setTSABackend(2);
            Assert.assertEquals(Library.getTSABackend(), 2);
        }
        if (opencl != 0) {
            Library.setTSABackend(4);
            Assert.assertEquals(Library.getTSABackend(), 4);
        }
        if (cpu != 0) {
            Library.setTSABackend(1);
            Assert.assertEquals(Library.getTSABackend(), 1);
        }
    }

    @Test
    public void test_get_device() {
        int backends = Library.getTSABackends();
        int cuda = backends & 2;
        int opencl = backends & 4;
        int cpu = backends & 1;

        if (cuda != 0) {
            Library.setTSABackend(2);
            Library.setTSADevice(0);

            Assert.assertEquals(Library.getTSADevice(), 0);
        }
        if (opencl != 0) {
            Library.setTSABackend(2);
            Library.setTSADevice(0);

            Assert.assertEquals(Library.getTSADevice(), 0);
            Library.setTSADevice(1);

            Assert.assertEquals(Library.getTSADevice(), 1);
        }
        if (cpu != 0) {
            Library.setTSABackend(1);
            Library.setTSADevice(0);

            Assert.assertEquals(Library.getTSADevice(), 0);
        }
    }

}
