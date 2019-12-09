package pom.farmacia.casas;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author Angel Torre
 */
public class CrearCasa extends MetodosGenerales {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[contains(text(),'Crear Casa')]")
    WebElement botonCrearCasa;

    @FindBy(name = "descripcion")
    WebElement inputDescripcion;

    @FindBy(xpath = "(//div[@class='v-select-list v-card theme--light']/div[@role = 'list'])[2]")
    WebElement selectProveedor;

    public void clickBotonCrearCasa(){
        esperar(1000);
        click(botonCrearCasa,driver,wait);
    }

    public void ingresarDescripcion(String texto){
        ingresarTexto(inputDescripcion,texto,driver,wait);
    }


    public void seleccionarProveedor(String opcion){
        seleccionarOpcionPorTexto(selectProveedor,opcion,driver,wait);
    }

    public CrearCasa(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }
}
