package dao.employee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import util.JDBCconnection;

public class EmployeeDAO implements Iemployee {

	/*
	 * @param Employee employee
	 * 
	 * @param String reason
	 * 
	 * @param double amount returns bool
	 */
	public boolean submitReimbursment(Employee employee, String reason, double amount, String s3url) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "call submit_reimbursement(?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, employee.getEmp_id());
			cs.setDouble(2, amount);
			cs.setLong(3, System.currentTimeMillis());
			cs.setString(4, reason);
			cs.setString(5, s3url);
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * @param Employee employee returns bool
	 */
	public boolean updateEmployeeInfo(Employee employee) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "UPDATE employees set username = ?, password = ?, fname = ?, lname = ? where emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, employee.getUsername());
			ps.setString(2, employee.getPassword());
			ps.setString(3, employee.getFname());
			ps.setString(4, employee.getLname());
			ps.setInt(5, employee.getEmp_id());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/*
	 * @param Employee employee
	 * returns 2d string list
	 * each line contains reimbursement info
	 */
	public List<List<String>> getPendingReimbursements(Employee employee) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "select reimbursement_id, amount, date_requested, reason, image from "
					+ "junction natural join reimbursements " + "where emp_id = ? and resolved_id is null";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getEmp_id());
			ResultSet rs = ps.executeQuery();
			List<List<String>> results = new ArrayList<List<String>>();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add("" + rs.getInt("Reimbursement_id"));
				list.add("" + rs.getDouble("amount"));
				list.add("" + rs.getLong("date_requested"));
				list.add(rs.getString("reason"));
				list.add(rs.getString("image"));
				results.add(list);
			}

			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * @param Employee employee
	 * returns 2d string list
	 * each line contains reimbursement info
	 */
	public List<List<String>> getResolvedReimbursements(Employee employee) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = " select reimbursement_id, reason, amount, status, date_requested, date_resolved, manager, image "
					+ "from resolved_reimbursements natural join junction natural join reimbursements"
					+ " where emp_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getEmp_id());
			ResultSet rs = ps.executeQuery();
			List<List<String>> results = new ArrayList<List<String>>();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add("" + rs.getInt("reimbursement_id"));
				list.add(rs.getString("reason"));
				list.add("" + rs.getDouble("amount"));
				if(rs.getInt("status") == 1)
					list.add("Approved");
				else
					list.add("Denied");
				list.add("" + rs.getLong("date_requested"));
				list.add("" + rs.getLong("date_resolved"));
				list.add(rs.getString("manager"));
				list.add(rs.getString("image"));
				results.add(list);
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * @param string username returns employee object
	 */
	public Employee getEmployee(String username) {
		try {
			Connection conn = JDBCconnection.getConnection();
			Employee emp = new Employee();
			String sql = "SELECT * FROM employees WHERE username = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				emp.setEmp_id(rs.getInt("emp_id"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("password"));
				emp.setFname(rs.getString("fname"));
				emp.setLname(rs.getString("lname"));
				emp.setPriv(rs.getInt("priv"));
			}
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
