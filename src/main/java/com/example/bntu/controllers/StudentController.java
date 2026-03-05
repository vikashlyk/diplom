package com.example.bntu.controllers;

import com.example.bntu.dto.ExtractParamsDto;
import com.example.bntu.models.Diploma;
import com.example.bntu.models.Group;
import com.example.bntu.models.Student;
import com.example.bntu.services.DiplomaService;
import com.example.bntu.services.GroupService;
import com.example.bntu.services.StudentService;
import com.example.bntu.util.WordCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final WordCreator wordCreator;
    private final DiplomaService diplomaService;
    private final StudentService studentService;
    private final GroupService groupService;

    @GetMapping
    public String diploma(@RequestParam(name = "lastName", required = false) String lastName, Model model) {
        model.addAttribute("students", studentService.listStudents(lastName));
        model.addAttribute("groups", groupService.listGroups());

        return "student";
    }
    @GetMapping("/{id}")
    public String studentInfo(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentByIdWithDiploma(id));
        return "student_info";
    }

    @PostMapping("/{id}/extract")
    public String studentExtract(@PathVariable Long id,
                                 ExtractParamsDto extractParamsDto,
                                 Model model) {
        model.addAttribute("student", studentService.getStudentByIdWithDiploma(id));
        wordCreator.createDoc(id, extractParamsDto);
        return "redirect:/student";
    }

    @PostMapping("/create")
    public String createStudent(Student student, HttpServletRequest request) {

        Long groupId = Long.valueOf(request.getParameter("groupId"));
        student.setGroup(new Group(groupId));

        studentService.saveStudent(student);
        return "redirect:/student";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/student";
    }
}
