package com.sapient.healthyreps.LoginTest;


import com.sapient.healthyreps.controller.Register;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {
    
    // @Test
    public void RegTest() throws Exception {

        String uname = "Zeref";
        String email = "Zeref@drag.com";
        String pass1 = "123456";
        String pass2 = "123456";
        Register reg = new Register(uname, email, pass1, pass2);
        int status = reg.reg();
        assertEquals(status, 1);
    }
}
