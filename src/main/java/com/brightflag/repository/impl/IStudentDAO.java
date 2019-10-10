package com.brightflag.repository.impl;

import com.brightflag.domain.Student;

import java.util.List;

public interface IStudentDAO {
    public String QUERY_GET_ALL_STUDENTS="SELECT studentID,firstName,lastName FROM student";
    public String QUERY_FIND_STUDENT_BY_ID="SELECT studentID,firstName,lastName FROM student s WHERE s.studentID=?";
    public String QUERY_ADD_NEW_STUDENT="INSERT INTO student(studentID,firstName,lastName) VALUES (?,?,?)";

    public List<Student> getAllStudents();
    public Student findStudentByID(int id);
    public boolean createNewStudent(Student std);
}
