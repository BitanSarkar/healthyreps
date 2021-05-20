/*
 * @author GauravismPS
 * @date 13-05-2021 23:12
 * @version 1.0
 */

package com.sapient.healthyreps.controller;


import java.util.*;
import javax.validation.Valid;
import com.sapient.healthyreps.dao.User;
import com.sapient.healthyreps.exceptions.PasswordMisMatchException;
import org.springframework.web.bind.annotation.*;
import com.sapient.healthyreps.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RestController
@RequestMapping("/login")
public class LoginAPI {

    @GetMapping
    public String health() {
        return "Ready to logIn users";
    }

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public String logIn(@Valid @RequestBody User user) throws Exception {
        Optional<User> _user = userRepository.findByUserName(user.getUserName());
        _user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + user.getUserName()));
        User newUser = _user.orElse(null);
        if(newUser.getPassWord().equals(new String(Base64.getEncoder().encode(user.getPassWord().getBytes())))) {
            try {
                newUser.setLoggedIn(true);
                userRepository.save(newUser);
                return "SUCCESS" + newUser.getUserName();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else throw new PasswordMisMatchException("Wrong Password");
//        return newUser.getPassWord() + " " + user.getPassWord();
        return "FAILED";
    }



    @PostMapping("/logoutuser")
    public String logOut(@Valid @RequestBody User user) throws Exception {
        Optional<User> _user = userRepository.findByUserName(user.getUserName());
        _user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + user.getUserName()));
        User newUser = _user.orElse(null);
        try {
            newUser.setLoggedIn(false);
            userRepository.save(newUser);
            return "SUCCESS";
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "FAILED";

    }
}
