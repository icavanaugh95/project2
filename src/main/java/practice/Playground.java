package practice;

import java.util.List;

import model.Employee;
import service.EmployeeService;
import service.ManagerService;

public class Playground {
	public static void main(String[] args) {
		List<Employee> emps = ManagerService.getAllEmployees();
		for(Employee emp : emps) {
			System.out.println(emp.getUsername());
		}
		
	}
}
