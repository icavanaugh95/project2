package service;

import java.util.List;

import dao.employee.EmployeeDAO;
import model.Employee;

public class EmployeeService {
	public static Employee getEmployee(String username) {
		return new EmployeeDAO().getEmployee(username);
	}
	
	public static boolean submitReimbursement(Employee employee, String reason, double amount, String s3url) {
		return new EmployeeDAO().submitReimbursment(employee, reason, amount, s3url);
	}
	
	public static boolean updateEmployeeInfo(Employee employee) {
		return new EmployeeDAO().updateEmployeeInfo(employee);
	}

	public static List<List<String>> getPendingReimbursements(Employee employee){
		return new EmployeeDAO().getPendingReimbursements(employee);
	}
	
	public static List<List<String>> getResolvedReimbursements(Employee employee){
		return new EmployeeDAO().getResolvedReimbursements(employee);
	}
}
