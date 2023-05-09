package ru.yuliya.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ru.yuliya.utils.CommonMethods;

import java.util.List;

public class GoogleMainPage extends CommonMethods {
	@FindBy(name = "q")
	private WebElement searchInput;
	private By linksXpath = By.xpath("//div[@class='r']/a/h3");
	private String hintAtt = "title";
	private String hintValue = "Поиск";

	public GoogleMainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public GoogleMainPage checkEmptySearchResults() throws InterruptedException {
		List<WebElement> firstResult = driver.findElements(linksXpath);
		Assert.assertTrue(firstResult.size() == 0, "Search result must be 0");
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
