package pom.impresoras3D.costos;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AnadirOEditarPersonal extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "input#personal")
    WebElement inputNombrePersonal;

    @FindBy(css = "input#sueldo_liquido")
    WebElement inputSueldoLiquido;

    @FindBy(css = "button.btn.btn-success")
    WebElement botonGuardar;

    public AnadirOEditarPersonal(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void ingresarNombre(String nombre) {
        ingresarTexto(inputNombrePersonal, nombre, driver, wait);
    }

    public void ingresarSueldo(String sueldo) {
        ingresarTexto(inputSueldoLiquido, sueldo, driver, wait);
    }

    public void clickBotonGuardar() {
        click(botonGuardar, driver, wait);
    }
}
