package com.artivisi.payroll.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table
public class Jabatan extends BaseEntity{

    @NotNull
    private String nameJabatan;

    @NotNull
    private String description;

    @JsonManagedReference
    @OneToOne(mappedBy = "jabatan", orphanRemoval = true, cascade = CascadeType.ALL)
    private Karyawan karyawan;

    @NotNull
    private BigDecimal gajiPokok;

    // uang tunjangan
    @NotNull private BigDecimal uangMakan;
    @NotNull private BigDecimal uangTransport;

    public BigDecimal getTotalGaji(){
        BigDecimal gp = this.gajiPokok != null ? this.gajiPokok : BigDecimal.ZERO;
        BigDecimal um = this.uangMakan != null ? this.uangMakan : BigDecimal.ZERO;
        BigDecimal ut = this.uangTransport != null ? this.uangTransport : BigDecimal.ZERO;

        return gp.add(um).add(ut);
    }

    public BigDecimal getGajiSatuHariKerja(){
        return getTotalGaji().divide(new BigDecimal(20));
    }

}
