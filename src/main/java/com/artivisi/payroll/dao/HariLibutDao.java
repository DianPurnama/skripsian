package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.HariLibur;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HariLibutDao extends PagingAndSortingRepository<HariLibur, String> {
}
