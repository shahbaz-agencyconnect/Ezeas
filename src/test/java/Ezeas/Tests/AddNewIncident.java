package Ezeas.Tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import Ezeas.PageObjects.Incident;
import Ezeas.PageObjects.LandingPage;
import Ezeas.TestComponents.BaseTest;

public class AddNewIncident extends BaseTest{
	
	@Test
	public void addIncident() throws IOException, InterruptedException {
		String contactName="Jarrod Russel";
		String incidentType="Behavioural";
		String incidentCategory="Test Incident Pipeline Group";
		String incidentSeverity="Test Incident Pipeline";
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Ezeas//resources//LoginCreds.properties");
		prop.load(fis);
		LandingPage landingPage = new LandingPage(driver);
		landingPage.loginApplication(prop.getProperty("username"), prop.getProperty("password"));
		Incident incident = new Incident(driver);		
		incident.createIncident(contactName,incidentType, incidentCategory,incidentSeverity);
	}

}
