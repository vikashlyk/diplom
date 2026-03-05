package com.example.bntu.repositories;

import com.example.bntu.models.Group;
import com.example.bntu.models.LessonMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g FROM Group g LEFT join fetch g.specialization where g.number = :number")
    List<Group> findByNumberWithSpecialization(@Param("number") int number);
    @Query("select g FROM Group g LEFT join fetch g.specialization")
    List<Group> findAllWithSpecialization();

    @Query("select g FROM Group g LEFT join fetch g.specialization s where g.id = :id")
    Optional<Group> findByWithSpecialization(@Param("id") long id);

    //дописано
//    @Query("select g FROM Group g LEFT join fetch g.faculty")
//    List<Group> findAllWithFaculty();

    @Query("select g FROM Group g LEFT join fetch g.faculty s where g.id = :id")
    Optional<Group> findByWithFaculty(@Param("id") long id);

}