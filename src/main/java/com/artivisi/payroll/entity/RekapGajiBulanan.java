//package com.artivisi.payroll.entity;
//
//import lombok.Data;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Table
//@Entity
//@Data
//public class RekapGajiBulanan extends BaseEntity{
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @NotNull private LocalDateTime createdTime = LocalDateTime.now();
//
//    // periode rekap bulan dan tahun
//    // periode rekap akan menghitung denda dari tanggal 28 bulan sebelumnya hingga tanggal 28 bulan ini
//    @NotNull
//    private int bulan;
//    @NotNull private int tahun;
//
//    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "rekapGajiBulanan")
//    private List<SlipGaji> slipGajis = new ArrayList<>();
//
//    @NotNull private BigDecimal totalDendaTelat = BigDecimal.ZERO;
//    @NotNull private BigDecimal totalDendaAbsent = BigDecimal.ZERO;
//
//    @NotNull private BigDecimal totalDenda = BigDecimal.ZERO;
//    @NotNull private BigDecimal totalPenggajian = BigDecimal.ZERO;
//}
