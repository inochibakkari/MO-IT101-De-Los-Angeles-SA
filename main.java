package copro1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.*;

public class main {
public static String employee_det;
public static String[] employee;
public static String hourly;
public static String hours;




	public static void main(String [] args) throws ParseException, IOException { /*main method to be used*/
		
		boolean success = true;
		while (success) {
		
		System.out.println("------------------Start-----------------------");
		System.out.println("Welcome to MotorPH Portal!");
		
		get_employee_ID();
		
		if (employee.length != 0) {
				
		System.out.println("Thank you for verifying. Here are your details:");
		System.out.println("-----------------------------------------------");
		System.out.println("Employee ID: " + employee[0]);
		System.out.println("Employee Name: " + employee[1] + ", " + employee[2]);
		System.out.println("Birthday: " + employee[3]);
		System.out.println("Address: " + replace_data(employee[4]));
		System.out.println("Phone Number: "+employee[5]);
		System.out.println("SSS # : "+employee[6]);
		System.out.println("Philhealth # : "+employee[7]);
		System.out.println("TIN # : "+employee[8]);
		System.out.println("Pag-ibig # : "+employee[9]);
		System.out.println("Status: "+employee[10]);
		System.out.println("Position: "+employee[11]);
		System.out.println("Immediate Supervisor: "+replace_data(employee[12]));
		System.out.println("-----------------------------------------------");
	
		
		view_payslip();
		sss_deductions();
		philhealth_deduc();
		pagibig_deduc();
		calculate_salary();
		
			
		
		} else {
			System.out.println("No employee with this ID. Please try again.");
		
		}
		}
	}
	
	
	
//method to get employee ID from user input - done
//method to get employee details - done
//method to view and print payslip - done
//method to calculate hours worked - done
//method to calculate deductions - done
//method to calculate salary - done
	
	public static void get_employee_ID() { /*method to get employee ID by user input*/
		reset();
		System.out.println("Please enter your employee ID: ");
		
		
		try {
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine();
			String employee_details = get_employee_details(input);
			
			if (!employee_details.equals("")) {
				String[] row = employee_details.split(",");
				employee = row;
				hourly = row[18];
				employee_details = input;
			}
			
			
			
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("Error in getting employee_ID");
		
}
		
		
		}
	
	public static void reset () { /*to reset certain block of code*/
	employee = new String [0];
	hourly = "";
	}
	
