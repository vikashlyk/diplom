package com.example.bntu.services;

import com.example.bntu.models.Diploma;
import com.example.bntu.models.Student;
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
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> listStudents (String lastName) {
        if (lastName != null) {
            List<Student> l =  studentRepository.findByLastNameLike("%"+lastName+"%");
            if (l.isEmpty())
                return new ArrayList<>();
            else
                return l;
        }

        return studentRepository.findByLastNameLike("%");
    }

    public Student getStudentByIdWithDiploma(Long id) {
        return studentRepository.findById(id).orElse(null) ;
    }
    public void saveStudent( Student student) {
        log.info("Saving new {}", student);
        studentRepository.save(student);
    }
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    public Student getStudentByIdWithFullInfo(Long id){
        return studentRepository.findById(id).get();
    }
}
