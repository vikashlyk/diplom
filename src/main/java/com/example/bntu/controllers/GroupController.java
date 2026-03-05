package com.example.bntu.controllers;

import com.example.bntu.models.Faculty;
import com.example.bntu.models.Group;
import com.example.bntu.models.Specialization;
import com.example.bntu.services.GroupService;
import com.example.bntu.services.FacultyService;
import com.example.bntu.services.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final SpecializationService specializationService;
    private final FacultyService facultyService;

    @GetMapping
    public String group(@RequestParam(name = "number", required = false) Integer number, Model model) {
        model.addAttribute("groups", groupService.listGroups(number));
        model.addAttribute("specializations", specializationService.listSpecializations(""));
        //дописано
        model.addAttribute("faculties", facultyService.listFaculties(""));
        return "group";
    }

    @GetMapping("/{id}")
    public String groupInfo(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getGroupByIdWithSpecialization(id));
        model.addAttribute("group", groupService.getGroupByIdWithFaculty(id));

        return "group_info";
    }



    @PostMapping("/create")
    public String createGroup(Group group, HttpServletRequest request) {
        Long specializationId = Long.valueOf(request.getParameter("specializationId"));
//        group.setSpecialization(specializationService.getSpecializationById(specializationId));
        group.setSpecialization(new Specialization(specializationId));

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
//        group.setSpecialization(specializationService.getSpecializationById(specializationId));
        group.setFaculty(new Faculty(facultyId));

        groupService.saveGroup(group);
        return "redirect:/group";
    }


    @PostMapping("/delete/{id}")
    public String deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return "redirect:/group";
    }
}