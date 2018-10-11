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
			TestListenerAdapter adapter = new TestListenerAdapter();
			TestNG testng = new TestNG();
			List<String> suites = new ArrayList<String>();;
			
			//path to xml
			suites.add("C:\\Users\\Administrator\\.jenkins\\workspace\\Project 2\\src\\test\\resources\\testngAuditPage.xml"); 
			testng.setVerbose(10);
			testng.setTestSuites(suites);
			testng.setOutputDirectory("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.34\\webapps\\Project2");
			testng.run();
			response.getWriter().println("Tests are complete");
//			response.sendRedirect("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.34\\bin\\test-output\\Quality Audit Page\\Test page.html");
		}
		else if(uri.equals("/Project2/Servlet/ProtractorTests")) {			
			// execute command from command line
			String cmd = "cmd /c start C:/Users/Administrator/Desktop/protractor";
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmd);
			
			try {
				pr.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String str = "", data = "";
			while ((str = buf.readLine()) != null) {
				data += str + "\n";
			}
			// direct filepath to practor output
//			File f = new File("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.34\\webapps\\Project2\\output.txt");
//			
////			File f = new File("C:\\Users\\Ian\\Documents\\workspace-sts-3.9.5.RELEASE\\Project2\\src\\main\\webapp\\this.txt");
//			while(!f.exists()) { // waits for file to be created
//				try {
//					System.out.println("File not found");
//					Thread.sleep(1000); // bad but only way to do it :(
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			System.out.println("File found");
//			BufferedReader br = new BufferedReader(new FileReader(f));
//			String line, data = "";
//			while ((line = br.readLine()) != null) {
//			       data += line + "\n";
//			}
//			
			response.getWriter().append(data);
			
			buf.close();
//			br.close();
//			f.delete();
			pr.destroy();
		}
	}

}
