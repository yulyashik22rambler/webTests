package ru.yuliya.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ru.yuliya.utils.CommonMethods;

import java.util.List;
import java.util.Optional;

public class GoogleSearchPage extends CommonMethods {

    @FindBy(name = "q")
    private WebElement searchInput;
    @FindBy(xpath = "//div[@class='logo']")
    private WebElement logoElement;
    By googleSearchResultsXpath = By.xpath(".//h3[contains(@class,'MBeuO')]");
    By hintsXpath = By.xpath(".//div[@role='option']");


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
        List<WebElement> searchResultByWord = driver.findElements(googleSearchResultsXpath);
        long count = countSearchResults(expectedText, searchResultByWord);
        Assert.assertTrue(count > 0, "Search result must be more then 0");
        return this;
    }
    public WikipediaPage clickOnResultByText(String text) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> searchResultByWord = driver.findElements(googleSearchResultsXpath);
        Optional<WebElement> first = searchResultByWord.stream().filter(el -> el.getText().equals(text)).findFirst();
        first.get().click();
        return returnNewPage(WikipediaPage.class);
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
        List<WebElement> searchResultByWord = driver.findElements(hintsXpath);
        checkEachElementWithTextIsDisplayed(word, searchResultByWord);
        return this;
    }

    private GoogleSearchPage searchByParameters(String keyWord, Keys key) throws InterruptedException {
        sendKeyIntoField(searchInput, keyWord);
        searchInput.sendKeys(key);
        return this;
    }

    public GoogleSearchPage chooseHint(int hintNumber) {
        List<WebElement> searchResultByWord = driver.findElements(hintsXpath);
        searchResultByWord.get(hintNumber).click();
        return this;
    }

}
