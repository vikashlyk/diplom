package com.example.bntu.controllers;

import com.example.bntu.models.Faculty;
import com.example.bntu.services.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @GetMapping
    public String faculty(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("faculties", facultyService.listFaculties(name));
        return "faculty";
    }

    @GetMapping("/{id}")
    public String facultyInfo(@PathVariable Long id, Model model) {
        model.addAttribute("faculty", facultyService.getFacultyById(id));
        return "faculty_info";
    }

    @PostMapping("/create")
    public String createFaculty(Faculty faculty) {
        facultyService.saveFaculty(faculty);
        return "redirect:/faculty";
    }


    @PostMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return "redirect:/faculty";
    }

}
