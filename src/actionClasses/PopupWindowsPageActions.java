package actionClasses;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PopupWindowsPageActions {
	
	WebDriver driver;
	WebElement popupWindowLink, proceedLink;
	ArrayList<String> tabs;
	
	public PopupWindowsPageActions(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyPopupWindowsPageLaunched() {
		WebElement header = driver.findElement(By.xpath("//h1[text() = 'Popup Windows']"));
		Assert.assertTrue(header.isDisplayed());
		System.out.println("User is on popup windows page");
	}

	public void verifyLinksAreDisplayed() {
		popupWindowLink = driver.findElement(By.xpath("//a[text() = 'Launch Popup Window']"));
		Assert.assertTrue(popupWindowLink.isDisplayed());
		System.out.println("Launch Popup Window link is displayed");
		
		proceedLink = driver.findElement(By.xpath("//a[text() = 'Proceed']"));
		Assert.assertTrue(proceedLink.isDisplayed());
		System.out.println("Proceed link is displayed");
	}

	public void clickOnLaunchPopupWindowLink() {
		popupWindowLink.click();
		System.out.println("Launch Popup Window link clicked");
	}

	public void verifyNewWindowLaunched(String expectedURL) {
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String actualURL = driver.getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL);
		System.out.println("User Launched new Popup Window");
	}

	public void sendKeysToNewWindowsTextFieldAndClickSubmit() {
		driver.findElement(By.id("name")).sendKeys("kunika");
		driver.findElement(By.id("submit")).click();
		driver.switchTo().window(tabs.get(0));
	}

	public void clickProceedLinkOnPreviousWindow() {
		proceedLink.click();
		System.out.println("Proceed link is clicked");
	}

	public void verifyProceedLinkIsClicked(String expectedURL) {
		String actualURLLaunched = driver.getCurrentUrl();
		Assert.assertEquals(expectedURL, actualURLLaunched);
		System.out.println("Proceed link is clicked - Verified");
		System.out.println("User Launched URL: " + actualURLLaunched);
		System.out.println();
	}
}
