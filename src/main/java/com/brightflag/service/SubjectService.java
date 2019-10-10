package com.brightflag.service;

import com.brightflag.domain.Student;
import com.brightflag.domain.Subject;
import com.brightflag.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public List<Subject> getSubjectsOfStudent(Student std) {
        return subjectRepository.getSubjectsOfStudent(std);
    }

}
