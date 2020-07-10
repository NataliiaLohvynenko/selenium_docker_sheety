package chromeTests;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class StandaloneTest extends WebDriver {

    RemoteWebDriver driver;

    @Parameters("browser")
    @Test
    public void mainTest(@Optional("chrome") String browser) throws MalformedURLException {
       driver = getDriver(browser);
       driver.get("https://www.google.com/");
       System.out.println(driver.getTitle());
       driver.quit();
    }
}
