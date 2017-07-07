package ejb;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Local;

import data.Professor;

@Local
public interface CRUDProfessorLocal {

	//updates the given field of the given student with the given new info
	//returns true on success
	public boolean create(String name, Date dateBirth, String address, String institution_email,
			String alternative_email, String phone, String password, String internal_number, String category,
			String office, String internal_phone, double salary);

	//returns the professor with the given email, or null if it doesn't exist
	public Professor readOne(String institutional_email);

	//updates the given field of the given professor with the given new info
	//returns true on success
	public boolean update(String institutional_email,String fieldname,String newinfo);

	//deletes a professor with the given email
	//returns true on success
	public boolean delete(String institutional_email);

	//returns whether there is a professor with the given user/password.
	public boolean validate(String user, String password);

}
