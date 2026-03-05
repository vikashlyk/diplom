package com.example.bntu.controllers;

import com.example.bntu.models.Specialization;
import com.example.bntu.services.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/specialization")
@RequiredArgsConstructor
public class SpecializationController {
    private final SpecializationService specializationService;

    @GetMapping
    public String specialization(@RequestParam(name = "specialtyName", required = false) String specialtyName, Model model) {
        model.addAttribute("specializations", specializationService.listSpecializations(specialtyName));
        return "specialization";
    }
    @GetMapping("/{id}")
    public String specializationInfo(@PathVariable Long id, Model model) {
        model.addAttribute("specialization", specializationService.getSpecializationById(id));
        return "specialization_info";
    }

    @PostMapping("/create")
    public String createSpecialization(Specialization specialization){
        specializationService.saveSpecialization(specialization);
        return "redirect:/specialization";
    }


    @PostMapping("/delete/{id}")
    public String deleteSpecialization(@PathVariable Long id){
        specializationService.deleteSpecialization(id);
        return "redirect:/specialization";
    }

//    @PutMapping("/update-{id}")
//    public String updateSpecialization (@RequestBody Specialization specialization) {
//        specializationService.update(specialization);
//        return "redirect:/specialization";
//    }
}
