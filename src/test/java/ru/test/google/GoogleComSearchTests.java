package ru.test.google;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import ru.yuliya.pages.GoogleMainPage;
import ru.yuliya.pages.GoogleSearchPage;
import ru.yuliya.pages.WikipediaPage;
import ru.yuliya.utils.InitTestPage;

import java.util.logging.Level;

public class GoogleComSearchTests extends InitTestPage {
    private String url = "https://www.google.com";
    private String urlQ = "https://www.google.com/search?q=q";


    @Issue("Issue-1")
    @Description("Check google search any word")
    @Test(enabled = true, description = "Check search results by send key word")
    public void checkSearchResultsBySendKeyWord() throws InterruptedException {
        GoogleSearchPage queryPage = new GoogleSearchPage(driver);

        pageLogging("Step 1.", "Open the page 'www.google.com'", "The page is opened, url is verified.");
        queryPage.openURL(urlQ);
        queryPage.assertPageIsOpened(urlQ);

        pageLogging("Step 2.", "Search by title 'Hollywood HISTORY'", "Results are verified");
        queryPage.checkInputFieldExist()
                .searchByParameter("Hollywood HISTORY")
                .verifySearchResultsExist("Hollywood")
                .verifySearchResultsExist("Hollywood - Wikipedia");

        pageLogging("Step 3.", "Click on Hollywood - Wikipedia", "Results are verified");
       WikipediaPage wikipediaPage= queryPage.clickOnResultByText("Hollywood - Wikipedia");
       wikipediaPage
               .checkIsDisplayed(wikipediaPage.logoElement)
               .checkIsDisplayed(wikipediaPage.logoElementDescr);
        log.log(Level.INFO, "Test 1 has run!");
    }

    @Issue("Issue-2")
    @Description("Check the browser hint 'Search' which appear when do mouse over the input on google page")
    @Test(enabled = true)
    public void checkHintRiseByMouseOverInput() throws InterruptedException {
        GoogleMainPage mainPage = new GoogleMainPage(driver);

        pageLogging("Step 1.", "Open the page 'www.google.com'", "The page is opened, url is verified.");
        mainPage.openURL(url);
        mainPage.assertPageIsOpened(url);

        pageLogging("Step 2.", "Check the browser hint 'Search' in the input attribute", "The attribute was checked");
        mainPage.checkInputFieldExist()
                .checkPhraseForHint("title","Поиск");
        log.log(Level.INFO, "Test 2 has run!");
    }

    @Issue("Issue-3")
    @Description("After clicking on the logo in the upper-left part of the page the empty page is displayed")
    @Test(enabled = true)
    public void getEmptyPageByClickOnLogo() throws InterruptedException {
        GoogleSearchPage queryPage = new GoogleSearchPage(driver);
        pageLogging("Step 1.", "Open the page 'www.google.com'", "The page is opened, url is verified.");
        queryPage.openURL(urlQ);
        queryPage.assertPageIsOpened(urlQ);

        pageLogging("Step 2.", "Click on Loge", "Get empty page");
        queryPage.checkLogoExist();
        GoogleMainPage mainPage = queryPage.clickOnLogo();
        mainPage.assertPageIsOpened(url);
        mainPage.checkEmptySearchResults();
        log.log(Level.INFO, "Test 3 has run!");
    }

    @Issue("Issue-4")
    @Description("Check drop down hints by typing word 'hg push'")
    @Test(enabled = true)
    public void checkDropDownByTypingWord() throws InterruptedException {
        GoogleSearchPage queryPage = new GoogleSearchPage(driver);

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
