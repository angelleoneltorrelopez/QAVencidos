package pom.impresoras3D.impresoras;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author Rafael Mazariegos
 */

public class AnadirOEditarImpresora extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "input#nombre")
    WebElement inputNombre;

    @FindBy(css = "input#consumo")
    WebElement inputConsumo;

    @FindBy(css = "input[name = 'requiereLamina']")
    WebElement checkboxRequiereLaminas;

    @FindBy(css = "select#medida")
    WebElement selectMedida;

    @FindBy(css = "button.btn.btn-success")
    WebElement botonGuardar;

    @FindBy(css = "select#medida option")
    List<WebElement> listaOpcionesSelectMedida;

    public AnadirOEditarImpresora(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void ingresarNombre(String nombre) {
        ingresarTexto(inputNombre, nombre, driver, wait);
    }

    public void ingresarConsumo(float consumo) {
        ingresarTexto(inputConsumo, Float.toString(consumo), driver, wait);
    }

    public void clickCheckboxRequiereLaminas() {
        click(checkboxRequiereLaminas, driver, wait);
    }

    public void seleccionarOpcionMedida(String tipoMedida) {
        seleccionarOpcionPorTexto(selectMedida, tipoMedida, driver, wait);
    }

    public List<String> obtenerListaMedidas() {
        return obtenerTextoLista(listaOpcionesSelectMedida, driver);
    }

    public void clickBotonGuardar() {
        click(botonGuardar, driver, wait);
    }
}
