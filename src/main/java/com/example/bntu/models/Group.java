package com.example.bntu.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private int number;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    private Specialization specialization;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_Id", referencedColumnName = "id")
    private Faculty faculty;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private List<Student> students;

    public Group(Long id) {
        this.id = id;
    }
}
