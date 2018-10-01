package requests;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Employee;
import service.EmployeeService;
import service.ManagerService;

public class ManagerRequestHelper {
	
	public static void getAllEmployees(HttpServletResponse response) {
		List<Employee> employees = ManagerService.getAllEmployees();

		try {
			response.getWriter().println(String.format("%-20s %-20s", "Username:", "Name:"));
			response.getWriter().println("----------------------------------------");
			for (Employee emp : employees)
				response.getWriter().println(
						String.format("%-20s %-20s", emp.getUsername(), emp.getFname() + " " + emp.getLname()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void getAllPendingReimbursements(HttpServletResponse response) {
		List<List<String>> results = ManagerService.getAllPendingReimbursements();
		try {
			response.getWriter().println(String.format("	%-15s %-10s %-10s %-15s %-14s %-15s", "Employee", "Number",
					"Amount", "Reason", "Date", "Receipt"));
			response.getWriter()
					.println("--------------------------------------------------------------------------------");
			Calendar calendar = Calendar.getInstance();

			for (List<String> str : results) {
				String url = "       <a href=#                       ></a>";
				if (!str.get(6).equals("0")) // only changes url if there is one present
					url = "<a href=\"" + str.get(6) + "\"target=\"_blank\">Receipt</a>"; // formatted anchor for html
				calendar.setTimeInMillis(Long.parseLong(str.get(5)));
				int month = calendar.get(Calendar.MONTH);
				response.getWriter().println(String.format("%-15s %-10s $%-10s %-15s %-14s %-1s",
						str.get(0) + " " + str.get(1), str.get(2), str.get(3), str.get(4),
						++month + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR), url));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void getAllResolvedReimbursements(HttpServletResponse response) {
		List<List<String>> results = ManagerService.getAllResolvedReimbursements();
		Calendar calendar = Calendar.getInstance();

		try {
			response.getWriter().println(String.format("%-10s %-10s %-20s %-13s %-12s %-12s %-15s", "Number", "Amount",
					"Reason", "Resolution", "Submitted", "Resolved", "Manager"));
			response.getWriter().println(
					"----------------------------------------------------------------------------------------------------");

			for (List<String> str : results) {
				calendar.setTimeInMillis(Long.parseLong(str.get(4)));
				int month = calendar.get(Calendar.MONTH); // months start at 0, so add 1
				String submitted = ++month + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/"
						+ calendar.get(Calendar.YEAR);
				calendar.setTimeInMillis(Long.parseLong(str.get(5)));
				month = calendar.get(Calendar.MONTH);
				String resolved = ++month + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/"
						+ calendar.get(Calendar.YEAR);
				System.out.println(str.get(2));
				response.getWriter().println(String.format("%-10s $%-10s %-20s %-13s %-12s %-12s %-15s", str.get(0),
						str.get(1), str.get(2), str.get(3), submitted, resolved, str.get(6)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getEmployeeMenu(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		Employee employee = EmployeeService.getEmployee(username);
		try {
			response.sendRedirect(
					"/Project1/Employee.html?=" + username + "?=" + employee.getFname() + " " + employee.getLname());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void approveReimbursement(HttpServletRequest request, HttpServletResponse response) {
		JsonElement jelement = new JsonParser().parse(request.getParameter("data"));
		JsonObject jobject = jelement.getAsJsonObject();
		boolean found = false;

		try {
			int id = Integer.parseInt(jobject.get("id").toString().replace("\"", "")); // catches if letter entered

			// check if reimbursement exists
			List<List<String>> results = ManagerService.getAllPendingReimbursements();
			for (List<String> str : results) {
				if (id == Integer.parseInt(str.get(2))) // id = id in database
					found = true;
			}

			try {
				if (found) { // reimbursement exists
					Employee employee = EmployeeService
							.getEmployee(jobject.get("username").toString().replace("\"", ""));
					ManagerService.approveReimbursement(id, employee.getFname() + " " + employee.getLname());
					response.getWriter().println("Reimbursement " + id + " has been approved.");
					found = false; // reset for later
				} else // reimbursement doesn't exist
					response.getWriter().println("Please enter a valid reimbursement");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (NumberFormatException e) {
			try {
				response.getWriter().println("Please enter a valid remibursement number.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void denyReimbursement(HttpServletRequest request, HttpServletResponse response) {
		JsonElement jelement = new JsonParser().parse(request.getParameter("data"));
		JsonObject jobject = jelement.getAsJsonObject();
		boolean found = false;
		Employee employee = null;

		try {
			int id = Integer.parseInt(jobject.get("id").toString().replace("\"", "")); // catches if letter entered

			// check if reimbursement exists
			List<List<String>> results = ManagerService.getAllPendingReimbursements();
			for (List<String> str : results) {
				if (id == Integer.parseInt(str.get(2))) // id = id in database
					found = true;
			}

			try {
				if (found) { // reimbursement exists
					employee = EmployeeService.getEmployee(jobject.get("username").toString().replace("\"", ""));
					ManagerService.denyReimbursement(id, employee.getFname() + " " + employee.getLname());
					response.getWriter().println("Reimbursement " + id + " has been denied.");
					found = false; // reset for later
				} else
					response.getWriter().println("Please enter a valid remibursement number.");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (NumberFormatException e) {
			try {
				response.getWriter().println("Please enter a valid reimbursement number.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