	public static String get_employee_details(String employee_det) { /*method to get employee details from CSV file*/
	
		String file = System.getProperty("user.dir") + "/src/employee_deets.csv";
		
		BufferedReader csvreader = null;
		String line = "";
		String located = "";
		
		try {
			csvreader = new BufferedReader (new FileReader(file));
			
		
			while ((line = csvreader.readLine()) != null) {
			
				String replce = line.replaceAll(",(?!(([^\"]*\"){2})*[^\"]*$)", ";x;");
				String[] row = line.split(",");
				
				
				
				if (Integer.parseInt(row[0]) == Integer.parseInt(employee_det)) {
				located = replce;
				
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return located;
	}
	
public static String replace_data (String data) { /*method to replace x with comma*/
	return data.replace(";x;", ",").replace("\"", "");
}
	
public static void view_payslip() { /*method to view and print pay slip*/
	
	System.out.println("Would you like to check your salary details?");
	
	try {
		
	
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String userinput = reader.readLine(); //new user input

	
	if (userinput.equalsIgnoreCase("Yes")) {
		System.out.println("Basic Salary: "+replace_data(employee[13]));
		System.out.println("Hourly Rate: "+replace_data(employee[18]));
		System.out.println("Rice Subsidy: "+replace_data(employee[14]));
		System.out.println("Phone Allowance: "+replace_data(employee[15]));
		System.out.println("Clothing Allowance: "+replace_data(employee[16]));
		System.out.println("-----------------------------------------------");
		
	}
	
	else if (userinput.equalsIgnoreCase("No")) {
		System.out.println("Thank you. Goodbye.");
	}
	else {
		System.out.println("Error. Please try again.");
		get_employee_ID();
		
	}
	}
	
 catch (Exception e) {
	e.printStackTrace();
	System.out.println("Error in your input. Try again.");
	
}

	
}

public static void sss_deductions() { /*method to calculate SSS deductions*/
	
	System.out.println("Deductions:");
	
	String basic_sal = employee[13].replace(";x;", "").replace("\"", "");
	hourly = employee[18];
	double sss_deduc1 = 1012.50;
	double sss_deduc2 = 1035.00;
	double sss_deduc3 = 1057.50;
	double sss_deduc4 = 1080.00;
	double sss_deduc5 = 1102.50;
	double sss_deduc6 = 1125.00;
	
	
	if (Integer.parseInt(basic_sal) <= 22751) {
		System.out.println("SSS: "+ sss_deduc1);
		
	}	else if (Integer.parseInt(basic_sal) <= 23250) {
		System.out.println("SSS: "+ sss_deduc2);
		}
	else if (Integer.parseInt(basic_sal) <= 23750) {
		System.out.println("SSS: "+ sss_deduc3);
	}
	else if (Integer.parseInt(basic_sal) <= 24250) {
		System.out.println("SSS: "+ sss_deduc4);
	}
	else if (Integer.parseInt(basic_sal) <= 24750) {
		System.out.println("SSS: "+ sss_deduc5);
	}
	else if (Integer.parseInt(basic_sal) >= 24750) {
		System.out.println("SSS: "+ sss_deduc6);
	}
	else {
		System.out.println("Error. Try again.");
	}
	
}

public static void philhealth_deduc() { /*method to calculate Philhealth deductions*/
	
	String basic_sal = employee[13].replace(";x;", "").replace("\"", "");
	hourly = employee[18];
	
	double philhealth = (Integer.parseInt(basic_sal) * 0.03 / 2);
	
	
	if (Integer.parseInt(basic_sal) <= 59999.00) {
		System.out.println("Philhealth: "+ Integer.parseInt(basic_sal) * 0.03 / 2);
		
	}	else if (Integer.parseInt(basic_sal) >= 60000) {
		System.out.println("Philhealth: "+ Integer.parseInt(basic_sal) * 0.03 / 2);
	}
	
}

public static void pagibig_deduc() { /*method to calculate pagibig deductions*/
	
	String basic_sal = employee[13].replace(";x;", "").replace("\"", "");
	hourly = employee[18];
	double pagibig_deduc = Integer.parseInt(basic_sal) * 0.02;
	
	if (Integer.parseInt(basic_sal) >= 1500) {
		System.out.println("Pag-ibig: " + Integer.parseInt(basic_sal) * 0.02);
	}
	
} 



public static void calculate_salary () throws ParseException, IOException { /*method to calculate salary based on hours worked*/
	
	 String csvFile = System.getProperty("user.dir")+"/src/attendance_data.csv"; 
	 
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy"); //to parse date from CSV file
	    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");// helps parse time from CSV file

	    Scanner scanner = new Scanner(System.in); //provides user input
	    
	    System.out.println("----------------------------------------------");

	    System.out.print("Enter your Employee ID to check your payslip: ");
	    int targetEmployeeId = Integer.parseInt(scanner.nextLine()); //this will target data based on a specific employee ID

	    System.out.print("Enter the month you would like to check (1-12): ");
	    int targetMonth = Integer.parseInt(scanner.nextLine()) - 1; //this will help calculate data of a certain month

	    System.out.print("Enter the year you would like to check: ");
	    int targetYear = Integer.parseInt(scanner.nextLine()); //filters the year as needed

	    double totalHoursWorked = 0.0;

	    try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
	      String line;

	      
	      reader.readLine();

	      while ((line = reader.readLine()) != null) {
	        String[] data = line.split(",", -1); 

	        String employeeIdStr = data[0];
	        int employeeId = Integer.parseInt(employeeIdStr); //parsed string from the data
	        Date date = dateFormat.parse(data[1]);

	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);

	        if (employeeId == targetEmployeeId && cal.get(Calendar.MONTH) == targetMonth && cal.get(Calendar.YEAR) == targetYear) { //calendar helps in getting the month and year for comparison
	          String loginTimeStr = data[2];
	          String logoutTimeStr = data[3];

	          Date loginTime = timeFormat.parse(loginTimeStr); //need to parse time to be able to compare and calculate
	          Date logoutTime = null;

	          if (logoutTimeStr != null && !logoutTimeStr.isEmpty()) {
	            logoutTime = timeFormat.parse(logoutTimeStr);
	          }

	          
	          long timeDiff = logoutTime != null ? logoutTime.getTime() - loginTime.getTime() : 0;
	          double hoursWorked = (double) timeDiff / (1000 * 60 * 60); // converted milliseconds to hours to calculate time difference
	          totalHoursWorked += hoursWorked;
	        }
	      }
	    }
	    System.out.println("----------------------------------------------------");
	    System.out.println("Total hours worked in " + (targetMonth + 1) + "/" + targetYear + ": " + totalHoursWorked);
	    
		
		
		double total_taxable = totalHoursWorked * Double.parseDouble(employee[18]); //calculate salary based on hours worked
		
		
		System.out.println("Hourly rate: " + employee[18]);
		System.out.println("Total Taxable Income: "  + total_taxable);
		
		double amount = total_taxable;
		double taxBase = 20833;  // the base amount for tax calculation as per MotorPH web site
		double taxBase2 = 33333;
		double taxBase3 = 66667;
		double excessPercent = 20.0 / 100.0; // converted percentage to decimal
		double excessPercent2 = 25.0 / 100.0;
		double excessPercent3 = 30.0 / 100.0;

		// to calculate the amount of excess tax based on the computation from MotorPH web site
		double taxExcess = taxBase * excessPercent;
		double taxExcess2 = taxBase2 * excessPercent2;
		double taxExcess3 = taxBase3 * excessPercent3;

		// to calculate the total tax base (base + excess)
		double totalTaxBase = taxBase + taxExcess;
		double totalTaxBase2 = taxBase2 + taxExcess2;
		double totalTaxBase3 = taxBase3 + taxExcess3;
		

		// to calculate the tax amount as a percentage of the total tax base
		double taxRate = 0.20;
		double taxRate2 = 0.25;
		double taxRate3 = 0.30;
		double totalTax = totalTaxBase * taxRate;
		double totalTax2 = totalTaxBase2 * taxRate2;
		double totalTax3 = totalTaxBase3 * taxRate3;
		int additional = 2500;
		int additional2 = 10833;
		
		String rice = employee[14].replace(";x;", "").replace("\"", ""); //replaced ;x; and double quote to be able to parse string
		String phone = employee[15].replace(";x;", "").replace("\"", "");
		String cloth = employee[16].replace(";x;", "").replace("\"", "");
		
		int ricE = Integer.parseInt(rice); //parsed from string so the numbers can be used for computation
		int phonE = Integer.parseInt(phone);
		int clotH = Integer.parseInt(cloth);
		
		//deductions total:
		String basic_sal = employee[13].replace(";x;", "").replace("\"", "");
		double sss_deduc1 = 1012.50;
		double sss_deduc2 = 1035.00;
		double sss_deduc3 = 1057.50;
		double sss_deduc4 = 1080.00;
		double sss_deduc5 = 1102.50;
		double sss_deduc6 = 1125.00;
		double philhealth = (Integer.parseInt(basic_sal) * 0.03 / 2);
		double pagibig_deduc = (Integer.parseInt(basic_sal) * 0.02);
		
		double phil_pagibig = (philhealth+pagibig_deduc);
		
		
		
		//net pay calculation already includes allowances that are non-taxable
		
		if (amount <= 20832) {
			System.out.println("Witholding tax: "+ "N/A") ;
			System.out.println("Net pay: "+ (amount + ricE + phonE + clotH - phil_pagibig - sss_deduc1)); //no tax deduction as per computation
		
		}
		else if (amount >= 20833) {
		System.out.println("Witholding tax: "+ totalTax) ;
		System.out.println("Net pay: "+ (amount - totalTax) + (ricE + phonE + clotH - phil_pagibig - sss_deduc4));
		}
		
		else if (amount >= 66667) {
		System.out.println("Witholding tax: "+ totalTax2 + additional);
		System.out.println("Net pay: "+ (amount - totalTax2 - additional) + (ricE + phonE + clotH - phil_pagibig - sss_deduc6));
		}
		
		else if (amount >= 166667) {
		System.out.println("Witholding tax: "+ totalTax3 + additional);
		System.out.println("Net pay: "+ (amount - totalTax3 - additional2) + (ricE + phonE + clotH - phil_pagibig - sss_deduc6));
		
		}
		
		System.out.println("---------------------End------------------------");
		
		
		}

}


