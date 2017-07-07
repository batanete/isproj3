package ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import data.Administrator;
import data.Course;
import data.Material;
import data.Professor;
import data.Student;
import datarest.CourseREST;
import datarest.ListCoursesREST;
import datarest.ListStudentsREST;
import datarest.MaterialREST;
import datarest.StudentREST;

/**
 * Session Bean implementation class CRUDStudent
 */
@Stateless
public class CRUDStudent implements CRUDStudentLocal {

	@PersistenceContext(name="ISproj2")
	EntityManager em;
	
	private static final Logger LOGGER = 
			Logger.getLogger(CRUDStudent.class.getName());
	
    /**
     * Default constructor. 
     */
    public CRUDStudent() {}
    
    public boolean create(String name, Date dateBirth, String address, String institution_email,
			String alternative_email, String phone, String password, String number, int enrolmentYear){
    	
    	Query q=em.createQuery( "SELECT COUNT(*) FROM Student WHERE institution_email= :p");
		q.setParameter("p",institution_email);


		if((Long)q.getSingleResult()<1){
			em.persist(new Student(name, dateBirth, address,
					institution_email, alternative_email, phone, password,  number, enrolmentYear));
			LOGGER.info("student "+institution_email+" added to database.");
			return true;
		}else{
			LOGGER.warning("student "+institution_email+" couldn't be added to database.already exists.");
			return false;
		}
		
    }

	@Override
	public Student readOne(String institutionalEmail) {
		Query q=em.createNamedQuery("Student.findByName",Student.class);
		q.setParameter("s", institutionalEmail);

		Student res;

		try{
			res=(Student)q.getSingleResult();
		}
		catch(NoResultException e){
			LOGGER.warning("tried to obtain inexisting student "+institutionalEmail);
			return null;
		}

		LOGGER.info("obtained student "+institutionalEmail+" from database");
		return res;
	}

	@Override
	public boolean update(String institutionalEmail, String fieldName, String newInfo) {
		Student p=readOne(institutionalEmail);

		if(p==null){
			LOGGER.warning("couldn't update student "+institutionalEmail+". student doesn't exist.");
			return false;
		}
		if(p.editPerson(fieldName, newInfo)){
			LOGGER.info("Student "+institutionalEmail+" edited.");
			return true;
		}
		else{
			LOGGER.info("couldn't edit field "+fieldName+" on student "+institutionalEmail);
			return false;
		}
	}

	@Override
	public boolean delete(String institutional_email) {
		Student s=readOne(institutional_email);

		if(s==null){
			LOGGER.warning("couldn't delete student "+institutional_email);
			return false;
		}
		else{
			em.remove(s);

			LOGGER.info("student "+institutional_email+" deleted.");
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> searchAll(String name,  String address, String institution_email,
			String alternative_email, String phone, String number) {
		ArrayList<String> res=new ArrayList<String>();
		
		List<Student> students;
		
		Query q=em.createNamedQuery("Student.search",Student.class);
		
		q.setParameter("name", "%"+name+"%");
		q.setParameter("address", "%"+address+"%");
		q.setParameter("institution_email", "%"+institution_email+"%");
		q.setParameter("alternative_email", "%"+alternative_email+"%");
		q.setParameter("phone", "%"+phone+"%");
		q.setParameter("number", "%"+number+"%");
		
		students=(List<Student>)q.getResultList();
		
		for(Student s:students){
			res.add(s.toString());
		}
		
		LOGGER.info("obtained list of students with a criteria.");
		return res;
	}

	@Override
	public ArrayList<String> fromCourse(String coursename, char order) {
		ArrayList<String> res;
		List<Student> students;
		
		Query q=em.createNamedQuery("Course.findByName",Course.class);
		q.setParameter("c", coursename);
		
		Course c=null;
		try{
			c=(Course)q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
		students=c.getStudents();
		
		res=new ArrayList<String>();
		
		for(Student s:students){
			res.add(s.getName());
		}
		
		
		
		if(order=='d'){
			Comparator<String> comparator = Collections.reverseOrder();
			Collections.sort(res,comparator);
		}
		else if(order=='a'){
			Collections.sort(res);
		}
		else{
			LOGGER.severe("tried to obtain students in non existing order "+order);
			return null;
		}
		
		LOGGER.info("retrieved students from course "+coursename);
		return res;
	}

	@Override
	public boolean validate(String user, String password) {
		Query q=em.createNamedQuery("Student.login",Student.class);
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

	@Override
	public ListStudentsREST getStudentsREST(int courseid) {
		
		Course c=em.find(Course.class,courseid);
		
		System.out.println("c:"+c);
		
		ListStudentsREST res=new ListStudentsREST();
		
		if(c==null)
			return res;
		
		for(Student s:c.getStudents()){
			StudentREST s1=new StudentREST();
			s1.setAddress(s.getAddress());
			s1.setAlternativeEmail(s.getAlternative_email());
			s1.setBirthdate(s.getDateBirth());
			s1.setEmail(s.getInstitution_email());
			s1.setId(s.getId());
			s1.setName(s.getName());
			s1.setPassword(s.getPassword());
			s1.setStudentNumber(s.getNumber());
			s1.setTelephone(s.getPhone());
			s1.setYearEnrolled(s.getEnrolmentYear());

			res.addStudent(s1);
		}

		return res;
	}
	
	
}
