package pom.impresoras3D.historialDeCotizaciones;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Rafael Mazariegos
 */

public class HistorialDeCotizaciones extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    public HistorialDeCotizaciones(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void clickVerCotizacion(String cliente, String total) {
        String xpath = "//tbody//td[contains(text(), '" + cliente + "')]/following-sibling::td[contains(text(), '" + total + "')]/following-sibling::td//button[contains(@title, 'er cotizaci')]";
        WebElement botonDescargar = encontrarElementoXpath(xpath, driver, wait);
        click(botonDescargar, driver, wait);
    }

    public void clickDescargarCotizacion(String cliente, String total) {
        String xpath = "//tbody//td[contains(text(), '" + cliente + "')]/following-sibling::td[contains(text(), '" + total + "')]/following-sibling::td//button[contains(@title, 'escargar')]";
        WebElement botonDescargar = encontrarElementoXpath(xpath, driver, wait);
        click(botonDescargar, driver, wait);
    }
}
