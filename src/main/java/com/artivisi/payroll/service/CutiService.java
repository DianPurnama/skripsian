package com.artivisi.payroll.service;

import com.artivisi.payroll.dao.CutiKaryawanDao;
import com.artivisi.payroll.entity.CutiKaryawan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class CutiService {
    
    @Autowired private CutiKaryawanDao cutiKaryawanDao;

    public List<CutiKaryawan> getCutiKaryawan(LocalDate startDate, LocalDate endDate, String idKaryawan){
        if(StringUtils.hasText(idKaryawan)){
            return cutiKaryawanDao.findByTanggalCutiGreaterThanEqualAndTanggalCutiLessThanEqualAndKaryawanId(startDate, endDate,idKaryawan);
        }else {
            return cutiKaryawanDao.findByTanggalCutiGreaterThanEqualAndTanggalCutiLessThanEqual(startDate, endDate);
        }
    }

    public List<CutiKaryawan> getCutiKaryawan(String startDate, String endDate, String idKaryawan){
        DateTimeFormatter formatLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate sd = LocalDate.parse(startDate,formatLocalDate);
        LocalDate ed = LocalDate.parse(endDate,formatLocalDate);

        if(StringUtils.hasText(idKaryawan)){
            return cutiKaryawanDao.findByTanggalCutiGreaterThanEqualAndTanggalCutiLessThanEqualAndKaryawanId(sd, ed,idKaryawan);
        }else {
            return cutiKaryawanDao.findByTanggalCutiGreaterThanEqualAndTanggalCutiLessThanEqual(sd, ed);
        }
    }

    public void save(CutiKaryawan cutiKaryawan) throws Exception {

        Long cutiCountForTheYear = cutiKaryawanDao.countByYearAndKaryawan(LocalDate.now().getYear(),cutiKaryawan.getKaryawan().getId());
        if(cutiCountForTheYear >= 12){
            log.error("======= FAILED SAVING CUTI KARYAWAN, ALREADY HAVE 12 DAYS OF PAID LEAVE =======");
            throw new Exception(cutiKaryawan.getKaryawan().getFullname() + " Sudah cuti 12 hari di tahun ini");
        }

        try {
            cutiKaryawanDao.save(cutiKaryawan);
        }catch (Exception e){
            log.error("======= ERROR SAVING CUTI KARYAWAN =======",e);
            throw new Exception("Gagal membuat menyimpan CUTI KARYAWAN, harap hubungi admin");
        }
    }

    public void delete(String id) throws Exception {
        try {
            cutiKaryawanDao.deleteById(id);
        }catch (Exception e){
            log.error("======= ERROR DELETING CUTI KARYAWAN =======",e);
            throw new Exception("Gagal menghapus hari libur, harap hubungi admin");
        }
    }
    
}
