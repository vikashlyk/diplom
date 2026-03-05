package com.example.bntu.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specializations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specialty_code")
    private String specialtyCode;

    @Column(name = "specialty_index")
    private String specialtyIndex;

    @Column(name = "specialty_name")
    private String specialtyName;

    @Column(name = "direction_name")
    private String directionName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specialization")
    private List<Group> groups;

    public Specialization(Long id) {
        this.id = id;
    }

}
