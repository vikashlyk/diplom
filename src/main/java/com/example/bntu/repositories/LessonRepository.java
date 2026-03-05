package com.example.bntu.repositories;

import com.example.bntu.models.Diploma;
import com.example.bntu.models.Group;
import com.example.bntu.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

//    List<Lesson> findByLessonName_Name(String lessonName_name);
    List<Lesson> findByLessonName_NameLike(String lessonName_name);

}
