package com.example.bntu.services;

import com.example.bntu.models.Group;
import com.example.bntu.models.Lesson;
import com.example.bntu.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public List<Lesson> listLessons (String name) {
        if (name != null) {
            List<Lesson> l = lessonRepository.findByLessonName_NameLike("%"+name+"%");
            if (l.isEmpty())
                return new ArrayList<>();
            else
                return l;
        }

        return lessonRepository.findAll();
    }
//    public List<Lesson> listLessons () {
//        return lessonRepository.findAll();
//    }
    @Transactional
    public Lesson getLessonByIdWithLessonName(Long id){
        return lessonRepository.findById(id).orElse(null) ;
    }
    public void saveLesson( Lesson lesson) {
        log.info("Saving new {}", lesson);
        lessonRepository.save(lesson);
    }
    public void deleteLesson(Long id){
        lessonRepository.deleteById(id);
    }
}
