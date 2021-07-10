package com.artivisi.payroll.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data @Entity
@Table( name = "presensi",
        indexes = {
                @Index( name="idx_absen_karyawan", columnList="karyawan"),
                @Index( name="idx_absen_tanggal", columnList="tanggal"),
            },
        uniqueConstraints = @UniqueConstraint(columnNames = {"karyawan", "tanggal"})
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
}
