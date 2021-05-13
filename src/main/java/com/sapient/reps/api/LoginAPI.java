/**
 * @author GauravismPS
 * @date 13-05-2021 23:12
 * @version 1.0
 */

package com.sapient.reps.api;


import com.sapient.reps.dao.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginAPI {

    @GetMapping
    public String health() throws Exception {
        return "Ready to logIn users";
    }

    @Autowired
    UserRepository userRepository;

    @PostMapping("/loginuser")
    public String logIn(@Valid @RequestBody UserRegister user) throws Exception {
        List<UserRegister> users = userRepository.findByUserName(user.getUserName());
        for(UserRegister candidate : users) {
            if(candidate.getPassWord().equals(user.getPassWord())) {
                candidate.setLoggedIn(true);
                userRepository.save(candidate);
                return "SUCCESS";
            }
        }
        return "FAILED";
    }

    @PostMapping("/logoutuser")
    public String logOut(@Valid @RequestBody UserRegister user) throws Exception {
        List<UserRegister> users = userRepository.findByUserName(user.getUserName());
        for(UserRegister candidate : users) {
            user.setLoggedIn(false);
            userRepository.save(user);
            return "SUCCESS";
        }
        return "FAILED";
    }
}
