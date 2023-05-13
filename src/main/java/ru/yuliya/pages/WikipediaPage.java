package ru.yuliya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yuliya.utils.CommonMethods;

public class WikipediaPage extends CommonMethods {
    public WikipediaPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = ".//*[@class='mw-logo-container']/img[@alt='Wikipedia']")
    public WebElement logoElement;

    @FindBy(xpath = ".//*[contains(@class,'logo-container')]/img[@alt='The Free Encyclopedia']")
    public WebElement logoElementDescr;

    @FindBy(xpath = ".//h1/span[contains(text(),'Hollywood')]")
    public WebElement pageHeader;
    public WikipediaPage checkIsDisplayed(WebElement webElement) {
        assertElementIsDisplayed(webElement);
        return this;
    }
}
