package pom.impresoras3D.variables;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Variables extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "input#margen")
    WebElement inputMargen;

    @FindBy(css = "input#dias")
    WebElement inputDiasAlMes;

    @FindBy(css = "input#horas")
    WebElement inputHorasAlDia;

    @FindBy(css = "input#horasmes")
    WebElement inputHorasAlMes;

    @FindBy(css = "button.btn.btn-success")
    WebElement botonGuardar;

    public Variables(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void ingresarMargen(String margen) {
        ingresarTexto(inputMargen, margen, driver, wait);
    }

    public String obtenerMargen() {
        return inputMargen.getAttribute("value");
    }

    public void ingresarDiasAlMes(String diasAlMes) {
        ingresarTexto(inputDiasAlMes, diasAlMes, driver, wait);
    }

    public String obtenerDiasAlMes() {
        return inputDiasAlMes.getAttribute("value");
    }

    public void ingresarHorasAlDia(String horasAlDia) {
        ingresarTexto(inputHorasAlDia, horasAlDia, driver, wait);
    }

    public String obtenerHorasAlDia() {
        return inputHorasAlDia.getAttribute("value");
    }

    public void ingresarHorasAlMes(String horasAlMes) {
        ingresarTexto(inputHorasAlMes, horasAlMes, driver, wait);
    }

    public String obtenerHorasAlMes() {
        return inputHorasAlMes.getAttribute("value");
    }

    public void clickBotonGuardar() {
        click(botonGuardar, driver, wait);
    }
}
