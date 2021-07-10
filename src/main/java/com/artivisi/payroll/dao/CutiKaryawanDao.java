package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.CutiKaryawan;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CutiKaryawanDao extends PagingAndSortingRepository<CutiKaryawan, String> {
}
