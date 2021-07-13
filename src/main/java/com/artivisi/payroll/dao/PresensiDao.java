package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.Presensi;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface PresensiDao extends PagingAndSortingRepository<Presensi, String> {
    List<Presensi> findByTanggalBetween(LocalDate startDate, LocalDate endDate);
    List<Presensi> findByTanggalBetweenAndKaryawanId(LocalDate startDate, LocalDate endDate, String idKaryawan);
    Presensi findByKaryawanIdAndTanggal(String idKaryawan, LocalDate localDate);
}
