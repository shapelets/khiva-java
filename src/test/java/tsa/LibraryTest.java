/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package tsa;

import org.junit.Test;

public class LibraryTest {

    @Test
    public void testInfo() {
        Library.infoTSA();
    }


    @Test
    public void testDevice() {
        Library.setTSADevice(1);
        Library.infoTSA();

    }

    @Test
    public void testBE() {
        Library.setTSABackend(Library.BACKEND.TSA_BACKEND_CPU);
        Library.infoTSA();

    }
}
