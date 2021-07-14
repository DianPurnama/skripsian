package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.CutiKaryawan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface CutiKaryawanDao extends PagingAndSortingRepository<CutiKaryawan, String> {

    List<CutiKaryawan> findByTanggalCutiGreaterThanEqualAndTanggalCutiLessThanEqual(LocalDate startDate, LocalDate endDate);
    List<CutiKaryawan> findByTanggalCutiGreaterThanEqualAndTanggalCutiLessThanEqualAndKaryawanId(LocalDate startDate, LocalDate endDate, String idKaryawan);
    CutiKaryawan findByTanggalCutiAndKaryawanId(LocalDate tanggalCuti, String idKaryawan);
    Long countByTanggalCutiAndKaryawanId(LocalDate tanggalCuti, String idKaryawan);

    @Query("select count(cuti) from CutiKaryawan cuti where function('YEAR', cuti.tanggalCuti) = ?1 and cuti.karyawan.id = ?2")
    Long countByYearAndKaryawan(int year, String idKaryawan);
}
