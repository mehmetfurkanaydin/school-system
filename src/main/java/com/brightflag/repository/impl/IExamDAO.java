package com.brightflag.repository.impl;

import com.brightflag.domain.Exam;
import com.brightflag.domain.Grade;
import com.brightflag.domain.Student;

import java.util.List;
import java.util.Map;

public interface IExamDAO {
    public String QUERY_GET_ALL_EXAMS="SELECT examID,examName,studentID,subjectID FROM exam";
    public String QUERY_ADD_NEW_EXAM="INSERT INTO exam(examID, examName, studentID, subjectID) VALUES (?,?,?,?)";
    public String QUERY_FIND_EXAM_BY_ID="SELECT examID, examName, studentID, subjectID FROM exam WHERE examID=?";

    public String QUERY_GET_GRADES_OF_STUDENT="SELECT g.gradeID, g.examID, e.examName, g.studentID, g.grade\n" +
            "FROM exam AS e\n" +
            "INNER JOIN grade AS g\n" +
            "    ON e.examID = g.examID  WHERE e.studentID = ?";

    public String QUERY_GET_EXAMS_OF_STUDENT="SELECT examID, examName, studentID, subjectID FROM exam WHERE studentID=?";

    public List<Exam> getAllExams();
    public List<Grade> getGradesOfStudent(Student std);
    public List<Exam> getExamsOfStudent(Student std);
    public Exam findExamByID(int id);
    public boolean createNewExam(Exam exam);
}
