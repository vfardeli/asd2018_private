package org.alignprivate.asd.model;

import org.alignprivate.asd.enumeration.Term;

public class Electives {
  private int electiveId;

  private String neuId;
  private String courseId;
  private Term courseTerm;
  private int courseYear;
  private boolean retake = false;
  private float gpa = 0;
  private boolean plagiarism = false;

  public Electives(String neuId, String courseId, Term courseTerm, int courseYear, boolean retake, float gpa, boolean plagiarism) {
    this.neuId = neuId;
    this.courseId = courseId;
    this.courseTerm = courseTerm;
    this.courseYear = courseYear;
    this.retake = retake;
    this.gpa = gpa;
    this.plagiarism = plagiarism;
  }

  public Electives() {
    super();
  }

  public int getElectiveId() {
    return electiveId;
  }

  public void setElectiveId(int electiveId) {
    this.electiveId = electiveId;
  }

  public String getNeuId() {
    return neuId;
  }

  public void setNeuId(String neuId) {
    this.neuId = neuId;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public Term getCourseTerm() {
    return courseTerm;
  }

  public void setCourseTerm(Term courseTerm) {
    this.courseTerm = courseTerm;
  }

  public int getCourseYear() {
    return courseYear;
  }

  public void setCourseYear(int courseYear) {
    this.courseYear = courseYear;
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
