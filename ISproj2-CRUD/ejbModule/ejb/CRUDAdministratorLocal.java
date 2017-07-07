package ejb;

import javax.ejb.Local;

import data.Administrator;

@Local
public interface CRUDAdministratorLocal {
	
	//returns the admin with the given email, or null if it doesn't exist
	public Administrator readOne(String institutionalEmail);

	//updates the given field of the given admin with the given new info
	//returns true on success
	public boolean update(String institutionalEmail, String fieldName, String newInfo);

	//deletes an admin with the given email
	//returns true on success
	public boolean delete(String institutional_email);

	//returns whether there is an admin with the given user/password.
	public boolean validate(String user, String password);
	
	
	
}
