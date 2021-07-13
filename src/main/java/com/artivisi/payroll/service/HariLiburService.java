package com.artivisi.payroll.service;

import com.artivisi.payroll.dao.HariLiburDao;
import com.artivisi.payroll.entity.HariLibur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class HariLiburService {

    @Autowired private HariLiburDao hariLiburDao;

    public List<HariLibur> getHariLibur(LocalDate startDate, LocalDate endDate){
        return hariLiburDao.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
    }

    public List<HariLibur> getHariLibur(String startDate, String endDate){
        DateTimeFormatter formatLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate sd = LocalDate.parse(startDate,formatLocalDate);
        LocalDate ed = LocalDate.parse(endDate,formatLocalDate);
        return hariLiburDao.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(sd,ed);
    }

    public void save(HariLibur hariLibur) throws Exception {
        try {
            hariLiburDao.save(hariLibur);
        }catch (Exception e){
            log.error("======= ERROR SAVING HARI LIBUR =======",e);
            throw new Exception("Gagal membuat menyimpan hari libur, harap hubungi admin");
        }
    }

    public void delete(String id) throws Exception {
        try {
            hariLiburDao.deleteById(id);
        }catch (Exception e){
            log.error("======= ERROR DELETING HARI LIBUR =======",e);
            throw new Exception("Gagal menghapus hari libur, harap hubungi admin");
        }
    }
}
