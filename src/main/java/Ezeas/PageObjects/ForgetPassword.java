package Ezeas.PageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ezeas.AbstractComponents.AbstractComponent;
import Ezeas.AbstractComponents.EmailLinkExtractor;

public class ForgetPassword extends AbstractComponent {
	WebDriver driver;

	public ForgetPassword(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[text()='Worker']")
	WebElement selectWorker;

	@FindBy(xpath = "//a[@href='/login']")
	WebElement loginBtn;

	@FindBy(xpath = "//a[@href='/forgot-password']")
	WebElement forgotPass;

	@FindBy(id = "email")
	WebElement emailInput;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement sendBtn;

	public void passwordReset() throws IOException, InterruptedException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Ezeas//resources//forgotPassword.properties");
		prop.load(fis);
		String email = prop.getProperty("email");
		selectWorker.click();
		loginBtn.click();
		forgotPass.click();
		Thread.sleep(2000);
		emailInput.sendKeys(email);
		sendBtn.click();
		Thread.sleep(5000);
		String userEmail = "anand.mahindra4578@gmail.com";
		String appPassCode= "aayu nhuf xvxs chdk";
		String subject ="Reset Password";
		String regexPattern="https://stg\\.ezeas\\.com/account/reset-password/[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+";

		EmailLinkExtractor emailLinkExtract = new EmailLinkExtractor();
		String link = emailLinkExtract.getAttachedLink(userEmail, appPassCode, subject, regexPattern);
		driver.navigate().to(link);
		driver.findElement(By.id("password")).sendKeys("Admin@321");
		driver.findElement(By.id("confirmPassword")).sendKeys("Admin@321");
		driver.findElement(By.xpath("//span[text()='Reset Password']")).click();
	}

}
