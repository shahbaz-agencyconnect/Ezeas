package Ezeas.PageObjects;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Ezeas.AbstractComponents.AbstractComponent;
import dev.failsafe.internal.util.Assert;

public class OnDemandJob extends AbstractComponent {
	WebDriver driver;

	public OnDemandJob(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'EzeasApp__app')]")
	WebElement ezeasApp;

	@FindBy(xpath = "//i[contains(@class,'plus-small')]")
	WebElement addJobBtn;

	@FindBy(xpath = "//div[text()='On Demand']")
	WebElement selectOnDemand;

	@FindBy(id = "worksiteId")
	WebElement selectWorksite;

	@FindBy(id = "position")
	WebElement selectPosition;

	@FindBy(css = "show-placeholder")
	WebElement descriptionElement;

	public void createOnDemandJob() throws InterruptedException, IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//Ezeas//resources//OnDemandJobDataProvider.properties");
		prop.load(fis);
		String worksite = prop.getProperty("worksite");
		String award = prop.getProperty("award");
		String position = prop.getProperty("position");
		String designation = prop.getProperty("designation");
		;
		String quantity = prop.getProperty("quantity");
		String fromYear = prop.getProperty("fromYear");
		String fromMonth = prop.getProperty("fromMonth");
		String enterFromDay = prop.getProperty("enterFromDay");
		String fromDay = "//div[contains(@class,'date-from')]//td[@class='ezeas-picker-cell ezeas-picker-cell-in-view']//div[text()='"
				+ enterFromDay + "']";
		String monthFromXpath = ".ezeas-picker-cell-inner";
		String nextYearFromBtnXpath = ".date-from .ezeas-picker-header-super-next-btn";
		String toYear = prop.getProperty("toYear");
		String toMonth = prop.getProperty("toMonth");
		String enterToDay = prop.getProperty("enterToDay");
		String toDay = "//div[contains(@class,'date-to')]//td[@class='ezeas-picker-cell ezeas-picker-cell-in-view']//div[text()='"
				+ enterToDay + "']";
		String monthToXpath = ".date-to .ezeas-picker-cell-inner";
		String nextYearToBtnXpath = ".date-to .ezeas-picker-header-super-next-btn";
		waitForWebElementToAppear(ezeasApp);
		driver.navigate().to("https://uat.ezeas.com/app/jobs");
		waitForWebElementToAppear(addJobBtn);
		addJobBtn.click();
		waitForWebElementToAppear(selectOnDemand);
		selectOnDemand.click();

		// Select Worksite
		selectWorksite.click();
		List<WebElement> worksiteList = driver.findElements(By.xpath("//div[contains(@class,'option-content')]"));
		worksiteList.stream().filter(expectedWorksite -> expectedWorksite.getText().contains(worksite))
				.forEach(expectedWorksite -> expectedWorksite.click());

		// Select Award
		selectPosition.click();
//		Thread.sleep(1000);
		while (true) {
			List<WebElement> awardList = driver
					.findElements(By.cssSelector(".ezeas-select-tree-list-holder-inner .ezeas-select-tree-title"));
			boolean awardFound = false;

			for (int i = 0; i < awardList.size(); i++) {
				String expectedAward = awardList.get(i).getText();

				if (expectedAward.equals(award)) {
					driver.findElement(with(By.tagName("span")).toLeftOf(awardList.get(i))).click();
					awardFound = true;
					break;
				}
			}
			if (awardFound) {
				break;
			} else {

				Actions actions = new Actions(driver);
				WebElement element = driver.findElement(By.cssSelector("[class*='scrollbar-thumb']"));
				actions.clickAndHold(element).moveByOffset(0, 100).release().perform();
			}
		}
//		Thread.sleep(1000);
		// Select Position

		while (true) {
			List<WebElement> positions = driver
					.findElements(By.xpath("//span[contains(@class,'content-wrapper-close')]//span"));
			boolean positionFound = false;
			for (WebElement positionElement : positions) {
				String expectedPosition = positionElement.getText();
				if (expectedPosition.equals(position)) {
					driver.findElement(with(By.tagName("span")).toLeftOf(positionElement)).click();
					positionFound = true;
					break;
				}
			}
			if (positionFound) {
				break;
			} else {

				Actions actions = new Actions(driver);
				actions.moveToElement(driver.findElement(By.xpath("//span[text()='"+position+"']"))).build()
				.perform();
//				WebElement sourceElement = driver.findElement(By.cssSelector("[class*='scrollbar-thumb']"));
//				actions.clickAndHold(sourceElement).moveByOffset(4, 10).release().perform();
				waitForWebElementToAppear(driver.findElement(By.xpath("//span[text()='" + position + "']")));
				driver.findElement(By.xpath("//span[text()='" + position + "']")).click();
				break;
			}
		}
		waitForWebElementToDisappear(descriptionElement);
//		Thread.sleep(2000);

		WebElement quantityElement = driver.findElement(By.cssSelector("[title='Inductions']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", quantityElement);
		waitForWebElementToAppear(quantityElement);
		driver.findElement(By.id("quantity")).click();
		driver.findElement(By.id("quantity")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.id("quantity")).sendKeys(quantity);

		WebElement moveToRepeat = driver.findElement(By.xpath("//label[@for='statusName']"));
		new Actions(driver).moveToElement(moveToRepeat).build().perform();
		driver.findElement(By.id("date_dateFrom")).click();
		driver.findElement(By.xpath("//button[@aria-label='Choose a year']")).click();
		datePicker(monthFromXpath, fromYear, fromMonth, fromDay, nextYearFromBtnXpath);
		driver.findElement(By.cssSelector(".date-to .ezeas-picker-year-btn")).click();
		datePicker(monthToXpath, toYear, toMonth, toDay, nextYearToBtnXpath);

		// Select Shift Time
		List<WebElement> getShiftDays = driver.findElements(By.xpath("//td[@role='button']"));
		for (int i = 0; i < getShiftDays.size(); i++) {
			getShiftDays.get(i).click();

		}
//		driver.findElement(By.xpath("//span[text()='Save']")).click();

	}

}
