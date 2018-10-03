package basictests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MainPage;


public class LoginPage {
	public static MainPage login;
	public static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		login = new MainPage(driver);
		
		Properties props = new Properties();
		FileInputStream in = new FileInputStream("src/main/resources/login.properties");
		props.load(in);
		
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		System.out.println(username);
		System.out.println(password);
		driver.get(url);
		login.getUsername().sendKeys(username);
		login.getPassword().sendKeys(password);
		login.getLoginButton().click();
		Thread.sleep(5000);
		driver.quit();
	}
}
