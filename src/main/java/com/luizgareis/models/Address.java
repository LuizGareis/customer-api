package com.luizgareis.models;

import com.luizgareis.enums.StateEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS", schema = "CUSTOMER")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @Column(name = "CITY")
    private String city;

    @Column(name = "NEIGHBORHOOD")
    private String neighborhood;

    @Column(name = "ZIPCODE")
    private String zipCode;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "ADDITIONAL_INFORMATION")
    private String additionalInformation;

    @Column(name = "MAIN")
    private Boolean main;

    @JoinColumn(name = "ID_CUSTOMER", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

}
