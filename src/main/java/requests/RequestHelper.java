package requests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Employee;
import service.EmployeeService;


public class RequestHelper {
	public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);

		switch (uri) {
		case "/Project1/Servlet/employeeMenu": // For manager to view the employee menu
			ManagerRequestHelper.getEmployeeMenu(request, response);
			break;

		case "/Project1/Servlet/getAllEmployees":
			ManagerRequestHelper.getAllEmployees(response);
			break;

		case "/Project1/Servlet/getAllPendingReimbursements": // Get all pending reimbursements
			ManagerRequestHelper.getAllPendingReimbursements(response);
			break;

		case "/Project1/Servlet/getAllResolvedReimbursements": // Get all approved reimbursements
			ManagerRequestHelper.getAllResolvedReimbursements(response);
			break;

		case "/Project1/Servlet/approveReimbursement": // Approve reimbursement
			ManagerRequestHelper.approveReimbursement(request, response);
			break;

		case "/Project1/Servlet/denyReimbursement": // Deny reimbursement
			ManagerRequestHelper.denyReimbursement(request, response);
			break;

		case "/Project1/Servlet/getEmployeeReimbursements": // Get 1 employee pending reimbursements
			EmployeeRequestHelper.getEmployeeReimbursements(request, response);
			break;

		case "/Project1/Servlet/getEmployeeResolvedReimbursements": // Get 1 employee resolved reimbursements
			EmployeeRequestHelper.getEmployeeResolvedReimbursements(request, response);
			break;

		case "/Project1/Servlet/updateEmployee": // update username/password
			EmployeeRequestHelper.updateEmployee(request, response);
			break;

		case "/Project1/Servlet/submitReimbursement": // submit reimbursement
			EmployeeRequestHelper.submitReimbursement(request, response);
			break;
			
		case "/Project1/Servlet/Login": // Login
			login(request, response);
			break;
		}
		
	}

	public static void login(HttpServletRequest request, HttpServletResponse response) {
		JsonElement jelement = new JsonParser().parse(request.getParameter("data"));
		JsonObject jobject = jelement.getAsJsonObject();
		String username = jobject.get("username").toString();
		username = username.replace("\"", "");
		String password = jobject.get("password").toString();
		password = password.replace("\"", "");
		Employee employee = EmployeeService.getEmployee(username);

		try {
			if (employee.getUsername() != null) { // check username
				if (employee.getPassword().equals(password)) { // check password
					if (employee.getPriv() == 1) // check priv
						response.sendRedirect("/Project1/Manager.html?=" + username + "?=" + employee.getFname() + " "
								+ employee.getLname());
					else
						response.sendRedirect("/Project1/Employee.html?=" + username + "?=" + employee.getFname() + " "
								+ employee.getLname());
				} else
					response.getWriter().println("Bad username/password");
			} else
				response.getWriter().println("Bad username/password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
