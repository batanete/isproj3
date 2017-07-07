package ejb;

import java.util.ArrayList;

import javax.ejb.Local;

import data.Material;

@Local
public interface CRUDMaterialLocal {
	
	//returns the material with the given name and course, or null if it doesn't exist
	public Material readOne(String name,String coursename);
	
	//creates a new material, associated with the given course.
	//returns true on success
	public boolean create(String name, String location, String courseName);

	//deletes the material with the given name and course
	//returns true on success
	public boolean delete(String coursename,String name);

	//returns the names of the materials of a course
	public ArrayList<String> getNames(String coursename);

}
