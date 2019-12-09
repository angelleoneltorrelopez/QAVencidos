package pom.farmacia;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Angel Torre
 */
public class Menu extends MetodosGenerales {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@href,'/casas')]")
    WebElement linkCasas;

    @FindBy(xpath = "//aside//div[contains(text(),'Mantenimiento')]")
    WebElement listMantenimiento;

    private void menuMantenimiento(){
        click(listMantenimiento,driver,wait);
    }
    public void subMenuCasas(){
        menuMantenimiento();
        click(linkCasas,driver,wait);
    }


    public Menu (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }
}
