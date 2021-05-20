/**
 * @author GauravismPS
 * @date 14-05-2021 03:21
 * @version 1.0
 */

package com.sapient.healthyreps.services;

import com.sapient.healthyreps.dao.RegisteredUserDetails;
import com.sapient.healthyreps.dao.User;
import com.sapient.healthyreps.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;


public class UserRegisterService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(uname);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + uname));
        return user.map(RegisteredUserDetails::new).get();
    }
}
