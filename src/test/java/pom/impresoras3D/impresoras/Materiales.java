package pom.impresoras3D.impresoras;

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

public class Materiales extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "button.btn.btn-info.btn-xs")
    WebElement botonAgregarNuevoMaterial;

    @FindBy(css = "tbody button#deleteBtn")
    WebElement botonBorrarMaterial;

    @FindBy(css = "button#modalAccept")
    WebElement botonAceptarEliminarMaterial;

    public Materiales(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public boolean existeBotonAgregarNuevoMaterial() {
        return existeWebElement(botonAgregarNuevoMaterial, driver, wait);
    }

    public void clickBotonAgregarNuevoMaterial() {
        click(botonAgregarNuevoMaterial, driver, wait);
    }

    public boolean existeMaterial() {
        return existeWebElement(botonBorrarMaterial, driver, wait);
    }

    public boolean existeMaterial(String nombreMaterial) {
        String xpath = "//tbody//td[contains(text(), '" + nombreMaterial + "')]";
        return existeXpath(xpath, driver, wait);
    }

    public boolean existeMaterial(String nombreMaterial, String tipoDeMaterial, String unidadDeMedida, String consumible, String precioUnitario, String precioPorConsumo) {
        String xpath = "//tbody//td[contains(text(), '" + nombreMaterial + "')]/..//td[contains(text(), '" + tipoDeMaterial + "')]/..//td[contains(text(), '" + unidadDeMedida + "')]/..//td[contains(text(), '" + consumible + "')]/..//td[contains(text(), '" + precioUnitario + "')]/..//td[contains(text(), '" + precioPorConsumo + "')]";
        System.out.println("Buscando material con xpath: " + xpath);
        return existeXpath(xpath, driver, wait);
    }

    public boolean existeBotonEditarMaterial(String nombreMaterial) {
        String xpath = "//tbody//td[contains(text(), '" + nombreMaterial + "')]/..//button[contains(@class, 'warning')]";
        return existeXpath(xpath, driver, wait);
    }

    public void clickBotonEditarMaterial(String nombreMaterial) {
        String xpath = "//tbody//td[contains(text(), '" + nombreMaterial + "')]/..//button[contains(@class, 'warning')]";
        clickXpathViaJS(xpath, driver, wait);
    }

    public boolean existeBotonBorrarMaterial(String nombreMaterial) {
        String xpath = "//tbody//td[contains(text(), '" + nombreMaterial + "')]/..//button[contains(@class, 'danger')]";
        return existeXpath(xpath, driver, wait);
    }

    public void clickBotonBorrarMaterial() {
        click(botonBorrarMaterial, driver, wait);
    }

    public void clickBotonBorrarMaterial(String nombreMaterial) {
        String xpath = "//tbody//td[contains(text(), '" + nombreMaterial + "')]/..//button[contains(@class, 'danger')]";
        clickXpathViaJS(xpath, driver, wait);
    }

    public void clickBotonAceptarEliminar() {
        click(botonAceptarEliminarMaterial, driver, wait);
    }
}
