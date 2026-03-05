package com.example.bntu.services;

import com.example.bntu.models.Diploma;
import com.example.bntu.models.Student;
import com.example.bntu.repositories.DiplomaRepository;
import com.example.bntu.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiplomaService {
    private final DiplomaRepository  diplomaRepository;
    private final StudentRepository studentRepository;
    public List<Diploma> listDiploms (String lastName) {
        if (lastName != null) {
            List<Diploma> l =  diplomaRepository.findByStudent_LastNameLike("%"+lastName+"%");
            if (l.isEmpty())
                return new ArrayList<>();
            else
                return l;
        }

        return diplomaRepository.findAll();
    }

    @Transactional
    public Diploma getDiplomaByIdWithStudent(Long id){
        return diplomaRepository.findById(id).orElse(null) ;
    }

    public void saveDiploma( Diploma diploma) {
        log.info("Saving new {}", diploma);
        Student s = studentRepository.findById(diploma.getStudent().getId())
                        .orElse(null);
        diploma.setStudent(s);
        diploma = diplomaRepository.save(diploma);
        s.setDiploma(diploma);
        studentRepository.save(s);
    }

    public void deleteDiploma(Long id){
        diplomaRepository.deleteById(id);
    }

}
