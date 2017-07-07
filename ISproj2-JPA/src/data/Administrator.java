package data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrator
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Administrator.login",
			query="SELECT s FROM Administrator s WHERE s.institution_email = :email "+
			"AND s.password = :password"),
    @NamedQuery(name="Administrator.findAll",
                query="SELECT a FROM Administrator a"),
    @NamedQuery(name="Administrator.findByName",
                query="SELECT a FROM Administrator a WHERE a.institution_email = :a"),
}) 
@Access(value=AccessType.PROPERTY)
public class Administrator extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	public Administrator() {
		super();
	}
	
	public Administrator(String name, Date dateBirth, String address, String institution_email, String alternative_email,
			String phone, String password){
		super(name, dateBirth, address, institution_email,alternative_email,
			phone, password);
		this.role="administrator";
	}
	
	@Override
	public boolean editPerson(String param, String newParam){
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
        }   
		
		return true;
		
	}
	
}
