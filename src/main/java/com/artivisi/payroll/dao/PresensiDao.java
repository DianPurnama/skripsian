package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.Presensi;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PresensiDao extends PagingAndSortingRepository<Presensi, String> {
}
