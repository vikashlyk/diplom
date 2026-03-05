package com.example.bntu.models.enums;

import java.util.List;

public enum LessonType {
    COURSE_WORK, COURSE_PROJECT, EXAM, TEST, PRACTICE;

    public static List<LessonType> getAll() {
        return List.of(COURSE_WORK, COURSE_PROJECT, EXAM, TEST, PRACTICE);
    }
}
