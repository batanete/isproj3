package ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Administrator;

/**
 * Session Bean implementation class CreateAdmin
 */
@Stateless
public class CreateAdminBean implements CreateAdminBeanRemote {

	private static final Logger LOGGER = 
			Logger.getLogger(CreateAdminBean.class.getName());

	@PersistenceContext(name="ISproj2")
	EntityManager em;

	/**
	 * Default constructor. 
	 */
	public CreateAdminBean() {
		// TODO Auto-generated constructor stub
	}

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
	public boolean createAdmin(String name, Date dateBirth, String address, String institution_email,
			String alternative_email, String phone, String password) {

		password=SHAsum(password);
		
		Query q=em.createQuery( "SELECT COUNT(*) "+ "FROM Administrator WHERE institution_email= :a");
		q.setParameter("a",institution_email);

		if((Long)q.getSingleResult()==0){
			em.persist(new Administrator(name, dateBirth, address, institution_email, alternative_email, phone, password));
			LOGGER.info("account "+institution_email+" created.");
			return true;
		}else{
			LOGGER.warning("could not create account "+institution_email);
			return false;

		}
	}


	@Override
	public boolean deleteAdmin(String institutionalEmail) {
		Query q=em.createQuery( "from Administrator WHERE institution_email= :a");
		q.setParameter("a",institutionalEmail);
		LOGGER.info("admin to delete:"+institutionalEmail);
		try{
			Administrator admin=(Administrator)q.getSingleResult();

			em.remove(admin);


			LOGGER.info("admin "+institutionalEmail+" deleted.");
			return true;
		}
		catch(NoResultException e){
			LOGGER.warning("could not delete account "+institutionalEmail+". account doesn't exist.");
			return false;
		}

	}


}
