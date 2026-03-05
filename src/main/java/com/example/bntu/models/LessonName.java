package com.example.bntu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lesson_names")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    public LessonName(Long id) {
        this.id = id;
    }
}
