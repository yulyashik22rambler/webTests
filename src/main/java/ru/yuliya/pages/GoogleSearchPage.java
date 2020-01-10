package ru.yuliya.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ru.yuliya.utils.CommonMethods;

public class GoogleSearchPage extends CommonMethods {

	@FindBy(name = "q")
	private WebElement searchInput;

	@FindBy(xpath = "//div[@class='logo']")
	private WebElement logoElement;

	private List<WebElement> searchResultbyWord;

	public GoogleSearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public GoogleSearchPage searchByParameter(String keyWord) throws InterruptedException {
		searchByParameters(keyWord, Keys.ENTER);
		return this;
	}

	public GoogleSearchPage verifySearchResultsExist(String expectedText) throws InterruptedException {
		Thread.sleep(3000);
		searchResultbyWord = driver.findElements(By.xpath("//div[@class='r']/a/h3"));
		long count = countSearchResults(expectedText, searchResultbyWord);
		Assert.assertTrue(count > 0, "Search result must be more then 0");
		return this;
	}

	public GoogleSearchPage checkLogoExist() {
		assertElementIsDisplayed(logoElement);
		return this;
	}

	public GoogleMainPage clickOnLogo() {
		logoElement.click();
		return returnNewPage(GoogleMainPage.class);
	}

	public GoogleSearchPage checkInputFieldExist() {
		assertElementIsDisplayed(searchInput);
		return this;
	}

	public GoogleSearchPage searchBySearchWord(String keyWord) throws InterruptedException {
		sendKeyIntoField(searchInput, keyWord);
		Thread.sleep(1000);
		return this;
	}

	public GoogleSearchPage checkHintsInDropDown(String word) {
		searchResultbyWord = driver.findElements(By.xpath("//div[@class='suggestions-inner-container']//span"));
		checkEachElementWithTextIsDisplayed(word, searchResultbyWord);
		return this;
	}

	private GoogleSearchPage searchByParameters(String keyWord, Keys key) throws InterruptedException {
		sendKeyIntoField(searchInput, keyWord);
		searchInput.sendKeys(key);
		return this;
	}

}
