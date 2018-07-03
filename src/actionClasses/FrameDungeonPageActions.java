package actionClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class FrameDungeonPageActions {
	
	WebDriver driver;
	
	public FrameDungeonPageActions(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyFrameDungeonPageLaunched() {
		WebElement header = driver.findElement(By.xpath("//h1[text() = 'Frame Dungeon']"));
		Assert.assertTrue(header.isDisplayed());
		System.out.println("User is on frame dungeon page");
	}

	public void verifyBoxesAreDisplayed() {
		driver.switchTo().frame("main");
		WebElement boxOne = driver.findElement(By.xpath("//div[text() = 'Box 1']"));
		Assert.assertTrue(boxOne.isDisplayed());
		System.out.println("Box 1 is displayed");
		
		driver.switchTo().frame("child");
		WebElement boxTwo = driver.findElement(By.xpath("//div[text() = 'Box 2']"));
		Assert.assertTrue(boxTwo.isDisplayed());
		System.out.println("Box 2 is displayed");
		driver.switchTo().defaultContent();
	}

	public void verifyLinksAreDisplayed() {
		driver.switchTo().frame("main");
		WebElement linkOne = driver.findElement(By.xpath("//a[text() = 'Repaint Box 2']"));
		Assert.assertTrue(linkOne.isDisplayed());
		WebElement linkTwo = driver.findElement(By.xpath("//a[text() = 'Proceed']"));
		Assert.assertTrue(linkTwo.isDisplayed());
		System.out.println("Page Links are displayed");
	}

	public void matchBoxesColorByClickingRepaintBoxLink() {
		String boxOneColor = driver.findElement(By.xpath("//div[text() = 'Box 1']")).getAttribute("class");
		driver.switchTo().frame("child");
		String boxTwoColor = driver.findElement(By.xpath("//div[text() = 'Box 2']")).getAttribute("class");
		while (!boxOneColor.equals(boxTwoColor)) {
			driver.switchTo().parentFrame();
			driver.findElement(By.linkText("Repaint Box 2")).click();
			driver.switchTo().frame("child");
			boxTwoColor = driver.findElement(By.id("answer")).getAttribute("class");
		}
		Assert.assertEquals(boxOneColor, boxTwoColor);
		System.out.println("Boxes Color Matches");
		driver.switchTo().defaultContent();
	}

	public void clickProceedLink() {
		driver.switchTo().frame("main");
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
