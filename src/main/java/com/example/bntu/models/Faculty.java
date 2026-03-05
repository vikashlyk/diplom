package com.example.bntu.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dean")
    private String dean;

    @Column(name = "secretary")
    private String secretary;

    @Column(name = "rector")
    private String rector;

    @Column(name = "faculty_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private List<Group> groups;

    public Faculty(Long id) {
        this.id = id;
    }
}
