package com.artivisi.payroll.service;

import com.artivisi.payroll.dao.KaryawanDao;
import com.artivisi.payroll.dao.UserDao;
import com.artivisi.payroll.entity.Karyawan;
import com.artivisi.payroll.entity.User;
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
    @Autowired private UserDao userDao;

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

    public Optional<Karyawan> getKaryawanByEmail(String email){
        if (StringUtils.hasText(email)){
            log.info("DAPET KARYAWAN  =========== {}", email);
            return karyawanDao.findByEmail(email);
        }
        return Optional.empty();
    }

    @Transactional
    public void saveKaryawan(Karyawan karyawan) throws Exception {
        try {
            User user = new User();
            user.setActive(true);
            user.setFullname(karyawan.getFullname());
            user.setRole(User.Role.KRY);
            user.setUsername(karyawan.getEmail());
            user.setPassword("$2a$12$lxkj2ABPAJs7b0nC25J6m.IFoNM7/XiVDTaAZVGG8Ng9O/OdWr4FC");
            karyawanDao.save(karyawan);
            userDao.save(user);
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
