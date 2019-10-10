package com.brightflag.domain;

public class Exam {

	private Integer examID;
	private String examName;
	private Integer studentID;
	private Integer subjectID;

	public Integer getExamID() {
		return examID;
	}

	public void setExamID(Integer examID) {
		this.examID = examID;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Integer getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Integer subjectID) {
		this.subjectID = subjectID;
	}

	@Override
	public String toString() {
		return "Exam [examID=" + examID + ", examName=" + examName + "]";
	}

}
