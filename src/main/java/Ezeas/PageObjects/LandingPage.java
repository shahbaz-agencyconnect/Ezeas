package Ezeas.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Ezeas.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[text()='Accept']")
	WebElement acceptCookies;

	@FindBy(xpath = "//*[text()='Worker']")
	WebElement selectWorker;

	@FindBy(xpath = "//a[@href='/login']")
	WebElement loginBtn;

	@FindBy(id = "email")
	WebElement userEmail;

	@FindBy(id = "password")
	WebElement userPassword;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement userLogin;

	public void loginApplication(String username, String password) {
		selectWorker.click();
		loginBtn.click();
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		userLogin.click();
	}

	public void goTo() throws InterruptedException {
//		driver.get("https://uat.ezeas.com/?_vercel_share=HMLdENdv4FmQbd5QMkYBzRrWtIGj5Hx8");
//		driver.get("https://uat.ezeas.com/?_vercel_share=AHBOfYR1KhHe3kswlFO49kpBUEPfEKJQ");
		driver.get("https://stg.ezeas.com/?_vercel_share=opfKH8a1EmpU8TOmyptXLYzEMTffqEds");
		
		waitForWebElementToAppear(acceptCookies);
		acceptCookies.click();
	}

}
