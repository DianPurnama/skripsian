package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.HariLibur;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface HariLiburDao extends PagingAndSortingRepository<HariLibur, String> {

    List<HariLibur> findByStartDateGreaterThanAndEndDateLessThan(LocalDate startDate, LocalDate endDate);
}
