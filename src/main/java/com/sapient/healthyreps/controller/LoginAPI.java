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

    public User validate(User user) throws Exception {
        Optional<User> _user = userRepository.findByUserName(user.getUserName());
        _user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + user.getUserName()));
        return _user.orElse(null);
    }

    @PostMapping("/user")
    public String logIn(@Valid @RequestBody User user) throws Exception {
//        Optional<User> _user = userRepository.findByUserName(user.getUserName());
//        _user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + user.getUserName()));
        User newUser = validate(user);
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
        return "FAILED";
    }

    @PostMapping("/update")
    public String upDatePass(@Valid @RequestBody User user) throws Exception {
        User newUser = validate(user);
        newUser.setPassWord(new String(Base64.getEncoder().encode(user.getPassWord().getBytes())));
        try {
//            newUser.setLoggedIn(true);
            userRepository.save(newUser);
            return "SUCCESS" + newUser.getUserName();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "FAILED";

    }

    @PostMapping("/recover")
    public String recoverPass(@Valid @RequestBody User user) throws Exception {
        User newUser = validate(user);
        return new String(Base64.getDecoder().decode(newUser.getPassWord()));
    }



    @PostMapping("/logoutuser")
    public String logOut(@Valid @RequestBody User user) throws Exception {
        User newUser = validate(user);
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
