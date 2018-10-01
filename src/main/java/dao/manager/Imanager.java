package dao.manager;

import java.util.List;

import model.Employee;

public interface Imanager {
	public boolean approveReimbursement(int reimbursement_id, String name);
	public boolean denyReimbursement(int reimbursement_id, String name);
	public List<List<String> >getAllPendingReimbursements();
	// method to get images of receipts
	public List<List<String>> getAllResolvedReimbursements();
	public List<Employee> getAllEmployees();
	public List<List<String>> getEmployeeReimbursements(Employee emp);
	// add chart methods
	
}
