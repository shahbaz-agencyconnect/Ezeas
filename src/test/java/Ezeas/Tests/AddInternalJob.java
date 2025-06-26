package Ezeas.Tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import Ezeas.PageObjects.InternalJob;
import Ezeas.PageObjects.LandingPage;
import Ezeas.TestComponents.BaseTest;

public class AddInternalJob extends BaseTest{
	
	@Test
	public void addNewInternalJob() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Ezeas//resources//LoginCreds.properties");
		prop.load(fis);
		LandingPage landingPage = new LandingPage(driver);
		landingPage.loginApplication(prop.getProperty("username"), prop.getProperty("password"));
		InternalJob internalJob = new InternalJob(driver);
		internalJob.createInternalJob();
		
	}

}
