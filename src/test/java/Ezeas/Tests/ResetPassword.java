package Ezeas.Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import Ezeas.AbstractComponents.EmailLinkExtractor;
import Ezeas.PageObjects.ForgetPassword;
import Ezeas.TestComponents.BaseTest;

public class ResetPassword extends BaseTest{
	
	@Test
	public void requestResetPassword() throws IOException, InterruptedException {
		ForgetPassword forgetPass = new ForgetPassword(driver);
		forgetPass.passwordReset();
		
	}
}
