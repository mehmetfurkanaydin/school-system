package com.brightflag.repository;

import java.util.List;

import com.brightflag.domain.Subject;
import com.brightflag.repository.impl.IStudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brightflag.domain.Student;

@Repository
public class StudentRepository implements IStudentDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Student> getAllStudents() {
		return jdbcTemplate.query(QUERY_GET_ALL_STUDENTS,
				new BeanPropertyRowMapper<Student>(Student.class));
	}

	@Override
	public Student findStudentByID(int id) {
		Student student = new Student();
		try{
			student= this.jdbcTemplate.queryForObject(QUERY_FIND_STUDENT_BY_ID,
							new BeanPropertyRowMapper<Student>(Student.class), id);

		} catch(DataAccessException de){
			System.err.println(de.getMessage());
		}
		return student;
	}

	@Override
	public boolean createNewStudent(Student std) {
		int added = this.jdbcTemplate.update(QUERY_ADD_NEW_STUDENT,
						std.getFirstName(),std.getLastName());

		return (added == 1);
	}
}
