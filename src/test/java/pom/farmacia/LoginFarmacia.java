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
public class LoginFarmacia extends MetodosGenerales {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "#email")
    WebElement inputEmail;

    @FindBy(css = "#password")
    WebElement inputPassword;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    WebElement botonLogin;

    public LoginFarmacia(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void clickBotonLogin() {
        click(botonLogin, driver, wait);
    }

    public void ingresarEmail(String email) {
        ingresarTexto(inputEmail, email, driver, wait);
    }
    public void ingresarContrasena(String contrasena) {
        ingresarTexto(inputPassword, contrasena, driver, wait);
    }

}