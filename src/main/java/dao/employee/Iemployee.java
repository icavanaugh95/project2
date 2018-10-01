package dao.employee;

import java.util.List;

import model.Employee;

// Employee DOA
public interface Iemployee {
	public boolean submitReimbursment(Employee employee, String reason, double amount, String s3url);
	public boolean updateEmployeeInfo(Employee employee);
	public List<List<String>> getPendingReimbursements(Employee emplyoee);
	public List<List<String>> getResolvedReimbursements(Employee employee);
	public Employee getEmployee(String username);
}
