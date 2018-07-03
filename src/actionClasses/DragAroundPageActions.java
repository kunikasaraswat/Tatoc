package actionClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class DragAroundPageActions {
	
	WebDriver driver;
	WebElement dropbox, dragbox;
	
	public DragAroundPageActions(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyDragAroundPageLaunched() {
		WebElement header = driver.findElement(By.xpath("//h1[text() = 'Drag Around']"));
		Assert.assertTrue(header.isDisplayed());
		System.out.println("User is on drag around page");
	}

	public void verifyBoxesAreDisplayed() {
		dropbox = driver.findElement(By.xpath("//div[@id = 'dropbox']"));
		Assert.assertTrue(dropbox.isDisplayed());
		System.out.println("Dropbox is displayed");
		
		dragbox = driver.findElement(By.xpath("//div[@id = 'dragbox']"));
		Assert.assertTrue(dragbox.isDisplayed());
		System.out.println("Dragbox is displayed");
	}

	public void verifyLinksAreDisplayed() {
		WebElement proceedLink = driver.findElement(By.xpath("//a[text() = 'Proceed']"));
		Assert.assertTrue(proceedLink.isDisplayed());
		System.out.println("Link is displayed");
	}

	public void performDragOperation() {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(dragbox, dropbox).build().perform();
		System.out.println("Drag Performed");
	}

	public void clickProceedLink() {
		driver.findElement(By.xpath("//a[text() = 'Proceed']")).click();
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
