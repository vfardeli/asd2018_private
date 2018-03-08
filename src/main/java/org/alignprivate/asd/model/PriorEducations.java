package org.alignprivate.asd.model;

import org.alignprivate.asd.enumeration.DegreeCandidacy;

import java.util.Date;

public class PriorEducations {
  private int priorEducationId;
  private String institutionName;
  private String majorName;
  private Date graduationDate;
  private float gpa;
  private DegreeCandidacy degreeCandidacy;
  private Students student;

  public PriorEducations(String institutionName, String majorName, Date graduationDate,
                         float gpa, DegreeCandidacy degreeCandidacy, Students student) {
    this.institutionName = institutionName;
    this.majorName = majorName;
    this.graduationDate = graduationDate;
    this.gpa = gpa;
    this.degreeCandidacy = degreeCandidacy;
    this.student = student;
  }

  public PriorEducations() { }

  public int getPriorEducationId() {
    return priorEducationId;
  }

  public void setPriorEducationId(int priorEducationId) {
    this.priorEducationId = priorEducationId;
  }

  public String getInstitutionName() {
    return institutionName;
  }

  public void setInstitutionName(String institutionName) {
    this.institutionName = institutionName;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public Date getGraduationDate() {
    return graduationDate;
  }

  public void setGraduationDate(Date graduationDate) {
    this.graduationDate = graduationDate;
  }

  public float getGpa() {
    return gpa;
  }

  public void setGpa(float gpa) {
    this.gpa = gpa;
  }

  public DegreeCandidacy getDegreeCandidacy() {
    return degreeCandidacy;
  }

  public void setDegreeCandidacy(DegreeCandidacy degreeCandidacy) {
    this.degreeCandidacy = degreeCandidacy;
  }

  public Students getStudent() {
    return student;
  }

  public void setStudent(Students student) {
    this.student = student;
  }
}
