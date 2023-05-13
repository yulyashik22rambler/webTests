package ru.yuliya.utils;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class CommonMethods {
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
	}

	protected Logger log = WebDriverUtils.getLog();

	protected WebDriver driver = WebDriverUtils.getDriver();

	public void assertPageIsOpened(String URL) throws InterruptedException {
		Assert.assertTrue(driver.getCurrentUrl().contains(URL));
	}

	public void openURL(String url) {
		driver.get(url);
	}

	protected void checkImageElement(WebElement element, String elementName, String alt) {
		Assert.assertTrue(element.isDisplayed(), "Element" + elementName + " is not displayed!");
		Assert.assertTrue(element.getAttribute("alt").contains(alt), "Alt text is not " + alt);

	}

	protected void checkLinkElement(WebElement element, String elementName, String link) {
		Assert.assertTrue(element.isDisplayed(), "Element" + elementName + " is not displayed!");
		Assert.assertTrue(element.getAttribute("href").contains(link), "Link is not " + link);
	}

	protected void checkPersonPageHeader(List<String> headers, String path) {
		List<WebElement> headerList = driver.findElements(By.xpath(path));
		for (int i = 0; i < headerList.size(); i++) {
			assertElementIsDisplayedWithText(headerList.get(i), headers.get(i));
		}
	}

	protected void assertElementIsDisplayedWithText(WebElement element, String text) {
		element.isDisplayed();
		log.log(Level.INFO, "Expected value '" + element.getText() + "'" + ", Actual value '" + text + "'");
		Assert.assertEquals(element.getText(), text);
	}

	protected void assertElementIsDisplayed(WebElement element) {
		Assert.assertTrue(element.isDisplayed(), "Element is not displayed");

	}

	protected void sendKeyIntoField(WebElement element, String key) throws InterruptedException {
		element.clear();
		element.sendKeys(key);
		Thread.sleep(1000);
	}

	protected long countSearchResults(String text, List<WebElement> elemntsList) {
		checkWebElementsDisplayedAndPrint(text, elemntsList);
		long count = elemntsList.stream().filter(checkResults(text)).count();
		log.log(Level.INFO, String.valueOf(count));
		return count;
	}

	protected void checkEachElementWithTextIsDisplayed(String word, List<WebElement> elementsList) {
		checkWebElementsDisplayedAndPrint(word, elementsList);
	}

	protected void mouseOver(WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();
	}

	protected <T extends CommonMethods> T returnNewPage(Class<T> classToOpen) {
		return PageFactory.initElements(driver, classToOpen);
	}

	protected void checkElementByAttribute(WebElement searchInput, String attName, String attValue) {
		Assert.assertEquals(searchInput.getAttribute(attName), attValue);
	}

	protected void waitTillElementIsDisplayed(int sec, String elementId) {
		new WebDriverWait(driver, sec).until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
	}

	protected Predicate<WebElement> checkResults(String text) {
		return p -> p.getText().contains(text);
	}

	private void checkWebElementsDisplayedAndPrint(String text, List<WebElement> elementsList) {
		elementsList.stream().filter(checkResults(text)).forEach(e -> log.log(Level.INFO, e.getText()));
		elementsList.stream().filter(checkResults(text)).forEach(e -> e.isDisplayed());
	}
}
