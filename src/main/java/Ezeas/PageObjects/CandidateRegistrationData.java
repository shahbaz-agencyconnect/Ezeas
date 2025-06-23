package Ezeas.PageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.util.Assert;

import Ezeas.AbstractComponents.AbstractComponent;
import Ezeas.AbstractComponents.EmailLinkExtractor;

public class CandidateRegistrationData extends AbstractComponent {
	WebDriver driver;

	public CandidateRegistrationData(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[text()='Worker']")
	WebElement selectWorker;

	@FindBy(xpath = "//a[@href='/register?role=Candidate']")
	WebElement signUp;

	@FindBy(id = "first_name")
	WebElement enterFirstName;

	@FindBy(id = "last_name")
	WebElement enterLastName;

	@FindBy(id = "email")
	WebElement enterEmai;

	@FindBy(id = "mobile_value")
	WebElement enterPhone;

	@FindBy(id = "password")
	WebElement enterPass;

	@FindBy(id = "password_confirmation")
	WebElement confirmPass;

	@FindBy(id = "referral_code")
	WebElement enterRefferalCode;

	@FindBy(id = "Terms")
	WebElement selectTerms;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement registerCandidate;

	@FindBy(css = ".ezeas-modal-content")
	WebElement registrationSuccess;

	@FindBy(id = "refresh")
	WebElement refreshIcon;

	@FindBy(css = "input#dateOfBirth")
	WebElement dateOfBirth;

	@FindBy(css = ".rc-virtual-list")
	WebElement showAddress;

	@FindBy(id = "__googleAddress_description")
	WebElement googleAddress;

	@FindBy(xpath = "//span[text()='Save']")
	WebElement save_btn;

	@FindBy(xpath = "//gmp-advanced-marker[@title='Location']")
	WebElement map_icon;

	@FindBy(xpath = "//h5[text()='Warehouse & Distribution Centre Roles']")
	WebElement jobApplyFor;

	@FindBy(xpath = "//span[text()='Register Interest']")
	WebElement registerBtn;

	@FindBy(xpath = "//span[text()='Ok']")
	WebElement confirmCongrats;

	Properties prop = new Properties();

	public void candidateData() throws IOException, InterruptedException {
		selectWorker.click();
		signUp.click();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Ezeas//resources//CandidateSignUpData.properties");
		prop.load(fis);
		String firstName = prop.getProperty("firstName");
		String lastName = prop.getProperty("lastName");
		String email = prop.getProperty("email");
		String phone = prop.getProperty("phone");
		String password = prop.getProperty("password");
		String userEmail = "anand.mahindra4578@gmail.com";
		String appPassCode= "aayu nhuf xvxs chdk";
		String subject ="Great Job Opportunities";
		String regexPattern="https://stg\\.ezeas\\.com/account/confirm-registration/[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\\\.[A-Za-z0-9-_]+";
		enterFirstName.sendKeys(firstName);
		enterLastName.sendKeys(lastName);
		enterEmai.sendKeys(email);
		enterPhone.sendKeys(phone);
		enterPass.sendKeys(password);
		confirmPass.sendKeys(password);
		selectTerms.click();
		registerCandidate.click();
//		emailVerification();
		EmailLinkExtractor emailLinkExtractor = new EmailLinkExtractor();
		String link = emailLinkExtractor.getAttachedLink(userEmail,appPassCode,subject,regexPattern);
//		driver.switchTo().newWindow(WindowType.TAB);

		driver.navigate().to(link);
		waitForWebElementToAppear(dateOfBirth);
//		Thread.sleep(10000);
		completeProfile();
	}

	public void emailVerification() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String verifyEmail = prop.getProperty("email");
		waitForWebElementToAppear(registrationSuccess);
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://yopmail.com/");
		driver.findElement(By.id("login")).sendKeys(verifyEmail);
		driver.findElement(By.id("login")).sendKeys(Keys.ENTER);
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		waitForWebElementToAppear(refreshIcon);
//		Thread.sleep(3000);
//		driver.navigate().refresh();
		
		
		boolean elementFound = false;
		int maxRetries = 5;
		int currentRetry = 0;

		while (!elementFound && currentRetry < maxRetries) {

			try {
				WebElement mailFrame = driver.findElement(By.id("ifmail"));
				driver.switchTo().frame(mailFrame);
				By tagetElementLocator = By.cssSelector("a[href*='confirm-registration']");
				WebElement targetElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(tagetElementLocator));
				elementFound = true;
				targetElement.click();
				Set<String> windowsHandler = driver.getWindowHandles();
				Iterator<String> iterator = windowsHandler.iterator();
				parentId = iterator.next();
				childId = iterator.next();
				String grantChild = iterator.next();
				driver.switchTo().window(grantChild);

			} catch (TimeoutException e) {
				currentRetry++;
				if (currentRetry <= maxRetries) {
					driver.navigate().refresh();
					Thread.sleep(2000);
				}
			}
		}
	}

	public void completeProfile() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String year = "1995";
		String month = "07";
		String day = "15";
		String dayPath = "//div[text()='" + day + "']";
		String nextBtn = ".ezeas-picker-header-super-prev-btn";
		String xpath = ".ezeas-picker-cell-inner";
		waitForWebElementToAppear(dateOfBirth);
		dateOfBirth.click();
		driver.findElement(By.cssSelector("button[aria-label='Choose a year']")).click();
		datePicker(xpath, year, month, dayPath, nextBtn);
		googleAddress.click();
		googleAddress.sendKeys("BlueskyCreations");
		waitForWebElementToAppear(showAddress);
		googleAddress.sendKeys(Keys.ENTER);
		save_btn.click();
		jobApplyFor.click();
		registerBtn.click();
		confirmCongrats.click();
	}
}
