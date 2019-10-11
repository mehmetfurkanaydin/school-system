package com.brightflag.repository.impl;

import com.brightflag.domain.Student;
import com.brightflag.domain.Subject;

import java.util.List;

public interface ISubjectDAO {
    public String QUERY_GET_ALL_SUBJECTS="SELECT subjectID,subjectName FROM subject";
    public String QUERY_CREATE_NEW_SUBJECT="INSERT INTO subject(subjectID,subjectName) VALUES (?,?)";
    public String QUERY_CREATE_NEW_SUBJECT_STUDENT="INSERT INTO studentSubjects(studentID,subjectID) VALUES (?,?)";
    public String QUERY_GET_SUBJECTS_OF_STUDENT="SELECT Su.subjectID, Su.subjectName\n" +
            "FROM Student AS S\n" +
            "INNER JOIN studentSubjects AS SS\n" +
            "    ON S.studentID = SS.studentID\n" +
            "INNER JOIN subject AS Su\n" +
            "    ON SS.subjectID = Su.subjectID WHERE S.studentID = ?" ;

    public List<Subject> findAllSubjects();
    public List<Subject> getSubjectsOfStudent(Student std);
    public boolean createNewSubj(Subject proj);
    public boolean assignSubjectToStudent(int studentId, int subjectId);
}
