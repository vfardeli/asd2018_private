package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.model.Courses;
import org.alignprivate.asd.model.Experiences;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExperiencesDaoTest {
	private ExperiencesDao experiencesDao;

	@Before
	public void init() {
		experiencesDao = new ExperiencesDao();
	}

//	@Test
//	public void addNullcourseTest() {
//		Courses Courses = coursesDao.createCourse(null);
//		Assert.assertNull(Courses);
//	}
//
//	@Test
//	public void addCourseTest() {
//		Courses newcourse = new Courses("222", "course1", "course description 1");
//		Courses courses = coursesDao.createCourse(newcourse);
//		Assert.assertTrue(courses.toString().equals(newcourse.toString()));
//		coursesDao.deleteCourseById("222");
//	}
//
//	@Test
//	public void deleteCourseTest() {
//		List<Courses> Courses = coursesDao.getAllCourses();
//		int oldSize = Courses.size();		
//		Courses newcourse = new Courses("111", "course name", "desc");		
//
//		coursesDao.createCourse(newcourse);
//		coursesDao.deleteCourseById("111");
//		int newSize = Courses.size();	
//		Assert.assertEquals(oldSize, newSize); 
//	}

	@Test
	public void getAllCoursesTest() {
		List<Experiences> experiences = experiencesDao.getAllExperiences();

		System.out.println(experiences.get(0).getDescription() + ", " + experiences.get(0).getStudent().toString());
		System.out.println(experiences.size());
	}

//	@Test
//	public void deleteNullcourseTest() {
//		boolean result = coursesDao.deleteCourseById(null);
//		Assert.assertFalse(result);
//		
//		result = coursesDao.deleteCourseById("");
//		Assert.assertFalse(result);
//	}
}
