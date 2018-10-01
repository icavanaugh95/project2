package service;

import java.util.List;

import dao.manager.ManagerDAO;
import model.Employee;

public class ManagerService {
	
	public static List<Employee> getAllEmployees() {
		return new ManagerDAO().getAllEmployees();
	}

	public static List<List<String>> getAllPendingReimbursements(){
		return new ManagerDAO().getAllPendingReimbursements();
	}
	
	public static List<List<String>> getAllResolvedReimbursements(){
		return new ManagerDAO().getAllResolvedReimbursements();
	}
	
	public static boolean approveReimbursement(int reimbursement_id, String name) {
		return new ManagerDAO().approveReimbursement(reimbursement_id, name);
	}
	
	public static boolean denyReimbursement(int reimbursement_id, String name) {
		return new ManagerDAO().denyReimbursement(reimbursement_id, name);
	}
}
