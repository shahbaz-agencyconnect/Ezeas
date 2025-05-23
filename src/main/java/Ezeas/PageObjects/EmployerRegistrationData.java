package Ezeas.PageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Ezeas.AbstractComponents.AbstractComponent;

public class EmployerRegistrationData extends AbstractComponent {
	WebDriver driver;

	public EmployerRegistrationData(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[text()='Employer']")
	WebElement selectEmployer;

	@FindBy(xpath = "//span[text()='Register Now and Take Control of Labour Costs!']")
	WebElement registerBtn;

	@FindBy(id = "first_name")
	WebElement enterFname;

	@FindBy(id = "last_name")
	WebElement enterLname;

	@FindBy(id = "email")
	WebElement enterEmail;

	@FindBy(id = "mobile_value")
	WebElement phoneNumber;

	@FindBy(id = "company_name")
	WebElement companyName;

	@FindBy(id = "password")
	WebElement enterPassword;

	@FindBy(id = "password_confirmation")
	WebElement confirmPass;

	@FindBy(id = "Terms")
	WebElement termsOfServices;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitBtn;

	public void employerData() throws IOException, InterruptedException {
		selectEmployer.click();
		Thread.sleep(2000);
		registerBtn.click();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Ezeas//resources//EmployerSignUpData.properties");
		prop.load(fis);
		String firstName = prop.getProperty("firstName");
		String lastName = prop.getProperty("lastName");
		String email = prop.getProperty("email");
		String phone = prop.getProperty("phone");
		String compName = prop.getProperty("companyName");
		String password = prop.getProperty("password");
		enterFname.sendKeys(firstName);
		enterLname.sendKeys(lastName);
		enterEmail.sendKeys(email);
		phoneNumber.sendKeys(phone);
		companyName.sendKeys(compName);
		enterPassword.sendKeys(password);
		confirmPass.sendKeys(password);
		termsOfServices.click();
		submitBtn.click();
	}

}
