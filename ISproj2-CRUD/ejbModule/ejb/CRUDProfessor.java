package ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Professor;
import data.Student;

/**
 * Session Bean implementation class CRUDProfessor
 */
@Stateless
public class CRUDProfessor implements CRUDProfessorLocal {

	@PersistenceContext(name="ISproj2")
	EntityManager em;


	private static final Logger LOGGER = 
			Logger.getLogger(CRUDProfessor.class.getName());

	/**
	 * Default constructor. 
	 */
	public CRUDProfessor() {
		// TODO Auto-generated constructor stub
	}

	public boolean create(String name, Date dateBirth, String address, String institution_email,
			String alternative_email, String phone, String password, String internal_number, String category,
			String office, String internal_phone, double salary){

		Query q=em.createQuery( "SELECT COUNT(*) FROM Professor WHERE institution_email= :p");
		q.setParameter("p",institution_email);


		if((Long)q.getSingleResult()<1){
			em.persist(new Professor(name, dateBirth, address, institution_email,
					alternative_email, phone, password,internal_number, category, office, internal_phone,salary));
			LOGGER.info("professor "+institution_email+" added to database.");
			return true;
		}else{
			LOGGER.warning("professor "+institution_email+" couldn't be added to database.already exists.");
			return false;

		}

	}


	@Override
	public Professor readOne(String institutional_email) {
		Query q=em.createNamedQuery("Professor.findByName",Professor.class);
		q.setParameter("p", institutional_email);

		Professor res;

		try{
			res=(Professor)q.getSingleResult();
		}
		catch(NoResultException e){
			LOGGER.warning("tried to obtain inexisting professor "+institutional_email);
			return null;
		}

		LOGGER.info("obtained professor "+institutional_email+" from database");
		return res;
	}

	@Override
	public boolean update(String institutional_email,String fieldname, String newinfo) {

		Professor p=readOne(institutional_email);

		if(p==null){
			LOGGER.warning("couldn't update professor "+institutional_email);
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

		Professor p=readOne(institutional_email);

		if(p==null){
			LOGGER.warning("couldn't delete professor "+institutional_email);
			return false;
		}
		else{
			em.remove(p);

			LOGGER.info("professor "+institutional_email+" deleted.");
			return true;
		}
	}

	@Override
	public boolean validate(String user, String password) {
		Query q=em.createNamedQuery("Professor.login",Professor.class);
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
