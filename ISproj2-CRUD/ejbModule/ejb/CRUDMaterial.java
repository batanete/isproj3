package ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import data.Course;
import data.Material;

/**
 * Session Bean implementation class CRUDMaterial
 */
@Stateless
public class CRUDMaterial implements CRUDMaterialLocal {

	@PersistenceContext(name="ISproj2")
	EntityManager em;

	private static final Logger LOGGER = 
			Logger.getLogger(CRUDMaterial.class.getName());

	/**
	 * Default constructor. 
	 */
	public CRUDMaterial() {}

	@SuppressWarnings("unchecked")
	@Override
	public Material readOne(String name, String coursename) {
		Query q=em.createNamedQuery("Material.findByName");
		q.setParameter("m", name);

		List<Material> result;

		try{
			result=(List<Material>)q.getResultList();
		}
		catch(NoResultException e){
			LOGGER.warning("tried to obtain inexisting material "+name);
			return null;
		}

		for(Material m:result){
			if(m.getCourse().getName().equals(coursename)){
				LOGGER.info("retrieved material "+name+" from course "+coursename);
				return m;
			}
		}

		LOGGER.warning("tried to obtain inexisting material "+name+"on course "+coursename);
		return null;

	}

	@Override
	public boolean create(String name, String location, String courseName) {
		Course c=null;
		Query q=em.createNamedQuery("Course.findByName",Course.class);
		q.setParameter("c",courseName);

		try{
			c=(Course)q.getSingleResult();
		}
		catch(NoResultException e){
			LOGGER.warning("failed to add material "+name+" to "
					+ "course "+courseName+". course doesn't exist.");
			return false;
		}


		Material m=new Material(name,location,c);
		
		em.persist(m);

		LOGGER.info("material "+name+" added to course "+courseName);
		return true;
	}

	@Override
	public boolean delete(String coursename,String name) {

		Material m=readOne(name,coursename);

		if(m==null){
			LOGGER.warning("could not delete material "+name+" from course "+ coursename+
					". material or course doesn't exist.");
			return false;
		}

		else{
			em.remove(m);

			return true;
		}


	}

	@Override
	public ArrayList<String> getNames(String coursename) {
		Query qq=em.createNamedQuery("Course.findByName",Course.class);
		qq.setParameter("c", coursename);
		Course c=null;
		try{
			c=(Course)qq.getSingleResult();
		}
		catch(NoResultException e){
			LOGGER.warning("couldn't obtain materials from course "+coursename+".course doesn't exist.");
			return new ArrayList<String>();
		}
		ArrayList<String> names=new ArrayList<String>();
		
		for(Material m:c.getMaterials()){
			names.add(m.toString());
		}
		
		return names;
	}

}
