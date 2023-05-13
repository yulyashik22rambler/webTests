package ru.test.google;

import java.util.logging.Level;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import ru.yuliya.pages.GoogleMainPage;
import ru.yuliya.pages.GoogleSearchPage;
import ru.yuliya.utils.InitTestPage;

public class GoogleComPageTest extends InitTestPage {
    private String url = "https://www.google.com";
    private String urlQ = "https://www.google.com/search?q=q";
    private GoogleSearchPage queryPage;
    private GoogleMainPage mainPage;

    @Issue("Issue-1")
    @Description("Check google search by title and surname, name and middle name")
    @Test(enabled = true, description="Check search results by send key word")
    public void checkSearchResultsBySendKeyWord() throws InterruptedException {
        queryPage = new GoogleSearchPage(driver);

        pageLogging("Step 1.", "Open the page 'www.google.com'", "The page is opened, url is verifeied.");
        queryPage.openURL(urlQ);
        queryPage.assertPageIsOpened(urlQ);

        pageLogging("Step 2.", "Search by title 'Sberbank'", "Results are verified");
        queryPage.checkInputFieldExist()
                .searchByParameter("Sberbank")
                .verifySearchResultsExist("Сбербанк")
                .verifySearchResultsExist("для физических лиц");

        pageLogging("Step 3.", "Search by title 'German Gref'", "Results are verified");
        queryPage.searchByParameter("German Gref Oskarovich")
                .verifySearchResultsExist("Герман Греф")
                .verifySearchResultsExist("Греф Герман Оскарович");
        log.log(Level.INFO, "Test 1 has run!");
    }

    @Issue("Issue-2")
    @Description("Check the browser hint 'Поиск' which appear when do mouse over the input on google page")
    @Test(enabled = true)
    public void checkHintRiseByMouseOverTnput() throws InterruptedException {
        mainPage = new GoogleMainPage(driver);

        pageLogging("Step 1.", "Open the page 'www.google.com'", "The page is opened, url is verified.");
        mainPage.openURL(url);
        mainPage.assertPageIsOpened(url);

        pageLogging("Step 2.", "Check the browser hint 'Поиск' in the input attribute", "The attribute was checked");
        mainPage.checkInputFieldExist().checkPhraseForHint();
        log.log(Level.INFO, "Test 2 has run!");
    }

    @Issue("Issue-3")
    @Description("After clicking on the logo in the upper-left part of the page the empty page is displayed")
    @Test(enabled = true)
    public void getEmptyPageByCklickOnLogo() throws InterruptedException {
        queryPage = new GoogleSearchPage(driver);
        pageLogging("Step 1.", "Open the page 'www.google.com'", "The page is opened, url is verified.");
        queryPage.openURL(urlQ);
        queryPage.assertPageIsOpened(urlQ);

        pageLogging("Step 2.", "Click on Loge", "Get empty page");
        queryPage.checkLogoExist();
        mainPage = queryPage.clickOnLogo();
        mainPage.assertPageIsOpened(url);
        mainPage.checkEmptySearchResults();
        log.log(Level.INFO, "Test 3 has run!");
    }

    @Issue("Issue-4")
    @Description("Check drop down hints by typyng word 'hg push'")
    @Test(enabled = true)
    public void checkDropDownByTypingWord() throws InterruptedException {
        queryPage = new GoogleSearchPage(driver);

        pageLogging("Step 1.", "Open the page 'www.google.com'", "The page is opened, url is verifeied.");
        queryPage.openURL(urlQ);
        queryPage.assertPageIsOpened(urlQ);

        pageLogging("Step 2.", "Write a word and check google input hints", "Рints were checked");
        queryPage.checkInputFieldExist()
                .searchBySearchWord("hg push")
                .checkHintsInDropDown("hg push")
                .chooseHint(3);
        log.log(Level.INFO, "Test 4 has run!");
    }

}
