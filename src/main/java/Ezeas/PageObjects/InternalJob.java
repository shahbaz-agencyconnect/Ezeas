package Ezeas.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ezeas.AbstractComponents.AbstractComponent;

public class InternalJob extends AbstractComponent {
	WebDriver driver;

	public InternalJob(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'EzeasApp__app')]")
	WebElement ezeasApp;

	@FindBy(xpath = "//i[contains(@class,'plus-small')]")
	WebElement addJobBtn;

	@FindBy(xpath = "(//div[@class='Jobs_job_type_title__ZVKh_'])[2]")
	WebElement selectInternalJob;

	@FindBy(id = "title")
	WebElement title;

	@FindBy(id = "worksiteId")
	WebElement selectWorksite;

	@FindBy(id = "positionId")
	WebElement clickPositionLookup;

	@FindBy(xpath = "//div[@title='Test Position']")
	WebElement selectPosition;

	@FindBy(id = "industryId")
	WebElement industry;

	@FindBy(xpath = "//span[text()='Amusement, Events and Recreation']")
	WebElement selectIndustry;

	@FindBy(xpath = "//span[text()='Select Licenses']")
	WebElement induction;
	

	@FindBy(xpath = "//span[text()='Full Time']")
	WebElement selectemploymentStatus;

	@FindBy(xpath = "//span[text()='Hourly Rate']")
	WebElement selectHourlyRate;
	
	@FindBy(id = "remunerationMin")
	WebElement minHourlyRate;
	
	@FindBy(id = "remunerationMax")
	WebElement maxHourlyRate;	

	@FindBy(id = "dateFrom")
	WebElement startDate;

	@FindBy(xpath = "//div[text()='26']")
	WebElement selectDate;

	@FindBy(xpath = "//span[text()='Save']")
	WebElement saveBtn;

	public void createInternalJob() {
		waitForWebElementToAppear(ezeasApp);
		driver.findElement(By.xpath("//div[text()='Jobs']")).click();
		waitForWebElementToAppear(addJobBtn);
		addJobBtn.click();
		waitForWebElementToAppear(selectInternalJob);
		selectInternalJob.click();
		title.sendKeys("Test Job");
		selectWorksite.click();
		String worksite = "Recruit Complete";
		List<WebElement> worksiteList = driver.findElements(By.xpath("//div[contains(@class,'option-content')]"));
		worksiteList.stream().filter(expectedWorksite -> expectedWorksite.getText().contains(worksite))
				.forEach(expectedWorksite -> expectedWorksite.click());
		clickPositionLookup.click();
		selectPosition.click();
		industry.click();
		selectIndustry.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", induction);
		waitForWebElementToAppear(induction);
		selectemploymentStatus.click();
		selectHourlyRate.click();
		waitForWebElementToAppear(minHourlyRate);
		minHourlyRate.sendKeys("2");
		maxHourlyRate.sendKeys("10");
		startDate.click();
		waitForWebElementToAppear(selectDate);
		selectDate.click();
		saveBtn.click();

	}

}
