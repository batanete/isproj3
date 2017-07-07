package datarest;


import java.io.Serializable;

import java.util.Date;




public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	private String alternativeEmail;
	private String telephone;
	private String password;
	private String address;
	private Date birthdate;
	private String studentNumber;
	private int yearEnrolled;


	public Student() {}
	
	public Student(String name, Date birthdate, String email, String password, String alternativeEmail, String address, String telephone, String number, int year) {
		this.name = name;
		this.email = email;
		this.birthdate = birthdate;
		this.alternativeEmail = alternativeEmail;
		this.telephone = telephone;
		this.password = password;
		this.address = address;
		this.studentNumber = number;
		this.yearEnrolled = year;
	}
	
	


	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public int getYearEnrolled() {
		return yearEnrolled;
	}

	public void setYearEnrolled(int yearEnrolled) {
		this.yearEnrolled = yearEnrolled;
	}
	




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlternativeEmail() {
		return alternativeEmail;
	}

	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}	
   
}
