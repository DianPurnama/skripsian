package com.artivisi.payroll.service;

import com.artivisi.payroll.dao.PresensiDao;
import com.artivisi.payroll.entity.Presensi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class PresensiService {

    @Autowired private PresensiDao presensiDao;

    public List<Presensi> getPresensi(LocalDate startDate, LocalDate endDate, String idKaryawan){
        if(StringUtils.hasText(idKaryawan)){
            return presensiDao.findByTanggalGreaterThanAndTanggalLessThanAndKaryawanId(startDate, endDate,idKaryawan);
        }else{
            return presensiDao.findByTanggalGreaterThanAndTanggalLessThan(startDate, endDate);
        }
    }

    public List<Presensi> getPresensi(String startDate, String endDate, String idKaryawan){
        DateTimeFormatter formatLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate sd = LocalDate.parse(startDate,formatLocalDate);
        LocalDate ed = LocalDate.parse(endDate,formatLocalDate);

        if(StringUtils.hasText(idKaryawan)){
            return presensiDao.findByTanggalGreaterThanAndTanggalLessThanAndKaryawanId(sd,ed,idKaryawan);
        }else{
            return presensiDao.findByTanggalGreaterThanAndTanggalLessThan(sd,ed);
        }
    }

    @Transactional
    public void save(Presensi presensi){
        try {
            presensiDao.save(presensi);
        }catch (Exception e){
            log.error("========== ERROR SAVING PRESENSI =========",e);

        }
    }
}
