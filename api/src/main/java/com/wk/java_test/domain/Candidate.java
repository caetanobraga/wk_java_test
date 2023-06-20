package com.wk.java_test.domain;

import jakarta.persistence.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Date;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Data @Getter @Setter
public class Candidate {
    @Id
    private UUID id;
    private String name;
    @Column(unique = true)
    private String cpf;
    private String rg;
    private Date birthDate;
    private String sex;
    private String mother;
    private String father;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    private String phone;
    private String cellPhone;
    private Double height;
    private Integer weight;
    private String bloodType;
    public Candidate(){
        this.setId();
    }
    protected void setId(){
        this.id = UUID.randomUUID();
    }
    public UUID getId(){
        return this.id;
    }
}
