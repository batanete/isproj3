package ejb;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;


import data.Administrator;
import data.Professor;
import data.Material;
import data.Student;
import ejb.CRUDProfessorLocal;
import ejb.CRUDStudentLocal;
import ejb.CRUDMaterialLocal;
import ejb.CRUDCourseLocal;


/**
 * Session Bean implementation class AdminBean
 */
@Stateless
public class AdminBean implements AdminBeanLocal {

	private static final Logger LOGGER = 
			Logger.getLogger(AdminBean.class.getName());

	@Inject
	CRUDProfessorLocal profejb;

	@Inject
	CRUDStudentLocal studentejb;

	@Inject
	CRUDAdministratorLocal adminejb;

	@Inject
	CRUDMaterialLocal materialejb;

	@Inject
	CRUDCourseLocal courseejb;


	/**
	 * Default constructor. 
	 */
	public AdminBean() {}
	
	//encrypts a string using sha-1
	private static String SHAsum(String s){
	    byte[] convertme=s.getBytes();
		
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	    
	    Formatter formatter = new Formatter();
	    for (byte b :convertme) {
	        formatter.format("%02x", b);
	    }
	    
	    String res=formatter.toString();
	    
	    formatter.close();
	    return res;
	    
	}

	@Override
	public boolean createProfessor(String name, Date dateBirth, String address, String institution_email,
			String alternative_email, String phone, String password, String internal_number, String category,
			String office, String internal_phone, double salary) {
		password=SHAsum(password);
		
		
		if(profejb.create(name,dateBirth,address,institution_email,
				alternative_email, phone, password, internal_number, category,
				office, internal_phone, salary)){
			LOGGER.info("professor "+institution_email+" created");
			return true;
		}
		else{
			LOGGER.info("professor "+institution_email+" not created. maybe it already exists.");
			return false;
		}
	}

	@Override
	public boolean createStudent(String name, Date dateBirth, String address, String institution_email,
			String alternative_email, String phone, String password, String number, int enrolmentYear) {
		
		password=SHAsum(password);
		
		if(studentejb.create(name,dateBirth,address,institution_email,
				alternative_email, phone, password, number, enrolmentYear)){
			LOGGER.info("student "+institution_email+" created");
			return true;
		}
		else{
			LOGGER.info("student "+institution_email+" not created. maybe it already exists.");
			return false;
		}

	}


	@Override
	public boolean changeInfo(String institutionalEmail, String fieldName, String newInfo) {

		
		Professor p;
		Student s;
		Administrator a;

		p=profejb.readOne(institutionalEmail);
		
		if(fieldName.equals("password")){
			newInfo=SHAsum(newInfo);
		}

		//is professor
		if(p!=null){
			if(profejb.update(institutionalEmail,fieldName,newInfo)){
				LOGGER.info("info changed successfully");
				return true;
			}
			else{
				LOGGER.severe("info not changed successfully even though professor exists!");
				return false;
			}
		}

		//is student
		s=studentejb.readOne(institutionalEmail);
		if(s!=null){
			if(studentejb.update(institutionalEmail,fieldName,newInfo)){
				LOGGER.info("info changed successfully");
				return true;
			}
			else{
				LOGGER.severe("info not changed successfully even though student exists!");
				return false;
			}
		}

		//is admin
		a=adminejb.readOne(institutionalEmail);
		if(a!=null){
			if(adminejb.update(institutionalEmail,fieldName,newInfo)){
				LOGGER.info("info changed successfully");
				return true;
			}
			else{
				LOGGER.severe("info not changed successfully even though admin exists!");
				return false;
			}
		}

		LOGGER.warning("Error editing, email doesn't exist.");
		return false;
	}

	
	@Override
	public boolean createCourse(String coursename) {
		boolean res=courseejb.create(coursename);

		return res;
	}
	

	//creates a new course with a given professor and list of students(transactional operation)
	//returns true on success
	//note that ejbs are transactional by nature using JTA.
	@Override
	public boolean createCourse(String coursename, String professoremail, ArrayList<String> studentemails)  {
			boolean res=courseejb.create(coursename,professoremail,studentemails);

			return res;
	}

	@Override
	public boolean addStudentToCourse(String coursename, String studentemail) {
		
		return courseejb.addStudent(coursename,studentemail);
		
	}

	@Override
	public boolean removeStudentFromCourse(String coursename, String studentemail) {
		return courseejb.removeStudent(coursename,studentemail);
	}

	@Override
	public boolean changeCourseProfessor(String coursename, String newProfessor) {
		return courseejb.changeProfessor(coursename,newProfessor);
	}

	@Override
	public boolean deleteCourse(String name) {
		return courseejb.delete(name);
	}

	@Override
	public boolean deletePerson(String institutionalEmail) {
		return (profejb.delete(institutionalEmail)||studentejb.delete(institutionalEmail));
		
	}

	@Override
	public boolean deleteMaterial(String coursename,String name) {
		Material m=materialejb.readOne(name, coursename);
		
		if(m==null){
			LOGGER.warning("could not delete inexisting material "+name);
			return false;
		}
		String path=m.getLocation();
		
		//delete it from DB first
		if(!materialejb.delete(coursename,name)){
			return false;
		}
		
		//then delete the file
		try {
			File fileToDelete = new File(path);
		    fileToDelete.delete();
		}
		catch(Exception e){
			LOGGER.severe("failed to delete file "+name+"!");
			return false;
		}
		
		
		return true;
	}

	@Override
	public boolean renameCourse(String coursename, String newname) {
		return courseejb.rename(coursename,newname);
	}

	





}
