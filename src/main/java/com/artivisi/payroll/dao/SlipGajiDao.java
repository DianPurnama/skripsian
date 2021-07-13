package com.artivisi.payroll.dao;

import com.artivisi.payroll.entity.SlipGaji;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SlipGajiDao extends PagingAndSortingRepository<SlipGaji, String> {

    List<SlipGaji> findByBulanAndTahun(int month, int year);

}
