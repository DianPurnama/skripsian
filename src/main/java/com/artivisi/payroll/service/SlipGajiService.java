package com.artivisi.payroll.service;

import com.artivisi.payroll.dao.HariLiburDao;
import com.artivisi.payroll.dao.KaryawanDao;
import com.artivisi.payroll.dao.PresensiDao;
import com.artivisi.payroll.dao.SlipGajiDao;
import com.artivisi.payroll.entity.Karyawan;
import com.artivisi.payroll.entity.Presensi;
import com.artivisi.payroll.entity.SlipGaji;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class SlipGajiService {

    @Autowired private KaryawanDao karyawanDao;
    @Autowired private SlipGajiDao slipGajiDao;
    @Autowired private PresensiDao presensiDao;
    @Autowired private HariLiburDao hariLiburDao;

    public List<SlipGaji> getSlipGaji(int month, int year){
        return slipGajiDao.findByBulanAndTahun(month, year);
    }

    @Transactional
    public void generateSlipGaji(int month, int year) throws Exception {
        try {

            LocalDate workFirstDay = LocalDate.of(year,month,28).minusMonths(1);
            LocalDate workLastDay = LocalDate.of(year,month,27);
            long days = workFirstDay.until(workLastDay, ChronoUnit.DAYS);

            System.out.println("SlipGajiService.generateSlipGaji ======== workFirstDay = "+workFirstDay);
            System.out.println("SlipGajiService.generateSlipGaji ======== workLastDay = "+workLastDay);
            System.out.println("SlipGajiService.generateSlipGaji ======== days = "+days);

            List<DayOfWeek> weekdays = Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);
            List<LocalDate> workingDays = new ArrayList<>();
            for (int i = 0; i < days; i++) {
                LocalDate workingDay = workFirstDay.plusDays(i);
                if(!weekdays.contains(workingDay.getDayOfWeek())){
                    Long hariLibur = hariLiburDao.countByStartDateLessThanEqualAndEndDateGreaterThanEqual(workingDay,workingDay);
                    if(hariLibur == 0){
                        workingDays.add(workingDay);
                    }
                }
            }

            System.out.println("SlipGajiService.generateSlipGaji ======== workingDays = "+workingDays.size());
            for (Karyawan k: karyawanDao.findAll()) {
                System.out.println("SlipGajiService.generateSlipGaji ======== karyawan = "+k.getFullname());

                SlipGaji slipGaji = new SlipGaji();
                BigDecimal dendaTelat = BigDecimal.ZERO;
                BigDecimal dendaBolos = BigDecimal.ZERO;
                for (LocalDate workingDay:workingDays) {
                    Presensi presensi = presensiDao.findByKaryawanIdAndTanggal(k.getId(),workingDay);
                    System.out.println("SlipGajiService.generateSlipGaji ======= tanggal = "+workingDay);
                    if(presensi !=null){
                        System.out.println("SlipGajiService.generateSlipGaji ======= waktu absen = "+presensi.getWaktuAbsen());
                        System.out.println("SlipGajiService.generateSlipGaji ======= izin = "+presensi.isIzin());
                        if(!presensi.isIzin()){
                            System.out.println("SlipGajiService.generateSlipGaji ======= telat = "+presensi.isTelat());
                            if(presensi.isTelat()){
                                System.out.println("SlipGajiService.generateSlipGaji ======= denda telat= "+presensi.getDenda());
                                dendaTelat = dendaTelat.add(presensi.getDenda());
                            }
                        }
                    }else{
                        System.out.println("SlipGajiService.generateSlipGaji =========== tidak hadir / bolos ");
                        BigDecimal gajiPerhari = k.getTotalGaji().divide(new BigDecimal(20));
                        System.out.println("SlipGajiService.generateSlipGaji =========== denda bolos / gaji perhari = "+gajiPerhari);
                        dendaBolos = dendaBolos.add(gajiPerhari);
                    }
                }

                System.out.println("SlipGajiService.generateSlipGaji =========== dendaTelat = "+dendaTelat.toPlainString());
                System.out.println("SlipGajiService.generateSlipGaji =========== dendaAbsent = "+dendaBolos.toPlainString());

                slipGaji.setKaryawan(k);
                slipGaji.setBulan(month);
                slipGaji.setTahun(year);
                slipGaji.setDendaTelat(dendaTelat);
                slipGaji.setDendaAbsent(dendaBolos);

                slipGajiDao.save(slipGaji);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("ERROR GENERATING SLIP GAJI , ",e);
            throw new Exception("Terjadi kesalahan ketika generate, silahkan hubungi admin",e);
        }

    }
}
