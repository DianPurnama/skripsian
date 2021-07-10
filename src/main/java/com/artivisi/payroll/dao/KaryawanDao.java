package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.Karyawan;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KaryawanDao extends PagingAndSortingRepository<Karyawan, String> {
}
