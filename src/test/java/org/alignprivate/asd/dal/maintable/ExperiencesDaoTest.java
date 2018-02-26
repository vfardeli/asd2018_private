package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.DegreeCandidacy;
import org.alignprivate.asd.enumeration.EnrollmentStatus;
import org.alignprivate.asd.enumeration.Gender;
import org.alignprivate.asd.model.Experiences;
import org.alignprivate.asd.model.Students;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExperiencesDaoTest {
	private ExperiencesDao experiencesDao;

	@Before
	public void init() {
		experiencesDao = new ExperiencesDao();
	}

	@Test
	public void addNullExperienceTest() {
		Experiences experiences = experiencesDao.addExperience(null);
		Assert.assertNull(experiences);
	}

	@Test
	public void addExperiencesTest() {
		Students newStudent = new Students("0000000","tomcat@gmail.com", "Tom", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle","98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

		StudentsDao studentsDao =  new StudentsDao();
		studentsDao.addStudent(newStudent);

		Experiences experiences = new Experiences();
		experiences.setTitle("title");
		experiences.setDescription("description34");
		experiences.setStudent(newStudent);

		Experiences experiencesNew = experiencesDao.addExperience(experiences);

		Assert.assertTrue(experiencesNew.getDescription().equals(experiences.getDescription().toString()));

		experiencesDao.deleteExperienceRecord(experiencesNew.getExperienceId());
		studentsDao.deleteStudent("0000000");
	}


	@Test
	public void getAllEperiencesTest() {
		List<Experiences> experiencesList = experiencesDao.getAllExperiences();

		// add a stuent
		Students newStudent = new Students("00000094","tomcat44@gmail.com", "Tom21", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle", "98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);
		StudentsDao studentdao = new StudentsDao();
		studentdao.addStudent(newStudent);

		// add experience to table
		Experiences newExperience = new Experiences(newStudent, "Angular 3 Conference", "my desc");
		Experiences newExperience2 = new Experiences(newStudent, "Angular 3 Conference22", "my desc22");
		experiencesDao.addExperience(newExperience);
		experiencesDao.addExperience(newExperience2);

		// get the exp and match
		List<Experiences> experiencesListNew = experiencesDao.getAllExperiences();
		Assert.assertTrue(experiencesListNew.size() == experiencesList.size() + 2);

		experiencesDao.deleteExperienceRecord(newExperience.getExperienceId());
		experiencesDao.deleteExperienceRecord(newExperience2.getExperienceId());
		studentdao.deleteStudent("00000094");
	}

	@Test
	public void getExperienceById() {
		// add a stuent
		Students newStudent = new Students("00000094","tomcat44@gmail.com", "Tom21", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle", "98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);
		StudentsDao studentdao = new StudentsDao();
		studentdao.addStudent(newStudent);

		// add experience to table
		Experiences experience = new Experiences(newStudent, "Angular 3 Conference", "my desc");
		Experiences newExperience = experiencesDao.addExperience(experience);

		Experiences addedExperience = experiencesDao.getExperience(newExperience.getExperienceId());
		Assert.assertEquals(newExperience.getDescription(), addedExperience.getDescription());
		experiencesDao.deleteExperienceRecord(addedExperience.getExperienceId());
		studentdao.deleteStudent("00000094");
	}

	@Test
	public void deleteExperienceTest() {
		List<Experiences> experiencesOld = experiencesDao.getAllExperiences();
		int oldSize = experiencesOld.size();

		Students newStudent = new Students("00000094","tomcat44@gmail.com", "Tom21", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle", "98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);
		StudentsDao studentdao = new StudentsDao();
		studentdao.addStudent(newStudent);

		// add experience to table
		Experiences experience = new Experiences(newStudent, "Angular 3 Conference", "my desc");
		Experiences newExperience = experiencesDao.addExperience(experience);	

		// now delete the experience
		experiencesDao.deleteExperienceRecord(newExperience.getExperienceId());

		List<Experiences> experiencesNew = experiencesDao.getAllExperiences();
		int newSize = experiencesNew.size();	
		Assert.assertEquals(oldSize, newSize); 
		
		studentdao.deleteStudent("00000094");
	}

	@Test
	public void updateExperiencesTest() {
		// add a stuent
		Students newStudent = new Students("00000094","tomcat44@gmail.com", "Tom21", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle", "98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);
		StudentsDao studentdao = new StudentsDao();
		studentdao.addStudent(newStudent);

		// add experience to table
		Experiences newExperience = new Experiences(newStudent, "Angular 3 Conference", "my desc");
		experiencesDao.addExperience(newExperience);
		
		// now update it
		newExperience.setDescription("new desc908");
		Experiences addedExperience = experiencesDao.getExperience(newExperience.getExperienceId());
		Assert.assertEquals(newExperience.getDescription(), "new desc908");
		
		experiencesDao.deleteExperienceRecord(newExperience.getExperienceId());
		studentdao.deleteStudent("00000094");
	}
}
