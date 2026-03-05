package com.example.bntu.controllers;

import com.example.bntu.models.Faculty;
import com.example.bntu.models.LessonName;
import com.example.bntu.services.FacultyService;
import com.example.bntu.services.LessonNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessonName")
@RequiredArgsConstructor
public class LessonNameController {
    private final LessonNameService lessonNameService;

    @GetMapping
    public String lessonName(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("lessonNames", lessonNameService.listLessonNames(name));
        return "lessonName";
    }

    @GetMapping("/{id}")
    public String lessonNameInfo(@PathVariable Long id, Model model) {
        model.addAttribute("lessonNames", lessonNameService.getLessonNameById(id));
        return "lessonName_info";
    }
    @PostMapping("/create")
    public String createLessonName(LessonName lessonName) {
        lessonNameService.saveNameService(lessonName);
        return "redirect:/lessonName";
    }


    @PostMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        lessonNameService.deleteNameService(id);
        return "redirect:/lessonName";
    }
}
