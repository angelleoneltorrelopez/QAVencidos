package pom.impresoras3D;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActividadDelUsuario extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "select[name = 'example_length']")
    WebElement selectMostrarRegistros;

    public ActividadDelUsuario(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void seleccionarCantidadRegistrosAMostrar(int registros) {
        seleccionarOpcionPorTexto(selectMostrarRegistros, Integer.toString(registros), driver, wait);
    }

    public boolean existeRegistro(String hora, String usuario, String accion, String tabla) {
        String xpath = "//tbody//td[contains(text()[2],'" + hora + "')]/following-sibling::td[contains(text(), '" + usuario + "')]/following-sibling::td[contains(text(), '" + accion + "')]/following-sibling::td[contains(text(), '" + tabla + "')]";
        System.out.println("Buscando xpath: " + xpath);
        return existeXpath(xpath, driver, wait);
    }
}
