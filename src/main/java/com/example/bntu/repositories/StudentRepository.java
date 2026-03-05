package com.example.bntu.repositories;

import com.example.bntu.models.Diploma;
import com.example.bntu.models.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository  extends JpaRepository<Student, Long> {

//    @Query("select s from Student s left join s.diploma where s.lastName like :lastName")
//    List<Student> findByLastNameLike(@Param("lastName") String lastName);


//    @Override
//    @EntityGraph(attributePaths = {"diploma"})
//    @Query("select s from Student s left join fetch s.diploma where s.lastName like :lastName")
    List<Student> findByLastNameLike(String lastName);
    List<Student> findAllByGroup(String Group);

//    @Override
//    @EntityGraph(attributePaths = {"diploma"})
//    @Query("select s from Student s left join fetch s.diploma where s.id = :id")
//    Optional<Student> findById(@Param("id") Long id);
}
