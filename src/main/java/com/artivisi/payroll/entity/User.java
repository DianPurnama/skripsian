/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.payroll.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
@Getter @Setter @ToString
public class User extends BaseEntity {

    private static final long serialVersionUID = 3462877375373850405L;

    @Size(max = 100)
    @NotEmpty(message = "Username tidak boleh dikosongkan")
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @NotEmpty
    private String fullname;

    private Boolean active = Boolean.FALSE;

    @Column(nullable = false)
    private String password;

    @NotNull(message = "Role tidak boleh kosong")
    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;

    public enum Role{
        ADMIN
    }
}
