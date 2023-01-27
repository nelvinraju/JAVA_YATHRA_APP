package com.nrk.yathra.server.repo;

import com.nrk.yathra.server.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users readByUserName(String userName);
}
