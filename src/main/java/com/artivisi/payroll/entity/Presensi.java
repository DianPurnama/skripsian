package com.artivisi.payroll.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Data @Entity
@Table( name = "presensi",
        indexes = {
                @Index( name="idx_absen_karyawan", columnList="id_karyawan"),
                @Index( name="idx_absen_tanggal", columnList="tanggal"),
            },
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_karyawan", "tanggal"})
        )
public class Presensi extends BaseEntity {

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggal;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime waktuAbsen;

    @NotNull
    private boolean izin = false;

    @Transient private boolean telat;
    @Transient private long telatMenit = 0;
    @Transient private BigDecimal denda;

    public boolean isTelat(){
        LocalTime jamMasuk = LocalTime.of(8,0,0);
        return jamMasuk.isBefore(this.waktuAbsen);
    }

    public long getTelatMenit(){
        if(this.isTelat()){
            LocalTime jamMasuk = LocalTime.of(8,0,0);
            return jamMasuk.until(this.waktuAbsen, ChronoUnit.MINUTES);
        }
        return 0L;
    }

    public BigDecimal getDenda(){
        BigDecimal result = BigDecimal.ZERO;
        LocalTime jamMasuk = LocalTime.of(8,0,0);
        if(jamMasuk.isBefore(this.waktuAbsen) && !isIzin()){
            BigDecimal dendaPerMenit = new BigDecimal("500");
            // telat, hitung menit
            long menitLewat = jamMasuk.until(this.waktuAbsen, ChronoUnit.MINUTES);

            if(menitLewat >= 100){
                //jika telat lebih dari 100 menit (limit maksimum denda)
                result = dendaPerMenit.multiply(new BigDecimal(100));
            }else{
                result = dendaPerMenit.multiply(new BigDecimal(menitLewat));
            }
        }
        return result;
    }
}
