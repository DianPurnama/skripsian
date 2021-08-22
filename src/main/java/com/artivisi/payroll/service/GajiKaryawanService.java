package com.artivisi.payroll.service;

import com.artivisi.payroll.dao.GajiKaryawanDao;
import com.artivisi.payroll.entity.GajiKaryawan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GajiKaryawanService {

    @Autowired private GajiKaryawanDao gajiKaryawanDao;

    public Page<GajiKaryawan> getPageGajiKaryawan(String name, Pageable pageable){
        if(StringUtils.hasText(name)){
            return gajiKaryawanDao.findByKaryawanFullnameContainingIgnoreCase(name, pageable);
        }else{
            return gajiKaryawanDao.findAll(pageable);
        }
    }

    public List<GajiKaryawan> getGajiKaryawan(){
        return (List<GajiKaryawan>) gajiKaryawanDao.findAll();
    }

    public Optional<GajiKaryawan> getGajiKaryawan(String id){
        if(StringUtils.hasText(id)){
            return gajiKaryawanDao.findById(id);
        }
        return Optional.empty();
    }

    @Transactional
    public void saveGajiKaryawan(GajiKaryawan gajiKaryawan) throws Exception {
        try {
            gajiKaryawanDao.save(gajiKaryawan);
        }catch (Exception e){
            log.error("=========== ERROR SAVING GajiKaryawan ============", e);
            throw new Exception("Something went wrong, please contact admin",e);
        }
    }
}
