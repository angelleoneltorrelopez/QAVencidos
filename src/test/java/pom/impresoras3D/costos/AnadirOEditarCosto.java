package pom.impresoras3D.costos;

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

public class AnadirOEditarCosto extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "input#nombre")
    WebElement inputNombre;

    @FindBy(css = "input#cantidad")
    WebElement inputCantidad;

    @FindBy(css = "input#valor_unitario")
    WebElement inputValorUnitario;

    @FindBy(css = "input#depreciacion")
    WebElement inputDepreciacion;

    @FindBy(css = "select#esDirecto")
    WebElement selectTipoDeCosto;

    @FindBy(css = "button.btn.btn-success")
    WebElement botonGuardar;

    public AnadirOEditarCosto(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void ingresarNombre(String nombre) {
        ingresarTexto(inputNombre, nombre, driver, wait);
    }

    public void ingresarCantidad(String cantidad) {
        ingresarTexto(inputCantidad, cantidad, driver, wait);
    }

    public void ingresarValorUnitario(String valorUnitario) {
        ingresarTexto(inputValorUnitario, valorUnitario, driver, wait);
    }

    public void ingresarDepreciacion(String depreciacion) {
        ingresarTexto(inputDepreciacion, depreciacion, driver, wait);
    }

    public void seleccionarTipoDeCosto(String tipoDeCosto) {
        seleccionarOpcionPorTexto(selectTipoDeCosto, tipoDeCosto, driver, wait);
    }

    public void clickBotonGuardar() {
        click(botonGuardar, driver, wait);
    }
}
