package Ezeas.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	public void scrollDown() {
		try {
 

            // Initialize JavascriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // --- Example: Finding "Grade 1" element ---
            // This element is nested under "Usher" which is likely off-screen initially.
            // We need to scroll "Usher" into view first, or directly scroll "Grade 1"

            // 1. Find the parent element "Usher" first (optional, but good for understanding hierarchy)
            // If you know the exact XPath for "Grade 1", you can skip finding "Usher" first
            WebElement usherElement = driver.findElement(By.xpath("//span[@title='Usher']"));

            // Scroll the "Usher" element into view.
            // Using true aligns the top of the element with the top of the viewport.
            // Using false would align the bottom of the element with the bottom of the viewport.
            js.executeScript("arguments[0].scrollIntoView(true);", usherElement);

            // Add a small wait to allow the page to render content after scrolling
            Thread.sleep(1000); 

            // Now, find the "Grade 1" element after scrolling its parent into view
            WebElement grade1Element = driver.findElement(By.xpath("//span[@title='Grade 2 - 17 Years']"));
            
            // You can also directly scroll to "Grade 1" if you have its specific XPath
            // js.executeScript("arguments[0].scrollIntoView(true);", grade1Element);

            // Now you can interact with grade1Element, e.g., click it
//            grade1Element.click();
//            System.out.println("Clicked on: " + grade1Element.getText());


            // --- General approach for elements loaded on scroll ---

            // Let's say you want to find "Ticket seller" which might be further down.
            // You might not know its exact XPath initially if it's dynamic.
            // You'll need a robust locator (XPath, CSS selector, etc.) for the element you want to reach.

            // Example for "Ticket seller":
            WebElement ticketSellerElement = null;
            try {
            	
				WebElement targetElementLocator = driver.findElement(
						By.xpath("(//div[contains(@class,'ezeas-select-tree-treenode-switcher-close')])[9]"));
//				WebElement targetElementLocator = driver.findElement(By.cssSelector(".ezeas-select-tree-list-holder-inner"));
//				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", targetElementLocator);
                // Use WebDriverWait to wait for the element to be present and visible
                // This is crucial for elements that load dynamically on scroll
                ticketSellerElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Introductory - 19 Years']")));
                
                // Once found, scroll it into view
//                js.executeScript("arguments[0].scrollIntoView(true);", ticketSellerElement);
                Thread.sleep(500); // Give it a moment to stabilize

                System.out.println("Found and scrolled to: " + ticketSellerElement.getText());
                // Now you can interact with ticketSellerElement
                 ticketSellerElement.click(); 
            } catch (Exception e) {
                System.out.println("Element 'Ticket seller' not found or not visible after scrolling: " + e.getMessage());
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
//            driver.quit();
        }
	}
	
	

}
