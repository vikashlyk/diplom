package com.example.bntu.services;

import com.example.bntu.models.Faculty;
import lombok.RequiredArgsConstructor;
import com.example.bntu.repositories.FacultyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;
    public List<Faculty> listFaculties(String name) {
        if (name != null) {
            List<Faculty> l =  facultyRepository.findByNameLike("%"+name+"%");
            if (l.isEmpty())
                return new ArrayList<>();
            else
                return l;
        }

        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(Long id){
        return facultyRepository.findById(id).orElse(null) ;
    }
    public void saveFaculty(Faculty faculty) {
        log.info("Saving new {}", faculty);
        facultyRepository.save(faculty);
    }
    public void deleteFaculty(Long id){
        facultyRepository.deleteById(id);
    }

//    public Specialization update(Specialization specialization){
//        return SpecializationRepository.save(specialization)
//    }
}
