package com.example.bntu.controllers;

import com.example.bntu.models.*;
import com.example.bntu.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/lessonMark")
@RequiredArgsConstructor
public class LessonMarkController {
    private final LessonMarkService lessonMarkService;
    private final LessonService lessonService;
    private final StudentService studentService;

    @GetMapping
    public String lessonMark(@RequestParam(name = "mark", required = false) String mark, Model model) {
        model.addAttribute("lessonMarks", lessonMarkService.listLessonMarks(mark));
        model.addAttribute("lessons", lessonService.listLessons(""));
        model.addAttribute("students", studentService.listStudents(""));
        return "lessonMark";
    }
    @GetMapping("/{id}")
    public String lessonMarkInfo(@PathVariable Long id, Model model) {
        model.addAttribute("lessonMark", lessonMarkService.getLessonMarkByIdWithAll(id));
        //дописано
//        model.addAttribute("lessonMarks", lessonMarkService.getLessonMarkByIdWithStudent(id));

      return "lessonMark_info";
    }
    @PostMapping("/create")
    public String createLessonMark(LessonMark lessonMark, HttpServletRequest request) {
        Long lessonId = Long.valueOf(request.getParameter("lessonId"));
        lessonMark.setLesson(new Lesson(lessonId));

        Long studentId = Long.valueOf(request.getParameter("studentId"));
        lessonMark.setStudent(new Student(studentId));

        lessonMarkService.saveLessonMark(lessonMark);
        return "redirect:/lessonMark";
    }

    @PostMapping("/delete/{id}")
    public String deleteLessonMark(@PathVariable Long id) {
        lessonMarkService.deleteLessonMark(id);
        return "redirect:/lessonMark";
    }
}
