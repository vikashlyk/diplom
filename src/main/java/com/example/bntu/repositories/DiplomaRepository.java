package com.example.bntu.repositories;

import com.example.bntu.models.Diploma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiplomaRepository extends JpaRepository<Diploma, Long> {
//    @Query("select d FROM Diploma d LEFT join fetch d.topic where d.student.lastName like :studentLastname")
//    List<Diploma> findByStudentLastnameWithStudent(@Param("studentLastname") String studentLastname);

    List<Diploma> findByStudent_LastNameLike(String student_lastName);

    List<Diploma> findByTopic(String topic);

}
