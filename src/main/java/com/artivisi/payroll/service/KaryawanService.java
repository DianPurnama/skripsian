package com.artivisi.payroll.service;

import com.artivisi.payroll.dao.KaryawanDao;
import com.artivisi.payroll.entity.Karyawan;
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
public class KaryawanService {

    @Autowired private KaryawanDao karyawanDao;

    public Page<Karyawan> getPageKaryawan(String name, Pageable pageable){
        if(StringUtils.hasText(name)){
            return karyawanDao.findByFullnameContainingIgnoreCase(name, pageable);
        }else{
            return karyawanDao.findAll(pageable);
        }
    }

    public List<Karyawan> getKaryawan(){
        return (List<Karyawan>) karyawanDao.findAll();
    }

    public Optional<Karyawan> getKaryawan(String id){
        if(StringUtils.hasText(id)){
            return karyawanDao.findById(id);
        }
        return Optional.empty();
    }

    @Transactional
    public void saveKaryawan(Karyawan karyawan) throws Exception {
        try {
            karyawanDao.save(karyawan);
        }catch (Exception e){
            log.error("=========== ERROR Menyimpan Karyawan ============", e);
            throw new Exception("Something went wrong, please contact admin",e);
        }
    }

    public void deleteKaryawan(String id) throws Exception {
        try {
            karyawanDao.deleteById(id);
        } catch (Exception e) {
            log.error("==============ERROR Hapus Karyawan==============", e);
            throw new Exception("Gagal Menghapus : "+ e.getMessage(), e);
        }
    }
}
