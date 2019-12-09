package pom.impresoras3D.usuarios;

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

public class Usuarios extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "button.btn.btn-info.btn-xs")
    WebElement botonAgregarNuevoUsuario;

    public Usuarios(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public boolean existeBotonAgregarNuevoUsuario() {
        return existeWebElement(botonAgregarNuevoUsuario, driver, wait);
    }

    public void clickBotonAgregarNuevoUsuario() {
        click(botonAgregarNuevoUsuario, driver, wait);
    }

    public boolean existeBotonEditarUsuario(String nombre) {
        String xpath = "//tbody//td[contains(text(), '" + nombre + "')]/following-sibling::td//span[@class = 'fa fa-pencil']";
        return existeXpath(xpath, driver, wait);
    }

    public void clickBotonEditarUsuario(String nombre) {
        String xpath = "//tbody//td[contains(text(), '" + nombre + "')]/following-sibling::td//span[@class = 'fa fa-pencil']";
        clickXpathViaJS(xpath, driver, wait);
    }

    public boolean existeBotonBorrarUsuario(String nombre) {
        String xpath = "//tbody//td[contains(text(), '" + nombre + "')]/following-sibling::td//button[@class = 'btn btn-danger']";
        return existeXpath(xpath, driver, wait);
    }

    public void clickBotonBorrarUsuario(String nombre) {
        String xpath = "//tbody//td[contains(text(), '" + nombre + "')]/following-sibling::td//button[@class = 'btn btn-danger']";
        WebElement boton = encontrarElementoXpath(xpath, driver, wait);
        clickConAlerta(boton, driver, wait);
    }

    public boolean existeUsuario(String nombre) {
        String xpath = "//tbody//td[contains(text(), '" + nombre + "')]";
        return existeXpath(xpath, driver, wait);
    }

    public boolean existeUsuario(String nombre, String correo, String rol) {
        String xpath = "//tbody//td[contains(text(), '" + nombre + "')]/following-sibling::td[1][contains(text(), '" + correo + "')]/following-sibling::td[1][contains(text(), '" + rol + "')]";
        return existeXpath(xpath, driver, wait);
    }
}
