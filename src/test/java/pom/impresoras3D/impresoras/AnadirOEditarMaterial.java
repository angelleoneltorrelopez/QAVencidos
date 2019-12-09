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

public class AnadirOEditarMaterial extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "input#material")
    WebElement inputMaterial;

    @FindBy(css = "select#tipo_material")
    WebElement selectTipoMaterial;

    @FindBy(css = "select#unidad_medida")
    WebElement selectUnidadDeMedida;

    @FindBy(css = "input#consumible")
    WebElement inputConsumible;

    @FindBy(css = "input#precio_unitario")
    WebElement inputPrecioUnitario;

    @FindBy(css = "input#total_material")
    WebElement inputTotalDeMaterial;

    @FindBy(css = "button#crearManual")
    WebElement botonGuardar;

    public AnadirOEditarMaterial(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void ingresarNombreMaterial(String nombreMaterial) {
        ingresarTexto(inputMaterial, nombreMaterial, driver, wait);
    }

    public void seleccionarTipoDeMaterial(String tipoMaterial) {
        seleccionarOpcionPorTexto(selectTipoMaterial, tipoMaterial, driver, wait);
    }

    public boolean existeSelectUnidadDeMedida() {
        return existeWebElement(selectUnidadDeMedida, driver, wait);
    }

    public void seleccionarUnidadDeMedida(String unidadDeMedida) {
        seleccionarOpcionPorTexto(selectUnidadDeMedida, unidadDeMedida, driver, wait);
    }

    public void ingresarConsumible(String consumible) {
        ingresarTexto(inputConsumible, consumible, driver, wait);
    }

    public void ingresarPrecioUnitario(float precioUnitario) {
        ingresarTexto(inputPrecioUnitario, Float.toString(precioUnitario), driver, wait);
    }

    public void ingresarTotalDeMaterial(float totalMaterial) {
        ingresarTexto(inputTotalDeMaterial, Float.toString(totalMaterial), driver, wait);
    }

    public void clickBotonGuardar() {
        click(botonGuardar, driver, wait);
    }
}
