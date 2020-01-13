package com.pink.retail;

import com.pink.retail.util.BlUtilityService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlUtilityServiceTest {

    private BlUtilityService service;

    @Before
    public void init(){
        service = new BlUtilityService();
    }

    @Test
    public void testEncryption(){
       assertEquals("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", service.encryptThisString("test"));
       assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", service.encryptThisString(""));
       assertEquals("40bd001563085fc35165329ea1ff5c5ecbdbbeef", service.encryptThisString("123"));
       assertEquals("6baf8765756553a1319d3f3638beda94965616c5", service.encryptThisString("testy1313"));
    }

}
