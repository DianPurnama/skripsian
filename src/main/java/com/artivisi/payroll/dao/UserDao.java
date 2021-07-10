package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserDao extends PagingAndSortingRepository<User, String> {

    Optional<User> findByUsername(String username);

}
