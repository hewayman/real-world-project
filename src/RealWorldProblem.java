/* Hannah Wayman
 * April 26, 2020
 * This program reads a text file with patient information and produces a report.
 * The user can choose whether to output the report to the screen, a file, or both.
 */

import java.io.*;
import java.util.Formatter;
import java.util.ArrayList;
import java.util.Scanner;

public class RealWorldProblem {
	
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		// Create list to store patients
		ArrayList<Patient> list = new ArrayList<>(); 
		
		try {
			// Create a new file instance
			File file = new File("XYZHospitalData.txt"); 
		
			// Check if source file exists
			if (!file.exists()) {
				System.out.println("Source file does not exist");
				return;
			}
			
			// Create a new buffered reader for reading the source file
		    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		    String line = null;
		    while( (line = reader.readLine())!= null ){
		    	// Split each line into strings based on the delimiter ^ and add each to an array
		    	// Each string is saved to a variable for the patient records
		        String [] tokens = line.split("\\^");
		        String id = tokens[0];
		        String lastName = tokens[1];
		        String firstName = tokens[2];
		        String address1 = tokens[3];
		        String address2 = tokens[4];
		        String city = tokens[5];
		        String state = tokens[6];
		        String zip = tokens[7];
		        String zip4 = tokens[8];
		        String paymentDate = tokens[9];
		        String amountOwed = tokens[10];
		        String amountPaid = tokens[11];
		        
		        // Create a new patient record	
		        Patient patient = new Patient(id, lastName, firstName, address1, address2, city, state, zip, zip4, paymentDate, 
						amountOwed, amountPaid); 
				list.add(patient); // Add patient information to array list
		    }
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }	
		
		// Build a string with the header
		StringBuilder headerBuilder = new StringBuilder(); 
		
		// Format the header
		Formatter formatHeader = new Formatter(headerBuilder);
		formatHeader.format("%n%-62s%-50s%n%-100s%n%-20s%-50s%-50s%-50s%n%n%-8s%-13s%-12s%-20s%-18s%-15s%-10s%-6s" +
						"%-10s%-15s%-15s%-15s%n%-100s%n", "", "XYZ Community Hospital",
						"======================================================================"+
						"=====================================================================================",
						"", "Name", "Address", "Payment Information",
						"ID","Last","First", "Address Line 1", "Address Line 2", "City", "State", "Zip+4", "", 
						"Amount Owed", "Amount Paid", "Payment Date",
						"======================================================================"+
						"=====================================================================================");
		
		// Build a string with the patient records
		StringBuilder recordBuilder = new StringBuilder();
		Formatter formatRecords = new Formatter(recordBuilder); // Create a new formatter for the records
		
		// Format the patient records
		for (int i = 0; i < list.size(); i++) {
			Patient patient = list.get(i); 
			formatRecords.format("%-8s%-13s%-12s%-20s%-18s%-15s%-10s%-6s%-10s%-15s%-15s%-15s%n",patient.getId(),patient.getLastName(),
					  patient.getFirstName(), patient.getAddress1(),patient.getAddress2(), patient.getCity(), patient.getState(),
					  patient.getZip(), patient.getZip4(), patient.getAmountOwed(), patient.getAmountPaid(), patient.getPaymentDate());
		} 
		
		// Prompt user for output type
		System.out.println("Do you want to output the report to the screen ('S'), to a file ('F') or both ('B')");
		String userChoice = input.nextLine();
		
		// Prints the report to the screen if the user selects 'S'
		if ("S".equalsIgnoreCase(userChoice)) {
			// Print the header and patient records
			System.out.println(formatHeader);
			System.out.println(formatRecords);
		}
		
		// Saves the report to a file if the user select 'F'
		if ("F".equalsIgnoreCase(userChoice)) {
			// Prompts user for file name
			System.out.println("Enter the name of the output along with the full path: ");
			String outputFileName = input.nextLine();
			
			// Saves records to a file
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
				writer.write(headerBuilder.toString());
			    writer.write(recordBuilder.toString());
			    System.out.println("File successfully written to " + outputFileName);
			} catch (IOException ex) { // throws IOException
				ex.printStackTrace();
			}
		}
	    
		// Prints the report and saves it to to a file if the user select 'B'
		if ("B".equalsIgnoreCase(userChoice)) {
			// Prompts user for file name
			System.out.println("Enter the name of the output along with the full path: ");
			String outputFileName = input.nextLine();
			
			// Saves records to a file
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
				writer.write(headerBuilder.toString());
			    writer.write(recordBuilder.toString());
			    System.out.println("File successfully written to " + outputFileName);
			} catch (IOException ex) { // throws IOException
				ex.printStackTrace();
			}
			// Print the header and patient records
			System.out.println(formatHeader);
			System.out.println(formatRecords);
		}
	}
}

class Patient {
	Scanner input = new Scanner(System.in);
	// Initialize variables
	private String id = "";
	private String lastName = "";
	private String firstName = "";
	private String address1 = "";
	private String address2 = "";
	private String city = "";
	private String state = "";
	private String zip = "";
	private String zip4 = "";
	private String paymentDate = "";
	private String amountOwed = "";
	private String amountPaid = "";
	
	Patient(String id, String lastName, String firstName, String address1, String address2, String city, String state, String zip,
			String zip4, String paymentDate, String amountOwed, String amountPaid) { // Create patient object
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.zip4 = zip4;
		this.paymentDate = paymentDate;
		this.amountOwed = amountOwed;
		this.amountPaid = amountPaid;
	}
	// Create the getters and setters
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getZip4() {
		return zip4;
	}
	public void setZip4(String zip4) {
		this.zip4 = zip4;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getAmountOwed() {
		return amountOwed;
	}
	public void setAmountOwed(String amountOwed) {
		this.amountOwed = amountOwed;
	}
	public String getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
}
