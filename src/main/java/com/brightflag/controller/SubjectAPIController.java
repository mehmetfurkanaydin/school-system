package com.brightflag.controller;

import com.brightflag.domain.Student;
import com.brightflag.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brightflag.service.SubjectService;

import java.util.List;

@RestController
public class SubjectAPIController {

	@Autowired
	SubjectService subjectService;

	@RequestMapping("api/getSubjectsOfStudent/{studentId}")
	public List<Subject> getSubjectsOfStudent(@PathVariable String studentId) {
		Student student = new Student();
		student.setStudentID(Integer.parseInt(studentId));
		return subjectService.getSubjectsOfStudent(student);
	}

	@RequestMapping("api/getSubjects")
	public List<Subject> getAllSubjects() {
		return subjectService.getAllSubjects();
	}


}
