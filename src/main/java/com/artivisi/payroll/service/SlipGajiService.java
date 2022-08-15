package com.artivisi.payroll.service;

import com.artivisi.payroll.dao.*;
import com.artivisi.payroll.dto.DetailPresensiDto;
import com.artivisi.payroll.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired private CutiKaryawanDao cutiKaryawanDao;
//    @Autowired private RekapGajiBulananDao rekapGajiBulananDao;

    public List<SlipGaji> getSlipGaji(int month, int year){
        List<SlipGaji> result = slipGajiDao.findByBulanAndTahun(month, year);
        for (SlipGaji sg:result) { fillDetails(sg); }
        return result;
    }

    @Transactional
    public void generateSlipGajis(int month, int year) throws Exception {
        try {

//            RekapGajiBulanan rekapGajiBulanan = new RekapGajiBulanan();
//            rekapGajiBulanan.setBulan(month);
//            rekapGajiBulanan.setTahun(year);

            List<LocalDate> workingDays = getWorkingDays(month,year);

            System.out.println(" ======== workingDays = "+workingDays.size()+" ==================");
            for (Karyawan k: karyawanDao.findAll()) {
                System.out.println("================ karyawan : "+k.getFullname()+" ==================");

                SlipGaji slipGaji = new SlipGaji();

                slipGaji.setBulan(month);
                slipGaji.setTahun(year);
                BigDecimal dendaTelat = BigDecimal.ZERO;
                BigDecimal dendaBolos = BigDecimal.ZERO;
                BigDecimal dendaIzin = BigDecimal.ZERO;
                for (LocalDate workingDay:workingDays) {
                    CutiKaryawan cutiKaryawan = cutiKaryawanDao.findByTanggalCutiAndKaryawanId(workingDay, k.getId());
                    if(cutiKaryawan != null) break;

                    Presensi presensi = presensiDao.findByKaryawanIdAndTanggal(k.getId(),workingDay);
                    System.out.println(" ================== tanggal : "+workingDay+" ==================");
                    if(presensi !=null){
                        System.out.println(" ================== waktu absen :"+presensi.getWaktuAbsen()+" ==================");
                        System.out.println(" ================== izin : "+presensi.isIzin()+" ==================");
                        if(!presensi.isIzin()){
                            System.out.println(" ================== telat : "+presensi.isTelat()+" ==================");
                            if(presensi.isTelat()){
                                System.out.println(" ================== denda telat: "+presensi.getDenda()+" ==================");
                                dendaTelat = dendaTelat.add(presensi.getDenda());
                            }
                        }else{
                            if(Presensi.IzinType.PERSONAL.equals(presensi.getIzinType())){
                                System.out.println(" ================== denda izin : "+presensi.getIzinType()+" ==================");
                                dendaIzin = dendaIzin.add(new BigDecimal("25000"));
                            }
                        }
                    }else{
                        System.out.println(" ================== tidak hadir / bolos ==================");
                        BigDecimal gajiPerhari = k.getJabatan().getGajiSatuHariKerja();
                        System.out.println(" ================== denda bolos / gaji perhari = "+gajiPerhari+" ==================");
                        dendaBolos = dendaBolos.add(gajiPerhari);
                    }
                }

                System.out.println(" =========== dendaTelat = "+dendaTelat.toPlainString()+" ==================");
                System.out.println(" =========== dendaAbsent = "+dendaBolos.toPlainString()+" ==================");
                BigDecimal totalDenda = dendaBolos.add(dendaTelat).add(dendaIzin);
                BigDecimal totalGaji = k.getJabatan().getTotalGaji().subtract(totalDenda);

                slipGaji.setKaryawan(k);
                slipGaji.setDendaTelat(dendaTelat);
                slipGaji.setDendaIzin(dendaTelat);
                slipGaji.setDendaAbsent(dendaBolos);
                slipGaji.setTotalDenda(totalDenda);
                slipGaji.setTotalGaji(totalGaji);
                slipGajiDao.save(slipGaji);

//                rekapGajiBulanan.getSlipGajis().add(slipGaji);
//                rekapGajiBulanan.setTotalDendaTelat(rekapGajiBulanan.getTotalDendaTelat().add(dendaTelat));
//                rekapGajiBulanan.setTotalDendaAbsent(rekapGajiBulanan.getTotalDendaTelat().add(dendaBolos));
//                rekapGajiBulanan.setTotalDenda(rekapGajiBulanan.getTotalDenda().add(totalDenda));
//                rekapGajiBulanan.setTotalPenggajian(rekapGajiBulanan.getTotalPenggajian().add(totalGaji));
            }
//            rekapGajiBulananDao.save(rekapGajiBulanan);
        }catch (Exception e){
            e.printStackTrace();
            log.error("ERROR GENERATING SLIP GAJI , ",e);
            throw new Exception("Terjadi kesalahan ketika generate, silahkan hubungi admin",e);
        }
    }

    public SlipGaji getSlipgaji(String id){
        if(!StringUtils.hasText(id)) return null;
        SlipGaji slipGaji = slipGajiDao.findById(id).get();
        slipGaji.setDetails(getDetailPresensi(slipGaji.getBulan(),slipGaji.getTahun(),slipGaji.getKaryawan()));
        return slipGaji;
    }

    public SlipGaji fillDetails(SlipGaji slipGaji){
        slipGaji.setDetails(getDetailPresensi(slipGaji.getBulan(),slipGaji.getTahun(),slipGaji.getKaryawan()));
        return slipGaji;
    }

    public List<DetailPresensiDto> getDetailPresensi(int month, int year, Karyawan karyawan){
        List<DetailPresensiDto> result = new ArrayList<>();

        List<LocalDate> workingDays = getWorkingDays(month,year);

        for (LocalDate workingDay:workingDays) {
            Presensi presensi = presensiDao.findByKaryawanIdAndTanggal(karyawan.getId(),workingDay);
            CutiKaryawan cutiKaryawan = cutiKaryawanDao.findByTanggalCutiAndKaryawanId(workingDay, karyawan.getId());
            result.add(new DetailPresensiDto(workingDay,presensi,cutiKaryawan,karyawan));
        }
        return result;
    }

    public List<LocalDate> getWorkingDays(int month, int year){
        List<LocalDate> workingDays = new ArrayList<>();
        
        LocalDate workFirstDay = LocalDate.of(year,month,28).minusMonths(1);
        LocalDate workLastDay = LocalDate.of(year,month,27);
        long days = workFirstDay.until(workLastDay, ChronoUnit.DAYS);

        System.out.println(" ======== workFirstDay = "+workFirstDay);
        System.out.println(" ======== workLastDay = "+workLastDay);
        System.out.println(" ======== days = "+days);

        List<DayOfWeek> weekdays = Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);
        for (int i = 0; i < days; i++) {
            LocalDate workingDay = workFirstDay.plusDays(i);
            if(!weekdays.contains(workingDay.getDayOfWeek())){
                Long hariLibur = hariLiburDao.countByStartDateLessThanEqualAndEndDateGreaterThanEqual(workingDay,workingDay);
                if(hariLibur == 0){
                    workingDays.add(workingDay);
                }
            }
        }
        
        return workingDays;
    }
}
