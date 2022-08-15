package com.artivisi.payroll.service;

import com.artivisi.payroll.dao.GajiKaryawanDao;
import com.artivisi.payroll.dao.JabatanDao;
import com.artivisi.payroll.dto.JabatanDto;
import com.artivisi.payroll.entity.GajiKaryawan;
import com.artivisi.payroll.entity.Jabatan;
import com.artivisi.payroll.entity.Karyawan;
import com.artivisi.payroll.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@Service @Slf4j
public class JabatanService {

    @Autowired private JabatanDao jabatanDao;
    @Autowired private GajiKaryawanDao gajiKaryawanDao;

    public Page<Jabatan> getPageJabatan(String name, Pageable pageable){
        if(StringUtils.hasText(name)){
            return jabatanDao.findByNameJabatanContainingIgnoreCase(name, pageable);
        }else{
            return jabatanDao.findAll(pageable);
        }
    }

    public Optional<Jabatan> getJabatan(String id){
        if(StringUtils.hasText(id)){
            return jabatanDao.findById(id);
        }
        return Optional.empty();
    }

    @Transactional
    public void saveJabatan(Jabatan jabatan) throws Exception {
        Optional<Jabatan> opJabatan = jabatanDao.findByNameJabatanContainingIgnoreCase(jabatan.getNameJabatan());
        if (opJabatan.isPresent()){
            throw new Exception("Jabatan sudah ada");
        }
        try {
            jabatanDao.save(jabatan);
        }catch (Exception e){
            log.error("=========== ERROR SAVING GajiKaryawan ============", e);
            throw new Exception("Something went wrong, please contact admin",e);
        }
    }

}
