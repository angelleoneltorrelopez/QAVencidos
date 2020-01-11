package Auxiliar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;


public class Drivers {
    public static String directorioDescargas;

    public static WebDriver locaLinuxFirefox(){
        System.setProperty("webdriver.gecko.driver", "Drivers/Linux/Firefox/geckodriver");
        directorioDescargas = System.getProperty("user.dir")+"/downloads/";
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        return driver;
    }

    public static WebDriver localLinuxChrome(){
        System.setProperty("webdriver.chrome.driver","Drivers/Linux/Chrome/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver localWindowsFirefox(){
        System.setProperty("webdriver.gecko.driver", "Drivers\\Windows\\Firefox\\geckodriver.exe");
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        return driver;
    }

    public static WebDriver localWindowsChrome(){
        String pathDescarga = System.getProperty("user.dir")+"\\downloads\\";
        System.out.println("Dir Descarga:"+pathDescarga);

        System.setProperty("webdriver.chrome.driver","Drivers\\Windows\\Chrome\\chromedriver.exe");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", pathDescarga);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        ChromeDriver driver= new ChromeDriver(options);

        return driver;
    }

    public static WebDriver localMacOSFirefox(){
        System.setProperty("webdriver.gecko.driver", "Drivers/macOS/Firefox/geckodriver");
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        return driver;
    }

    public static WebDriver localMacOSChrome(){
        System.setProperty("webdriver.chrome.driver","Drivers/macOS/Chrome/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        return driver;
    }
}
