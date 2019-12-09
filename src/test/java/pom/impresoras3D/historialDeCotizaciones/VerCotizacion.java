package pom.impresoras3D.historialDeCotizaciones;

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

public class VerCotizacion extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "(//thead)[1]")
    WebElement encabezadoNombreCliente;

    @FindBy(xpath = "(//table)[2]//b[contains(text(), 'Material')]/..")
    WebElement datoNombreMaterial;

    @FindBy(xpath = "(//table)[2]//b[contains(text(), 'Lamina')]/..")
    WebElement datoNombreLamina;

    @FindBy(xpath = "(//table)[2]//b[contains(text(), 'Soporte')]/..")
    WebElement datoNombreSoporte;

    @FindBy(xpath = "((//table)[2]//b[contains(text(), 'Material')]/../following-sibling::td)[1]")
    WebElement datoCantidadMaterial;

    @FindBy(xpath = "((//table)[2]//b[contains(text(), 'Lamina')]/../following-sibling::td)[1]")
    WebElement datoCantidadLamina;

    @FindBy(xpath = "((//table)[2]//b[contains(text(), 'Soporte')]/../following-sibling::td)[1]")
    WebElement datoCantidadSoporte;

    @FindBy(xpath= "((//table)[2]//td[contains(text(), 'Costos')]/following-sibling::td)[1]")
    WebElement datoCantidadTiempoImpresion;

    @FindBy(xpath = "((//table)[2]//b[contains(text(), 'Material')]/../following-sibling::td)[3]")
    WebElement datoTotalMaterial;

    @FindBy(xpath = "((//table)[2]//b[contains(text(), 'Lamina')]/../following-sibling::td)[3]")
    WebElement datoTotalLamina;

    @FindBy(xpath = "((//table)[2]//b[contains(text(), 'Soporte')]/../following-sibling::td)[3]")
    WebElement datoTotalSoporte;

    @FindBy(xpath = "((//table)[2]//strong[contains(text(), 'TOTAL')]/../following-sibling::td)[3]")
    WebElement datoTotal;

    public VerCotizacion(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public String obtenerNombreCliente() {
        return encabezadoNombreCliente.getText();
    }

    public String obtenerNombreMaterial() {
        return datoNombreMaterial.getText();
    }

    public String obtenerNombreLamina() {
        return datoNombreLamina.getText();
    }

    public String obtenerNombreSoporte() {
        return datoNombreSoporte.getText();
    }

    public String obtenerCantidadMaterial() {
        return datoCantidadMaterial.getText();
    }

    public String obtenerCantidadLamina() {
        return datoCantidadLamina.getText();
    }

    public String obtenerCantidadSoporte() {
        return datoCantidadSoporte.getText();
    }

    public String obtenerTiempoImpresion() {
        return datoCantidadTiempoImpresion.getText();
    }

    public String obtenerTotalMaterial() {
        return datoTotalMaterial.getText();
    }

    public String obtenerTotalLamina() {
        return datoTotalLamina.getText();
    }

    public String obtenerTotalSoporte() {
        return datoTotalSoporte.getText();
    }

    public String obtenerTotal() {
        return datoTotalLamina.getText();
    }
}
