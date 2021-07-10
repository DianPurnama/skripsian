package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.SlipGaji;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SlipGajiDao extends PagingAndSortingRepository<SlipGaji, String> {
}
