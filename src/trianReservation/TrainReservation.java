package trianReservation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Tickets{
	static int idNumber ;
	int id;
	String passengerName;
	String MobileNumber;
	String passengerGender;
	int age;
	int preferedBerth;
	
	Tickets(String passengerName , String MobileNumber , String passengerGender , int age , int preferedBerth){
		this.id = ++idNumber;
		this.passengerName = passengerName;
		this.MobileNumber = MobileNumber;
		this.passengerGender = passengerGender;
		this.age = age;
		this.preferedBerth = preferedBerth;
	}
}

public class TrainReservation {
	
	static ArrayList <Tickets> LowerTicketDB = new ArrayList<>();
	static ArrayList <Tickets> MiddleTicketDB = new ArrayList<>();
	static ArrayList <Tickets> UpperTicketDB = new ArrayList<>();
	static Queue <Tickets> TicketLowerPendingDB = new LinkedList<>();
	static Queue <Tickets> TicketMiddlePendingDB = new LinkedList<>();
	static Queue <Tickets> TicketUpperPendingDB = new LinkedList<>();
	static int lowerBerth =2;
	static int middleBerth =2;
	static int upperBerth =2;
	
	public static void createPassenger(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Passenger Name: ");
		String passengerName = sc.next();
		System.out.print("Enter Passenger Mobile Number: ");
		String MobileNumber = sc.next();
		System.out.print("Enter Passenger Gender(Male/Female): ");
		String passengerGender = sc.next();
		System.out.print("Enter Passenger Age: ");
		int age = sc.nextInt();
		System.out.print("Enter Passenger Preffered berth: ");
		int berth = sc.nextInt();
		Tickets temp = new Tickets(passengerName , MobileNumber , passengerGender , age , berth);
		
		if(berth == 1 && lowerBerth > 0) {
			LowerTicketDB.add(temp);
			lowerBerth--;
			Tickets LastUser = LowerTicketDB.get(LowerTicketDB.size()-1);
			System.out.println("Your Ticket Has been Booked and your ID is " + LastUser.id + " and your Berth is Lower Berth");
			}
		else TicketLowerPendingDB.add(temp);
		
		if(berth == 2 && middleBerth > 0) {
			MiddleTicketDB.add(temp);
			middleBerth--;
			Tickets LastUser = MiddleTicketDB.get(MiddleTicketDB.size()-1);
			System.out.println("Your Ticket Has been Booked and your ID is " + LastUser.id + " and your Berth is Middle Berth");
			}
		else TicketMiddlePendingDB.add(temp);
		
		if(berth == 3 && upperBerth > 0) {
			UpperTicketDB.add(temp);
			upperBerth--;
			Tickets LastUser = UpperTicketDB.get(UpperTicketDB.size()-1);
			System.out.println("Your Ticket Has been Booked and your ID is " + LastUser.id + " and your Berth is Upper Berth");
		}
		else TicketUpperPendingDB.add(temp);
	}
	
	public static void PrintAllTickets(int password) {
		System.out.println("Enter Password to Access Tickets");
		int token = 2003;
		if(password==token) {			
			PrintLowerTickets();
			PrintMiddleTickets();
			PrintUpperTickets();
		}
	}
	
	public static void PrintLowerTickets() {
		if(LowerTicketDB.isEmpty())System.out.println("No Passengers info in Lower berth");
		else {			
			for(int i = 0 ; i < LowerTicketDB.size() ; i++) {
				Tickets temp = LowerTicketDB.get(i);
				System.out.println("Passenger ID : " + temp.id);
				System.out.println("Passenger Name : " + temp.passengerName);
				System.out.println("Passenger Mobile number : " + temp.MobileNumber);
				System.out.println("Passenger Gender : " + temp.passengerGender);
				System.out.println("Passenger Age : " + temp.age);
				System.out.println("Prefered Berth : " + temp.preferedBerth);
				System.out.println();
			}
		}
	}
	
