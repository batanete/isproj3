package ejb;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Remote;

//bean used for admin operations
@Local
public interface AdminBeanLocal {
	
	//creates a new professor, given his user credentials and other necessary information.
	//returns true on success and false on fail.
	public boolean createProfessor(String name, Date dateBirth, 
			String address, String institution_email, 
			String alternative_email,
			String phone, String password,
			String internal_number, String category, 
			String office, String internal_phone,
			double salary);
	
	//creates a new student, given his user credentials and other necessary information.
	//returns true on success and false on fail.
	public boolean createStudent(String name, Date dateBirth, 
			String address, String institution_email, 
			String alternative_email,
			String phone, String password,String number,
			int enrolmentYear);
	
	//changes a field on a user's profile
	//returns true on success and false on fail.
	public boolean changeInfo(String institutionalEmail,String fieldName,
			String newinfo);
	
	//creates a new course, without any associated students or professor.
	//returns true on success and false on fail.
	public boolean createCourse(String coursename);
	
	//creates a new course, with a given list of students and a professor
	//returns true on success and false on fail.
	public boolean createCourse(String coursename,String professoremail,ArrayList<String> studentemails);
	
	//adds a student to a course
	//returns true on success and false on fail.
	public boolean addStudentToCourse(String coursename,String studentemail);
	
	//changes a course's name.
	//returns true on success and false on fail.
	public boolean renameCourse(String coursename,String newname);
	
	//removes a student from a course.
	//returns true on success and false on fail.
	public boolean removeStudentFromCourse(String coursename,String studentemail);
	
	//changes a course's professor.
	//returns true on success and false on fail.
	public boolean changeCourseProfessor(String coursename,String newProfessor);
	
	//deletes a course from the system.
	//returns true on success and false on fail.
	public boolean deleteCourse(String name);
	
	//deletes a student or professor from the system.
	//returns true on success and false on fail.
	public boolean deletePerson(String intitutionalEmail);
	
	//deletes a given material from a given course from the system.
	//returns true on success and false on fail.
	public boolean deleteMaterial(String coursename,String name);
}
