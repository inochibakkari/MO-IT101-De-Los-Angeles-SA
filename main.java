import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.Arrays;

public class main {
	public static String[] employee;
	public static String employee_per_hour;

	public static void main(String [] args) {
		
		boolean empl_data = true;
		while (empl_data) {
			
			System.out.println("Welcome to MotorPH Employee Portal!");
			get_employee_data();
			
			if (employee.length != 0) {
				System.out.println("Thank you for verifying your ID.");
				System.out.println("--------------------------------");
				System.out.println("Employee Name: " + employee [1] + ", " + employee [2]);
				System.out.println("Employee ID: " + employee [0]);
				System.out.println("Position: " + employee [11]);
				System.out.println("Basic Salary: " + sanitize_data(employee [13]));
			}
			
		}
	
			
		}
	public static void reset () {
		employee = new String [0];
		employee_per_hour = "";
		
	}
	
	public static void get_employee_data () {
		reset();
		System.out.println("Please enter your employee ID: ");
		
		BufferedReader emp_input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String userInput = emp_input.readLine();
			
			String employee_detail = get_emp_details(userInput);
			
			if(!employee_detail.equals("")) {
				String[] row = employee_detail.split(",");
				employee = row;
				employee_per_hour = row[18];
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static String sanitize_data(String info) {
		return info.replace(";x;", ",").replace("\"", "");
	}
	
	public static String get_emp_details (String emp_ID) {
		//this is to identify the file directory
		String file = System.getProperty("user.dir") + "/src/employee_deets.csv";
		
		BufferedReader reader = null;
		String blank = "";
		String success = "";
		
				try {
					
					reader = new BufferedReader(new FileReader(file));
					
					while ((blank = reader.readLine()) != null) {
						String repl = blank.replaceAll(",(?!(([^\"]*\"){2})*[^\"]*$)", ";x;");
						String[] row = repl.split(",");
		
						if (Integer.parseInt(row[0]) == Integer.parseInt(emp_ID)) {
							success = repl;
				
						}
					}
				} catch (Exception e) {e.printStackTrace(); }
				
				return success;
}

	public static String govt_deductions() {
		
	}
}

