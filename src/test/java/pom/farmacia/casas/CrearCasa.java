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

    @FindBy(xpath = "//div[@class = 'v-dialog__activator']/button")
    WebElement botonCrearCasa;

    @FindBy(name = "descripcion")
    WebElement inputDescripcion;

    @FindBy(xpath = "(//div[@class='v-select-list v-card theme--light']/div[@role = 'list'])[2]")
    WebElement selectProveedor;

    @FindBy (xpath = "//label[contains(text(),'Buscar')]/following::input[@id]")
    WebElement inputSearch;

    public void clickBotonCrearCasa(){
        botonCrearCasa.click();
       // click(botonCrearCasa,driver,wait);
    }

    public void ingresarDescripcion(String texto){
        ingresarTexto(inputDescripcion,texto,driver,wait);
    }


    public void seleccionarProveedor(String opcion){
        seleccionarOpcionPorTexto(selectProveedor,opcion,driver,wait);
    }

    public void escribirBusqueda(String texto){
       // ingresarTexto(inputSearch,texto, driver,wait);
        inputSearch.sendKeys(texto);
    }

    public CrearCasa(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 200);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 10);
        PageFactory.initElements(factory, this);
    }
}
