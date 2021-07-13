package com.artivisi.payroll.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @Table @Entity
public class SlipGaji extends BaseEntity{

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull private LocalDateTime createdTime = LocalDateTime.now();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

    // periode rekap bulan dan tahun
    // periode rekap akan menghitung denda dari tanggal 28 bulan sebelumnya hingga tanggal 28 bulan ini
    @NotNull private int bulan;
    @NotNull private int tahun;

    // total denda di bulan tersebut
    // karyawan dikenakan denda sebesar Rp. 500 per menit jika absen diatas jam 8
    // max denda perhari adalah Rp. 50,000 (telat 100 menit)
    // jika pegawai absent pada hari tersebut maka akan didenda 1 hari kerja (gaji/20)
    @NotNull private BigDecimal dendaTelat = BigDecimal.ZERO;
    @NotNull private BigDecimal dendaAbsent = BigDecimal.ZERO;

    @Transient private BigDecimal totalDenda = BigDecimal.ZERO;
    @Transient private BigDecimal totalGaji = BigDecimal.ZERO;

    public BigDecimal getTotalDenda() {
        return this.dendaAbsent.add(this.dendaTelat);
    }

    public BigDecimal getTotalGaji() {
        return this.karyawan.getTotalGaji().subtract(getTotalDenda());
    }
}
