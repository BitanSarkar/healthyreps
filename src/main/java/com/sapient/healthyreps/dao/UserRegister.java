/*
 * @author GauravismPS
 * @date 10-05-2021 15:53
 * @version 1.0
 */

package com.sapient.healthyreps.dao;


import com.sapient.healthyreps.dbs.ConnectionManager;
import com.sapient.healthyreps.dbs.DatabaseManager;
import com.sapient.healthyreps.exceptions.DuplicateEmailException;
import com.sapient.healthyreps.exceptions.DuplicateUsernameException;

import javax.persistence.*;
import javax.persistence.Id;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import static com.sapient.healthyreps.dbs.DatabaseManager.getQuery;

@Entity
@Table(name = "user_register")
public class UserRegister {
    private Long userId;
    String userName;
    String emailId;
    String passWord;
    Boolean isLoggedIn;
    Connection conn = ConnectionManager.getConnection();

    public UserRegister(String uname, String email, String pass) throws Exception {
        userName = uname;
        emailId = email;
        passWord = pass;
        if(queryByUserName().size() > 0) {
            throw new DuplicateUsernameException("Username Already Exists in database");
        }
        if(queryByEmailId().size() > 0) {
            throw new DuplicateEmailException("Email already exists in database");
        }

    }
    public UserRegister() throws Exception {
        super();
    }

    public UserRegister(String uname, String pass) throws Exception {
        userName = uname;
        passWord = pass;
    }

    public void setUserName(String uname) throws Exception {
        userName = uname;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }


    public String getUserName() {
        return userName;
    }

    public void setEmailId(String email) throws Exception {
        emailId = email;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setLoggedIn(Boolean val) throws Exception {
        isLoggedIn = val;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public int Register() throws Exception{

        PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO user_register (user_name, email_id, pass_word) values (?, ?, ?)");
        pst.setString(1, userName);
        pst.setString(2, emailId);
        pst.setString(3, passWord);
        return DatabaseManager.modify(pst);
    }
    public List<Map<String, Object>> queryByUserName() throws Exception{
        PreparedStatement pst = conn.prepareStatement(
                "SELECT user_id, email_id from user_register where user_name=?"
        );
        pst.setString(1, userName);
        return getQuery(pst);
    }
    public List<Map<String, Object>> queryByEmailId() throws Exception{
        PreparedStatement pst = conn.prepareStatement(
                "SELECT user_id, user_name from user_register where emai_lid=?"
        );
        pst.setString(1, emailId);
        return getQuery(pst);
    }

    public List<Map<String, Object>> queryAll() throws Exception {
        PreparedStatement pst = conn.prepareStatement(
                "SELECT user_id, user_name from user_register"
        );
        return getQuery(pst);
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Id
    public Long getUserId() {
        return userId;
    }
}