package com.brightflag.repository;

import com.brightflag.domain.Exam;
import com.brightflag.domain.Grade;
import com.brightflag.domain.Student;
import com.brightflag.repository.impl.IExamDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExamRepository implements IExamDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;


	@Override
	public List<Exam> getAllExams() {
		return jdbcTemplate.query(QUERY_GET_ALL_EXAMS,
				new BeanPropertyRowMapper<Exam>(Exam.class));
	}

	@Override
	public List<Grade> getGradesOfStudent(Student std) {
		List<Grade> gradeList=
				this.jdbcTemplate.query(QUERY_GET_GRADES_OF_STUDENT,
						new BeanPropertyRowMapper<Grade>(Grade.class), std.getStudentID());

		return gradeList;
	}

	@Override
	public List<Exam> getExamsOfStudent(Student std) {
		List<Exam> examList=
				this.jdbcTemplate.query(QUERY_GET_EXAMS_OF_STUDENT,
						new BeanPropertyRowMapper<Exam>(Exam.class), std.getStudentID());

		return examList;
	}

	@Override
	public Exam findExamByID(int id) {
		Exam exam = new Exam();
		try{
			exam= this.jdbcTemplate.queryForObject(QUERY_FIND_EXAM_BY_ID,
					new BeanPropertyRowMapper<Exam>(Exam.class), id);

		}catch(DataAccessException de){
			System.err.println(de.getMessage());
		}
		return exam;
	}

	@Override
	public boolean createNewExam(Exam exam) {
		int added= jdbcTemplate.update(QUERY_ADD_NEW_EXAM,
				exam.getExamName(), exam.getStudentID(), exam.getSubjectID());

		return (added == 1);
	}

	@Override
	public boolean createGrade(Grade grade) {
		int added= jdbcTemplate.update(QUERY_ADD_NEW_GRADE,
				grade.getExamID(), grade.getStudentID(), grade.getGrade());

		return (added == 1);
	}
}
