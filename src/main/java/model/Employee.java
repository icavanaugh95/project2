package model;

public class Employee {
	private String username, password, fname, lname;
	private int priv, emp_id;
	
	public Employee() {
		super();
	}
	public Employee(String username, String password, String fname, String lname, int priv, int emp_id) {
		super();
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.priv = priv;
		this.emp_id = emp_id;
	}	
	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", fname=" + fname + ", lname=" + lname
				+ ", priv=" + priv + ", emp_id=" + emp_id + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getPriv() {
		return priv;
	}
	public void setPriv(int priv) {
		this.priv = priv;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	
}
