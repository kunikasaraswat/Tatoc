package actionClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class GridGatePageActions {

	WebDriver driver;
	
	public GridGatePageActions(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyGridGatePageLaunched() {
		WebElement header = driver.findElement(By.xpath("//h1[text() = 'Grid Gate']"));
		Assert.assertTrue(header.isDisplayed());
		System.out.println("User is on grid gate page");
	}

	public void verifyBoxesAreDisplayed() {
		WebElement greenBox = driver.findElement(By.xpath("//div[@class = 'greenbox']"));
		Assert.assertTrue(greenBox.isDisplayed());
		System.out.println("Green Box is displayed");

		WebElement redBox = driver.findElement(By.xpath("//div[@class = 'redbox']"));
		Assert.assertTrue(redBox.isDisplayed());
		System.out.println("Red Box is displayed");
	}

	public void clickGreenBox() {
		driver.findElement(By.xpath("//div[@class = 'greenbox']")).click();
		System.out.println("Greenbox is clicked");
	}

	public void verifyGreenBoxIsClicked(String expectedURL) {
		String actualURLLaunched = driver.getCurrentUrl();
		Assert.assertEquals(expectedURL, actualURLLaunched);
		System.out.println("Green Box is clicked - Verified");
		System.out.println("User Launched URL: " + actualURLLaunched);
		System.out.println();
	}
}
