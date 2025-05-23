package Ezeas.Tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import Ezeas.PageObjects.LandingPage;
import Ezeas.PageObjects.OnDemandJob;
import Ezeas.TestComponents.BaseTest;

public class AddOnDemandJob extends BaseTest {

	@Test
	public void addNewDemandjob() throws IOException, InterruptedException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Ezeas//resources//LoginCreds.properties");
		prop.load(fis);
		LandingPage landingPage = new LandingPage(driver);
		landingPage.loginApplication(prop.getProperty("username"), prop.getProperty("password"));
		OnDemandJob demandJob = new OnDemandJob(driver);
		demandJob.createOnDemandJob();

	}

}
