package com.example.bntu.repositories;

import com.example.bntu.models.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecializationRepository extends JpaRepository <Specialization, Long> {

//    @Query("select s From Specialization s WHERE s.specialtyName = ?1")
//    @Query(value = "select * from specialization WHERE specialty_name = ?1", nativeQuery = true)
    List<Specialization> findBySpecialtyNameLike(String specialtyName);
}
