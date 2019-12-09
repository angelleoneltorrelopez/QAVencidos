package Auxiliar;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class TestBase extends MetodosGenerales {
    protected WebDriver driver;

    /**
     * Return the class driver
     *
     * @return: Webdriver object
     */
    protected WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void init(Object[] args) {
        System.out.println(System.getProperty("os.name"));
        switch (System.getProperty("os.name")) {
            case "Windows 10":
                System.out.println("Windows Environment");
                switch (args[0].toString()) {
                    case "Chrome":
                        driver = Drivers.localWindowsChrome();
                        break;
                    default:
                        driver = Drivers.localWindowsFirefox();
                        break;
                }
                break;
            case "Mac OS X":
                System.out.println("MacOS environemnt");
                switch (args[0].toString()) {
                    case "Chrome":
                        driver = Drivers.localMacOSChrome();
                        break;
                    default: //Default browser Firefox
                        driver = Drivers.localMacOSFirefox();
                        break;
                }
                break;
            default:
                System.out.println("Unix/Linux settings");
                switch (args[0].toString()) {
                    case "Chrome":
                        driver = Drivers.localLinuxChrome();
                        break;
                    default: //Default browser Firefox
                        driver = Drivers.locaLinuxFirefox();
                        break;
                }
                break;
        }
    }

    @AfterMethod
    public void close() {
        closeDriver(getDriver());
    }

    @DataProvider(name = "Firefox")
    public Object[][] firefoxDriver() {
        Object[][] driverOption;
        driverOption = new Object[][]{{"Firefox"}};
        return driverOption;
    }

    @DataProvider(name = "Chrome")
    public Object[][] chromeDriver() {
        Object[][] driverOption;
        driverOption = new Object[][]{{"Chrome"}};
        return driverOption;
    }

    @DataProvider(name = "Drivers")
    public Object[][] allDrivers(ITestContext context) {
        Object[][] driverOption;
        switch (context.getCurrentXmlTest().getParameter("browserName")) {
            case "Chrome":
                driverOption = new Object[][]{{context.getCurrentXmlTest().getParameter("browserName")}};
                break;
            default: //Default browser Firefox
                driverOption = new Object[][]{{context.getCurrentXmlTest().getParameter("browserName")}};
                break;
        }
        return driverOption;
    }
}