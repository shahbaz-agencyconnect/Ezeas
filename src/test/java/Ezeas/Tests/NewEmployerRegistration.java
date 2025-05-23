package Ezeas.Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Ezeas.PageObjects.EmployerRegistrationData;
import Ezeas.TestComponents.BaseTest;

public class NewEmployerRegistration extends BaseTest{
	@Test
	public void registerEmployer() throws IOException, InterruptedException {
		EmployerRegistrationData employerRegister = new EmployerRegistrationData(driver);
		employerRegister.employerData();	

	}

}
