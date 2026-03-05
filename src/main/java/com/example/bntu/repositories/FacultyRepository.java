package com.example.bntu.repositories;

import com.example.bntu.models.Faculty;
import com.example.bntu.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    //    @Query("select s From Specialization s WHERE s.specialtyName = ?1")
//    @Query(value = "select * from specialization WHERE specialty_name = ?1", nativeQuery = true)
    List<Faculty> findByNameLike(String name);
}