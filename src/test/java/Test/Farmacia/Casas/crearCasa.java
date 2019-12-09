package Test.Farmacia.Casas;

import Auxiliar.TestBase;
import org.testng.annotations.Test;
import pom.farmacia.Menu;
import pom.farmacia.casas.CrearCasa;
import procesos.asistencias.ProcesoFarmacia;

/**
 * @author Angel Torre
 */
public class crearCasa extends TestBase {
    ProcesoFarmacia procesoFarmacia;
    Menu menu;
    CrearCasa crearCasa;

   @Test(description = "Verificación del funcionamiento de las Asistencias por Etapas", dataProvider = "Chrome")
    public void crearCasa(String browserName){
       iniciarVariables();
       procesoFarmacia.inicioDeSesion();
       menu.subMenuCasas();

       crearCasa.clickBotonCrearCasa();
       crearCasa.ingresarDescripcion("Prueba");
       crearCasa.seleccionarProveedor("ABASA");
   }

   public void iniciarVariables(){
       procesoFarmacia = new ProcesoFarmacia(driver);
       menu = new Menu(driver);
       crearCasa = new CrearCasa(driver);
   }
}
