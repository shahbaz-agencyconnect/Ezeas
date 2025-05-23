package Ezeas.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForWebElementToDisappear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(findBy));
	}
	
	// Date Picker
	public void datePicker(String xPath, String selectYear, String selectMonth, String selectDay, String nextButton)
			throws InterruptedException {

		// Year select

		while (true) {

			List<WebElement> getyears = driver.findElements(By.cssSelector(".ezeas-picker-cell-inner"));
			boolean yearFound = false;

			for (WebElement yearElement : getyears) {
				String year = yearElement.getText();
				if (year.equals(selectYear) || year.equals(null)) {
					yearElement.click();
					yearFound = true;
					break;
				}
			}
			if (yearFound) {
				break;
			} else {
				WebElement nextYearBtn = driver.findElement(By.cssSelector(nextButton));
				nextYearBtn.click();
//				Thread.sleep(1000);
			}
		}

		// Month select
		driver.findElements(By.cssSelector(xPath)).get(Integer.parseInt(selectMonth) - 1).click();

		// Days select
		driver.findElement(By.xpath(selectDay)).click();
//		Thread.sleep(1000);
	}

}
