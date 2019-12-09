package procesos.impresoras3d.impresoras;


import clases.impresoras3d.impresoras.Impresora;
import org.openqa.selenium.WebDriver;
import pom.impresoras3D.MenuImpresoras3D;
import pom.impresoras3D.impresoras.AnadirOEditarImpresora;
import pom.impresoras3D.impresoras.Impresoras;

public class ProcesoImpresoras {

    WebDriver driver;

    public ProcesoImpresoras(WebDriver driver) {
        this.driver = driver;
    }

    public void crearImpresora(Impresora impresora) {
        MenuImpresoras3D menuImpresoras3D = new MenuImpresoras3D(driver);
        menuImpresoras3D.navegarSubmoduloImpresoras();

        Impresoras paginaImpresoras = new Impresoras(driver);
        paginaImpresoras.clickBotonAgregarImpresora();

        llenarFormulario(impresora);

        AnadirOEditarImpresora paginaAgregarImpresora = new AnadirOEditarImpresora(driver);
        paginaAgregarImpresora.clickBotonGuardar();
    }

    public void editarImpresora(String nombreImpresora, Impresora impresora) {
        MenuImpresoras3D menuImpresoras3D = new MenuImpresoras3D(driver);
        menuImpresoras3D.navegarSubmoduloImpresoras();

        Impresoras paginaImpresoras = new Impresoras(driver);
        paginaImpresoras.clickBotonEditarImpresora(nombreImpresora);

        llenarFormulario(impresora);

        AnadirOEditarImpresora paginaEditarImpresora = new AnadirOEditarImpresora(driver);
        paginaEditarImpresora.clickBotonGuardar();
    }

    private void llenarFormulario(Impresora impresora) {
        AnadirOEditarImpresora paginaFormulario = new AnadirOEditarImpresora(driver);

        paginaFormulario.ingresarNombre(impresora.nombre);
        paginaFormulario.ingresarConsumo(impresora.consumo);
        if (impresora.requiereLaminas) {
            paginaFormulario.clickCheckboxRequiereLaminas();
        }
        paginaFormulario.seleccionarOpcionMedida(impresora.medida);
    }
}
