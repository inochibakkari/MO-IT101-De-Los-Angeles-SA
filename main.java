package copro1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;

public class main {
public static String employee_det;
public static String[] employee;
public static String hourly;
public static String hours_worked;
public static String date;
public static String time;




	public static void main(String [] args) { /*main method to be used*/
		
		boolean success = true;
		while (success) {
		
		System.out.println("Welcome to MotorPH Portal!");
		
		get_employee_ID();
		
		if (employee.length != 0) {
			
		System.out.println("Thank you for verifying. Here are your details:");
		System.out.println("Employee ID: " + employee[0]);
		System.out.println("Employee Name: " + employee[1] + ", " + employee[2]);
		System.out.println("Birthday: " + employee[3]);
		System.out.println("----------------------------------");
		
		
		view_payslip();
		
		sss_deductions();
		philhealth_deduc();
		pagibig_deduc();
		tax_deduc();
		
		System.out.println("Hours worked: ");
		
		
		
		} else {
			System.out.println("Error. Please try again.");
		}
		}
	}
	
	
//method to get employee ID from user input - done
//method to get employee details - done
//method to view and print payslip - done
//method to calculate hours worked
//method to calculate deductions - done
//method to calculate salary
//method to logout or end session
	
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
	
	public static String get_employee_details(String employee_det) { /*method to get employee details from csv file*/
	
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
	
public static void view_payslip() { /*method to view and print payslip*/
	
	System.out.println("Would you like to check your payslip?");
	
	try {
		
	
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String userinput = reader.readLine(); //new user input

	
	if (userinput.equals("Yes")) {
		System.out.println("Basic Salary: "+replace_data(employee[13]));
		System.out.println("Rice Subsidy: "+replace_data(employee[14]));
		System.out.println("Phone Allowance: "+replace_data(employee[15]));
		System.out.println("Clothing Allowance: "+replace_data(employee[16]));
		System.out.println("----------------------------------------");
	}
	else if (userinput.equals("No")) {
		System.out.println("Thank you. Goodbye.");
	}
	else {
		System.out.println("Error. Please try again.");
	}
	}
	
 catch (Exception e) {
	e.printStackTrace();
	System.out.println("Error in your input. Try again.");
	
}

	
}

public static String sss_deductions() { /*method to calculate sss deductions*/
	
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
	return null;
	
}

public static void philhealth_deduc() { /*method to calculate philhealth deductions*/
	
	String basic_sal = employee[13].replace(";x;", "").replace("\"", "");
	hourly = employee[18];
	
	
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



public static void tax_deduc() { /*method to calculate tax deductions*/

	String basic_sal = employee[13].replace(";x;", "").replace("\"", "");
	double tax = Double.parseDouble(basic_sal) * 0.20 + 1875;
	double tax2 = Double.parseDouble(basic_sal) * 0.25 + 8541.80;
	
	if (Integer.parseInt(basic_sal) >= 33333) {
		System.out.println("Witholding Tax: " + Integer.parseInt(basic_sal) * 0.15);
		
	}
	else if (Integer.parseInt(basic_sal) >= 66667) {
		System.out.println("Witholding Tax: " + tax);
	}
	else if (Integer.parseInt(basic_sal) >= 166667) {
		System.out.println("Witholding Tax: " + tax2);
	}
	
}

public static void calculate_hours_worked() { /*method to calculate hours worked*/
	
	
}

public static void get_hours_worked() { /*method to get hours worked using csv file*/
	
	
}

}

