package org.alignprivate.asd.model;

import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.DegreeCandidacy;
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
    private String visa;
    private String phoneNum;
    private String address;
    private String state;
    private String city;
    private String zip;
    private EnrollmentStatus enrollmentStatus;
    private Campus campus;
    private DegreeCandidacy degree;
    private Blob photo;

    public Students(String neuId, String email, String firstName, String middleName, String lastName, Gender gender,
                    String visa, String phoneNum, String address, String state, String city, String zip,
                    EnrollmentStatus status, Campus campus, DegreeCandidacy degree, Blob photo) {
        this.neuId = neuId;
        this.email = email;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.scholarship = false;
        this.visa = visa;
        this.phoneNum = phoneNum;
        this.address = address;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.enrollmentStatus = status;
        this.campus = campus;
        this.degree = degree;
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

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public DegreeCandidacy getDegree() {
        return degree;
    }

    public void setDegree(DegreeCandidacy degree) {
        this.degree = degree;
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
                ", visa=" + visa +
                ", phoneNum='" + phoneNum + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", enrollmentStatus=" + enrollmentStatus +
                ", campus=" + campus +
                ", degree=" + degree +
                ", photo=" + photo +
                '}';
    }
}