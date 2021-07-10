package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, String> {



}
