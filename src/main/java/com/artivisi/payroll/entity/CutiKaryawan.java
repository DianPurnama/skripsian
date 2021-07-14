package com.artivisi.payroll.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

// karyawan hanya dapat cuti 12 hari per tahun

@Data @Entity @Table
public class CutiKaryawan extends BaseEntity {

    @ManyToOne @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

    @NotEmpty private String keteranganCuti;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull private LocalDate tanggalCuti;
}
