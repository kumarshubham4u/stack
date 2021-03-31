package resources;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;

public class BaseTest {

	public static WebDriver driver=null;
	public Properties prop;
	public static final String USERNAME = "kumarshubham6";
	public static final String AUTOMATE_KEY = "RpCymy7LgDJBBsc3kuXh";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	@Parameters({ "os", "os_version", "browser", "browser_version" })
	@BeforeMethod
	public void initializeBrowser(String os, String os_version, String browser, String browser_version)
			throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("os", os);
		caps.setCapability("os_version", os_version);
		caps.setCapability("browser", browser);
		caps.setCapability("browser_version", browser_version);
		caps.setCapability("browserstack.debug", "true");
		caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		caps.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

		URL browserStackUrl = new URL(URL);

		driver = new RemoteWebDriver(browserStackUrl, caps);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}

	@AfterMethod
	public void teardown() {

		// closing driver
		driver.close();
	}

	public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}
	


}
