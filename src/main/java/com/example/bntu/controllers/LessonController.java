package com.example.bntu.controllers;

import com.example.bntu.models.*;
import com.example.bntu.models.enums.LessonType;
import com.example.bntu.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final LessonNameService lessonNameService;

    @GetMapping
    public String lesson(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("lessons", lessonService.listLessons(name));
        model.addAttribute("lessonNames", lessonNameService.listLessonNames(""));
        model.addAttribute("lessonTypes", LessonType.getAll());
        return "lesson";

    }
    @GetMapping("/{id}")
    public String lessonInfo(@PathVariable Long id, Model model) {
        model.addAttribute("lessons", lessonService.getLessonByIdWithLessonName(id));
        return "lesson_info";
    }

    @PostMapping("/create")
    public String createLesson(Lesson lesson, HttpServletRequest request) {
        Long lessonNameId = Long.valueOf(request.getParameter("lessonNameId"));
        lesson.setLessonName(new LessonName(lessonNameId));

        lessonService.saveLesson(lesson);
        return "redirect:/lesson";
    }
    @PostMapping("/delete/{id}")
    public String deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return "redirect:/lesson";
    }
}
