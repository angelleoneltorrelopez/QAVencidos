package pom.impresoras3D.roles;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Roles extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    public Roles(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void clickBotonAsignarPermisos(String nombreRol) {
        String xpath = "(//td[contains(text(), '" + nombreRol + "')]/following-sibling::td)[last()]//a[contains(@title, 'signar')]";
        clickXpathViaJS(xpath, driver, wait);
    }
}
