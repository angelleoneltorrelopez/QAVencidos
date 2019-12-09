package Auxiliar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class SaltoPagina extends MetodosGenerales {
    private WebDriver driver;
    private String originalHandle;

    public SaltoPagina(WebDriver driver) {
        this.driver = driver;
        this.originalHandle = this.driver.getWindowHandle();
    }
    public void obtenerPaginaActual(){
        originalHandle = driver.getWindowHandle();
    }
    public String obtenerPaginaTitulo(){
        return driver.getTitle();
    }
    public void switchAndClose() {
        this.switchToNextWindow();
        this.goMain();
    }
    public void switchToNextWindow() {
        ArrayList<String> newWindow = new ArrayList<String>(driver.getWindowHandles());
        newWindow.remove(this.originalHandle);
        driver.switchTo().window(newWindow.get(0));
        esperarCargaDeLaPagina(driver);
    }
    public void goMain() {
        driver.close();
        driver.switchTo().window(originalHandle);
        esperarCargaDeLaPagina(driver);
    }
    public void irVentanaNumero(int numeroVentana){
        esperarNumeroDeVentanas(numeroVentana);
        ArrayList<String> ventanasAbiertas = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(ventanasAbiertas.get(numeroVentana-1));
        esperarCargaDeLaPagina(driver);
    }
    private void esperarNumeroDeVentanas(final int numberOfWindows) {
        new WebDriverWait(driver, 5) {
        }.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (driver.getWindowHandles().size() == numberOfWindows);
            }
        });
    }
    public void goFrame(String idFrame) {
        driver.switchTo().frame(idFrame);
    }
    public void outFrame() {
        this.driver.switchTo().defaultContent();
    }
}
