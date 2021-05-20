/*
 * @author GauravismPS
 * @date 10-05-2021 15:53
 * @version 1.0
 */


package com.sapient.healthyreps.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.User;
//import com.sapient.healthyreps.dbs.ConnectionManager;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterApi {


    @GetMapping
    public String health() {
        return "Service is up";
    }

    @GetMapping("/registered")
    public List<Map<String, Object>> getUsers() throws Exception{
        User user = new User();
        return user.queryAll();
    }

    @GetMapping("/registered/uname/{uname}")
    public List<Map<String, Object>> getUsersbyUserName(@PathVariable String uname) throws Exception{
        User user = new User();
        user.setUserName(uname);
        return user.queryByUserName();
    }

    @GetMapping("/registered/email/{email}")
    public List<Map<String, Object>> getUsersbyEmail(@PathVariable String email) throws Exception{
        User user = new User();
        user.setEmailId(email);
        return user.queryByEmailId();
    }

//    @GetMapping("/testing")
//    public String test() throws Exception {
//        ConnectionManager.writeConnString();
//        return "Writing connection String in db.properties";
//    }

    @PostMapping("/register")
    public String regis(@RequestBody Register reg) throws Exception {
        return reg.reg() > 0 ? "Inserted" : "Not Inserted";
    }

}
