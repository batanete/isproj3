package data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Person
 *
 */
@MappedSuperclass
public abstract class Person implements Serializable {

	private static  final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="role")
	protected String role;
	
	@Column(name="name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_birth")
	private Date dateBirth;
	
	private String address;
	
	@Column(name="institution_email",unique=true)
	private String institution_email;
	
	@Column(name="alternative_email")
	private String alternative_email;
	
	private String phone;
	private String password;
	
	public Person(){
		super();
	}
	
	public Person(String name, Date dateBirth, String address, String institution_email, String alternative_email,
			String phone, String password) {
		super();
		this.name=name;
		this.dateBirth = dateBirth;
		this.address = address;
		this.institution_email = institution_email;
		this.alternative_email = alternative_email;
		this.phone = phone;
		this.password = password;
	}
	
	public abstract boolean editPerson(String param, String newParam);
	
	
	//getters and setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInstitution_email() {
		return institution_email;
	}
	public void setInstitution_email(String institution_email) {
		this.institution_email = institution_email;
	}
	public String getAlternative_email() {
		return alternative_email;
	}
	public void setAlternative_email(String alternative_email) {
		this.alternative_email = alternative_email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	public int compareTo(Person other){
		return this.name.compareTo(other.getName());
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", role=" + role 
				+ ", name=" + name + ", dateBirth=" + dateBirth + ", address="
				+ address + ", institution_email=" + institution_email + ", alternative_email=" + alternative_email
				+ ", phone=" + phone + ", password=" + password + "]";
	}
}
