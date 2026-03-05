package com.example.bntu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "diploms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Diploma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "hours")
    private String hours;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Diploma(Long id) {
        this.id = id;
    }
}
