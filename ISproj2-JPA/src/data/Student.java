package data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Student
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Student.login",
			query="SELECT s FROM Student s WHERE s.institution_email = :email "+
			"AND s.password = :password"),
	@NamedQuery(name="Student.findAll",
	query="SELECT s FROM Student s"),
	@NamedQuery(name="Student.findByName",
	query="SELECT s FROM Student s WHERE s.institution_email = :s"),
	@NamedQuery(name="Student.search",
	query="SELECT s FROM Student s WHERE s.institution_email LIKE :institution_email "
			+ "AND s.name LIKE :name " 
			+ "AND s.number LIKE :number " 
			+ "AND s.address LIKE :address "
			+ "AND s.alternative_email LIKE :alternative_email "
			+ "AND s.phone LIKE :phone "
			)
}) 
public class Student extends Person implements Serializable {
	private static final long serialVersionUID = 1L;

	public Student() {
		super();
	}


	@ManyToMany(mappedBy="students")
	private List<Course> courses;

	private String number;
	private int enrolmentYear;

	public Student(String name, Date dateBirth, String address, String institution_email, String alternative_email,
			String phone, String password,String number,int enrolmentYear){
		super(name, dateBirth, address, institution_email,alternative_email,
				phone, password);
		this.role="student";
		this.enrolmentYear=enrolmentYear;
		this.number=number;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getEnrolmentYear() {
		return enrolmentYear;
	}

	public void setEnrolmentYear(int enrolmentYear) {
		this.enrolmentYear = enrolmentYear;
	}

	
	@PreRemove
	private void removeFromCourses() {
	    for (Course c : courses) {
	        c.getStudents().remove(this);
	    }
	}
	
	@Override
	public String toString() {
		String res="";

		res+="Name:"+this.getName()+";";
		res+="Institutional email:"+this.getInstitution_email()+"<br>";
		res+="Alternative email:"+this.getAlternative_email()+"<br>";
		res+="Number:"+this.getNumber()+"<br>";
		res+="Enrolment year:"+this.getEnrolmentYear()+"<br>";
		res+="Name:"+this.getName()+"<br>";
		res+="Address:"+this.getAddress()+"<br>";
		res+="Date Of Birth:"+this.getDateBirth()+"<br>";

		return res;
	}

	@Override
	public boolean editPerson(String param, String newParam) {
		switch(param){
		case("address"):
			this.setAddress(newParam);
		break;
		case("alternative_email"):
			this.setAlternative_email(newParam);
		break;
		case("institutional_email"):
			this.setInstitution_email(newParam);
		break;

		case("name"):
			this.setName(newParam);
		break;

		case("date_birth"):
			try {
				this.setDateBirth(new SimpleDateFormat("dd/MM/yyyy").parse(newParam));
			} catch (ParseException e) {
				return false;
			}
		break;

		case("role"):
			this.setRole(newParam);
		break;

		case("phone"):
			this.setPhone(newParam);
		break;


		case("password"):
			this.setPassword(newParam);
		break;

		case("enrolment_year"):
			this.setEnrolmentYear(Integer.parseInt(newParam));
		break;

		case("number"):
			this.setNumber(newParam);
		break;
		default:
			return false;

		}  
		return true;
	}
}
