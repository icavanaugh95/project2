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
			String cmd = "cmd /c call C:/Users/Administrator/Desktop/protractor";
			String line = "";
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmd);
			
			try {
				pr.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			while((line = buf.readLine()) != null) {
				System.out.println(line);
				response.getWriter().println(line);
			}

			
			response.getWriter().println("End line");
		}
	}
}
