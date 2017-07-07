package ejb;

import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.enterprise.context.RequestScoped;


@Remote
public interface CreateAdminBeanRemote {

	//creates a new admin account. returns true on success.
	public boolean createAdmin(String name, Date dateBirth, String address, 
			String institution_email, String alternative_email,
			String phone, String password);
	
	//deletes an admin account. returns true on success.
	public boolean deleteAdmin(String institutionalEmail);

}
