package ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface ProfessorBeanLocal {
	
	//adds a material to a given course.
	//returns true on success and false on fail.
	public boolean addMaterial(String institutional_email,String courseName,String name,byte[] data);
	
	//returns the list of students from a given course.
	//returns an empty list if course doesn't exist.
	public ArrayList<String> getStudents(String institutional_email,String coursename,char order);
	
	//deletes a material from a given course, but only if the professor teaches that course.
	//returns true on success and false on fail.
	public boolean deleteMaterial(String institutional_email,String coursename,String name);

	//returns the list of students that meet the given criteria.
	ArrayList<String> getStudentInfo(String name, String address, String institution_email, String alternative_email,
			String phone, String number);
	
}
