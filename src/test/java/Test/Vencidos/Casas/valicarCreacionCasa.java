package Test.Vencidos.Casas;

import Auxiliar.TestBase;
import org.testng.annotations.Test;
import procesos.vencidos.ProcesoVencidos;

public class valicarCreacionCasa extends TestBase {

    @Test(description = "Verificación de creacion de casas", dataProvider = "Firefox")
    public void crearCasa(String browserName){
        ProcesoVencidos procesoVencidos = new ProcesoVencidos(driver);
        procesoVencidos.inicioDeSesion();
        System.out.println("Finalizar ");
    }

}
