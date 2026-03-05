package com.example.bntu.services;


import com.example.bntu.models.Group;
import com.example.bntu.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    public List<Group> listGroups (Integer number) {
        if (number != null) {
            List<Group> l =  groupRepository.findByNumberWithSpecialization(number);
            if (l.isEmpty())
                return groupRepository.findAllWithSpecialization();
            else
                return l;
        }

        return groupRepository.findAll();
    }
    public List<Group> listGroups () {
        return groupRepository.findAll();
    }

    @Transactional
    public Group getGroupByIdWithSpecialization(Long id){
        return groupRepository.findByWithSpecialization(id).orElse(null) ;
    }

    //дописано
    @Transactional
    public Group getGroupByIdWithFaculty(Long id){
        return groupRepository.findByWithFaculty(id).orElse(null) ;
    }
    public void saveGroup( Group group) {
//       (Principal principal, Group group)  group.setSpecialization(getSpecializationByPrincipal(principal));
        log.info("Saving new {}", group);
        groupRepository.save(group);
    }

    public void deleteGroup(Long id){
        groupRepository.deleteById(id);
    }

}
