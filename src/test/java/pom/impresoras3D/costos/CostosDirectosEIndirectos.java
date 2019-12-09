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

public class CostosDirectosEIndirectos extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "button.btn.btn-info.btn-xs")
    WebElement botonAgregarNuevoCosto;

    public CostosDirectosEIndirectos(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public boolean existeBotonAgregarNuevoCosto() {
        return existeWebElement(botonAgregarNuevoCosto, driver, wait);
    }

    public void agregarNuevoCosto() {
        click(botonAgregarNuevoCosto, driver, wait);
    }

    public boolean existeBotonEditarCosto(String nombreCosto) {
        String xpath = "//tbody//td[contains(text(), '" + nombreCosto + "')]/..//button[contains(@title, 'ditar')]";
        return existeXpath(xpath, driver, wait);
    }

    public void editarCosto(String nombreCosto) {
        String xpath = "//tbody//td[contains(text(), '" + nombreCosto + "')]/..//button[contains(@title, 'ditar')]";
        clickXpathViaJS(xpath, driver, wait);
    }

    public boolean existeBotonBorrarCosto(String nombreCosto) {
        String xpath = "//tbody//td[contains(text(), '" + nombreCosto + "')]/..//button[contains(@title, 'orrar')]";
        return existeXpath(xpath, driver, wait);
    }

    public void borrarCosto(String nombreCosto) {
        String xpath = "//tbody//td[contains(text(), '" + nombreCosto + "')]/..//button[contains(@title, 'orrar')]";
        WebElement botonBorrar = encontrarElementoXpath(xpath, driver, wait);
        clickConAlerta(botonBorrar, driver, wait);
    }

    public boolean existeCosto(String nombreCosto) {
        String xpath = "//tbody//td[contains(text(), '" + nombreCosto + "')]";
        return existeXpath(xpath, driver, wait);
    }

    public boolean existeCostoEnCostosDirectos(String nombre, String cantidad, String valorUnitario, String depreciacion) {
        String xpath = "//strong[contains(text(), ' Costos Directos')]/../..//tbody//td[contains(text(), '" + nombre + "')]/following-sibling::td[1][contains(text(), '" + cantidad + "')]/following-sibling::td[1][contains(text(), '" + valorUnitario + "')]/following-sibling::td[1][contains(text(), '" + depreciacion + "')]";
        return existeXpath(xpath, driver, wait);
    }

    public boolean existeCostoEnCostosIndirectos(String nombre, String cantidad, String valorUnitario, String depreciacion) {
        String xpath = "//strong[contains(text(), ' Costos Indirectos')]/../..//tbody//td[contains(text(), '" + nombre + "')]/following-sibling::td[1][contains(text(), '" + cantidad + "')]/following-sibling::td[1][contains(text(), '" + valorUnitario + "')]/following-sibling::td[1][contains(text(), '" + depreciacion + "')]";
        return existeXpath(xpath, driver, wait);
    }
}
