package com.example.bntu.services;

import com.example.bntu.models.LessonMark;
import com.example.bntu.repositories.LessonMarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LessonMarkService {
    private final LessonMarkRepository lessonMarkRepository;
    public List<LessonMark> listLessonMarks (String mark) {
//        if (mark != null) {
//            List<LessonMark> l =  lessonMarkRepository.findByMarkWithLesson(mark);
//            if (l.isEmpty())
//                return lessonMarkRepository.findAll();
//            else
//                return l;
//        }

        return lessonMarkRepository.findAll();
    }
//    public List<LessonMark> listLessonMarks () {
//        return lessonMarkRepository.findAll();
//    }
//    @Transactional
//    public LessonMark getLessonMarkByStudentId_WithLesson(Long studentId){
//        return lessonMarkRepository.findByStudent_Id(studentId).orElse(null) ;
//    }


    @Transactional
    public LessonMark getLessonMarkByIdWithAll(Long id){
        return lessonMarkRepository.findById(id).orElse(null) ;
    }
    public void saveLessonMark( LessonMark lessonMark) {
        log.info("Saving new {}", lessonMark);
        lessonMarkRepository.save(lessonMark);
    }

    public void deleteLessonMark(Long id){
        lessonMarkRepository.deleteById(id);
    }

}
