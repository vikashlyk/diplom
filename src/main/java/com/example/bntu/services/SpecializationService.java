package com.example.bntu.services;

import com.example.bntu.models.Specialization;
import com.example.bntu.repositories.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpecializationService {

    private final SpecializationRepository specializationRepository;
    public List<Specialization> listSpecializations(String specialtyName) {
        if (specialtyName != null) {
            List<Specialization> l =  specializationRepository.findBySpecialtyNameLike("%"+specialtyName+"%");
            if (l.isEmpty())
                return new ArrayList<>();
            else
                return l;
        }

        return specializationRepository.findAll();
    }

    public Specialization getSpecializationById(Long id){
        return specializationRepository.findById(id).orElse(null) ;
    }
    public void saveSpecialization(Specialization specialization) {
       log.info("Saving new {}", specialization);
       specializationRepository.save(specialization);
    }
    public void deleteSpecialization(Long id){
        specializationRepository.deleteById(id);
    }

//    public Specialization update(Specialization specialization){
//        return SpecializationRepository.save(specialization)
//    }
}
