package ejb;

import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Remote;



//bean used for general operations

@Local
public interface GeneralBeanLocal {
	
	//attempts to validate a pair of login credentials
	//returns 'a' if account is admin, 's' if student and 'p' if professor.
	public char login(String user,String password);
	
	//returns the names of the materials from a given course.
	public ArrayList<String> getMaterialNames(String course);
	
	//returns the data from a given course or null on error.
	public byte[] getMaterialData(String materialName, String courseName);
	
	
}
