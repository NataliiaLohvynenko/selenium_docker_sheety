package chromeTests;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class WebDriver {

    private static RemoteWebDriver driver;
    private static URL url;
    private static DesiredCapabilities ds;

       static Supplier<RemoteWebDriver> chromeDriver = ()-> {
            try {
                url = new URL("http://localhost:4444/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            ds = DesiredCapabilities.chrome();

            return driver = new RemoteWebDriver(url, ds);
        };


        static Supplier<RemoteWebDriver> fireFoxDriver = ()-> {
            try {
                url = new URL("http://localhost:4444/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            ds = DesiredCapabilities.firefox();

            return driver = new RemoteWebDriver(url, ds);
        };

        static Supplier<RemoteWebDriver> OperaDriver = ()-> {
        try {
            url = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        ds = DesiredCapabilities.operaBlink();

        return driver = new RemoteWebDriver(url, ds);
    };


        static Map<String, Supplier<RemoteWebDriver>> driverMap = new HashMap<>();

        static{driverMap.put("chrome",chromeDriver);
            driverMap.put("firefox",fireFoxDriver);
            driverMap.put("opera",OperaDriver);

        }

    public RemoteWebDriver getDriver(String browser){
        return driverMap.get(browser).get();
    }

    @BeforeSuite
    public void startDockerFile() throws IOException, InterruptedException {

        DockerComposeRun dc = new DockerComposeRun();
        dc.DockerComposeRunToOutput("dockerUp.bat", "Registering the node to the hub");
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start scaleChrome.bat");
        Thread.sleep(3000);
    }

    @AfterSuite
    public void stopDockerFile() throws IOException, InterruptedException {
        DockerComposeRun dc = new DockerComposeRun();
        dc.DockerComposeRunToOutput("dockerComposeDown.bat", "INFO stopped: selenium-hub");
        File f = new File("output.txt");
        Thread.sleep(7000);
        System.out.println("after dockerCompose Run " +f.delete());
    }



}
