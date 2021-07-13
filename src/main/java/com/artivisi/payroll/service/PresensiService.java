package com.artivisi.payroll.service;

import com.artivisi.payroll.dao.HariLiburDao;
import com.artivisi.payroll.dao.KaryawanDao;
import com.artivisi.payroll.dao.PresensiDao;
import com.artivisi.payroll.dto.PresensiDto;
import com.artivisi.payroll.entity.Karyawan;
import com.artivisi.payroll.entity.Presensi;
import com.artivisi.payroll.util.PresensiFileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PresensiService {

    @Autowired private PresensiDao presensiDao;
    @Autowired private KaryawanDao karyawanDao;
    @Autowired private HariLiburDao hariLiburDao;

    private DateTimeFormatter formatLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Presensi> getPresensi(LocalDate startDate, LocalDate endDate, String idKaryawan){
        if(StringUtils.hasText(idKaryawan)){
            return presensiDao.findByTanggalBetweenAndKaryawanId(startDate, endDate,idKaryawan);
        }else{
            return presensiDao.findByTanggalBetween(startDate, endDate);
        }
    }

    public List<Presensi> getPresensi(String startDate, String endDate, String idKaryawan){
        DateTimeFormatter formatLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate sd = LocalDate.parse(startDate,formatLocalDate);
        LocalDate ed = LocalDate.parse(endDate,formatLocalDate);

        if(StringUtils.hasText(idKaryawan)){
            return presensiDao.findByTanggalBetweenAndKaryawanId(sd,ed,idKaryawan);
        }else{
            return presensiDao.findByTanggalBetween(sd,ed);
        }
    }

    @Transactional
    public void save(Presensi presensi) throws Exception {
        Presensi checkDupe = presensiDao.findByKaryawanIdAndTanggal(presensi.getKaryawan().getId(),presensi.getTanggal());
        if(checkDupe != null &&  !checkDupe.getId().equalsIgnoreCase(presensi.getId())){
            throw new Exception(presensi.getKaryawan().getFullname() + " Sudah absen di hari ini, klik label di calendar jika ingin merubah.");
        }
        try {
            presensiDao.save(presensi);
        }catch (Exception e){
            log.error("========== ERROR SAVING PRESENSI =========",e);
            throw new Exception("Terjadi kesalahan, silahkan hubungi admin",e);
        }
    }

    @Transactional
    public void processUpload(MultipartFile file) throws Exception {
        try {
            List<PresensiDto> presensiList = PresensiFileReader.readFile(file);
            presensiDao.saveAll(convertToValidPresensi(presensiList));
        }catch (Exception e){
            log.error("ERROR PROCESSING UPLOAD ========== ",e);
            throw new Exception("Terjadi kesalahan ketika upload file, harap hubungi admin", e);
        }
    }

    public List<Presensi> convertToValidPresensi(List<PresensiDto> presensiDtos){
        List<Presensi> result = new ArrayList<>();
        List<Map<String, String>> mapValidation = new ArrayList<>();

        for (PresensiDto dto:presensiDtos) {
            LocalDate tanggal = LocalDate.parse(dto.getTanggalAbsen(), formatLocalDate);
            LocalTime waktu = LocalTime.parse(dto.getWaktuAbsen());

            Karyawan karyawan = karyawanDao.findByFingerPrintId(dto.getFingerPrintId());
            Long hariLiburCount = hariLiburDao.countByStartDateLessThanEqualAndEndDateGreaterThanEqual(tanggal,tanggal);

            if(karyawan != null && hariLiburCount.intValue() == 0){
                Presensi presensi = presensiDao.findByKaryawanIdAndTanggal(karyawan.getId(),tanggal);
                //cek apakah sudah ada data presensi, jika sudah ada maka update waktu saja.
                if(presensi != null){
                    presensi.setWaktuAbsen(waktu);
                }else{
                    presensi = new Presensi();
                    presensi.setKaryawan(karyawan);
                    presensi.setTanggal(tanggal);
                    presensi.setWaktuAbsen(waktu);
                }

                Map<String, String> validateContraint = new HashMap<>();
                validateContraint.put("karyawanId",presensi.getKaryawan().getId());
                validateContraint.put("tanggal",presensi.getTanggal().format(formatLocalDate));

                if(!mapValidation.contains(validateContraint)){
                    mapValidation.add(validateContraint);
                    result.add(presensi);
                }
            }
        }
        return result;
    }
}
