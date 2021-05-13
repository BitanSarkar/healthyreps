package com.sapient.reps.api;

import com.sapient.reps.dao.UserRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserRegister, Long> {
    List<UserRegister> findByUserName(String username);
}