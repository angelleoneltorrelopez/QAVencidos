package procesos.asistencias;

import Auxiliar.Data;
import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import pom.farmacia.LoginFarmacia;

/**
 * @author Angel Torre
 */
public class ProcesoFarmacia extends MetodosGenerales {

    private final String USUARIO = "angeltorrelopez@hotmail.com";
    private final String CONTRASENA = "angelzero87A";
    WebDriver driver;

    public ProcesoFarmacia(WebDriver driver) {
        this.driver = driver;
    }

    public void inicioDeSesion() {
        irAlSitio(Data.URLS.URL_FARMACIA, driver);
        LoginFarmacia loginFarmacia = new LoginFarmacia(driver);
        loginFarmacia.ingresarEmail(USUARIO);
        loginFarmacia.ingresarContrasena(CONTRASENA);
        loginFarmacia.clickBotonLogin();
        esperarCargaDeLaPagina(driver);
    }
}