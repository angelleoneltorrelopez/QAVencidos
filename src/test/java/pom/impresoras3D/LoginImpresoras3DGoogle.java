package pom.impresoras3D;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Rafael Mazariegos
 */

public class LoginImpresoras3DGoogle extends MetodosGenerales {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "a[href='login/google']")
    WebElement botonIngresarCuentagoogle;

    @FindBy(css = "input[type = 'email']")
    WebElement inputCorreo;

    @FindBy(css = "div#identifierNext")
    WebElement botonSiguienteCorreo;

    @FindBy(css = "input[type = 'password']")
    WebElement inputPassword;

    @FindBy(css = "div#passwordNext")
    WebElement botonSiguientePassword;

    public LoginImpresoras3DGoogle(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 10);
        PageFactory.initElements(factory, this);
    }

    public void clickBotonIngresarConCuentaDeGoogle() {
        click(botonIngresarCuentagoogle, driver, wait);
    }

    public void ingresarCorreo(String correo) {
        ingresarTexto(inputCorreo, correo, driver, wait);
    }

    public void clickBotonSiguienteCorreo() {
        click(botonSiguienteCorreo, driver, wait);
    }

    public void ingresarContrasena(String contrasena) {
        ingresarTexto(inputPassword, contrasena, driver, wait);
    }

    public void clickBotonSiguienteContrasena() {
        click(botonSiguientePassword, driver, wait);
    }
}
