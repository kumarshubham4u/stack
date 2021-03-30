package testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPasswordPage;
import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.BaseTest;

public class Testcase1 extends BaseTest {
	
	public WebDriver driver;
	private static Logger log=LogManager.getLogger(BaseTest.class.getName());
	@BeforeMethod
	public void initialize() throws IOException {

		driver = initializeBrowser();// Browser Initialization
		log.info("Driver is initialised");
		driver.get(prop.getProperty("url"));// hitting the url
		log.info("Hitting the url");

	}

	@Test(dataProvider = "credentials") // passing DataProvider values into the test case
	public void Login(String email, String password) throws InterruptedException {

		HomePage objHome = new HomePage(driver); // creating object of HomePage class
		LoginPage objLogin = new LoginPage(driver);// creating object of LoginPage class
		ForgotPasswordPage objFP=new ForgotPasswordPage(driver);
		
		log.debug("Clicking on Login link");
		objHome.LoginLink().click();
		log.info("Login Link is clicked");
		
		
		log.debug("Sending keys in email field");
		objLogin.EmailId().sendKeys(email);
		log.info("Email id is provided");
		
		log.debug("Sending keys in password field");
		objLogin.Password().sendKeys(password);
		log.info("password is provided");
		
		log.debug("Clicking on Login button");
		objLogin.LoginButton().click();
		log.info("Login button is clicked");
		
		log.debug("Clicking on Forgot Password button");
		objLogin.ForgotPassword().click();
		log.info("Forgot Password button is clicked");
		
		log.debug("Sending keys in email field");
		objFP.EmailId().sendKeys("kumarshubham2994");
		log.info("Email id is provided");
		
		log.debug("Clicking on Send Me Instruction button");
		objFP.SendMeInstructionButton().click();
		log.info("Send Me Instruction button is clicked");
	}

	@AfterMethod
	public void teardown() {
		
		//closing driver
		driver.close();
	}

	// Creating a Data Provider method
	@DataProvider(name = "credentials")
	public Object[][] getData() {
		return new Object[][] { { "kumarshubham2994@gmail.com", "Shubham@4u" },
				{ "kumarshubham143@gmail.com", "Loveisevol@4u" } };

	}
	

}
