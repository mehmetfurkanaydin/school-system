package com.brightflag.repository;

import com.brightflag.domain.Student;
import com.brightflag.domain.Subject;
import com.brightflag.repository.impl.ISubjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectRepository implements ISubjectDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Subject> getSubjectsOfStudent(Student std) {
        List<Subject> subjectList=
                this.jdbcTemplate.query(QUERY_GET_SUBJECTS_OF_STUDENT,
                        new BeanPropertyRowMapper<Subject>(Subject.class), std.getStudentID());

        return subjectList;
    }

    @Override
    public boolean createNewSubj(Subject subj) {
        int added= jdbcTemplate.update(QUERY_CREATE_NEW_SUBJECT,
                subj.getSubjectName());

        return (added == 1);
    }
}
