package com.luizgareis.models;

import com.luizgareis.enums.GenderEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CUSTOMER", schema = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String uuid;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String birthDate;

    @Column
    private String cpf;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address mainAddress;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> adresses;

    @Temporal(TemporalType.TIME)
    private Date createdAt;

    @Temporal(TemporalType.TIME)
    private Date updateAt;
}
