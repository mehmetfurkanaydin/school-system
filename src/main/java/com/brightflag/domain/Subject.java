package com.brightflag.domain;

import java.util.List;

public class Subject {

	private Integer subjectID;
	private String subjectName;
	private List studentList;

	public Integer getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Integer subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List getStudentList() {
		return studentList;
	}

	public void setStudentList(List studentList) {
		this.studentList = studentList;
	}

	@Override
	public String toString() {
		return "Subject [subjectID=" + subjectID + ", subjectName=" + subjectName + "]";
	}

}
