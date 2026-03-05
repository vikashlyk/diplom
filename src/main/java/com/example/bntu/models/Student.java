package com.example.bntu.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "student")
    private Diploma diploma;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "student")
    private List<LessonMark> lessonMarks;

    public Student(Long id) {
        this.id = id;
    }

}
