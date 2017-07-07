package ejb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Course;
import data.Material;

/**
 * Session Bean implementation class ProfessorBean
 */
@Stateless
public class ProfessorBean implements ProfessorBeanLocal {



	@EJB
	CRUDMaterialLocal materialejb;

	@EJB
	CRUDCourseLocal courseejb;
	
	@EJB
	CRUDStudentLocal studentejb;

	private static final String MATERIALFOLDER="/home/bata/Uni/uploaded";


	private static final Logger LOGGER = 
			Logger.getLogger(ProfessorBean.class.getName());

	/**
	 * Default constructor. 
	 */
	public ProfessorBean() {

	}
	
	
	@Override
	public boolean addMaterial(String institution_email,String courseName, String name, byte[] data) {
		Course c=courseejb.readOne(courseName);
		
		if(c==null){
			LOGGER.warning("professor "+institution_email+" tried to add materials to a"
					+ " course that doesn't exist.");
			return false;
		}
		
		if(c.getProfessor()==null){
			LOGGER.warning("professor "+institution_email+" tried to add materials to a"
					+ " course he doesn't teach.");
			return false;
		}
		
		if(!c.getProfessor().getInstitution_email().equals(institution_email)){
			LOGGER.warning("professor "+institution_email+" tried to add materials to a"
					+ " course he doesn't teach.");
			return false;
		}
		
		
		String location=MATERIALFOLDER+"/"+courseName+"/"+name;

		System.out.println("Working Directory = " +
				System.getProperty("user.dir"));

		if(courseejb.readOne(courseName)==null){
			LOGGER.warning("material "+name+" couldn't be added to course "+courseName+"."+
					"course doesn't exist.");
			return false;
		}

		if(materialejb.readOne(name, courseName)!=null){
			LOGGER.warning("material "+name+" couldn't be added to course "+courseName+"."+
					"material already exists in course");
			return false;
		}

		FileOutputStream stream=null;
		try{
			Files.createDirectories(Paths.get(MATERIALFOLDER+"/"+courseName));
			File yourFile = new File(location);
			yourFile.createNewFile();
			stream = new FileOutputStream(yourFile); 
			stream.write(data);
			stream.close();
		}
		catch(IOException e){
			LOGGER.severe("failed to store data from material "+name+" in file!");

			if(stream!=null){
				try {
					stream.close();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}

			e.printStackTrace();

			return false;
		}

		materialejb.create(name,location,courseName);


		LOGGER.info("material "+name+" added to course "+courseName+".");
		return true;
	}

	@Override
	public boolean deleteMaterial(String institution_email, String coursename,String name) {
		Course c=courseejb.readOne(coursename);
		
		if(c==null){
			LOGGER.warning("professor "+institution_email+" tried to delete materials from a"
					+ " course that doesn't exist.");
			return false;
		}
		
		if(c.getProfessor()==null){
			LOGGER.warning("professor "+institution_email+" tried to delete materials from a"
					+ " course he doesn't teach.");
			return false;
		}
		
		if(!c.getProfessor().getInstitution_email().equals(institution_email)){
			LOGGER.warning("professor "+institution_email+" tried to delete materials from a"
					+ " course he doesn't teach.");
			return false;
		}
		
		
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
	public ArrayList<String> getStudents(String institution_email,String coursename, char order) {
		Course c=courseejb.readOne(coursename);
		
		if(c==null){
			LOGGER.warning("professor "+institution_email+" tried to retrieve students from a"
					+ " course that doesn't exist.");
			return null;
		}
		
		if(c.getProfessor()==null){
			LOGGER.warning("professor "+institution_email+" tried to retrieve students from a"
					+ " course he doesn't teach.");
			return null;
		}
		
		if(!c.getProfessor().getInstitution_email().equals(institution_email)){
			LOGGER.warning("professor "+institution_email+" tried to retrieve students from a"
					+ " course he doesn't teach.");
			return null;
		}
		
		return studentejb.fromCourse(coursename,order);
	}

	@Override
	public ArrayList<String> getStudentInfo(String name,  String address, String institution_email,
			String alternative_email, String phone, String number) {
		
		//sanitize query first
		if(name==null){
			name="";
		}
		if(address==null){
			address="";
		}
		if(institution_email==null){
			institution_email="";
		}
		if(alternative_email==null){
			alternative_email="";
		}
		if(phone==null){
			phone="";
		}
		if(number==null){
			number="";
		}
		
		return studentejb.searchAll(name,address, institution_email,
				alternative_email,phone,number);
	}

}
