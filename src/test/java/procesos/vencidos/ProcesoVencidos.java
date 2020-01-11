package procesos.vencidos;

import Auxiliar.Data;
import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import pom.vencidos.LoginVencidos;

public class ProcesoVencidos extends MetodosGenerales {
    private final String USUARIO = "ANGEL";
    private final String CONTRASENA = "ANGelzero87A";

    WebDriver driver;

    public ProcesoVencidos(WebDriver driver) {
        this.driver = driver;
    }

    public void inicioDeSesion() {
        irAlSitio(Data.URLS.URL_VENCIOS, driver);
        LoginVencidos loginVencidos = new LoginVencidos(driver);
        if (loginVencidos.existeUlIniciarSession()) {
            loginVencidos.clickUlIniciarSession();
            loginVencidos.ingresarUsername(USUARIO);
            loginVencidos.ingresarPassword(CONTRASENA);
            loginVencidos.clickBotonLogin();
            esperarCargaDeLaPagina(driver);
        }
    }
}
