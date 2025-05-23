package Ezeas.PageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ezeas.AbstractComponents.AbstractComponent;

public class CandidateRegistrationData extends AbstractComponent{
	WebDriver driver;

	public CandidateRegistrationData(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath  = "//h2[text()='Worker']")
	WebElement selectWorker;
	
	@FindBy(xpath  = "//a[@href='/register?role=Candidate']")
	WebElement signUp;

	@FindBy(id  = "first_name")
	WebElement enterFirstName;
	
	@FindBy(id  = "last_name")
	WebElement enterLastName;
	
	@FindBy(id  = "email")
	WebElement enterEmai;
	
	@FindBy(id  = "mobile_value")
	WebElement enterPhone;
	
	@FindBy(id  = "password")
	WebElement enterPass;
	
	@FindBy(id  = "password_confirmation")
	WebElement confirmPass;
	
	@FindBy(id  = "referral_code")
	WebElement enterRefferalCode;
	
	@FindBy(id  = "Terms")
	WebElement selectTerms;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement registerCandidate;
	
	
	public void candidateData() throws IOException {
		selectWorker.click();
		signUp.click();
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//Ezeas//resources//CandidateSignUpData.properties");
		prop.load(fis);
		String firstName = prop.getProperty("firstName");
		String lastName = prop.getProperty("lastName");
		String email = prop.getProperty("email");
		String phone = prop.getProperty("phone");
		String password = prop.getProperty("password");
		enterFirstName.sendKeys(firstName);
		enterLastName.sendKeys(lastName);
		enterEmai.sendKeys(email);
		enterPhone.sendKeys(phone);
		enterPass.sendKeys(password);
		confirmPass.sendKeys(password);
		selectTerms.click();
		registerCandidate.click();
		

	}

}
