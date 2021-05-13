/*
 * @author GauravismPS
 * @date 10-05-2021 15:53
 * @version 1.0
 */

package com.sapient.healthyreps.login;

import java.util.regex.*;

public class EmailValidator {

    public static boolean validate(String email) {


        String regex = "^(.+)@(.+)$"; 
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
    
}
