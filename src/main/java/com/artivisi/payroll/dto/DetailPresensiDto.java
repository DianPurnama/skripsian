package com.artivisi.payroll.dto;

import com.artivisi.payroll.entity.CutiKaryawan;
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
    private CutiKaryawan cutiKaryawan;
    private Karyawan karyawan;

    public String getDescription(){
        if(cutiKaryawan != null)return "Keterangan cuti : "+cutiKaryawan.getKeteranganCuti();


        if(this.presensi != null){
            if(this.presensi.isIzin()){
                if(Presensi.IzinType.PERSONAL.equals(this.presensi.getIzinType())){
                    return "Izin Personal";
                }else{
                    return "Izin Pekerjaan";
                }
            }
            if(presensi.isTelat()) return "Telat "+this.presensi.getTelatMenit()+" menit";
        }else{
            return "Tidak masuk kerja";
        }
        return "Tepat Waktu";
    }

    public BigDecimal getDenda(){
        if(cutiKaryawan != null) return BigDecimal.ZERO;

        BigDecimal denda = BigDecimal.ZERO;

        if(this.presensi != null){
            if(this.presensi.isTelat()){
                denda = denda.add(this.presensi.getDenda());
            }

            if(this.presensi.isIzin()){
                if(Presensi.IzinType.WORK.equals(this.presensi.getIzinType())){
                    denda = BigDecimal.ZERO;
                }else{
                    denda = denda.add(new BigDecimal("25000"));
                }
            }
        }else{
            return this.karyawan.getJabatan().getGajiSatuHariKerja();
        }
        return denda;
    }

}
