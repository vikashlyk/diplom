package com.example.bntu.repositories;

import com.example.bntu.models.LessonMark;
import com.example.bntu.models.LessonName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LessonMarkRepository extends JpaRepository<LessonMark, Long> {

//    List<LessonMark> findAllWithLesson();

//    List<LessonMark> findByLesson_LessonName_name(long id);

    List<LessonMark> findByLesson_LessonName_Name(String lesson_lessonName_name);
    List<LessonMark> findByStudent_Id(Long student_id);

//    @Query("")
//    LessonMark findByIdWithAll(long id);

//    List<LessonMark> findByStudent_LastName(String student_lastName);
    List<LessonMark> findByStudent_LastNameAndStudent_FirstName(String student_lastName, String student_firstName);
//
}
