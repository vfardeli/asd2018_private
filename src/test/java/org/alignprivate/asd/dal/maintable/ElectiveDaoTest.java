package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.DegreeCandidacy;
import org.alignprivate.asd.enumeration.EnrollmentStatus;
import org.alignprivate.asd.enumeration.Gender;
import org.alignprivate.asd.enumeration.Term;
import org.alignprivate.asd.enumeration.TermType;
import org.alignprivate.asd.model.Courses;
import org.alignprivate.asd.model.Electives;
import org.alignprivate.asd.model.Experiences;
import org.alignprivate.asd.model.Students;
import org.alignprivate.asd.model.Terms;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ElectiveDaoTest {
	private ElectivesDao electivesDao;

	@BeforeClass
	public void init() {
		electivesDao = new ElectivesDao();
	}

	@Test
	public void addNullElectivesTest() {
		Electives Electives = electivesDao.addElective(null);
		Assert.assertNull(Electives);
	}

	@Test
	public void getAllElectives() {
		List<Electives> electivesList = electivesDao.getAllElectives();

		System.out.println(electivesList.size());
	}

	@Test
	public void addElectivesTest() {
		int tempId = 1221;

		Students newStudent = new Students(tempId + "","tomcat78@gmail.com", "Tom3", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle","98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

		StudentsDao studentsDao =  new StudentsDao();
		studentsDao.addStudent(newStudent);

		Courses newCourse = new Courses(tempId + "", "course2", "course description 2");
		CoursesDao coursesDao = new CoursesDao();
		Courses courses = coursesDao.createCourse(newCourse);

		Terms newTerm = new Terms(Term.FALL, tempId, TermType.QUARTER);
		TermsDao termsDao = new TermsDao();
		Terms term = termsDao.addTerm(newTerm);

		Electives elective = new Electives();
		elective.setStudent(newStudent);
		elective.setCourse(newCourse);
		elective.setTerms(newTerm);
		elective.setRetake(false);
		elective.setGpa((float) 3.2);
		elective.setPlagiarism(false);

		Electives electivesNew = electivesDao.addElective(elective);
		
		electivesDao.deleteElectiveRecord(electivesNew.getElectiveId());
		coursesDao.deleteCourseById( tempId + "");
		termsDao.deleteTerm(term.getTermId());
		studentsDao.deleteStudent(tempId + "");
	}
	
	@Test
	public void deleteElectivesTest() {
		int tempId = 289;

		List<Electives> experiencesOld = electivesDao.getAllElectives();
		int oldSize = experiencesOld.size();

		Students newStudent = new Students(tempId + "","tomcat2e1kk3@gmail.com", "Tom3", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle","98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

		StudentsDao studentsDao =  new StudentsDao();
		studentsDao.addStudent(newStudent);

		Courses newCourse = new Courses(tempId + "", "course2", "course description 2");
		CoursesDao coursesDao = new CoursesDao();
		Courses courses = coursesDao.createCourse(newCourse);

		Terms newTerm = new Terms(Term.FALL, tempId, TermType.QUARTER);
		TermsDao termsDao = new TermsDao();
		Terms term = termsDao.addTerm(newTerm);

		Electives elective = new Electives();
		elective.setStudent(newStudent);
		elective.setCourse(newCourse);
		elective.setTerms(newTerm);
		elective.setRetake(false);
		elective.setGpa((float) 3.2);
		elective.setPlagiarism(false);

		Electives electivesNew = electivesDao.addElective(elective);
		electivesDao.deleteElectiveRecord(electivesNew.getElectiveId());
		
		List<Electives> electivessNew = electivesDao.getAllElectives();
		int newSize = electivessNew.size();	
		Assert.assertEquals(oldSize, newSize); 
		
		coursesDao.deleteCourseById( tempId + "");
		termsDao.deleteTerm(term.getTermId());
		studentsDao.deleteStudent(tempId + "");
	}
//	
	@Test
	public void updateElectivesTest() {
		int tempId = 9187;

		Students newStudent = new Students(tempId + "","tommcautty@gmail.com", "Tom3", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle","98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

		StudentsDao studentsDao =  new StudentsDao();
		studentsDao.addStudent(newStudent);

		Courses newCourse = new Courses(tempId + "", "course2", "course description 2");
		CoursesDao coursesDao = new CoursesDao();
		Courses courses = coursesDao.createCourse(newCourse);

		Terms newTerm = new Terms(Term.FALL, tempId, TermType.QUARTER);
		TermsDao termsDao = new TermsDao();
		Terms term = termsDao.addTerm(newTerm);

		Electives elective = new Electives();
		elective.setStudent(newStudent);
		elective.setCourse(newCourse);
		elective.setTerms(newTerm);
		elective.setRetake(false);
		elective.setGpa((float) 3.2);
		elective.setPlagiarism(false);

		Electives electivesNew = electivesDao.addElective(elective);

		electivesNew.setGpa((float)4.2332);
		electivesDao.updateElectives(electivesNew);
		Assert.assertEquals(electivesNew.getGpa(), ((float)4.2332), 0.1);

		electivesDao.deleteElectiveRecord(electivesNew.getElectiveId());
		coursesDao.deleteCourseById( tempId + "");
		termsDao.deleteTerm(term.getTermId());
		studentsDao.deleteStudent(tempId + "");
	}

	


}
