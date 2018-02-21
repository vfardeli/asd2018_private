package org.alignprivate.asd.model;

import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.Degree;
import org.alignprivate.asd.enumeration.EnrollmentStatus;
import org.alignprivate.asd.enumeration.Gender;

import java.sql.Blob;

public class Students {
  private String neuId;
  private String email;
  private String firstName;
  private String middleName;
  private String lastName;
  private Gender gender;
  private boolean scholarship;
  private boolean f1Visa;
  private int age;
  private String phone;
  private String address;
  private String state;
  private String zip;
  private EnrollmentStatus enrollmentStatus;
  private Campus campus;
  private Degree degreeCandidacy;
  private Blob photo;

  public Students(String neuId, String email, String firstName, String middleName, String lastName, Gender gender,
                  boolean scholarship, boolean f1Visa, int age, String phone, String address, String state,
                  String zip, EnrollmentStatus enrollmentStatus, Campus campus, Degree degreeCandidacy, Blob photo) {
    this.neuId = neuId;
    this.email = email;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.gender = gender;
    this.scholarship = scholarship;
    this.f1Visa = f1Visa;
    this.age = age;
    this.phone = phone;
    this.address = address;
    this.state = state;
    this.zip = zip;
    this.enrollmentStatus = enrollmentStatus;
    this.campus = campus;
    this.degreeCandidacy = degreeCandidacy;
    this.photo = photo;
  }

  public Students() {
    super();
  }

  public String getNeuId() {
    return neuId;
  }

  public void setNeuId(String neuId) {
    this.neuId = neuId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public boolean isScholarship() {
    return scholarship;
  }

  public void setScholarship(boolean scholarship) {
    this.scholarship = scholarship;
  }

  public boolean isF1Visa() {
    return f1Visa;
  }

  public void setF1Visa(boolean f1Visa) {
    this.f1Visa = f1Visa;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public EnrollmentStatus getEnrollmentStatus() {
    return enrollmentStatus;
  }

  public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
    this.enrollmentStatus = enrollmentStatus;
  }

  public Campus getCampus() {
    return campus;
  }

  public void setCampus(Campus campus) {
    this.campus = campus;
  }

  public Degree getDegreeCandidacy() {
    return degreeCandidacy;
  }

  public void setDegreeCandidacy(Degree degree) {
    this.degreeCandidacy = degree;
  }

  public Blob getPhoto() {
    return photo;
  }

  public void setPhoto(Blob photo) {
    this.photo = photo;
  }

  @Override
  public String toString() {
    return "Students{" +
            "neuId='" + neuId + '\'' +
            ", email='" + email + '\'' +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", gender=" + gender +
            ", scholarship=" + scholarship +
            ", f1Visa=" + f1Visa +
            ", age=" + age +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", state='" + state + '\'' +
            ", zip='" + zip + '\'' +
            ", enrollmentStatus=" + enrollmentStatus +
            ", campus=" + campus +
            ", degree=" + degreeCandidacy +
            ", photo=" + photo +
            '}';
  }
}
