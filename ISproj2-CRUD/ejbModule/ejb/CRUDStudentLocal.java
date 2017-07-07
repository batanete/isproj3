package ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import data.Student;
import datarest.ListStudentsREST;

@Local
public interface CRUDStudentLocal {
	
	
	//returns the student with the given email, or null if it doesn't exist
	public Student readOne(String institutionalEmail);

	//updates the given field of the given student with the given new info
	//returns true on success
	public boolean update(String institutionalEmail, String fieldName, String newInfo);

	//creates a new student with the given new info
	//returns true on success
	public boolean create(String name, Date dateBirth, String address, String institution_email, String alternative_email,
			String phone, String password, String number, int enrolmentYear);
	
	//deletes a student with the given email
	//returns true on success
	public boolean delete(String institutional_email);
	
	//returns the info of a list of student that meet the given criteria.
	//for each string provided, only students which contain that string on the field are included
	public ArrayList<String> searchAll(String name,  String address, String institution_email,
			String alternative_email, String phone, String number);
	
	//returns students from the given course in the given order('a' or 'd')
	//returns empty list if course doesn't exist
	public ArrayList<String> fromCourse(String coursename, char order);

	//returns whether there is a student with the given user/password.
	public boolean validate(String user, String password);
	
	//returns a formatted list of all the students and their courses
	public ListStudentsREST getStudentsREST(int courseid);
	
	
}
