package dao.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import util.JDBCconnection;

/*
 * 
 * CHANGE DAO IMPLEMENTATIONS WHEN IMAGE IS IMPLEMENTED
 */

public class ManagerDAO implements Imanager{

	/*
	 * @param int reimbursement_id
	 * @param String name
	 * returns bool
	 */
	public boolean approveReimbursement(int reimbursement_id, String name) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "call resolve_reimbursement(?, 1, ?, ?)";
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, reimbursement_id);
			cs.setString(2, name);
			cs.setLong(3, System.currentTimeMillis());
			cs.execute();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * @param int reimbursement_id
	 * @param String name
	 * returns bool
	 */
	public boolean denyReimbursement(int reimbursement_id, String name) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "call resolve_reimbursement(?, 0, ?, ?)";
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, reimbursement_id);
			cs.setString(2, name);
			cs.setLong(3, System.currentTimeMillis());
			cs.execute();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * No parameter
	 * Return 2d string list
	 * Each line contains reimbursement info
	 */
	public List<List<String>> getAllPendingReimbursements() {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "select fname, lname, reimbursement_id, amount, reason, date_requested, image from "
					+ "employees natural join reimbursements natural join junction "
					+ "where junction.resolved_id is null";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<List<String>> results = new ArrayList<List<String>>();
			
			while(rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("fname"));
				list.add(rs.getString("lname"));
				list.add("" + rs.getInt("reimbursement_id"));
				list.add("" + rs.getDouble("amount"));
				list.add(rs.getString("reason"));
				list.add("" + rs.getLong("date_requested"));
				list.add(rs.getString("image"));
				results.add(list);
			}
			return results;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * No parameter
	 * Returns 2d string list
	 * Each line contains reimbursement info
	 */
	public List<List<String>> getAllResolvedReimbursements() {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "select reimbursement_id, amount, reason, status, date_requested, date_resolved, manager "
					+ "from reimbursements natural join resolved_reimbursements natural join junction";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<List<String>> results = new ArrayList<List<String>>();
			
			while(rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add("" + rs.getInt("reimbursement_id"));
				list.add("" + rs.getDouble("amount"));
				list.add(rs.getString("reason"));
				if(rs.getInt("status") == 1)
					list.add("Approved");
				else
					list.add("Denied");
				list.add("" + rs.getLong("date_requested"));
				list.add("" + rs.getLong("date_resolved"));
				list.add(rs.getString("manager"));
				results.add(list);
			}
			return results;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * No parameter
	 * Returns ArrayList of all Employees
	 */
	public List<Employee> getAllEmployees() {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "SELECT * FROM employees";
			List<Employee> employees = new ArrayList<Employee>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmp_id(rs.getInt("emp_id"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("password"));
				emp.setFname(rs.getString("fname"));
				emp.setLname(rs.getString("lname"));
				emp.setPriv(rs.getInt("priv"));
				employees.add(emp);
			}
			return employees;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * @param Employee emp
	 * returns 2d string list
	 * each line contains reimbursement info
	 */
	public List<List<String>> getEmployeeReimbursements(Employee emp) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "select reimbursement_id, amount, date_requested, reason "
					+ "from junction natural join reimbursements where emp_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getEmp_id());
			ResultSet rs = ps.executeQuery();
			List<List<String>> results = new ArrayList<List<String>>();
			
			while(rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add("" +rs.getInt("reimbursement_id"));
				list.add("" + rs.getDouble("amount"));
				list.add("" + rs.getLong("date_requested"));
				list.add(rs.getString("reason"));
				results.add(list);
			}
			
			return results;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
