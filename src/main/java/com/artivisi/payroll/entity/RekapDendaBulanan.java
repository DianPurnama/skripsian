package com.artivisi.payroll.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table
@Entity
public class RekapDendaBulanan extends BaseEntity{

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull private LocalDateTime createdTime;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

    // periode rekap bulan dan tahun
    // periode rekap akan menghitung denda dari tanggal 28 bulan sebelumnya hingga tanggal 28 bulan ini
    @NotNull private int bulan;
    @NotNull private int tahun;

    // total denda di bulan tersebut
    // karyawan dikenakan denda sebesar Rp. 500 per menit jika absen diatas jam 8
    // max denda perhari adalah Rp. 120,000 (telat 4 jam)
    // jika pegawai absent pada hari tersebut maka akan didenda dengan jumlah sama seperti diatas
    private BigDecimal todalDenda = BigDecimal.ZERO;

}
