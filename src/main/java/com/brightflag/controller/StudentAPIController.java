package com.brightflag.controller;

import java.util.Iterator;
import java.util.List;

import com.brightflag.service.ExamService;
import com.brightflag.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brightflag.domain.Student;
import com.brightflag.service.StudentService;

@RestController
public class StudentAPIController {

	@Autowired
	StudentService studentService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	ExamService examService;

	@RequestMapping("api/getStudents")
	public List<Student> getStudents() {
		List<Student> students = studentService.getStudents();
		Iterator<Student> iterator = students.iterator();
		while(iterator.hasNext()) {
			Student currentStudent = iterator.next();
			currentStudent.setSubjects(subjectService.getSubjectsOfStudent(currentStudent));
			currentStudent.setGrades(examService.getGradesOfStudent(currentStudent));
			currentStudent.setExams(examService.getExamsOfStudent(currentStudent));
		}
		return students;
	}

	@RequestMapping("api/getStudent/{studentId}")
	public Student getStudent(@PathVariable String studentId) {
		Student student = studentService.getStudent(Integer.parseInt(studentId));
		student.setSubjects(subjectService.getSubjectsOfStudent(student));
		student.setGrades(examService.getGradesOfStudent(student));
		student.setExams(examService.getExamsOfStudent(student));
		return student;
	}

	@RequestMapping("api/assignSubject")
	public boolean assignSubjectToStudent(@RequestParam String studentId, @RequestParam String subjectId) {
		return subjectService.assignSubjectToStudent(Integer.parseInt(studentId), Integer.parseInt(subjectId));
	}
}
