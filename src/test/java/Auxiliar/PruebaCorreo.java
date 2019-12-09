package Auxiliar;

import org.testng.annotations.Test;
import procesos.asistencias.ProcesoFarmacia;

public class PruebaCorreo extends TestBase {

    @Test(description = "Notificar regresión", dataProvider = "Chrome")
    public void notificarRegresion(String browserName) {
        ProcesoFarmacia asistencias = new ProcesoFarmacia(driver);
        asistencias.inicioDeSesion();
        notificarRegresion(obtenerClase(), obtenerMetodo(), "No se recibe el correo de confirmación.");
    }
}