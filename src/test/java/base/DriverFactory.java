package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.Reporter;

public class DriverFactory {

    public static final String BASE_URL = "https://www.edreams.com.ar/";
    private static final String driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\browserDriver\\";
    private static final String firefoxPath = "C:\\Program Files\\Mozilla Firefox\\";

    public static WebDriver OpenBrowser (WebDriver driver, ITestContext context) {
        String browser = context.getCurrentXmlTest().getParameter("Browser");
        switch (browser.toUpperCase()) {
            case "EDGE": {
                Reporter.log("EDGE Browser Selected");
                System.setProperty("webdriver.edge.driver", driverPath+"msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            }
            case "FIREFOX": {
                Reporter.log("FIREFOX Browser Selected");
                System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary(firefoxPath+"firefox.exe");
                driver = new FirefoxDriver(options);
                break;
            }
            default: //chrome browser
            {
                Reporter.log("CHROME Browser Selected");
                System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            }
        }
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        return driver;
    }

    public static void KillBrowser(WebDriver driver){
        Reporter.log("Killing Browser... ");
        driver.quit();
        driver = null;
    }
}
