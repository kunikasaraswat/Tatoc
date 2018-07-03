package testClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actionClasses.CookieHandlingPageActions;
import actionClasses.DragAroundPageActions;
import actionClasses.FrameDungeonPageActions;
import actionClasses.GridGatePageActions;
import actionClasses.HomePageActions;
import actionClasses.PopupWindowsPageActions;

public class TatocBasic {
	
	WebDriver driver;
	HomePageActions homePage;
	GridGatePageActions gridGatePage;
	FrameDungeonPageActions frameDungeonPage;
	DragAroundPageActions dragAroundPage;
	PopupWindowsPageActions popupWindowsPage;
	CookieHandlingPageActions cookieHandlingPage;
	
	@BeforeClass
	public void initVars() {
		driver = new ChromeDriver();
		homePage = new HomePageActions(driver);
		gridGatePage = new GridGatePageActions(driver);
		frameDungeonPage = new FrameDungeonPageActions(driver);
		dragAroundPage = new DragAroundPageActions(driver);
		popupWindowsPage = new PopupWindowsPageActions(driver);
		cookieHandlingPage = new CookieHandlingPageActions(driver);
	}
	
	@Test
	public void Step01_Launch_HomePage() {
		homePage.launchHomePage("http://10.0.1.86/tatoc");
		homePage.verifyHomePageLaunched();
		homePage.verifyLinksDisplayed();
		homePage.clickBasicCourseLink();
		homePage.verifyBasicCourseLinkClicked("http://10.0.1.86/tatoc/basic/grid/gate");
	}
	
	@Test(dependsOnMethods = {"Step01_Launch_HomePage"})
	public void Step02_Launch_GridGatePage() {
		gridGatePage.verifyGridGatePageLaunched();
		gridGatePage.verifyBoxesAreDisplayed();
		gridGatePage.clickGreenBox();
		gridGatePage.verifyGreenBoxIsClicked("http://10.0.1.86/tatoc/basic/frame/dungeon");
	}
	
	@Test(dependsOnMethods = {"Step02_Launch_GridGatePage"})
	public void Step03_Launch_FrameDungeonPage() {
		frameDungeonPage.verifyFrameDungeonPageLaunched();
		frameDungeonPage.verifyBoxesAreDisplayed();
		frameDungeonPage.verifyLinksAreDisplayed();
		frameDungeonPage.matchBoxesColorByClickingRepaintBoxLink();
		frameDungeonPage.clickProceedLink();
		frameDungeonPage.verifyProceedLinkIsClicked("http://10.0.1.86/tatoc/basic/drag");
	}
	
	@Test(dependsOnMethods = {"Step03_Launch_FrameDungeonPage"})
	public void Step04_Launch_DragAround() {
		dragAroundPage.verifyDragAroundPageLaunched();
		dragAroundPage.verifyBoxesAreDisplayed();
		dragAroundPage.verifyLinksAreDisplayed();
		dragAroundPage.performDragOperation();
		dragAroundPage.clickProceedLink();
		dragAroundPage.verifyProceedLinkIsClicked("http://10.0.1.86/tatoc/basic/windows");
	}
	
	@Test(dependsOnMethods = {"Step04_Launch_DragAround"})
	public void Step05_Launch_PopupWindows() {
		popupWindowsPage.verifyPopupWindowsPageLaunched();
		popupWindowsPage.verifyLinksAreDisplayed();
		popupWindowsPage.clickOnLaunchPopupWindowLink();
		popupWindowsPage.verifyNewWindowLaunched("http://10.0.1.86/tatoc/basic/windows/popup");
		popupWindowsPage.sendKeysToNewWindowsTextFieldAndClickSubmit();
		popupWindowsPage.clickProceedLinkOnPreviousWindow();
		popupWindowsPage.verifyProceedLinkIsClicked("http://10.0.1.86/tatoc/basic/cookie");
	}
	
	@Test(dependsOnMethods = {"Step05_Launch_PopupWindows"})
	public void Step06_Launch_CookieHandling() {
		cookieHandlingPage.verifyCookieHandlingPageLaunched();
		cookieHandlingPage.verifyLinksAreDisplayed();
		cookieHandlingPage.verifyTokenValueFieldIsDisplayed();
		cookieHandlingPage.clickGenerateTokenLink();
		cookieHandlingPage.verifyTokenIsGenerated();
		cookieHandlingPage.createCookie();
		cookieHandlingPage.clickProceedLink();
		cookieHandlingPage.verifyProceedLinkIsClicked("http://10.0.1.86/tatoc/end");
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}
}
