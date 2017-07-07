package ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Administrator;
import data.Professor;
import data.Student;

/**
 * Session Bean implementation class CRUDAdministrator
 */
@Stateless
public class CRUDAdministrator implements CRUDAdministratorLocal {

	@PersistenceContext(name="ISproj2")
	EntityManager em;
	
	private static final Logger LOGGER = 
			Logger.getLogger(CRUDAdministrator.class.getName());
	
    /**
     * Default constructor. 
     */
    public CRUDAdministrator(){}

    @Override
	public Administrator readOne(String institutional_email) {
		Query q=em.createNamedQuery("Administrator.findByName",Administrator.class);
		q.setParameter("a", institutional_email);

		Administrator res;

		try{
			res=(Administrator)q.getSingleResult();
		}
		catch(NoResultException e){
			LOGGER.warning("tried to obtain inexisting admin "+institutional_email);
			return null;
		}

		LOGGER.info("obtained admin "+institutional_email+" from database");
		return res;
	}

	@Override
	public boolean update(String institutional_email,String fieldname, String newinfo) {

		Administrator p=readOne(institutional_email);

		if(p==null){
			LOGGER.warning("couldn't update Administrator "+institutional_email);
			return false;
		}
		if(p.editPerson(fieldname, newinfo)){
			LOGGER.info("Student "+institutional_email+" edited.");
			return true;
		}
		else{
			LOGGER.info("couldn't edit field "+fieldname+" on student "+institutional_email);
			return false;
		}
	}

	@Override
	public boolean delete(String institutional_email) {

		Administrator p=readOne(institutional_email);

		if(p==null){
			LOGGER.warning("couldn't delete Administrator "+institutional_email);
			return false;
		}
		else{
			em.remove(p);

			LOGGER.info("Administrator "+institutional_email+" deleted.");
			return true;
		}
	}

	@Override
	public boolean validate(String user, String password) {
		Query q=em.createNamedQuery("Administrator.login",Administrator.class);
		q.setParameter("email", user);
		q.setParameter("password", password);
		
		try{
			q.getSingleResult();
			return true;
		}
		catch(NoResultException e){
			return false;
		}
	}

}
