package com.example.bntu.models;

import com.example.bntu.models.enums.LessonType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_name_id", referencedColumnName = "id")
    private LessonName lessonName;

    @Column(name = "hours")
    private String hours;

    @Enumerated(EnumType.STRING)
    private LessonType lessonType;

    public Lesson(Long id) {
        this.id = id;
    }
}
