package com.legal_guardian_soa_canete_2023.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.swing.plaf.basic.BasicDesktopIconUI;

@Table
@Data
public class legalGuardian {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String father_last_name;
    @Column
    private String mother_last_name;
    @Column
    private String documentType;
    @Column
    private String documentNumber;
    @Column
    private String address;
    @Column
    private String cell_phone;
    @Column
    private String email;
    @Column
    private String active;

    public legalGuardian(String name, String father_last_name, String mother_last_name, String documentType, String documentNumber, String address, String cell_phone, String email, String active) {
        this.name = name;
        this.father_last_name = father_last_name;
        this.mother_last_name = mother_last_name;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.address = address;
        this.cell_phone = cell_phone;
        this.email = email;
        this.active = active;
    }
}
