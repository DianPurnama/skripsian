package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.CutiKaryawan;
import com.artivisi.payroll.entity.Jabatan;
import com.artivisi.payroll.entity.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface JabatanDao extends PagingAndSortingRepository<Jabatan, String> {
    Page<Jabatan> findByNameJabatanContainingIgnoreCase(String name, Pageable pageable);
    Optional<Jabatan> findByNameJabatanContainingIgnoreCase(String name);
}
