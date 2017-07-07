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
import data.Professor;
import data.Student;
import datarest.CourseREST;
import datarest.ListCoursesREST;
import datarest.ListStudentsREST;
import datarest.MaterialREST;


/**
 * Session Bean implementation class CRUDCourse
 */
@Stateless
public class CRUDCourse implements CRUDCourseLocal {

	@PersistenceContext(name="ISproj2")
	EntityManager em;

	private static final Logger LOGGER = 
			Logger.getLogger(CRUDCourse.class.getName());

	/**
	 * Default constructor. 
	 */
	public CRUDCourse() {

	}

	@Override
	public boolean create(String coursename) {
		//course already exists
		if(readOne(coursename)!=null){
			LOGGER.warning("tried to create already existing course");
			return false;
		}

		try{
			Course c=new Course();
			c.setCourseName(coursename);
			em.persist(c);
		}
		catch(Exception e){
			LOGGER.warning("course "+coursename+" could not be created. Course already exists.");
			return false;
		}

		LOGGER.info("course "+coursename+" created.");
		return true;


	}

	@Override
	public boolean create(String coursename, String professoremail, ArrayList<String> studentemails) {

		//course already exists
		if(readOne(coursename)!=null){
			LOGGER.warning("tried to create already existing course");
			return false;
		}

		Query q=em.createNamedQuery("Professor.findByName",Professor.class);
		Query q2=em.createNamedQuery("Student.findByName",Student.class);

		Professor p;
		ArrayList<Student> students;

		q.setParameter("p",professoremail);
		try{
			p=(Professor)q.getSingleResult();
		}
		catch(NoResultException e){
			LOGGER.warning("course "+coursename+" could not be created. professor "+professoremail+
					" doesn't exist.");
			return false;
		}

		students=new ArrayList<Student>();


		for(String studentemail:studentemails){
			q2.setParameter("s", studentemail);
			try{
				Student s=(Student)q2.getSingleResult();
				students.add(s);
			}
			catch(NoResultException e){
				LOGGER.warning("course "+coursename+" could not be created. student "+studentemail+
						" doesn't exist.");
				return false;
			}
		}



		try{
			Course c=new Course(coursename,p);


			c.setStudents(students);

			em.persist(c);
		}
		catch(Exception e){
			LOGGER.severe("course "+coursename+" could not be created! System error!");
			e.printStackTrace();
			return false;
		}

		LOGGER.info("course "+coursename+" created.");
		return true;
	}



	@Override
	public Course readOne(String name) {
		Course res=null;
		Query q=em.createNamedQuery("Course.findByName",Course.class);
		q.setParameter("c",name);

		try{
			res=(Course)q.getSingleResult();
		}
		catch(NoResultException e){
			LOGGER.warning("tried to obtain inexisting course "+name);
			return null;
		}

		LOGGER.info("obtained course "+name+" from database");
		return res;
	}



	@Override
	public boolean delete(String name) {
		Course c=readOne(name);

		if(c==null){
			LOGGER.warning("tried to delete inexisting course "+name);
			return false;
		}
		else{
			em.remove(c);
			LOGGER.info("course "+name+" deleted.");
			return true;
		}
	}



	@Override
	public boolean addStudent(String coursename, String studentemail) {
		Course c;
		Student s;

		Query q1=em.createNamedQuery("Course.findByName",Course.class);
		q1.setParameter("c",coursename);

		Query q2=em.createNamedQuery("Student.findByName",Student.class);
		q2.setParameter("s", studentemail);

		try{
			c=(Course)q1.getSingleResult();
		}
		catch(NoResultException e)
		{
			LOGGER.warning("couldn't add student "+studentemail+" to course "+coursename+". course doesn't"
					+ "exist.");
			return false;
		}

		try{
			s=(Student)q2.getSingleResult();
		}
		catch(NoResultException e)
		{
			LOGGER.warning("couldn't add student "+studentemail+" to course "+coursename+". student doesn't"
					+ "exist.");
			return false;
		}


		if(c.getStudents().contains(s)){
			LOGGER.warning("couldn't add student "+studentemail+" to course "+coursename+". student already"
					+ " exists in course.");
			return false;
		}

		c.addStudent(s);
		s.getCourses().add(c);

		LOGGER.info("added student "+studentemail+" to course "+coursename);

		return true;
	}



	@Override
	public boolean removeStudent(String coursename, String studentemail) {
		Query q=em.createNamedQuery("Student.findByName",Student.class);
		q.setParameter("s", studentemail);


		Course c;
		Student s;

		c=readOne(coursename);

		if(c==null){
			LOGGER.warning("couldn't remove student "+studentemail+" from course "+
					coursename+". course doesn't exist.");
			return false;
		}
		try{
			s=(Student)q.getSingleResult();
		}
		catch(NoResultException e)
		{
			LOGGER.warning("couldn't remove student "+studentemail+" from course "+coursename+". student doesn't"
					+ "exist.");
			return false;
		}

		if(!c.getStudents().contains(s)){
			LOGGER.warning("couldn't remove student "+studentemail+" from course "+coursename+". student doesn't"
					+ " exist in course.");
			return false;
		}


		c.getStudents().remove(s);
		s.getCourses().remove(c);


		LOGGER.info("removed student "+studentemail+" from course "+coursename);
		return true;
	}



	@Override
	public boolean changeProfessor(String coursename, String newProfessor) {

		Course c=readOne(coursename);
		Professor p;


		if(c==null){
			LOGGER.warning("couldn't change professor on course "+coursename+". course doesn't exist.");
			return false;
		}

		Professor old=c.getProfessor();


		Query q=em.createNamedQuery("Professor.findByName",Professor.class);
		q.setParameter("p", newProfessor);

		try{
			p=(Professor)q.getSingleResult();
		}
		catch(NoResultException e)
		{
			LOGGER.warning("couldn't change professor to "+newProfessor+" on course "
					+coursename+". professor doesn't exist.");
			return false;
		}

		c.setProfessor(p);

		LOGGER.info("professor "+newProfessor+" is the new "+coursename+" professor.");
		return true;
	}



	@Override
	public boolean rename(String coursename, String newname) {
		Course c=readOne(coursename);

		if(c==null){
			LOGGER.warning("couldn't rename course "+coursename+".");
			return false;
		}

		Course c2=readOne(newname);

		if(c2!=null){
			LOGGER.warning("couldn't rename course "+coursename+".course with name "+newname+" exists.");
			return false;
		}

		c.setCourseName(newname);

		LOGGER.info("renamed course "+coursename+" with name "+newname);
		return true;
	}

	@Override
	public ListCoursesREST getCoursesREST() {
		Query q=em.createNamedQuery("Course.findAll",Course.class);

		@SuppressWarnings("unchecked")
		List<Course> cs=q.getResultList();

		ListCoursesREST res=new ListCoursesREST();
		for(Course c:cs){
			CourseREST c1=new CourseREST();
			c1.setId(c.getId());
			c1.setName(c.getName());

			for(Material m:c.getMaterials()){
				MaterialREST m1=new MaterialREST();
				m1.setTitle(m.getName());
				m1.setLocation(m.getLocation());

				c1.addMaterial(m1);
			}


			res.addCourse(c1);
		}

		return res;
	}


}
