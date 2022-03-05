package com.client;

import java.util.Scanner;

import com.controller.AdminController;
import com.controller.CustomerController;
import com.database.AdminDao;
import com.registration.Login;
import com.registration.Register;

public class FoodOrderMain {
	static String username, password;
	static Login ld; // reference of login
	Register rd;
	static String t = "true", f = "false";
	static Scanner sc;
	static int a, choice;
	static int ch1;

	static AdminController a1 = new AdminController();
	static CustomerController c1 = new CustomerController();
	static AdminDao adminDao = new AdminDao();
	static String ans;
	static String conpassword;
	// static String Username;
	// static String pass;
	static long phoneno;
	static String emailid;
	// static int id=0;

	static int choice4;

	String loginDetail() {
		// System.out.println("---LOGIN as ADMIN--");
		System.out.println();
		sc = new Scanner(System.in);
		System.out.println("Enter the UserName");
		username = sc.next();
		System.out.println("Enter the Password");
		password = sc.next();
		ld = new Login();
		ld.setUsername(username);
		ld.setPassword(password);
		boolean b1 = a1.loginContoller(ld);
		if (b1 == true)
			return t;
		else
			return f;
	}// loginDetail

	String loginDetailCust() {
		// System.out.println("---LOGIN as CUSTOMER---");
		System.out.println();
		sc = new Scanner(System.in);
		System.out.println("Enter the UserName");
		username = sc.next();
		System.out.println("Enter the Password");
		password = sc.next();
		ld = new Login();
		ld.setUsername(username);
		ld.setPassword(password);
		boolean b1 = c1.loginContoller(ld);
		if (b1 == true)
			return t;
		else
			return f;
	}

	String RegDetail() {
		System.out.println("---REGISTER AS CUSTOMER--");
		sc = new Scanner(System.in);
		System.out.println("Enter the UserName");
		username = sc.next();
		System.out.println("Enter emailid::");
		emailid = sc.next();
		System.out.println("Enter the Phone_no");
		phoneno = sc.nextLong();
		System.out.println("Enter the Password");
		password = sc.next();
		System.out.println("Enter the Password again");
		conpassword = sc.next();
		if (password.equals(conpassword)) {
			rd = new Register();
			rd.setUsername(username);
			rd.setEmailid(emailid);
			rd.setPhone_no(phoneno);
			rd.setPassword(password);
			rd.setConnpassword(conpassword);
			boolean b1 = c1.registerContoller(rd);
			if (b1 == true)
				return t;
			else
				return f;
		} else {
			return "Password doesn't matches ConfirmPassword";
		}

	}

	int showMenu() {
		int ch;
		System.out.println("Menu :-");
		System.out.println("------------------------------------Welcome Admin------------------------------------------");

		System.out.println(
				"1.Press '1' to delete Customer\n2.Press '2' to view Customers\n3.Press '3' to view Food menu\n4.Press '4' to add Restaturant \n5.Press '5' to delete Resturant \n6.Press '6' to view Restaurant ");

		sc = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter choice");
		ch = sc.nextInt();
		return (ch);
	}

	int showMenuCust() {
		int ch;
		System.out.println("Menu :-");
		System.out.println("-------------------------------------Welcome Customer-----------------------------------------------------------");
		System.out.println("1.Press '1' To View Food Item\n2.Press '2' To place Order");
		sc = new Scanner(System.in);
		System.out.println("Enter choice");
		ch = sc.nextInt();
		return (ch);
	}

	public static void main(String[] args) {
		System.out.println("Hello there, this project is available on Github");
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("                                Food Ordering System                                ");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("Hey there ! Welcome to Foody's Restaurant");
		// System.out.println();
		sc = new Scanner(System.in);
		FoodOrderMain obj = new FoodOrderMain();

		System.out.println("-------------------------------------------------------------------------------------------------");

		// a=sc.nextInt();
		// System.out.println();
		adminDao.getConn();

		do {
			System.out.println("Press 1 if you want to countinue as Administrator");
			System.out.println("Press 2 if you are a Customer");
			choice4 = sc.nextInt();

			switch (choice4) {
			case 1:

				// System.out.println("You choose Administrator");
				System.out.println("Hello there ! You chose Administrator");
				String str = obj.loginDetail();

				if (str.equals(t)) {
					do {
						choice = obj.showMenu();
						switch (choice) {

//				case 1:
//					a1.addCustomer();
//					break;
//		
						case 1:
							a1.deleteCustomer();
							break;

						case 2:
							System.out.println(
									"--------------------Customers List----------------------------------------------------");
							System.out.println("id" + "\t    " + "Name" + "          " + "Emailid" + "         	"
									+ "Phone" + " ");

							System.out.println(
									"-----------------------------------------------------------------------------");
							a1.showAllCustomer();
							System.out.println(
									"-----------------------------------------------------------------------------");

							break;

						case 3:
							System.out.println();
							System.out.println("---------------------------------------Menu List--------------------------------------------------");
							System.out.println("Name                   " + "\t\t\t" + "Category" + "                        \t" + " " + "Price");
							System.out.println("---------------------------------------------------------------------------------------------------");
							a1.showallFoodItems();
							System.out.println("---------------------------------------------------------------------------------------------------");
							break;

						case 4:
							a1.addRestaurant();
							break;

						case 5:
							a1.deleteRestaurant();
							break;

						case 6:
							System.out.println(
									"------------------------------------------------------------------------------------------");
							System.out.println(" Restaurant List : ");
							System.out.println(
									"-------------------------------------------------------------------------------------------");
							a1.showRestaurant();
							System.out.println(
									"------------------------------------------------------------------------------------------");
							break;

						default:
							System.out.println("Not Allowed");
							break;

						}

						System.out.println("Do you want to continue : (Y/N)");
						ans = sc.next();
					} while (ans.equals("Y") || ans.equals("y"));
				}
				break;

			case 2:
				// System.out.println("You choose Customer");
				System.out.println("Hello there ! You chose customer");

				do {
					System.out.println("Press 1 if you are a new user else press 2 to login");
					System.out.println("You choose to:\n1.LOGIN\n2.REGISTER");
					int ch = sc.nextInt();
					if (ch == 1) {

						String st = obj.loginDetailCust();
						do {
							if (st.equals(t)) {
								ch1 = obj.showMenuCust();

								switch (ch1) {

								case 1:

									c1.showFoodItems();
									break;

								case 2:
									c1.placeOrder(ld);
									break;

								}
							} else
								break;
							System.out.println("Do you want to continue : (Y/N)");
							ans = sc.next();
						} while (ans.equals("Y") || ans.equals("y"));

//	if(ans.equals("N") || ans.equals("n"))
//			{
//		ch1=obj.showMenuCust();
//			}
					}

					else if (ch == 2) {
						String st2 = obj.RegDetail();
						if (st2.equals(t)) {
							System.out.println("You may now LOGIN as CUSTOMER");
						} else
							System.out.println(st2);
					} else
						System.out.println("Invalid Choice");

					System.out.println("Do you want to continue as CUSTOMER : (Y/N)");
					ans = sc.next();
				} while (ans.equals("Y") || ans.equals("y"));
				break;

			default:
				System.out.println("Not Allowed");
				break;
			}
		} while (choice4 == 1 || choice4 == 2);
		adminDao.closeConn();
		System.out.println("Exit");

	}
}// class
