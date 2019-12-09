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

public class Impresoras extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "a[href = 'http://beta.akademeia.ufm.edu/cotizacionbeta/public/impresoras/create']")
    WebElement botonAgregarImpresora;

    @FindBy(css = "form#confirmDel")
    WebElement botonConfirmarEliminar;

    public Impresoras(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public boolean existeBotonAgregarImpresora() {
        return existeWebElement(botonAgregarImpresora, driver, wait);
    }

    public void clickBotonAgregarImpresora() {
        click(botonAgregarImpresora, driver, wait);
    }

    public boolean existeImpresora(String nombreImpresora) {
        String xpath = "//tbody//span[contains(text(), '" + nombreImpresora + "')]";
        return existeXpath(xpath, driver, wait);
    }

    public boolean existeImpresora(String nombreImpresora, boolean lamina, String medida, String consumo) {
        String laminaString = lamina ? "Si" : "No";
        String xpath = "//tbody//span[contains(text(), '" + nombreImpresora + "')]/../..//td[contains(text(), '" + laminaString + "')]/..//td[contains(text(), '" + medida + "')]/..//td[contains(text(), '" + consumo + "')]";
        System.out.println("Verificando que exista elemento con xpath " + xpath);
        return existeXpath(xpath, driver, wait);
    }

    public boolean existeBotonEditarImpresora(String nombreImpresora) {
        String xpath = "//tbody//span[contains(text(), '" + nombreImpresora + "')]/../..//button[contains(@class, 'warning')]";
        return existeXpath(xpath, driver, wait);
    }

    public void clickBotonEditarImpresora(String nombreImpresora) {
        String xpath = "//tbody//span[contains(text(), '" + nombreImpresora + "')]/../..//button[contains(@class, 'warning')]";
        clickXpathViaJS(xpath, driver, wait);
    }

    public boolean existeBotonBorrarImpresora(String nombreImpresora) {
        String xpath = "//tbody//span[contains(text(), '" + nombreImpresora + "')]/../..//button[contains(@class, 'danger')]";
        return existeXpath(xpath, driver, wait);
    }

    public void clickBotonBorrarImpresora(String nombreImpresora) {
        String xpath = "//tbody//span[contains(text(), '" + nombreImpresora + "')]/../..//button[contains(@class, 'danger')]";
        clickXpathViaJS(xpath, driver, wait);
    }

    public void clickBotonAceptarEliminar() {
        click(botonConfirmarEliminar, driver, wait);
    }

    public void clickBotonAgregarMateriales(String nombreImpresora) {
        String xpath = "//tbody//span[contains(text(), '" + nombreImpresora + "')]/../..//button[contains(@class, 'primary')]";
        clickXpathViaJS(xpath, driver, wait);
    }
}
