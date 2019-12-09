package pom.impresoras3D.cotizador;

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

public class Cotizador extends MetodosGenerales {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "nombre")
    WebElement inputNombre;

    @FindBy(id = "correo")
    WebElement inputCorreo;

    @FindBy(id = "carne")
    WebElement inputCarne;

    /* Impresora */

    @FindBy(id = "impresoraId")
    WebElement selectImpresora;

    @FindBy(css = "#impresoraId option[value]")
    List<WebElement> listadoOpcionesSelectImpresora;

    /* Material de Impresión */

    @FindBy(id = "material")
    WebElement selectMaterial;

    @FindBy(id = "uMedidaM")
    WebElement labelUnidadDeMedidaMaterial;

    @FindBy(id = "materialC")
    WebElement inputCantidadDeMaterial;

    @FindBy(css = "#material option:not([disabled])")
    List<WebElement> listadoOpcionesSelectMaterial;

    @FindBy(id = "materialT")
    WebElement spanTotalMaterial;

    /* Material de Soporte */

    @FindBy(id = "soporte")
    WebElement selectMaterialDeSoporte;

    @FindBy(id = "uMedidaS")
    WebElement labelUnidadDeMedidaSoporte;

    @FindBy(id = "soporteC")
    WebElement inputCantidadDeMaterialDeSoporte;

    @FindBy(css = "select#soporte option:not([disabled])")
    List<WebElement> listadoOpcionesSelectSoporte;

    @FindBy(id = "soporteT")
    WebElement spanTotalSoporte;

    /* Lamina */

    @FindBy(id = "laminaSelect")
    WebElement selectLamina;

    @FindBy(id = "uMedidaL")
    WebElement labelUnidadDeMedidaLamina;

    @FindBy(id = "laminaC")
    WebElement inputCantidadDeLamina;

    @FindBy(css = "select#laminaSelect option:not([disabled])")
    List<WebElement> listadoOpcionesSelectLamina;

    @FindBy(id = "laminaT")
    WebElement spanTotalLamina;

    /* Otros */

    @FindBy(id = "horas")
    WebElement inputHoras;

    @FindBy(id = "minutos")
    WebElement inputMinutos;

    @FindBy(id = "total")
    WebElement spanTotal;

    @FindBy(id = "buttonConsulta")
    WebElement botonCalcular;

    public Cotizador(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void ingresarNombre(String nombre) {
        ingresarTexto(inputNombre, nombre, driver, wait);
    }

    public void ingresarCorreo(String correo) {
        ingresarTexto(inputCorreo, correo, driver, wait);
    }

    public void ingresarCarne(String carne) {
        ingresarTexto(inputCarne, carne, driver, wait);
    }

    /* Impresora */

    public List<String> obtenerListadoImpresoras() {
        return obtenerTextoLista(listadoOpcionesSelectImpresora, driver);
    }

    public void seleccionarImpresora(String nombre) {
        System.out.println("Seleccionando la impresoras " + nombre);
        seleccionarOpcionPorTexto(selectImpresora, nombre, driver, wait);
    }

    /* Material de Impresión */

    public List<String> obtenerListadoDeMaterialesDeImpresion() {
        return obtenerTextoLista(listadoOpcionesSelectMaterial, driver);
    }

    public void seleccionarMaterialDeImpresion(String material) {
        System.out.println("Seleccionando el material de impresión " + material);
        seleccionarOpcionPorTexto(selectMaterial, material, driver, wait);
    }

    public String obtenerUnidadDeMedidaMaterialDeImpresion() {
        return obtenerTextoDeElemento(labelUnidadDeMedidaMaterial, driver, wait);
    }

    public void ingresarCantidadMaterialDeImpresion(String cantidad) {
        ingresarTexto(inputCantidadDeMaterial, cantidad, driver, wait);
    }

    public String obtenerTotalMaterialDeImpresion() {
        return obtenerTextoDeElemento(spanTotalMaterial, driver, wait);
    }

    /* Material de Soporte */

    public List<String> obtenerListadoDeMaterialesDeSoporte() {
        return obtenerTextoLista(listadoOpcionesSelectSoporte, driver);
    }

    public void seleccionarMaterialDeSoporte(String material) {
        System.out.println("Seleccionando el material de soporte " + material);
        seleccionarOpcionPorTexto(selectMaterialDeSoporte, material, driver, wait);
    }

    public String obtenerUnidadDeMedidaMaterialDeSoporte() {
        return obtenerTextoDeElemento(labelUnidadDeMedidaSoporte, driver, wait);
    }

    public void ingresarCantidadMaterialDeSoporte(String cantidad) {
        ingresarTexto(inputCantidadDeMaterialDeSoporte, cantidad, driver, wait);
    }

    public String obtenerTotalMaterialDeSoporte() {
        return obtenerTextoDeElemento(spanTotalSoporte, driver, wait);
    }

    /* Lamina */

    public boolean requiereLaminas() {
        return existeWebElement(selectLamina, driver, wait);
    }

    public List<String> obtenerListadoDeLaminas() {
        return obtenerTextoLista(listadoOpcionesSelectLamina, driver);
    }

    public void seleccionarLamina(String lamina) {
        seleccionarOpcionPorTexto(selectLamina, lamina, driver, wait);
    }

    public String obtenerUnidadDeMedidaLamina() {
        return obtenerTextoDeElemento(labelUnidadDeMedidaLamina, driver, wait);
    }

    public void ingresarCantidadLamina(String cantidad) {
        ingresarTexto(inputCantidadDeLamina, cantidad, driver, wait);
    }

    public String obtenerTotalLamina() {
        return obtenerTextoDeElemento(spanTotalLamina, driver, wait);
    }

    /* Otros */

    public void ingresarHoras(String horas) {
        ingresarTexto(inputHoras, horas, driver, wait);
    }

    public void ingresarMinutos(String minutos) {
        ingresarTexto(inputMinutos, minutos, driver, wait);
    }

    public String obtenerTotal() {
        return obtenerTextoDeElemento(spanTotal, driver, wait);
    }

    public void clickBotonCalcular() {
        click(botonCalcular, driver, wait);
    }
}
