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

    @FindBy(xpath = ".//*[@class='mw-logo-container']/img[@alt='The Free Encyclopedia']")
    public WebElement logoElementDescr;

    public WikipediaPage checkIsDisplayed(WebElement webElement) {
        assertElementIsDisplayed(webElement);
        return this;
    }
}
