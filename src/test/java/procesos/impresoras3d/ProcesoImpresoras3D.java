package procesos.impresoras3d;

import Auxiliar.Data;
import Auxiliar.MetodosGenerales;
import org.openqa.selenium.WebDriver;
import pom.impresoras3D.LoginImpresoras3DGoogle;

/**
 * @author Rafael Mazariegos
 */

public class ProcesoImpresoras3D extends MetodosGenerales {

    private final String USUARIO = "xik@ufm.edu";
    private final String CONTRASENA = "xik$ufm$2018";
    private final String USUARIO_ALTERNATIVO = "xiktst@ufm.edu";
    private final String CONTRASENA_ALTERNATIVA = "TyGI35pL$19";
    WebDriver driver;

    public ProcesoImpresoras3D(WebDriver driver) {
        this.driver = driver;
    }

    public void inicioDeSesion() {
        irAlSitio(Data.URLS.URL_IMPRESORAS_3D, driver);
        LoginImpresoras3DGoogle paginaLogin = new LoginImpresoras3DGoogle(driver);
        paginaLogin.clickBotonIngresarConCuentaDeGoogle();
        paginaLogin.ingresarCorreo(USUARIO);
        paginaLogin.clickBotonSiguienteCorreo();
        paginaLogin.ingresarContrasena(CONTRASENA);
        paginaLogin.clickBotonSiguienteContrasena();
    }

    public void inicioDeSesionConCuentaAlternativa() {
        irAlSitio(Data.URLS.URL_IMPRESORAS_3D, driver);
        LoginImpresoras3DGoogle paginaLogin = new LoginImpresoras3DGoogle(driver);
        paginaLogin.clickBotonIngresarConCuentaDeGoogle();
        paginaLogin.ingresarCorreo(USUARIO_ALTERNATIVO);
        paginaLogin.clickBotonSiguienteCorreo();
        paginaLogin.ingresarContrasena(CONTRASENA_ALTERNATIVA);
        paginaLogin.clickBotonSiguienteContrasena();
    }
}
