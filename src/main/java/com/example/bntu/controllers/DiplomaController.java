package com.example.bntu.controllers;

import com.example.bntu.models.*;
import com.example.bntu.services.DiplomaService;
import com.example.bntu.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/diploma")
@RequiredArgsConstructor
public class DiplomaController {
    private final DiplomaService diplomaService;
    private final StudentService studentService;


    @GetMapping
    public String diploma(@RequestParam(name = "topic", required = false) String topic, Model model) {
        model.addAttribute("diploms", diplomaService.listDiploms(topic));
        model.addAttribute("students", studentService.listStudents(""));
        return "diploma";
    }

    @GetMapping("/{id}")
    public String diplomaInfo(@PathVariable Long id, Model model) {
        model.addAttribute("diploma", diplomaService.getDiplomaByIdWithStudent(id));
        return "diploma_info";
    }

    @PostMapping("/create")
    public String createDiploma(Diploma diploma, HttpServletRequest request) {
        Long studentId = Long.valueOf(request.getParameter("studentId"));
        diploma.setStudent(new Student(studentId));

        diplomaService.saveDiploma(diploma);
        return "redirect:/diploma";
    }


    @PostMapping("/delete/{id}")
    public String deleteDiploma(@PathVariable Long id) {
        diplomaService.deleteDiploma(id);
        return "redirect:/diploma";
    }
}
