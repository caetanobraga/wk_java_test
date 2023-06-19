package com.wk.java_test.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Getter
@Setter
public class Address {
    @Id
    private UUID id;
    private String cep;
    private String address;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;
    public Address(){
        this.setId();
    }
    protected void setId(){
        this.id = UUID.randomUUID();
    }
    public UUID getId(){
        return this.id;
    }
}
