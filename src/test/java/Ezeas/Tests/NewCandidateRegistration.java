package Ezeas.Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import Ezeas.PageObjects.CandidateRegistrationData;
import Ezeas.TestComponents.BaseTest;

public class NewCandidateRegistration extends BaseTest{
	@Test
	public void registerCandidate() throws IOException, InterruptedException {
		CandidateRegistrationData cadidateRegister = new CandidateRegistrationData(driver);
		cadidateRegister.candidateData();
	}

}
