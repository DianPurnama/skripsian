package com.artivisi.payroll.dto;

import com.artivisi.payroll.entity.Karyawan;
import com.artivisi.payroll.entity.Presensi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class DetailPresensiDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggal;
    private Presensi presensi;
    private Karyawan karyawan;

    public String getDescription(){
        if(this.presensi != null){
            if(this.presensi.isIzin()) return "Izin";
            if(presensi.isTelat()) return "Telat "+this.presensi.getTelatMenit()+" menit";
        }else{
            return "Tidak masuk kerja";
        }
        return "Tepat Waktu";
    }

    public BigDecimal getDenda(){
        if(this.presensi != null){
            if(this.presensi.isTelat() && !this.presensi.isIzin()) return this.presensi.getDenda();
        }else{
            return this.karyawan.getGajiSatuHariKerja();
        }
        return BigDecimal.ZERO;
    }

}
