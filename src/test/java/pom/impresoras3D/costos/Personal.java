package pom.impresoras3D.costos;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Personal extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "button[title = 'Agregar nuevo personal'] ")
    WebElement botonAgregarPersonal;

    @FindBy(css = "form#confirmDel button#modalAccept")
    WebElement botonAceptarEliminarModal;

    public Personal(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void clickBotonAgregarPersonal() {
        click(botonAgregarPersonal, driver, wait);
    }

    public void clickBotonEditarPersonal(String nombrePersonal) {
        String xpath = "//tbody//td[contains(text(), '" + nombrePersonal + "')]/..//button[contains(@title, 'ditar')]";
        clickXpathViaJS(xpath, driver, wait);
    }

    public void clickBotonBorrarPersonal(String nombrePersonal) {
        String xpath = "//tbody//td[contains(text(), '" + nombrePersonal + "')]/..//button[contains(@title, 'orrar')]";
        clickXpathViaJS(xpath, driver, wait);
    }

    public void clickBotonAceptarEliminar() {
        click(botonAceptarEliminarModal, driver, wait);
    }

    public boolean existePersonal(String nombre){
        String xpath = "//tbody//td[contains(text(), '" + nombre + "')]";
        return existeXpath(xpath, driver, wait);
    }

    public boolean existePersonal(String nombre, String sueldoLiquido, String sueldoPrestaciones) {
        String xpath = "//tbody//td[contains(text(), '" + nombre + "')]/..//span[contains(text(), '" + sueldoLiquido + "')]/../..//span[contains(text(), '" + sueldoPrestaciones + "')]";
        return existeXpath(xpath, driver, wait);
    }
}
