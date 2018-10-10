package requests;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class RequestHelper {
	public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		if(uri.equals("/Project2/Servlet/getSomething")) 
			response.getWriter().append("[{\"name\":\"Adam\",\"Age\":19},{\"name\":\"Brian\",\"Age\":24},{\"name\":\"Jackie\",\"Age\":23}]");
		else if(uri.equals("/Project2/Servlet/QualityAuditTests")) {
			System.out.println("Run TestNG Quality Audit Tests......");
			TestListenerAdapter adapter = new TestListenerAdapter();
			TestNG testng = new TestNG();
			List<String> suites = new ArrayList<String>();;
			
			//path to xml
			suites.add("C:\\Users\\Administrator\\.jenkins\\workspace\\Project 2\\src\\test\\resources\\testngAuditPage.xml"); 
			testng.setVerbose(10);
			testng.setTestSuites(suites);
			testng.run();
			response.sendRedirect("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.34\\bin\\test-output\\Quality Audit Page\\Test page.html");
		}
		else if(uri.equals("/Project2/Servlet/ProtractorTests")) {
			// execute command from command line
			String line = "Nothing";
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec("cmd /c cd C:\\Users\\Administrator\\.jenkins\\workspace\\Project 2\\protractorTests & protractor conf.js");
//			Process pr = run.exec("cmd /c dir");
		
			try {
				pr.waitFor();
				response.getWriter().println("After process.waitFor()");
			} catch (Exception e) {
				// something went wrong
				response.getWriter().println(e.getMessage());
				BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				while((line = buf.readLine()) != null) {
					
					response.getWriter().println(line);
				}
				e.printStackTrace();
			}
			
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			if(buf.readLine() != null) {
				response.getWriter().println("Buf if");
				while((line = buf.readLine()) != null) {
					System.out.println(line);
					response.getWriter().println(line);
				}
			}
			
			if(line.equals("")) {
				System.out.println("Something bad happened");
				response.getWriter().println("Something bad happened :(");
			}
			
			response.getWriter().println("End line");
		}
	}
}
