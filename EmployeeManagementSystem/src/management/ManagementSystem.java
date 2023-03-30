package management;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/* Tasks
 * 1. How many male and female are there in the organization?
 * 2. Print the name of all departments in the organization.
 * 3. What is the average age of all male and female employee?
 * 4. Get the details of highest paid employee in the organization.
 * 5. Get the name of all employees who have joined after 2015.
 * 6. Count the number of employees in each department.
 * 7. What is the average salary of each department?
 * 8. Get the details of youngest male employee int the product development department.
 * 9. Who has the most working experience in the organization?
 *10. How many male and female employees are there in the sales and marketing team?
 *11. What is the average salary of male and female employees?
 *12. List down the names of all employees in each department.
 *13. What is the average salary and total salary of the whole organization?
 *14. Separate the employees who are younger or equal to 25 years from those employess who are older than 25 years.
 *15. Who is the oldest employee in the organization? What is his age and which department he belongs to?
*/

@SuppressWarnings("unused")
public class ManagementSystem {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.0));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin joshi", 25, "Male", "Product Development", 2015, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
		
		/* 1. NUMBER OF MALE AND FEMALE IN THE ORGANIZATION*/
		
		Map<String, Long> noOfMaleAndFemale = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		
		System.out.println("1. Total number of male and female in the organization is: ".toUpperCase() + noOfMaleAndFemale + ".\n");
		
		/* 2. DEPARTMENTS IN THE ORGANIZATION */
		
		System.out.println("2. Departments".toUpperCase());
		
		employeeList.stream()
					.map(Employee::getDepartment)
					.distinct()
					.forEach(System.out::println);
		
		/* 3. AVERAGE AGE OF ALL MALE AND FEMALE EMPLOYEE */
		
		Map<String, Double> avgAgeOfMaleAndFemale = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		
		System.out.println("\n" + "3. The average age of male and female: ".toUpperCase() + avgAgeOfMaleAndFemale + ".");
		
		/* 4. HIGHEST PAID EMPLOYEE IN THE ORGANIZATION */	
		
		Optional<Employee> highestPaidEmployeeWrapper = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		
		Employee highestPaidEmployee = highestPaidEmployeeWrapper.get();
		System.out.println();
		System.out.println("4. Highest paid employee details:".toUpperCase());
		System.out.println("=================================");
		System.out.println("ID: " + highestPaidEmployee.getId());
		System.out.println("Name: " + highestPaidEmployee.getName());
		System.out.println("Age: " + highestPaidEmployee.getAge());
		System.out.println("Gender: " + highestPaidEmployee.getGender());
		System.out.println("Department: " + highestPaidEmployee.getDepartment());
		System.out.println("Year Of Joining: " + highestPaidEmployee.getYearOfJoining());
		System.out.println("Salary: " + highestPaidEmployee.getSalary());
		
		/* 5. EMPLOYEES WHO JOINED AFTER 2015 */
		
		System.out.println();
		System.out.println("5. Name of employees who joined after 2015:".toUpperCase());
		employeeList.stream()
					.filter(e -> e.getYearOfJoining() > 2015)
					.map(Employee::getName)
					.forEach(System.out::println);
		
		/* 6. COUNT THE NUMBER OF EMPLOYEE IN EACH DEPARTMENT*/
		
		Map<String, Long> employeeCountByDepartment = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		
		System.out.println();
		Set<Map.Entry<String, Long>> entrySet = employeeCountByDepartment.entrySet();
		
		System.out.println("6. NUMBER OF EMPLOYEE IN EACH DEPARTMENT: ");
		for (Map.Entry<String, Long> entry : entrySet) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		/* 7. WHAT IS THE AVERAGE SALARY OF EACH DEPARTMENT?*/
		
		Map<String, Double> avgSalaryOfDepartments = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		
		System.out.println();
		Set<Map.Entry<String, Double>> avgSalary = avgSalaryOfDepartments.entrySet();
		
		System.out.println("7. AVERAGE SALARY FOR EACH DEPARTMENT");
		for (Map.Entry<String, Double> salary : avgSalary) {
			System.out.println(salary.getKey() + " : " + salary.getValue());
		}
	}

}
