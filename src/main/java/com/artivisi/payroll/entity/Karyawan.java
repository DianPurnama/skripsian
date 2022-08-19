package com.artivisi.payroll.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

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

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "id_jabatan", nullable = false)
    @ToString.Exclude
    private Jabatan jabatan;
}
