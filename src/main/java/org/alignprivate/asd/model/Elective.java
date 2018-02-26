package org.alignprivate.asd.model;

public class Elective {
	private int electiveId;

	private Students student;
	private Courses course;
	private Terms terms;
	private boolean retake = false;
	private float gpa = 0;
	private boolean plagiarism = false;

	public int getElectiveId() {
		return electiveId;
	}

	public void setElectiveId(int electiveId) {
		this.electiveId = electiveId;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public Terms getTerms() {
		return terms;
	}

	public void setTerms(Terms terms) {
		this.terms = terms;
	}

	public boolean isRetake() {
		return retake;
	}

	public void setRetake(boolean retake) {
		this.retake = retake;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public boolean isPlagiarism() {
		return plagiarism;
	}

	public void setPlagiarism(boolean plagiarism) {
		this.plagiarism = plagiarism;
	}

}
