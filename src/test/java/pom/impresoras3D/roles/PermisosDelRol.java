package pom.impresoras3D.roles;

import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PermisosDelRol extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "(//div[contains(text(), 'Usuarios')]/following-sibling::div//input)[1]")
    WebElement checkBoxCrearUsuarios;

    @FindBy(xpath = "(//div[contains(text(), 'Usuarios')]/following-sibling::div//input)[2]")
    WebElement checkBoxEditarUsuarios;

    @FindBy(xpath = "(//div[contains(text(), 'Usuarios')]/following-sibling::div//input)[3]")
    WebElement checkBoxEliminarUsuarios;

    @FindBy(xpath = "(//div[contains(text(), 'Costos')]/following-sibling::div//input)[1]")
    WebElement checkBoxCrearCostos;

    @FindBy(xpath = "(//div[contains(text(), 'Costos')]/following-sibling::div//input)[2]")
    WebElement checkBoxEditarCostos;

    @FindBy(xpath = "(//div[contains(text(), 'Costos')]/following-sibling::div//input)[3]")
    WebElement checkBoxEliminarCostos;

    @FindBy(xpath = "(//div[contains(text(), 'Impresoras')]/following-sibling::div//input)[1]")
    WebElement checkBoxCrearImpresoras;

    @FindBy(xpath = "(//div[contains(text(), 'Impresoras')]/following-sibling::div//input)[2]")
    WebElement checkBoxEditarImpresoras;

    @FindBy(xpath = "(//div[contains(text(), 'Impresoras')]/following-sibling::div//input)[3]")
    WebElement checkBoxEliminarImpresoras;

    @FindBy(xpath = "(//div[contains(text(), 'Materiales')]/following-sibling::div//input)[1]")
    WebElement checkBoxCrearMateriales;

    @FindBy(xpath = "(//div[contains(text(), 'Materiales')]/following-sibling::div//input)[2]")
    WebElement checkBoxEditarMateriales;

    @FindBy(xpath = "(//div[contains(text(), 'Materiales')]/following-sibling::div//input)[3]")
    WebElement checkBoxEliminarMateriales;

    @FindBy(css = "button#crearImagen")
    WebElement botonGuardar;

    public PermisosDelRol(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void asignarPermisoCrearUsuarios(boolean activar) {
        if(checkBoxCrearUsuarios.isSelected() ^ activar) {
            click(checkBoxCrearUsuarios, driver, wait);
        }
    }

    public void asignarPermisoEditarUsuarios(boolean activar) {
        if(checkBoxEditarUsuarios.isSelected() ^ activar) {
            click(checkBoxEditarUsuarios, driver, wait);
        }
    }

    public void asignarPermisoEliminarUsuarios(boolean activar) {
        if(checkBoxEliminarUsuarios.isSelected() ^ activar) {
            click(checkBoxEliminarUsuarios, driver, wait);
        }
    }

    public void asignarPermisoCrearCostos(boolean activar) {
        if(checkBoxCrearCostos.isSelected() ^ activar) {
            click(checkBoxCrearCostos, driver, wait);
        }
    }

    public void asignarPermisoEditarCostos(boolean activar) {
        if(checkBoxEditarCostos.isSelected() ^ activar) {
            click(checkBoxEditarCostos, driver, wait);
        }
    }

    public void asignarPermisoEliminarCostos(boolean activar) {
        if(checkBoxEliminarCostos.isSelected() ^ activar) {
            click(checkBoxEliminarCostos, driver, wait);
        }
    }

    public void asignarPermisoCrearImpresoras(boolean activar) {
        if(checkBoxCrearImpresoras.isSelected() ^ activar) {
            click(checkBoxCrearImpresoras, driver, wait);
        }
    }

    public void asignarPermisoEditarImpresoras(boolean activar) {
        if(checkBoxEditarImpresoras.isSelected() ^ activar) {
            click(checkBoxEditarImpresoras, driver, wait);
        }
    }

    public void asignarPermisoEliminarImpresoras(boolean activar) {
        if(checkBoxEliminarImpresoras.isSelected() ^ activar) {
            click(checkBoxEliminarImpresoras, driver, wait);
        }
    }

    public void asignarPermisoCrearMateriales(boolean activar) {
        if(checkBoxCrearMateriales.isSelected() ^ activar) {
            click(checkBoxCrearMateriales, driver, wait);
        }
    }

    public void asignarPermisoEditarMateriales(boolean activar) {
        if(checkBoxEditarMateriales.isSelected() ^ activar) {
            click(checkBoxEditarMateriales, driver, wait);
        }
    }

    public void asignarPermisoEliminarMateriales(boolean activar) {
        if(checkBoxEliminarMateriales.isSelected() ^ activar) {
            click(checkBoxEliminarMateriales, driver, wait);
        }
    }

    public void clickBotonGuardar() {
        click(botonGuardar, driver, wait);
    }
}
