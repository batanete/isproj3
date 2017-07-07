package ejb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.*;

/**
 * Session Bean implementation class LoginBean
 */
@Stateless
public class GeneralBean implements GeneralBeanLocal {

	private static final Logger LOGGER = 
			Logger.getLogger(GeneralBean.class.getName());
	
	@EJB
	CRUDAdministratorLocal adminejb;
	
	@EJB
	CRUDProfessorLocal professorejb;
	
	@EJB
	CRUDStudentLocal studentejb;
	
	@EJB
	private CRUDMaterialLocal materialejb;
	
	//encrypts a string using sha-1
	private static String SHAsum(String s){
	    byte[] convertme=s.getBytes();
		
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	    
	    Formatter formatter = new Formatter();
	    for (byte b :convertme) {
	        formatter.format("%02x", b);
	    }
	    
	    String res=formatter.toString();
	    
	    formatter.close();
	    return res;
	    
	}

	//returns 'p' if account is of type professor,
	//'s' if student, 
	//'a' if admin
	//and '0' if credentials are wrong.
	//TODO: HASH PASSWORDS BEFORE STORING AND CHECKING THEM
	public char login(String user,String password) {
		password=SHAsum(password);
		
		if(adminejb.validate(user,password)){
			LOGGER.info("admin "+user+" logged in.");
			return 'a';
		}
		else if(professorejb.validate(user,password)){
			LOGGER.info("professor "+user+" logged in.");
			return 'p';
		}
		
		else if(studentejb.validate(user,password)){
			LOGGER.info("student "+user+" logged in.");
			return 's';
		}
		
		
		else{
			LOGGER.warning("login failed.");
			return '0';
		}
	}



	/**
	 * Default constructor. 
	 */
	public GeneralBean() {}

	@Override
	public ArrayList<String> getMaterialNames(String coursename) {
		return materialejb.getNames(coursename);
	}

	@Override
	public byte[] getMaterialData(String materialName,String courseName) {
		Material m=materialejb.readOne(materialName, courseName);
		if(m==null){
			LOGGER.warning("tried to download inexisting material "+materialName);
			return null;
		}
		String location=m.getLocation();
		byte[] res=null;
		try {
			Path path = Paths.get(location);
			res=Files.readAllBytes(path);
			
			
		} catch (IOException e) {
			LOGGER.severe("error reading file!");
			e.printStackTrace();
			return null;
		} 
		LOGGER.info("material "+materialName+" was downloaded.");
		return res;
	}

}
