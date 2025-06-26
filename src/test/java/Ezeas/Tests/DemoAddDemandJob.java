package Ezeas.Tests;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import org.bouncycastle.pqc.jcajce.provider.xmss.XMSSMTSignatureSpi.withSha256;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Ezeas.PageObjects.LandingPage;
import Ezeas.TestComponents.BaseTest;

public class DemoAddDemandJob extends BaseTest {

	@Test
	public void addDemandJob() throws IOException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Ezeas//resources//LoginCreds.properties");
		prop.load(fis);
		LandingPage landingPage = new LandingPage(driver);
		landingPage.loginApplication(prop.getProperty("username"), prop.getProperty("password"));
		driver.findElement(By.xpath("//div[text()='Jobs']")).click();
		driver.findElement(By.xpath("//i[contains(@class,'plus-small')]")).click();
		driver.findElement(By.cssSelector(".Jobs_job_type_card__w8g0N")).click();
		driver.findElement(By.id("position")).click();

		// Select Award
		List<WebElement> awards = driver.findElements(By.cssSelector(".ezeas-select-tree-title"));
		for (WebElement award : awards) {
			String expectedAward = award.getText();
			System.out.println(expectedAward);
			if (expectedAward.equals("Storage Services Award")) {
				WebElement selectAward = driver.findElement(with(By.tagName("span")).toLeftOf(award));
				selectAward.click();
				break;
			}

		}

		// Select Position
		while (true) {
			List<WebElement> positions = driver.findElements(By.cssSelector(".ezeas-select-tree-title"));
			boolean positionFound = false;
			for (WebElement position : positions) {

				String expectedPosition = position.getText();
				if (expectedPosition.equals("XWholesale Employee")) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Usher']")));
					WebElement selectPosition = driver.findElement(with(By.tagName("span")).toLeftOf(position));
					selectPosition.click();
					while(true) {
						boolean designationFound=false;
						List<WebElement> designations=driver.findElements(By.cssSelector(".ezeas-select-tree-title"));
						for(WebElement designation:designations) {
						String design =designation.getText();
						if(design.equals("Level 1")) {
							driver.findElement(By.xpath("//span[@title='Level 1']")).click();
							designationFound = true;
							break;
						}
						}
						if(designationFound) {
							break;
						}else {
						try {
							WebElement targetElementLocator3=driver.findElement(By.xpath("(//span[@class='ezeas-select-tree-title'])[10]"));
							js.executeScript("arguments[0].scrollIntoView(true);", targetElementLocator3);

						}catch (Exception e) {
							// TODO: handle exception
						}
						}

					
				}}
			}
			if (positionFound) {
				break;
			} else {try {
				WebElement targetElementLocator2 = driver.findElement(
						By.xpath("(//div[contains(@class,'ezeas-select-tree-treenode-switcher-close')])[9]"));
				js.executeScript("arguments[0].scrollIntoView(true);", targetElementLocator2);
			}catch (Exception e) {
				// TODO: handle exception
			}}
		}

	}
}
