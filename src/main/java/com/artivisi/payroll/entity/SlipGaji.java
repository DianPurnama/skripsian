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

@Data @Table @Entity
public class SlipGaji extends BaseEntity{

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull private LocalDateTime createdTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

    @NotNull private int bulan;
    @NotNull private int tahun;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_rekap_denda_bulanan")
    private RekapDendaBulanan rekapDendaBulanan;

    @NotNull private BigDecimal totalGaji;
}
