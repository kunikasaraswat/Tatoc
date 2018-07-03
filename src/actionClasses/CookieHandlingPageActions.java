package actionClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CookieHandlingPageActions {
	
	WebDriver driver;
	WebElement generateTokenLink, proceedLink, emptyToken;
	String emptyTokenValue, tokenWithValue;
	
	public CookieHandlingPageActions(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyCookieHandlingPageLaunched() {
		WebElement header = driver.findElement(By.xpath("//h1[text() = 'Cookie Handling']"));
		Assert.assertTrue(header.isDisplayed());
		System.out.println("User is on cookie handling page");
	}

	public void verifyLinksAreDisplayed() {
		generateTokenLink = driver.findElement(By.xpath("//a[text() = 'Generate Token']"));
		Assert.assertTrue(generateTokenLink.isDisplayed());
		System.out.println("Launch Popup Window link is displayed");
		
		proceedLink = driver.findElement(By.xpath("//a[text() = 'Proceed']"));
		Assert.assertTrue(proceedLink.isDisplayed());
		System.out.println("Proceed link is displayed");
	}

	public void verifyTokenValueFieldIsDisplayed() {
		emptyToken = driver.findElement(By.xpath("//span[@id = 'token']"));
		Assert.assertTrue(emptyToken.isDisplayed());
		System.out.println("Token is displayed");
		emptyTokenValue = emptyToken.getText();
		String value = emptyTokenValue.replaceAll("Token", "");
		emptyTokenValue = emptyToken.getText();
		System.out.println("Value in token is: " + value);
	}

	public void clickGenerateTokenLink() {
		driver.findElement(By.xpath("//a[text() = 'Generate Token']")).click();
		System.out.println("User clicked Generate Token Link");
	}

	public void verifyTokenIsGenerated() {
		tokenWithValue = driver.findElement(By.xpath("//span[@id = 'token']")).getText();
		Assert.assertNotEquals(tokenWithValue, emptyTokenValue);
		System.out.println("Token is generated");
	}

	public void createCookie() {
		String value = tokenWithValue.substring(7);
		Cookie cookie = new Cookie("Token", value);
		driver.manage().addCookie(cookie);
		System.out.println("Cookie is created");
	}

	public void clickProceedLink() {
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
