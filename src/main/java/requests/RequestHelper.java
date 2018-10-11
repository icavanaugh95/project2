package requests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
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
			TestNG testng = new TestNG();
			List<String> suites = new ArrayList<String>();;
			
			//path to xml
			suites.add("C:\\Users\\Administrator\\.jenkins\\workspace\\Project 2\\src\\test\\resources\\testngAuditPage.xml"); 
			testng.setVerbose(10);
			testng.setTestSuites(suites);
			testng.setOutputDirectory("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.34\\webapps\\Project2");
			testng.run();
			
			File f = new File("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.34\\bin\\test-output\\Quality Audit Page\\Test page.html");
			
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = "", data = "";
			while((line = br.readLine()) != null) {
				data += line + "\n";
			}
			response.getWriter().append(data);
//			response.getWriter().println("Tests are complete");
//			response.sendRedirect("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.34\\bin\\test-output\\Quality Audit Page\\Test page.html");
		}
		else if(uri.equals("/Project2/Servlet/ProtractorTests")) {			
			// execute command from command line

//			String cmd = "cmd /c protractor C:\\Users\\Ian\\Documents\\workspace-sts-3.9.5.RELEASE\\Project2\\protractorTests\\conf.js";

			String cmd = "cmd /c chdir C:\\Users\\Administrator\\.jenkins\\workspace\\Project 2\\protractorTests & C:\\Users\\Administrator\\AppData\\Roaming\\npm\\protractor conf.js";
			Runtime run = Runtime.getRuntime();
			System.out.println("Before exe");
			Process pr = run.exec(cmd);
			System.out.println("After run");
			
			try {
				pr.waitFor();
				System.out.println("After for");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream())); // gets protractor output
			String str = "", data = "";
			while ((str = buf.readLine()) != null) {
				data += str + "\n"; // puts in a formatted string
			}
		
			response.getWriter().append(data); // send protractor data
			
			buf.close();
			pr.destroy();
		}
	}

}
