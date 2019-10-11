package com.brightflag.service;

import java.util.List;

import com.brightflag.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightflag.domain.Student;
import com.brightflag.repository.StudentRepository;

import javax.security.auth.Subject;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public List<Student> getStudents() {
		return studentRepository.getAllStudents();
	}

	public Student getStudent(int studentId) {
		return studentRepository.findStudentByID(studentId);
	}
}
