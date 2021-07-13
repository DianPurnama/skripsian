package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KaryawanDao extends PagingAndSortingRepository<Karyawan, String> {

    Page<Karyawan> findByFullnameContainingIgnoreCase(String fullname, Pageable pageable);
    Karyawan findByFingerPrintId(String fingerPrintId);
}
