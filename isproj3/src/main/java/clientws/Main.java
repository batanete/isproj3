package clientws;

import java.util.Scanner;

public class Main {




	public static void main(String[] args){

		SubscriberServiceService sss = new SubscriberServiceService();
		SubscriberService ss = sss.getSubscriberServicePort();
		String email,course;

		Scanner sc=new Scanner(System.in);
		int option=-1;

		while(option<0 || option>2){
			System.out.println("0:list subs");
			System.out.println("1:add sub(will need to be confirmed via email)");
			System.out.println("2:delete sub(will need to be confirmed via email)");

			option=Integer.parseInt(sc.nextLine());
		}

		switch(option){
		case(0):
			System.out.println(ss.listSubs());
		break;
		case(1):
			System.out.println("email:");
		email=sc.nextLine();
		System.out.println("course:");
		course=sc.nextLine();
		System.out.println(ss.createSub(email,course));
		break;
		case(2):
			System.out.println("email:");
		email=sc.nextLine();
		System.out.println("course:");
		course=sc.nextLine();
		System.out.println(ss.delSub(email,course));
		break;
		}

	}


}
