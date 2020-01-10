package ru.yuliya.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverUtils {

    public static WebDriver driver;
    private static Logger log;

    private static final String PROP_FIREFOX_BINARY = "firefox.binary";
    private static final String PROP_FIREFOX_DRIVER_PATH = "webdriver.gecko.driver";

    private static final String PROP_CHROME_BINARY = "chrome.binary";
    private static final String PROP_CHROME_DRIVER_PATH = "webdriver.chrome.driver";
    private static final String PROP_BROWSER = "browser";
    private static final String PROP_SYSTEM = "system";
    private static final String PROP_SYSTEM_LINUX = "linux";
    private static final String PROP_SYSTEM_WINDOWS = "windows";

    public static WebDriver createAndStartWebService() throws IOException {
        log = Logger.getLogger(Logger.class.getName());
        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream("configuration.properties")) {
            properties.load(inputStream);

            switch (properties.getProperty(PROP_SYSTEM)) {
            case PROP_SYSTEM_LINUX:
                switch (properties.getProperty(PROP_BROWSER)) {
                case "chrome":
                    if (properties.containsKey(PROP_CHROME_DRIVER_PATH)) {
                        driver = new ChromeDriver();
                        System.setProperty(PROP_CHROME_DRIVER_PATH, properties.getProperty(PROP_CHROME_DRIVER_PATH));
                        log.log(Level.INFO, "Begin of Chrome Driver!");
                    }
                    break;
                case "firefox":
                    if (properties.containsKey(PROP_FIREFOX_DRIVER_PATH)) {
                        System.setProperty(PROP_FIREFOX_DRIVER_PATH, properties.getProperty(PROP_FIREFOX_DRIVER_PATH));
                        driver = new FirefoxDriver();
                        log.log(Level.INFO, "Begin of Firefox Driver!");
                    }

                    break;
                default:
                    throw new IllegalArgumentException("Property driver was not defined in config");
                }
                break;
            case PROP_SYSTEM_WINDOWS:
                switch (properties.getProperty(PROP_BROWSER)) {
                case "chrome":
                    if (properties.containsKey(PROP_CHROME_DRIVER_PATH)) {
                        System.setProperty(PROP_CHROME_DRIVER_PATH, properties.getProperty(PROP_CHROME_DRIVER_PATH));
                    }
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (properties.containsKey(PROP_CHROME_BINARY)) {
                        chromeOptions.setBinary(properties.getProperty(PROP_CHROME_BINARY));
                    }
                    driver = new ChromeDriver(chromeOptions);
                    log.log(Level.INFO, "Begin of Chrome Driver!");
                    break;
                case "firefox":
                    if (properties.containsKey(PROP_FIREFOX_DRIVER_PATH)) {
                        System.setProperty(PROP_FIREFOX_DRIVER_PATH, properties.getProperty(PROP_FIREFOX_DRIVER_PATH));
                    }
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addPreference("intl.accept_languages", "ru,en");
                    if (properties.containsKey(PROP_FIREFOX_BINARY)) {
                        firefoxOptions.setBinary(properties.getProperty(PROP_FIREFOX_BINARY));
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    log.log(Level.INFO, "Begin of Firefox Driver!");
                    break;
                default:
                    throw new IllegalArgumentException("Property driver was not defined in config");
                }
                break;
            }
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        log.log(Level.INFO, "WebDriver was opened");
        return driver;
    }

    public static void quitDriver() {
        try {
            log.info("Exit of WebDriver!");
            driver.quit();
        } catch (Exception e) {
            log.log(Level.WARNING, "Exit of WebDriver was failed");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static Logger getLog() {
        return log;
    }

}
