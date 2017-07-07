package ejb;

import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Remote;

import data.Course;
import datarest.ListCoursesREST;
import datarest.ListStudentsREST;
import datarest.ListCoursesREST;

@Local
public interface CRUDCourseLocal {

	//creates a new course, with no professor or student associated
	//returns true on success
	public boolean create(String coursename);
	
	//creates a new course, with a professor and list of students associated
	//returns true on success
	public boolean create(String coursename, String professoremail, ArrayList<String> studentemails);
	
	//returns the course with the given name, or null if it doesn't exist
	public Course readOne(String name);

	//deletes the course with the given name
	//returns true on success
	public boolean delete(String name);

	//adds a student to a course
	//returns true on success
	public boolean addStudent(String coursename, String studentemail);

	//removes a student from a course
	//returns true on success
	public boolean removeStudent(String coursename, String studentemail);
	
	//changes the professor from a course
	//returns true on success
	public boolean changeProfessor(String coursename, String newProfessor);
	
	//renames a course
	//returns true on success
	public boolean rename(String coursename, String newname);

	//returns a formatted list of all the materials of each course in a POJO
	public ListCoursesREST getCoursesREST();
	
	
	
	
}
