package pom.impresoras3D.usuarios;

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

public class AnadirOEditarUsuario extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "input#name")
    WebElement inputNombreUsuario;

    @FindBy(css = "input#email")
    WebElement inputCorreoUsuario;

    @FindBy(css = "select#rol")
    WebElement selectRol;

    @FindBy(css = "button#crearImagen")
    WebElement botonGuardar;

    @FindBy(css = "select#rol option[value]")
    List<WebElement> listaOpcionesSelectRol;

    public AnadirOEditarUsuario(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void ingresarNombreUsuario(String nombreUsuario) {
        ingresarTexto(inputNombreUsuario, nombreUsuario, driver, wait);
    }

    public void ingresarCorreoUsuario(String correoUsuario) {
        ingresarTexto(inputCorreoUsuario, correoUsuario, driver, wait);
    }

    public void seleccionarRol(String rol) {
        seleccionarOpcionPorTexto(selectRol, rol, driver, wait);
    }

    public int obtenerCantidadOpcionesRol() {
        return listaOpcionesSelectRol.size();
    }

    public void clickBotonGuardar() {
        click(botonGuardar, driver, wait);
    }

    public List<String> obtenerListadoOpcionesRol() {
        return obtenerTextoLista(listaOpcionesSelectRol, driver);
    }
}
