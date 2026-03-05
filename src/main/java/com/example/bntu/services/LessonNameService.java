package com.example.bntu.services;

import com.example.bntu.models.Faculty;
import com.example.bntu.models.LessonName;
import com.example.bntu.repositories.FacultyRepository;
import com.example.bntu.repositories.LessonNameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class LessonNameService {
    private final LessonNameRepository lessonNameRepository;
    public List<LessonName> listLessonNames(String name) {
        if (name != null) {
            List<LessonName> l =  lessonNameRepository.findByNameLike("%"+name+"%");
            if (l.isEmpty())
                return new ArrayList<>();
            else
                return l;
        }

        return lessonNameRepository.findAll();
    }

    public LessonName getLessonNameById(Long id){
        return lessonNameRepository.findById(id).orElse(null) ;
    }
    public void saveNameService(LessonName lessonName) {
        log.info("Saving new {}", lessonName);
        lessonNameRepository.save(lessonName);
    }
    public void deleteNameService(Long id){
        lessonNameRepository.deleteById(id);
    }
}
