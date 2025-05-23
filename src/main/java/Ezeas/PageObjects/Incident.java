package Ezeas.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ezeas.AbstractComponents.AbstractComponent;

public class Incident extends AbstractComponent {
	WebDriver driver;

	public Incident(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[contains(@class,'EzeasApp__app')]")
	WebElement ezeasApp;
	@FindBy(css = "[class*='plus-small']")
	WebElement addIncidentBtn;

	@FindBy(id = "contactId")
	WebElement clickContact;

	@FindBy(id = "incidentTypeId")
	WebElement clickIncidentType;

	@FindBy(id = "incidentCategoryId")
	WebElement clickIncidentCategory;

	@FindBy(id = "incidentSeverityId")
	WebElement clickIncidentSeverity;

	@FindBy(id = "summary")
	WebElement enterSummary;

	@FindBy(id = "personInvolved")
	WebElement personInvolved;

	@FindBy(id = "date")
	WebElement selectDatePicker;

	@FindBy(css = "[aria-label='Choose a year']")
	WebElement chooseYear;

	public void createIncident(String contactName, String incidentType, String incidentCategory,
			String incidentSeverity) throws InterruptedException {
		String xPath = ".ezeas-picker-cell-inner";
		String selectYear = "2025";
		String selectMonth = "6";
		String selectDate = "17";
		String enterDateXpath = "//div[text()=" + selectDate + "]";
		String nextYearFromBtnXpath = ".date-from .ezeas-picker-header-super-next-btn";
		waitForWebElementToAppear(ezeasApp);
		driver.findElement(By.xpath("//div[text()='Incident']")).click();
//		driver.navigate().to("https://uat.ezeas.com/app/incident");
		addIncidentBtn.click();
		clickContact.click();
		driver.findElement(By.xpath("//div[text()='" + contactName + "']")).click();
		clickIncidentType.click();
		driver.findElement(By.xpath("//span[text()='" + incidentType + "']")).click();
		clickIncidentCategory.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//span[text()='"+incidentCategory+"']"))).build()
				.perform();
		driver.findElement(By.xpath("//span[text()='" + incidentCategory + "']")).click();

		clickIncidentSeverity.click();
		driver.findElement(By.xpath("//span[text()='" + incidentSeverity + "']")).click();
		enterSummary.sendKeys("This is a test summary");
		personInvolved.sendKeys("Shahbaz");
		selectDatePicker.click();
		chooseYear.click();
		datePicker(xPath, selectYear, selectMonth, enterDateXpath, nextYearFromBtnXpath);
		driver.findElement(By.xpath("//span[text()='Save']")).click();
	}

}
