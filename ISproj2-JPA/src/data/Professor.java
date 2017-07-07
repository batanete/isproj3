package data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Professor
 *
 */
@Entity
@NamedQueries({

	@NamedQuery(name="Professor.login",
			query="SELECT s FROM Professor s WHERE s.institution_email = :email "+
			"AND s.password = :password"),
	@NamedQuery(name="Professor.findAll",
	query="SELECT p FROM Professor p"),
	@NamedQuery(name="Professor.findByName",
	query="SELECT p FROM Professor p WHERE p.institution_email = :p"),
}) 
public class Professor extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String internal_number;
	private String category;
	private String office;
	private String internal_phone;
	private double salary;

	
	@OneToMany(mappedBy="professor")
	private List<Course> courses;


	public Professor() {
		super();
	}


	public Professor(String name, Date dateBirth, String address, String institution_email, String alternative_email,
			String phone, String password,
			String internal_number, String category, 
			String office, String internal_phone,
			double salary) {

		super(name, dateBirth, address, institution_email,alternative_email,
				phone, password);
		this.role="professor";
		this.internal_number = internal_number;
		this.category = category;
		this.office = office;
		this.internal_phone = internal_phone;
		this.salary = salary;
	}



	//getters e setters
	public String getInternal_number() {
		return internal_number;
	}


	public void setInternal_number(String internal_number) {
		this.internal_number = internal_number;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getOffice() {
		return office;
	}


	public void setOffice(String office) {
		this.office = office;
	}


	public String getInternal_phone() {
		return internal_phone;
	}


	public void setInternal_phone(String internal_phone) {
		this.internal_phone = internal_phone;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	
	@PreRemove
	private void removeFromCourses() {
	    for (Course c : courses) {
	        c.setProfessor(null);
	    }
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

		case("internal_number"):
			this.setInternal_number(newParam);
		break;
		case("office"):
            this.setOffice(newParam);
         break;
		case("category"):
            this.setCategory(newParam);
         break;
		case("internal_phone"):
            this.setInternal_number(newParam);
         break;
		case("salary"):
            this.setSalary(Double.parseDouble(newParam));
         break;
		}  

		return true;

	}
}
