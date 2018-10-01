package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import requests.RequestHelper;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		AWSCredentials credentials = new BasicAWSCredentials(
//			"AKIAJCHCJNNXJKJUHY3A",
//			"CviRUpNXf3idlJbdnRJ/dKIGMBBUqoGVKCNyoV5G"	
//		);
//		
//		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
//				.withCredentials(new AWSStaticCredentialsProvider(credentials))
//				.withRegion(Regions.US_EAST_1).build();
//		
//		ObjectListing objectListing = s3client.listObjects("icavanaughproject1");
//		for(S3ObjectSummary os : objectListing.getObjectSummaries())
//			System.out.println(s3client.getUrl("icavanaughproject1", os.getKey()));
		
		
		RequestHelper.process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
