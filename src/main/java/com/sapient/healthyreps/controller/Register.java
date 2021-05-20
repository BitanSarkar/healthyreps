/*
 * @author GauravismPS
 * @date 10-05-2021 15:53
 * @version 1.0
 */

package com.sapient.healthyreps.controller;

import java.util.Base64;

import com.sapient.healthyreps.dao.User;
import com.sapient.healthyreps.exceptions.EmptyUsernameException;
import com.sapient.healthyreps.exceptions.InvalidEmailException;
import com.sapient.healthyreps.exceptions.PasswordMisMatchException;
import com.sapient.healthyreps.utils.EmailValidator;

public class Register {
    private String uname;
    private String email;
    private String pass1;
    private String pass2;

    public Register() {
        super();
    }

    public Register(String uname, String email, String pass1, String pass2) {
        super();
        this.uname = uname;
        this.email = email;
        this.pass1 = pass1;
        this.pass2 = pass2;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int reg() throws Exception {
        if (!pass1.equals(pass2))
            throw new PasswordMisMatchException("Passwords doesn't match");
        if (!EmailValidator.validate(email))
            throw new InvalidEmailException(email + " is not a valid Email");
        if (uname.isEmpty())
            throw new EmptyUsernameException("Username is Empty");
        pass1 = new String(Base64.getEncoder().encode(pass1.getBytes()));
        User _reg = new User(uname, email, pass1);
        return _reg.Register();
//        Connection conn = ConnectionManager.getConnection();

//        pst = conn.prepareStatement(
//                "INSERT INTO User (username, emailid, password) values (?, ?, ?)");
//        pst.setString(1, uname);
//        pst.setString(2, email);
//        pst.setString(3, pass1);
//        return DatabaseManager.modify(pst);

    }

}
