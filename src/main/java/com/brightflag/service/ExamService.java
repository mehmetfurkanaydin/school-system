package com.brightflag.service;

import com.brightflag.domain.Exam;
import com.brightflag.domain.Grade;
import com.brightflag.domain.Student;
import com.brightflag.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;

    public List<Exam> getExamsOfStudent(Student std) {
        return examRepository.getExamsOfStudent(std);
    }

    public List<Grade> getGradesOfStudent(Student std) {
        return examRepository.getGradesOfStudent(std);
    }

    public boolean createNewExam(Exam exam) { return examRepository.createNewExam(exam); }

    public boolean createNewGrade(Grade grade) { return examRepository.createGrade(grade); }
}