	public static void PrintMiddleTickets() {
		for(int i = 0 ; i < MiddleTicketDB.size() ; i++) {
			Tickets temp = MiddleTicketDB.get(i);
			System.out.println("Passenger ID : " + temp.id);
			System.out.println("Passenger Name : " + temp.passengerName);
			System.out.println("Passenger Mobile number : " + temp.MobileNumber);
			System.out.println("Passenger Gender : " + temp.passengerGender);
			System.out.println("Passenger Age : " + temp.age);
			System.out.println("Prefered Berth : " + temp.preferedBerth);
			System.out.println();
		}
	}
	
	public static void PrintUpperTickets() {
		for(int i = 0 ; i < UpperTicketDB.size() ; i++) {
			Tickets temp = UpperTicketDB.get(i);
			System.out.println("Passenger ID : " + temp.id);
			System.out.println("Passenger Name : " + temp.passengerName);
			System.out.println("Passenger Mobile number : " + temp.MobileNumber);
			System.out.println("Passenger Gender : " + temp.passengerGender);
			System.out.println("Passenger Age : " + temp.age);
			System.out.println("Prefered Berth : " + temp.preferedBerth);
			System.out.println();
		}
	}
	
	public static void TicketCancellation() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your Ticket ID ");
		int ticketID = sc.nextInt();
		System.out.print("Enter your Berth Number ");
		int berthNumber = sc.nextInt();
		if(berthNumber==1) {
			for(int i = 0 ; i < LowerTicketDB.size() ; i++) {
				Tickets temp = LowerTicketDB.get(i);
				if(temp.id==ticketID) {
					LowerTicketDB.remove(i);
					reallocateLowerBerthTicket();
					return;
					}
				}
			System.out.println("No Info is available , Please enter correct ID and Berth number");
			}
		else if(berthNumber==2) {
			for(int i = 0 ; i < MiddleTicketDB.size() ; i++) {
				Tickets temp = MiddleTicketDB.get(i);
				if(temp.id==ticketID) {
					MiddleTicketDB.remove(i);
					reallocateMiddleBerthTicket();
					return;
					}
				}
			System.out.println("No Info is available , Please enter correct ID and Berth number");
			}
		else if(berthNumber==3) {
			for(int i = 0 ; i < UpperTicketDB.size() ; i++) {
				Tickets temp = UpperTicketDB.get(i);
				if(temp.id==ticketID) {
					UpperTicketDB.remove(i);
					reallocateUpperBerthTicket();
					return;
					}
				}
			System.out.println("No Info is available , Please enter correct ID and Berth number");
			}
		}
	
	public static void reallocateLowerBerthTicket(){
		if(!(TicketLowerPendingDB.isEmpty())) {
			LowerTicketDB.add(TicketLowerPendingDB.peek());
			TicketLowerPendingDB.remove();
		}
	}
	
	public static void reallocateMiddleBerthTicket(){
		if(!(TicketMiddlePendingDB.isEmpty())) {
			LowerTicketDB.add(TicketMiddlePendingDB.peek());
			TicketMiddlePendingDB.remove();
		}
	}
	
	public static void reallocateUpperBerthTicket(){
		if(!(TicketUpperPendingDB.isEmpty())) {
			LowerTicketDB.add(TicketUpperPendingDB.peek());
			TicketUpperPendingDB.remove();
		}
	}
	
	public static void main(String args[]) {
		while(true) {
			System.out.println("Select a option");
			System.out.println("1.Book Ticket");
			System.out.println("2.Cancel Ticket");
			System.out.println("3.Print All Tickets(required Admin Password)");
			System.out.println("4.Exit");
			Scanner sc = new Scanner(System.in);
			int choose = sc.nextInt();
			if(choose==1)createPassenger();
			else if(choose==2)TicketCancellation();
			else if(choose==3) {
				int password;
				password = sc.nextInt();
				PrintAllTickets(password);
			}
			else if(choose==4)break;
			else System.out.println("Enter a valid option");
		}
	}
}