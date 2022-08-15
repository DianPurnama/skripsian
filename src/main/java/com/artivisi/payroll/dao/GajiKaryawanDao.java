package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.GajiKaryawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GajiKaryawanDao extends PagingAndSortingRepository<GajiKaryawan, String>, JpaSpecificationExecutor<GajiKaryawan> {

//    Page<GajiKaryawan> findByKaryawanFullnameContainingIgnoreCase(String fullname, Pageable pageable);
}
