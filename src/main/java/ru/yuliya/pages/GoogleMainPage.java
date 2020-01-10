package ru.yuliya.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ru.yuliya.utils.CommonMethods;

public class GoogleMainPage extends CommonMethods {
	@FindBy(name = "q")
	private WebElement searchInput;

	private List<WebElement> firsTresult;
	private String xpathLinks = "//div[@class='r']/a/h3";
	private String hintAtt = "title";
	private String hintValue = "Поиск";

	public GoogleMainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public GoogleMainPage checkEmptySearchResults() throws InterruptedException {
		firsTresult = driver.findElements(By.xpath(xpathLinks));
		Assert.assertTrue(firsTresult.size() == 0, "Search result must be 0");
		return this;
	}

	public GoogleMainPage checkInputFieldExist() {
		assertElementIsDisplayed(searchInput);
		return this;
	}

	public GoogleMainPage checkPhraseForHint() {
		checkElementByAttribute(searchInput, hintAtt, hintValue);
		return this;
	}
}
