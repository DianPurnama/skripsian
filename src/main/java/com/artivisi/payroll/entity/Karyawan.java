package com.artivisi.payroll.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table
public class Karyawan extends BaseEntity {

    @Id
    private String id;

    @NotEmpty private String fullname;

    @Column(unique = true)
    @NotEmpty private String email;

    @Column(unique = true)
    @NotEmpty private String phone;

    @Column(unique = true)
    @NotEmpty private String fingerPrintId;

    private String address;

    @JsonManagedReference
    @OneToOne(mappedBy = "karyawan", orphanRemoval = true, cascade = CascadeType.ALL)
    private GajiKaryawan gajiKaryawan;
}
