package requests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Employee;
import service.EmployeeService;

public class EmployeeRequestHelper {
	public static void getEmployeeReimbursements(HttpServletRequest request, HttpServletResponse response) {
		Employee employee = EmployeeService.getEmployee(request.getParameter("username"));
		List<List<String>> results = EmployeeService.getPendingReimbursements(employee);

		try {
			if (employee.getUsername() == null) // check if entered username in database
				response.getWriter()
						.println("Employee username " + request.getParameter("username") + " does not exist");
			else {
				if (results.isEmpty()) // no reimbursements
					response.getWriter().println("No pending reimbursements");
				else { // response with formatted output
					Calendar calendar = Calendar.getInstance();
					response.getWriter().println(String.format("	%-10s %-12s %-12s %-15s %-15s", "Number", "Amount",
							"Date", "Reason", "Receipt"));
					response.getWriter().println("------------------------------------------------------------------");

					for (List<String> str : results) {
						String url = "       <a href=#                       ></a>";
						if (!str.get(4).equals("0")) // only changes url if there is one present
							url = "<a href=\"" + str.get(4) + "\"target=\"_blank\">Receipt</a>"; // formatted anchor for
																									// html

						calendar.setTimeInMillis(Long.parseLong(str.get(2)));
						int month = calendar.get(Calendar.MONTH);
						response.getWriter()
								.println(String.format("%-10s $%-11s %-12s %-15s %-1s", str.get(0), str.get(1), ++month
										+ "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR),
										str.get(3), url));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void getEmployeeResolvedReimbursements(HttpServletRequest request, HttpServletResponse response) {
		Employee employee = EmployeeService.getEmployee(request.getParameter("username"));
		List<List<String>> results = EmployeeService.getResolvedReimbursements(employee);

		try {
			if (results.isEmpty())
				response.getWriter().println("No resolved reimbursements");
			else {	
				Calendar calendar = Calendar.getInstance();

				response.getWriter().println(String.format("%-10s %-20s %-10s %-13s %-12s %-12s %-15s", "Number",
						"Reason", "Amount", "Resolution", "Submitted", "Resolved", "Manager"));
				response.getWriter().println(
						"--------------------------------------------------------------------------------------------------");

				for (List<String> str : results) {
					calendar.setTimeInMillis(Long.parseLong(str.get(4)));
					int month = calendar.get(Calendar.MONTH); // months start at 0, so add 1
					String submitted = ++month + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/"
							+ calendar.get(Calendar.YEAR);
					calendar.setTimeInMillis(Long.parseLong(str.get(5)));
					month = calendar.get(Calendar.MONTH);
					String resolved = ++month + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/"
							+ calendar.get(Calendar.YEAR);
					response.getWriter().println(String.format("%-10s %-20s $%-10s %-13s %-12s %-12s %-15s", str.get(0),
							str.get(1), str.get(2), str.get(3), submitted, resolved, str.get(6)));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		JsonElement jelement = new JsonParser().parse(request.getParameter("data"));
		JsonObject jobject = jelement.getAsJsonObject();
		String username = jobject.get("olduser").toString();
		String password = jobject.get("oldpass").toString();
		username = username.replace("\"", "");
		password = password.replace("\"", "");
		Employee employee = EmployeeService.getEmployee(username);

		try {
			if (employee.getUsername() != null) {
				if (employee.getPassword().equals(password)) {
					// continue updating employee info
					employee.setUsername(jobject.get("newuser").toString().replace("\"", ""));
					employee.setPassword(jobject.get("newpass").toString().replace("\"", ""));
					if (EmployeeService.updateEmployeeInfo(employee))
						response.getWriter().println("Account updated successfully");
					else
						response.getWriter().println("An error occured please try again");
				} else
					response.getWriter().println("Bad username/password");
			} else
				response.getWriter().println("Bad username/password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void submitReimbursement(HttpServletRequest request, HttpServletResponse response) {
		JsonElement jelement = new JsonParser().parse(request.getParameter("data"));
		JsonObject jobject = jelement.getAsJsonObject();
		String filename, fileData, s3url = "0";

		// getting file data throws exception when no receipt is sent
		try {
			fileData = jobject.get("file").toString().replace("\"", "");
			filename = "" + System.currentTimeMillis();
			s3url = uploadToS3(fileData, filename);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		String username = jobject.get("username").toString().replace("\"", "");
		String reason = jobject.get("reason").toString().replace("\"", "");
		double amount = Double.parseDouble(jobject.get("amount").toString().replace("\"", ""));
		Employee employee = EmployeeService.getEmployee(username);
		
		EmployeeService.submitReimbursement(employee, reason, amount, s3url);
		try {
			response.getWriter().println("Reimbursement submitted");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
