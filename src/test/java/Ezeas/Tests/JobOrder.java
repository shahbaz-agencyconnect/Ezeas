package Ezeas.Tests;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JobOrder  {
	public static void main(String[] args) throws InterruptedException {

//		openChrome();
//		userLogin();
		JobOrder jobOrderObj = new JobOrder();
		jobOrderObj.createJobOrderJob();

	}

	public void createJobOrderJob() throws InterruptedException {
		WebDriver driver = null;//Added to remove error
		String jobTitle = "Test Job Order";
		String enterWorksite = "SantaCruize";
		String enterOwner = "Daryl Roob";
		String hiringManager = "Daryl Roob";
		String supervisor = "Daryl Roob";
		String approver = "Daryl Roob";
		String invoiceRecipient = "Daryl Roob";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'EzeasApp__app')]")));
		driver.navigate().to("https://uat.ezeas.com/app/jobs");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class,'plus-small')]")));
		driver.findElement(By.xpath("//i[contains(@class,'plus-small')]")).click();
		driver.findElement(By.xpath("//div[text()='Job Order']")).click();
		driver.findElement(By.id("title")).sendKeys(jobTitle);

		// Select Worksite
		driver.findElement(By.id("worksiteId")).click();
		List<WebElement> worksiteList = driver.findElements(
				By.xpath("//div[@id='worksiteId_list']/following-sibling::div//div[@class='ezeas-space-item']"));
		worksiteList.stream().filter(worksite -> worksite.getText().contains(enterWorksite))
				.forEach(worksite -> worksite.click());

		// Select Owner
		driver.findElement(By.id("primaryContactId")).click();
		List<WebElement> ownerList = driver.findElements(
				By.xpath("//div[@id='primaryContactId_list']/following-sibling::div//div[@class='ezeas-space-item']"));
		List<WebElement> selectOwner = ownerList.stream().filter(owner -> owner.getText().contains(enterOwner))
				.collect(Collectors.toList());
		selectOwner.get(0).click();

		// Select Hiring Manager
		driver.findElement(By.id("hiringManagerId")).click();
		List<WebElement> hiringManagerList = driver.findElements(
				By.xpath("//div[@id='hiringManagerId_list']/following-sibling::div//div[@class='ezeas-space-item']"));
		List<WebElement> selectHM = hiringManagerList.stream().filter(hm -> hm.getText().contains(hiringManager))
				.collect(Collectors.toList());
		selectHM.get(0).click();

		driver.findElement(By.xpath("//span[text()='Direct Sourcing']")).click();
		
		WebElement moveToSupervisor = driver.findElement(By.cssSelector(".fr-element"));
		new Actions(driver).moveToElement(moveToSupervisor).build().perform();
		Thread.sleep(2000);
		// Select Supervisor
		driver.findElement(By.xpath("//div[contains(@aria-describedby,'supervisorIds')]")).click();
		List<WebElement> supervisorList = driver.findElements(
				By.xpath("//div[@id='supervisorIds_list']/following-sibling::div//div[@class='ezeas-space-item']"));
		List<WebElement> selectSupervisor = supervisorList.stream()
				.filter(superVisor -> superVisor.getText().contains(supervisor)).collect(Collectors.toList());
		selectSupervisor.get(0).click();
		driver.findElement(By.id("supervisorIds")).click();
		
		WebElement moveToSourceType = driver.findElement(By.xpath("//label[text()='Approvers']"));
		new Actions(driver).moveToElement(moveToSourceType).build().perform();
		
		// Select Approvers
		driver.findElement(By.id("approverIds")).click();
		List<WebElement> approverList = driver.findElements(
				By.xpath("//div[@id='approverIds_list']/following-sibling::div//div[@class='ezeas-space-item']"));
		List<WebElement> selectApprover = approverList.stream().filter(avr -> avr.getText().contains(approver))
				.collect(Collectors.toList());
		selectApprover.get(0).click();
		driver.findElement(By.id("approverIds")).click();



		// Select Invoice Recipient
		driver.findElement(By.id("approverIds")).click();
		List<WebElement> recipientList = driver.findElements(
				By.xpath("//div[@id='approverIds_list']/following-sibling::div//div[@class='ezeas-space-item']"));
		List<WebElement> selectRecipient = recipientList.stream()
				.filter(recipient -> recipient.getText().contains(invoiceRecipient)).collect(Collectors.toList());
		selectRecipient.get(0).click();
		driver.findElement(By.id("approverIds")).click();


	}

}
