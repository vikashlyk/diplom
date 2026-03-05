package com.example.bntu.repositories;

import com.example.bntu.models.Faculty;
import com.example.bntu.models.LessonName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonNameRepository extends JpaRepository<LessonName, Long> {
    List<LessonName> findByNameLike(String name);

}
