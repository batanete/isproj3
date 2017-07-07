
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.CreateAdminBeanRemote;;

public class Main {
	/**
	 * @param args
	 * @throws NamingException 
	 * @throws ParseException 
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args)  {

		Scanner sc=new Scanner(System.in);

		//usage: -d for deletion and -c for creation of admin
		String operation=args[0];
		InitialContext ic=null;

		CreateAdminBeanRemote bean=null;

		try{
			ic=new InitialContext();

			bean = (CreateAdminBeanRemote) 
					ic.lookup("ISproj2-EAR/ISproj2-EJB/CreateAdminBean!ejb.CreateAdminBeanRemote");

		}
		catch(NamingException e){
			System.out.println("couldn't connect to server. try again later.");
			System.exit(1);
		}

		//create
		if(operation.equals("-c")){

			System.out.println("name:");
			String name=sc.nextLine();

			
			Date dob=null;
			
			//ask for new date until we get a correct one.
			while(true){
				System.out.println("date of birth:");
				String birthdateStr=sc.nextLine();
				
				try {
					dob=new SimpleDateFormat("dd/MM/yyyy").parse(birthdateStr);
					break;
				} catch (ParseException e) {
					continue;
				}
			}
			
			

			System.out.println("address:");
			String address=sc.nextLine();

			System.out.println("institutional email(login):");
			String institutionalEmail=sc.nextLine();

			System.out.println("alternative email(login):");
			String alternativeEmail=sc.nextLine();

			System.out.println("phone:");
			String phone=sc.nextLine();

			System.out.println("password");
			String password=sc.nextLine();

			if(bean.createAdmin(name,dob,
					address,
					institutionalEmail,
					alternativeEmail,
					phone,
					password)){
				System.out.println("admin created");
			}
			else
				System.out.println("admin could not be created. maybe it already exists.");

		}
		//delete admin
		else if(operation.equals("-d")){

			System.out.println("institutional email(login):");
			String institutionalEmail=sc.nextLine();

			if(bean.deleteAdmin(institutionalEmail)){
				System.out.println("admin deleted");
			}
			else
				System.out.println("admin couldn't be deleted. maybe it doesn't exist.");
		}
		else{
			System.out.println("invalid operation.");
		}
		
		try{
			ic.close();
		}
		catch(NamingException e){
			e.printStackTrace();
		}

	}

}


