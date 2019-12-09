package pom.impresoras3D;

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

public class MenuImpresoras3D extends MetodosGenerales {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "a[href = 'http://beta.akademeia.ufm.edu/cotizacionbeta/public/userActivity']")
    WebElement linkLogActividadUsuario;

    /* Elementos Navegación Menú */

    @FindBy(css = "a[href = 'http://beta.akademeia.ufm.edu/cotizacionbeta/public/costos/index']")
    WebElement menuCostosDirectosEIndirectos;

    @FindBy(css = "a[href = 'http://beta.akademeia.ufm.edu/cotizacionbeta/public/personal']")
    WebElement menuCostosPersonal;

    @FindBy(css = "a[href = 'http://beta.akademeia.ufm.edu/cotizacionbeta/public/calculadora']")
    WebElement menuCotizador;

    @FindBy(css = "a[href = 'http://beta.akademeia.ufm.edu/cotizacionbeta/public/historial']")
    WebElement menuHistorialDeCotizaciones;

    @FindBy(css = "a[href = 'http://beta.akademeia.ufm.edu/cotizacionbeta/public/impresoras/index']")
    WebElement menuImpresoras;

    @FindBy(css = "a[href = 'http://beta.akademeia.ufm.edu/cotizacionbeta/public/usuarios']")
    WebElement menuUsuarios;

    @FindBy(css = "a[href = 'http://beta.akademeia.ufm.edu/cotizacionbeta/public/role'")
    WebElement menuRoles;

    @FindBy(css = "a[href = 'http://beta.akademeia.ufm.edu/cotizacionbeta/public/variables']")
    WebElement menuVariables;

    public MenuImpresoras3D(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 15);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 15);
        PageFactory.initElements(factory, this);
    }

    public void navegarLogDeActividadDeUsuario() {
        clickElementoViaJS(linkLogActividadUsuario, driver, wait);
    }

    /* Funciones Navegación Menú */

    public void navegarSubmoduloCostosDirectosEIndirectos() {
        clickElementoViaJS(menuCostosDirectosEIndirectos, driver, wait);
    }

    public void navegarSubmoduloPersonal() {
        clickElementoViaJS(menuCostosPersonal, driver, wait);
    }

    public void navegarSubmoduloCotizador() {
        clickElementoViaJS(menuCotizador, driver, wait);
    }

    public void navegarSubmoduloImpresoras() {
        clickElementoViaJS(menuImpresoras, driver, wait);
    }

    public void navegarSubmoduloHistorialDeCotizaciones() {
        clickElementoViaJS(menuHistorialDeCotizaciones, driver, wait);
    }

    public void navegarSubmoduloUsuarios() {
        clickElementoViaJS(menuUsuarios, driver, wait);
    }

    public void navegarSubmoduloRoles() {
        clickElementoViaJS(menuRoles, driver, wait);
    }

    public void navegarSubmoduloVariables() {
        clickElementoViaJS(menuVariables, driver, wait);
    }
}
