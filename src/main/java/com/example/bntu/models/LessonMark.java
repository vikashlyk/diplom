package com.example.bntu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lesson_student_marks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    private String mark;

}
