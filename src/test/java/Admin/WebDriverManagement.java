package Admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class WebDriverManagement {

	private static WebDriver webDriver;
    
	private static WebDriver createFirefoxDriver() {
        return new FirefoxDriver();
    }

    private static WebDriver createChromeDriver() {
        File chromeDriverBinary = new File("C:" + File.separator + "Browser_Drivers" + File.separator + "chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriverBinary.getAbsolutePath());

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-save-password-bubble");
        return new ChromeDriver();
    }

    public static WebDriver getFirefoxDriver() {

        if (webDriver == null) {
            webDriver = createFirefoxDriver();
        }

        return webDriver;
    }

    public static WebDriver getChromeDriver() {

        if (webDriver == null) {
            webDriver = createChromeDriver();
        }

        return webDriver;
    }
}
