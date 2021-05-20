/*
 * @author GauravismPS
 * @date 10-05-2021 15:53
 * @version 1.0
 */

package com.sapient.healthyreps.dbs;

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static String writeConnString() throws Exception {
        Properties p = new Properties();
        OutputStream os = new FileOutputStream("src/main/resources/db.properties");
        p.setProperty("user", "root");
        p.setProperty("password", "toor");
        p.setProperty("url", "jdbc:mysql://172.23.16.1/reps");
        p.store(os, null);
        return "";
    }

    public static Connection getConnection() throws Exception {
        Properties p = new Properties();
        FileReader reader = new FileReader("src/main/resources/db.properties");
        
        p.load(reader);
        String url = p.getProperty("url");
        String user = p.getProperty("user");
        String pass = p.getProperty("password");

        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);

        }
        catch(SQLException e) {
            System.out.println("username : " + user);
            System.out.println("password : " + pass);
            System.out.println("url : " + url);
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
//        return null;

        throw new SQLException("Connect object is null");
    }
}