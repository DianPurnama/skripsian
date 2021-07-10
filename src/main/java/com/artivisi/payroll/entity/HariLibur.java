package com.artivisi.payroll.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data @Entity @Table
public class HariLibur extends BaseEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull private LocalDate localDate;
    @NotEmpty private String nama;
    @NotEmpty private String deskripsi;

}
